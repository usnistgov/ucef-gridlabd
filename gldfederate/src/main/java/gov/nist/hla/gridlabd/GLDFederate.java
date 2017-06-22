package gov.nist.hla.gridlabd;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import gov.nist.hla.FederateAmbassador;
import gov.nist.hla.Interaction;
import gov.nist.hla.ObjectReflection;
import gov.nist.hla.som.SOMQuery;
import gov.nist.hla.som.SOMReader;
import hla.rti.AsynchronousDeliveryAlreadyEnabled;
import hla.rti.AttributeHandleSet;
import hla.rti.EnableTimeConstrainedPending;
import hla.rti.EnableTimeRegulationPending;
import hla.rti.FederationExecutionDoesNotExist;
import hla.rti.LogicalTime;
import hla.rti.RTIambassador;
import hla.rti.RTIexception;
import hla.rti.ResignAction;
import hla.rti.RestoreInProgress;
import hla.rti.SaveInProgress;
import hla.rti.SuppliedAttributes;
import hla.rti.SuppliedParameters;
import hla.rti.TimeConstrainedAlreadyEnabled;
import hla.rti.TimeRegulationAlreadyEnabled;
import hla.rti.jlc.EncodingHelpers;
import hla.rti.jlc.RtiFactoryFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.portico.impl.hla13.types.DoubleTime;
import org.portico.impl.hla13.types.DoubleTimeInterval;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class GLDFederate {    
    private static final Logger logger = LogManager.getLogger();
    
    private static final String INTERACTION_ROOT = "InteractionRoot.C2WInteractionRoot";
    private static final String SIMULATION_END = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimEnd";
    private static final String SIMULATION_TIME = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime";
    private static final String READY_TO_POPULATE = "readyToPopulate";
    private static final String READY_TO_RUN = "readyToRun";
    private static final String READY_TO_RESIGN = "readyToResign";
    
    private Configuration configuration;
    final private GLDClient client;
    private Process gridlabd = null;
    
    private RTIambassador rtiAmb;
    private FederateAmbassador fedAmb;
    private SOMQuery objectModel;
    
    private HashMap<String, Integer> objectInstances = new HashMap<String, Integer>();
    
    private boolean receivedSimEnd = false;
    private boolean receivedSimTime = false;
    private boolean reachedStopTime = false;
    private boolean gridlabdStarted = false;
    
    public GLDFederate(String filepath)
            throws IOException {
        loadConfiguration(filepath);
        client = new GLDClient("localhost", configuration.getServerPortNumber());
        try {
            rtiAmb = RtiFactoryFactory.getRtiFactory().createRtiAmbassador();
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
        fedAmb = new FederateAmbassador();
        loadObjectModel();
    }
    
    private void loadConfiguration(String filepath)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); 
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        logger.info("reading GridLAB-D federate configuration from " + filepath);
        try {
            configuration = mapper.readValue(new File(filepath), Configuration.class);
        } catch (JsonParseException e) {
            logger.error("invalid format for YAML configuration file " + filepath);
            throw new IOException(e);
        } catch (JsonMappingException e) {
            logger.error("invalid options in YAML configuration file " + filepath);
            throw new IOException(e);
        }
    }
    
    private void loadObjectModel()
            throws IOException {
        try {
            SOMReader reader = new SOMReader();
            reader.readXML(configuration.getSomFilepath());
            objectModel = new SOMQuery(reader);
        } catch (ParserConfigurationException e) {
            throw new IOException(e);
        }
    }
    
    public void execute()
            throws InterruptedException,
                   GLDException,
                   IOException {
        try {
            joinFederationExecution();

            enableAsynchronousDelivery();
            enableTimeConstrained();
            enableTimeRegulation();
            publishAndSubscribe();
            createObjectInstances();

            synchronize(READY_TO_POPULATE);
            if (configuration.getUnixTimeStart() < 0) {
                if (!objectModel.getSubscribedInteractions().contains(SIMULATION_TIME)) {
                    logger.error("start time not specified and no subscription to " + SIMULATION_TIME);
                    throw new RuntimeException("no start time specified for the GridLAB-D simulation");
                }
                logger.info("waiting to receive " + SIMULATION_TIME);
                while (!receivedSimTime) {
                    // how to recover if we receive simulation end?
                    tick();
                }
            } else {
                logger.info("starting simulation without waiting for " + SIMULATION_TIME);
            }
            startGLD();
            connectToGLD();
            synchronize(READY_TO_RUN);
            
            if (configuration.getUnixTimeStop() < 0) {
                logger.info("no simulation stop time specified");
            }
            if (!objectModel.getSubscribedInteractions().contains(SIMULATION_END)) {
                logger.info("no subscription for " + SIMULATION_END);
                if (configuration.getUnixTimeStop() < 0) {
                    logger.warn("no exit condition specified; will run forever until Ctrl+C");
                }
            }
            final double timestep = configuration.getLogicalTimeStep() * configuration.getSimulationTimeScale();
            while (!receivedSimEnd && !reachedStopTime) {
                double currentLogicalTime = fedAmb.getLogicalTime();
                
                sendPublications();
                if (currentLogicalTime >= configuration.getUnixTimeStop()) {
                    reachedStopTime = true;
                } else {
                    handleSubscriptions();
                    try {
                        int code = gridlabd.exitValue();
                        logger.info("GridLAB-D done with exit value = " + code);
                        reachedStopTime = true; // or due to exception; might check code value
                    } catch (IllegalThreadStateException e) {
                        double nextLogicalTime = currentLogicalTime + timestep;
                        if (nextLogicalTime > configuration.getUnixTimeStop()) {
                            nextLogicalTime = configuration.getUnixTimeStop();
                        }
                        advanceSimulationTime((int)nextLogicalTime); // truncate
                        advanceLogicalTime();
                    }
                }
            }
        } finally {
            try {
                if (receivedSimEnd) {
                    synchronize(READY_TO_RESIGN);
                    resignFederationExecution();
                } else if (reachedStopTime) {
                    resignFederationExecution();
                }
            } catch (Exception e) {
                logger.warn("failed to resign federation execution", e);
            }
        }
    }
    
    private void createObjectInstances() {
        try {
            for (String objectClassName : objectModel.getPublishedObjects()) {
                int objectClassHandle = rtiAmb.getObjectClassHandle(objectClassName);
                int objectInstanceHandle = rtiAmb.registerObjectInstance(objectClassHandle);
                objectInstances.put(objectClassName, objectInstanceHandle);
                logger.info("registered instance for " + objectClassName + " with handle " + objectInstanceHandle);
            }
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
    }
    
    private void sendPublications()
            throws GLDException {
        LogicalTime timestamp = new DoubleTime(fedAmb.getLogicalTime() + configuration.getLookahead());
        
        try {
            Set<String> rootParameters = objectModel.getParameterSet(INTERACTION_ROOT);
            for (String interactionName : objectModel.getPublishedInteractions()) {
                int interactionHandle = rtiAmb.getInteractionClassHandle(interactionName);
                
                SuppliedParameters suppliedParameters = RtiFactoryFactory.getRtiFactory().createSuppliedParameters();
                for (String parameterName : objectModel.getParameterSet(interactionName)) {
                    if (rootParameters.contains(parameterName)) {
                        // we probably need to set these to some reasonable default value
                        logger.debug("skipping parameter " + parameterName + " from " + INTERACTION_ROOT);
                        continue;
                    }
                    String object = interactionName;
                    String property = parameterName;
                    String value;
                    
                    String dataType = objectModel.getParameterType(interactionName, parameterName);
                    if (dataType.contains("integer")) {
                        // required to strip the units from the returned value
                        value = Integer.toString(client.getObjectPropertyAsInteger(object, property));
                    } else if (dataType.contains("float")) {
                        // required to strip the units from the returned value
                        value = Double.toString(client.getObjectPropertyAsDouble(object, property));
                    } else {
                        value = client.getObjectProperty(object, property);
                    }
                    
                    int parameterHandle = rtiAmb.getParameterHandle(parameterName, interactionHandle);
                    byte[] parameterValue = EncodingHelpers.encodeString(value);
                    suppliedParameters.add(parameterHandle, parameterValue);
                }
                
                rtiAmb.sendInteraction(interactionHandle, suppliedParameters, null, timestamp);
            }
            
            for (String objectClassName : objectModel.getPublishedObjects()) {
                int classHandle = rtiAmb.getObjectClassHandle(objectClassName);
                int objectHandle = objectInstances.get(objectClassName);
                
                SuppliedAttributes suppliedAttributes = RtiFactoryFactory.getRtiFactory().createSuppliedAttributes();
                for (String attributeName : objectModel.getPublishedAttributes(objectClassName)) {
                    String object = objectClassName;
                    String property = attributeName;
                    String value;
                    
                    String dataType = objectModel.getAttributeType(objectClassName, attributeName);
                    if (dataType.contains("integer")) {
                        // required to strip the units from the returned value
                        value = Integer.toString(client.getObjectPropertyAsInteger(object, property));
                    } else if (dataType.contains("float")) {
                        // required to strip the units from the returned value
                        value = Double.toString(client.getObjectPropertyAsDouble(object, property));
                    } else {
                        value = client.getObjectProperty(object, property);
                    }
                    
                    int attributeHandle = rtiAmb.getAttributeHandle(attributeName, classHandle);
                    byte[] attributeValue = EncodingHelpers.encodeString(value);
                    suppliedAttributes.add(attributeHandle, attributeValue);
                }
                
                rtiAmb.updateAttributeValues(objectHandle, suppliedAttributes, null, timestamp);
            }
        } catch (RTIexception e) {
            // might want to separate this out into recoverable cases
            throw new RTIAmbassadorException(e);
        }
    }

    private void handleSubscriptions() {
        try {
            Interaction receivedInteraction;
            while ((receivedInteraction = fedAmb.nextInteraction()) != null) {
                int interactionHandle = receivedInteraction.getInteractionClassHandle();
                String interactionName = rtiAmb.getInteractionClassName(interactionHandle);
                logger.debug("received interaction name=" + interactionName);

                HashMap<String, String> parameters = new HashMap<String, String>();
                for (int i = 0; i < receivedInteraction.getParameterCount(); i++) {
                    int parameterHandle = receivedInteraction.getParameterHandle(i);
                    String parameterName = rtiAmb.getParameterName(parameterHandle, interactionHandle);
                    String parameterValue = receivedInteraction.getParameterValue(i);
                    logger.debug(parameterName + "=" + parameterValue);
                    parameters.put(parameterName, parameterValue);
                }

                if (interactionName.equals(SIMULATION_END)) {
                    receivedSimEnd = true;
                } else if (interactionName.equals(SIMULATION_TIME)) {
                    configuration.setUnixTimeStart(Double.valueOf(parameters.get("timeStart")).intValue());
                    configuration.setUnixTimeStop(Double.valueOf(parameters.get("timeStop")).intValue());
                    configuration.setSimulationTimeScale(Double.valueOf(parameters.get("timeScale")).intValue());
                    receivedSimTime = true;
                } else if (gridlabdStarted) {
                    handleInteraction(interactionName, parameters);
                } else {
                    logger.warn("dropped interaction " + interactionName);
                }
            }

            ObjectReflection receivedObjectReflection;
            while ((receivedObjectReflection = fedAmb.nextObjectReflection()) != null) {
                int objectClassHandle = receivedObjectReflection.getObjectClass();
                String objectClassName = rtiAmb.getObjectClassName(objectClassHandle);
                String objectName = receivedObjectReflection.getObjectName();
                logger.debug("received object class=" + objectClassName + " with name=" + objectName);

                HashMap<String, String> attributes = new HashMap<String, String>();
                for (int i = 0; i < receivedObjectReflection.getAttributeCount(); i++) {
                    int attributeHandle = receivedObjectReflection.getAttributeHandle(i);
                    String attributeName = rtiAmb.getAttributeName(attributeHandle, objectClassHandle);
                    String attributeValue = receivedObjectReflection.getAttributeValue(i);
                    logger.debug(attributeName + "=" + attributeValue);
                    attributes.put(attributeName, attributeValue);
                }
                
                if (gridlabdStarted) {
                    handleObjectReflection(objectName, objectClassName, attributes);
                } else {
                    logger.warn("dropped object reflection " + objectName);
                }
            }

            String removedObjectName;
            while ((removedObjectName = fedAmb.nextRemovedObjectName()) != null) {
                // do nothing
                logger.info("removed object instance with name " + removedObjectName);
            }
        } catch(RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
    }

    private void handleInteraction(String interactionName, HashMap<String, String> parameters) {
        Set<String> rootParameters = objectModel.getParameterSet(INTERACTION_ROOT);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (rootParameters.contains(entry.getKey())) {
                logger.debug("skipping parameter " + entry.getKey() + " from " + INTERACTION_ROOT);
                continue;
            }
            String object = interactionName;
            String property = entry.getKey();
            String value = entry.getValue();
            try {
                // this cannot be more efficient due to the limitations of GridLAB-D
                client.setObjectProperty(object, property, value);
                logger.debug("set " + object + ":" + property + "=" + value);
            } catch (GLDException e) {
                logger.error("could not set " + object + ":" + property + "=" + value);
            }
        }
    }
    
    private void handleObjectReflection(String objectName, String objectClassName, HashMap<String, String> attributes) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String object = objectClassName;
            String property = entry.getKey();
            String value = entry.getValue();
            try {
                // this cannot be more efficient due to the limitations of GridLAB-D
                client.setObjectProperty(object, property, value);
                logger.debug("set " + object + ":" + property + "=" + value);
            } catch (GLDException e) {
                logger.error("could not set " + object + ":" + property + "=" + value);
            }
        }
    }
    
    private void tick() {
        try {
            rtiAmb.tick();
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
        handleSubscriptions();
    }

    private void joinFederationExecution()
            throws InterruptedException {
        boolean joinSuccessful = false;
        final String federateName = configuration.getFederateName();
        final String federationName = configuration.getFederationName();

        for (int i = 0; !joinSuccessful && i < configuration.getMaxConnectionAttempts(); i++) {
            if (i > 0) {
                logger.info("next join attempt in " + configuration.getWaitReconnectMs() + " ms...");
                Thread.sleep(configuration.getWaitReconnectMs());
            }

            try {
                logger.info("joining federation " + federationName + " as " + federateName + " (" + i + ")");
                rtiAmb.joinFederationExecution(federateName, federationName, fedAmb, null);
                joinSuccessful = true;
            } catch (FederationExecutionDoesNotExist e) {
                logger.warn("federation execution does not exist: " + federationName);
            } catch (SaveInProgress e) {
                logger.warn("failed to join federation: save in progress");
            } catch (RestoreInProgress e) {
                logger.warn("failed to join federation: restore in progress");
            } catch (RTIexception e) {
                throw new RTIAmbassadorException(e);
            }
        }
    }

    // enable Receive Order messages during any tick call
    private void enableAsynchronousDelivery() {
        try {
            logger.info("enabling asynchronous delivery of receive order messages");
            rtiAmb.enableAsynchronousDelivery();
        } catch (AsynchronousDeliveryAlreadyEnabled e) {
            logger.info("asynchronous delivery already enabled");
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
    }

    private void enableTimeConstrained() {
        try {
            logger.info("enabling time constrained");
            rtiAmb.enableTimeConstrained();
            while (fedAmb.isTimeConstrained() == false) {
                tick();
            }
        } catch (TimeConstrainedAlreadyEnabled e) {
            logger.info("time constrained already enabled");
        } catch (EnableTimeConstrainedPending e) {
            logger.warn("multiple attempts made to enable time constrained mode");
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
    }

    private void enableTimeRegulation() {
        try {
            logger.info("enabling time regulation");
            rtiAmb.enableTimeRegulation(
                    new DoubleTime(fedAmb.getLogicalTime()),
                    new DoubleTimeInterval(configuration.getLookahead())
                    );
            while (fedAmb.isTimeRegulating() == false) {
                tick();
            }
        } catch (TimeRegulationAlreadyEnabled e) {
            logger.info("time regulation already enabled");
        } catch (EnableTimeRegulationPending e) {
            logger.warn("multiple attempts made to enable time regulation mode");
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
    }

    private void publishAndSubscribe() {
        try {
            for (String interactionName : objectModel.getPublishedInteractions()) {
                logger.info("creating HLA publication for the interaction " + interactionName);
                int interactionHandle = rtiAmb.getInteractionClassHandle(interactionName);
                rtiAmb.publishInteractionClass(interactionHandle);
            }
            for (String interactionName : objectModel.getSubscribedInteractions()) {
                logger.info("creating HLA subscription for the interaction " + interactionName);
                int interactionHandle = rtiAmb.getInteractionClassHandle(interactionName);
                rtiAmb.subscribeInteractionClass(interactionHandle);
            }
            for (String objectClass : objectModel.getPublishedObjects()) {
                int objectHandle = rtiAmb.getObjectClassHandle(objectClass);
                AttributeHandleSet attributes = RtiFactoryFactory.getRtiFactory().createAttributeHandleSet();

                for (String attributeName : objectModel.getPublishedAttributes(objectClass)) {
                    logger.info("creating HLA publication for object=" + objectClass + ", attribute=" + attributeName);
                    int attributeHandle = rtiAmb.getAttributeHandle(attributeName, objectHandle);
                    attributes.add(attributeHandle);
                }
                rtiAmb.publishObjectClass(objectHandle, attributes);
            }
            for (String objectClass : objectModel.getSubscribedObjects()) {
                int objectHandle = rtiAmb.getObjectClassHandle(objectClass);
                AttributeHandleSet attributes = RtiFactoryFactory.getRtiFactory().createAttributeHandleSet();

                for (String attributeName : objectModel.getSubscribedAttributes(objectClass)) {
                    logger.info("creating HLA subscription for object=" + objectClass + ", attribute=" + attributeName);
                    int attributeHandle = rtiAmb.getAttributeHandle(attributeName, objectHandle);
                    attributes.add(attributeHandle);
                }
                rtiAmb.subscribeObjectClassAttributes(objectHandle, attributes);
            }
        } catch(RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
    }

    private void synchronize(String label) {  
        logger.info("waiting for announcement of the synchronization point " + label);
        while (fedAmb.isSynchronizationPointPending(label) == false) {
            tick();
        }

        try {
            rtiAmb.synchronizationPointAchieved(label);
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }

        logger.info("waiting for federation to synchronize on synchronization point " + label);
        while (fedAmb.isSynchronizationPointPending(label) == true) {
            tick();
        }
        logger.info("federation synchronized on " + label);
    }

    private void advanceLogicalTime() {
        Double newLogicalTime = fedAmb.getLogicalTime() + configuration.getLogicalTimeStep();
        logger.info("advancing logical time to " + newLogicalTime);
        try {
            fedAmb.setTimeAdvancing();
            rtiAmb.timeAdvanceRequest(new DoubleTime(newLogicalTime));
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
        while (fedAmb.isTimeAdvancing() == true) {
            tick();
        }
        logger.info("advanced logical time to " + fedAmb.getLogicalTime());
    }

    private void resignFederationExecution() {
        logger.info("resigning from the federation execution " + configuration.getFederationName());
        try {
            rtiAmb.resignFederationExecution(ResignAction.NO_ACTION);
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
    }
    
    private void startGLD()
            throws IOException {
        String startTime = client.unixTimeToDate(configuration.getUnixTimeStart());
        String stopTime;
        if (configuration.getUnixTimeStop() < 0) {
            stopTime = "NEVER";
        } else {
            stopTime = client.unixTimeToDate(configuration.getUnixTimeStop());
        }
        
        // how do we handle time zones in GLD?
        // server_quit_on_close=1 can be used for clean exits if the client connection is recycled
        logger.debug("creating the GridLAB-D process builder");
        ProcessBuilder builder = new ProcessBuilder();
        builder.inheritIO(); // maybe replace
        builder.directory(new File(configuration.getWorkingDirectory()));
        builder.command(
                "gridlabd",
                configuration.getModelFilepath(),
                "--server",
                "--server_portnum",
                Integer.toString(configuration.getServerPortNumber()), // no guarantee this port gets used
                "--define",
                "starttime=" + startTime,
                "--define",
                "stoptime=" + stopTime,
                "--define",
                "pauseat=" + startTime
                );
        logger.debug("command   = " + Arrays.toString(builder.command().toArray()));
        logger.debug("directory = " + configuration.getWorkingDirectory());
        
        logger.info("launching the GridLAB-D process");
        gridlabd = builder.start();
        
        // this will handle SIGINT; pkill java will be required for other halt conditions 
        logger.info("registering shutdown hook");
        Thread gldShutdown = new Thread() {
            public void run() {
                try {
                    client.shutdown();
                } catch (GLDException e) {
                    logger.info("destroying the GridLAB-D process");
                    gridlabd.destroy();
                }
            }
        };
        Runtime.getRuntime().addShutdownHook(gldShutdown);
    }
    
    private void connectToGLD()
            throws InterruptedException,
                   GLDException {
        // need a small delay before control can be issued; need to improve how we do this
        int attempt = 1;
        boolean connected = false;
        while (!connected) {
            try {
                logger.info("trying to connect to GridLAB-D (" + attempt + ")");
                client.getUnixTime();
                connected = true;
            } catch (GLDException e) {
                if (attempt == configuration.getMaxConnectionAttempts()) {
                    // does GridLAB-D shutdown successfully when this case occurs?
                    throw e;
                }
                final int delay = configuration.getWaitReconnectMs();
                logger.warn("connection to GridLAB-D server failed; retry in " + delay + " ms");
                Thread.sleep(delay);
                attempt += 1;
            }
        }
    }
    
    private void advanceSimulationTime(long unixTime)
            throws GLDException,
                   InterruptedException {
        client.pauseat(unixTime);
        
        boolean advancing = true;
        while (advancing) {
            if (client.getUnixTime() < unixTime) {
                logger.debug("waiting " + configuration.getWaitAdvanceTimeMs() + " ms for GridLAB-D clock to advance");
                Thread.sleep(configuration.getWaitAdvanceTimeMs());
            } else {
                advancing = false;
            }
        }
    }
    
    public static void main(String args[]) {
        if (args.length != 1) {
            logger.error("command line argument for YAML configuration file not specified");
            System.exit(1);
        }
        
        try {
            GLDFederate gridlabd = new GLDFederate(args[0]);
            gridlabd.execute();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}

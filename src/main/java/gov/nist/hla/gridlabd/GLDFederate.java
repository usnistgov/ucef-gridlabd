package gov.nist.hla.gridlabd;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import gov.nist.hla.FederateAmbassador;
import gov.nist.hla.Interaction;
import gov.nist.hla.ObjectReflection;
import gov.nist.hla.som.SOMQuery;
import gov.nist.hla.som.SOMReader;
import hla.rti.AsynchronousDeliveryAlreadyEnabled;
import hla.rti.AttributeHandleSet;
import hla.rti.ConcurrentAccessAttempted;
import hla.rti.EnableTimeConstrainedPending;
import hla.rti.EnableTimeRegulationPending;
import hla.rti.FederationExecutionDoesNotExist;
import hla.rti.RTIambassador;
import hla.rti.RTIexception;
import hla.rti.ResignAction;
import hla.rti.RestoreInProgress;
import hla.rti.SaveInProgress;
import hla.rti.TimeConstrainedAlreadyEnabled;
import hla.rti.TimeRegulationAlreadyEnabled;
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
    
    private static final String SIMULATION_END = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimEnd";
    private static final String SIMULATION_TIME = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime";
    
    private static final String READY_TO_POPULATE = "readyToPopulate";
    private static final String READY_TO_RUN = "readyToRun";
    private static final String READY_TO_RESIGN = "readyToResign";
    
    private Configuration configuration;
    final private GLDClient client;
    private Process gridlabd;
    
    private RTIambassador rtiAmb;
    private FederateAmbassador fedAmb;
    private SOMQuery simulationObjectModel;
    
    private boolean receivedSimEnd = false;
    private boolean receivedSimTime = false;
    private boolean reachedStopTime = false;
    
    public GLDFederate(String filepath)
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
        
        client = new GLDClient("localhost", configuration.getServerPortNumber());
        try {
            rtiAmb = RtiFactoryFactory.getRtiFactory().createRtiAmbassador();
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
        fedAmb = new FederateAmbassador();
        
        SOMReader reader;
        try {
            reader = new SOMReader();
        } catch (ParserConfigurationException e) {
            throw new IOException(e);
        }
        reader.readXML(configuration.getSomFilepath());
        simulationObjectModel = new SOMQuery(reader);
    }
    
    public void execute()
            throws InterruptedException,
                   GLDClientException,
                   IOException {
        try {
            joinFederationExecution();

            enableAsynchronousDelivery();
            enableTimeConstrained();
            enableTimeRegulation();
            publishAndSubscribe();

            synchronize(READY_TO_POPULATE);
            // check if start time provided
            // if not, wait for RO message SimInit (verify subscribed!)
            // if SimInit, write to configuraton using the set functions
            // how do we handle time zones in GLD?
            // server_quit_on_close=1 can be used for clean exits if the client connection is recycled
            runGLD();
            connectToGLD();
            synchronize(READY_TO_RUN);

            // check if SimEnd subscribed
            double timestep = configuration.getLogicalTimeStep() * configuration.getSimulationTimeScale();
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
    
    private void sendPublications() {
        // check time requirements
        // reformat from GLD to HLA
        // send interaction
    }

    private void handleSubscriptions()
            throws RTIAmbassadorException {
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
                    // need to check if misconfiguration / any of the following parameters are null
                    configuration.setUnixTimeStart(Double.valueOf(parameters.get("timeStart")).intValue());
                    configuration.setUnixTimeStop(Double.valueOf(parameters.get("timeStop")).intValue());
                    configuration.setSimulationTimeScale(Double.valueOf(parameters.get("timeScale")).intValue());
                    receivedSimTime = true;
                } else {
                    // send to GLD
                }
            }

            ObjectReflection receivedObjectReflection;
            while ((receivedObjectReflection = fedAmb.nextObjectReflection()) != null) {
                int objectClassHandle = receivedObjectReflection.getObjectClass();
                String objectClassName = rtiAmb.getObjectClassName(objectClassHandle);
                String objectName = receivedObjectReflection.getObjectName();
                logger.debug("received object class=" + objectClassName + ", name=" + objectName);

                HashMap<String, String> attributes = new HashMap<String, String>();
                for (int i = 0; i < receivedObjectReflection.getAttributeCount(); i++) {
                    int attributeHandle = receivedObjectReflection.getAttributeHandle(i);
                    String attributeName = rtiAmb.getAttributeName(attributeHandle, objectClassHandle);
                    String attributeValue = receivedObjectReflection.getAttributeValue(i);
                    logger.debug(attributeName + "=" + attributeValue);
                    attributes.put(attributeName, attributeValue);
                }
                
                // send to GLD
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

    private void tick()
            throws RTIAmbassadorException {
        try {
            rtiAmb.tick();
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
        handleSubscriptions();
    }

    private void joinFederationExecution()
            throws InterruptedException,
                   RTIAmbassadorException {
        boolean joinSuccessful = false;
        String federateName = configuration.getFederateName();
        String federationName = configuration.getFederationName();

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
    private void enableAsynchronousDelivery()
            throws RTIAmbassadorException {
        try {
            logger.info("enabling asynchronous delivery of receive order messages");
            rtiAmb.enableAsynchronousDelivery();
        } catch (AsynchronousDeliveryAlreadyEnabled e) {
            logger.info("asynchronous delivery already enabled");
        } catch (RTIexception e) {
            throw new RTIAmbassadorException(e);
        }
    }

    private void enableTimeConstrained()
            throws RTIAmbassadorException {
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

    private void enableTimeRegulation()
            throws RTIAmbassadorException {
        try {
            logger.info("enabling time regulation");
            double lookahead = configuration.getLookahead();
            rtiAmb.enableTimeRegulation(new DoubleTime(fedAmb.getLogicalTime()), new DoubleTimeInterval(lookahead));
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

    private void publishAndSubscribe()
            throws RTIAmbassadorException {
        try {
            for (String interactionName : simulationObjectModel.getPublishedInteractions()) {
                logger.info("creating HLA publication for the interaction " + interactionName);
                int interactionHandle = rtiAmb.getInteractionClassHandle(interactionName);
                rtiAmb.publishInteractionClass(interactionHandle);
            }
            for (String interactionName : simulationObjectModel.getSubscribedInteractions()) {
                logger.info("creating HLA subscription for the interaction " + interactionName);
                int interactionHandle = rtiAmb.getInteractionClassHandle(interactionName);
                rtiAmb.subscribeInteractionClass(interactionHandle);
            }
            for (String objectClass : simulationObjectModel.getPublishedObjects()) {
                int objectHandle = rtiAmb.getObjectClassHandle(objectClass);
                AttributeHandleSet attributes = RtiFactoryFactory.getRtiFactory().createAttributeHandleSet();

                for (String attributeName : simulationObjectModel.getPublishedAttributes(objectClass)) {
                    logger.info("creating HLA publication for object=" + objectClass + ", attribute=" + attributeName);
                    int attributeHandle = rtiAmb.getAttributeHandle(attributeName, objectHandle);
                    attributes.add(attributeHandle);
                }
                rtiAmb.publishObjectClass(objectHandle, attributes);
            }
            for (String objectClass : simulationObjectModel.getSubscribedObjects()) {
                int objectHandle = rtiAmb.getObjectClassHandle(objectClass);
                AttributeHandleSet attributes = RtiFactoryFactory.getRtiFactory().createAttributeHandleSet();

                for (String attributeName : simulationObjectModel.getSubscribedAttributes(objectClass)) {
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

    private void synchronize(String label)
            throws RTIAmbassadorException {  
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

    private void advanceLogicalTime()
            throws RTIAmbassadorException {
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
    
    
    private void runGLD()
            throws IOException {
        long unixStopTime = configuration.getUnixTimeStop();
        String stopTime = unixStopTime < 0 ? "NEVER" : client.unixTimeToDate(unixStopTime); 
        String startTime = client.unixTimeToDate(configuration.getUnixTimeStart());
        
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
        logger.info("regisstering shutdown hook");
        Thread gldShutdown = new Thread() {
            public void run() {
                try {
                    client.shutdown();
                    logger.info("sent shutdown command to GridLAB-D");
                } catch (GLDClientException e) {
                    logger.info("destroying the GridLAB-D process");
                    gridlabd.destroy();
                }
            }
        };
        Runtime.getRuntime().addShutdownHook(gldShutdown);
    }
    
    private void connectToGLD()
            throws InterruptedException,
                   GLDClientException {
        // need a small delay before control can be issued; need to improve how we do this
        int attempt = 1;
        boolean connected = false;
        while (!connected) {
            try {
                logger.info("trying to connect to GridLAB-D (" + attempt + ")");
                client.getClockValue();
                connected = true;
            } catch (GLDClientException e) {
                // should check process exit value to see if still running
                if (attempt == configuration.getMaxConnectionAttempts()) {
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
            throws GLDClientException,
                   InterruptedException {
        client.advanceTime(unixTime);
        
        boolean advancing = true;
        while (advancing) {
            long currentUnixTime = client.getClockValue();
            if (currentUnixTime < unixTime) {
                logger.debug("waiting " + configuration.getWaitAdvanceTimeMs() + " ms for GridLAB-D clock to advance");
                Thread.sleep(configuration.getWaitAdvanceTimeMs());
            } else {
                advancing = false;
            }
        }
    }
}

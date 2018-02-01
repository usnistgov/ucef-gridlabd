package gov.nist.hla.gridlabd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import gov.nist.hla.gateway.GatewayCallback;
import gov.nist.hla.gateway.GatewayFederate;
import gov.nist.hla.gridlabd.exception.GridLabDException;
import gov.nist.hla.gridlabd.exception.SchemaValidationException;
import gov.nist.pages.ucef.ObjectClassConfigType;
import gov.nist.pages.ucef.PublishedObjectsType;
import gov.nist.pages.ucef.ucefPackage;
import gov.nist.sds4emf.Deserialize;
import hla.rti.AttributeNotOwned;
import hla.rti.FederateNotExecutionMember;
import hla.rti.NameNotFound;
import hla.rti.ObjectClassNotPublished;
import hla.rti.ObjectNotKnown;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.ieee.standards.ieee1516._2010.InteractionClassType;
import org.ieee.standards.ieee1516._2010.ObjectClassType;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GridLabDFederate implements GatewayCallback {    
    private static final Logger log = LogManager.getLogger();
    
    private static final String SIMULATION_END = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimEnd";
    
    private static final String SIMULATION_TIME = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime";
    
    final private GridLabDConfig configuration;
    
    final private SimpleDateFormat dateFormat;
    
    final private GatewayFederate gateway;
    
    final private GridLabDClient client;
    
    private Process gridlabd = null;
    
    private boolean isInitialized = false;
    
    private boolean receivedSimTime = false;
    
    private boolean receivedSimEnd = false;
    
    public static GridLabDConfig readConfiguration(String filePath)
            throws IOException {
        log.info("reading JSON configuration file " + filePath);
        File configFile = Paths.get(filePath).toFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(configFile, GridLabDConfig.class);
    }
    
    public static void main(String args[]) {
        if (args.length != 1) {
            log.error("missing argument for JSON configuration file");
            return;
        }
         
        try {
            GridLabDConfig config = GridLabDFederate.readConfiguration(args[0]);
            GridLabDFederate gridlabdFederate = new GridLabDFederate(config);
            gridlabdFederate.run();
        } catch (Exception e) {
            log.error(e);
            return;
        }
        
        log.info("Done.");
    }
    
    public GridLabDFederate(GridLabDConfig configuration)
            throws SchemaValidationException {
        this.configuration = configuration;
        
        registerUcefSchema();
        validateAgainstSchema(configuration.getFomFilepath());
        
        // future GridLAB-D releases will continue to support GMT
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        this.gateway = new GatewayFederate(configuration, this);
        this.client = new GridLabDClient("localhost", configuration.getServerPortNumber());
    }
    
    public void run() {
        log.trace("run");
        gateway.run();
    }
    
    @Override
    public void initializeSelf() {
        log.trace("initializeSelf");
        registerObjectInstances();
    }
    
    @Override
    public void initializeWithPeers() {
        log.trace("initializeWithPeers");
        
        if (configuration.getUseSimTime()) {
            InteractionClassType simTime = gateway.getObjectModel().getInteraction(SIMULATION_TIME);
            if (!gateway.getObjectModel().getSubscribedInteractions().contains(simTime)) {
                throw new GridLabDException("no subscription for " + SIMULATION_TIME);
            }
            
            while (!receivedSimTime) {
                try {
                    if (receivedSimEnd) {
                        log.warn("received {} prior to initialization", SIMULATION_END);
                        throw new GridLabDException("unexpected " + SIMULATION_END);
                    }
                    log.info("waiting {} ms to receive {}", configuration.getWaitTimeMs(), SIMULATION_TIME);
                    Thread.sleep(configuration.getWaitTimeMs());
                    gateway.tick();
                } catch (FederateNotExecutionMember | InterruptedException e) {
                    throw new GridLabDException(e);
                }
            }
        } else {
            log.info("starting GridLAB-D simulation without " + SIMULATION_TIME);
        }
        
        try {
            startGld();
            connectToGld();
        } catch (IOException | InterruptedException e) {
            throw new GridLabDException(e);
        }
        isInitialized = true;
        log.info("Initialized.");
    }
    
    @Override
    public void receiveInteraction(Double timeStep, String className, Map<String, String> parameters) {
        log.trace("receiveInteraction {} {} {}", timeStep, className, parameters.toString());
        
        if (className.equals(SIMULATION_END)) {
            receivedSimEnd = true;
        } else if (className.equals(SIMULATION_TIME)) {
            configuration.setUnixTimeStart(Long.valueOf(parameters.get("unixTimeStart")));
            configuration.setUnixTimeStop(Long.valueOf(parameters.get("unixTimeStop")));
            configuration.setSimulationTimeScale(Double.valueOf(parameters.get("timeScale")));
            configuration.setSimulationTimeZone(parameters.get("timeZone"));
            receivedSimTime = true;
        } else if (isInitialized) {
            //handleInteraction(className, parameters);
        } else {
            log.warn("dropped interaction " + className);
        }
    }

    @Override
    public void receiveObject(Double timeStep, String className, String instanceName, Map<String, String> attributes) {
        // TODO Auto-generated method stub
    }

    @Override
    public void doTimeStep(Double timeStep) {
        // TODO Auto-generated method stub
    }

    @Override
    public void terminate() {
        log.trace("terminate");
        // clean shutdown for early exit
    }
    
    private void registerUcefSchema() {
        log.info("loading schema {}", ucefPackage.eNS_URI);
        Deserialize.registerPackage(ucefPackage.eNS_URI, ucefPackage.eINSTANCE);
    }
    
    private void validateAgainstSchema(String fomFilePath)
            throws SchemaValidationException {
        log.info("validating FOM {}", fomFilePath);
        
        Source fomFile = new StreamSource(new File(fomFilePath));
        InputStream hlaSchema = this.getClass().getClassLoader().getResourceAsStream("IEEE1516-DIF-2010.xsd");
        InputStream ucefSchema = this.getClass().getClassLoader().getResourceAsStream("ucef.xsd");
        
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new Source[] {
                        new StreamSource(hlaSchema),
                        new StreamSource(ucefSchema)});
            Validator validator = schema.newValidator();
            validator.validate(fomFile);
        } catch (IOException | SAXException e) {
            throw new SchemaValidationException(e);
        }
    }
    
    private String toTimeStamp(long unixTime) {
        log.trace("toTimeStamp {}", unixTime);
        return dateFormat.format(new Date(unixTime*1000));
    }
    
    private long toUnixTime(String timeStamp)
            throws ParseException {
        log.trace("toUnixTime {}", timeStamp);
        return dateFormat.parse(timeStamp).getTime()/1000;
    }
    
    private boolean isGlobalVariable(ObjectClassType object) {
        log.trace("isGlobalVariable {}", object.getName().getValue());
        return gateway.getObjectModel().getAttribute(object, "name") == null;
    }
    
    private boolean isGlobalVariable(InteractionClassType interaction) {
        log.trace("isGlobalVariable {}", interaction.getName().getValue());
        return gateway.getObjectModel().getParameter(interaction, "name") == null;
    }
    
    private void registerObjectInstances() {
        log.trace("registerObjectInstances");
        
        for (ObjectClassType object : gateway.getObjectModel().getPublishedObjects()) {
            if (isGlobalVariable(object)) {
                registerGlobalVariable(object);
            } else {
                registerGldObjects(object);
            }
        }
    }
    
    private void registerGlobalVariable(ObjectClassType object) {
        log.trace("registerGlobalVariable {}", object.getName().getValue());
        
        final String classPath = gateway.getObjectModel().getClassPath(object);
        
        if (!getGldObjectNames(object).isEmpty()) {
            log.warn("GridLAB-D object names were associated with global variables in {}", classPath);
        }
        
        try {
            String instanceName = gateway.registerObjectInstance(classPath);
            // store this somehow for updates
            log.info("registered object {} with class {} to publish global variables", instanceName, classPath);
        } catch (FederateNotExecutionMember | NameNotFound | ObjectClassNotPublished e) {
            throw new GridLabDException(e);
        }
    }
    
    private void registerGldObjects(ObjectClassType object) {
        log.trace("registerGldObjects {}", object.getName().getValue());
        
        String classPath = gateway.getObjectModel().getClassPath(object);
        Set<String> publishedObjectNames = getGldObjectNames(object);
        
        if (publishedObjectNames.isEmpty()) {
            log.warn("no GridLAB-D object names were defined for published object {}", classPath);
        }
        for (String objectName : publishedObjectNames) {
            try {
                String instanceName = gateway.registerObjectInstance(classPath);
                Map<String, String> initialValues = new HashMap<String, String>();
                initialValues.put("name", objectName);
                gateway.updateObject(instanceName, initialValues);
                // store this somehow for updates
                log.info("registered object {} with class {} to publish {}", instanceName, classPath, objectName);
            } catch (FederateNotExecutionMember | NameNotFound | ObjectClassNotPublished | ObjectNotKnown
                    | AttributeNotOwned e) {
                throw new GridLabDException(e);
            }
        }
    }
    
    private Set<String> getGldObjectNames(ObjectClassType object) {
        log.trace("getGldObjectNames {}", object.getName().getValue());
        
        Set<String> publishedObjectNames = new HashSet<String>();
        
        for (FeatureMap.Entry feature : object.getAny()) {
            log.trace("on {}", feature.getValue());
            if (feature.getValue() instanceof ObjectClassConfigType) {
                ObjectClassConfigType objectConfig = (ObjectClassConfigType) feature.getValue();
                
                PublishedObjectsType gridlabdObjectNames = objectConfig.getPublishedObjects();
                if (gridlabdObjectNames == null) {
                    log.debug("no GridLAB-D objects defined for {}", object.getName().getValue());
                    continue;
                }
                publishedObjectNames.addAll(gridlabdObjectNames.getObjectName());
            }
        }
        return publishedObjectNames;
    }
    
    private void startGld()
            throws IOException {
        log.trace("startGld");
        
        String timeStart = toTimeStamp(configuration.getUnixTimeStart());
        String timeStop  = configuration.getUnixTimeStop() < 0 ? "NEVER" : toTimeStamp(configuration.getUnixTimeStop());
        String timeZone  = configuration.getSimulationTimeZone();
        configuration.getSimulationTimeScale(); // to trigger early exception if missing
        
        log.debug("creating process builder for GridLAB-D");
        ProcessBuilder builder = new ProcessBuilder();
        builder.inheritIO(); // maybe replace
        
        builder.directory(new File(configuration.getWorkingDirectory()));
        log.debug("directory: {}", configuration.getWorkingDirectory());
        
        builder.command(
                "gridlabd",
                configuration.getModelFilePath(),
                "--server",
                "--server_portnum",
                Integer.toString(configuration.getServerPortNumber()), // no guarantee this port gets used
                "--define",
                "starttime=" + timeStart,
                "--define",
                "stoptime=" + timeStop,
                "--define",
                "pauseat=" + timeStart
                );
        log.debug("command: {}", Arrays.toString(builder.command().toArray()));
        
        Map<String, String> environment = builder.environment();
        environment.put("TZ", timeZone);
        log.debug("timezone: {}", timeZone);
        
        log.info("starting GridLAB-D process");
        gridlabd = builder.start();
        
        // this will handle SIGINT; pkill java will be required for other halt conditions 
        log.info("registering shutdown hook");
        Thread gldShutdown = new Thread() {
            public void run() {
                try {
                    client.shutdown();
                } catch (IOException e) {
                    log.info("destroying the GridLAB-D process");
                    gridlabd.destroy();
                }
            }
        };
        Runtime.getRuntime().addShutdownHook(gldShutdown);
    }
    
    private void connectToGld()
            throws InterruptedException {
        log.trace("connectToGld");
        
        boolean connected = false;
        while (!connected) {
            try {
                log.info("waiting {} ms on connection to {}", configuration.getWaitTimeMs(), client);
                Thread.sleep(configuration.getWaitTimeMs());
                connected = client.isPaused();
                if (!connected) {
                    log.info("GridLAB-D model not yet initialized");
                }
            } catch (IOException e) {
                log.warn("failed to connect to GridLAB-D server");
            }
        }
    }
    
    /*
    private HashMap<String, Integer> objectInstances = new HashMap<String, Integer>();
    
    private boolean reachedStopTime = false;
    
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
                        log.debug("skipping parameter " + parameterName + " from " + INTERACTION_ROOT);
                        continue;
                    }
                    String object = truncateClassName(interactionName);
                    String property = parameterName;
                    String value;
                    
                    String dataType = objectModel.getParameterType(interactionName, parameterName);
                    if (dataType.contains("integer")) {
                        // required to strip the units from the returned value
                        value = Long.toString(client.getObjectPropertyAsLong(object, property));
                    } else if (dataType.contains("float")) {
                        // required to strip the units from the returned value
                        value = Double.toString(client.getObjectPropertyAsDouble(object, property));
                    } else {
                        value = client.getObjectProperty(object, property);
                    }
                    
                    int parameterHandle = rtiAmb.getParameterHandle(parameterName, interactionHandle);
                    byte[] parameterValue = value.getBytes(); // cpswt does not use the null character
                    suppliedParameters.add(parameterHandle, parameterValue);
                }
                
                rtiAmb.sendInteraction(interactionHandle, suppliedParameters, null, timestamp);
            }
            
            for (String objectClassName : objectModel.getPublishedObjects()) {
                int classHandle = rtiAmb.getObjectClassHandle(objectClassName);
                int objectHandle = objectInstances.get(objectClassName);
                
                SuppliedAttributes suppliedAttributes = RtiFactoryFactory.getRtiFactory().createSuppliedAttributes();
                for (String attributeName : objectModel.getPublishedAttributes(objectClassName)) {
                    String object = truncateClassName(objectClassName);
                    String property = attributeName;
                    String value;
                    
                    String dataType = objectModel.getAttributeType(objectClassName, attributeName);
                    if (dataType.contains("integer")) {
                        // required to strip the units from the returned value
                        value = Long.toString(client.getObjectPropertyAsLong(object, property));
                    } else if (dataType.contains("float")) {
                        // required to strip the units from the returned value
                        value = Double.toString(client.getObjectPropertyAsDouble(object, property));
                    } else {
                        value = client.getObjectProperty(object, property);
                    }
                    log.debug(object + "." + property + "=" + value);
                    log.debug("value size: " + value.length());
                    
                    int attributeHandle = rtiAmb.getAttributeHandle(attributeName, classHandle);
                    byte[] attributeValue = value.getBytes(); // cpswt does not use the null character
                    suppliedAttributes.add(attributeHandle, attributeValue);
                }
                
                rtiAmb.updateAttributeValues(objectHandle, suppliedAttributes, null, timestamp);
            }
        } catch (RTIexception e) {
            // might want to separate this out into recoverable cases
            throw new RTIAmbassadorException(e);
        }
    }

    private void handleInteraction(String interactionName, HashMap<String, String> parameters) {
        Set<String> rootParameters = objectModel.getParameterSet(INTERACTION_ROOT);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (rootParameters.contains(entry.getKey())) {
                log.debug("skipping parameter " + entry.getKey() + " from " + INTERACTION_ROOT);
                continue;
            }
            String object = truncateClassName(interactionName);
            String property = entry.getKey();
            String value = entry.getValue();
            try {
                // this cannot be more efficient due to the limitations of GridLAB-D
                client.setObjectProperty(object, property, value);
                log.debug("set " + object + ":" + property + "=" + value);
            } catch (GLDException e) {
                log.error("could not set " + object + ":" + property + "=" + value);
            }
        }
    }
    
    private void handleObjectReflection(String objectName, String objectClassName, HashMap<String, String> attributes) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String object = truncateClassName(objectClassName);
            String property = entry.getKey();
            String value = entry.getValue();
            try {
                // this cannot be more efficient due to the limitations of GridLAB-D
                client.setObjectProperty(object, property, value);
                log.debug("set " + object + ":" + property + "=" + value);
            } catch (GLDException e) {
                log.error("could not set " + object + ":" + property + "=" + value);
            }
        }
    }
    
    private String truncateClassName(String className) {
        String result = className;
        
        int lastPeriod = className.lastIndexOf(".");
        if (lastPeriod > 0) {
          result = className.substring(lastPeriod+1);
        }
        return result;
    }
    
    private void advanceSimulationTime(long unixTime)
            throws GLDException,
                   InterruptedException {
        client.pauseat(unixTime);
        
        boolean advancing = true;
        while (advancing) {
            if (client.getUnixTime() < unixTime) {
                log.debug("waiting " + configuration.getWaitAdvanceTimeMs() + " ms for GridLAB-D clock to advance");
                Thread.sleep(configuration.getWaitAdvanceTimeMs());
            } else {
                advancing = false;
            }
        }
    }

    @Override
    public void receiveObject(Double timeStep, String className, String instanceName, Map<String, String> attributes) {
        int objectClassHandle = receivedObjectReflection.getObjectClass();
        String objectClassName = rtiAmb.getObjectClassName(objectClassHandle);
        String objectName = receivedObjectReflection.getObjectName();
        log.debug("received object class=" + objectClassName + " with name=" + objectName);

        HashMap<String, String> attributes = new HashMap<String, String>();
        for (int i = 0; i < receivedObjectReflection.getAttributeCount(); i++) {
            int attributeHandle = receivedObjectReflection.getAttributeHandle(i);
            String attributeName = rtiAmb.getAttributeName(attributeHandle, objectClassHandle);
            String attributeValue = receivedObjectReflection.getAttributeValue(i);
            log.debug(attributeName + "=" + attributeValue);
            attributes.put(attributeName, attributeValue);
        }
        
        if (gridlabdStarted) {
            handleObjectReflection(objectName, objectClassName, attributes);
        } else {
            log.warn("dropped object reflection " + objectName);
        }
    }

    @Override
    public void doTimeStep(Double timeStep) {
        // if first time step
        if (configuration.getUnixTimeStop() < 0) {
            log.info("no simulation stop time specified");
        }
        if (!objectModel.getSubscribedInteractions().contains(SIMULATION_END)) {
            log.info("no subscription for " + SIMULATION_END);
            if (configuration.getUnixTimeStop() < 0) {
                log.warn("no exit condition specified; will run forever until Ctrl+C");
            }
        }
        double simulationTime = configuration.getUnixTimeStart();
        final double timestep = configuration.getLogicalTimeStep() * configuration.getSimulationTimeScale();
        
            try {
                int code = gridlabd.exitValue();
                log.info("GridLAB-D done with exit value = " + code);
                reachedStopTime = true; // or due to exception; might check code value
            } catch (IllegalThreadStateException e) {
                sendPublications();
                handleSubscriptions();
                simulationTime += timestep;
                if (simulationTime > configuration.getUnixTimeStop()) {
                    simulationTime = configuration.getUnixTimeStop();
                }
                advanceSimulationTime((int)simulationTime); // truncate
            }
    }
    */
}

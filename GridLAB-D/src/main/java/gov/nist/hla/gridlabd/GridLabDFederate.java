package gov.nist.hla.gridlabd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gov.nist.hla.gateway.GatewayCallback;
import gov.nist.hla.gateway.GatewayFederate;
import gov.nist.hla.gateway.exception.RTIAmbassadorException;
import gov.nist.hla.gridlabd.exception.GridLabDException;
import gov.nist.hla.gridlabd.exception.SchemaValidationException;
import gov.nist.pages.ucef.LinearConversionType;
import hla.rti.AttributeNotOwned;
import hla.rti.FederateNotExecutionMember;
import hla.rti.InteractionClassNotPublished;
import hla.rti.InvalidFederationTime;
import hla.rti.NameNotFound;
import hla.rti.ObjectClassNotPublished;
import hla.rti.ObjectNotKnown;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ieee.standards.ieee1516._2010.AttributeType;
import org.ieee.standards.ieee1516._2010.InteractionClassType;
import org.ieee.standards.ieee1516._2010.ObjectClassType;
import org.ieee.standards.ieee1516._2010.ParameterType;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class implements a federate that creates and manages a GridLAB-D simulation as a child process. The GridLAB-D
 * simulation will run in server mode using the options specified in a JSON configuration file that mirrors the class
 * structure of {@link GridLabDConfig}. When the federation execution starts, {@link ExtendedObjectModel#SIM_TIME} can
 * be sent as a receive order interaction to further customize the behavior of the GridLAB-D simulation.
 * <p>
 * The GridLAB-D federate will terminate when it either receives {@link ExtendedObjectModel#SIMULATION_END}, or it
 * reaches its configured stop time. If it terminates due to the stop time, the federate will leave the federation
 * early without any attempt to synchronize with the other federates.
 * <p>
 * The GridLAB-D federate will ignore all interaction classes and object classes that do not define a special field
 * called `name`. This field should be set to the name of an object in the GridLAB-D simulation, and defines a 1-to-1
 * relation between an HLA entity and a GridLAB-D simulation object.
 * <p>
 * There is no guarantee that the port number specified in the configuration is used by GridLAB-D. If the specified
 * port number is unavailable, then GridLAB-D will select and use the next available port number. This fail case is
 * unrecoverable, and the GridLAB-D federate will continue to output warning messages as it tries to connect to the
 * wrong server address.
 * 
 * @author Thomas Roth
 */
public class GridLabDFederate implements GatewayCallback {    
    private static final Logger log = LogManager.getLogger();
    
    final private GridLabDConfig configuration;
    
    final private ExtendedObjectModel objectModel;
    
    final private GatewayFederate gateway;
    
    final private GridLabDClient client;
    
    private Process gridlabd = null;
    
    private TimeToPublish publicationManager = null;
    
    private Set<PropertyUpdate> propertyUpdates = new HashSet<PropertyUpdate>();
    
    private Set<ObjectInstanceInfo> registeredObjects = new HashSet<ObjectInstanceInfo>();
    
    private boolean receivedSimTime = false;
    
    private boolean receivedSimEnd = false;
    
    private boolean isClockPublished = false;
    
    private boolean isInitialized = false;
    
    private boolean isRunForever = false;
    
    public static GridLabDConfig readConfiguration(String filePath)
            throws IOException {
        log.info("reading JSON configuration file {}", filePath);
        File configFile = Paths.get(filePath).toFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(configFile, GridLabDConfig.class);
    }
    
    public static void main(String args[]) {
        if (args.length != 1) {
            log.error("expect single argument for JSON configuration file");
            return;
        }
         
        try {
            final String jsonFilePath = args[0];
            GridLabDConfig config = GridLabDFederate.readConfiguration(jsonFilePath);
            GridLabDFederate gridlabdFederate = new GridLabDFederate(config);
            gridlabdFederate.run();
            log.info("Done.");
        } catch (Exception e) {
            log.error(e);
        }
    }
    
    public GridLabDFederate(GridLabDConfig configuration)
            throws SchemaValidationException {
        this.configuration = configuration;
        this.objectModel = new ExtendedObjectModel(configuration.getFomFilepath());
        this.gateway = new GatewayFederate(configuration, this, objectModel);
        this.client = new GridLabDClient("localhost", configuration.getServerPortNumber());
        this.publicationManager = new TimeToPublish(objectModel);
        this.isClockPublished = objectModel.isPublished(ExtendedObjectModel.GLD_CLOCK);
    }
    
    public void run() {
        gateway.run();
    }
    
    ////////////////////////////////////////////////////////////
    // Gateway Callback Methods
    ////////////////////////////////////////////////////////////
    
    @Override
    public void initializeSelf() {
        log.trace("initializeSelf");
    }
    
    @Override
    public void initializeWithPeers() {
        log.trace("initializeWithPeers");
        
        if (configuration.getUseSimTime()) {
            waitForSimTime(); // side effect - modifies the content of configuration
        }
        validateExitCondition(); // must happen after the side effect of SimTime
        
        try {
            startGld();
            addShutdownHook();
            connectToGld();
        } catch (IOException | InterruptedException e) {
            throw new GridLabDException(e);
        }
        
        registerObjectInstances();
        sendPublications();
        
        isInitialized = true;
        log.info("Initialized.");
    }
    
    @Override
    public void receiveInteraction(Double timeStep, String className, Map<String, String> parameters) {
        log.trace("receiveInteraction {} as {} @ t={}", className, parameters.toString(), timeStep);
        
        switch (className) {
            case ExtendedObjectModel.SIMULATION_END:
                handleSimEnd();
                break;
            case ExtendedObjectModel.SIM_TIME:
                handleSimTime(parameters);
                break;
            default:
                handleInteraction(className, parameters);
        }
    }
    
    @Override
    public void receiveObject(Double timeStep, String className, String instanceName, Map<String, String> attributes) {
        log.trace("receiveObject {}:{} as {} @ t={}", className, instanceName, attributes.toString(), timeStep);
        handleObjectReflection(className, instanceName, attributes);
    }
    
    @Override
    public void doTimeStep(Double timeStep) {
        log.trace("doTimeStep {}", timeStep);
        
        if (hasGldExited()) {
            gateway.requestExit();
            return; // cannot access GridLAB-D after it exits so nothing left to do
        }
        
        publicationManager.setLogicalTime(timeStep); // has to happen before sendPublications
        sendPublications();
        updateGldProperties();
        
        try {
            advanceSimulationTime(getNextPauseTime(timeStep));
        } catch (InterruptedException | IOException e) {
            throw new GridLabDException(e);
        }
    }

    @Override
    public void prepareToResign() {
        log.trace("prepareToResign");
    }

    @Override
    public void terminate() {
        log.trace("terminate");
        
        if (!propertyUpdates.isEmpty()) {
            log.warn("dropped {} updates queued for GridLAB-D", propertyUpdates.size());
        }
    }
    
    ////////////////////////////////////////////////////////////
    // Initialization Methods
    ////////////////////////////////////////////////////////////
    
    private void waitForSimTime() {
        if (!objectModel.isSubscribed(ExtendedObjectModel.SIM_TIME)) {
            throw new GridLabDException("no subscription for " + ExtendedObjectModel.SIM_TIME);
        }
        
        while (!receivedSimTime) { // loop until the RTI calls handleSimTime in gateway.tick()
            try {
                log.info("waiting {} ms to receive {}", configuration.getWaitTimeMs(), ExtendedObjectModel.SIM_TIME);
                Thread.sleep(configuration.getWaitTimeMs());
                gateway.tick();
                
                if (receivedSimEnd) {
                    log.error("received {} prior to initialization", ExtendedObjectModel.SIMULATION_END);
                    throw new GridLabDException("unexpected " + ExtendedObjectModel.SIMULATION_END);
                }
            } catch (FederateNotExecutionMember e) {
                throw new RTIAmbassadorException(e);
            } catch (InterruptedException e) {
                throw new GridLabDException(e);
            }
        }
    }
    
    private void validateExitCondition() {
        // the GridLAB-D federate has two independent exit conditions:
        //  1. the GridLAB-D federate receives the SimEnd interaction
        //  2. the GridLAB-D simulation reaches the configured stop time
        if (!objectModel.isSubscribed(ExtendedObjectModel.SIMULATION_END)) {
            if (configuration.getUnixTimeStop() < 0) {
                throw new GridLabDException("no exit condition");
            }
            log.info("configured to run without " + ExtendedObjectModel.SIMULATION_END);
        }
        
        if (configuration.getUnixTimeStop() < 0) {
            log.info("configured to run using stoptime=NEVER");
            this.isRunForever = true;
        }
    }
    
    private void startGld()
            throws IOException {
        gridlabd = createProcessBuilder().start();
        log.info("started the GridLAB-D process");
    }
    
    private void addShutdownHook() {
        Thread gldShutdown = new Thread() {
            public void run() {
                try {
                    log.info("stopping the GridLAB-D server");
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
        boolean connected = false;
        while (!connected) {
            try {
                log.info("waiting {} ms on connection to {}", configuration.getWaitTimeMs(), client);
                Thread.sleep(configuration.getWaitTimeMs());
                connected = client.isPaused(); // true when GridLAB-D model initialized
            } catch (IOException e) {
                log.warn("failed to connect to GridLAB-D server");
            }
        }
    }
    
    private void registerObjectInstances() {
        for (ObjectClassType publishedObjectClass : objectModel.getPublishedObjects()) {
            final String classPath = objectModel.getClassPath(publishedObjectClass);
            
            if (objectModel.isCoreObject(publishedObjectClass) || !objectModel.isGldObject(publishedObjectClass)) {
                log.debug("skipped object instance registration for {}", classPath);
                continue; // ignore objects related to gateway infrastructure
            }
            
            for (String gldObjectName : objectModel.getPublishedNames(publishedObjectClass)) {
                try {
                    // assign a random instance name to avoid collision
                    final String instanceName = gateway.registerObjectInstance(classPath);
                    registeredObjects.add(new ObjectInstanceInfo(classPath, instanceName, gldObjectName));
                    log.debug("registered {}:{} to publish {}", classPath, instanceName, gldObjectName);
                } catch (FederateNotExecutionMember | NameNotFound | ObjectClassNotPublished e) {
                    throw new RTIAmbassadorException(e);
                }
            }
        }
    }
    
    ////////////////////////////////////////////////////////////
    // GridLAB-D Process Methods
    ////////////////////////////////////////////////////////////
    
    private ProcessBuilder createProcessBuilder() {
        log.debug("creating process builder for GridLAB-D");
        ProcessBuilder builder = new ProcessBuilder();
        
        setIO(builder);
        setCommand(builder);
        setWorkingDirectory(builder);
        setTimeZone(builder);
        
        return builder;
    }
    
    private void setIO(ProcessBuilder builder) {
        builder.inheritIO();
    }
    
    private void setCommand(ProcessBuilder builder) {
        final String startTime = ConversionMethods.toTimeStamp(configuration.getUnixTimeStart());
        final String stopTime;
        if (isRunForever) {
            stopTime = "NEVER";
        } else {
            stopTime = ConversionMethods.toTimeStamp(configuration.getUnixTimeStop());
        }
        
        builder.command(
                "gridlabd",
                configuration.getModelFilePath(),
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
        log.debug("command: {}", Arrays.toString(builder.command().toArray()));
    }
    
    private void setWorkingDirectory(ProcessBuilder builder) {
        if (configuration.getWorkingDirectory() != null) {
            final File workingDirectory = new File(configuration.getWorkingDirectory());
            builder.directory(workingDirectory.getAbsoluteFile());
            log.debug("directory: {}", workingDirectory.getAbsolutePath());
        }
    }
    
    private void setTimeZone(ProcessBuilder builder) {
        final String timeZone = configuration.getSimulationTimeZone();
        Map<String, String> environment = builder.environment();
        environment.put("TZ", timeZone);
        log.debug("timezone: {}", timeZone);
    }
    
    ////////////////////////////////////////////////////////////
    // HLA Subscription Handlers
    ////////////////////////////////////////////////////////////
    
    private void handleSimEnd() {
        log.debug("handleSimEnd");
        receivedSimEnd = true;
    }
    
    private void handleSimTime(Map<String, String> parameters) {
        log.debug("handleSimTime {}", parameters.toString());
        
        if (isInitialized) {
            log.warn("received {} after initialization", ExtendedObjectModel.SIM_TIME);
            return;
        }
        
        if (receivedSimTime) {
            // replace the old values with the latest received ones
            log.warn("received duplicate {}", ExtendedObjectModel.SIM_TIME);
        }
        
        configuration.setUnixTimeStart(Long.valueOf(parameters.get("unixTimeStart")));
        configuration.setUnixTimeStop(Long.valueOf(parameters.get("unixTimeStop")));
        configuration.setSimulationTimeScale(Double.valueOf(parameters.get("timeScale")));
        configuration.setSimulationTimeZone(parameters.get("timeZone"));
        receivedSimTime = true;
    }
    
    private void handleInteraction(String classPath, Map<String, String> parameters) {
        InteractionClassType interaction = objectModel.getInteraction(classPath);
        if (objectModel.isCoreInteraction(interaction) || !objectModel.isGldObject(interaction)) {
            log.debug("skipped interaction {}", classPath);
            return;
        }
        
        final String gldObjectName = parameters.get("name");
        if (gldObjectName == null || gldObjectName.isEmpty()) {
            log.error("subscribed interaction omits GridLAB-D object name: {}", classPath);
            return;
        }
        
        queue(interaction, gldObjectName, parameters);
    }

    private void handleObjectReflection(String classPath, String instanceName, Map<String, String> attributes) {
        ObjectClassType object = objectModel.getObject(classPath);
        if (objectModel.isCoreObject(object) || !objectModel.isGldObject(object)) {
            log.debug("skipped object {}:{}", classPath, instanceName);
            return;
        }
        
        Map<String, String> objectState = gateway.getObjectState(instanceName);
        if (objectState == null) {
            objectState = new HashMap<String, String>();
        }
        final String receivedName = attributes.get("name");
        final String storedName = objectState.get("name");
        
        if (receivedName != null && !receivedName.isEmpty()) {
            // if we receive a new value for the name attribute, then the GridLAB-D object with name="receivedName" has
            // no prior history with the object instance being handled in this method. therefore, the entire instance
            // state (stored in objectState) must be reflected into GridLAB-D using the received name.
            queue(object, receivedName, objectState);
        } else if (storedName != null && !storedName.isEmpty()) {
            // if we do not receive the name attribute, but have a stored name for this instance from a prior object
            // reflection, then the GridLAB-D object with name="storedName" must have a history of being updated by
            // this method during prior iterations. therefore, we just append the latest received attributes.
            queue(object, storedName, attributes);
        } else {
            // if we have never received a name attribute, then there is no GridLAB-D object that can be updated. when
            // a value for the name attribute is received in a future iteration, the full object state will be pulled
            // from the gateway.
            log.warn("deferred update of {}:{} until name attribute received", classPath, instanceName);
        }
    }
    
    private void queue(InteractionClassType interaction, String gldObjectName, Map<String, String> parameters) {
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            final String parameterName = parameter.getKey();
            final String parameterValue = parameter.getValue();
            
            ParameterType parameterType = objectModel.getParameter(interaction, parameterName);
            if (!objectModel.isGldProperty(parameterType)) {
                log.debug("skipped parameter {} for {}", parameterName, gldObjectName);
                continue;
            }
            log.trace("on parameter {}={}", parameterName, parameterValue);
            
            String propertyName  = objectModel.getPropertyName(parameterType);
            String propertyUnit  = objectModel.getUnitName(parameterType);
            String propertyValue = parameterValue;
            
            LinearConversionType linearConversion = objectModel.getLinearConversion(parameterType);
            if (linearConversion != null) {
                propertyValue = Double.toString(
                        ConversionMethods.toGldValue(Double.parseDouble(propertyValue), linearConversion));
            }
            
            PropertyUpdate update = new PropertyUpdate(gldObjectName, propertyName, propertyValue, propertyUnit);
            if (propertyUpdates.contains(update)) {
                // it doesn't matter which update is dropped because interactions are processed in a random order
                log.warn("dropped duplicate property update: {}", update);
            } else {
                propertyUpdates.add(update);
            }
        }
    }
    
    private void queue(ObjectClassType object, String gldObjectName, Map<String, String> attributes) {
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            final String attributeName = attribute.getKey();
            final String attributeValue = attribute.getValue();
            
            AttributeType attributeType = objectModel.getAttribute(object, attributeName);
            if (!objectModel.isGldProperty(attributeType)) {
                log.debug("skipping attribute {} for {}", attributeName, gldObjectName);
                continue;
            }
            log.trace("on attribute {}={}", attributeName, attributeValue);
            
            String propertyName  = objectModel.getPropertyName(attributeType);
            String propertyUnit  = objectModel.getUnitName(attributeType);
            String propertyValue = attributeValue;
            
            LinearConversionType linearConversion = objectModel.getLinearConversion(attributeType);
            if (linearConversion != null) {
                propertyValue = Double.toString(
                        ConversionMethods.toGldValue(Double.parseDouble(propertyValue), linearConversion));
            }
            
            PropertyUpdate update = new PropertyUpdate(gldObjectName, propertyName, propertyValue, propertyUnit);
            if (propertyUpdates.contains(update)) {
                // it doesn't matter which update is dropped because interactions are processed in a random order
                log.warn("dropped duplicate property update: {}", update);
            } else {
                propertyUpdates.add(update);
            }
        }
    }
    
    ////////////////////////////////////////////////////////////
    // Per Time Step Methods
    ////////////////////////////////////////////////////////////
    
    private boolean hasGldExited() {
        try {
            int code = gridlabd.exitValue(); // this will throw an exception if GridLAB-D is still running
            log.debug("GridLAB-D done with exit value {}", code);
            return true;
        } catch (IllegalThreadStateException e) {
            return false;
        }
    }
    
    private void sendPublications() {
        if (isClockPublished) {
            publishClock();
        }
        
        for (InteractionClassType interaction : objectModel.getPublishedInteractions()) {
            if (objectModel.isCoreInteraction(interaction) || !objectModel.isGldObject(interaction)) {
                log.trace("skipped interaction {}", objectModel.getClassPath(interaction));
                continue; // ignore interactions related to gateway infrastructure
            }
            if (publicationManager.isTimeToPublish(objectModel.getClassPath(interaction))) {
                publish(interaction);
            }
        }
        
        for (ObjectInstanceInfo instanceInfo : registeredObjects) {
            // all entries in registeredObjects are relevant so no need for the core/GridLAB-D object conditional
            final String objectClass = instanceInfo.getClassName();
            final String instanceName = instanceInfo.getInstanceName();
            final String gldObjectName = instanceInfo.getGridLabDName();
            publish(objectModel.getObject(objectClass), instanceName, gldObjectName);
        }
    }
    
    private void publishClock() {
        try {
            String timestamp = client.getGlobalVariable("clock");
            long unixtime = ConversionMethods.toUnixTime(timestamp);
            
            Map<String, String> clockValues = new HashMap<String, String>();
            clockValues.put("timeStamp", timestamp);
            clockValues.put("unixTime", Long.toString(unixtime));
            
            if (isInitialized) {
                gateway.sendInteraction(ExtendedObjectModel.GLD_CLOCK, clockValues, gateway.getTimeStamp());
            } else {
                gateway.sendInteraction(ExtendedObjectModel.GLD_CLOCK, clockValues); // send RO for t=0
            }
        } catch (IOException | ParseException | FederateNotExecutionMember | NameNotFound
                | InteractionClassNotPublished | InvalidFederationTime e) {
            log.error("failed to publish clock", e);
        }
    }
    
    private void publish(InteractionClassType interaction) {
        final String classPath = objectModel.getClassPath(interaction);
        
        for (String gldObjectName : objectModel.getPublishedNames(interaction)) {
            Map<String, String> values = getValuesToPublish(interaction, gldObjectName);
            
            if (values.isEmpty()) {
                log.warn("no values to update for {} ({})", gldObjectName, classPath);
                continue;
            }
            values.put("name", gldObjectName);
            
            try {
                if (isInitialized) {
                    gateway.sendInteraction(classPath, values, gateway.getTimeStamp());
                } else {
                    gateway.sendInteraction(classPath, values); // send RO for t=0
                }
            } catch (FederateNotExecutionMember | NameNotFound | InteractionClassNotPublished
                    | InvalidFederationTime e) {
                throw new RTIAmbassadorException(e);
            } 
        }
    }
    
    private Map<String, String> getValuesToPublish(InteractionClassType interaction, String gldObjectName) {
        final String classPath = objectModel.getClassPath(interaction);
        Map<String, String> values = new HashMap<String, String>();
        
        for (ParameterType parameter : objectModel.getParameters(interaction)) {
            final String parameterName = parameter.getName().getValue();
            
            if (!objectModel.isGldProperty(parameter)) {
                continue; // these should be set to a reasonable default value
            }
            
            final String propertyName = objectModel.getPropertyName(parameter);
            final String propertyUnit = objectModel.getUnitName(parameter);
            log.trace("on {}.{} = {}:{}", classPath, parameterName, gldObjectName, propertyName);
            
            try {
                if (propertyUnit != null) {
                    log.trace("processed using unit {}", propertyUnit);
                    double propertyValue = client.getDoubleProperty(gldObjectName, propertyName, propertyUnit);
                    values.put(parameterName, Double.toString(propertyValue));
                } else if (objectModel.isDouble(parameter)) {
                    log.trace("processed as type double");
                    double propertyValue = client.getDoubleProperty(gldObjectName, propertyName);
                    LinearConversionType conversionRule = objectModel.getLinearConversion(parameter);
                    if (conversionRule != null) {
                        propertyValue = ConversionMethods.toHlaValue(propertyValue, conversionRule);
                    }
                    values.put(parameterName, Double.toString(propertyValue));
                } else {
                    log.trace("processed as type string");
                    String propertyValue = client.getStringProperty(gldObjectName, propertyName);
                    values.put(parameterName, propertyValue);
                }
            } catch (IOException e) {
                log.error("unable to get {}:{}", gldObjectName, propertyName);
            }
        }
        return values;
    }
    
    private void publish(ObjectClassType object, String instanceName, String gldObjectName) {
        final String classPath = objectModel.getClassPath(object);
        Map<String, String> values = new HashMap<String, String>();
        
        for (AttributeType attribute : objectModel.getAttributes(object)) {
            final String attributeName = attribute.getName().getValue();
            
            if (!objectModel.isGldProperty(attribute)) {
                continue;
            }
            
            if (!publicationManager.isTimeToPublish(classPath, attributeName)) {
                continue;
            }
            
            final String propertyName = objectModel.getPropertyName(attribute);
            final String propertyUnit = objectModel.getUnitName(attribute);
            log.trace("on {}.{} = {}:{}", classPath, attributeName, gldObjectName, propertyName);
            
            try {
                if (propertyUnit != null) {
                    log.trace("unit conversion with unit {}", propertyUnit);
                    double propertyValue = client.getDoubleProperty(gldObjectName, propertyName, propertyUnit);
                    values.put(attributeName, Double.toString(propertyValue));
                } else if (objectModel.isDouble(attribute)) {
                    log.trace("processed as type double");
                    double propertyValue = client.getDoubleProperty(gldObjectName, propertyName);
                    LinearConversionType conversionRule = objectModel.getLinearConversion(attribute);
                    if (conversionRule != null) {
                        propertyValue = ConversionMethods.toHlaValue(propertyValue, conversionRule);
                    }
                    values.put(attributeName, Double.toString(propertyValue));
                } else {
                    log.trace("processed as type string");
                    String propertyValue = client.getStringProperty(gldObjectName, propertyName);
                    values.put(attributeName, propertyValue);
                }
            } catch (IOException e) {
                log.error("unable to get {}:{}", gldObjectName, propertyName);
            }
        }
        
        if (!isInitialized) {
            // send the name only during initialization
            values.put("name", gldObjectName);
        }
        
        if (values.isEmpty()) {
            log.warn("no values to update for {} ({}:{})", gldObjectName, classPath, instanceName);
            return;
        }
        
        try {
            if (isInitialized) {
                gateway.updateObject(instanceName, values, gateway.getTimeStamp());
            } else {
                gateway.updateObject(instanceName, values); // send RO for t=0
            }
        } catch (FederateNotExecutionMember | ObjectNotKnown | NameNotFound | AttributeNotOwned
                | InvalidFederationTime e) {
            throw new RTIAmbassadorException(e);
        }
    }
    
    private void updateGldProperties() {
        for (PropertyUpdate update : propertyUpdates) {
            try {
                updateProperty(update);
            } catch (IOException e) {
                throw new GridLabDException(e);
            }
        }
        propertyUpdates.clear();
    }
    
    private void updateProperty(PropertyUpdate update)
            throws IOException {
        log.trace("next update: {}", update);
        
        if (update.getUnit() == null) {
            client.setStringProperty(update.getObjectName(), update.getPropertyName(), update.getPropertyValue());
        } else {
            final double value = Double.parseDouble(update.getPropertyValue());
            client.setDoubleProperty(update.getObjectName(), update.getPropertyName(), value, update.getUnit());
        }
    }
    
    private void advanceSimulationTime(long unixTime)
            throws InterruptedException, IOException {
        final String timeStamp = ConversionMethods.toTimeStamp(unixTime);
        
        if (!isRunForever && unixTime >= configuration.getUnixTimeStop()) {
            log.info("running last time step (GridLAB-D will crash)");
            try {
                client.pauseat(timeStamp);
                // the GridLAB-D server will not respond to this HTTP GET request
                // the pauseat command should throw an exception in response to this
                throw new GridLabDException("unreachable code");
            } catch (IOException e) {
                log.info("GridLAB-D simulation complete");
                gateway.requestExit();
                return;
            }
        }
        
        client.pauseat(timeStamp);
        
        while (!client.isPaused()) {
            log.debug("waiting {} ms for GridLAB-D clock to advance", configuration.getWaitTimeMs());
            Thread.sleep(configuration.getWaitTimeMs());
        }
        log.info("advanced GridLAB-D simulation to {}", timeStamp);
        // maybe check if GridLAB-D advanced to the requested time ?
    }
    
    private long getNextPauseTime(double currentTime) {
        double elapsedTime = (currentTime + configuration.getStepSize()) * configuration.getSimulationTimeScale();
        long nextPauseTime = configuration.getUnixTimeStart() + Double.valueOf(elapsedTime).longValue();
        if (!isRunForever && nextPauseTime > configuration.getUnixTimeStop()) {
            nextPauseTime = configuration.getUnixTimeStop();
        }
        return nextPauseTime;
    }
}

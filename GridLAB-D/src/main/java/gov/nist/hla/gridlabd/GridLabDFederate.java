package gov.nist.hla.gridlabd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

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

public class GridLabDFederate implements GatewayCallback {    
    private static final Logger log = LogManager.getLogger();
    
    final private GridLabDConfig configuration;
    
    final private ExtendedObjectModel objectModel;
    
    final private SimpleDateFormat dateFormat;

    final private GatewayFederate gateway;
    
    final private GridLabDClient client;
    
    private Process gridlabd = null;
    
    private TimeToUpdate publicationManager = null;
    
    private Set<PropertyUpdate> propertyUpdates = new HashSet<PropertyUpdate>();
    
    private Map<String, ObjectState> objectStates = new HashMap<String, ObjectState>();
    
    private Set<NamePair> registeredObjects = new HashSet<NamePair>();
    
    private boolean receivedSimTime = false;
    
    private boolean receivedSimEnd = false;
    
    private boolean isInitialized = false;
    
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
        } catch (Exception e) {
            log.error(e);
            return;
        }
        
        log.info("Done.");
    }
    
    public GridLabDFederate(GridLabDConfig configuration)
            throws SchemaValidationException {
        this.configuration = configuration;
        this.objectModel = new ExtendedObjectModel(configuration.getFomFilepath());
        this.publicationManager = new TimeToUpdate(objectModel);
        
        // GridLAB-D accepts date formats using both the simulation time zone and GMT
        //  future releases of GridLAB-D will continue to support GMT as an additional time zone
        //  the conversion from GMT to native simulation time will be performed inside GridLAB-D
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        this.gateway = new GatewayFederate(configuration, this, objectModel);
        this.client = new GridLabDClient("localhost", configuration.getServerPortNumber());
    }
    
    public void run() {
        log.trace("run");
        gateway.run();
    }
    
    @Override
    public void initializeSelf() {
        log.trace("initializeSelf");
        // do nothing
    }
    
    @Override
    public void initializeWithPeers() {
        log.trace("initializeWithPeers");
        
        if (configuration.getUseSimTime()) {
            waitForSimTime(); // this will modify configuration
        }
        
        if (configuration.getUnixTimeStop() < 0) {
            log.info("configured to run using stoptime=NEVER");
        }
        
        // the GridLAB-D federate has two independent exit conditions:
        //  1. the GridLAB-D federate receives the SimEnd interaction
        //  2. the GridLAB-D simulation reaches the configured stop time
        if (!objectModel.isSubscribed(ExtendedObjectModel.SIMULATION_END)) {
            log.warn("configured to run without " + ExtendedObjectModel.SIMULATION_END);
            if (configuration.getUnixTimeStop() < 0) {
                throw new GridLabDException("no exit condition");
            }
        }
        
        try {
            startGld();
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
            case ExtendedObjectModel.SIMULATION_TIME:
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
        
        try {
            int code = gridlabd.exitValue(); // this will throw an exception if GridLAB-D is still running
            log.info("GridLAB-D done with exit value {}", code);
            gateway.requestExit();
            return;
        } catch (IllegalThreadStateException e) {
            // do nothing; this is not a real exception
        }
        
        publicationManager.step(timeStep); // has to happen before sendPublications
        sendPublications();
        updateProperties();
        
        try {
            double elapsedTime = (timeStep + configuration.getStepSize()) * configuration.getSimulationTimeScale();
            long nextPauseTime = configuration.getUnixTimeStart() + Double.valueOf(elapsedTime).longValue();
            if (nextPauseTime > configuration.getUnixTimeStop()) {
                nextPauseTime = configuration.getUnixTimeStop();
            }
            advanceSimulationTime(nextPauseTime);
        } catch (InterruptedException | IOException e) {
            throw new GridLabDException(e);
        }
    }
    
    @Override
    public void terminate() {
        log.trace("terminate");
        
        if (!propertyUpdates.isEmpty()) {
            log.warn("dropped {} updates queued for GridLAB-D", propertyUpdates.size());
        }
    }
    
    private void waitForSimTime() {
        log.trace("waitForSimTime");
        
        if (!objectModel.isSubscribed(ExtendedObjectModel.SIMULATION_TIME)) {
            throw new GridLabDException("no subscription for " + ExtendedObjectModel.SIMULATION_TIME);
        }
        
        while (!receivedSimTime) {
            try {
                log.info("waiting {} ms to receive {}", configuration.getWaitTimeMs(), ExtendedObjectModel.SIMULATION_TIME);
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
    
    private void startGld()
            throws IOException {
        log.trace("startGld");
        
        String timeStart = toTimeStamp(configuration.getUnixTimeStart());
        String timeStop  = configuration.getUnixTimeStop() < 0 ? "NEVER" : toTimeStamp(configuration.getUnixTimeStop());
        String timeZone  = configuration.getSimulationTimeZone();
        configuration.getSimulationTimeScale(); // to trigger early exception if the time scale is not set
        
        log.debug("creating process builder for GridLAB-D");
        ProcessBuilder builder = new ProcessBuilder();
        builder.inheritIO(); // maybe replace
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
        
        if (configuration.getWorkingDirectory() != null) {
            final File workingDirectory = new File(configuration.getWorkingDirectory());
            builder.directory(workingDirectory.getAbsoluteFile());
            log.debug("directory: {}", workingDirectory.getAbsolutePath());
        }
        
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
                connected = client.isPaused(); // true when GridLAB-D model initialized
            } catch (IOException e) {
                log.warn("failed to connect to GridLAB-D server");
            }
        }
    }
    
    private void handleSimEnd() {
        log.trace("handleSimEnd");
        receivedSimEnd = true;
    }
    
    private void handleSimTime(Map<String, String> parameters) {
        log.trace("handleSimTime {}", parameters.toString());
        
        if (receivedSimTime) {
            // replace the old values with the latest received ones
            log.warn("received duplicate {}", ExtendedObjectModel.SIMULATION_TIME);
        }
        
        final long unixTimeStart = Long.valueOf(parameters.get("unixTimeStart"));
        final long unixTimeStop  = Long.valueOf(parameters.get("unixTimeStop"));
        final double timeScale   = Double.valueOf(parameters.get("timeScale"));
        final String timeZone    = parameters.get("timeZone");
        
        configuration.setUnixTimeStart(unixTimeStart);
        configuration.setUnixTimeStop(unixTimeStop);
        configuration.setSimulationTimeScale(timeScale);
        configuration.setSimulationTimeZone(timeZone);
        receivedSimTime = true;
    }
    
    private void handleInteraction(String className, Map<String, String> parameters) {
        log.trace("handleInteraction {} as {}", className, parameters.toString());
        
        InteractionClassType interaction = objectModel.getInteraction(className);
        if (objectModel.isCoreInteraction(interaction)) {
            log.debug("skipped core interaction {}", className);
            return;
        }
        
        final String gldObjectName = parameters.get("name");
        if (gldObjectName == null || gldObjectName.isEmpty()) {
            log.error("subscribed interaction omits GridLAB-D object name: {}", className);
            return;
        }
        
        queue(interaction, gldObjectName, parameters);
    }

    private void handleObjectReflection(String className, String instanceName, Map<String, String> attributes) {
        log.trace("handleObjectReflection {}:{} as {}", className, instanceName, attributes.toString());
        
        ObjectClassType object = objectModel.getObject(className);
        if (objectModel.isCoreObject(object)) {
            log.debug("skipped core object {}:{}", className, instanceName);
            return;
        }
        
        if (!objectStates.containsKey(instanceName)) {
            objectStates.put(instanceName, new ObjectState(className, instanceName));
            log.info("discovered object {}:{}", className, instanceName);
        }
        
        ObjectState state = objectStates.get(instanceName);
        state.reflectAttributes(attributes);
        
        final String receivedName = attributes.get("name");
        final String storedName = state.getAttribute("name");
        if (receivedName != null && !receivedName.isEmpty()) {
            // if we receive a new value for the name attribute, then the GridLAB-D object with name="receivedName" has
            // no prior history with the object instance being handled in this method. therefore, the entire instance
            // state (represented by the state variable) must be reflected into GridLAB-D using the received name.
            queue(object, receivedName, state.getAttributes());
        } else if (storedName != null && !storedName.isEmpty()) {
            // if we do not receive the name attribute, but have a stored name for this instance from a prior object
            // reflection, then the GridLAB-D object with name="storedName" must have a history of being updated by
            // this method during prior iterations. therefore, we just append the latest received attributes.
            queue(object, storedName, attributes);
        } else {
            // if we have never received a name attribute, then there is no GridLAB-D object that can be updated. the
            // received attributes are reflected into objectStates and will be handled during a future iteration when a
            // value for the name attribute is received.
            log.warn("deferred update of {}:{} until name attribute received", className, instanceName);
        }
    }
    
    private void queue(InteractionClassType interaction, String gldObjectName, Map<String, String> parameters) {
        log.trace("queue interaction for {} as {}", gldObjectName, parameters.toString());
        
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            final String parameterName = parameter.getKey();
            final String parameterValue = parameter.getValue();
            
            ParameterType parameterType = objectModel.getParameter(interaction, parameterName);
            if (parameterName.equals("name") || !objectModel.isRelevantParameter(parameterType)) {
                log.debug("skipped parameter {}", parameterName);
                continue;
            }
            log.trace("on parameter {}={}", parameterName, parameterValue);
            
            String propertyName  = objectModel.getPropertyName(parameterType);
            String propertyUnit  = objectModel.getUnitName(parameterType);
            String propertyValue = parameterValue;
            
            LinearConversionType linearConversion = objectModel.getLinearConversion(parameterType);
            if (linearConversion != null) {
                double convertedValue = convertToGld(linearConversion, Double.parseDouble(propertyValue));
                propertyValue = Double.toString(convertedValue);
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
        log.trace("queue object for {} as {}", gldObjectName, attributes.toString());
        
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            final String attributeName = attribute.getKey();
            final String attributeValue = attribute.getValue();
            
            AttributeType attributeType = objectModel.getAttribute(object, attributeName);
            if (attributeName.equals("name") || !objectModel.isRelevantAttribute(attributeType)) {
                log.debug("skipping attribute {}", attributeName);
                continue;
            }
            log.trace("on attribute {}={}", attributeName, attributeValue);
            
            String propertyName  = objectModel.getPropertyName(attributeType);
            String propertyUnit  = objectModel.getUnitName(attributeType);
            String propertyValue = attributeValue;
            
            LinearConversionType linearConversion = objectModel.getLinearConversion(attributeType);
            if (linearConversion != null) {
                double convertedValue = convertToGld(linearConversion, Double.parseDouble(propertyValue));
                propertyValue = Double.toString(convertedValue);
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
    
    private void updateProperties() {
        log.trace("updateProperties");
        
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
        log.trace("updateProperty {}", update);
        
        if (update.getUnit() == null) {
            log.trace("no unit defined");
            client.setStringProperty(update.getObjectName(), update.getPropertyName(), update.getPropertyValue());
        } else {
            log.trace("using unit conversion [{}]", update.getUnit());
            final double value = Double.parseDouble(update.getPropertyValue());
            client.setDoubleProperty(update.getObjectName(), update.getPropertyName(), value, update.getUnit());
        }
    }
    
    private void registerObjectInstances() {
        log.trace("registerObjectInstances");
        
        for (ObjectClassType publishedObject : objectModel.getPublishedObjects()) {
            final String objectClass = objectModel.getClassPath(publishedObject);
            
            if (objectModel.isCoreObject(publishedObject)) {
                log.debug("skipped core object {}", objectClass);
                continue; // ignore objects related to gateway infrastructure
            }
            
            for (String gldObjectName : objectModel.getPublishedNames(publishedObject)) {
                try {
                    // assign a random instance name to avoid collision
                    final String instanceName = gateway.registerObjectInstance(objectClass);
                    objectStates.put(instanceName, new ObjectState(objectClass, instanceName));
                    registeredObjects.add(new NamePair(instanceName, gldObjectName));
                    log.debug("registered {}:{} to publish {}", objectClass, instanceName, gldObjectName);
                } catch (FederateNotExecutionMember | NameNotFound | ObjectClassNotPublished e) {
                    throw new RTIAmbassadorException(e);
                }
            }
        }
    }
    
    private void sendPublications() {
        log.trace("sendPublications");
        
        for (NamePair names : registeredObjects) {
            final String instanceName = names.getInstanceName();
            final String gldObjectName = names.getGridLabDName();
            final String objectClass = objectStates.get(instanceName).getClassName();
            
            ObjectClassType object = objectModel.getObject(objectClass);
            publish(object, instanceName, gldObjectName);
        }
        
        for (InteractionClassType interaction : objectModel.getPublishedInteractions()) {
            if (objectModel.isCoreInteraction(interaction)) {
                log.debug("skipped core interaction {}", objectModel.getClassPath(interaction));
                continue; // ignore interactions related to gateway infrastructure
            }
            if (publicationManager.isTimeToUpdate(objectModel.getClassPath(interaction))) {
                publish(interaction);
            }
        }
    }
    
    private void publish(ObjectClassType object, String instanceName, String gldObjectName) {
        final String classPath = objectModel.getClassPath(object);
        log.trace("publish {} as {}:{}", gldObjectName, object, instanceName);
        
        Map<String, String> updatedValues = new HashMap<String, String>();
        for (AttributeType attribute : objectModel.getAttributes(object)) {
            final String attributeName = attribute.getName().getValue();
            
            if (!objectModel.isRelevantAttribute(attribute) || attributeName.equals("name")) {
                log.debug("skipping attribute {}", attributeName);
                continue;
            }
            log.trace("on attribute {}", attributeName);
            
            if (!publicationManager.isTimeToUpdate(classPath, attributeName)) {
                log.debug("skipping {}:{} until time to update", classPath, attributeName);
                continue;
            }
            
            String propertyName = objectModel.getPropertyName(attribute);
            String propertyUnit = objectModel.getUnitName(attribute);
            
            try {
                if (propertyUnit != null) {
                    log.trace("unit conversion with unit {}", propertyUnit);
                    double propertyValue = client.getDoubleProperty(gldObjectName, propertyName, propertyUnit);
                    log.debug("got {}:{}={} [{}]", gldObjectName, propertyName, propertyValue, propertyUnit);
                    updatedValues.put(attributeName, Double.toString(propertyValue));
                } else if (objectModel.isDouble(attribute)) {
                    log.trace("double property");
                    double propertyValue = client.getDoubleProperty(gldObjectName, propertyName);
                    LinearConversionType conversionRule = objectModel.getLinearConversion(attribute);
                    if (conversionRule != null) {
                        propertyValue = convertToHla(conversionRule, propertyValue);
                    }
                    log.debug("got {}:{}={}", gldObjectName, propertyName, propertyValue);
                    updatedValues.put(attributeName, Double.toString(propertyValue));
                } else {
                    log.trace("string property");
                    String propertyValue = client.getStringProperty(gldObjectName, propertyName);
                    log.debug("got {}:{}={}", gldObjectName, propertyName, propertyValue);
                    updatedValues.put(attributeName, propertyValue);
                }
            } catch (IOException e) {
                log.error("unable to get {}:{}", gldObjectName, propertyName);
            }
        }
        
        if (!isInitialized) {
            // send the name only during initialization
            updatedValues.put("name", gldObjectName);
        }
        
        if (updatedValues.isEmpty()) {
            log.warn("no values to update for {} ({}:{})", gldObjectName, classPath, instanceName);
            return;
        }
        
        try {
            gateway.updateObject(instanceName, updatedValues, gateway.getTimeStamp());
        } catch (FederateNotExecutionMember | ObjectNotKnown | NameNotFound | AttributeNotOwned | InvalidFederationTime e) {
            throw new RTIAmbassadorException(e);
        }
    }
    
    private void publish(InteractionClassType interaction) {
        final String classPath = objectModel.getClassPath(interaction);
        log.trace("publish {}", classPath);
        
        for (String gldObjectName : objectModel.getPublishedNames(interaction)) {
            Map<String, String> updatedValues = new HashMap<String, String>();
            for (ParameterType parameter : objectModel.getParameters(interaction)) {
                final String parameterName = parameter.getName().getValue();
                
                if (!objectModel.isRelevantParameter(parameter) || parameterName.equals("name")) {
                    // these should be set to a reasonable default value
                    log.debug("skipping parameter {}", parameterName);
                    continue;
                }
                log.trace("on parameter {}", parameterName);
                
                String propertyName = objectModel.getPropertyName(parameter);
                String propertyUnit = objectModel.getUnitName(parameter);
                
                try {
                    if (propertyUnit != null) {
                        log.trace("unit conversion with unit {}", propertyUnit);
                        double propertyValue = client.getDoubleProperty(gldObjectName, propertyName, propertyUnit);
                        log.debug("got {}:{}={} [{}]", gldObjectName, propertyName, propertyValue, propertyUnit);
                        updatedValues.put(parameterName, Double.toString(propertyValue));
                    } else if (objectModel.isDouble(parameter)) {
                        log.trace("double property");
                        double propertyValue = client.getDoubleProperty(gldObjectName, propertyName);
                        LinearConversionType conversionRule = objectModel.getLinearConversion(parameter);
                        if (conversionRule != null) {
                            propertyValue = convertToHla(conversionRule, propertyValue);
                        }
                        log.debug("got {}:{}={}", gldObjectName, propertyName, propertyValue);
                        updatedValues.put(parameterName, Double.toString(propertyValue));
                    } else {
                        log.trace("string property");
                        String propertyValue = client.getStringProperty(gldObjectName, propertyName);
                        log.debug("got {}:{}={}", gldObjectName, propertyName, propertyValue);
                        updatedValues.put(parameterName, propertyValue);
                    }
                } catch (IOException e) {
                    log.error("unable to get {}:{}", gldObjectName, propertyName);
                }
            }
            
            if (updatedValues.isEmpty()) {
                log.warn("no values to update for {} ({})", classPath, gldObjectName);
                return;
            }
            
            updatedValues.put("name", gldObjectName);
            
            try {
                gateway.sendInteraction(classPath, updatedValues, gateway.getTimeStamp());
            } catch (FederateNotExecutionMember | NameNotFound | InteractionClassNotPublished | InvalidFederationTime e) {
                throw new RTIAmbassadorException(e);
            } 
        }
    }
    
    private void advanceSimulationTime(long unixTime)
            throws InterruptedException, IOException {
        log.trace("advanceSimulationTime {}", unixTime);
        
        final String timeStamp = toTimeStamp(unixTime);
        
        if (unixTime >= configuration.getUnixTimeStop()) {
            log.info("running last timestep (GridLAB-D will crash)");
            try {
                client.pauseat(timeStamp);
                // the GridLAB-D server will not respond to this HTTP GET request
                // the pauseat command should throw an exception in response to this
                throw new GridLabDException("unreachable code");
            } catch (IOException e) {
                log.info("GridLAB-D simulation complete");
            }
            return;
        }
        
        client.pauseat(timeStamp);
        
        while (!client.isPaused()) {
            log.debug("waiting {} ms for GridLAB-D clock to advance", configuration.getWaitTimeMs());
            Thread.sleep(configuration.getWaitTimeMs());
        }
        log.info("advanced GridLAB-D simulation to {}", timeStamp);
        // maybe check if GridLAB-D advanced to the requested time ?
    }
    
    private String toTimeStamp(long unixTime) {
        log.trace("toTimeStamp {}", unixTime);
        return dateFormat.format(new Date(unixTime*1000));
    }
    
    private double convertToHla(LinearConversionType conversion, double value) {
        log.trace("convertToHla");
        
        double scale = conversion.getScale();
        double offset = conversion.getOffset();
        double result = scale * value + offset;
        
        log.debug("GLD to HLA : {} = {} * {} - {}", result, scale, value, offset);
        return result;
    }
    
    private double convertToGld(LinearConversionType conversion, double value) {
        log.trace("convertToGld");
        
        double scale  = conversion.getScale();
        double offset = conversion.getOffset();
        double result = (value - offset) / scale;
        
        log.debug("HLA to GLD : {} = ({} - {}) / {}", result, value, offset, scale);
        return result;
    }
}

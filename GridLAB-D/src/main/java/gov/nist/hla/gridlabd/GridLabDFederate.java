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
import gov.nist.pages.ucef.AttributeConfigType;
import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ObjectClassConfigType;
import gov.nist.pages.ucef.ParameterConfigType;
import gov.nist.pages.ucef.PublishedObjectsType;
import gov.nist.pages.ucef.UnitConversionType;
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
import org.ieee.standards.ieee1516._2010.AttributeType;
import org.ieee.standards.ieee1516._2010.InteractionClassType;
import org.ieee.standards.ieee1516._2010.ObjectClassType;
import org.ieee.standards.ieee1516._2010.ParameterType;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GridLabDFederate implements GatewayCallback {    
    private static final Logger log = LogManager.getLogger();
    
    private static final String INTERACTION_ROOT = "InteractionRoot.C2WInteractionRoot";
    
    private static final String SIMULATION_END = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimEnd";
    
    private static final String SIMULATION_TIME = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime";
    
    private static final String OBJECT_ROOT = "ObjectRoot";
    
    final private GridLabDConfig configuration;
    
    final private SimpleDateFormat dateFormat;
    
    final private GatewayFederate gateway;
    
    final private GridLabDClient client;
    
    private Process gridlabd = null;
    
    private double nextPauseTime;
    
    private boolean isInitialized = false;
    
    private boolean receivedSimTime = false;
    
    private boolean receivedSimEnd = false;
    
    private Map<String, String> gldObjectName = new HashMap<String, String>();
    
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
        
        nextPauseTime = configuration.getUnixTimeStart();
        
        if (configuration.getUnixTimeStop() < 0) {
            log.info("running without a simulation stop time");
        }
        InteractionClassType simEnd = gateway.getObjectModel().getInteraction(SIMULATION_END);
        if (!gateway.getObjectModel().getSubscribedInteractions().contains(simEnd)) {
            log.info("running without support for " + SIMULATION_END);
            if (configuration.getUnixTimeStop() < 0) {
                log.warn("no exit condition; will run forever!");
            }
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
            handleInteraction(className, parameters);
        } else {
            log.warn("dropped interaction " + className);
        }
    }

    @Override
    public void receiveObject(Double timeStep, String className, String instanceName, Map<String, String> attributes) {
        log.trace("receiveObject {} {} {} {}", timeStep, className, instanceName, attributes.toString());
        
        handleObjectReflection(className, instanceName, attributes);
    }
    
    @Override
    public void doTimeStep(Double timeStep) {
        log.trace("doTimeStep {}", timeStep);
        
        try {
            int code = gridlabd.exitValue();
            log.info("GridLAB-D done with exit value {}", code);
            gateway.requestExit();
        } catch (IllegalThreadStateException e) {
            // maybe check if GridLAB-D advanced to the expected time ?
            nextPauseTime += configuration.getStepSize() * configuration.getSimulationTimeScale();
            if (nextPauseTime > configuration.getUnixTimeStop()) {
                nextPauseTime = configuration.getUnixTimeStop();
            }
            try {
                advanceSimulationTime(Double.valueOf(nextPauseTime).longValue());
            } catch (InterruptedException | IOException e2) {
                throw new GridLabDException(e2);
            } 
        }
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
    
    private void advanceSimulationTime(long unixTime)
            throws InterruptedException, IOException {
        log.trace("advanceSimulationTime {}", unixTime);
        
        final String timeStamp = toTimeStamp(unixTime);
        client.pauseat(timeStamp);
        
        while (!client.isPaused()) {
            log.debug("waiting {} ms for GridLAB-D clock to advance", configuration.getWaitTimeMs());
            Thread.sleep(configuration.getWaitTimeMs());
        }
        log.info("advanced GridLAB-D simulation to {}", timeStamp);
    }
    
    private void handleInteraction(String className, Map<String, String> parameters) {
        log.trace("handleInteraction {} {}", className, parameters.toString());
        
        InteractionClassType root = gateway.getObjectModel().getInteraction(INTERACTION_ROOT);
        Set<ParameterType> rootParameters = gateway.getObjectModel().getParameters(root);
        
        Set<String> rootParameterNames = new HashSet<String>();
        for (ParameterType parameter : rootParameters) {
            rootParameterNames.add(parameter.getName().getValue());
        }
        
        InteractionClassType interaction = gateway.getObjectModel().getInteraction(className);
        if (isGlobalVariable(interaction)) {
            log.warn("update for global variables defined in {} not supported", className);
            return;
        }
        
        String gldObjectName = parameters.get("name");
        log.debug("received update for {}", gldObjectName);
        
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            if (rootParameterNames.contains(parameter.getKey())) {
                log.debug("skipping parameter {} from {}", parameter.getKey(), INTERACTION_ROOT);
                continue;
            }
            if (parameter.getKey().equals("name")) {
                continue;
            }
            ParameterType param = gateway.getObjectModel().getParameter(interaction, parameter.getKey());
            String unit = doNameConversion(param);
            try {
                if (unit != null) {
                    double value = Double.parseDouble(parameter.getValue());
                    // this cannot be more efficient due to the limitations of GridLAB-D
                    client.setObjectProperty(gldObjectName, parameter.getKey(), value, unit);
                    log.debug("set {}:{}={} [{}]", gldObjectName, parameter.getKey(), value, unit);
                } else {
                    String gldValue = doLinearConversion(param, parameter.getValue());
                    // this cannot be more efficient due to the limitations of GridLAB-D
                    client.setObjectProperty(gldObjectName, parameter.getKey(), gldValue);
                    log.debug("set {}:{}={}", gldObjectName, parameter.getKey(), gldValue);
                }
            } catch (IOException e) {
                log.error("could not set value for {}:{}", gldObjectName, parameter.getKey());
            }
        }
    }
    
    private String doNameConversion(AttributeType attribute) {
        log.trace("doNameConversion {}", attribute.getName().getValue());
        
        // unit conversion only works with type double in GridLAb-D ; how to check ?
        
        for (FeatureMap.Entry feature : attribute.getAny()) {
            if (feature.getValue() instanceof AttributeConfigType) {
                AttributeConfigType attributeConfig = (AttributeConfigType) feature.getValue();
                
                UnitConversionType unitConversion = attributeConfig.getUnitConversion();
                
                if (unitConversion.getNameConversion() != null) {
                    return unitConversion.getNameConversion();
                }
            }
        }
        log.debug("no conversion rule defined");
        return null;
    }
    
    private String doLinearConversion(AttributeType attribute, String hlaValue) {
        log.trace("doLinearConversion {} {}", attribute.getName().getValue(), hlaValue);
        
        // unit conversion only works with type double in GridLAb-D ; how to check ?
        
        for (FeatureMap.Entry feature : attribute.getAny()) {
            if (feature.getValue() instanceof AttributeConfigType) {
                AttributeConfigType attributeConfig = (AttributeConfigType) feature.getValue();
                
                UnitConversionType unitConversion = attributeConfig.getUnitConversion();
                
                if (unitConversion.getLinearConversion() != null) {
                    return doLinearConversion(unitConversion.getLinearConversion(), hlaValue);
                }
            }
        }
        log.debug("no conversion rule defined");
        return hlaValue;
    }
    
    private String doNameConversion(ParameterType parameter) {
        log.trace("doNameConversion {}", parameter.getName().getValue());
        
        // unit conversion only works with type double in GridLAb-D ; how to check ?
        
        for (FeatureMap.Entry feature : parameter.getAny()) {
            if (feature.getValue() instanceof ParameterConfigType) {
                ParameterConfigType parameterConfig = (ParameterConfigType) feature.getValue();
                
                UnitConversionType unitConversion = parameterConfig.getUnitConversion();
                
                if (unitConversion.getNameConversion() != null) {
                    return unitConversion.getNameConversion();
                }
            }
        }
        log.debug("no conversion rule defined");
        return null;
    }
    
    private String doLinearConversion(LinearConversionType conversion, String value){
        log.trace("doLinearConversion");
        double scale = conversion.getScale();
        double offset = conversion.getOffset();
        double y = Double.parseDouble(value);
        double x = (y - offset) / scale;
        log.debug("linear conversion {} = ({} - {}) / {}", x, y, offset, scale);
        return Double.toString(x);
    }
    
    private String doLinearConversion(ParameterType parameter, String hlaValue) {
        log.trace("doLinearConversion {} {}", parameter.getName().getValue(), hlaValue);
        
        // unit conversion only works with type double in GridLAb-D ; how to check ?
        
        for (FeatureMap.Entry feature : parameter.getAny()) {
            if (feature.getValue() instanceof ParameterConfigType) {
                ParameterConfigType parameterConfig = (ParameterConfigType) feature.getValue();
                
                UnitConversionType unitConversion = parameterConfig.getUnitConversion();
                
                if (unitConversion.getLinearConversion() != null) {
                    return doLinearConversion(unitConversion.getLinearConversion(), hlaValue);
                }
            }
        }
        log.debug("no conversion rule defined");
        return hlaValue;
    }
    
    private void handleObjectReflection(String className, String instanceName, Map<String, String> attributes) {
        log.trace("handleObjectReflection {} {} {}", className, instanceName, attributes.toString());
        
        if (!gldObjectName.containsKey(instanceName) && !attributes.containsKey("name")) {
            // might be better to store this to retrieve later when we get a name
            log.warn("skipped update for {} - missing name", instanceName);
            return;
        }
        
        if (attributes.containsKey("name")) {
            gldObjectName.put(instanceName, attributes.get("name"));
            log.debug("using {} for object instance {}", attributes.get("name"), instanceName);
        }
        
        ObjectClassType object = gateway.getObjectModel().getObject(className);
        if (isGlobalVariable(object)) {
            log.warn("update for global variables defined in {} not supported", className);
            return;
        }
        
        String name = gldObjectName.get(instanceName);
        log.debug("received update for {}", name);
        
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            if (attribute.getKey().equals("name")) {
                continue;
            }
            AttributeType attr = gateway.getObjectModel().getAttribute(object, attribute.getKey());
            String unit = doNameConversion(attr);
            try {
                if (unit != null) {
                    double value = Double.parseDouble(attribute.getValue());
                    // this cannot be more efficient due to the limitations of GridLAB-D
                    client.setObjectProperty(name, attribute.getKey(), value, unit);
                    log.debug("set {}:{}={} [{}]", name, attribute.getKey(), value, unit);
                } else {
                    String gldValue = doLinearConversion(attr, attribute.getValue());
                    // this cannot be more efficient due to the limitations of GridLAB-D
                    client.setObjectProperty(name, attribute.getKey(), gldValue);
                    log.debug("set {}:{}={}", name, attribute.getKey(), gldValue);
                }
            } catch (IOException e) {
                log.error("could not set value for {}:{}", name, attribute.getKey());
            }
        }
    }
    
    /*
    private HashMap<String, Integer> objectInstances = new HashMap<String, Integer>();
    
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
    
    private String truncateClassName(String className) {
        String result = className;
        
        int lastPeriod = className.lastIndexOf(".");
        if (lastPeriod > 0) {
          result = className.substring(lastPeriod+1);
        }
        return result;
    }
    */
}

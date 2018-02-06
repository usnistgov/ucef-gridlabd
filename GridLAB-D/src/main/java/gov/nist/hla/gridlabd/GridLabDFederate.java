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
import gov.nist.hla.gateway.exception.RTIAmbassadorException;
import gov.nist.hla.gridlabd.exception.GridLabDException;
import gov.nist.hla.gridlabd.exception.SchemaValidationException;
import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ucefPackage;
import gov.nist.sds4emf.Deserialize;
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
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GridLabDFederate implements GatewayCallback {    
    private static final Logger log = LogManager.getLogger();
    
    private static final String INTERACTION_SIM_TIME = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime";
    
    private static final String INTERACTION_SIM_END = "InteractionRoot.C2WInteractionRoot.SimulationControl.SimEnd";
    
    final private GridLabDConfig configuration;
    
    final private SimpleDateFormat dateFormat;
    
    final private GatewayFederate gateway;
    
    final private ObjectModelHelper objectModelHelper;
    
    final private GridLabDClient client;
    
    private Process gridlabd = null;
    
    private boolean isGridLabDRunning = false;
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
        this.objectModelHelper = new ObjectModelHelper(gateway.getObjectModel());
        this.client = new GridLabDClient("localhost", configuration.getServerPortNumber());
    }
    
    private void registerUcefSchema() {
        log.info("registering schema {}", ucefPackage.eNS_URI);
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
    
    public void run() {
        log.trace("run");
        gateway.run();
    }
    
    @Override
    public void initializeSelf() {
        log.trace("initializeSelf");
    }
    
    @Override
    public void initializeWithPeers() {
        log.trace("initializeWithPeers");
        
        registerObjectInstances();
        
        if (configuration.getUseSimTime()) {
            waitForSimTime();
        } else {
            log.info("configured to run without " + INTERACTION_SIM_TIME);
        }
        
        if (configuration.getUnixTimeStop() < 0) {
            log.info("configured to run with stoptime=NEVER");
        }
        if (!objectModelHelper.isSubscribed(INTERACTION_SIM_END)) {
            log.warn("configured to run without " + INTERACTION_SIM_END);
            if (configuration.getUnixTimeStop() < 0) {
                throw new GridLabDException("no exit condition");
            }
        }
        
        try {
            startGld();
            connectToGld();
            isGridLabDRunning = true;
        } catch (IOException | InterruptedException e) {
            throw new GridLabDException(e);
        }
        
        log.info("Initialized.");
    }
    
    private void registerObjectInstances() {
        log.trace("registerObjectInstances");
        
        for (ObjectClassType object : gateway.getObjectModel().getPublishedObjects()) {
            if (objectModelHelper.isGlobalVariable(object)) {
                registerGlobalVariable(object);
            } else {
                registerObjectInstances(object);
            }
        }
    }
    
    private void registerGlobalVariable(ObjectClassType object) {
        log.trace("registerGlobalVariable {}", object.getName().getValue());
        final String classPath = gateway.getObjectModel().getClassPath(object);
        
        try {
            String instanceName = gateway.registerObjectInstance(classPath);
            //registeredObjectNames.put(classPath, instanceName);
            log.info("registered object {} with class {} to publish global variables", instanceName, classPath);
        } catch (FederateNotExecutionMember | NameNotFound | ObjectClassNotPublished e) {
            throw new RTIAmbassadorException(e);
        }
    }
    
    private void registerObjectInstances(ObjectClassType object) {
        log.trace("registerGldObjects {}", object.getName().getValue());
        final String classPath = gateway.getObjectModel().getClassPath(object);
        
        Set<String> publishedObjectNames = objectModelHelper.getPublishedNames(object);
        
        if (publishedObjectNames.isEmpty()) {
            log.warn("no GridLAB-D object names were defined for published object {}", classPath);
        }
        for (String objectName : publishedObjectNames) {
            try {
                String instanceName = gateway.registerObjectInstance(classPath);
                Map<String, String> initialValues = new HashMap<String, String>();
                initialValues.put("name", objectName);
                gateway.updateObject(instanceName, initialValues);
                //publishedObjectInstances.put(instanceName, objectName);
                log.info("registered object {} with class {} to publish {}", instanceName, classPath, objectName);
            } catch (FederateNotExecutionMember | NameNotFound | ObjectClassNotPublished | ObjectNotKnown
                    | AttributeNotOwned e) {
                throw new GridLabDException(e);
            }
        }
    }
    
    private void waitForSimTime() {
        log.trace("waitForSimTime");
        
        if (!objectModelHelper.isSubscribed(INTERACTION_SIM_TIME)) {
            throw new GridLabDException("no subscription for " + INTERACTION_SIM_TIME);
        }
        
        while (!receivedSimTime) {
            try {
                log.info("waiting {} ms to receive {}", configuration.getWaitTimeMs(), INTERACTION_SIM_TIME);
                Thread.sleep(configuration.getWaitTimeMs());
                gateway.tick();
                
                if (receivedSimEnd) {
                    log.error("received {} prior to initialization", INTERACTION_SIM_END);
                    throw new GridLabDException("unexpected " + INTERACTION_SIM_END);
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
        configuration.getSimulationTimeScale(); // to trigger early exception if missing
        
        log.debug("creating process builder for GridLAB-D");
        ProcessBuilder builder = new ProcessBuilder();
        builder.inheritIO(); // maybe replace
        
        builder.directory(new File(configuration.getWorkingDirectory())); // null is okay
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
                connected = client.isPaused(); // true when GridLAB-D model initialized
            } catch (IOException e) {
                log.warn("failed to connect to GridLAB-D server");
            }
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
    
    @Override
    public void receiveInteraction(Double timeStep, String className, Map<String, String> parameters) {
        log.trace("receiveInteraction {} {} {}", timeStep, className, parameters.toString());
        
        if (className.equals(INTERACTION_SIM_END)) {
            receivedSimEnd = true;
        } else if (className.equals(INTERACTION_SIM_TIME)) {
            configuration.setUnixTimeStart(Long.valueOf(parameters.get("unixTimeStart")));
            configuration.setUnixTimeStop(Long.valueOf(parameters.get("unixTimeStop")));
            configuration.setSimulationTimeScale(Double.valueOf(parameters.get("timeScale")));
            configuration.setSimulationTimeZone(parameters.get("timeZone"));
            receivedSimTime = true;
        } else if (isGridLabDRunning) {
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
            
            sendPublications();
            
            double elapsedTime = (timeStep + configuration.getStepSize()) * configuration.getSimulationTimeScale();
            long nextPauseTime = configuration.getUnixTimeStart() + Double.valueOf(elapsedTime).longValue();
            if (nextPauseTime > configuration.getUnixTimeStop()) {
                nextPauseTime = configuration.getUnixTimeStop();
            }
            try {
                advanceSimulationTime(nextPauseTime);
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
        
        InteractionClassType interaction = gateway.getObjectModel().getInteraction(className);
        if (objectModelHelper.isGlobalVariable(interaction)) {
            log.warn("update for global variables defined in {} not supported", className);
            return;
        }
        
        String gldObjectName = parameters.get("name");
        log.debug("received update for {}", gldObjectName);
        
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            if (objectModelHelper.isRootParameter(parameter.getKey())) {
                log.debug("skipping parameter {}", parameter.getKey());
                continue;
            }
            if (parameter.getKey().equals("name")) {
                continue;
            }
            ParameterType param = gateway.getObjectModel().getParameter(interaction, parameter.getKey());
            String unit = objectModelHelper.getNameConversion(param);
            try {
                if (unit != null) {
                    double value = Double.parseDouble(parameter.getValue());
                    // this cannot be more efficient due to the limitations of GridLAB-D
                    client.setObjectProperty(gldObjectName, parameter.getKey(), value, unit);
                    log.debug("set {}:{}={} [{}]", gldObjectName, parameter.getKey(), value, unit);
                } else {
                    LinearConversionType conversionRule = objectModelHelper.getLinearConversion(param);
                    String gldValue = parameter.getValue();
                    if (conversionRule != null) {
                       gldValue = doLinearConversion(conversionRule, parameter.getValue()); 
                    }
                    // this cannot be more efficient due to the limitations of GridLAB-D
                    client.setObjectProperty(gldObjectName, parameter.getKey(), gldValue);
                    log.debug("set {}:{}={}", gldObjectName, parameter.getKey(), gldValue);
                }
            } catch (IOException e) {
                log.error("could not set value for {}:{}", gldObjectName, parameter.getKey());
            }
        }
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
        if (objectModelHelper.isGlobalVariable(object)) {
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
            String unit = objectModelHelper.getNameConversion(attr);
            try {
                if (unit != null) {
                    double value = Double.parseDouble(attribute.getValue());
                    // this cannot be more efficient due to the limitations of GridLAB-D
                    client.setObjectProperty(name, attribute.getKey(), value, unit);
                    log.debug("set {}:{}={} [{}]", name, attribute.getKey(), value, unit);
                } else {
                    LinearConversionType conversionRule = objectModelHelper.getLinearConversion(attr);
                    String gldValue = attribute.getValue();
                    if (conversionRule != null) {
                       gldValue = doLinearConversion(conversionRule, attribute.getValue()); 
                    }
                    // this cannot be more efficient due to the limitations of GridLAB-D
                    client.setObjectProperty(name, attribute.getKey(), gldValue);
                    log.debug("set {}:{}={}", name, attribute.getKey(), gldValue);
                }
            } catch (IOException e) {
                log.error("could not set value for {}:{}", name, attribute.getKey());
            }
        }
    }

    private void sendPublications() {
        log.trace("sendPublications");
        
        // need to check for global variables 
        for (InteractionClassType interaction : gateway.getObjectModel().getPublishedInteractions()) {
            if (objectModelHelper.isGlobalVariable(interaction)) {
                Map<String, String> updatedValues = new HashMap<String, String>();
                for (ParameterType parameter : gateway.getObjectModel().getParameters(interaction)) {
                    if (objectModelHelper.isRootParameter(parameter.getName().getValue())) {
                        // we probably need to set these to some reasonable default value
                        log.debug("skipping parameter " + parameter.getName().getValue());
                        continue;
                    }
                    
                    try {
                        String value = client.getGlobalVariable(parameter.getName().getValue());
                        // need to check if double and strip units
                        updatedValues.put(parameter.getName().getValue(), value);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        log.warn(e);
                    }
                }
                String className = gateway.getObjectModel().getClassPath(interaction);
                try {
                    gateway.sendInteraction(className, updatedValues, gateway.getTimeStamp());
                } catch (FederateNotExecutionMember | NameNotFound | InteractionClassNotPublished
                        | InvalidFederationTime e) {
                    // TODO Auto-generated catch block
                    log.warn(e);
                }
            } else {
                for (String objectName : objectModelHelper.getPublishedNames(interaction)) {
                    Map<String, String> updatedValues = new HashMap<String, String>();
                    for (ParameterType parameter : gateway.getObjectModel().getParameters(interaction)) {
                        // can a derived re-define a root parameter with same name / different class? uhh...
                        if (objectModelHelper.isRootParameter(parameter.getName().getValue())) {
                            // we probably need to set these to some reasonable default value
                            log.debug("skipping parameter " + parameter.getName().getValue());
                            continue;
                        }
                        String unit = objectModelHelper.getNameConversion(parameter);
                        if (unit != null) {
                            try {
                                double value = client.getObjectProperty(objectName, parameter.getName().getValue(), unit);
                                updatedValues.put(parameter.getName().getValue(), Double.toString(value));
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                log.warn(e);
                            }
                        } else {
                            try {
                                String value = client.getObjectProperty(objectName, parameter.getName().getValue());
                                // need to check if double and strip units
                                LinearConversionType conversionRule = objectModelHelper.getLinearConversion(parameter);
                                if (conversionRule != null) {
                                    value = doLinearConversion(conversionRule, value); 
                                }
                                updatedValues.put(parameter.getName().getValue(), value);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                log.warn(e);
                            }
                        }
                    }
                    String className = gateway.getObjectModel().getClassPath(interaction);
                    try {
                        gateway.sendInteraction(className, updatedValues, gateway.getTimeStamp());
                    } catch (FederateNotExecutionMember | NameNotFound | InteractionClassNotPublished
                            | InvalidFederationTime e) {
                        // TODO Auto-generated catch block
                        log.warn(e);
                    }
                }
            }
        }
        // missing object updates
    }
}

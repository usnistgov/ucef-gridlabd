package gov.nist.hla.gridlabd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.ieee.standards.ieee1516._2010.InteractionClassType;
import org.ieee.standards.ieee1516._2010.ParameterType;
import org.xml.sax.SAXException;

import gov.nist.hla.gateway.GatewayCallback;
import gov.nist.hla.gateway.GatewayFederate;
import gov.nist.hla.gateway.GatewayFederateConfig;
import gov.nist.pages.ucef.InteractionClassConfigType;
import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ParameterConfigType;
import gov.nist.pages.ucef.PublishedObjectsType;
import gov.nist.pages.ucef.UnitConversionType;
import gov.nist.pages.ucef.ucefPackage;
import gov.nist.sds4emf.Deserialize;

public class SchemaTest implements GatewayCallback {
    private static final Logger log = LogManager.getLogger();
    
    private GatewayFederate gateway;
    
    public static void main(String[] args)
            throws IOException, SAXException {
        if (args.length != 1) {
            log.error("missing command line argument for JSON configuration file");
            return;
        }
        
        Deserialize.registerPackage(ucefPackage.eNS_URI, ucefPackage.eINSTANCE);
        GatewayFederateConfig config = GatewayFederate.readConfiguration(args[0]);
        SchemaTest federate = new SchemaTest(config);
        // federate.run();
    }
    
    public SchemaTest(GatewayFederateConfig configuration)
            throws SAXException, IOException {
        this.gateway = new GatewayFederate(configuration, this);
        validate(configuration.getFomFilepath());
    }
    
    private void validate(String fomFilePath)
            throws SAXException, IOException {
        log.trace("validate {}", fomFilePath);
        
        Source xmlFile = new StreamSource(new File(fomFilePath));
        InputStream schemaFile1 = this.getClass().getClassLoader().getResourceAsStream("IEEE1516-DIF-2010.xsd");
        InputStream schemaFile2 = this.getClass().getClassLoader().getResourceAsStream("ucef.xsd");
        
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new Source[] {
                    new StreamSource(schemaFile1),
                    new StreamSource(schemaFile2)});
        Validator validator = schema.newValidator();
        validator.validate(xmlFile);
        
        InteractionClassType interaction = gateway.getObjectModel().getInteraction("InteractionRoot.C2WInteractionRoot");
        log.info(interaction.getName().getValue());
        for (FeatureMap.Entry entry : interaction.getAny()) {
            log.info(entry.getValue().toString());
            if (entry.getValue() instanceof InteractionClassConfigType) {
                log.info("conversion found");
                InteractionClassConfigType gld = (InteractionClassConfigType) entry.getValue();
                log.info(gld.isSetUpdatePeriod());
                log.info(gld.getUpdatePeriod());
                PublishedObjectsType objects = gld.getPublishedObjects();
                if (objects == null) {
                    continue;
                }
                for (String name : objects.getObjectName()) {
                    log.info(name);
                }
            }
        }
        interaction = gateway.getObjectModel().getInteraction("InteractionRoot.C2WInteractionRoot.Heartbeat");
        log.info(interaction.getName().getValue());
        ParameterType parameter = gateway.getObjectModel().getParameter(interaction, "id");
        log.info(parameter.getName().getValue());
        log.info(parameter.toString());
        for (FeatureMap.Entry entry : parameter.getAny()) {
            log.info(entry.getValue().toString());
            if (entry.getValue() instanceof ParameterConfigType) {
                ParameterConfigType gld = (ParameterConfigType) entry.getValue();
                UnitConversionType unit = gld.getUnitConversion();
                if (unit == null) {
                    continue;
                }
                String name = unit.getNameConversion();
                if (name != null) {
                    log.info("name conversion");
                    log.info(name);
                }
                LinearConversionType linear = unit.getLinearConversion();
                if (linear != null) {
                    log.info("linear conversion");
                    log.info(linear.getScale());
                    log.info(linear.getOffset());
                }
            }
        }
    }
    
    public void initializeSelf() {
        // TODO Auto-generated method stub
        
    }

    public void initializeWithPeers() {
        // TODO Auto-generated method stub
        
    }

    public void receiveInteraction(Double timeStep, String className, Map<String, String> parameters) {
        // TODO Auto-generated method stub
        
    }

    public void receiveObject(Double timeStep, String className, String instanceName, Map<String, String> attributes) {
        // TODO Auto-generated method stub
        
    }

    public void doTimeStep(Double timeStep) {
        // TODO Auto-generated method stub
        
    }

    public void terminate() {
        // TODO Auto-generated method stub
        
    }
}

package gov.nist.hla.gridlabd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.ieee.standards.ieee1516._2010.AttributeType;
import org.ieee.standards.ieee1516._2010.InteractionClassType;
import org.ieee.standards.ieee1516._2010.ObjectClassType;
import org.ieee.standards.ieee1516._2010.ParameterType;
import org.xml.sax.SAXException;

import gov.nist.hla.gateway.ObjectModel;
import gov.nist.hla.gridlabd.exception.SchemaValidationException;
import gov.nist.pages.ucef.AttributeDetailsType;
import gov.nist.pages.ucef.InteractionDetailsType;
import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ObjectDetailsType;
import gov.nist.pages.ucef.ParameterDetailsType;
import gov.nist.pages.ucef.ucefPackage;
import gov.nist.sds4emf.Deserialize;

public class ExtendedObjectModel extends ObjectModel {
    public static final String SIMULATION_TIME = ObjectModel.INTERACTION_CPSWT + ".SimulationControl.SimTime";
    
    private static final Logger log = LogManager.getLogger();
    
    private static boolean packageRegistered = false;
    
    private Set<String> baseParameters = new HashSet<String>();
    
    public ExtendedObjectModel(String filePath)
            throws SchemaValidationException {
        super(filePath);
        validateXmlFile(filePath);
        
        // base parameters are not sent to the GridLAB-D simulation
        InteractionClassType baseInteraction = getInteraction(ObjectModel.INTERACTION_CPSWT);
        for (ParameterType parameter : getParameters(baseInteraction)) {
            baseParameters.add(parameter.getName().getValue());
        }
        log.debug("baseParameters {}", baseParameters.toString());
    }
    
    public boolean isRelevantParameter(ParameterType parameter) {
        log.trace("isRelevantParameter {}", parameter.getName().getValue());
        
        // a parameter is irrelevant if it was defined in the base class or specified as ignored
        if (baseParameters.contains(parameter.getName().getValue())) {
            return false;
        }
        
        ParameterDetailsType details = getParameterDetails(parameter);
        return !(details != null && details.isSetIgnored() && details.isIgnored());
    }
    
    public boolean isRelevantAttribute(AttributeType attribute) {
        log.trace("isRelevantAttribute {}", attribute.getName().getValue());
        
        // an attribute is irrelevant if it was specified as ignored
        AttributeDetailsType details = getAttributeDetails(attribute);
        return !(details != null && details.isSetIgnored() && details.isIgnored());
    }
    
    public boolean isSubscribed(String classPath) {
        log.trace("isSubscribed {}", classPath);
        
        if (classPath.startsWith("InteractionRoot")) {
            log.trace("interaction class");
            InteractionClassType interaction = getInteraction(classPath);
            return getSubscribedInteractions().contains(interaction);
        }
        if (classPath.startsWith("ObjectRoot")) {
            log.trace("object class");
            ObjectClassType object = getObject(classPath);
            return getSubscribedObjects().contains(object);
        }
        log.warn("bad class path {}", classPath);
        return false;
    }
    
    public boolean isPublished(String classPath) {
        log.trace("isPublished {}", classPath);
        
        if (classPath.startsWith("InteractionRoot")) {
            log.trace("interaction class");
            InteractionClassType interaction = getInteraction(classPath);
            return getPublishedInteractions().contains(interaction);
        }
        if (classPath.startsWith("ObjectRoot")) {
            log.trace("object class");
            ObjectClassType object = getObject(classPath);
            return getPublishedObjects().contains(object);
        }
        log.warn("bad class path {}", classPath);
        return false;
    }
    
    public Set<String> getPublishedNames(InteractionClassType interaction) {
        log.trace("getPublishedNames {}", getClassPath(interaction));
        
        Set<String> publishedNames = new HashSet<String>();
        InteractionDetailsType details = getInteractionDetails(interaction);
        
        if (details != null) {
            if (details.getPublishedObjects() == null) {
                log.debug("no GridLAB-D objects defined for {}", getClassPath(interaction));
            } else {
                publishedNames.addAll(details.getPublishedObjects().getObjectName());
            }
            return publishedNames;
        }
        
        log.debug("no interaction details for {}", getClassPath(interaction));
        return publishedNames;
    }
    
    public Set<String> getPublishedNames(ObjectClassType object) {
        log.trace("getPublishedNames {}", getClassPath(object));
        
        Set<String> publishedNames = new HashSet<String>();
        ObjectDetailsType details = getObjectDetails(object);
        
        if (details != null) {
            if (details.getPublishedObjects() == null) {
                log.debug("no GridLAB-D objects defined for {}", getClassPath(object));
            } else {
                publishedNames.addAll(details.getPublishedObjects().getObjectName());
            }
            return publishedNames;
        }
    
        log.debug("no object details for {}", getClassPath(object));
        return publishedNames;
    }
    
    public String getUnitName(AttributeType attribute) {
        log.trace("getUnitName {}", attribute.getName().getValue());
        
        if (!isDouble(attribute)) {
            log.debug("skipped unit conversion: unsupported data type");
            return null;
        }
        
        AttributeDetailsType details = getAttributeDetails(attribute);
        if (details == null || details.getUnitConversion() == null) {
            log.debug("skipped unit conversion: no conversion rule");
            return null;
        }
        
        // this will return null if a different conversion rule was specified
        return details.getUnitConversion().getUnitName();
    }
    
    public String getUnitName(ParameterType parameter) {
        log.trace("getUnitName {}", parameter.getName().getValue());
        
        if (!isDouble(parameter)) {
            log.debug("skipped unit conversion: unsupported data type");
            return null;
        }
        
        ParameterDetailsType details = getParameterDetails(parameter);
        if (details == null || details.getUnitConversion() == null) {
            log.debug("skipped unit conversion: no conversion rule");
            return null;
        }
        
        // this will return null if a different conversion rule was specified
        return details.getUnitConversion().getUnitName();
    }
    
    public LinearConversionType getLinearConversion(AttributeType attribute) {
        log.trace("getLinearConversion {}", attribute.getName().getValue());
        
        if (!isDouble(attribute)) {
            log.debug("skipped unit conversion: unsupported data type");
            return null;
        }
        
        AttributeDetailsType details = getAttributeDetails(attribute);
        if (details == null || details.getUnitConversion() == null) {
            log.debug("skipped unit conversion: no conversion rule");
            return null;
        }
        
        // this will return null if a different conversion rule was specified
        return details.getUnitConversion().getLinearConversion();
    }
    
    public LinearConversionType getLinearConversion(ParameterType parameter) {
        log.trace("getLinearConversion {}", parameter.getName().getValue());
        
        if (!isDouble(parameter)) {
            log.debug("skipped unit conversion: unsupported data type");
            return null;
        }
        
        ParameterDetailsType details = getParameterDetails(parameter);
        if (details == null || details.getUnitConversion() == null) {
            log.debug("skipped unit conversion: no conversion rule");
            return null;
        }
        
        // this will return null if a different conversion rule was specified
        return details.getUnitConversion().getLinearConversion();
    }
    
    public double getUpdatePeriod(AttributeType attribute) {
        log.trace("getUpdatePeriod {}", attribute.getName().getValue());
        
        AttributeDetailsType details = getAttributeDetails(attribute);
        if (details == null || !details.isSetUpdatePeriod()) {
            return -1; // no update period
        }
        return details.getUpdatePeriod();
    }
    
    public double getUpdatePeriod(InteractionClassType interaction) {
        log.trace("getUpdatePeriod {}", getClassPath(interaction));
        
        InteractionDetailsType details = getInteractionDetails(interaction);
        if (details == null || !details.isSetUpdatePeriod()) {
            return -1; // no update period
        }
        return details.getUpdatePeriod();
    }
    
    public String getPropertyName(AttributeType attribute) {
        log.trace("getPropertyName {}", attribute.getName().getValue());
        
        AttributeDetailsType details = getAttributeDetails(attribute);
        if (details == null || details.getPropertyName() == null) {
            return attribute.getName().getValue();
        }
        return details.getPropertyName();
    }
    
    public String getPropertyName(ParameterType parameter) {
        log.trace("getPropertyName {}", parameter.getName().getValue());
        
        ParameterDetailsType details = getParameterDetails(parameter);
        if (details == null || details.getPropertyName() == null) {
            return parameter.getName().getValue();
        }
        return details.getPropertyName();
    }
    
    public boolean isDouble(ParameterType parameter) {
        log.trace("isDouble {}", parameter.getName().getValue());
        final String dataType = parameter.getDataType().getValue();
        return dataType.equals("double"); // float not supported in GridLAB-D
    }
    
    public boolean isDouble(AttributeType attribute) {
        log.trace("isDouble {}", attribute.getName().getValue());
        final String dataType = attribute.getDataType().getValue();
        return dataType.equals("double"); // float not supported in GridLAB-D
    }
    
    @Override
    protected void registerPackage() {
        if (!packageRegistered) {
            super.registerPackage();
            Deserialize.registerPackage(ucefPackage.eNS_URI, ucefPackage.eINSTANCE);
            packageRegistered = true;
        }
    }
    
    private void validateXmlFile(String fomFilePath)
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
    
    private ParameterDetailsType getParameterDetails(ParameterType parameter) {
        log.trace("getParameterDetails {}", parameter.getName().getValue());
        
        for (FeatureMap.Entry feature : parameter.getAny()) {
            if (feature.getValue() instanceof ParameterDetailsType) {
                return (ParameterDetailsType) feature.getValue();
            }
        }
        return null;
    }
    
    private AttributeDetailsType getAttributeDetails(AttributeType attribute) {
        log.trace("AttributeDetailsType {}", attribute.getName().getValue());
        
        for (FeatureMap.Entry feature : attribute.getAny()) {
            if (feature.getValue() instanceof AttributeDetailsType) {
                return (AttributeDetailsType) feature.getValue();
            }
        }
        return null;
    }
    
    private InteractionDetailsType getInteractionDetails(InteractionClassType interaction) {
        log.trace("InteractionDetailsType {}", getClassPath(interaction));
        
        for (FeatureMap.Entry feature : interaction.getAny()) {
            if (feature.getValue() instanceof InteractionDetailsType) {
                return (InteractionDetailsType) feature.getValue();
            }
        }
        return null;
    }
    
    private ObjectDetailsType getObjectDetails(ObjectClassType object) {
        log.trace("ObjectDetailsType {}", getClassPath(object));
        
        for (FeatureMap.Entry feature : object.getAny()) {
            if (feature.getValue() instanceof ObjectDetailsType) {
                return (ObjectDetailsType) feature.getValue();
            }
        }
        return null;
    }
}

package gov.nist.hla.gridlabd;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.ieee.standards.ieee1516._2010.AttributeType;
import org.ieee.standards.ieee1516._2010.InteractionClassType;
import org.ieee.standards.ieee1516._2010.ObjectClassType;
import org.ieee.standards.ieee1516._2010.ParameterType;

import gov.nist.hla.gateway.ObjectModel;
import gov.nist.pages.ucef.AttributeDetailsType;
import gov.nist.pages.ucef.InteractionDetailsType;
import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ObjectDetailsType;
import gov.nist.pages.ucef.ParameterDetailsType;

public class ObjectModelHelper {
    private static final Logger log = LogManager.getLogger();
    
    private final ObjectModel objectModel;
    
    private Set<String> baseAttributes = new HashSet<String>();
    
    private Set<String> baseParameters = new HashSet<String>();
    
    public ObjectModelHelper(ObjectModel objectModel) {
        this.objectModel = objectModel;
        
        // base attributes are not sent to the GridLAB-D simulation
        ObjectClassType baseObject = objectModel.getObject("ObjectRoot");
        for (AttributeType attribute : objectModel.getAttributes(baseObject)) {
            baseAttributes.add(attribute.getName().getValue());
        }
        log.debug("baseAttributes {}", baseAttributes.toString());
        
        // base parameters are not sent to the GridLAB-D simulation
        InteractionClassType baseInteraction = objectModel.getInteraction("InteractionRoot.C2WInteractionRoot");
        for (ParameterType parameter : objectModel.getParameters(baseInteraction)) {
            baseParameters.add(parameter.getName().getValue());
        }
        log.debug("baseParameters {}", baseParameters.toString());
    }
    
    public boolean isGlobalVariable(ObjectClassType object) {
        log.trace("isGlobalVariable {}", objectModel.getClassPath(object));
        
        // an HLA object maps to a GridLAB-D global variable if it satisfies any of the following conditions:
        //  1. it does not define a name attribute to define the corresponding GridLAB-D object name
        //  2. it defines a name attribute with an ignored annotation set to either true or 1
        //  3. it defines a name attribute annotated with a substitute name (that could be name)
        // #3 does not make much sense to do, but is included just in case someone gets wild
        AttributeType name = objectModel.getAttribute(object, "name");
        if (name == null) {
            return true; // case 1
        }
        
        AttributeDetailsType details = getAttributeDetails(name);
        if (details != null && details.isSetIgnored() && details.isIgnored()) {
            return true; // case 2
        }
        if (details != null && details.getPropertyName() != null) {
            return true; // case 3
        }
        return false;
    }
    
    public boolean isGlobalVariable(InteractionClassType interaction) {
        log.trace("isGlobalVariable {}", objectModel.getClassPath(interaction));
        
        // an HLA interaction maps to a GridLAB-D global variable if it satisfies any of the following conditions:
        //  1. it does not define a name parameter to define the corresponding GridLAB-D object name
        //  2. it defines a name parameter with an ignored annotation set to either true or 1
        //  3. it defines a name parameter annotated with a substitute name (that could be name)
        // #3 does not make much sense to do, but is included just in case someone gets wild
        ParameterType name = objectModel.getParameter(interaction, "name");
        if (name == null) {
            return true; // case 1
        }
        
        ParameterDetailsType details = getParameterDetails(name);
        if (details != null && details.isSetIgnored() && details.isIgnored()) {
            return true; // case 2
        }
        if (details != null && details.getPropertyName() != null) {
            return true; // case 3
        }
        return false;
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
        
        // an attribute is irrelevant if it was defined in the base class or specified as ignored
        if (baseAttributes.contains(attribute.getName().getValue())) {
            return false;
        }
        
        AttributeDetailsType details = getAttributeDetails(attribute);
        return !(details != null && details.isSetIgnored() && details.isIgnored());
    }
    
    public boolean isSubscribed(String classPath) {
        log.trace("isSubscribed {}", classPath);
        
        if (classPath.startsWith("InteractionRoot")) {
            log.trace("interaction class");
            InteractionClassType interaction = objectModel.getInteraction(classPath);
            return objectModel.getSubscribedInteractions().contains(interaction);
        }
        if (classPath.startsWith("ObjectRoot")) {
            log.trace("object class");
            ObjectClassType object = objectModel.getObject(classPath);
            return objectModel.getSubscribedObjects().contains(object);
        }
        log.warn("bad class path {}", classPath);
        return false;
    }
    
    public boolean isPublished(String classPath) {
        log.trace("isPublished {}", classPath);
        
        if (classPath.startsWith("InteractionRoot")) {
            log.trace("interaction class");
            InteractionClassType interaction = objectModel.getInteraction(classPath);
            return objectModel.getPublishedInteractions().contains(interaction);
        }
        if (classPath.startsWith("ObjectRoot")) {
            log.trace("object class");
            ObjectClassType object = objectModel.getObject(classPath);
            return objectModel.getPublishedObjects().contains(object);
        }
        log.warn("bad class path {}", classPath);
        return false;
    }
    
    public Set<String> getPublishedNames(InteractionClassType interaction) {
        log.trace("getPublishedNames {}", objectModel.getClassPath(interaction));
        
        Set<String> publishedNames = new HashSet<String>();
        InteractionDetailsType details = getInteractionDetails(interaction);
        
        if (details != null) {
            if (details.getPublishedObjects() == null) {
                log.debug("no GridLAB-D objects defined for {}", objectModel.getClassPath(interaction));
            } else {
                publishedNames.addAll(details.getPublishedObjects().getObjectName());
            }
            return publishedNames;
        }
        
        log.debug("no interaction details for {}", objectModel.getClassPath(interaction));
        return publishedNames;
    }
    
    public Set<String> getPublishedNames(ObjectClassType object) {
        log.trace("getPublishedNames {}", objectModel.getClassPath(object));
        
        Set<String> publishedNames = new HashSet<String>();
        ObjectDetailsType details = getObjectDetails(object);
        
        if (details != null) {
            if (details.getPublishedObjects() == null) {
                log.debug("no GridLAB-D objects defined for {}", objectModel.getClassPath(object));
            } else {
                publishedNames.addAll(details.getPublishedObjects().getObjectName());
            }
            return publishedNames;
        }
    
        log.debug("no object details for {}", objectModel.getClassPath(object));
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
        log.trace("getUpdatePeriod {}", objectModel.getClassPath(interaction));
        
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
        log.trace("InteractionDetailsType {}", objectModel.getClassPath(interaction));
        
        for (FeatureMap.Entry feature : interaction.getAny()) {
            if (feature.getValue() instanceof InteractionDetailsType) {
                return (InteractionDetailsType) feature.getValue();
            }
        }
        return null;
    }
    
    private ObjectDetailsType getObjectDetails(ObjectClassType object) {
        log.trace("ObjectDetailsType {}", objectModel.getClassPath(object));
        
        for (FeatureMap.Entry feature : object.getAny()) {
            if (feature.getValue() instanceof ObjectDetailsType) {
                return (ObjectDetailsType) feature.getValue();
            }
        }
        return null;
    }
}

package gov.nist.hla.gridlabd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import gov.nist.pages.ucef.IgnoredType;
import gov.nist.pages.ucef.InteractionDetailsType;
import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ObjectDetailsType;
import gov.nist.pages.ucef.ParameterDetailsType;
import gov.nist.pages.ucef.ucefPackage;
import gov.nist.sds4emf.Deserialize;

/**
 * This class defines a set of helper functions that act on an Eclipse Modeling Framework (EMF) representation of a
 * Federation Object Model (FOM). The GridLAB-D federate uses an extended schema that allows the FOM to be annotated
 * with additional configuration options. The public methods of this class define the set of queries that can be made
 * over this extended schema.
 *
 * @author Thomas Roth
 */
public class ExtendedObjectModel extends ObjectModel {
    /**
     * The string identifier of the interaction used to publish the GridLAB-D clock.
     */
    public static final String GLD_CLOCK = ObjectModel.INTERACTION_CPSWT + ".GLDClock";

    /**
     * The string identifier of the interaction used to initialize the GridLAB-D clock.
     */
    public static final String SIM_TIME = ObjectModel.INTERACTION_CPSWT + ".SimulationControl.SimTime";

    // All interactions sent and received by the GridLAB-D federate must derive from the base interaction.
    private static final String BASE_INTERACTION = ObjectModel.INTERACTION_CPSWT;

    // All object instances registered and discovered by the GridLAB-D federate must derive from the base object.
    private static final String BASE_OBJECT = ObjectModel.OBJECT_ROOT;

    // The parameters of the base interaction class.
    private Set<String> baseParameters = new HashSet<String>();

    // The attributes of the base object class.
    private Set<String> baseAttributes = new HashSet<String>();

    // Track which interaction and object class paths correspond to objects in the GridLAB-D simulation.
    private Map<String, Boolean> isGldObject = new HashMap<String, Boolean>();

    private static final Logger log = LogManager.getLogger();

    private static boolean packageRegistered = false;

    /**
     * Constructs a new document tree that mirrors the structure of the passed FOM file.
     *
     * @param filePath Path to the Federation Object Model (FOM) XML file that represents the object model.
     * @throws SchemaValidationException if the XML file does not validate against the extended GridLAB-D schema
     */
    public ExtendedObjectModel(String filePath)
            throws SchemaValidationException {
        super(filePath);
        validateXmlFile(filePath);
        initializeBaseParameters();
        initializeBaseAttributes();
    }

    private void validateXmlFile(String filePath)
            throws SchemaValidationException {
        log.info("validating FOM {}", filePath);

        Source fomFile = new StreamSource(new File(filePath));
        InputStream hlaSchema = this.getClass().getClassLoader().getResourceAsStream("IEEE1516-DIF-2010.xsd");
        InputStream ucefSchema = this.getClass().getClassLoader().getResourceAsStream("ucef.xsd");

        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new Source[] {
                        new StreamSource(hlaSchema),
                        new StreamSource(ucefSchema)});
            Validator validator = schema.newValidator();
            validator.validate(fomFile);
            log.info("Validated.");
        } catch (IOException | SAXException e) {
            throw new SchemaValidationException(e);
        }
    }

    private void initializeBaseParameters() {
        // base parameters are not sent to the GridLAB-D simulation
        InteractionClassType baseInteraction = getInteraction(BASE_INTERACTION);
        if (baseInteraction == null) {
            log.warn("no valid interaction classes in the object model");
        } else {
            for (ParameterType parameter : getParameters(baseInteraction)) {
                baseParameters.add(parameter.getName().getValue());
            }
            log.debug("baseParameters {}", baseParameters.toString());
        }
    }

    private void initializeBaseAttributes() {
        // base attributes are not sent to the GridLAB-D simulation
        ObjectClassType baseObject = getObject(BASE_OBJECT);
        if (baseObject == null) {
            log.warn("no valid object classes in the object model");
        } else {
            for (AttributeType attribute : getAttributes(baseObject)) {
                baseAttributes.add(attribute.getName().getValue());
            }
            log.debug("baseAttributes {}", baseAttributes.toString());
        }
    }

    @Override
    protected void registerPackage() {
        if (!packageRegistered) {
            super.registerPackage();
            Deserialize.registerPackage(ucefPackage.eNS_URI, ucefPackage.eINSTANCE);
            packageRegistered = true;
        }
    }

    @Override
    public boolean isCoreInteraction(InteractionClassType interaction) {
        switch (getClassPath(interaction)) {
            case BASE_INTERACTION:
            case GLD_CLOCK:
            case SIM_TIME:
                return true;
            default:
                return super.isCoreInteraction(interaction);
        }
    }

    @Override
    public boolean isCoreObject(ObjectClassType object) {
        switch (getClassPath(object)) {
            case BASE_OBJECT:
                return true;
            default:
                return super.isCoreObject(object);
        }
    }

    /**
     * Checks whether an interaction class maps to a GridLAB-D simulation object.
     *
     * @param interaction The interaction class of interest.
     * @return True if the interaction class should be treated as a GridLAB-D simulation object.
     */
    public boolean isGldObject(InteractionClassType interaction) {
        final String classPath = getClassPath(interaction);

        if (isGldObject.containsKey(classPath)) {
            return isGldObject.get(classPath);
        }

        boolean result = classPath.startsWith(BASE_INTERACTION) // derived from the base interaction
                && !containsIgnored(interaction.getAny())       // not specified as ignore in the XML file
                && definesGldName(interaction);                 // has the name property required by GridLAB-D

        log.debug("{} isGldObject={}", classPath, result);
        isGldObject.put(classPath, result);
        return result;
    }

    private boolean definesGldName(InteractionClassType interaction) {
        return getParameter(interaction, "name") != null;
    }

    /**
     * Checks whether an object class maps to a GridLAB-D simulation object.
     *
     * @param object The object class of interest.
     * @return True if the object class should be treated as a GridLAB-D simulation object.
     */
    public boolean isGldObject(ObjectClassType object) {
        final String classPath = getClassPath(object);

        if (isGldObject.containsKey(classPath)) {
            return isGldObject.get(classPath);
        }

        boolean result = classPath.startsWith(BASE_OBJECT)      // derived from the base object
                && !containsIgnored(object.getAny())            // not specified as ignore in the XML file
                && definesGldName(object);                      // has the name property required by GridLAB-D

        log.debug("{} isGldObject={}", classPath, result);
        isGldObject.put(classPath, result);
        return result;
    }

    private boolean definesGldName(ObjectClassType object) {
        return getAttribute(object, "name") != null;
    }

    /**
     * Checks whether an interaction parameter maps to a property of a GridLAB-D simulation object. Note that although
     * GridLAB-D objects have a name property, the equivalent name parameter will return false when passed to this
     * method.
     *
     * @param parameter The parameter of interest.
     * @return True if the parameter should be treated as a GridLAB-D object property.
     */
    public boolean isGldProperty(ParameterType parameter) {
        final String parameterName = parameter.getName().getValue();

        return !baseParameters.contains(parameterName)          // not defined in the base interaction
                && !containsIgnored(parameter.getAny())         // not specified as ignored in the XML file
                && !parameterName.equals("name");               // not the name property used as the unique ID
    }

    /**
     * Checks whether an object attribute maps to a property of a GridLAB-D simulation object. Note that although
     * GridLAB-D objects have a name property, the equivalent name attribute will return false when passed to this
     * method.
     *
     * @param attribute The attribute of interest.
     * @return True if the attribute should be treated as a GridLAB-D object property.
     */
    public boolean isGldProperty(AttributeType attribute) {
        final String attributeName = attribute.getName().getValue();

        return !baseAttributes.contains(attributeName)          // not defined in the base object
                && !containsIgnored(attribute.getAny())         // not specified as ignored in the XML file
                && !attributeName.equals("name");               // not the name property used as the unique ID
    }

    private boolean containsIgnored(FeatureMap features) {
        for (FeatureMap.Entry feature : features) {
            if (feature.getValue() instanceof IgnoredType) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether a fully qualified class path refers to a subscribed interaction or object class.
     *
     * @param classPath A fully qualified class path for an interaction or object.
     * @return True if the object model contains a subscription to the given interaction or object class.
     */
    public boolean isSubscribed(String classPath) {
        return isSubscribedInteraction(classPath) || isSubscribedObject(classPath);
    }

    private boolean isSubscribedInteraction(String classPath) {
        final InteractionClassType interaction = getInteraction(classPath);
        return interaction != null && getSubscribedInteractions().contains(interaction);
    }

    private boolean isSubscribedObject(String classPath) {
        final ObjectClassType object = getObject(classPath);
        return object != null && getSubscribedObjects().contains(object);
    }

    /**
     * Checks whether a fully qualified class path refers to a published interaction or object class.
     *
     * @param classPath A fully qualified class path for an interaction or object.
     * @return True if the object model contains a publication for the given interaction or object class.
     */
    public boolean isPublished(String classPath) {
        return isPublishedInteraction(classPath) || isPublishedObject(classPath);
    }

    private boolean isPublishedInteraction(String classPath) {
        final InteractionClassType interaction = getInteraction(classPath);
        return interaction != null && getPublishedInteractions().contains(interaction);
    }

    private boolean isPublishedObject(String classPath) {
        final ObjectClassType object = getObject(classPath);
        return object != null && getPublishedObjects().contains(object);
    }

    /**
     * Get the list of GridLAB-D object names that should be published as the given interaction class.
     *
     * @param interaction The published interaction class of interest.
     * @return The set of GridLAB-D object names associated with the interaction class in the FOM.
     */
    public Set<String> getPublishedNames(InteractionClassType interaction) {
        Set<String> publishedNames = new HashSet<String>();
        InteractionDetailsType details = getInteractionDetails(interaction);

        if (details != null && details.getPublishedObjects() != null) {
            publishedNames.addAll(details.getPublishedObjects().getObjectName());
        } else {
            log.debug("no GridLAB-D objects defined for {}", getClassPath(interaction));
        }

        return publishedNames;
    }

    /**
     * Get the list of GridLAB-D object names that should be published as the given object class.
     *
     * @param object The published object class of interest.
     * @return The set of GridLAB-D object names associated with the object class in the FOM.
     */
    public Set<String> getPublishedNames(ObjectClassType object) {
        Set<String> publishedNames = new HashSet<String>();
        ObjectDetailsType details = getObjectDetails(object);

        if (details != null && details.getPublishedObjects() != null) {
            publishedNames.addAll(details.getPublishedObjects().getObjectName());
        } else {
            log.debug("no GridLAB-D objects defined for {}", getClassPath(object));
        }

        return publishedNames;
    }

    /**
     * Check whether a parameter's data type is equivalent to a GridLAB-D double.
     *
     * @param parameter The parameter of interest.
     * @return True if the parameter has the data type of double.
     */
    public boolean isDouble(ParameterType parameter) {
        final String dataType = parameter.getDataType().getValue();
        return dataType.equals("double"); // float not supported in GridLAB-D
    }

    /**
     * Checks whether an attribute's data type is equivalent to a GridLAB-D double
     *
     * @param attribute The attribute of interest.
     * @return True if the attribute has the data type of double.
     */
    public boolean isDouble(AttributeType attribute) {
        final String dataType = attribute.getDataType().getValue();
        return dataType.equals("double"); // float not supported in GridLAB-D
    }

    /**
     * Get the GridLAB-D unit associated with the given parameter, if a unit has been defined.
     *
     * @param parameter The parameter of interest.
     * @return The unit name associated with the parameter, or null if no unit is specified.
     */
    public String getUnitName(ParameterType parameter) {
        if (!isDouble(parameter)) {
            log.debug("skipped unit conversion for {}: unsupported data type", parameter.getName().getValue());
            return null;
        }

        ParameterDetailsType details = getParameterDetails(parameter);
        if (details == null || details.getUnitConversion() == null) {
            log.debug("skipped unit conversion for {}: no conversion rule", parameter.getName().getValue());
            return null;
        }
        return details.getUnitConversion().getUnitName();
    }

    /**
     * Gets the GridLAB-D unit associated with the given attribute, if a unit has been defined.
     *
     * @param attribute The attribute of interest.
     * @return The unit name associated with the attribute, or null if no unit is specified.
     */
    public String getUnitName(AttributeType attribute) {
        if (!isDouble(attribute)) {
            log.debug("skipped unit conversion for {}: unsupported data type", attribute.getName().getValue());
            return null;
        }

        AttributeDetailsType details = getAttributeDetails(attribute);
        if (details == null || details.getUnitConversion() == null) {
            log.debug("skipped unit conversion for {}: no conversion rule", attribute.getName().getValue());
            return null;
        }
        return details.getUnitConversion().getUnitName();
    }

    /**
     * Gets the linear conversion associated with the given parameter, if a linear conversion has been defined. All the
     * linear conversions are of the form (hla = scale * gld + offset) where the value of the parameter in GridLAB-D is
     * considered the independent variable.
     *
     * @param parameter The parameter of interest.
     * @return The linear conversion to apply to the parameter's value, or null if no conversion is specified.
     */
    public LinearConversionType getLinearConversion(ParameterType parameter) {
        if (!isDouble(parameter)) {
            log.debug("skipped unit conversion for {}: unsupported data type", parameter.getName().getValue());
            return null;
        }

        ParameterDetailsType details = getParameterDetails(parameter);
        if (details == null || details.getUnitConversion() == null) {
            log.debug("skipped unit conversion for {}: no conversion rule", parameter.getName().getValue());
            return null;
        }
        return details.getUnitConversion().getLinearConversion();
    }

    /**
     * Gets the linear conversion associated with the given attribute, if a linear conversion has been defined. All the
     * linear conversions are of the form (hla = scale * gld + offset) where the value of the attribute in GridLAB-D is
     * considered the independent variable.
     *
     * @param attribute The attribute of interest.
     * @return The linear conversion to apply to the attribute's value, or null if no conversion is specified.
     */
    public LinearConversionType getLinearConversion(AttributeType attribute) {
        if (!isDouble(attribute)) {
            log.debug("skipped unit conversion for {}: unsupported data type", attribute.getName().getValue());
            return null;
        }

        AttributeDetailsType details = getAttributeDetails(attribute);
        if (details == null || details.getUnitConversion() == null) {
            log.debug("skipped unit conversion for {}: no conversion rule", attribute.getName().getValue());
            return null;
        }
        return details.getUnitConversion().getLinearConversion();
    }

    /**
     * Gets the update period for how often new values for the given interaction should be published to the federation.
     * Because HLA defines publications for interaction classes rather than the individual parameters, this method is
     * asymmetric with the object attribute equivalent.
     *
     * @param interaction The interaction of interest.
     * @return The amount of logical time between consecutive updates, or -1 to update as frequently as possible.
     */
    public double getUpdatePeriod(InteractionClassType interaction) {
        InteractionDetailsType details = getInteractionDetails(interaction);
        if (details == null || !details.isSetUpdatePeriod()) {
            return -1; // no update period
        }
        return details.getUpdatePeriod();
    }

    /**
     * Gets the update period for how often new values for the given attribute should be published to the federation.
     * Because HLA defines publications for object attributes rather than object classes, this method is asymmetric
     * with the interaction equivalent.
     *
     * @param attribute The attribute of interest.
     * @return The amount of logical time between consecutive updates, or -1 to update as frequently as possible.
     */
    public double getUpdatePeriod(AttributeType attribute) {
        AttributeDetailsType details = getAttributeDetails(attribute);
        if (details == null || !details.isSetUpdatePeriod()) {
            return -1; // no update period
        }
        return details.getUpdatePeriod();
    }

    /**
     * Gets the GridLAB-D property name associated with the given parameter.
     *
     * @param parameter The parameter of interest.
     * @return The equivalent name of the parameter in the GridLAB-D simulation.
     */
    public String getPropertyName(ParameterType parameter) {
        ParameterDetailsType details = getParameterDetails(parameter);
        if (details == null || details.getPropertyName() == null) {
            return parameter.getName().getValue(); // no alternative name defined
        }
        return details.getPropertyName();
    }

    /**
     * Gets the GridLAB-D property name associated with the given attribute.
     *
     * @param attribute The attribute of interest.
     * @return The equivalent name of the attribute in the GridLAB-D simulation.
     */
    public String getPropertyName(AttributeType attribute) {
        AttributeDetailsType details = getAttributeDetails(attribute);
        if (details == null || details.getPropertyName() == null) {
            return attribute.getName().getValue(); // no alternative name defined
        }
        return details.getPropertyName();
    }

    private InteractionDetailsType getInteractionDetails(InteractionClassType interaction) {
        for (FeatureMap.Entry feature : interaction.getAny()) {
            if (feature.getValue() instanceof InteractionDetailsType) {
                return (InteractionDetailsType) feature.getValue();
            }
        }
        return null;
    }

    private ParameterDetailsType getParameterDetails(ParameterType parameter) {
        for (FeatureMap.Entry feature : parameter.getAny()) {
            if (feature.getValue() instanceof ParameterDetailsType) {
                return (ParameterDetailsType) feature.getValue();
            }
        }
        return null;
    }

    private ObjectDetailsType getObjectDetails(ObjectClassType object) {
        for (FeatureMap.Entry feature : object.getAny()) {
            if (feature.getValue() instanceof ObjectDetailsType) {
                return (ObjectDetailsType) feature.getValue();
            }
        }
        return null;
    }

    private AttributeDetailsType getAttributeDetails(AttributeType attribute) {
        for (FeatureMap.Entry feature : attribute.getAny()) {
            if (feature.getValue() instanceof AttributeDetailsType) {
                return (AttributeDetailsType) feature.getValue();
            }
        }
        return null;
    }
}

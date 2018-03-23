/**
 */
package gov.nist.pages.ucef.util;

import gov.nist.pages.ucef.*;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see gov.nist.pages.ucef.ucefPackage
 * @generated
 */
public class ucefValidator extends EObjectValidator {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final ucefValidator INSTANCE = new ucefValidator();

    /**
     * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.common.util.Diagnostic#getSource()
     * @see org.eclipse.emf.common.util.Diagnostic#getCode()
     * @generated
     */
    public static final String DIAGNOSTIC_SOURCE = "gov.nist.pages.ucef";

    /**
     * A constant with a fixed name that can be used as the base value for additional hand written constants.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

    /**
     * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

    /**
     * The cached base package validator.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeValidator xmlTypeValidator;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ucefValidator() {
        super();
        xmlTypeValidator = XMLTypeValidator.INSTANCE;
    }

    /**
     * Returns the package of this validator switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EPackage getEPackage() {
      return ucefPackage.eINSTANCE;
    }

    /**
     * Calls <code>validateXXX</code> for the corresponding classifier of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
        switch (classifierID) {
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE:
                return validateAttributeDetailsType((AttributeDetailsType)value, diagnostics, context);
            case ucefPackage.DOCUMENT_ROOT:
                return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
            case ucefPackage.INTERACTION_DETAILS_TYPE:
                return validateInteractionDetailsType((InteractionDetailsType)value, diagnostics, context);
            case ucefPackage.LINEAR_CONVERSION_TYPE:
                return validateLinearConversionType((LinearConversionType)value, diagnostics, context);
            case ucefPackage.OBJECT_DETAILS_TYPE:
                return validateObjectDetailsType((ObjectDetailsType)value, diagnostics, context);
            case ucefPackage.PARAMETER_DETAILS_TYPE:
                return validateParameterDetailsType((ParameterDetailsType)value, diagnostics, context);
            case ucefPackage.PUBLISHED_OBJECTS_TYPE:
                return validatePublishedObjectsType((PublishedObjectsType)value, diagnostics, context);
            case ucefPackage.UNIT_CONVERSION_TYPE:
                return validateUnitConversionType((UnitConversionType)value, diagnostics, context);
            case ucefPackage.PROPERTY_NAME_TYPE:
                return validatePropertyNameType((String)value, diagnostics, context);
            case ucefPackage.UNIT_NAME_TYPE:
                return validateUnitNameType((String)value, diagnostics, context);
            case ucefPackage.UPDATE_PERIOD_TYPE:
                return validateUpdatePeriodType((Double)value, diagnostics, context);
            case ucefPackage.UPDATE_PERIOD_TYPE_OBJECT:
                return validateUpdatePeriodTypeObject((Double)value, diagnostics, context);
            default:
                return true;
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAttributeDetailsType(AttributeDetailsType attributeDetailsType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(attributeDetailsType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateInteractionDetailsType(InteractionDetailsType interactionDetailsType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(interactionDetailsType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLinearConversionType(LinearConversionType linearConversionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(linearConversionType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateObjectDetailsType(ObjectDetailsType objectDetailsType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(objectDetailsType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateParameterDetailsType(ParameterDetailsType parameterDetailsType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(parameterDetailsType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validatePublishedObjectsType(PublishedObjectsType publishedObjectsType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(publishedObjectsType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateUnitConversionType(UnitConversionType unitConversionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(unitConversionType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validatePropertyNameType(String propertyNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = validatePropertyNameType_Pattern(propertyNameType, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see #validatePropertyNameType_Pattern
     */
    public static final  PatternMatcher [][] PROPERTY_NAME_TYPE__PATTERN__VALUES =
        new PatternMatcher [][] {
            new PatternMatcher [] {
                XMLTypeUtil.createPatternMatcher("[a-zA-Z0-9_.]+")
            }
        };

    /**
     * Validates the Pattern constraint of '<em>Property Name Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validatePropertyNameType_Pattern(String propertyNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validatePattern(ucefPackage.Literals.PROPERTY_NAME_TYPE, propertyNameType, PROPERTY_NAME_TYPE__PATTERN__VALUES, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateUnitNameType(String unitNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = validateUnitNameType_MinLength(unitNameType, diagnostics, context);
        return result;
    }

    /**
     * Validates the MinLength constraint of '<em>Unit Name Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateUnitNameType_MinLength(String unitNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        int length = unitNameType.length();
        boolean result = length >= 1;
        if (!result && diagnostics != null)
            reportMinLengthViolation(ucefPackage.Literals.UNIT_NAME_TYPE, unitNameType, length, 1, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateUpdatePeriodType(double updatePeriodType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = validateUpdatePeriodType_Min(updatePeriodType, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see #validateUpdatePeriodType_Min
     */
    public static final double UPDATE_PERIOD_TYPE__MIN__VALUE = 0.0;

    /**
     * Validates the Min constraint of '<em>Update Period Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateUpdatePeriodType_Min(double updatePeriodType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = updatePeriodType > UPDATE_PERIOD_TYPE__MIN__VALUE;
        if (!result && diagnostics != null)
            reportMinViolation(ucefPackage.Literals.UPDATE_PERIOD_TYPE, updatePeriodType, UPDATE_PERIOD_TYPE__MIN__VALUE, false, diagnostics, context);
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateUpdatePeriodTypeObject(Double updatePeriodTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = validateUpdatePeriodType_Min(updatePeriodTypeObject, diagnostics, context);
        return result;
    }

    /**
     * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        // TODO
        // Specialize this to return a resource locator for messages specific to this validator.
        // Ensure that you remove @generated or mark it @generated NOT
        return super.getResourceLocator();
    }

} //ucefValidator

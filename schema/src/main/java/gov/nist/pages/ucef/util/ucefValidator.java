/**
 */
package gov.nist.pages.ucef.util;

import gov.nist.pages.ucef.*;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

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
            case ucefPackage.ATTRIBUTE_CONFIG_TYPE:
                return validateAttributeConfigType((AttributeConfigType)value, diagnostics, context);
            case ucefPackage.DOCUMENT_ROOT:
                return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
            case ucefPackage.INTERACTION_CLASS_CONFIG_TYPE:
                return validateInteractionClassConfigType((InteractionClassConfigType)value, diagnostics, context);
            case ucefPackage.LINEAR_CONVERSION_TYPE:
                return validateLinearConversionType((LinearConversionType)value, diagnostics, context);
            case ucefPackage.OBJECT_CLASS_CONFIG_TYPE:
                return validateObjectClassConfigType((ObjectClassConfigType)value, diagnostics, context);
            case ucefPackage.PARAMETER_CONFIG_TYPE:
                return validateParameterConfigType((ParameterConfigType)value, diagnostics, context);
            case ucefPackage.PUBLISHED_OBJECTS_TYPE:
                return validatePublishedObjectsType((PublishedObjectsType)value, diagnostics, context);
            case ucefPackage.UNIT_CONVERSION_TYPE:
                return validateUnitConversionType((UnitConversionType)value, diagnostics, context);
            case ucefPackage.NAME_CONVERSION_TYPE:
                return validateNameConversionType((String)value, diagnostics, context);
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
    public boolean validateAttributeConfigType(AttributeConfigType attributeConfigType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(attributeConfigType, diagnostics, context);
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
    public boolean validateInteractionClassConfigType(InteractionClassConfigType interactionClassConfigType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(interactionClassConfigType, diagnostics, context);
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
    public boolean validateObjectClassConfigType(ObjectClassConfigType objectClassConfigType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(objectClassConfigType, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateParameterConfigType(ParameterConfigType parameterConfigType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(parameterConfigType, diagnostics, context);
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
    public boolean validateNameConversionType(String nameConversionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        boolean result = validateNameConversionType_MinLength(nameConversionType, diagnostics, context);
        return result;
    }

    /**
     * Validates the MinLength constraint of '<em>Name Conversion Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateNameConversionType_MinLength(String nameConversionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
        int length = nameConversionType.length();
        boolean result = length >= 1;
        if (!result && diagnostics != null)
            reportMinLengthViolation(ucefPackage.Literals.NAME_CONVERSION_TYPE, nameConversionType, length, 1, diagnostics, context);
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

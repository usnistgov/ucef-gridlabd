/**
 */
package gov.nist.pages.ucef.impl;

import gov.nist.pages.ucef.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ucefFactoryImpl extends EFactoryImpl implements ucefFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ucefFactory init() {
        try {
            ucefFactory theucefFactory = (ucefFactory)EPackage.Registry.INSTANCE.getEFactory(ucefPackage.eNS_URI);
            if (theucefFactory != null) {
                return theucefFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ucefFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ucefFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE: return createAttributeDetailsType();
            case ucefPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case ucefPackage.INTERACTION_DETAILS_TYPE: return createInteractionDetailsType();
            case ucefPackage.LINEAR_CONVERSION_TYPE: return createLinearConversionType();
            case ucefPackage.OBJECT_DETAILS_TYPE: return createObjectDetailsType();
            case ucefPackage.PARAMETER_DETAILS_TYPE: return createParameterDetailsType();
            case ucefPackage.PUBLISHED_OBJECTS_TYPE: return createPublishedObjectsType();
            case ucefPackage.UNIT_CONVERSION_TYPE: return createUnitConversionType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case ucefPackage.PROPERTY_NAME_TYPE:
                return createPropertyNameTypeFromString(eDataType, initialValue);
            case ucefPackage.UNIT_NAME_TYPE:
                return createUnitNameTypeFromString(eDataType, initialValue);
            case ucefPackage.UPDATE_PERIOD_TYPE:
                return createUpdatePeriodTypeFromString(eDataType, initialValue);
            case ucefPackage.UPDATE_PERIOD_TYPE_OBJECT:
                return createUpdatePeriodTypeObjectFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case ucefPackage.PROPERTY_NAME_TYPE:
                return convertPropertyNameTypeToString(eDataType, instanceValue);
            case ucefPackage.UNIT_NAME_TYPE:
                return convertUnitNameTypeToString(eDataType, instanceValue);
            case ucefPackage.UPDATE_PERIOD_TYPE:
                return convertUpdatePeriodTypeToString(eDataType, instanceValue);
            case ucefPackage.UPDATE_PERIOD_TYPE_OBJECT:
                return convertUpdatePeriodTypeObjectToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AttributeDetailsType createAttributeDetailsType() {
        AttributeDetailsTypeImpl attributeDetailsType = new AttributeDetailsTypeImpl();
        return attributeDetailsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InteractionDetailsType createInteractionDetailsType() {
        InteractionDetailsTypeImpl interactionDetailsType = new InteractionDetailsTypeImpl();
        return interactionDetailsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinearConversionType createLinearConversionType() {
        LinearConversionTypeImpl linearConversionType = new LinearConversionTypeImpl();
        return linearConversionType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ObjectDetailsType createObjectDetailsType() {
        ObjectDetailsTypeImpl objectDetailsType = new ObjectDetailsTypeImpl();
        return objectDetailsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterDetailsType createParameterDetailsType() {
        ParameterDetailsTypeImpl parameterDetailsType = new ParameterDetailsTypeImpl();
        return parameterDetailsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PublishedObjectsType createPublishedObjectsType() {
        PublishedObjectsTypeImpl publishedObjectsType = new PublishedObjectsTypeImpl();
        return publishedObjectsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UnitConversionType createUnitConversionType() {
        UnitConversionTypeImpl unitConversionType = new UnitConversionTypeImpl();
        return unitConversionType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String createPropertyNameTypeFromString(EDataType eDataType, String initialValue) {
        return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertPropertyNameTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String createUnitNameTypeFromString(EDataType eDataType, String initialValue) {
        return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertUnitNameTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Double createUpdatePeriodTypeFromString(EDataType eDataType, String initialValue) {
        return (Double)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.DOUBLE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertUpdatePeriodTypeToString(EDataType eDataType, Object instanceValue) {
        return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.DOUBLE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Double createUpdatePeriodTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createUpdatePeriodTypeFromString(ucefPackage.Literals.UPDATE_PERIOD_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertUpdatePeriodTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertUpdatePeriodTypeToString(ucefPackage.Literals.UPDATE_PERIOD_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ucefPackage getucefPackage() {
        return (ucefPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ucefPackage getPackage() {
        return ucefPackage.eINSTANCE;
    }

} //ucefFactoryImpl

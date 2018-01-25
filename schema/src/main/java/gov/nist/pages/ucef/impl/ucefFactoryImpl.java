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
            case ucefPackage.ATTRIBUTE_CONFIG_TYPE: return createAttributeConfigType();
            case ucefPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case ucefPackage.INTERACTION_CLASS_CONFIG_TYPE: return createInteractionClassConfigType();
            case ucefPackage.LINEAR_CONVERSION_TYPE: return createLinearConversionType();
            case ucefPackage.OBJECT_CLASS_CONFIG_TYPE: return createObjectClassConfigType();
            case ucefPackage.PARAMETER_CONFIG_TYPE: return createParameterConfigType();
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
            case ucefPackage.NAME_CONVERSION_TYPE:
                return createNameConversionTypeFromString(eDataType, initialValue);
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
            case ucefPackage.NAME_CONVERSION_TYPE:
                return convertNameConversionTypeToString(eDataType, instanceValue);
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
    public AttributeConfigType createAttributeConfigType() {
        AttributeConfigTypeImpl attributeConfigType = new AttributeConfigTypeImpl();
        return attributeConfigType;
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
    public InteractionClassConfigType createInteractionClassConfigType() {
        InteractionClassConfigTypeImpl interactionClassConfigType = new InteractionClassConfigTypeImpl();
        return interactionClassConfigType;
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
    public ObjectClassConfigType createObjectClassConfigType() {
        ObjectClassConfigTypeImpl objectClassConfigType = new ObjectClassConfigTypeImpl();
        return objectClassConfigType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterConfigType createParameterConfigType() {
        ParameterConfigTypeImpl parameterConfigType = new ParameterConfigTypeImpl();
        return parameterConfigType;
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
    public String createNameConversionTypeFromString(EDataType eDataType, String initialValue) {
        return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertNameConversionTypeToString(EDataType eDataType, Object instanceValue) {
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

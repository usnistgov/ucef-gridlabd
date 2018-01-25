/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see gov.nist.pages.ucef.ucefPackage
 * @generated
 */
public interface ucefFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ucefFactory eINSTANCE = gov.nist.pages.ucef.impl.ucefFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Attribute Config Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Attribute Config Type</em>'.
     * @generated
     */
    AttributeConfigType createAttributeConfigType();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Interaction Class Config Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Interaction Class Config Type</em>'.
     * @generated
     */
    InteractionClassConfigType createInteractionClassConfigType();

    /**
     * Returns a new object of class '<em>Linear Conversion Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Linear Conversion Type</em>'.
     * @generated
     */
    LinearConversionType createLinearConversionType();

    /**
     * Returns a new object of class '<em>Object Class Config Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Object Class Config Type</em>'.
     * @generated
     */
    ObjectClassConfigType createObjectClassConfigType();

    /**
     * Returns a new object of class '<em>Parameter Config Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Config Type</em>'.
     * @generated
     */
    ParameterConfigType createParameterConfigType();

    /**
     * Returns a new object of class '<em>Published Objects Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Published Objects Type</em>'.
     * @generated
     */
    PublishedObjectsType createPublishedObjectsType();

    /**
     * Returns a new object of class '<em>Unit Conversion Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Unit Conversion Type</em>'.
     * @generated
     */
    UnitConversionType createUnitConversionType();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ucefPackage getucefPackage();

} //ucefFactory

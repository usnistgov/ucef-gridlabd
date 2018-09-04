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
     * Returns a new object of class '<em>Attribute Details Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Attribute Details Type</em>'.
     * @generated
     */
    AttributeDetailsType createAttributeDetailsType();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Ignored Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Ignored Type</em>'.
     * @generated
     */
    IgnoredType createIgnoredType();

    /**
     * Returns a new object of class '<em>Interaction Details Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Interaction Details Type</em>'.
     * @generated
     */
    InteractionDetailsType createInteractionDetailsType();

    /**
     * Returns a new object of class '<em>Linear Conversion Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Linear Conversion Type</em>'.
     * @generated
     */
    LinearConversionType createLinearConversionType();

    /**
     * Returns a new object of class '<em>Object Details Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Object Details Type</em>'.
     * @generated
     */
    ObjectDetailsType createObjectDetailsType();

    /**
     * Returns a new object of class '<em>Parameter Details Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Parameter Details Type</em>'.
     * @generated
     */
    ParameterDetailsType createParameterDetailsType();

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

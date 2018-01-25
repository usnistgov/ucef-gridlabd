/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Config Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.AttributeConfigType#getUpdatePeriod <em>Update Period</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.AttributeConfigType#getUnitConversion <em>Unit Conversion</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getAttributeConfigType()
 * @model extendedMetaData="name='attributeConfigType' kind='elementOnly'"
 * @generated
 */
public interface AttributeConfigType extends EObject {
    /**
     * Returns the value of the '<em><b>Update Period</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The period in federation logical time at which new values are published for an interaction or object attribute.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Update Period</em>' attribute.
     * @see #isSetUpdatePeriod()
     * @see #unsetUpdatePeriod()
     * @see #setUpdatePeriod(double)
     * @see gov.nist.pages.ucef.ucefPackage#getAttributeConfigType_UpdatePeriod()
     * @model unsettable="true" dataType="gov.nist.pages.ucef.UpdatePeriodType"
     *        extendedMetaData="kind='element' name='updatePeriod' namespace='##targetNamespace'"
     * @generated
     */
    double getUpdatePeriod();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.AttributeConfigType#getUpdatePeriod <em>Update Period</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Period</em>' attribute.
     * @see #isSetUpdatePeriod()
     * @see #unsetUpdatePeriod()
     * @see #getUpdatePeriod()
     * @generated
     */
    void setUpdatePeriod(double value);

    /**
     * Unsets the value of the '{@link gov.nist.pages.ucef.AttributeConfigType#getUpdatePeriod <em>Update Period</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetUpdatePeriod()
     * @see #getUpdatePeriod()
     * @see #setUpdatePeriod(double)
     * @generated
     */
    void unsetUpdatePeriod();

    /**
     * Returns whether the value of the '{@link gov.nist.pages.ucef.AttributeConfigType#getUpdatePeriod <em>Update Period</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Update Period</em>' attribute is set.
     * @see #unsetUpdatePeriod()
     * @see #getUpdatePeriod()
     * @see #setUpdatePeriod(double)
     * @generated
     */
    boolean isSetUpdatePeriod();

    /**
     * Returns the value of the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A rule that defines how to perform unit conversion between values in the GridLAB-D model and the HLA federation.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Unit Conversion</em>' containment reference.
     * @see #setUnitConversion(UnitConversionType)
     * @see gov.nist.pages.ucef.ucefPackage#getAttributeConfigType_UnitConversion()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='unitConversion' namespace='##targetNamespace'"
     * @generated
     */
    UnitConversionType getUnitConversion();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.AttributeConfigType#getUnitConversion <em>Unit Conversion</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unit Conversion</em>' containment reference.
     * @see #getUnitConversion()
     * @generated
     */
    void setUnitConversion(UnitConversionType value);

} // AttributeConfigType

/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Details Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.AttributeDetailsType#isIgnored <em>Ignored</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.AttributeDetailsType#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.AttributeDetailsType#getUpdatePeriod <em>Update Period</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.AttributeDetailsType#getUnitConversion <em>Unit Conversion</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getAttributeDetailsType()
 * @model extendedMetaData="name='attributeDetailsType' kind='elementOnly'"
 * @generated
 */
public interface AttributeDetailsType extends EObject {
    /**
     * Returns the value of the '<em><b>Ignored</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * An ignore flag to indicate that a parameter or attribute should not be sent to or retrieved from the GridLAB-D simulation.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Ignored</em>' attribute.
     * @see #isSetIgnored()
     * @see #unsetIgnored()
     * @see #setIgnored(boolean)
     * @see gov.nist.pages.ucef.ucefPackage#getAttributeDetailsType_Ignored()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='ignored' namespace='##targetNamespace'"
     * @generated
     */
    boolean isIgnored();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.AttributeDetailsType#isIgnored <em>Ignored</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ignored</em>' attribute.
     * @see #isSetIgnored()
     * @see #unsetIgnored()
     * @see #isIgnored()
     * @generated
     */
    void setIgnored(boolean value);

    /**
     * Unsets the value of the '{@link gov.nist.pages.ucef.AttributeDetailsType#isIgnored <em>Ignored</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIgnored()
     * @see #isIgnored()
     * @see #setIgnored(boolean)
     * @generated
     */
    void unsetIgnored();

    /**
     * Returns whether the value of the '{@link gov.nist.pages.ucef.AttributeDetailsType#isIgnored <em>Ignored</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Ignored</em>' attribute is set.
     * @see #unsetIgnored()
     * @see #isIgnored()
     * @see #setIgnored(boolean)
     * @generated
     */
    boolean isSetIgnored();

    /**
     * Returns the value of the '<em><b>Property Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A substitute name to use for the property in the associated GridLAB-D object instead of the attribute or parameter name.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Property Name</em>' attribute.
     * @see #setPropertyName(String)
     * @see gov.nist.pages.ucef.ucefPackage#getAttributeDetailsType_PropertyName()
     * @model dataType="gov.nist.pages.ucef.PropertyNameType"
     *        extendedMetaData="kind='element' name='propertyName' namespace='##targetNamespace'"
     * @generated
     */
    String getPropertyName();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.AttributeDetailsType#getPropertyName <em>Property Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Property Name</em>' attribute.
     * @see #getPropertyName()
     * @generated
     */
    void setPropertyName(String value);

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
     * @see gov.nist.pages.ucef.ucefPackage#getAttributeDetailsType_UpdatePeriod()
     * @model unsettable="true" dataType="gov.nist.pages.ucef.UpdatePeriodType"
     *        extendedMetaData="kind='element' name='updatePeriod' namespace='##targetNamespace'"
     * @generated
     */
    double getUpdatePeriod();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.AttributeDetailsType#getUpdatePeriod <em>Update Period</em>}' attribute.
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
     * Unsets the value of the '{@link gov.nist.pages.ucef.AttributeDetailsType#getUpdatePeriod <em>Update Period</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetUpdatePeriod()
     * @see #getUpdatePeriod()
     * @see #setUpdatePeriod(double)
     * @generated
     */
    void unsetUpdatePeriod();

    /**
     * Returns whether the value of the '{@link gov.nist.pages.ucef.AttributeDetailsType#getUpdatePeriod <em>Update Period</em>}' attribute is set.
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
     * @see gov.nist.pages.ucef.ucefPackage#getAttributeDetailsType_UnitConversion()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='unitConversion' namespace='##targetNamespace'"
     * @generated
     */
    UnitConversionType getUnitConversion();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.AttributeDetailsType#getUnitConversion <em>Unit Conversion</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unit Conversion</em>' containment reference.
     * @see #getUnitConversion()
     * @generated
     */
    void setUnitConversion(UnitConversionType value);

} // AttributeDetailsType

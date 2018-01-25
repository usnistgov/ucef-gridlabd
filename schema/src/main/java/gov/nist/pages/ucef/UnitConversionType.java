/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unit Conversion Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.UnitConversionType#getNameConversion <em>Name Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.UnitConversionType#getLinearConversion <em>Linear Conversion</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getUnitConversionType()
 * @model extendedMetaData="name='unitConversionType' kind='elementOnly'"
 * @generated
 */
public interface UnitConversionType extends EObject {
    /**
     * Returns the value of the '<em><b>Name Conversion</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A unit conversion rule that declares the HLA federation uses the specified unit name. This rule relies on GridLAB-D's internal unit conversion and only works for attributes and parameters defined as floating point values.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name Conversion</em>' attribute.
     * @see #setNameConversion(String)
     * @see gov.nist.pages.ucef.ucefPackage#getUnitConversionType_NameConversion()
     * @model dataType="gov.nist.pages.ucef.NameConversionType"
     *        extendedMetaData="kind='element' name='nameConversion' namespace='##targetNamespace'"
     * @generated
     */
    String getNameConversion();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.UnitConversionType#getNameConversion <em>Name Conversion</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name Conversion</em>' attribute.
     * @see #getNameConversion()
     * @generated
     */
    void setNameConversion(String value);

    /**
     * Returns the value of the '<em><b>Linear Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A unit conversion rule that implements the linear function (hlaValue = scale * gldValue + offset) where the GridLAB-D value is the independent variable.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Linear Conversion</em>' containment reference.
     * @see #setLinearConversion(LinearConversionType)
     * @see gov.nist.pages.ucef.ucefPackage#getUnitConversionType_LinearConversion()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='linearConversion' namespace='##targetNamespace'"
     * @generated
     */
    LinearConversionType getLinearConversion();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.UnitConversionType#getLinearConversion <em>Linear Conversion</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Linear Conversion</em>' containment reference.
     * @see #getLinearConversion()
     * @generated
     */
    void setLinearConversion(LinearConversionType value);

} // UnitConversionType

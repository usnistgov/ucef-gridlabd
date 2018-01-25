/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Config Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.ParameterConfigType#getUnitConversion <em>Unit Conversion</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getParameterConfigType()
 * @model extendedMetaData="name='parameterConfigType' kind='elementOnly'"
 * @generated
 */
public interface ParameterConfigType extends EObject {
    /**
     * Returns the value of the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A rule that defines how to perform unit conversion between values in the GridLAB-D model and the HLA federation.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Unit Conversion</em>' containment reference.
     * @see #setUnitConversion(UnitConversionType)
     * @see gov.nist.pages.ucef.ucefPackage#getParameterConfigType_UnitConversion()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='unitConversion' namespace='##targetNamespace'"
     * @generated
     */
    UnitConversionType getUnitConversion();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.ParameterConfigType#getUnitConversion <em>Unit Conversion</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unit Conversion</em>' containment reference.
     * @see #getUnitConversion()
     * @generated
     */
    void setUnitConversion(UnitConversionType value);

} // ParameterConfigType

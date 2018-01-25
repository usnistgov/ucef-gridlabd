/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Linear Conversion Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.LinearConversionType#getScale <em>Scale</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.LinearConversionType#getOffset <em>Offset</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getLinearConversionType()
 * @model extendedMetaData="name='linearConversionType' kind='elementOnly'"
 * @generated
 */
public interface LinearConversionType extends EObject {
    /**
     * Returns the value of the '<em><b>Scale</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The scale multiplier for the linear conversion.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Scale</em>' attribute.
     * @see #isSetScale()
     * @see #unsetScale()
     * @see #setScale(double)
     * @see gov.nist.pages.ucef.ucefPackage#getLinearConversionType_Scale()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
     *        extendedMetaData="kind='element' name='scale' namespace='##targetNamespace'"
     * @generated
     */
    double getScale();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.LinearConversionType#getScale <em>Scale</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Scale</em>' attribute.
     * @see #isSetScale()
     * @see #unsetScale()
     * @see #getScale()
     * @generated
     */
    void setScale(double value);

    /**
     * Unsets the value of the '{@link gov.nist.pages.ucef.LinearConversionType#getScale <em>Scale</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetScale()
     * @see #getScale()
     * @see #setScale(double)
     * @generated
     */
    void unsetScale();

    /**
     * Returns whether the value of the '{@link gov.nist.pages.ucef.LinearConversionType#getScale <em>Scale</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Scale</em>' attribute is set.
     * @see #unsetScale()
     * @see #getScale()
     * @see #setScale(double)
     * @generated
     */
    boolean isSetScale();

    /**
     * Returns the value of the '<em><b>Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The offset for the linear conversion.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Offset</em>' attribute.
     * @see #isSetOffset()
     * @see #unsetOffset()
     * @see #setOffset(double)
     * @see gov.nist.pages.ucef.ucefPackage#getLinearConversionType_Offset()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
     *        extendedMetaData="kind='element' name='offset' namespace='##targetNamespace'"
     * @generated
     */
    double getOffset();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.LinearConversionType#getOffset <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Offset</em>' attribute.
     * @see #isSetOffset()
     * @see #unsetOffset()
     * @see #getOffset()
     * @generated
     */
    void setOffset(double value);

    /**
     * Unsets the value of the '{@link gov.nist.pages.ucef.LinearConversionType#getOffset <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetOffset()
     * @see #getOffset()
     * @see #setOffset(double)
     * @generated
     */
    void unsetOffset();

    /**
     * Returns whether the value of the '{@link gov.nist.pages.ucef.LinearConversionType#getOffset <em>Offset</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Offset</em>' attribute is set.
     * @see #unsetOffset()
     * @see #getOffset()
     * @see #setOffset(double)
     * @generated
     */
    boolean isSetOffset();

} // LinearConversionType

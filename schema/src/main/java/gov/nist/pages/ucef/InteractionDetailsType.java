/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interaction Details Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.InteractionDetailsType#getUpdatePeriod <em>Update Period</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.InteractionDetailsType#getPublishedObjects <em>Published Objects</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getInteractionDetailsType()
 * @model extendedMetaData="name='interactionDetailsType' kind='elementOnly'"
 * @generated
 */
public interface InteractionDetailsType extends EObject {
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
     * @see gov.nist.pages.ucef.ucefPackage#getInteractionDetailsType_UpdatePeriod()
     * @model unsettable="true" dataType="gov.nist.pages.ucef.UpdatePeriodType"
     *        extendedMetaData="kind='element' name='updatePeriod' namespace='##targetNamespace'"
     * @generated
     */
    double getUpdatePeriod();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.InteractionDetailsType#getUpdatePeriod <em>Update Period</em>}' attribute.
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
     * Unsets the value of the '{@link gov.nist.pages.ucef.InteractionDetailsType#getUpdatePeriod <em>Update Period</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetUpdatePeriod()
     * @see #getUpdatePeriod()
     * @see #setUpdatePeriod(double)
     * @generated
     */
    void unsetUpdatePeriod();

    /**
     * Returns whether the value of the '{@link gov.nist.pages.ucef.InteractionDetailsType#getUpdatePeriod <em>Update Period</em>}' attribute is set.
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
     * Returns the value of the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A set of GridLAB-D object names that should be published in the federation as a HLA interaction or object class.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Published Objects</em>' containment reference.
     * @see #setPublishedObjects(PublishedObjectsType)
     * @see gov.nist.pages.ucef.ucefPackage#getInteractionDetailsType_PublishedObjects()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='publishedObjects' namespace='##targetNamespace'"
     * @generated
     */
    PublishedObjectsType getPublishedObjects();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.InteractionDetailsType#getPublishedObjects <em>Published Objects</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Published Objects</em>' containment reference.
     * @see #getPublishedObjects()
     * @generated
     */
    void setPublishedObjects(PublishedObjectsType value);

} // InteractionDetailsType

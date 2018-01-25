/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Class Config Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.ObjectClassConfigType#getPublishedObjects <em>Published Objects</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getObjectClassConfigType()
 * @model extendedMetaData="name='objectClassConfigType' kind='elementOnly'"
 * @generated
 */
public interface ObjectClassConfigType extends EObject {
    /**
     * Returns the value of the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A set of GridLAB-D object names that should be published in the federation as a HLA interaction or object class.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Published Objects</em>' containment reference.
     * @see #setPublishedObjects(PublishedObjectsType)
     * @see gov.nist.pages.ucef.ucefPackage#getObjectClassConfigType_PublishedObjects()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='publishedObjects' namespace='##targetNamespace'"
     * @generated
     */
    PublishedObjectsType getPublishedObjects();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.ObjectClassConfigType#getPublishedObjects <em>Published Objects</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Published Objects</em>' containment reference.
     * @see #getPublishedObjects()
     * @generated
     */
    void setPublishedObjects(PublishedObjectsType value);

} // ObjectClassConfigType

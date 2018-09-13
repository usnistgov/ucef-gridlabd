/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Published Objects Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.PublishedObjectsType#getObjectName <em>Object Name</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getPublishedObjectsType()
 * @model extendedMetaData="name='publishedObjectsType' kind='elementOnly'"
 * @generated
 */
public interface PublishedObjectsType extends EObject {
    /**
     * Returns the value of the '<em><b>Object Name</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The name of an object defined in the GridLAB-D model.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Object Name</em>' attribute list.
     * @see gov.nist.pages.ucef.ucefPackage#getPublishedObjectsType_ObjectName()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='objectName' namespace='##targetNamespace'"
     * @generated
     */
    EList<String> getObjectName();

} // PublishedObjectsType

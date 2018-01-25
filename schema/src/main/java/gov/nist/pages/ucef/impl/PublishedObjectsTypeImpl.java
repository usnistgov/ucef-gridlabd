/**
 */
package gov.nist.pages.ucef.impl;

import gov.nist.pages.ucef.PublishedObjectsType;
import gov.nist.pages.ucef.ucefPackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Published Objects Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.impl.PublishedObjectsTypeImpl#getObjectName <em>Object Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PublishedObjectsTypeImpl extends MinimalEObjectImpl.Container implements PublishedObjectsType {
    /**
     * The cached value of the '{@link #getObjectName() <em>Object Name</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectName()
     * @generated
     * @ordered
     */
    protected EList<String> objectName;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PublishedObjectsTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ucefPackage.Literals.PUBLISHED_OBJECTS_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getObjectName() {
        if (objectName == null) {
            objectName = new EDataTypeEList<String>(String.class, this, ucefPackage.PUBLISHED_OBJECTS_TYPE__OBJECT_NAME);
        }
        return objectName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ucefPackage.PUBLISHED_OBJECTS_TYPE__OBJECT_NAME:
                return getObjectName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ucefPackage.PUBLISHED_OBJECTS_TYPE__OBJECT_NAME:
                getObjectName().clear();
                getObjectName().addAll((Collection<? extends String>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ucefPackage.PUBLISHED_OBJECTS_TYPE__OBJECT_NAME:
                getObjectName().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ucefPackage.PUBLISHED_OBJECTS_TYPE__OBJECT_NAME:
                return objectName != null && !objectName.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (objectName: ");
        result.append(objectName);
        result.append(')');
        return result.toString();
    }

} //PublishedObjectsTypeImpl

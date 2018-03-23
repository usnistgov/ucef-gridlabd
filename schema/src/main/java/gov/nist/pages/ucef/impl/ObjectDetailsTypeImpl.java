/**
 */
package gov.nist.pages.ucef.impl;

import gov.nist.pages.ucef.ObjectDetailsType;
import gov.nist.pages.ucef.PublishedObjectsType;
import gov.nist.pages.ucef.ucefPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Details Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.impl.ObjectDetailsTypeImpl#getPublishedObjects <em>Published Objects</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ObjectDetailsTypeImpl extends MinimalEObjectImpl.Container implements ObjectDetailsType {
    /**
     * The cached value of the '{@link #getPublishedObjects() <em>Published Objects</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPublishedObjects()
     * @generated
     * @ordered
     */
    protected PublishedObjectsType publishedObjects;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ObjectDetailsTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ucefPackage.Literals.OBJECT_DETAILS_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PublishedObjectsType getPublishedObjects() {
        return publishedObjects;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPublishedObjects(PublishedObjectsType newPublishedObjects, NotificationChain msgs) {
        PublishedObjectsType oldPublishedObjects = publishedObjects;
        publishedObjects = newPublishedObjects;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS, oldPublishedObjects, newPublishedObjects);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPublishedObjects(PublishedObjectsType newPublishedObjects) {
        if (newPublishedObjects != publishedObjects) {
            NotificationChain msgs = null;
            if (publishedObjects != null)
                msgs = ((InternalEObject)publishedObjects).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS, null, msgs);
            if (newPublishedObjects != null)
                msgs = ((InternalEObject)newPublishedObjects).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS, null, msgs);
            msgs = basicSetPublishedObjects(newPublishedObjects, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS, newPublishedObjects, newPublishedObjects));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS:
                return basicSetPublishedObjects(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS:
                return getPublishedObjects();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS:
                setPublishedObjects((PublishedObjectsType)newValue);
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
            case ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS:
                setPublishedObjects((PublishedObjectsType)null);
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
            case ucefPackage.OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS:
                return publishedObjects != null;
        }
        return super.eIsSet(featureID);
    }

} //ObjectDetailsTypeImpl

/**
 */
package gov.nist.pages.ucef.impl;

import gov.nist.pages.ucef.InteractionDetailsType;
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
 * An implementation of the model object '<em><b>Interaction Details Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.impl.InteractionDetailsTypeImpl#getUpdatePeriod <em>Update Period</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.InteractionDetailsTypeImpl#getPublishedObjects <em>Published Objects</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InteractionDetailsTypeImpl extends MinimalEObjectImpl.Container implements InteractionDetailsType {
    /**
     * The default value of the '{@link #getUpdatePeriod() <em>Update Period</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpdatePeriod()
     * @generated
     * @ordered
     */
    protected static final double UPDATE_PERIOD_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getUpdatePeriod() <em>Update Period</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpdatePeriod()
     * @generated
     * @ordered
     */
    protected double updatePeriod = UPDATE_PERIOD_EDEFAULT;

    /**
     * This is true if the Update Period attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean updatePeriodESet;

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
    protected InteractionDetailsTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ucefPackage.Literals.INTERACTION_DETAILS_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getUpdatePeriod() {
        return updatePeriod;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpdatePeriod(double newUpdatePeriod) {
        double oldUpdatePeriod = updatePeriod;
        updatePeriod = newUpdatePeriod;
        boolean oldUpdatePeriodESet = updatePeriodESet;
        updatePeriodESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.INTERACTION_DETAILS_TYPE__UPDATE_PERIOD, oldUpdatePeriod, updatePeriod, !oldUpdatePeriodESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetUpdatePeriod() {
        double oldUpdatePeriod = updatePeriod;
        boolean oldUpdatePeriodESet = updatePeriodESet;
        updatePeriod = UPDATE_PERIOD_EDEFAULT;
        updatePeriodESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ucefPackage.INTERACTION_DETAILS_TYPE__UPDATE_PERIOD, oldUpdatePeriod, UPDATE_PERIOD_EDEFAULT, oldUpdatePeriodESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetUpdatePeriod() {
        return updatePeriodESet;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS, oldPublishedObjects, newPublishedObjects);
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
                msgs = ((InternalEObject)publishedObjects).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS, null, msgs);
            if (newPublishedObjects != null)
                msgs = ((InternalEObject)newPublishedObjects).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS, null, msgs);
            msgs = basicSetPublishedObjects(newPublishedObjects, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS, newPublishedObjects, newPublishedObjects));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS:
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
            case ucefPackage.INTERACTION_DETAILS_TYPE__UPDATE_PERIOD:
                return getUpdatePeriod();
            case ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS:
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
            case ucefPackage.INTERACTION_DETAILS_TYPE__UPDATE_PERIOD:
                setUpdatePeriod((Double)newValue);
                return;
            case ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS:
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
            case ucefPackage.INTERACTION_DETAILS_TYPE__UPDATE_PERIOD:
                unsetUpdatePeriod();
                return;
            case ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS:
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
            case ucefPackage.INTERACTION_DETAILS_TYPE__UPDATE_PERIOD:
                return isSetUpdatePeriod();
            case ucefPackage.INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS:
                return publishedObjects != null;
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
        result.append(" (updatePeriod: ");
        if (updatePeriodESet) result.append(updatePeriod); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //InteractionDetailsTypeImpl

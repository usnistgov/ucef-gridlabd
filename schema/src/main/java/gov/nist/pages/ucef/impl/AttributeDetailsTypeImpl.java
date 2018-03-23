/**
 */
package gov.nist.pages.ucef.impl;

import gov.nist.pages.ucef.AttributeDetailsType;
import gov.nist.pages.ucef.UnitConversionType;
import gov.nist.pages.ucef.ucefPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Details Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.impl.AttributeDetailsTypeImpl#isIgnored <em>Ignored</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.AttributeDetailsTypeImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.AttributeDetailsTypeImpl#getUpdatePeriod <em>Update Period</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.AttributeDetailsTypeImpl#getUnitConversion <em>Unit Conversion</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeDetailsTypeImpl extends MinimalEObjectImpl.Container implements AttributeDetailsType {
    /**
     * The default value of the '{@link #isIgnored() <em>Ignored</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIgnored()
     * @generated
     * @ordered
     */
    protected static final boolean IGNORED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIgnored() <em>Ignored</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIgnored()
     * @generated
     * @ordered
     */
    protected boolean ignored = IGNORED_EDEFAULT;

    /**
     * This is true if the Ignored attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean ignoredESet;

    /**
     * The default value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPropertyName()
     * @generated
     * @ordered
     */
    protected static final String PROPERTY_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPropertyName()
     * @generated
     * @ordered
     */
    protected String propertyName = PROPERTY_NAME_EDEFAULT;

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
     * The cached value of the '{@link #getUnitConversion() <em>Unit Conversion</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnitConversion()
     * @generated
     * @ordered
     */
    protected UnitConversionType unitConversion;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AttributeDetailsTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ucefPackage.Literals.ATTRIBUTE_DETAILS_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIgnored() {
        return ignored;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIgnored(boolean newIgnored) {
        boolean oldIgnored = ignored;
        ignored = newIgnored;
        boolean oldIgnoredESet = ignoredESet;
        ignoredESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.ATTRIBUTE_DETAILS_TYPE__IGNORED, oldIgnored, ignored, !oldIgnoredESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetIgnored() {
        boolean oldIgnored = ignored;
        boolean oldIgnoredESet = ignoredESet;
        ignored = IGNORED_EDEFAULT;
        ignoredESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ucefPackage.ATTRIBUTE_DETAILS_TYPE__IGNORED, oldIgnored, IGNORED_EDEFAULT, oldIgnoredESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetIgnored() {
        return ignoredESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPropertyName(String newPropertyName) {
        String oldPropertyName = propertyName;
        propertyName = newPropertyName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.ATTRIBUTE_DETAILS_TYPE__PROPERTY_NAME, oldPropertyName, propertyName));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.ATTRIBUTE_DETAILS_TYPE__UPDATE_PERIOD, oldUpdatePeriod, updatePeriod, !oldUpdatePeriodESet));
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
            eNotify(new ENotificationImpl(this, Notification.UNSET, ucefPackage.ATTRIBUTE_DETAILS_TYPE__UPDATE_PERIOD, oldUpdatePeriod, UPDATE_PERIOD_EDEFAULT, oldUpdatePeriodESet));
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
    public UnitConversionType getUnitConversion() {
        return unitConversion;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUnitConversion(UnitConversionType newUnitConversion, NotificationChain msgs) {
        UnitConversionType oldUnitConversion = unitConversion;
        unitConversion = newUnitConversion;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION, oldUnitConversion, newUnitConversion);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUnitConversion(UnitConversionType newUnitConversion) {
        if (newUnitConversion != unitConversion) {
            NotificationChain msgs = null;
            if (unitConversion != null)
                msgs = ((InternalEObject)unitConversion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION, null, msgs);
            if (newUnitConversion != null)
                msgs = ((InternalEObject)newUnitConversion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION, null, msgs);
            msgs = basicSetUnitConversion(newUnitConversion, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION, newUnitConversion, newUnitConversion));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION:
                return basicSetUnitConversion(null, msgs);
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
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__IGNORED:
                return isIgnored();
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__PROPERTY_NAME:
                return getPropertyName();
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UPDATE_PERIOD:
                return getUpdatePeriod();
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION:
                return getUnitConversion();
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
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__IGNORED:
                setIgnored((Boolean)newValue);
                return;
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__PROPERTY_NAME:
                setPropertyName((String)newValue);
                return;
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UPDATE_PERIOD:
                setUpdatePeriod((Double)newValue);
                return;
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION:
                setUnitConversion((UnitConversionType)newValue);
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
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__IGNORED:
                unsetIgnored();
                return;
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__PROPERTY_NAME:
                setPropertyName(PROPERTY_NAME_EDEFAULT);
                return;
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UPDATE_PERIOD:
                unsetUpdatePeriod();
                return;
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION:
                setUnitConversion((UnitConversionType)null);
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
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__IGNORED:
                return isSetIgnored();
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__PROPERTY_NAME:
                return PROPERTY_NAME_EDEFAULT == null ? propertyName != null : !PROPERTY_NAME_EDEFAULT.equals(propertyName);
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UPDATE_PERIOD:
                return isSetUpdatePeriod();
            case ucefPackage.ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION:
                return unitConversion != null;
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
        result.append(" (ignored: ");
        if (ignoredESet) result.append(ignored); else result.append("<unset>");
        result.append(", propertyName: ");
        result.append(propertyName);
        result.append(", updatePeriod: ");
        if (updatePeriodESet) result.append(updatePeriod); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //AttributeDetailsTypeImpl

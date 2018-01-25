/**
 */
package gov.nist.pages.ucef.impl;

import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ucefPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Linear Conversion Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.impl.LinearConversionTypeImpl#getScale <em>Scale</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.LinearConversionTypeImpl#getOffset <em>Offset</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LinearConversionTypeImpl extends MinimalEObjectImpl.Container implements LinearConversionType {
    /**
     * The default value of the '{@link #getScale() <em>Scale</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScale()
     * @generated
     * @ordered
     */
    protected static final double SCALE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getScale() <em>Scale</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScale()
     * @generated
     * @ordered
     */
    protected double scale = SCALE_EDEFAULT;

    /**
     * This is true if the Scale attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean scaleESet;

    /**
     * The default value of the '{@link #getOffset() <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOffset()
     * @generated
     * @ordered
     */
    protected static final double OFFSET_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getOffset() <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOffset()
     * @generated
     * @ordered
     */
    protected double offset = OFFSET_EDEFAULT;

    /**
     * This is true if the Offset attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean offsetESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LinearConversionTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ucefPackage.Literals.LINEAR_CONVERSION_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getScale() {
        return scale;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScale(double newScale) {
        double oldScale = scale;
        scale = newScale;
        boolean oldScaleESet = scaleESet;
        scaleESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.LINEAR_CONVERSION_TYPE__SCALE, oldScale, scale, !oldScaleESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetScale() {
        double oldScale = scale;
        boolean oldScaleESet = scaleESet;
        scale = SCALE_EDEFAULT;
        scaleESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ucefPackage.LINEAR_CONVERSION_TYPE__SCALE, oldScale, SCALE_EDEFAULT, oldScaleESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetScale() {
        return scaleESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getOffset() {
        return offset;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOffset(double newOffset) {
        double oldOffset = offset;
        offset = newOffset;
        boolean oldOffsetESet = offsetESet;
        offsetESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ucefPackage.LINEAR_CONVERSION_TYPE__OFFSET, oldOffset, offset, !oldOffsetESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetOffset() {
        double oldOffset = offset;
        boolean oldOffsetESet = offsetESet;
        offset = OFFSET_EDEFAULT;
        offsetESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ucefPackage.LINEAR_CONVERSION_TYPE__OFFSET, oldOffset, OFFSET_EDEFAULT, oldOffsetESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetOffset() {
        return offsetESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ucefPackage.LINEAR_CONVERSION_TYPE__SCALE:
                return getScale();
            case ucefPackage.LINEAR_CONVERSION_TYPE__OFFSET:
                return getOffset();
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
            case ucefPackage.LINEAR_CONVERSION_TYPE__SCALE:
                setScale((Double)newValue);
                return;
            case ucefPackage.LINEAR_CONVERSION_TYPE__OFFSET:
                setOffset((Double)newValue);
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
            case ucefPackage.LINEAR_CONVERSION_TYPE__SCALE:
                unsetScale();
                return;
            case ucefPackage.LINEAR_CONVERSION_TYPE__OFFSET:
                unsetOffset();
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
            case ucefPackage.LINEAR_CONVERSION_TYPE__SCALE:
                return isSetScale();
            case ucefPackage.LINEAR_CONVERSION_TYPE__OFFSET:
                return isSetOffset();
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
        result.append(" (scale: ");
        if (scaleESet) result.append(scale); else result.append("<unset>");
        result.append(", offset: ");
        if (offsetESet) result.append(offset); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //LinearConversionTypeImpl

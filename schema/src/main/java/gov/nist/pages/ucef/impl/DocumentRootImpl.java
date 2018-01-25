/**
 */
package gov.nist.pages.ucef.impl;

import gov.nist.pages.ucef.AttributeConfigType;
import gov.nist.pages.ucef.DocumentRoot;
import gov.nist.pages.ucef.InteractionClassConfigType;
import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ObjectClassConfigType;
import gov.nist.pages.ucef.ParameterConfigType;
import gov.nist.pages.ucef.PublishedObjectsType;
import gov.nist.pages.ucef.UnitConversionType;
import gov.nist.pages.ucef.ucefPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getAttributeConfig <em>Attribute Config</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getInteractionClassConfig <em>Interaction Class Config</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getLinearConversion <em>Linear Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getNameConversion <em>Name Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getObjectClassConfig <em>Object Class Config</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getParameterConfig <em>Parameter Config</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getPublishedObjects <em>Published Objects</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getUnitConversion <em>Unit Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getUpdatePeriod <em>Update Period</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

    /**
     * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXMLNSPrefixMap()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xMLNSPrefixMap;

    /**
     * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXSISchemaLocation()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xSISchemaLocation;

    /**
     * The default value of the '{@link #getNameConversion() <em>Name Conversion</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNameConversion()
     * @generated
     * @ordered
     */
    protected static final String NAME_CONVERSION_EDEFAULT = null;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DocumentRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ucefPackage.Literals.DOCUMENT_ROOT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, ucefPackage.DOCUMENT_ROOT__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXMLNSPrefixMap() {
        if (xMLNSPrefixMap == null) {
            xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ucefPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        }
        return xMLNSPrefixMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXSISchemaLocation() {
        if (xSISchemaLocation == null) {
            xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, ucefPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        }
        return xSISchemaLocation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AttributeConfigType getAttributeConfig() {
        return (AttributeConfigType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_CONFIG, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAttributeConfig(AttributeConfigType newAttributeConfig, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_CONFIG, newAttributeConfig, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAttributeConfig(AttributeConfigType newAttributeConfig) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_CONFIG, newAttributeConfig);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InteractionClassConfigType getInteractionClassConfig() {
        return (InteractionClassConfigType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInteractionClassConfig(InteractionClassConfigType newInteractionClassConfig, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG, newInteractionClassConfig, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInteractionClassConfig(InteractionClassConfigType newInteractionClassConfig) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG, newInteractionClassConfig);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinearConversionType getLinearConversion() {
        return (LinearConversionType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__LINEAR_CONVERSION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLinearConversion(LinearConversionType newLinearConversion, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__LINEAR_CONVERSION, newLinearConversion, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLinearConversion(LinearConversionType newLinearConversion) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__LINEAR_CONVERSION, newLinearConversion);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNameConversion() {
        return (String)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__NAME_CONVERSION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNameConversion(String newNameConversion) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__NAME_CONVERSION, newNameConversion);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ObjectClassConfigType getObjectClassConfig() {
        return (ObjectClassConfigType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__OBJECT_CLASS_CONFIG, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetObjectClassConfig(ObjectClassConfigType newObjectClassConfig, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__OBJECT_CLASS_CONFIG, newObjectClassConfig, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setObjectClassConfig(ObjectClassConfigType newObjectClassConfig) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__OBJECT_CLASS_CONFIG, newObjectClassConfig);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterConfigType getParameterConfig() {
        return (ParameterConfigType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__PARAMETER_CONFIG, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParameterConfig(ParameterConfigType newParameterConfig, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__PARAMETER_CONFIG, newParameterConfig, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParameterConfig(ParameterConfigType newParameterConfig) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__PARAMETER_CONFIG, newParameterConfig);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PublishedObjectsType getPublishedObjects() {
        return (PublishedObjectsType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__PUBLISHED_OBJECTS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPublishedObjects(PublishedObjectsType newPublishedObjects, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__PUBLISHED_OBJECTS, newPublishedObjects, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPublishedObjects(PublishedObjectsType newPublishedObjects) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__PUBLISHED_OBJECTS, newPublishedObjects);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UnitConversionType getUnitConversion() {
        return (UnitConversionType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__UNIT_CONVERSION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUnitConversion(UnitConversionType newUnitConversion, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__UNIT_CONVERSION, newUnitConversion, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUnitConversion(UnitConversionType newUnitConversion) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__UNIT_CONVERSION, newUnitConversion);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getUpdatePeriod() {
        return (Double)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__UPDATE_PERIOD, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpdatePeriod(double newUpdatePeriod) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__UPDATE_PERIOD, newUpdatePeriod);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ucefPackage.DOCUMENT_ROOT__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case ucefPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
            case ucefPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_CONFIG:
                return basicSetAttributeConfig(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG:
                return basicSetInteractionClassConfig(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                return basicSetLinearConversion(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__OBJECT_CLASS_CONFIG:
                return basicSetObjectClassConfig(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_CONFIG:
                return basicSetParameterConfig(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                return basicSetPublishedObjects(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
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
            case ucefPackage.DOCUMENT_ROOT__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case ucefPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                if (coreType) return getXMLNSPrefixMap();
                else return getXMLNSPrefixMap().map();
            case ucefPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                if (coreType) return getXSISchemaLocation();
                else return getXSISchemaLocation().map();
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_CONFIG:
                return getAttributeConfig();
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG:
                return getInteractionClassConfig();
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                return getLinearConversion();
            case ucefPackage.DOCUMENT_ROOT__NAME_CONVERSION:
                return getNameConversion();
            case ucefPackage.DOCUMENT_ROOT__OBJECT_CLASS_CONFIG:
                return getObjectClassConfig();
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_CONFIG:
                return getParameterConfig();
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                return getPublishedObjects();
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
                return getUnitConversion();
            case ucefPackage.DOCUMENT_ROOT__UPDATE_PERIOD:
                return getUpdatePeriod();
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
            case ucefPackage.DOCUMENT_ROOT__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_CONFIG:
                setAttributeConfig((AttributeConfigType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG:
                setInteractionClassConfig((InteractionClassConfigType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                setLinearConversion((LinearConversionType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__NAME_CONVERSION:
                setNameConversion((String)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__OBJECT_CLASS_CONFIG:
                setObjectClassConfig((ObjectClassConfigType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_CONFIG:
                setParameterConfig((ParameterConfigType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                setPublishedObjects((PublishedObjectsType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
                setUnitConversion((UnitConversionType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__UPDATE_PERIOD:
                setUpdatePeriod((Double)newValue);
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
            case ucefPackage.DOCUMENT_ROOT__MIXED:
                getMixed().clear();
                return;
            case ucefPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                getXMLNSPrefixMap().clear();
                return;
            case ucefPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                getXSISchemaLocation().clear();
                return;
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_CONFIG:
                setAttributeConfig((AttributeConfigType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG:
                setInteractionClassConfig((InteractionClassConfigType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                setLinearConversion((LinearConversionType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__NAME_CONVERSION:
                setNameConversion(NAME_CONVERSION_EDEFAULT);
                return;
            case ucefPackage.DOCUMENT_ROOT__OBJECT_CLASS_CONFIG:
                setObjectClassConfig((ObjectClassConfigType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_CONFIG:
                setParameterConfig((ParameterConfigType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                setPublishedObjects((PublishedObjectsType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
                setUnitConversion((UnitConversionType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__UPDATE_PERIOD:
                setUpdatePeriod(UPDATE_PERIOD_EDEFAULT);
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
            case ucefPackage.DOCUMENT_ROOT__MIXED:
                return mixed != null && !mixed.isEmpty();
            case ucefPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
            case ucefPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_CONFIG:
                return getAttributeConfig() != null;
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG:
                return getInteractionClassConfig() != null;
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                return getLinearConversion() != null;
            case ucefPackage.DOCUMENT_ROOT__NAME_CONVERSION:
                return NAME_CONVERSION_EDEFAULT == null ? getNameConversion() != null : !NAME_CONVERSION_EDEFAULT.equals(getNameConversion());
            case ucefPackage.DOCUMENT_ROOT__OBJECT_CLASS_CONFIG:
                return getObjectClassConfig() != null;
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_CONFIG:
                return getParameterConfig() != null;
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                return getPublishedObjects() != null;
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
                return getUnitConversion() != null;
            case ucefPackage.DOCUMENT_ROOT__UPDATE_PERIOD:
                return getUpdatePeriod() != UPDATE_PERIOD_EDEFAULT;
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
        result.append(" (mixed: ");
        result.append(mixed);
        result.append(')');
        return result.toString();
    }

} //DocumentRootImpl

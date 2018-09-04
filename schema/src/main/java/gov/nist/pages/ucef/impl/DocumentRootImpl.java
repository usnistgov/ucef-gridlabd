/**
 */
package gov.nist.pages.ucef.impl;

import gov.nist.pages.ucef.AttributeDetailsType;
import gov.nist.pages.ucef.DocumentRoot;
import gov.nist.pages.ucef.IgnoredType;
import gov.nist.pages.ucef.InteractionDetailsType;
import gov.nist.pages.ucef.LinearConversionType;
import gov.nist.pages.ucef.ObjectDetailsType;
import gov.nist.pages.ucef.ParameterDetailsType;
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
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getAttributeDetails <em>Attribute Details</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getIgnored <em>Ignored</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getInteractionDetails <em>Interaction Details</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getLinearConversion <em>Linear Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getObjectDetails <em>Object Details</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getParameterDetails <em>Parameter Details</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getPublishedObjects <em>Published Objects</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getUnitConversion <em>Unit Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.impl.DocumentRootImpl#getUnitName <em>Unit Name</em>}</li>
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
     * The default value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPropertyName()
     * @generated
     * @ordered
     */
    protected static final String PROPERTY_NAME_EDEFAULT = null;

    /**
     * The default value of the '{@link #getUnitName() <em>Unit Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnitName()
     * @generated
     * @ordered
     */
    protected static final String UNIT_NAME_EDEFAULT = null;

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
    public AttributeDetailsType getAttributeDetails() {
        return (AttributeDetailsType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_DETAILS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAttributeDetails(AttributeDetailsType newAttributeDetails, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_DETAILS, newAttributeDetails, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAttributeDetails(AttributeDetailsType newAttributeDetails) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE_DETAILS, newAttributeDetails);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IgnoredType getIgnored() {
        return (IgnoredType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__IGNORED, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIgnored(IgnoredType newIgnored, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__IGNORED, newIgnored, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIgnored(IgnoredType newIgnored) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__IGNORED, newIgnored);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InteractionDetailsType getInteractionDetails() {
        return (InteractionDetailsType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__INTERACTION_DETAILS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInteractionDetails(InteractionDetailsType newInteractionDetails, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__INTERACTION_DETAILS, newInteractionDetails, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInteractionDetails(InteractionDetailsType newInteractionDetails) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__INTERACTION_DETAILS, newInteractionDetails);
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
    public ObjectDetailsType getObjectDetails() {
        return (ObjectDetailsType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__OBJECT_DETAILS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetObjectDetails(ObjectDetailsType newObjectDetails, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__OBJECT_DETAILS, newObjectDetails, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setObjectDetails(ObjectDetailsType newObjectDetails) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__OBJECT_DETAILS, newObjectDetails);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParameterDetailsType getParameterDetails() {
        return (ParameterDetailsType)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__PARAMETER_DETAILS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParameterDetails(ParameterDetailsType newParameterDetails, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(ucefPackage.Literals.DOCUMENT_ROOT__PARAMETER_DETAILS, newParameterDetails, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParameterDetails(ParameterDetailsType newParameterDetails) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__PARAMETER_DETAILS, newParameterDetails);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPropertyName() {
        return (String)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__PROPERTY_NAME, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPropertyName(String newPropertyName) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__PROPERTY_NAME, newPropertyName);
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
    public String getUnitName() {
        return (String)getMixed().get(ucefPackage.Literals.DOCUMENT_ROOT__UNIT_NAME, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUnitName(String newUnitName) {
        ((FeatureMap.Internal)getMixed()).set(ucefPackage.Literals.DOCUMENT_ROOT__UNIT_NAME, newUnitName);
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
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_DETAILS:
                return basicSetAttributeDetails(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__IGNORED:
                return basicSetIgnored(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_DETAILS:
                return basicSetInteractionDetails(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                return basicSetLinearConversion(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__OBJECT_DETAILS:
                return basicSetObjectDetails(null, msgs);
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_DETAILS:
                return basicSetParameterDetails(null, msgs);
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
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_DETAILS:
                return getAttributeDetails();
            case ucefPackage.DOCUMENT_ROOT__IGNORED:
                return getIgnored();
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_DETAILS:
                return getInteractionDetails();
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                return getLinearConversion();
            case ucefPackage.DOCUMENT_ROOT__OBJECT_DETAILS:
                return getObjectDetails();
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_DETAILS:
                return getParameterDetails();
            case ucefPackage.DOCUMENT_ROOT__PROPERTY_NAME:
                return getPropertyName();
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                return getPublishedObjects();
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
                return getUnitConversion();
            case ucefPackage.DOCUMENT_ROOT__UNIT_NAME:
                return getUnitName();
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
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_DETAILS:
                setAttributeDetails((AttributeDetailsType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__IGNORED:
                setIgnored((IgnoredType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_DETAILS:
                setInteractionDetails((InteractionDetailsType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                setLinearConversion((LinearConversionType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__OBJECT_DETAILS:
                setObjectDetails((ObjectDetailsType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_DETAILS:
                setParameterDetails((ParameterDetailsType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__PROPERTY_NAME:
                setPropertyName((String)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                setPublishedObjects((PublishedObjectsType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
                setUnitConversion((UnitConversionType)newValue);
                return;
            case ucefPackage.DOCUMENT_ROOT__UNIT_NAME:
                setUnitName((String)newValue);
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
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_DETAILS:
                setAttributeDetails((AttributeDetailsType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__IGNORED:
                setIgnored((IgnoredType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_DETAILS:
                setInteractionDetails((InteractionDetailsType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                setLinearConversion((LinearConversionType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__OBJECT_DETAILS:
                setObjectDetails((ObjectDetailsType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_DETAILS:
                setParameterDetails((ParameterDetailsType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__PROPERTY_NAME:
                setPropertyName(PROPERTY_NAME_EDEFAULT);
                return;
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                setPublishedObjects((PublishedObjectsType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
                setUnitConversion((UnitConversionType)null);
                return;
            case ucefPackage.DOCUMENT_ROOT__UNIT_NAME:
                setUnitName(UNIT_NAME_EDEFAULT);
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
            case ucefPackage.DOCUMENT_ROOT__ATTRIBUTE_DETAILS:
                return getAttributeDetails() != null;
            case ucefPackage.DOCUMENT_ROOT__IGNORED:
                return getIgnored() != null;
            case ucefPackage.DOCUMENT_ROOT__INTERACTION_DETAILS:
                return getInteractionDetails() != null;
            case ucefPackage.DOCUMENT_ROOT__LINEAR_CONVERSION:
                return getLinearConversion() != null;
            case ucefPackage.DOCUMENT_ROOT__OBJECT_DETAILS:
                return getObjectDetails() != null;
            case ucefPackage.DOCUMENT_ROOT__PARAMETER_DETAILS:
                return getParameterDetails() != null;
            case ucefPackage.DOCUMENT_ROOT__PROPERTY_NAME:
                return PROPERTY_NAME_EDEFAULT == null ? getPropertyName() != null : !PROPERTY_NAME_EDEFAULT.equals(getPropertyName());
            case ucefPackage.DOCUMENT_ROOT__PUBLISHED_OBJECTS:
                return getPublishedObjects() != null;
            case ucefPackage.DOCUMENT_ROOT__UNIT_CONVERSION:
                return getUnitConversion() != null;
            case ucefPackage.DOCUMENT_ROOT__UNIT_NAME:
                return UNIT_NAME_EDEFAULT == null ? getUnitName() != null : !UNIT_NAME_EDEFAULT.equals(getUnitName());
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

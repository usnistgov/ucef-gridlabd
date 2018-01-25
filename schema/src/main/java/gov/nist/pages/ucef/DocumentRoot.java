/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getAttributeConfig <em>Attribute Config</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getInteractionClassConfig <em>Interaction Class Config</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getLinearConversion <em>Linear Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getNameConversion <em>Name Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getObjectClassConfig <em>Object Class Config</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getParameterConfig <em>Parameter Config</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getPublishedObjects <em>Published Objects</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getUnitConversion <em>Unit Conversion</em>}</li>
 *   <li>{@link gov.nist.pages.ucef.DocumentRoot#getUpdatePeriod <em>Update Period</em>}</li>
 * </ul>
 *
 * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
     * @generated
     */
    EMap<String, String> getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
    EMap<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>Attribute Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Custom GridLAB-D configuration options for object attributes.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Attribute Config</em>' containment reference.
     * @see #setAttributeConfig(AttributeConfigType)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_AttributeConfig()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='attributeConfig' namespace='##targetNamespace'"
     * @generated
     */
    AttributeConfigType getAttributeConfig();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getAttributeConfig <em>Attribute Config</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Attribute Config</em>' containment reference.
     * @see #getAttributeConfig()
     * @generated
     */
    void setAttributeConfig(AttributeConfigType value);

    /**
     * Returns the value of the '<em><b>Interaction Class Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Custom GridLAB-D configuration options for interaction classes.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Interaction Class Config</em>' containment reference.
     * @see #setInteractionClassConfig(InteractionClassConfigType)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_InteractionClassConfig()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='interactionClassConfig' namespace='##targetNamespace'"
     * @generated
     */
    InteractionClassConfigType getInteractionClassConfig();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getInteractionClassConfig <em>Interaction Class Config</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interaction Class Config</em>' containment reference.
     * @see #getInteractionClassConfig()
     * @generated
     */
    void setInteractionClassConfig(InteractionClassConfigType value);

    /**
     * Returns the value of the '<em><b>Linear Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A unit conversion rule that implements the linear function (hlaValue = scale * gldValue + offset) where the GridLAB-D value is the independent variable.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Linear Conversion</em>' containment reference.
     * @see #setLinearConversion(LinearConversionType)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_LinearConversion()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='linearConversion' namespace='##targetNamespace'"
     * @generated
     */
    LinearConversionType getLinearConversion();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getLinearConversion <em>Linear Conversion</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Linear Conversion</em>' containment reference.
     * @see #getLinearConversion()
     * @generated
     */
    void setLinearConversion(LinearConversionType value);

    /**
     * Returns the value of the '<em><b>Name Conversion</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A unit conversion rule that declares the HLA federation uses the specified unit name. This rule relies on GridLAB-D's internal unit conversion and only works for attributes and parameters defined as floating point values.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name Conversion</em>' attribute.
     * @see #setNameConversion(String)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_NameConversion()
     * @model unique="false" dataType="gov.nist.pages.ucef.NameConversionType" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='nameConversion' namespace='##targetNamespace'"
     * @generated
     */
    String getNameConversion();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getNameConversion <em>Name Conversion</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name Conversion</em>' attribute.
     * @see #getNameConversion()
     * @generated
     */
    void setNameConversion(String value);

    /**
     * Returns the value of the '<em><b>Object Class Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Custom GridLAB-D configuration options for object classes.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Object Class Config</em>' containment reference.
     * @see #setObjectClassConfig(ObjectClassConfigType)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_ObjectClassConfig()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='objectClassConfig' namespace='##targetNamespace'"
     * @generated
     */
    ObjectClassConfigType getObjectClassConfig();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getObjectClassConfig <em>Object Class Config</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Object Class Config</em>' containment reference.
     * @see #getObjectClassConfig()
     * @generated
     */
    void setObjectClassConfig(ObjectClassConfigType value);

    /**
     * Returns the value of the '<em><b>Parameter Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Custom GridLAB-D configuration options for interaction parameters.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Parameter Config</em>' containment reference.
     * @see #setParameterConfig(ParameterConfigType)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_ParameterConfig()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='parameterConfig' namespace='##targetNamespace'"
     * @generated
     */
    ParameterConfigType getParameterConfig();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getParameterConfig <em>Parameter Config</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameter Config</em>' containment reference.
     * @see #getParameterConfig()
     * @generated
     */
    void setParameterConfig(ParameterConfigType value);

    /**
     * Returns the value of the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A set of GridLAB-D object names that should be published in the federation as a HLA interaction or object class.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Published Objects</em>' containment reference.
     * @see #setPublishedObjects(PublishedObjectsType)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_PublishedObjects()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='publishedObjects' namespace='##targetNamespace'"
     * @generated
     */
    PublishedObjectsType getPublishedObjects();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getPublishedObjects <em>Published Objects</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Published Objects</em>' containment reference.
     * @see #getPublishedObjects()
     * @generated
     */
    void setPublishedObjects(PublishedObjectsType value);

    /**
     * Returns the value of the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A rule that defines how to perform unit conversion between values in the GridLAB-D model and the HLA federation.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Unit Conversion</em>' containment reference.
     * @see #setUnitConversion(UnitConversionType)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_UnitConversion()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='unitConversion' namespace='##targetNamespace'"
     * @generated
     */
    UnitConversionType getUnitConversion();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getUnitConversion <em>Unit Conversion</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unit Conversion</em>' containment reference.
     * @see #getUnitConversion()
     * @generated
     */
    void setUnitConversion(UnitConversionType value);

    /**
     * Returns the value of the '<em><b>Update Period</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The period in federation logical time at which new values are published for an interaction or object attribute.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Update Period</em>' attribute.
     * @see #setUpdatePeriod(double)
     * @see gov.nist.pages.ucef.ucefPackage#getDocumentRoot_UpdatePeriod()
     * @model unique="false" dataType="gov.nist.pages.ucef.UpdatePeriodType" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='updatePeriod' namespace='##targetNamespace'"
     * @generated
     */
    double getUpdatePeriod();

    /**
     * Sets the value of the '{@link gov.nist.pages.ucef.DocumentRoot#getUpdatePeriod <em>Update Period</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Period</em>' attribute.
     * @see #getUpdatePeriod()
     * @generated
     */
    void setUpdatePeriod(double value);

} // DocumentRoot

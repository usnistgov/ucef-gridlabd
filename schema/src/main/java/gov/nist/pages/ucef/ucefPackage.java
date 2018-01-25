/**
 */
package gov.nist.pages.ucef;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see gov.nist.pages.ucef.ucefFactory
 * @model kind="package"
 * @generated
 */
public interface ucefPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "ucef";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "https://pages.nist.gov/ucef";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "ucef";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ucefPackage eINSTANCE = gov.nist.pages.ucef.impl.ucefPackageImpl.init();

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.AttributeConfigTypeImpl <em>Attribute Config Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.AttributeConfigTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getAttributeConfigType()
     * @generated
     */
    int ATTRIBUTE_CONFIG_TYPE = 0;

    /**
     * The feature id for the '<em><b>Update Period</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_CONFIG_TYPE__UPDATE_PERIOD = 0;

    /**
     * The feature id for the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_CONFIG_TYPE__UNIT_CONVERSION = 1;

    /**
     * The number of structural features of the '<em>Attribute Config Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_CONFIG_TYPE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Attribute Config Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_CONFIG_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.DocumentRootImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getDocumentRoot()
     * @generated
     */
    int DOCUMENT_ROOT = 1;

    /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__MIXED = 0;

    /**
     * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

    /**
     * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

    /**
     * The feature id for the '<em><b>Attribute Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ATTRIBUTE_CONFIG = 3;

    /**
     * The feature id for the '<em><b>Interaction Class Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG = 4;

    /**
     * The feature id for the '<em><b>Linear Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LINEAR_CONVERSION = 5;

    /**
     * The feature id for the '<em><b>Name Conversion</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__NAME_CONVERSION = 6;

    /**
     * The feature id for the '<em><b>Object Class Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__OBJECT_CLASS_CONFIG = 7;

    /**
     * The feature id for the '<em><b>Parameter Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PARAMETER_CONFIG = 8;

    /**
     * The feature id for the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PUBLISHED_OBJECTS = 9;

    /**
     * The feature id for the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__UNIT_CONVERSION = 10;

    /**
     * The feature id for the '<em><b>Update Period</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__UPDATE_PERIOD = 11;

    /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_FEATURE_COUNT = 12;

    /**
     * The number of operations of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.InteractionClassConfigTypeImpl <em>Interaction Class Config Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.InteractionClassConfigTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getInteractionClassConfigType()
     * @generated
     */
    int INTERACTION_CLASS_CONFIG_TYPE = 2;

    /**
     * The feature id for the '<em><b>Update Period</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_CLASS_CONFIG_TYPE__UPDATE_PERIOD = 0;

    /**
     * The feature id for the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_CLASS_CONFIG_TYPE__PUBLISHED_OBJECTS = 1;

    /**
     * The number of structural features of the '<em>Interaction Class Config Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_CLASS_CONFIG_TYPE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Interaction Class Config Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_CLASS_CONFIG_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.LinearConversionTypeImpl <em>Linear Conversion Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.LinearConversionTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getLinearConversionType()
     * @generated
     */
    int LINEAR_CONVERSION_TYPE = 3;

    /**
     * The feature id for the '<em><b>Scale</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_CONVERSION_TYPE__SCALE = 0;

    /**
     * The feature id for the '<em><b>Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_CONVERSION_TYPE__OFFSET = 1;

    /**
     * The number of structural features of the '<em>Linear Conversion Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_CONVERSION_TYPE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Linear Conversion Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_CONVERSION_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.ObjectClassConfigTypeImpl <em>Object Class Config Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.ObjectClassConfigTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getObjectClassConfigType()
     * @generated
     */
    int OBJECT_CLASS_CONFIG_TYPE = 4;

    /**
     * The feature id for the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_CLASS_CONFIG_TYPE__PUBLISHED_OBJECTS = 0;

    /**
     * The number of structural features of the '<em>Object Class Config Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_CLASS_CONFIG_TYPE_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Object Class Config Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_CLASS_CONFIG_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.ParameterConfigTypeImpl <em>Parameter Config Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.ParameterConfigTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getParameterConfigType()
     * @generated
     */
    int PARAMETER_CONFIG_TYPE = 5;

    /**
     * The feature id for the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_CONFIG_TYPE__UNIT_CONVERSION = 0;

    /**
     * The number of structural features of the '<em>Parameter Config Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_CONFIG_TYPE_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Parameter Config Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_CONFIG_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.PublishedObjectsTypeImpl <em>Published Objects Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.PublishedObjectsTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getPublishedObjectsType()
     * @generated
     */
    int PUBLISHED_OBJECTS_TYPE = 6;

    /**
     * The feature id for the '<em><b>Object Name</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PUBLISHED_OBJECTS_TYPE__OBJECT_NAME = 0;

    /**
     * The number of structural features of the '<em>Published Objects Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PUBLISHED_OBJECTS_TYPE_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Published Objects Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PUBLISHED_OBJECTS_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.UnitConversionTypeImpl <em>Unit Conversion Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.UnitConversionTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUnitConversionType()
     * @generated
     */
    int UNIT_CONVERSION_TYPE = 7;

    /**
     * The feature id for the '<em><b>Name Conversion</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIT_CONVERSION_TYPE__NAME_CONVERSION = 0;

    /**
     * The feature id for the '<em><b>Linear Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIT_CONVERSION_TYPE__LINEAR_CONVERSION = 1;

    /**
     * The number of structural features of the '<em>Unit Conversion Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIT_CONVERSION_TYPE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Unit Conversion Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIT_CONVERSION_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '<em>Name Conversion Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getNameConversionType()
     * @generated
     */
    int NAME_CONVERSION_TYPE = 8;

    /**
     * The meta object id for the '<em>Update Period Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUpdatePeriodType()
     * @generated
     */
    int UPDATE_PERIOD_TYPE = 9;

    /**
     * The meta object id for the '<em>Update Period Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Double
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUpdatePeriodTypeObject()
     * @generated
     */
    int UPDATE_PERIOD_TYPE_OBJECT = 10;


    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.AttributeConfigType <em>Attribute Config Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Attribute Config Type</em>'.
     * @see gov.nist.pages.ucef.AttributeConfigType
     * @generated
     */
    EClass getAttributeConfigType();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.AttributeConfigType#getUpdatePeriod <em>Update Period</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Update Period</em>'.
     * @see gov.nist.pages.ucef.AttributeConfigType#getUpdatePeriod()
     * @see #getAttributeConfigType()
     * @generated
     */
    EAttribute getAttributeConfigType_UpdatePeriod();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.AttributeConfigType#getUnitConversion <em>Unit Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Unit Conversion</em>'.
     * @see gov.nist.pages.ucef.AttributeConfigType#getUnitConversion()
     * @see #getAttributeConfigType()
     * @generated
     */
    EReference getAttributeConfigType_UnitConversion();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Root</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot
     * @generated
     */
    EClass getDocumentRoot();

    /**
     * Returns the meta object for the attribute list '{@link gov.nist.pages.ucef.DocumentRoot#getMixed <em>Mixed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getMixed()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_Mixed();

    /**
     * Returns the meta object for the map '{@link gov.nist.pages.ucef.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getXMLNSPrefixMap()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XMLNSPrefixMap();

    /**
     * Returns the meta object for the map '{@link gov.nist.pages.ucef.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XSI Schema Location</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getXSISchemaLocation()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_XSISchemaLocation();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getAttributeConfig <em>Attribute Config</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Attribute Config</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getAttributeConfig()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_AttributeConfig();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getInteractionClassConfig <em>Interaction Class Config</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Interaction Class Config</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getInteractionClassConfig()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_InteractionClassConfig();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getLinearConversion <em>Linear Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Linear Conversion</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getLinearConversion()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_LinearConversion();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.DocumentRoot#getNameConversion <em>Name Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name Conversion</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getNameConversion()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_NameConversion();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getObjectClassConfig <em>Object Class Config</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Object Class Config</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getObjectClassConfig()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_ObjectClassConfig();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getParameterConfig <em>Parameter Config</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Parameter Config</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getParameterConfig()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_ParameterConfig();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getPublishedObjects <em>Published Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Published Objects</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getPublishedObjects()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_PublishedObjects();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getUnitConversion <em>Unit Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Unit Conversion</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getUnitConversion()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_UnitConversion();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.DocumentRoot#getUpdatePeriod <em>Update Period</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Update Period</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getUpdatePeriod()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_UpdatePeriod();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.InteractionClassConfigType <em>Interaction Class Config Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Interaction Class Config Type</em>'.
     * @see gov.nist.pages.ucef.InteractionClassConfigType
     * @generated
     */
    EClass getInteractionClassConfigType();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.InteractionClassConfigType#getUpdatePeriod <em>Update Period</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Update Period</em>'.
     * @see gov.nist.pages.ucef.InteractionClassConfigType#getUpdatePeriod()
     * @see #getInteractionClassConfigType()
     * @generated
     */
    EAttribute getInteractionClassConfigType_UpdatePeriod();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.InteractionClassConfigType#getPublishedObjects <em>Published Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Published Objects</em>'.
     * @see gov.nist.pages.ucef.InteractionClassConfigType#getPublishedObjects()
     * @see #getInteractionClassConfigType()
     * @generated
     */
    EReference getInteractionClassConfigType_PublishedObjects();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.LinearConversionType <em>Linear Conversion Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Linear Conversion Type</em>'.
     * @see gov.nist.pages.ucef.LinearConversionType
     * @generated
     */
    EClass getLinearConversionType();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.LinearConversionType#getScale <em>Scale</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Scale</em>'.
     * @see gov.nist.pages.ucef.LinearConversionType#getScale()
     * @see #getLinearConversionType()
     * @generated
     */
    EAttribute getLinearConversionType_Scale();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.LinearConversionType#getOffset <em>Offset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Offset</em>'.
     * @see gov.nist.pages.ucef.LinearConversionType#getOffset()
     * @see #getLinearConversionType()
     * @generated
     */
    EAttribute getLinearConversionType_Offset();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.ObjectClassConfigType <em>Object Class Config Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Object Class Config Type</em>'.
     * @see gov.nist.pages.ucef.ObjectClassConfigType
     * @generated
     */
    EClass getObjectClassConfigType();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.ObjectClassConfigType#getPublishedObjects <em>Published Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Published Objects</em>'.
     * @see gov.nist.pages.ucef.ObjectClassConfigType#getPublishedObjects()
     * @see #getObjectClassConfigType()
     * @generated
     */
    EReference getObjectClassConfigType_PublishedObjects();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.ParameterConfigType <em>Parameter Config Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Parameter Config Type</em>'.
     * @see gov.nist.pages.ucef.ParameterConfigType
     * @generated
     */
    EClass getParameterConfigType();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.ParameterConfigType#getUnitConversion <em>Unit Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Unit Conversion</em>'.
     * @see gov.nist.pages.ucef.ParameterConfigType#getUnitConversion()
     * @see #getParameterConfigType()
     * @generated
     */
    EReference getParameterConfigType_UnitConversion();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.PublishedObjectsType <em>Published Objects Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Published Objects Type</em>'.
     * @see gov.nist.pages.ucef.PublishedObjectsType
     * @generated
     */
    EClass getPublishedObjectsType();

    /**
     * Returns the meta object for the attribute list '{@link gov.nist.pages.ucef.PublishedObjectsType#getObjectName <em>Object Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Object Name</em>'.
     * @see gov.nist.pages.ucef.PublishedObjectsType#getObjectName()
     * @see #getPublishedObjectsType()
     * @generated
     */
    EAttribute getPublishedObjectsType_ObjectName();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.UnitConversionType <em>Unit Conversion Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Unit Conversion Type</em>'.
     * @see gov.nist.pages.ucef.UnitConversionType
     * @generated
     */
    EClass getUnitConversionType();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.UnitConversionType#getNameConversion <em>Name Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name Conversion</em>'.
     * @see gov.nist.pages.ucef.UnitConversionType#getNameConversion()
     * @see #getUnitConversionType()
     * @generated
     */
    EAttribute getUnitConversionType_NameConversion();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.UnitConversionType#getLinearConversion <em>Linear Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Linear Conversion</em>'.
     * @see gov.nist.pages.ucef.UnitConversionType#getLinearConversion()
     * @see #getUnitConversionType()
     * @generated
     */
    EReference getUnitConversionType_LinearConversion();

    /**
     * Returns the meta object for data type '{@link java.lang.String <em>Name Conversion Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Name Conversion Type</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     *        extendedMetaData="name='nameConversionType' baseType='http://www.eclipse.org/emf/2003/XMLType#string' whiteSpace='collapse' minLength='1'"
     * @generated
     */
    EDataType getNameConversionType();

    /**
     * Returns the meta object for data type '<em>Update Period Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Update Period Type</em>'.
     * @model instanceClass="double"
     *        extendedMetaData="name='updatePeriodType' baseType='http://www.eclipse.org/emf/2003/XMLType#double' minExclusive='0'"
     * @generated
     */
    EDataType getUpdatePeriodType();

    /**
     * Returns the meta object for data type '{@link java.lang.Double <em>Update Period Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Update Period Type Object</em>'.
     * @see java.lang.Double
     * @model instanceClass="java.lang.Double"
     *        extendedMetaData="name='updatePeriodType:Object' baseType='updatePeriodType'"
     * @generated
     */
    EDataType getUpdatePeriodTypeObject();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ucefFactory getucefFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.AttributeConfigTypeImpl <em>Attribute Config Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.AttributeConfigTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getAttributeConfigType()
         * @generated
         */
        EClass ATTRIBUTE_CONFIG_TYPE = eINSTANCE.getAttributeConfigType();

        /**
         * The meta object literal for the '<em><b>Update Period</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ATTRIBUTE_CONFIG_TYPE__UPDATE_PERIOD = eINSTANCE.getAttributeConfigType_UpdatePeriod();

        /**
         * The meta object literal for the '<em><b>Unit Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ATTRIBUTE_CONFIG_TYPE__UNIT_CONVERSION = eINSTANCE.getAttributeConfigType_UnitConversion();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.DocumentRootImpl <em>Document Root</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.DocumentRootImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getDocumentRoot()
         * @generated
         */
        EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

        /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

        /**
         * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

        /**
         * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

        /**
         * The meta object literal for the '<em><b>Attribute Config</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__ATTRIBUTE_CONFIG = eINSTANCE.getDocumentRoot_AttributeConfig();

        /**
         * The meta object literal for the '<em><b>Interaction Class Config</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG = eINSTANCE.getDocumentRoot_InteractionClassConfig();

        /**
         * The meta object literal for the '<em><b>Linear Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LINEAR_CONVERSION = eINSTANCE.getDocumentRoot_LinearConversion();

        /**
         * The meta object literal for the '<em><b>Name Conversion</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__NAME_CONVERSION = eINSTANCE.getDocumentRoot_NameConversion();

        /**
         * The meta object literal for the '<em><b>Object Class Config</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__OBJECT_CLASS_CONFIG = eINSTANCE.getDocumentRoot_ObjectClassConfig();

        /**
         * The meta object literal for the '<em><b>Parameter Config</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__PARAMETER_CONFIG = eINSTANCE.getDocumentRoot_ParameterConfig();

        /**
         * The meta object literal for the '<em><b>Published Objects</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__PUBLISHED_OBJECTS = eINSTANCE.getDocumentRoot_PublishedObjects();

        /**
         * The meta object literal for the '<em><b>Unit Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__UNIT_CONVERSION = eINSTANCE.getDocumentRoot_UnitConversion();

        /**
         * The meta object literal for the '<em><b>Update Period</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__UPDATE_PERIOD = eINSTANCE.getDocumentRoot_UpdatePeriod();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.InteractionClassConfigTypeImpl <em>Interaction Class Config Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.InteractionClassConfigTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getInteractionClassConfigType()
         * @generated
         */
        EClass INTERACTION_CLASS_CONFIG_TYPE = eINSTANCE.getInteractionClassConfigType();

        /**
         * The meta object literal for the '<em><b>Update Period</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INTERACTION_CLASS_CONFIG_TYPE__UPDATE_PERIOD = eINSTANCE.getInteractionClassConfigType_UpdatePeriod();

        /**
         * The meta object literal for the '<em><b>Published Objects</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION_CLASS_CONFIG_TYPE__PUBLISHED_OBJECTS = eINSTANCE.getInteractionClassConfigType_PublishedObjects();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.LinearConversionTypeImpl <em>Linear Conversion Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.LinearConversionTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getLinearConversionType()
         * @generated
         */
        EClass LINEAR_CONVERSION_TYPE = eINSTANCE.getLinearConversionType();

        /**
         * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINEAR_CONVERSION_TYPE__SCALE = eINSTANCE.getLinearConversionType_Scale();

        /**
         * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINEAR_CONVERSION_TYPE__OFFSET = eINSTANCE.getLinearConversionType_Offset();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.ObjectClassConfigTypeImpl <em>Object Class Config Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.ObjectClassConfigTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getObjectClassConfigType()
         * @generated
         */
        EClass OBJECT_CLASS_CONFIG_TYPE = eINSTANCE.getObjectClassConfigType();

        /**
         * The meta object literal for the '<em><b>Published Objects</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference OBJECT_CLASS_CONFIG_TYPE__PUBLISHED_OBJECTS = eINSTANCE.getObjectClassConfigType_PublishedObjects();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.ParameterConfigTypeImpl <em>Parameter Config Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.ParameterConfigTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getParameterConfigType()
         * @generated
         */
        EClass PARAMETER_CONFIG_TYPE = eINSTANCE.getParameterConfigType();

        /**
         * The meta object literal for the '<em><b>Unit Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PARAMETER_CONFIG_TYPE__UNIT_CONVERSION = eINSTANCE.getParameterConfigType_UnitConversion();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.PublishedObjectsTypeImpl <em>Published Objects Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.PublishedObjectsTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getPublishedObjectsType()
         * @generated
         */
        EClass PUBLISHED_OBJECTS_TYPE = eINSTANCE.getPublishedObjectsType();

        /**
         * The meta object literal for the '<em><b>Object Name</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PUBLISHED_OBJECTS_TYPE__OBJECT_NAME = eINSTANCE.getPublishedObjectsType_ObjectName();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.UnitConversionTypeImpl <em>Unit Conversion Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.UnitConversionTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUnitConversionType()
         * @generated
         */
        EClass UNIT_CONVERSION_TYPE = eINSTANCE.getUnitConversionType();

        /**
         * The meta object literal for the '<em><b>Name Conversion</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute UNIT_CONVERSION_TYPE__NAME_CONVERSION = eINSTANCE.getUnitConversionType_NameConversion();

        /**
         * The meta object literal for the '<em><b>Linear Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference UNIT_CONVERSION_TYPE__LINEAR_CONVERSION = eINSTANCE.getUnitConversionType_LinearConversion();

        /**
         * The meta object literal for the '<em>Name Conversion Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.lang.String
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getNameConversionType()
         * @generated
         */
        EDataType NAME_CONVERSION_TYPE = eINSTANCE.getNameConversionType();

        /**
         * The meta object literal for the '<em>Update Period Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUpdatePeriodType()
         * @generated
         */
        EDataType UPDATE_PERIOD_TYPE = eINSTANCE.getUpdatePeriodType();

        /**
         * The meta object literal for the '<em>Update Period Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.lang.Double
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUpdatePeriodTypeObject()
         * @generated
         */
        EDataType UPDATE_PERIOD_TYPE_OBJECT = eINSTANCE.getUpdatePeriodTypeObject();

    }

} //ucefPackage

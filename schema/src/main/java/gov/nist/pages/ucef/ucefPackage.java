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
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.AttributeDetailsTypeImpl <em>Attribute Details Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.AttributeDetailsTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getAttributeDetailsType()
     * @generated
     */
    int ATTRIBUTE_DETAILS_TYPE = 0;

    /**
     * The feature id for the '<em><b>Property Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_DETAILS_TYPE__PROPERTY_NAME = 0;

    /**
     * The feature id for the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION = 1;

    /**
     * The feature id for the '<em><b>Update Period</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_DETAILS_TYPE__UPDATE_PERIOD = 2;

    /**
     * The number of structural features of the '<em>Attribute Details Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_DETAILS_TYPE_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Attribute Details Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_DETAILS_TYPE_OPERATION_COUNT = 0;

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
     * The feature id for the '<em><b>Attribute Details</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__ATTRIBUTE_DETAILS = 3;

    /**
     * The feature id for the '<em><b>Ignored</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__IGNORED = 4;

    /**
     * The feature id for the '<em><b>Interaction Details</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__INTERACTION_DETAILS = 5;

    /**
     * The feature id for the '<em><b>Linear Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__LINEAR_CONVERSION = 6;

    /**
     * The feature id for the '<em><b>Object Details</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__OBJECT_DETAILS = 7;

    /**
     * The feature id for the '<em><b>Parameter Details</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PARAMETER_DETAILS = 8;

    /**
     * The feature id for the '<em><b>Property Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PROPERTY_NAME = 9;

    /**
     * The feature id for the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__PUBLISHED_OBJECTS = 10;

    /**
     * The feature id for the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__UNIT_CONVERSION = 11;

    /**
     * The feature id for the '<em><b>Unit Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__UNIT_NAME = 12;

    /**
     * The feature id for the '<em><b>Update Period</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT__UPDATE_PERIOD = 13;

    /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_FEATURE_COUNT = 14;

    /**
     * The number of operations of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_ROOT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.IgnoredTypeImpl <em>Ignored Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.IgnoredTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getIgnoredType()
     * @generated
     */
    int IGNORED_TYPE = 2;

    /**
     * The number of structural features of the '<em>Ignored Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IGNORED_TYPE_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Ignored Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IGNORED_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.InteractionDetailsTypeImpl <em>Interaction Details Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.InteractionDetailsTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getInteractionDetailsType()
     * @generated
     */
    int INTERACTION_DETAILS_TYPE = 3;

    /**
     * The feature id for the '<em><b>Update Period</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_DETAILS_TYPE__UPDATE_PERIOD = 0;

    /**
     * The feature id for the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS = 1;

    /**
     * The number of structural features of the '<em>Interaction Details Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_DETAILS_TYPE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Interaction Details Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTERACTION_DETAILS_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.LinearConversionTypeImpl <em>Linear Conversion Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.LinearConversionTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getLinearConversionType()
     * @generated
     */
    int LINEAR_CONVERSION_TYPE = 4;

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
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.ObjectDetailsTypeImpl <em>Object Details Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.ObjectDetailsTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getObjectDetailsType()
     * @generated
     */
    int OBJECT_DETAILS_TYPE = 5;

    /**
     * The feature id for the '<em><b>Published Objects</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS = 0;

    /**
     * The number of structural features of the '<em>Object Details Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_DETAILS_TYPE_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Object Details Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OBJECT_DETAILS_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.ParameterDetailsTypeImpl <em>Parameter Details Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.ParameterDetailsTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getParameterDetailsType()
     * @generated
     */
    int PARAMETER_DETAILS_TYPE = 6;

    /**
     * The feature id for the '<em><b>Property Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_DETAILS_TYPE__PROPERTY_NAME = 0;

    /**
     * The feature id for the '<em><b>Unit Conversion</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_DETAILS_TYPE__UNIT_CONVERSION = 1;

    /**
     * The number of structural features of the '<em>Parameter Details Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_DETAILS_TYPE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Parameter Details Type</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PARAMETER_DETAILS_TYPE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link gov.nist.pages.ucef.impl.PublishedObjectsTypeImpl <em>Published Objects Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.PublishedObjectsTypeImpl
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getPublishedObjectsType()
     * @generated
     */
    int PUBLISHED_OBJECTS_TYPE = 7;

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
    int UNIT_CONVERSION_TYPE = 8;

    /**
     * The feature id for the '<em><b>Unit Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNIT_CONVERSION_TYPE__UNIT_NAME = 0;

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
     * The meta object id for the '<em>Property Name Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getPropertyNameType()
     * @generated
     */
    int PROPERTY_NAME_TYPE = 9;

    /**
     * The meta object id for the '<em>Unit Name Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.String
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUnitNameType()
     * @generated
     */
    int UNIT_NAME_TYPE = 10;

    /**
     * The meta object id for the '<em>Update Period Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUpdatePeriodType()
     * @generated
     */
    int UPDATE_PERIOD_TYPE = 11;

    /**
     * The meta object id for the '<em>Update Period Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Double
     * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUpdatePeriodTypeObject()
     * @generated
     */
    int UPDATE_PERIOD_TYPE_OBJECT = 12;


    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.AttributeDetailsType <em>Attribute Details Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Attribute Details Type</em>'.
     * @see gov.nist.pages.ucef.AttributeDetailsType
     * @generated
     */
    EClass getAttributeDetailsType();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.AttributeDetailsType#getPropertyName <em>Property Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Property Name</em>'.
     * @see gov.nist.pages.ucef.AttributeDetailsType#getPropertyName()
     * @see #getAttributeDetailsType()
     * @generated
     */
    EAttribute getAttributeDetailsType_PropertyName();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.AttributeDetailsType#getUnitConversion <em>Unit Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Unit Conversion</em>'.
     * @see gov.nist.pages.ucef.AttributeDetailsType#getUnitConversion()
     * @see #getAttributeDetailsType()
     * @generated
     */
    EReference getAttributeDetailsType_UnitConversion();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.AttributeDetailsType#getUpdatePeriod <em>Update Period</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Update Period</em>'.
     * @see gov.nist.pages.ucef.AttributeDetailsType#getUpdatePeriod()
     * @see #getAttributeDetailsType()
     * @generated
     */
    EAttribute getAttributeDetailsType_UpdatePeriod();

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
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getAttributeDetails <em>Attribute Details</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Attribute Details</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getAttributeDetails()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_AttributeDetails();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getIgnored <em>Ignored</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Ignored</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getIgnored()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_Ignored();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getInteractionDetails <em>Interaction Details</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Interaction Details</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getInteractionDetails()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_InteractionDetails();

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
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getObjectDetails <em>Object Details</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Object Details</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getObjectDetails()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_ObjectDetails();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.DocumentRoot#getParameterDetails <em>Parameter Details</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Parameter Details</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getParameterDetails()
     * @see #getDocumentRoot()
     * @generated
     */
    EReference getDocumentRoot_ParameterDetails();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.DocumentRoot#getPropertyName <em>Property Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Property Name</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getPropertyName()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_PropertyName();

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
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.DocumentRoot#getUnitName <em>Unit Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Unit Name</em>'.
     * @see gov.nist.pages.ucef.DocumentRoot#getUnitName()
     * @see #getDocumentRoot()
     * @generated
     */
    EAttribute getDocumentRoot_UnitName();

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
     * Returns the meta object for class '{@link gov.nist.pages.ucef.IgnoredType <em>Ignored Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Ignored Type</em>'.
     * @see gov.nist.pages.ucef.IgnoredType
     * @generated
     */
    EClass getIgnoredType();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.InteractionDetailsType <em>Interaction Details Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Interaction Details Type</em>'.
     * @see gov.nist.pages.ucef.InteractionDetailsType
     * @generated
     */
    EClass getInteractionDetailsType();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.InteractionDetailsType#getUpdatePeriod <em>Update Period</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Update Period</em>'.
     * @see gov.nist.pages.ucef.InteractionDetailsType#getUpdatePeriod()
     * @see #getInteractionDetailsType()
     * @generated
     */
    EAttribute getInteractionDetailsType_UpdatePeriod();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.InteractionDetailsType#getPublishedObjects <em>Published Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Published Objects</em>'.
     * @see gov.nist.pages.ucef.InteractionDetailsType#getPublishedObjects()
     * @see #getInteractionDetailsType()
     * @generated
     */
    EReference getInteractionDetailsType_PublishedObjects();

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
     * Returns the meta object for class '{@link gov.nist.pages.ucef.ObjectDetailsType <em>Object Details Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Object Details Type</em>'.
     * @see gov.nist.pages.ucef.ObjectDetailsType
     * @generated
     */
    EClass getObjectDetailsType();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.ObjectDetailsType#getPublishedObjects <em>Published Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Published Objects</em>'.
     * @see gov.nist.pages.ucef.ObjectDetailsType#getPublishedObjects()
     * @see #getObjectDetailsType()
     * @generated
     */
    EReference getObjectDetailsType_PublishedObjects();

    /**
     * Returns the meta object for class '{@link gov.nist.pages.ucef.ParameterDetailsType <em>Parameter Details Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Parameter Details Type</em>'.
     * @see gov.nist.pages.ucef.ParameterDetailsType
     * @generated
     */
    EClass getParameterDetailsType();

    /**
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.ParameterDetailsType#getPropertyName <em>Property Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Property Name</em>'.
     * @see gov.nist.pages.ucef.ParameterDetailsType#getPropertyName()
     * @see #getParameterDetailsType()
     * @generated
     */
    EAttribute getParameterDetailsType_PropertyName();

    /**
     * Returns the meta object for the containment reference '{@link gov.nist.pages.ucef.ParameterDetailsType#getUnitConversion <em>Unit Conversion</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Unit Conversion</em>'.
     * @see gov.nist.pages.ucef.ParameterDetailsType#getUnitConversion()
     * @see #getParameterDetailsType()
     * @generated
     */
    EReference getParameterDetailsType_UnitConversion();

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
     * Returns the meta object for the attribute '{@link gov.nist.pages.ucef.UnitConversionType#getUnitName <em>Unit Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Unit Name</em>'.
     * @see gov.nist.pages.ucef.UnitConversionType#getUnitName()
     * @see #getUnitConversionType()
     * @generated
     */
    EAttribute getUnitConversionType_UnitName();

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
     * Returns the meta object for data type '{@link java.lang.String <em>Property Name Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Property Name Type</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     *        extendedMetaData="name='propertyNameType' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='[a-zA-Z0-9_.]+'"
     * @generated
     */
    EDataType getPropertyNameType();

    /**
     * Returns the meta object for data type '{@link java.lang.String <em>Unit Name Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Unit Name Type</em>'.
     * @see java.lang.String
     * @model instanceClass="java.lang.String"
     *        extendedMetaData="name='unitNameType' baseType='http://www.eclipse.org/emf/2003/XMLType#string' whiteSpace='collapse' minLength='1'"
     * @generated
     */
    EDataType getUnitNameType();

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
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.AttributeDetailsTypeImpl <em>Attribute Details Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.AttributeDetailsTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getAttributeDetailsType()
         * @generated
         */
        EClass ATTRIBUTE_DETAILS_TYPE = eINSTANCE.getAttributeDetailsType();

        /**
         * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ATTRIBUTE_DETAILS_TYPE__PROPERTY_NAME = eINSTANCE.getAttributeDetailsType_PropertyName();

        /**
         * The meta object literal for the '<em><b>Unit Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ATTRIBUTE_DETAILS_TYPE__UNIT_CONVERSION = eINSTANCE.getAttributeDetailsType_UnitConversion();

        /**
         * The meta object literal for the '<em><b>Update Period</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ATTRIBUTE_DETAILS_TYPE__UPDATE_PERIOD = eINSTANCE.getAttributeDetailsType_UpdatePeriod();

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
         * The meta object literal for the '<em><b>Attribute Details</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__ATTRIBUTE_DETAILS = eINSTANCE.getDocumentRoot_AttributeDetails();

        /**
         * The meta object literal for the '<em><b>Ignored</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__IGNORED = eINSTANCE.getDocumentRoot_Ignored();

        /**
         * The meta object literal for the '<em><b>Interaction Details</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__INTERACTION_DETAILS = eINSTANCE.getDocumentRoot_InteractionDetails();

        /**
         * The meta object literal for the '<em><b>Linear Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__LINEAR_CONVERSION = eINSTANCE.getDocumentRoot_LinearConversion();

        /**
         * The meta object literal for the '<em><b>Object Details</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__OBJECT_DETAILS = eINSTANCE.getDocumentRoot_ObjectDetails();

        /**
         * The meta object literal for the '<em><b>Parameter Details</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOCUMENT_ROOT__PARAMETER_DETAILS = eINSTANCE.getDocumentRoot_ParameterDetails();

        /**
         * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__PROPERTY_NAME = eINSTANCE.getDocumentRoot_PropertyName();

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
         * The meta object literal for the '<em><b>Unit Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__UNIT_NAME = eINSTANCE.getDocumentRoot_UnitName();

        /**
         * The meta object literal for the '<em><b>Update Period</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_ROOT__UPDATE_PERIOD = eINSTANCE.getDocumentRoot_UpdatePeriod();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.IgnoredTypeImpl <em>Ignored Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.IgnoredTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getIgnoredType()
         * @generated
         */
        EClass IGNORED_TYPE = eINSTANCE.getIgnoredType();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.InteractionDetailsTypeImpl <em>Interaction Details Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.InteractionDetailsTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getInteractionDetailsType()
         * @generated
         */
        EClass INTERACTION_DETAILS_TYPE = eINSTANCE.getInteractionDetailsType();

        /**
         * The meta object literal for the '<em><b>Update Period</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INTERACTION_DETAILS_TYPE__UPDATE_PERIOD = eINSTANCE.getInteractionDetailsType_UpdatePeriod();

        /**
         * The meta object literal for the '<em><b>Published Objects</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INTERACTION_DETAILS_TYPE__PUBLISHED_OBJECTS = eINSTANCE.getInteractionDetailsType_PublishedObjects();

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
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.ObjectDetailsTypeImpl <em>Object Details Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.ObjectDetailsTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getObjectDetailsType()
         * @generated
         */
        EClass OBJECT_DETAILS_TYPE = eINSTANCE.getObjectDetailsType();

        /**
         * The meta object literal for the '<em><b>Published Objects</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference OBJECT_DETAILS_TYPE__PUBLISHED_OBJECTS = eINSTANCE.getObjectDetailsType_PublishedObjects();

        /**
         * The meta object literal for the '{@link gov.nist.pages.ucef.impl.ParameterDetailsTypeImpl <em>Parameter Details Type</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see gov.nist.pages.ucef.impl.ParameterDetailsTypeImpl
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getParameterDetailsType()
         * @generated
         */
        EClass PARAMETER_DETAILS_TYPE = eINSTANCE.getParameterDetailsType();

        /**
         * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PARAMETER_DETAILS_TYPE__PROPERTY_NAME = eINSTANCE.getParameterDetailsType_PropertyName();

        /**
         * The meta object literal for the '<em><b>Unit Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PARAMETER_DETAILS_TYPE__UNIT_CONVERSION = eINSTANCE.getParameterDetailsType_UnitConversion();

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
         * The meta object literal for the '<em><b>Unit Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute UNIT_CONVERSION_TYPE__UNIT_NAME = eINSTANCE.getUnitConversionType_UnitName();

        /**
         * The meta object literal for the '<em><b>Linear Conversion</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference UNIT_CONVERSION_TYPE__LINEAR_CONVERSION = eINSTANCE.getUnitConversionType_LinearConversion();

        /**
         * The meta object literal for the '<em>Property Name Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.lang.String
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getPropertyNameType()
         * @generated
         */
        EDataType PROPERTY_NAME_TYPE = eINSTANCE.getPropertyNameType();

        /**
         * The meta object literal for the '<em>Unit Name Type</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see java.lang.String
         * @see gov.nist.pages.ucef.impl.ucefPackageImpl#getUnitNameType()
         * @generated
         */
        EDataType UNIT_NAME_TYPE = eINSTANCE.getUnitNameType();

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

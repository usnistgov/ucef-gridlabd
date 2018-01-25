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
import gov.nist.pages.ucef.ucefFactory;
import gov.nist.pages.ucef.ucefPackage;

import gov.nist.pages.ucef.util.ucefValidator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ucefPackageImpl extends EPackageImpl implements ucefPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass attributeConfigTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass interactionClassConfigTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass linearConversionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass objectClassConfigTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass parameterConfigTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass publishedObjectsTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass unitConversionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType nameConversionTypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType updatePeriodTypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType updatePeriodTypeObjectEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see gov.nist.pages.ucef.ucefPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ucefPackageImpl() {
        super(eNS_URI, ucefFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link ucefPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ucefPackage init() {
        if (isInited) return (ucefPackage)EPackage.Registry.INSTANCE.getEPackage(ucefPackage.eNS_URI);

        // Obtain or create and register package
        ucefPackageImpl theucefPackage = (ucefPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ucefPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ucefPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theucefPackage.createPackageContents();

        // Initialize created meta-data
        theucefPackage.initializePackageContents();

        // Register package validator
        EValidator.Registry.INSTANCE.put
            (theucefPackage, 
             new EValidator.Descriptor() {
                 public EValidator getEValidator() {
                     return ucefValidator.INSTANCE;
                 }
             });

        // Mark meta-data to indicate it can't be changed
        theucefPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ucefPackage.eNS_URI, theucefPackage);
        return theucefPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAttributeConfigType() {
        return attributeConfigTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAttributeConfigType_UpdatePeriod() {
        return (EAttribute)attributeConfigTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAttributeConfigType_UnitConversion() {
        return (EReference)attributeConfigTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentRoot() {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_AttributeConfig() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_InteractionClassConfig() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LinearConversion() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_NameConversion() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ObjectClassConfig() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_ParameterConfig() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_PublishedObjects() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_UnitConversion() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_UpdatePeriod() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getInteractionClassConfigType() {
        return interactionClassConfigTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getInteractionClassConfigType_UpdatePeriod() {
        return (EAttribute)interactionClassConfigTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getInteractionClassConfigType_PublishedObjects() {
        return (EReference)interactionClassConfigTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLinearConversionType() {
        return linearConversionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLinearConversionType_Scale() {
        return (EAttribute)linearConversionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLinearConversionType_Offset() {
        return (EAttribute)linearConversionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getObjectClassConfigType() {
        return objectClassConfigTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getObjectClassConfigType_PublishedObjects() {
        return (EReference)objectClassConfigTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getParameterConfigType() {
        return parameterConfigTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getParameterConfigType_UnitConversion() {
        return (EReference)parameterConfigTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPublishedObjectsType() {
        return publishedObjectsTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPublishedObjectsType_ObjectName() {
        return (EAttribute)publishedObjectsTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUnitConversionType() {
        return unitConversionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUnitConversionType_NameConversion() {
        return (EAttribute)unitConversionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getUnitConversionType_LinearConversion() {
        return (EReference)unitConversionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getNameConversionType() {
        return nameConversionTypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getUpdatePeriodType() {
        return updatePeriodTypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getUpdatePeriodTypeObject() {
        return updatePeriodTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ucefFactory getucefFactory() {
        return (ucefFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        attributeConfigTypeEClass = createEClass(ATTRIBUTE_CONFIG_TYPE);
        createEAttribute(attributeConfigTypeEClass, ATTRIBUTE_CONFIG_TYPE__UPDATE_PERIOD);
        createEReference(attributeConfigTypeEClass, ATTRIBUTE_CONFIG_TYPE__UNIT_CONVERSION);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTE_CONFIG);
        createEReference(documentRootEClass, DOCUMENT_ROOT__INTERACTION_CLASS_CONFIG);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LINEAR_CONVERSION);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__NAME_CONVERSION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__OBJECT_CLASS_CONFIG);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PARAMETER_CONFIG);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PUBLISHED_OBJECTS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__UNIT_CONVERSION);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__UPDATE_PERIOD);

        interactionClassConfigTypeEClass = createEClass(INTERACTION_CLASS_CONFIG_TYPE);
        createEAttribute(interactionClassConfigTypeEClass, INTERACTION_CLASS_CONFIG_TYPE__UPDATE_PERIOD);
        createEReference(interactionClassConfigTypeEClass, INTERACTION_CLASS_CONFIG_TYPE__PUBLISHED_OBJECTS);

        linearConversionTypeEClass = createEClass(LINEAR_CONVERSION_TYPE);
        createEAttribute(linearConversionTypeEClass, LINEAR_CONVERSION_TYPE__SCALE);
        createEAttribute(linearConversionTypeEClass, LINEAR_CONVERSION_TYPE__OFFSET);

        objectClassConfigTypeEClass = createEClass(OBJECT_CLASS_CONFIG_TYPE);
        createEReference(objectClassConfigTypeEClass, OBJECT_CLASS_CONFIG_TYPE__PUBLISHED_OBJECTS);

        parameterConfigTypeEClass = createEClass(PARAMETER_CONFIG_TYPE);
        createEReference(parameterConfigTypeEClass, PARAMETER_CONFIG_TYPE__UNIT_CONVERSION);

        publishedObjectsTypeEClass = createEClass(PUBLISHED_OBJECTS_TYPE);
        createEAttribute(publishedObjectsTypeEClass, PUBLISHED_OBJECTS_TYPE__OBJECT_NAME);

        unitConversionTypeEClass = createEClass(UNIT_CONVERSION_TYPE);
        createEAttribute(unitConversionTypeEClass, UNIT_CONVERSION_TYPE__NAME_CONVERSION);
        createEReference(unitConversionTypeEClass, UNIT_CONVERSION_TYPE__LINEAR_CONVERSION);

        // Create data types
        nameConversionTypeEDataType = createEDataType(NAME_CONVERSION_TYPE);
        updatePeriodTypeEDataType = createEDataType(UPDATE_PERIOD_TYPE);
        updatePeriodTypeObjectEDataType = createEDataType(UPDATE_PERIOD_TYPE_OBJECT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(attributeConfigTypeEClass, AttributeConfigType.class, "AttributeConfigType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAttributeConfigType_UpdatePeriod(), this.getUpdatePeriodType(), "updatePeriod", null, 0, 1, AttributeConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAttributeConfigType_UnitConversion(), this.getUnitConversionType(), null, "unitConversion", null, 0, 1, AttributeConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_AttributeConfig(), this.getAttributeConfigType(), null, "attributeConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_InteractionClassConfig(), this.getInteractionClassConfigType(), null, "interactionClassConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_LinearConversion(), this.getLinearConversionType(), null, "linearConversion", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentRoot_NameConversion(), this.getNameConversionType(), "nameConversion", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_ObjectClassConfig(), this.getObjectClassConfigType(), null, "objectClassConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_ParameterConfig(), this.getParameterConfigType(), null, "parameterConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_PublishedObjects(), this.getPublishedObjectsType(), null, "publishedObjects", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_UnitConversion(), this.getUnitConversionType(), null, "unitConversion", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentRoot_UpdatePeriod(), this.getUpdatePeriodType(), "updatePeriod", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(interactionClassConfigTypeEClass, InteractionClassConfigType.class, "InteractionClassConfigType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getInteractionClassConfigType_UpdatePeriod(), this.getUpdatePeriodType(), "updatePeriod", null, 0, 1, InteractionClassConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteractionClassConfigType_PublishedObjects(), this.getPublishedObjectsType(), null, "publishedObjects", null, 0, 1, InteractionClassConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(linearConversionTypeEClass, LinearConversionType.class, "LinearConversionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLinearConversionType_Scale(), theXMLTypePackage.getDouble(), "scale", null, 1, 1, LinearConversionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLinearConversionType_Offset(), theXMLTypePackage.getDouble(), "offset", null, 1, 1, LinearConversionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(objectClassConfigTypeEClass, ObjectClassConfigType.class, "ObjectClassConfigType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getObjectClassConfigType_PublishedObjects(), this.getPublishedObjectsType(), null, "publishedObjects", null, 0, 1, ObjectClassConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(parameterConfigTypeEClass, ParameterConfigType.class, "ParameterConfigType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getParameterConfigType_UnitConversion(), this.getUnitConversionType(), null, "unitConversion", null, 0, 1, ParameterConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(publishedObjectsTypeEClass, PublishedObjectsType.class, "PublishedObjectsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPublishedObjectsType_ObjectName(), theXMLTypePackage.getString(), "objectName", null, 1, -1, PublishedObjectsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(unitConversionTypeEClass, UnitConversionType.class, "UnitConversionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUnitConversionType_NameConversion(), this.getNameConversionType(), "nameConversion", null, 0, 1, UnitConversionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUnitConversionType_LinearConversion(), this.getLinearConversionType(), null, "linearConversion", null, 0, 1, UnitConversionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize data types
        initEDataType(nameConversionTypeEDataType, String.class, "NameConversionType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(updatePeriodTypeEDataType, double.class, "UpdatePeriodType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(updatePeriodTypeObjectEDataType, Double.class, "UpdatePeriodTypeObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
        addAnnotation
          (attributeConfigTypeEClass, 
           source, 
           new String[] {
             "name", "attributeConfigType",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getAttributeConfigType_UpdatePeriod(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "updatePeriod",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getAttributeConfigType_UnitConversion(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "unitConversion",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (documentRootEClass, 
           source, 
           new String[] {
             "name", "",
             "kind", "mixed"
           });	
        addAnnotation
          (getDocumentRoot_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "name", ":mixed"
           });	
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "xmlns:prefix"
           });	
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "xsi:schemaLocation"
           });	
        addAnnotation
          (getDocumentRoot_AttributeConfig(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "attributeConfig",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_InteractionClassConfig(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "interactionClassConfig",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_LinearConversion(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "linearConversion",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_NameConversion(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "nameConversion",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_ObjectClassConfig(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "objectClassConfig",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_ParameterConfig(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "parameterConfig",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_PublishedObjects(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "publishedObjects",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_UnitConversion(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "unitConversion",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getDocumentRoot_UpdatePeriod(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "updatePeriod",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (interactionClassConfigTypeEClass, 
           source, 
           new String[] {
             "name", "interactionClassConfigType",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getInteractionClassConfigType_UpdatePeriod(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "updatePeriod",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getInteractionClassConfigType_PublishedObjects(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "publishedObjects",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (linearConversionTypeEClass, 
           source, 
           new String[] {
             "name", "linearConversionType",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getLinearConversionType_Scale(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "scale",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getLinearConversionType_Offset(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "offset",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (nameConversionTypeEDataType, 
           source, 
           new String[] {
             "name", "nameConversionType",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
             "whiteSpace", "collapse",
             "minLength", "1"
           });	
        addAnnotation
          (objectClassConfigTypeEClass, 
           source, 
           new String[] {
             "name", "objectClassConfigType",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getObjectClassConfigType_PublishedObjects(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "publishedObjects",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (parameterConfigTypeEClass, 
           source, 
           new String[] {
             "name", "parameterConfigType",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getParameterConfigType_UnitConversion(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "unitConversion",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (publishedObjectsTypeEClass, 
           source, 
           new String[] {
             "name", "publishedObjectsType",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getPublishedObjectsType_ObjectName(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "objectName",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (unitConversionTypeEClass, 
           source, 
           new String[] {
             "name", "unitConversionType",
             "kind", "elementOnly"
           });	
        addAnnotation
          (getUnitConversionType_NameConversion(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "nameConversion",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (getUnitConversionType_LinearConversion(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "linearConversion",
             "namespace", "##targetNamespace"
           });	
        addAnnotation
          (updatePeriodTypeEDataType, 
           source, 
           new String[] {
             "name", "updatePeriodType",
             "baseType", "http://www.eclipse.org/emf/2003/XMLType#double",
             "minExclusive", "0"
           });	
        addAnnotation
          (updatePeriodTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "updatePeriodType:Object",
             "baseType", "updatePeriodType"
           });
    }

} //ucefPackageImpl

/**
 */
package gov.nist.pages.ucef.util;

import gov.nist.pages.ucef.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see gov.nist.pages.ucef.ucefPackage
 * @generated
 */
public class ucefAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ucefPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ucefAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ucefPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ucefSwitch<Adapter> modelSwitch =
        new ucefSwitch<Adapter>() {
            @Override
            public Adapter caseAttributeConfigType(AttributeConfigType object) {
                return createAttributeConfigTypeAdapter();
            }
            @Override
            public Adapter caseDocumentRoot(DocumentRoot object) {
                return createDocumentRootAdapter();
            }
            @Override
            public Adapter caseInteractionClassConfigType(InteractionClassConfigType object) {
                return createInteractionClassConfigTypeAdapter();
            }
            @Override
            public Adapter caseLinearConversionType(LinearConversionType object) {
                return createLinearConversionTypeAdapter();
            }
            @Override
            public Adapter caseObjectClassConfigType(ObjectClassConfigType object) {
                return createObjectClassConfigTypeAdapter();
            }
            @Override
            public Adapter caseParameterConfigType(ParameterConfigType object) {
                return createParameterConfigTypeAdapter();
            }
            @Override
            public Adapter casePublishedObjectsType(PublishedObjectsType object) {
                return createPublishedObjectsTypeAdapter();
            }
            @Override
            public Adapter caseUnitConversionType(UnitConversionType object) {
                return createUnitConversionTypeAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link gov.nist.pages.ucef.AttributeConfigType <em>Attribute Config Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see gov.nist.pages.ucef.AttributeConfigType
     * @generated
     */
    public Adapter createAttributeConfigTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link gov.nist.pages.ucef.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see gov.nist.pages.ucef.DocumentRoot
     * @generated
     */
    public Adapter createDocumentRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link gov.nist.pages.ucef.InteractionClassConfigType <em>Interaction Class Config Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see gov.nist.pages.ucef.InteractionClassConfigType
     * @generated
     */
    public Adapter createInteractionClassConfigTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link gov.nist.pages.ucef.LinearConversionType <em>Linear Conversion Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see gov.nist.pages.ucef.LinearConversionType
     * @generated
     */
    public Adapter createLinearConversionTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link gov.nist.pages.ucef.ObjectClassConfigType <em>Object Class Config Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see gov.nist.pages.ucef.ObjectClassConfigType
     * @generated
     */
    public Adapter createObjectClassConfigTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link gov.nist.pages.ucef.ParameterConfigType <em>Parameter Config Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see gov.nist.pages.ucef.ParameterConfigType
     * @generated
     */
    public Adapter createParameterConfigTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link gov.nist.pages.ucef.PublishedObjectsType <em>Published Objects Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see gov.nist.pages.ucef.PublishedObjectsType
     * @generated
     */
    public Adapter createPublishedObjectsTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link gov.nist.pages.ucef.UnitConversionType <em>Unit Conversion Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see gov.nist.pages.ucef.UnitConversionType
     * @generated
     */
    public Adapter createUnitConversionTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //ucefAdapterFactory

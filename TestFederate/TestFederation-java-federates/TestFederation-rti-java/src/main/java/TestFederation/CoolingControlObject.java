
package TestFederation;

import java.util.HashSet;
import java.util.Set;

import hla.rti.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;


import org.cpswt.hla.*;

/**
* The CoolingControlObject class implements the CoolingControlObject object in the
* TestFederation simulation.
*/
public class CoolingControlObject extends GLDObject {

	private static final Logger logger = LogManager.getLogger(CoolingControlObject.class);

	/**
	* Default constructor -- creates an instance of the CoolingControlObject object
	* class with default attribute values.
	*/
	public CoolingControlObject() { }

	
	
	private static int _cooling_setpoint_handle;
	
	
	/**
	* Returns the handle (RTI assigned) of the "cooling_setpoint" attribute of
	* its containing object class.
	*
	* @return the handle (RTI assigned) of the "cooling_setpoint" attribute
	*/
	public static int get_cooling_setpoint_handle() { return _cooling_setpoint_handle; }
	
	
	
	private static boolean _isInitialized = false;

	private static int _handle;

	/**
	* Returns the handle (RTI assigned) of the CoolingControlObject object class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the handle of the class pertaining to the reference,\
	* rather than the handle of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassHandle()}.
	*/
	public static int get_handle() { return _handle; }

	/**
	* Returns the fully-qualified (dot-delimited) name of the CoolingControlObject
	* object class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the name of the class pertaining to the reference,\
	* rather than the name of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassName()}.
	*/
	public static String get_class_name() { return "ObjectRoot.GLDObject.CoolingControlObject"; }

	/**
	* Returns the simple name (the last name in the dot-delimited fully-qualified
	* class name) of the CoolingControlObject object class.
	*/
	public static String get_simple_class_name() { return "CoolingControlObject"; }

	private static Set< String > _datamemberNames = new HashSet< String >();
	private static Set< String > _allDatamemberNames = new HashSet< String >();

	/**
	* Returns a set containing the names of all of the non-hidden attributes in the
	* CoolingControlObject object class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return a set of parameter names pertaining to the reference,\
	* rather than the parameter names of the class for the instance referred to by
	* the reference.  For the polymorphic version of this method, use
	* {@link #getAttributeNames()}.
	*/
	public static Set< String > get_attribute_names() {
		return new HashSet< String >(_datamemberNames);
	}


	/**
	* Returns a set containing the names of all of the attributes in the
	* CoolingControlObject object class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return a set of parameter names pertaining to the reference,\
	* rather than the parameter names of the class for the instance referred to by
	* the reference.  For the polymorphic version of this method, use
	* {@link #getAttributeNames()}.
	*/
	public static Set< String > get_all_attribute_names() {
		return new HashSet< String >(_allDatamemberNames);
	}


	
	private static AttributeHandleSet _publishedAttributeHandleSet;
	private static Set< String > _publishAttributeNameSet = new HashSet< String >();

	private static AttributeHandleSet _subscribedAttributeHandleSet; 
	private static Set< String > _subscribeAttributeNameSet = new HashSet< String >();

	

	static {
		_classNameSet.add("ObjectRoot.GLDObject.CoolingControlObject");
		_classNameClassMap.put("ObjectRoot.GLDObject.CoolingControlObject", CoolingControlObject.class);
		
		_datamemberClassNameSetMap.put("ObjectRoot.GLDObject.CoolingControlObject", _datamemberNames);
		_allDatamemberClassNameSetMap.put("ObjectRoot.GLDObject.CoolingControlObject", _allDatamemberNames);

		
		
		_datamemberNames.add("cooling_setpoint");
		
		
		
		_allDatamemberNames.add("cooling_setpoint");
		_allDatamemberNames.add("name");
		
		
		_datamemberTypeMap.put("cooling_setpoint", "double");
	

		_classNamePublishAttributeNameMap.put("ObjectRoot.GLDObject.CoolingControlObject", _publishAttributeNameSet);
		_publishedAttributeHandleSet = _factory.createAttributeHandleSet();
		_classNamePublishedAttributeMap.put("ObjectRoot.GLDObject.CoolingControlObject", _publishedAttributeHandleSet);

		_classNameSubscribeAttributeNameMap.put("ObjectRoot.GLDObject.CoolingControlObject", _subscribeAttributeNameSet);
		_subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
		_classNameSubscribedAttributeMap.put("ObjectRoot.GLDObject.CoolingControlObject", _subscribedAttributeHandleSet);
	

	}


	private static String initErrorMessage = "Error:  ObjectRoot.GLDObject.CoolingControlObject:  could not initialize:  ";
	protected static void init(RTIambassador rti) {
		if (_isInitialized) return;
		_isInitialized = true;
		
		GLDObject.init(rti);
		
		boolean isNotInitialized = true;
		while(isNotInitialized) {
			try {
				_handle = rti.getObjectClassHandle("ObjectRoot.GLDObject.CoolingControlObject");
				isNotInitialized = false;
			} catch (FederateNotExecutionMember f) {
				logger.error("{} Federate Not Execution Member", initErrorMessage);
				logger.error(f);
				return;				
			} catch (NameNotFound n) {
				logger.error("{} Name Not Found", initErrorMessage);
				logger.error(n);
				return;				
			} catch (Exception e) {
				logger.error(e);
				CpswtUtils.sleepDefault();
			}
		}

		_classNameHandleMap.put("ObjectRoot.GLDObject.CoolingControlObject", get_handle());
		_classHandleNameMap.put(get_handle(), "ObjectRoot.GLDObject.CoolingControlObject");
		_classHandleSimpleNameMap.put(get_handle(), "CoolingControlObject");

		
		isNotInitialized = true;
		while(isNotInitialized) {
			try {
							
				_cooling_setpoint_handle = rti.getAttributeHandle("cooling_setpoint", get_handle());
				isNotInitialized = false;
			} catch (FederateNotExecutionMember f) {
				logger.error("{} Federate Not Execution Member", initErrorMessage);
				logger.error(f);
				return;
			} catch (ObjectClassNotDefined i) {
				logger.error("{} Object Class Not Defined", initErrorMessage);
				logger.error(i);
				return;
			} catch (NameNotFound n) {
				logger.error("{} Name Not Found", initErrorMessage);
				logger.error(n);
				return;
			} catch (Exception e) {
				logger.error(e);
				CpswtUtils.sleepDefault();
			}
		}
			
			
		_datamemberNameHandleMap.put("ObjectRoot.GLDObject.CoolingControlObject,cooling_setpoint", get_cooling_setpoint_handle());
			
			
		_datamemberHandleNameMap.put(get_cooling_setpoint_handle(), "cooling_setpoint");
		
	}

	private static boolean _isPublished = false;
	private static String publishErrorMessage = "Error:  ObjectRoot.GLDObject.CoolingControlObject:  could not publish:  ";

	/**
	* Publishes the CoolingControlObject object class for a federate.
	*
	* @param rti handle to the Local RTI Component
	*/
	public static void publish(RTIambassador rti) {
		if (_isPublished) return;
		
		init(rti);

		
		_publishedAttributeHandleSet.empty();
		for(String attributeName : _publishAttributeNameSet) {
			try {
				_publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.GLDObject.CoolingControlObject," + attributeName));
			} catch (Exception e) {
				logger.error("{} Could not publish \"" + attributeName + "\" attribute.", publishErrorMessage);
			}
		}
	

		synchronized(rti) {
			boolean isNotPublished = true;
			while(isNotPublished) {
				try {
					rti.publishObjectClass(get_handle(), _publishedAttributeHandleSet);
					isNotPublished = false;
				} catch (FederateNotExecutionMember f) {
					logger.error("{} Federate Not Execution Member", publishErrorMessage);
					logger.error(f);
					return;
				} catch (ObjectClassNotDefined i) {
					logger.error("{} Object Class Not Defined", publishErrorMessage);
					logger.error(i);
					return;
				} catch (Exception e) {
					logger.error(e);
					CpswtUtils.sleepDefault();
				}
			}
		}
		
		_isPublished = true;
	}

	private static String unpublishErrorMessage = "Error:  ObjectRoot.GLDObject.CoolingControlObject:  could not unpublish:  ";
	/**
	* Unpublishes the CoolingControlObject object class for a federate.
	*
	* @param rti handle to the Local RTI Component
	*/
	public static void unpublish(RTIambassador rti) {
		if (!_isPublished) return;
		
		init(rti);
		synchronized(rti) {
			boolean isNotUnpublished = true;
			while(isNotUnpublished) {
				try {
					rti.unpublishObjectClass(get_handle());
					isNotUnpublished = false;
				} catch (FederateNotExecutionMember f) {
					logger.error("{} Federate Not Execution Member", unpublishErrorMessage);
					logger.error(f);
					return;
				} catch (ObjectClassNotDefined i) {
					logger.error("{} Object Class Not Defined", unpublishErrorMessage);
					logger.error(i);
					return;
				} catch (ObjectClassNotPublished i) {
					logger.error("{} Object Class Not Published", unpublishErrorMessage);
					logger.error(i);
					return;
				} catch (Exception e) {
					logger.error(e);
					CpswtUtils.sleepDefault();
				}
			}
		}
		
		_isPublished = false;
	}

	private static boolean _isSubscribed = false;
	private static String subscribeErrorMessage = "Error:  ObjectRoot.GLDObject.CoolingControlObject:  could not subscribe:  ";
	/**
	* Subscribes a federate to the CoolingControlObject object class.
	*
	* @param rti handle to the Local RTI Component
	*/
	public static void subscribe(RTIambassador rti) {
		if (_isSubscribed) return;
		
		init(rti);
		
		_subscribedAttributeHandleSet.empty();
		for(String attributeName : _subscribeAttributeNameSet) {
			try {
				_subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.GLDObject.CoolingControlObject," + attributeName));
			} catch (Exception e) {
				logger.error("{} Could not subscribe to \"" + attributeName + "\" attribute.", subscribeErrorMessage);
			}
		}
	
		
		synchronized(rti) {
			boolean isNotSubscribed = true;
			while(isNotSubscribed) {
				try {
					rti.subscribeObjectClassAttributes(get_handle(), _subscribedAttributeHandleSet);
					isNotSubscribed = false;
				} catch (FederateNotExecutionMember f) {
					logger.error("{} Federate Not Execution Member", subscribeErrorMessage);
					logger.error(f);
					return;
				} catch (ObjectClassNotDefined i) {
					logger.error("{} Object Class Not Defined", subscribeErrorMessage);
					logger.error(i);
					return;
				} catch (Exception e) {
					logger.error(e);
					CpswtUtils.sleepDefault();
				}
			}
		}
		
		_isSubscribed = true;
	}

	private static String unsubscribeErrorMessage = "Error:  ObjectRoot.GLDObject.CoolingControlObject:  could not unsubscribe:  ";
	/**
	* Unsubscribes a federate from the CoolingControlObject object class.
	*
	* @param rti handle to the Local RTI Component
	*/
	public static void unsubscribe(RTIambassador rti) {
		if (!_isSubscribed) return;

		init(rti);
		synchronized(rti) {
			boolean isNotUnsubscribed = true;
			while(isNotUnsubscribed) {
				try {
					rti.unsubscribeObjectClass(get_handle());
					isNotUnsubscribed = false;
				} catch (FederateNotExecutionMember f) {
					logger.error("{} Federate Not Execution Member", unsubscribeErrorMessage);
					logger.error(f);
					return;
				} catch (ObjectClassNotDefined i) {
					logger.error("{} Object Class Not Defined", unsubscribeErrorMessage);
					logger.error(i);
					return;
				} catch (ObjectClassNotSubscribed i) {
					logger.error("{} Object Class Not Subscribed", unsubscribeErrorMessage);
					logger.error(i);
					return;
				} catch (Exception e) {
					logger.error(e);
					CpswtUtils.sleepDefault();
				}
			}
		}
		
		_isSubscribed = false;
	}

	/**
	* Return true if "handle" is equal to the handle (RTI assigned) of this class
	* (that is, the CoolingControlObject object class).
	*
	* @param handle handle to compare to the value of the handle (RTI assigned) of
	* this class (the CoolingControlObject object class).
	* @return "true" if "handle" matches the value of the handle of this class
	* (that is, the CoolingControlObject object class).
	*/
	public static boolean match(int handle) { return handle == get_handle(); }

	/**
	* Returns the handle (RTI assigned) of this instance's object class .
	* 
	* @return the handle (RTI assigned) if this instance's object class
	*/
	public int getClassHandle() { return get_handle(); }

	/**
	* Returns the fully-qualified (dot-delimited) name of this instance's object class.
	* 
	* @return the fully-qualified (dot-delimited) name of this instance's object class
	*/
	public String getClassName() { return get_class_name(); }

	/**
	* Returns the simple name (last name in its fully-qualified dot-delimited name)
	* of this instance's object class.
	* 
	* @return the simple name of this instance's object class 
	*/
	public String getSimpleClassName() { return get_simple_class_name(); }

	/**
	* Returns a set containing the names of all of the non-hiddenattributes of an
	* object class instance.
	*
	* @return set containing the names of all of the attributes of an
	* object class instance
	*/
	public Set< String > getAttributeNames() { return get_attribute_names(); }

	/**
	* Returns a set containing the names of all of the attributes of an
	* object class instance.
	*
	* @return set containing the names of all of the attributes of an
	* object class instance
	*/
	public Set< String > getAllAttributeNames() { return get_all_attribute_names(); }

	/**
	* Publishes the object class of this instance of the class for a federate.
	*
	* @param rti handle to the Local RTI Component
	*/
	public void publishObject(RTIambassador rti) { publish(rti); }

	/**
	* Unpublishes the object class of this instance of this class for a federate.
	*
	* @param rti handle to the Local RTI Component
	*/
	public void unpublishObject(RTIambassador rti) { unpublish(rti); }

	/**
	* Subscribes a federate to the object class of this instance of this class.
	*
	* @param rti handle to the Local RTI Component
	*/
	public void subscribeObject(RTIambassador rti) { subscribe(rti); }

	/**
	* Unsubscribes a federate from the object class of this instance of this class.
	*
	* @param rti handle to the Local RTI Component
	*/
	public void unsubscribeObject(RTIambassador rti) { unsubscribe(rti); }

	
	/**
	* Returns a data structure containing the handles of all attributes for this object
	* class that are currently marked for subscription.  To actually subscribe to these
	* attributes, a federate must call <objectclassname>.subscribe(RTIambassador rti).
	*
	* @return data structure containing the handles of all attributes for this object
	* class that are currently marked for subscription
	*/
	public AttributeHandleSet getSubscribedAttributeHandleSet() { return _subscribedAttributeHandleSet; }
	

	public String toString() {
		return "CoolingControlObject("
			
			
			+ "cooling_setpoint:" + get_cooling_setpoint()
			+ ")";
	}
	



	
	
	/**
	* Publishes the "cooling_setpoint" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "cooling_setpoint" attribute for publication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void publish_cooling_setpoint() {
		_publishAttributeNameSet.add( "cooling_setpoint" );
	}

	/**
	* Unpublishes the "cooling_setpoint" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "cooling_setpoint" attribute for unpublication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void unpublish_cooling_setpoint() {
		_publishAttributeNameSet.remove( "cooling_setpoint" );
	}
	
	/**
	* Subscribes a federate to the "cooling_setpoint" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "cooling_setpoint" attribute for subscription.
	* To actually subscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void subscribe_cooling_setpoint() {
		_subscribeAttributeNameSet.add( "cooling_setpoint" );
	}

	/**
	* Unsubscribes a federate from the "cooling_setpoint" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "cooling_setpoint" attribute for unsubscription.
	* To actually unsubscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void unsubscribe_cooling_setpoint() {
		_subscribeAttributeNameSet.remove( "cooling_setpoint" );
	}
	
	
	/**
	* Publishes the "name" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "name" attribute for publication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void publish_name() {
		_publishAttributeNameSet.add( "name" );
	}

	/**
	* Unpublishes the "name" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "name" attribute for unpublication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void unpublish_name() {
		_publishAttributeNameSet.remove( "name" );
	}
	
	/**
	* Subscribes a federate to the "name" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "name" attribute for subscription.
	* To actually subscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void subscribe_name() {
		_subscribeAttributeNameSet.add( "name" );
	}

	/**
	* Unsubscribes a federate from the "name" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "name" attribute for unsubscription.
	* To actually unsubscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void unsubscribe_name() {
		_subscribeAttributeNameSet.remove( "name" );
	}
	

	
	
	private Attribute< Double > _cooling_setpoint =
 		new Attribute< Double >(  new Double( 0 )  );
	
	/**
	* Set the value of the "cooling_setpoint" attribute to "value" for this object.
	*
	* @param value the new value for the "cooling_setpoint" attribute
	*/
	public void set_cooling_setpoint( double value ) {
		_cooling_setpoint.setValue( value );
		_cooling_setpoint.setTime( getTime() );
	}
	
	/**
	* Returns the value of the "cooling_setpoint" attribute of this object.
	*
	* @return the value of the "cooling_setpoint" attribute
	*/
	public double get_cooling_setpoint() {
		return _cooling_setpoint.getValue();
	}
	
	/**
	* Returns the current timestamp of the "cooling_setpoint" attribute of this object.
	* 
	* @return the current timestamp of the "cooling_setpoint" attribute
	*/
	public double get_cooling_setpoint_time() {
		return _cooling_setpoint.getTime();
	}
	


	protected CoolingControlObject( ReflectedAttributes datamemberMap, boolean initFlag ) {
		super( datamemberMap, false );
		if ( initFlag ) setAttributes( datamemberMap );
	}
	
	protected CoolingControlObject( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
		super( datamemberMap, logicalTime, false );
		if ( initFlag ) setAttributes( datamemberMap );
	}


	/**
	* Creates an instance of the CoolingControlObject object class, using
	* "datamemberMap" to initialize its attribute values.
	* "datamemberMap" is usually acquired as an argument to an RTI federate
	* callback method, such as "receiveInteraction".
	*
	* @param datamemberMap data structure containing initial values for the
	* attributes of this new CoolingControlObject object class instance
	*/
	public CoolingControlObject( ReflectedAttributes datamemberMap ) {
		this( datamemberMap, true );
	}
	
	/**
	* Like {@link #CoolingControlObject( ReflectedAttributes datamemberMap )}, except this
	* new CoolingControlObject object class instance is given a timestamp of
	* "logicalTime".
	*
	* @param datamemberMap data structure containing initial values for the
	* attributes of this new CoolingControlObject object class instance
	* @param logicalTime timestamp for this new CoolingControlObject object class
	* instance
	*/
	public CoolingControlObject( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
		this( datamemberMap, logicalTime, true );
	}

	/**
	* Creates a new CoolingControlObject object class instance that is a duplicate
	* of the instance referred to by CoolingControlObject_var.
	*
	* @param CoolingControlObject_var CoolingControlObject object class instance of which
	* this newly created CoolingControlObject object class instance will be a
	* duplicate
	*/
	public CoolingControlObject( CoolingControlObject CoolingControlObject_var ) {
		super( CoolingControlObject_var );
		
		
		set_cooling_setpoint( CoolingControlObject_var.get_cooling_setpoint() );
	}


	/**
	* Returns the value of the attribute whose name is "datamemberName"
	* for this object.
	*
	* @param datamemberName name of attribute whose value is to be
	* returned
	* @return value of the attribute whose name is "datamemberName"
	* for this object
	*/
	public Object getAttribute( String datamemberName ) {
		
		
		
		if (  "cooling_setpoint".equals( datamemberName )  ) return new Double(get_cooling_setpoint());
		else return super.getAttribute( datamemberName );
	}
	
	/**
	* Returns the value of the attribute whose handle (RTI assigned)
	* is "datamemberHandle" for this object.
	*
	* @param datamemberHandle handle (RTI assigned) of attribute whose
	* value is to be returned
	* @return value of the attribute whose handle (RTI assigned) is
	* "datamemberHandle" for this object
	*/
	public Object getAttribute( int datamemberHandle ) {
		
				
		
		if ( get_cooling_setpoint_handle() == datamemberHandle ) return new Double(get_cooling_setpoint());
		else return super.getAttribute( datamemberHandle );
	}
	
	protected boolean setAttributeAux( int param_handle, String val ) {
		boolean retval = true;		
		
			
		
		if ( param_handle == get_cooling_setpoint_handle() ) set_cooling_setpoint( Double.parseDouble(val) );
		else retval = super.setAttributeAux( param_handle, val );
		
		return retval;
	}
	
	protected boolean setAttributeAux( String datamemberName, String val ) {
		boolean retval = true;
		
			
		
		if (  "cooling_setpoint".equals( datamemberName )  ) set_cooling_setpoint( Double.parseDouble(val) );	
		else retval = super.setAttributeAux( datamemberName, val );
		
		return retval;
	}
	
	protected boolean setAttributeAux( String datamemberName, Object val ) {
		boolean retval = true;
		
		
		
		if (  "cooling_setpoint".equals( datamemberName )  ) set_cooling_setpoint( (Double)val );		
		else retval = super.setAttributeAux( datamemberName, val );
		
		return retval;
	}

	protected SuppliedAttributes createSuppliedDatamembers( boolean force ) {
		SuppliedAttributes datamembers = super.createSuppliedDatamembers( force );

	
		boolean isPublished = false;
		
		
			try {
				isPublished = _publishedAttributeHandleSet.isMember( get_cooling_setpoint_handle() );
			} catch ( Exception e ) {
				logger.error("ERROR:  ObjectRoot.GLDObject.CoolingControlObject.createSuppliedAttributes:  could not determine if cooling_setpoint is published.");
				isPublished = false;
			}
			if (  isPublished && _cooling_setpoint.shouldBeUpdated( force )  ) {
				datamembers.add( get_cooling_setpoint_handle(), Double.toString(get_cooling_setpoint()).getBytes() );
				_cooling_setpoint.setHasBeenUpdated();
			}
	
		return datamembers;
	}

	
	public void copyFrom( Object object ) {
		super.copyFrom( object );
		if ( object instanceof CoolingControlObject ) {
			CoolingControlObject data = (CoolingControlObject)object;
			
			
				_cooling_setpoint = data._cooling_setpoint;
			
		}
	}
}

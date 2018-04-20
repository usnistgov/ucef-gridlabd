
package TestFederation;

import java.util.HashSet;
import java.util.Set;

import hla.rti.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;


import org.cpswt.hla.*;

/**
* The HouseObject class implements the HouseObject object in the
* TestFederation simulation.
*/
public class HouseObject extends GLDObject {

	private static final Logger logger = LogManager.getLogger(HouseObject.class);

	/**
	* Default constructor -- creates an instance of the HouseObject object
	* class with default attribute values.
	*/
	public HouseObject() { }

	
	
	private static int _air_temperature_handle;
	private static int _compressor_count_handle;
	private static int _compressor_on_handle;
	
	
	/**
	* Returns the handle (RTI assigned) of the "air_temperature" attribute of
	* its containing object class.
	*
	* @return the handle (RTI assigned) of the "air_temperature" attribute
	*/
	public static int get_air_temperature_handle() { return _air_temperature_handle; }
	
	/**
	* Returns the handle (RTI assigned) of the "compressor_count" attribute of
	* its containing object class.
	*
	* @return the handle (RTI assigned) of the "compressor_count" attribute
	*/
	public static int get_compressor_count_handle() { return _compressor_count_handle; }
	
	/**
	* Returns the handle (RTI assigned) of the "compressor_on" attribute of
	* its containing object class.
	*
	* @return the handle (RTI assigned) of the "compressor_on" attribute
	*/
	public static int get_compressor_on_handle() { return _compressor_on_handle; }
	
	
	
	private static boolean _isInitialized = false;

	private static int _handle;

	/**
	* Returns the handle (RTI assigned) of the HouseObject object class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the handle of the class pertaining to the reference,\
	* rather than the handle of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassHandle()}.
	*/
	public static int get_handle() { return _handle; }

	/**
	* Returns the fully-qualified (dot-delimited) name of the HouseObject
	* object class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the name of the class pertaining to the reference,\
	* rather than the name of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassName()}.
	*/
	public static String get_class_name() { return "ObjectRoot.GLDObject.HouseObject"; }

	/**
	* Returns the simple name (the last name in the dot-delimited fully-qualified
	* class name) of the HouseObject object class.
	*/
	public static String get_simple_class_name() { return "HouseObject"; }

	private static Set< String > _datamemberNames = new HashSet< String >();
	private static Set< String > _allDatamemberNames = new HashSet< String >();

	/**
	* Returns a set containing the names of all of the non-hidden attributes in the
	* HouseObject object class.
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
	* HouseObject object class.
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
		_classNameSet.add("ObjectRoot.GLDObject.HouseObject");
		_classNameClassMap.put("ObjectRoot.GLDObject.HouseObject", HouseObject.class);
		
		_datamemberClassNameSetMap.put("ObjectRoot.GLDObject.HouseObject", _datamemberNames);
		_allDatamemberClassNameSetMap.put("ObjectRoot.GLDObject.HouseObject", _allDatamemberNames);

		
		
		_datamemberNames.add("air_temperature");
		_datamemberNames.add("compressor_count");
		_datamemberNames.add("compressor_on");
		
		
		
		_allDatamemberNames.add("air_temperature");
		_allDatamemberNames.add("compressor_count");
		_allDatamemberNames.add("compressor_on");
		_allDatamemberNames.add("name");
		
		
		_datamemberTypeMap.put("air_temperature", "double");
		_datamemberTypeMap.put("compressor_count", "int");
		_datamemberTypeMap.put("compressor_on", "boolean");
	

		_classNamePublishAttributeNameMap.put("ObjectRoot.GLDObject.HouseObject", _publishAttributeNameSet);
		_publishedAttributeHandleSet = _factory.createAttributeHandleSet();
		_classNamePublishedAttributeMap.put("ObjectRoot.GLDObject.HouseObject", _publishedAttributeHandleSet);

		_classNameSubscribeAttributeNameMap.put("ObjectRoot.GLDObject.HouseObject", _subscribeAttributeNameSet);
		_subscribedAttributeHandleSet = _factory.createAttributeHandleSet();
		_classNameSubscribedAttributeMap.put("ObjectRoot.GLDObject.HouseObject", _subscribedAttributeHandleSet);
	

	}


	private static String initErrorMessage = "Error:  ObjectRoot.GLDObject.HouseObject:  could not initialize:  ";
	protected static void init(RTIambassador rti) {
		if (_isInitialized) return;
		_isInitialized = true;
		
		GLDObject.init(rti);
		
		boolean isNotInitialized = true;
		while(isNotInitialized) {
			try {
				_handle = rti.getObjectClassHandle("ObjectRoot.GLDObject.HouseObject");
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

		_classNameHandleMap.put("ObjectRoot.GLDObject.HouseObject", get_handle());
		_classHandleNameMap.put(get_handle(), "ObjectRoot.GLDObject.HouseObject");
		_classHandleSimpleNameMap.put(get_handle(), "HouseObject");

		
		isNotInitialized = true;
		while(isNotInitialized) {
			try {
							
				_air_temperature_handle = rti.getAttributeHandle("air_temperature", get_handle());			
				_compressor_count_handle = rti.getAttributeHandle("compressor_count", get_handle());			
				_compressor_on_handle = rti.getAttributeHandle("compressor_on", get_handle());
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
			
			
		_datamemberNameHandleMap.put("ObjectRoot.GLDObject.HouseObject,air_temperature", get_air_temperature_handle());
		_datamemberNameHandleMap.put("ObjectRoot.GLDObject.HouseObject,compressor_count", get_compressor_count_handle());
		_datamemberNameHandleMap.put("ObjectRoot.GLDObject.HouseObject,compressor_on", get_compressor_on_handle());
			
			
		_datamemberHandleNameMap.put(get_air_temperature_handle(), "air_temperature");
		_datamemberHandleNameMap.put(get_compressor_count_handle(), "compressor_count");
		_datamemberHandleNameMap.put(get_compressor_on_handle(), "compressor_on");
		
	}

	private static boolean _isPublished = false;
	private static String publishErrorMessage = "Error:  ObjectRoot.GLDObject.HouseObject:  could not publish:  ";

	/**
	* Publishes the HouseObject object class for a federate.
	*
	* @param rti handle to the Local RTI Component
	*/
	public static void publish(RTIambassador rti) {
		if (_isPublished) return;
		
		init(rti);

		
		_publishedAttributeHandleSet.empty();
		for(String attributeName : _publishAttributeNameSet) {
			try {
				_publishedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.GLDObject.HouseObject," + attributeName));
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

	private static String unpublishErrorMessage = "Error:  ObjectRoot.GLDObject.HouseObject:  could not unpublish:  ";
	/**
	* Unpublishes the HouseObject object class for a federate.
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
	private static String subscribeErrorMessage = "Error:  ObjectRoot.GLDObject.HouseObject:  could not subscribe:  ";
	/**
	* Subscribes a federate to the HouseObject object class.
	*
	* @param rti handle to the Local RTI Component
	*/
	public static void subscribe(RTIambassador rti) {
		if (_isSubscribed) return;
		
		init(rti);
		
		_subscribedAttributeHandleSet.empty();
		for(String attributeName : _subscribeAttributeNameSet) {
			try {
				_subscribedAttributeHandleSet.add(_datamemberNameHandleMap.get("ObjectRoot.GLDObject.HouseObject," + attributeName));
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

	private static String unsubscribeErrorMessage = "Error:  ObjectRoot.GLDObject.HouseObject:  could not unsubscribe:  ";
	/**
	* Unsubscribes a federate from the HouseObject object class.
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
	* (that is, the HouseObject object class).
	*
	* @param handle handle to compare to the value of the handle (RTI assigned) of
	* this class (the HouseObject object class).
	* @return "true" if "handle" matches the value of the handle of this class
	* (that is, the HouseObject object class).
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
		return "HouseObject("
			
			
			+ "air_temperature:" + get_air_temperature()
			+ "," + "compressor_count:" + get_compressor_count()
			+ "," + "compressor_on:" + get_compressor_on()
			+ ")";
	}
	



	
	
	/**
	* Publishes the "air_temperature" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "air_temperature" attribute for publication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void publish_air_temperature() {
		_publishAttributeNameSet.add( "air_temperature" );
	}

	/**
	* Unpublishes the "air_temperature" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "air_temperature" attribute for unpublication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void unpublish_air_temperature() {
		_publishAttributeNameSet.remove( "air_temperature" );
	}
	
	/**
	* Subscribes a federate to the "air_temperature" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "air_temperature" attribute for subscription.
	* To actually subscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void subscribe_air_temperature() {
		_subscribeAttributeNameSet.add( "air_temperature" );
	}

	/**
	* Unsubscribes a federate from the "air_temperature" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "air_temperature" attribute for unsubscription.
	* To actually unsubscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void unsubscribe_air_temperature() {
		_subscribeAttributeNameSet.remove( "air_temperature" );
	}
	
	
	/**
	* Publishes the "compressor_count" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "compressor_count" attribute for publication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void publish_compressor_count() {
		_publishAttributeNameSet.add( "compressor_count" );
	}

	/**
	* Unpublishes the "compressor_count" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "compressor_count" attribute for unpublication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void unpublish_compressor_count() {
		_publishAttributeNameSet.remove( "compressor_count" );
	}
	
	/**
	* Subscribes a federate to the "compressor_count" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "compressor_count" attribute for subscription.
	* To actually subscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void subscribe_compressor_count() {
		_subscribeAttributeNameSet.add( "compressor_count" );
	}

	/**
	* Unsubscribes a federate from the "compressor_count" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "compressor_count" attribute for unsubscription.
	* To actually unsubscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void unsubscribe_compressor_count() {
		_subscribeAttributeNameSet.remove( "compressor_count" );
	}
	
	
	/**
	* Publishes the "compressor_on" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "compressor_on" attribute for publication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void publish_compressor_on() {
		_publishAttributeNameSet.add( "compressor_on" );
	}

	/**
	* Unpublishes the "compressor_on" attribute of the attribute's containing object
	* class for a federate.
	* Note:  This method only marks the "compressor_on" attribute for unpublication.
	* To actually publish the attribute, the federate must (re)publish its containing
	* object class.
	* (using <objectClassName>.publish( RTIambassador rti ) ).
	*/
	public static void unpublish_compressor_on() {
		_publishAttributeNameSet.remove( "compressor_on" );
	}
	
	/**
	* Subscribes a federate to the "compressor_on" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "compressor_on" attribute for subscription.
	* To actually subscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void subscribe_compressor_on() {
		_subscribeAttributeNameSet.add( "compressor_on" );
	}

	/**
	* Unsubscribes a federate from the "compressor_on" attribute of the attribute's
	* containing object class.
	* Note:  This method only marks the "compressor_on" attribute for unsubscription.
	* To actually unsubscribe to the attribute, the federate must (re)subscribe to its
	* containing object class.
	* (using <objectClassName>.subscribe( RTIambassador rti ) ).
	*/
	public static void unsubscribe_compressor_on() {
		_subscribeAttributeNameSet.remove( "compressor_on" );
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
	

	
	
	private Attribute< Double > _air_temperature =
 		new Attribute< Double >(  new Double( 0 )  );
	
	/**
	* Set the value of the "air_temperature" attribute to "value" for this object.
	*
	* @param value the new value for the "air_temperature" attribute
	*/
	public void set_air_temperature( double value ) {
		_air_temperature.setValue( value );
		_air_temperature.setTime( getTime() );
	}
	
	/**
	* Returns the value of the "air_temperature" attribute of this object.
	*
	* @return the value of the "air_temperature" attribute
	*/
	public double get_air_temperature() {
		return _air_temperature.getValue();
	}
	
	/**
	* Returns the current timestamp of the "air_temperature" attribute of this object.
	* 
	* @return the current timestamp of the "air_temperature" attribute
	*/
	public double get_air_temperature_time() {
		return _air_temperature.getTime();
	}
	
	
	private Attribute< Integer > _compressor_count =
 		new Attribute< Integer >(  new Integer( 0 )  );
	
	/**
	* Set the value of the "compressor_count" attribute to "value" for this object.
	*
	* @param value the new value for the "compressor_count" attribute
	*/
	public void set_compressor_count( int value ) {
		_compressor_count.setValue( value );
		_compressor_count.setTime( getTime() );
	}
	
	/**
	* Returns the value of the "compressor_count" attribute of this object.
	*
	* @return the value of the "compressor_count" attribute
	*/
	public int get_compressor_count() {
		return _compressor_count.getValue();
	}
	
	/**
	* Returns the current timestamp of the "compressor_count" attribute of this object.
	* 
	* @return the current timestamp of the "compressor_count" attribute
	*/
	public double get_compressor_count_time() {
		return _compressor_count.getTime();
	}
	
	
	private Attribute< Boolean > _compressor_on =
 		new Attribute< Boolean >(  new Boolean( false )  );
	
	/**
	* Set the value of the "compressor_on" attribute to "value" for this object.
	*
	* @param value the new value for the "compressor_on" attribute
	*/
	public void set_compressor_on( boolean value ) {
		_compressor_on.setValue( value );
		_compressor_on.setTime( getTime() );
	}
	
	/**
	* Returns the value of the "compressor_on" attribute of this object.
	*
	* @return the value of the "compressor_on" attribute
	*/
	public boolean get_compressor_on() {
		return _compressor_on.getValue();
	}
	
	/**
	* Returns the current timestamp of the "compressor_on" attribute of this object.
	* 
	* @return the current timestamp of the "compressor_on" attribute
	*/
	public double get_compressor_on_time() {
		return _compressor_on.getTime();
	}
	


	protected HouseObject( ReflectedAttributes datamemberMap, boolean initFlag ) {
		super( datamemberMap, false );
		if ( initFlag ) setAttributes( datamemberMap );
	}
	
	protected HouseObject( ReflectedAttributes datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
		super( datamemberMap, logicalTime, false );
		if ( initFlag ) setAttributes( datamemberMap );
	}


	/**
	* Creates an instance of the HouseObject object class, using
	* "datamemberMap" to initialize its attribute values.
	* "datamemberMap" is usually acquired as an argument to an RTI federate
	* callback method, such as "receiveInteraction".
	*
	* @param datamemberMap data structure containing initial values for the
	* attributes of this new HouseObject object class instance
	*/
	public HouseObject( ReflectedAttributes datamemberMap ) {
		this( datamemberMap, true );
	}
	
	/**
	* Like {@link #HouseObject( ReflectedAttributes datamemberMap )}, except this
	* new HouseObject object class instance is given a timestamp of
	* "logicalTime".
	*
	* @param datamemberMap data structure containing initial values for the
	* attributes of this new HouseObject object class instance
	* @param logicalTime timestamp for this new HouseObject object class
	* instance
	*/
	public HouseObject( ReflectedAttributes datamemberMap, LogicalTime logicalTime ) {
		this( datamemberMap, logicalTime, true );
	}

	/**
	* Creates a new HouseObject object class instance that is a duplicate
	* of the instance referred to by HouseObject_var.
	*
	* @param HouseObject_var HouseObject object class instance of which
	* this newly created HouseObject object class instance will be a
	* duplicate
	*/
	public HouseObject( HouseObject HouseObject_var ) {
		super( HouseObject_var );
		
		
		set_air_temperature( HouseObject_var.get_air_temperature() );
		set_compressor_count( HouseObject_var.get_compressor_count() );
		set_compressor_on( HouseObject_var.get_compressor_on() );
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
		
		
		
		if (  "air_temperature".equals( datamemberName )  ) return new Double(get_air_temperature());
		else if (  "compressor_count".equals( datamemberName )  ) return new Integer(get_compressor_count());
		else if (  "compressor_on".equals( datamemberName )  ) return new Boolean(get_compressor_on());
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
		
				
		
		if ( get_air_temperature_handle() == datamemberHandle ) return new Double(get_air_temperature());
		else if ( get_compressor_count_handle() == datamemberHandle ) return new Integer(get_compressor_count());
		else if ( get_compressor_on_handle() == datamemberHandle ) return new Boolean(get_compressor_on());
		else return super.getAttribute( datamemberHandle );
	}
	
	protected boolean setAttributeAux( int param_handle, String val ) {
		boolean retval = true;		
		
			
		
		if ( param_handle == get_air_temperature_handle() ) set_air_temperature( Double.parseDouble(val) );
		else if ( param_handle == get_compressor_count_handle() ) set_compressor_count( Integer.parseInt(val) );
		else if ( param_handle == get_compressor_on_handle() ) set_compressor_on( Boolean.parseBoolean(val) );
		else retval = super.setAttributeAux( param_handle, val );
		
		return retval;
	}
	
	protected boolean setAttributeAux( String datamemberName, String val ) {
		boolean retval = true;
		
			
		
		if (  "air_temperature".equals( datamemberName )  ) set_air_temperature( Double.parseDouble(val) );
		else if (  "compressor_count".equals( datamemberName )  ) set_compressor_count( Integer.parseInt(val) );
		else if (  "compressor_on".equals( datamemberName )  ) set_compressor_on( Boolean.parseBoolean(val) );	
		else retval = super.setAttributeAux( datamemberName, val );
		
		return retval;
	}
	
	protected boolean setAttributeAux( String datamemberName, Object val ) {
		boolean retval = true;
		
		
		
		if (  "air_temperature".equals( datamemberName )  ) set_air_temperature( (Double)val );
		else if (  "compressor_count".equals( datamemberName )  ) set_compressor_count( (Integer)val );
		else if (  "compressor_on".equals( datamemberName )  ) set_compressor_on( (Boolean)val );		
		else retval = super.setAttributeAux( datamemberName, val );
		
		return retval;
	}

	protected SuppliedAttributes createSuppliedDatamembers( boolean force ) {
		SuppliedAttributes datamembers = super.createSuppliedDatamembers( force );

	
		boolean isPublished = false;
		
		
			try {
				isPublished = _publishedAttributeHandleSet.isMember( get_air_temperature_handle() );
			} catch ( Exception e ) {
				logger.error("ERROR:  ObjectRoot.GLDObject.HouseObject.createSuppliedAttributes:  could not determine if air_temperature is published.");
				isPublished = false;
			}
			if (  isPublished && _air_temperature.shouldBeUpdated( force )  ) {
				datamembers.add( get_air_temperature_handle(), Double.toString(get_air_temperature()).getBytes() );
				_air_temperature.setHasBeenUpdated();
			}
			try {
				isPublished = _publishedAttributeHandleSet.isMember( get_compressor_count_handle() );
			} catch ( Exception e ) {
				logger.error("ERROR:  ObjectRoot.GLDObject.HouseObject.createSuppliedAttributes:  could not determine if compressor_count is published.");
				isPublished = false;
			}
			if (  isPublished && _compressor_count.shouldBeUpdated( force )  ) {
				datamembers.add( get_compressor_count_handle(), Integer.toString(get_compressor_count()).getBytes() );
				_compressor_count.setHasBeenUpdated();
			}
			try {
				isPublished = _publishedAttributeHandleSet.isMember( get_compressor_on_handle() );
			} catch ( Exception e ) {
				logger.error("ERROR:  ObjectRoot.GLDObject.HouseObject.createSuppliedAttributes:  could not determine if compressor_on is published.");
				isPublished = false;
			}
			if (  isPublished && _compressor_on.shouldBeUpdated( force )  ) {
				datamembers.add( get_compressor_on_handle(), Boolean.toString(get_compressor_on()).getBytes() );
				_compressor_on.setHasBeenUpdated();
			}
	
		return datamembers;
	}

	
	public void copyFrom( Object object ) {
		super.copyFrom( object );
		if ( object instanceof HouseObject ) {
			HouseObject data = (HouseObject)object;
			
			
				_air_temperature = data._air_temperature;
				_compressor_count = data._compressor_count;
				_compressor_on = data._compressor_on;
			
		}
	}
}

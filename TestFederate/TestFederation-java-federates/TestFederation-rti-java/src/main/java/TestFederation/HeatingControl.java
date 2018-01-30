
package TestFederation;

import java.util.HashSet;
import java.util.Set;

import hla.rti.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;


import org.cpswt.hla.*;

/**
* The HeatingControl class implements the HeatingControl interaction in the
* TestFederation simulation.
*/
public class HeatingControl extends C2WInteractionRoot {

	private static final Logger logger = LogManager.getLogger(HeatingControl.class);

	/**
	* Default constructor -- creates an instance of the HeatingControl interaction
	* class with default parameter values.
	*/
	public HeatingControl() { }

	
	
	private static int _heating_setpoint_handle;
	private static int _name_handle;
	
	
	/**
	* Returns the handle (RTI assigned) of the "heating_setpoint" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "heating_setpoint" parameter
	*/
	public static int get_heating_setpoint_handle() { return _heating_setpoint_handle; }
	
	/**
	* Returns the handle (RTI assigned) of the "name" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "name" parameter
	*/
	public static int get_name_handle() { return _name_handle; }
	
	
	
	private static boolean _isInitialized = false;

	private static int _handle;

	/**
	* Returns the handle (RTI assigned) of the HeatingControl interaction class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the handle of the class pertaining to the reference,\
	* rather than the handle of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassHandle()}.
	*/
	public static int get_handle() { return _handle; }

	/**
	* Returns the fully-qualified (dot-delimited) name of the HeatingControl
	* interaction class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the name of the class pertaining to the reference,\
	* rather than the name of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassName()}.
	*/
	public static String get_class_name() { return "InteractionRoot.C2WInteractionRoot.HeatingControl"; }

	/**
	* Returns the simple name (the last name in the dot-delimited fully-qualified
	* class name) of the HeatingControl interaction class.
	*/
	public static String get_simple_class_name() { return "HeatingControl"; }

	private static Set< String > _datamemberNames = new HashSet< String >();
	private static Set< String > _allDatamemberNames = new HashSet< String >();

	/**
	* Returns a set containing the names of all of the non-hidden parameters in the
	* HeatingControl interaction class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return a set of parameter names pertaining to the reference,\
	* rather than the parameter names of the class for the instance referred to by
	* the reference.  For the polymorphic version of this method, use
	* {@link #getParameterNames()}.
	*/
	public static Set< String > get_parameter_names() {
		return new HashSet< String >(_datamemberNames);
	}


	/**
	* Returns a set containing the names of all of the parameters in the
	* HeatingControl interaction class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return a set of parameter names pertaining to the reference,\
	* rather than the parameter names of the class for the instance referred to by
	* the reference.  For the polymorphic version of this method, use
	* {@link #getParameterNames()}.
	*/
	public static Set< String > get_all_parameter_names() {
		return new HashSet< String >(_allDatamemberNames);
	}


	

	static {
		_classNameSet.add("InteractionRoot.C2WInteractionRoot.HeatingControl");
		_classNameClassMap.put("InteractionRoot.C2WInteractionRoot.HeatingControl", HeatingControl.class);
		
		_datamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.HeatingControl", _datamemberNames);
		_allDatamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.HeatingControl", _allDatamemberNames);

		
		
		
		
		_datamemberNames.add("heating_setpoint");
		_datamemberNames.add("name");
		
		
		
		
		_allDatamemberNames.add("actualLogicalGenerationTime");
		_allDatamemberNames.add("federateFilter");
		_allDatamemberNames.add("heating_setpoint");
		_allDatamemberNames.add("name");
		_allDatamemberNames.add("originFed");
		_allDatamemberNames.add("sourceFed");
		
		
		_datamemberTypeMap.put("heating_setpoint", "double");
		_datamemberTypeMap.put("name", "String");
	
	

	}


	private static String initErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.HeatingControl:  could not initialize:  ";
	protected static void init(RTIambassador rti) {
		if (_isInitialized) return;
		_isInitialized = true;
		
		C2WInteractionRoot.init(rti);
		
		boolean isNotInitialized = true;
		while(isNotInitialized) {
			try {
				_handle = rti.getInteractionClassHandle("InteractionRoot.C2WInteractionRoot.HeatingControl");
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

		_classNameHandleMap.put("InteractionRoot.C2WInteractionRoot.HeatingControl", get_handle());
		_classHandleNameMap.put(get_handle(), "InteractionRoot.C2WInteractionRoot.HeatingControl");
		_classHandleSimpleNameMap.put(get_handle(), "HeatingControl");

		
		isNotInitialized = true;
		while(isNotInitialized) {
			try {
							
				_heating_setpoint_handle = rti.getParameterHandle("heating_setpoint", get_handle());			
				_name_handle = rti.getParameterHandle("name", get_handle());
				isNotInitialized = false;
			} catch (FederateNotExecutionMember f) {
				logger.error("{} Federate Not Execution Member", initErrorMessage);
				logger.error(f);
				return;
			} catch (InteractionClassNotDefined i) {
				logger.error("{} Interaction Class Not Defined", initErrorMessage);
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
			
			
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.HeatingControl,heating_setpoint", get_heating_setpoint_handle());
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.HeatingControl,name", get_name_handle());
			
			
		_datamemberHandleNameMap.put(get_heating_setpoint_handle(), "heating_setpoint");
		_datamemberHandleNameMap.put(get_name_handle(), "name");
		
	}

	private static boolean _isPublished = false;
	private static String publishErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.HeatingControl:  could not publish:  ";

	/**
	* Publishes the HeatingControl interaction class for a federate.
	*
	* @param rti handle to the Local RTI Component
	*/
	public static void publish(RTIambassador rti) {
		if (_isPublished) return;
		
		init(rti);

	

		synchronized(rti) {
			boolean isNotPublished = true;
			while(isNotPublished) {
				try {
					rti.publishInteractionClass(get_handle());
					isNotPublished = false;
				} catch (FederateNotExecutionMember f) {
					logger.error("{} Federate Not Execution Member", publishErrorMessage);
					logger.error(f);
					return;
				} catch (InteractionClassNotDefined i) {
					logger.error("{} Interaction Class Not Defined", publishErrorMessage);
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

	private static String unpublishErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.HeatingControl:  could not unpublish:  ";
	/**
	* Unpublishes the HeatingControl interaction class for a federate.
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
					rti.unpublishInteractionClass(get_handle());
					isNotUnpublished = false;
				} catch (FederateNotExecutionMember f) {
					logger.error("{} Federate Not Execution Member", unpublishErrorMessage);
					logger.error(f);
					return;
				} catch (InteractionClassNotDefined i) {
					logger.error("{} Interaction Class Not Defined", unpublishErrorMessage);
					logger.error(i);
					return;
				} catch (InteractionClassNotPublished i) {
					logger.error("{} Interaction Class Not Published", unpublishErrorMessage);
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
	private static String subscribeErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.HeatingControl:  could not subscribe:  ";
	/**
	* Subscribes a federate to the HeatingControl interaction class.
	*
	* @param rti handle to the Local RTI Component
	*/
	public static void subscribe(RTIambassador rti) {
		if (_isSubscribed) return;
		
		init(rti);
	
		
		synchronized(rti) {
			boolean isNotSubscribed = true;
			while(isNotSubscribed) {
				try {
					rti.subscribeInteractionClass(get_handle());
					isNotSubscribed = false;
				} catch (FederateNotExecutionMember f) {
					logger.error("{} Federate Not Execution Member", subscribeErrorMessage);
					logger.error(f);
					return;
				} catch (InteractionClassNotDefined i) {
					logger.error("{} Interaction Class Not Defined", subscribeErrorMessage);
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

	private static String unsubscribeErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.HeatingControl:  could not unsubscribe:  ";
	/**
	* Unsubscribes a federate from the HeatingControl interaction class.
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
					rti.unsubscribeInteractionClass(get_handle());
					isNotUnsubscribed = false;
				} catch (FederateNotExecutionMember f) {
					logger.error("{} Federate Not Execution Member", unsubscribeErrorMessage);
					logger.error(f);
					return;
				} catch (InteractionClassNotDefined i) {
					logger.error("{} Interaction Class Not Defined", unsubscribeErrorMessage);
					logger.error(i);
					return;
				} catch (InteractionClassNotSubscribed i) {
					logger.error("{} Interaction Class Not Subscribed", unsubscribeErrorMessage);
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
	* (that is, the HeatingControl interaction class).
	*
	* @param handle handle to compare to the value of the handle (RTI assigned) of
	* this class (the HeatingControl interaction class).
	* @return "true" if "handle" matches the value of the handle of this class
	* (that is, the HeatingControl interaction class).
	*/
	public static boolean match(int handle) { return handle == get_handle(); }

	/**
	* Returns the handle (RTI assigned) of this instance's interaction class .
	* 
	* @return the handle (RTI assigned) if this instance's interaction class
	*/
	public int getClassHandle() { return get_handle(); }

	/**
	* Returns the fully-qualified (dot-delimited) name of this instance's interaction class.
	* 
	* @return the fully-qualified (dot-delimited) name of this instance's interaction class
	*/
	public String getClassName() { return get_class_name(); }

	/**
	* Returns the simple name (last name in its fully-qualified dot-delimited name)
	* of this instance's interaction class.
	* 
	* @return the simple name of this instance's interaction class 
	*/
	public String getSimpleClassName() { return get_simple_class_name(); }

	/**
	* Returns a set containing the names of all of the non-hiddenparameters of an
	* interaction class instance.
	*
	* @return set containing the names of all of the parameters of an
	* interaction class instance
	*/
	public Set< String > getParameterNames() { return get_parameter_names(); }

	/**
	* Returns a set containing the names of all of the parameters of an
	* interaction class instance.
	*
	* @return set containing the names of all of the parameters of an
	* interaction class instance
	*/
	public Set< String > getAllParameterNames() { return get_all_parameter_names(); }

	/**
	* Publishes the interaction class of this instance of the class for a federate.
	*
	* @param rti handle to the Local RTI Component
	*/
	public void publishInteraction(RTIambassador rti) { publish(rti); }

	/**
	* Unpublishes the interaction class of this instance of this class for a federate.
	*
	* @param rti handle to the Local RTI Component
	*/
	public void unpublishInteraction(RTIambassador rti) { unpublish(rti); }

	/**
	* Subscribes a federate to the interaction class of this instance of this class.
	*
	* @param rti handle to the Local RTI Component
	*/
	public void subscribeInteraction(RTIambassador rti) { subscribe(rti); }

	/**
	* Unsubscribes a federate from the interaction class of this instance of this class.
	*
	* @param rti handle to the Local RTI Component
	*/
	public void unsubscribeInteraction(RTIambassador rti) { unsubscribe(rti); }

	

	public String toString() {
		return "HeatingControl("
			
			
			+ "heating_setpoint:" + get_heating_setpoint()
			+ "," + "name:" + get_name()
			+ ")";
	}
	



	
	
	private double _heating_setpoint = 0;
	
	private String _name = "";

	
	
	/**
	* Set the value of the "heating_setpoint" parameter to "value" for this parameter.
	*
	* @param value the new value for the "heating_setpoint" parameter
	*/
	public void set_heating_setpoint( double value ) { _heating_setpoint = value; }
	
	/**
	* Returns the value of the "heating_setpoint" parameter of this interaction.
	*
	* @return the value of the "heating_setpoint" parameter
	*/
	public double get_heating_setpoint() { return _heating_setpoint; }
	
	
	/**
	* Set the value of the "name" parameter to "value" for this parameter.
	*
	* @param value the new value for the "name" parameter
	*/
	public void set_name( String value ) { _name = value; }
	
	/**
	* Returns the value of the "name" parameter of this interaction.
	*
	* @return the value of the "name" parameter
	*/
	public String get_name() { return _name; }
	


	protected HeatingControl( ReceivedInteraction datamemberMap, boolean initFlag ) {
		super( datamemberMap, false );
		if ( initFlag ) setParameters( datamemberMap );
	}
	
	protected HeatingControl( ReceivedInteraction datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
		super( datamemberMap, logicalTime, false );
		if ( initFlag ) setParameters( datamemberMap );
	}


	/**
	* Creates an instance of the HeatingControl interaction class, using
	* "datamemberMap" to initialize its parameter values.
	* "datamemberMap" is usually acquired as an argument to an RTI federate
	* callback method, such as "receiveInteraction".
	*
	* @param datamemberMap data structure containing initial values for the
	* parameters of this new HeatingControl interaction class instance
	*/
	public HeatingControl( ReceivedInteraction datamemberMap ) {
		this( datamemberMap, true );
	}
	
	/**
	* Like {@link #HeatingControl( ReceivedInteraction datamemberMap )}, except this
	* new HeatingControl interaction class instance is given a timestamp of
	* "logicalTime".
	*
	* @param datamemberMap data structure containing initial values for the
	* parameters of this new HeatingControl interaction class instance
	* @param logicalTime timestamp for this new HeatingControl interaction class
	* instance
	*/
	public HeatingControl( ReceivedInteraction datamemberMap, LogicalTime logicalTime ) {
		this( datamemberMap, logicalTime, true );
	}

	/**
	* Creates a new HeatingControl interaction class instance that is a duplicate
	* of the instance referred to by HeatingControl_var.
	*
	* @param HeatingControl_var HeatingControl interaction class instance of which
	* this newly created HeatingControl interaction class instance will be a
	* duplicate
	*/
	public HeatingControl( HeatingControl HeatingControl_var ) {
		super( HeatingControl_var );
		
		
		set_heating_setpoint( HeatingControl_var.get_heating_setpoint() );
		set_name( HeatingControl_var.get_name() );
	}


	/**
	* Returns the value of the parameter whose name is "datamemberName"
	* for this interaction.
	*
	* @param datamemberName name of parameter whose value is to be
	* returned
	* @return value of the parameter whose name is "datamemberName"
	* for this interaction
	*/
	public Object getParameter( String datamemberName ) {
		
		
		
		if (  "heating_setpoint".equals( datamemberName )  ) return new Double(get_heating_setpoint());
		else if (  "name".equals( datamemberName )  ) return get_name();
		else return super.getParameter( datamemberName );
	}
	
	/**
	* Returns the value of the parameter whose handle (RTI assigned)
	* is "datamemberHandle" for this interaction.
	*
	* @param datamemberHandle handle (RTI assigned) of parameter whose
	* value is to be returned
	* @return value of the parameter whose handle (RTI assigned) is
	* "datamemberHandle" for this interaction
	*/
	public Object getParameter( int datamemberHandle ) {
		
				
		
		if ( get_heating_setpoint_handle() == datamemberHandle ) return new Double(get_heating_setpoint());
		else if ( get_name_handle() == datamemberHandle ) return get_name();
		else return super.getParameter( datamemberHandle );
	}
	
	protected boolean setParameterAux( int param_handle, String val ) {
		boolean retval = true;		
		
			
		
		if ( param_handle == get_heating_setpoint_handle() ) set_heating_setpoint( Double.parseDouble(val) );
		else if ( param_handle == get_name_handle() ) set_name( val );
		else retval = super.setParameterAux( param_handle, val );
		
		return retval;
	}
	
	protected boolean setParameterAux( String datamemberName, String val ) {
		boolean retval = true;
		
			
		
		if (  "heating_setpoint".equals( datamemberName )  ) set_heating_setpoint( Double.parseDouble(val) );
		else if (  "name".equals( datamemberName )  ) set_name( val );	
		else retval = super.setParameterAux( datamemberName, val );
		
		return retval;
	}
	
	protected boolean setParameterAux( String datamemberName, Object val ) {
		boolean retval = true;
		
		
		
		if (  "heating_setpoint".equals( datamemberName )  ) set_heating_setpoint( (Double)val );
		else if (  "name".equals( datamemberName )  ) set_name( (String)val );		
		else retval = super.setParameterAux( datamemberName, val );
		
		return retval;
	}

	protected SuppliedParameters createSuppliedDatamembers() {
		SuppliedParameters datamembers = super.createSuppliedDatamembers();

	
		
		
			datamembers.add( get_heating_setpoint_handle(), Double.toString(get_heating_setpoint()).getBytes() );
		
			datamembers.add( get_name_handle(), get_name().getBytes() );
		
	
		return datamembers;
	}

	
	public void copyFrom( Object object ) {
		super.copyFrom( object );
		if ( object instanceof HeatingControl ) {
			HeatingControl data = (HeatingControl)object;
			
			
				_heating_setpoint = data._heating_setpoint;
				_name = data._name;
			
		}
	}
}
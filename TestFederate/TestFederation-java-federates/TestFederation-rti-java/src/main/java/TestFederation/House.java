
package TestFederation;

import java.util.HashSet;
import java.util.Set;

import hla.rti.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;


import org.cpswt.hla.*;

/**
* The House class implements the House interaction in the
* TestFederation simulation.
*/
public class House extends GLDInteraction {

	private static final Logger logger = LogManager.getLogger(House.class);

	/**
	* Default constructor -- creates an instance of the House interaction
	* class with default parameter values.
	*/
	public House() { }

	
	
	private static int _air_temperature_handle;
	private static int _compressor_count_handle;
	private static int _compressor_on_handle;
	
	
	/**
	* Returns the handle (RTI assigned) of the "air_temperature" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "air_temperature" parameter
	*/
	public static int get_air_temperature_handle() { return _air_temperature_handle; }
	
	/**
	* Returns the handle (RTI assigned) of the "compressor_count" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "compressor_count" parameter
	*/
	public static int get_compressor_count_handle() { return _compressor_count_handle; }
	
	/**
	* Returns the handle (RTI assigned) of the "compressor_on" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "compressor_on" parameter
	*/
	public static int get_compressor_on_handle() { return _compressor_on_handle; }
	
	
	
	private static boolean _isInitialized = false;

	private static int _handle;

	/**
	* Returns the handle (RTI assigned) of the House interaction class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the handle of the class pertaining to the reference,\
	* rather than the handle of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassHandle()}.
	*/
	public static int get_handle() { return _handle; }

	/**
	* Returns the fully-qualified (dot-delimited) name of the House
	* interaction class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the name of the class pertaining to the reference,\
	* rather than the name of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassName()}.
	*/
	public static String get_class_name() { return "InteractionRoot.C2WInteractionRoot.GLDInteraction.House"; }

	/**
	* Returns the simple name (the last name in the dot-delimited fully-qualified
	* class name) of the House interaction class.
	*/
	public static String get_simple_class_name() { return "House"; }

	private static Set< String > _datamemberNames = new HashSet< String >();
	private static Set< String > _allDatamemberNames = new HashSet< String >();

	/**
	* Returns a set containing the names of all of the non-hidden parameters in the
	* House interaction class.
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
	* House interaction class.
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
		_classNameSet.add("InteractionRoot.C2WInteractionRoot.GLDInteraction.House");
		_classNameClassMap.put("InteractionRoot.C2WInteractionRoot.GLDInteraction.House", House.class);
		
		_datamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.GLDInteraction.House", _datamemberNames);
		_allDatamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.GLDInteraction.House", _allDatamemberNames);

		
		
		
		_datamemberNames.add("air_temperature");
		_datamemberNames.add("compressor_count");
		_datamemberNames.add("compressor_on");
		
		
		
		
		
		
		_allDatamemberNames.add("actualLogicalGenerationTime");
		_allDatamemberNames.add("air_temperature");
		_allDatamemberNames.add("compressor_count");
		_allDatamemberNames.add("compressor_on");
		_allDatamemberNames.add("federateFilter");
		_allDatamemberNames.add("name");
		_allDatamemberNames.add("originFed");
		_allDatamemberNames.add("sourceFed");
		
		
		_datamemberTypeMap.put("air_temperature", "double");
		_datamemberTypeMap.put("compressor_count", "int");
		_datamemberTypeMap.put("compressor_on", "boolean");
	
	

	}


	private static String initErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.GLDInteraction.House:  could not initialize:  ";
	protected static void init(RTIambassador rti) {
		if (_isInitialized) return;
		_isInitialized = true;
		
		GLDInteraction.init(rti);
		
		boolean isNotInitialized = true;
		while(isNotInitialized) {
			try {
				_handle = rti.getInteractionClassHandle("InteractionRoot.C2WInteractionRoot.GLDInteraction.House");
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

		_classNameHandleMap.put("InteractionRoot.C2WInteractionRoot.GLDInteraction.House", get_handle());
		_classHandleNameMap.put(get_handle(), "InteractionRoot.C2WInteractionRoot.GLDInteraction.House");
		_classHandleSimpleNameMap.put(get_handle(), "House");

		
		isNotInitialized = true;
		while(isNotInitialized) {
			try {
							
				_air_temperature_handle = rti.getParameterHandle("air_temperature", get_handle());			
				_compressor_count_handle = rti.getParameterHandle("compressor_count", get_handle());			
				_compressor_on_handle = rti.getParameterHandle("compressor_on", get_handle());
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
			
			
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.GLDInteraction.House,air_temperature", get_air_temperature_handle());
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.GLDInteraction.House,compressor_count", get_compressor_count_handle());
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.GLDInteraction.House,compressor_on", get_compressor_on_handle());
			
			
		_datamemberHandleNameMap.put(get_air_temperature_handle(), "air_temperature");
		_datamemberHandleNameMap.put(get_compressor_count_handle(), "compressor_count");
		_datamemberHandleNameMap.put(get_compressor_on_handle(), "compressor_on");
		
	}

	private static boolean _isPublished = false;
	private static String publishErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.GLDInteraction.House:  could not publish:  ";

	/**
	* Publishes the House interaction class for a federate.
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

	private static String unpublishErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.GLDInteraction.House:  could not unpublish:  ";
	/**
	* Unpublishes the House interaction class for a federate.
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
	private static String subscribeErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.GLDInteraction.House:  could not subscribe:  ";
	/**
	* Subscribes a federate to the House interaction class.
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

	private static String unsubscribeErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.GLDInteraction.House:  could not unsubscribe:  ";
	/**
	* Unsubscribes a federate from the House interaction class.
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
	* (that is, the House interaction class).
	*
	* @param handle handle to compare to the value of the handle (RTI assigned) of
	* this class (the House interaction class).
	* @return "true" if "handle" matches the value of the handle of this class
	* (that is, the House interaction class).
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
		return "House("
			
			
			+ "air_temperature:" + get_air_temperature()
			+ "," + "compressor_count:" + get_compressor_count()
			+ "," + "compressor_on:" + get_compressor_on()
			+ ")";
	}
	



	
	
	private double _air_temperature = 0;
	
	private int _compressor_count = 0;
	
	private boolean _compressor_on = false;

	
	
	/**
	* Set the value of the "air_temperature" parameter to "value" for this parameter.
	*
	* @param value the new value for the "air_temperature" parameter
	*/
	public void set_air_temperature( double value ) { _air_temperature = value; }
	
	/**
	* Returns the value of the "air_temperature" parameter of this interaction.
	*
	* @return the value of the "air_temperature" parameter
	*/
	public double get_air_temperature() { return _air_temperature; }
	
	
	/**
	* Set the value of the "compressor_count" parameter to "value" for this parameter.
	*
	* @param value the new value for the "compressor_count" parameter
	*/
	public void set_compressor_count( int value ) { _compressor_count = value; }
	
	/**
	* Returns the value of the "compressor_count" parameter of this interaction.
	*
	* @return the value of the "compressor_count" parameter
	*/
	public int get_compressor_count() { return _compressor_count; }
	
	
	/**
	* Set the value of the "compressor_on" parameter to "value" for this parameter.
	*
	* @param value the new value for the "compressor_on" parameter
	*/
	public void set_compressor_on( boolean value ) { _compressor_on = value; }
	
	/**
	* Returns the value of the "compressor_on" parameter of this interaction.
	*
	* @return the value of the "compressor_on" parameter
	*/
	public boolean get_compressor_on() { return _compressor_on; }
	


	protected House( ReceivedInteraction datamemberMap, boolean initFlag ) {
		super( datamemberMap, false );
		if ( initFlag ) setParameters( datamemberMap );
	}
	
	protected House( ReceivedInteraction datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
		super( datamemberMap, logicalTime, false );
		if ( initFlag ) setParameters( datamemberMap );
	}


	/**
	* Creates an instance of the House interaction class, using
	* "datamemberMap" to initialize its parameter values.
	* "datamemberMap" is usually acquired as an argument to an RTI federate
	* callback method, such as "receiveInteraction".
	*
	* @param datamemberMap data structure containing initial values for the
	* parameters of this new House interaction class instance
	*/
	public House( ReceivedInteraction datamemberMap ) {
		this( datamemberMap, true );
	}
	
	/**
	* Like {@link #House( ReceivedInteraction datamemberMap )}, except this
	* new House interaction class instance is given a timestamp of
	* "logicalTime".
	*
	* @param datamemberMap data structure containing initial values for the
	* parameters of this new House interaction class instance
	* @param logicalTime timestamp for this new House interaction class
	* instance
	*/
	public House( ReceivedInteraction datamemberMap, LogicalTime logicalTime ) {
		this( datamemberMap, logicalTime, true );
	}

	/**
	* Creates a new House interaction class instance that is a duplicate
	* of the instance referred to by House_var.
	*
	* @param House_var House interaction class instance of which
	* this newly created House interaction class instance will be a
	* duplicate
	*/
	public House( House House_var ) {
		super( House_var );
		
		
		set_air_temperature( House_var.get_air_temperature() );
		set_compressor_count( House_var.get_compressor_count() );
		set_compressor_on( House_var.get_compressor_on() );
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
		
		
		
		if (  "air_temperature".equals( datamemberName )  ) return new Double(get_air_temperature());
		else if (  "compressor_count".equals( datamemberName )  ) return new Integer(get_compressor_count());
		else if (  "compressor_on".equals( datamemberName )  ) return new Boolean(get_compressor_on());
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
		
				
		
		if ( get_air_temperature_handle() == datamemberHandle ) return new Double(get_air_temperature());
		else if ( get_compressor_count_handle() == datamemberHandle ) return new Integer(get_compressor_count());
		else if ( get_compressor_on_handle() == datamemberHandle ) return new Boolean(get_compressor_on());
		else return super.getParameter( datamemberHandle );
	}
	
	protected boolean setParameterAux( int param_handle, String val ) {
		boolean retval = true;		
		
			
		
		if ( param_handle == get_air_temperature_handle() ) set_air_temperature( Double.parseDouble(val) );
		else if ( param_handle == get_compressor_count_handle() ) set_compressor_count( Integer.parseInt(val) );
		else if ( param_handle == get_compressor_on_handle() ) set_compressor_on( Boolean.parseBoolean(val) );
		else retval = super.setParameterAux( param_handle, val );
		
		return retval;
	}
	
	protected boolean setParameterAux( String datamemberName, String val ) {
		boolean retval = true;
		
			
		
		if (  "air_temperature".equals( datamemberName )  ) set_air_temperature( Double.parseDouble(val) );
		else if (  "compressor_count".equals( datamemberName )  ) set_compressor_count( Integer.parseInt(val) );
		else if (  "compressor_on".equals( datamemberName )  ) set_compressor_on( Boolean.parseBoolean(val) );	
		else retval = super.setParameterAux( datamemberName, val );
		
		return retval;
	}
	
	protected boolean setParameterAux( String datamemberName, Object val ) {
		boolean retval = true;
		
		
		
		if (  "air_temperature".equals( datamemberName )  ) set_air_temperature( (Double)val );
		else if (  "compressor_count".equals( datamemberName )  ) set_compressor_count( (Integer)val );
		else if (  "compressor_on".equals( datamemberName )  ) set_compressor_on( (Boolean)val );		
		else retval = super.setParameterAux( datamemberName, val );
		
		return retval;
	}

	protected SuppliedParameters createSuppliedDatamembers() {
		SuppliedParameters datamembers = super.createSuppliedDatamembers();

	
		
		
			datamembers.add( get_air_temperature_handle(), Double.toString(get_air_temperature()).getBytes() );
		
			datamembers.add( get_compressor_count_handle(), Integer.toString(get_compressor_count()).getBytes() );
		
			datamembers.add( get_compressor_on_handle(), Boolean.toString(get_compressor_on()).getBytes() );
		
	
		return datamembers;
	}

	
	public void copyFrom( Object object ) {
		super.copyFrom( object );
		if ( object instanceof House ) {
			House data = (House)object;
			
			
				_air_temperature = data._air_temperature;
				_compressor_count = data._compressor_count;
				_compressor_on = data._compressor_on;
			
		}
	}
}

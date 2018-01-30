
package GridLabDTestFederation;

import java.util.HashSet;
import java.util.Set;

import hla.rti.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cpswt.utils.CpswtUtils;


import org.cpswt.hla.*;

/**
* The SimTime class implements the SimTime interaction in the
* GridLabDTestFederation simulation.
*/
public class SimTime extends SimulationControl {

	private static final Logger logger = LogManager.getLogger(SimTime.class);

	/**
	* Default constructor -- creates an instance of the SimTime interaction
	* class with default parameter values.
	*/
	public SimTime() { }

	
	
	private static int _timeScale_handle;
	private static int _timeStart_handle;
	private static int _timeStop_handle;
	private static int _timeZone_handle;
	
	
	/**
	* Returns the handle (RTI assigned) of the "timeScale" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "timeScale" parameter
	*/
	public static int get_timeScale_handle() { return _timeScale_handle; }
	
	/**
	* Returns the handle (RTI assigned) of the "timeStart" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "timeStart" parameter
	*/
	public static int get_timeStart_handle() { return _timeStart_handle; }
	
	/**
	* Returns the handle (RTI assigned) of the "timeStop" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "timeStop" parameter
	*/
	public static int get_timeStop_handle() { return _timeStop_handle; }
	
	/**
	* Returns the handle (RTI assigned) of the "timeZone" parameter of
	* its containing interaction class.
	*
	* @return the handle (RTI assigned) of the "timeZone" parameter
	*/
	public static int get_timeZone_handle() { return _timeZone_handle; }
	
	
	
	private static boolean _isInitialized = false;

	private static int _handle;

	/**
	* Returns the handle (RTI assigned) of the SimTime interaction class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the handle of the class pertaining to the reference,\
	* rather than the handle of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassHandle()}.
	*/
	public static int get_handle() { return _handle; }

	/**
	* Returns the fully-qualified (dot-delimited) name of the SimTime
	* interaction class.
	* Note: As this is a static method, it is NOT polymorphic, and so, if called on
	* a reference will return the name of the class pertaining to the reference,\
	* rather than the name of the class for the instance referred to by the reference.
	* For the polymorphic version of this method, use {@link #getClassName()}.
	*/
	public static String get_class_name() { return "InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime"; }

	/**
	* Returns the simple name (the last name in the dot-delimited fully-qualified
	* class name) of the SimTime interaction class.
	*/
	public static String get_simple_class_name() { return "SimTime"; }

	private static Set< String > _datamemberNames = new HashSet< String >();
	private static Set< String > _allDatamemberNames = new HashSet< String >();

	/**
	* Returns a set containing the names of all of the non-hidden parameters in the
	* SimTime interaction class.
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
	* SimTime interaction class.
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
		_classNameSet.add("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime");
		_classNameClassMap.put("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime", SimTime.class);
		
		_datamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime", _datamemberNames);
		_allDatamemberClassNameSetMap.put("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime", _allDatamemberNames);

		
		
		
		
		
		
		_datamemberNames.add("timeScale");
		_datamemberNames.add("timeStart");
		_datamemberNames.add("timeStop");
		_datamemberNames.add("timeZone");
		
		
		_allDatamemberNames.add("actualLogicalGenerationTime");
		_allDatamemberNames.add("federateFilter");
		_allDatamemberNames.add("originFed");
		_allDatamemberNames.add("sourceFed");
		_allDatamemberNames.add("timeScale");
		_allDatamemberNames.add("timeStart");
		_allDatamemberNames.add("timeStop");
		_allDatamemberNames.add("timeZone");
		
		
		_datamemberTypeMap.put("timeScale", "double");
		_datamemberTypeMap.put("timeStart", "int");
		_datamemberTypeMap.put("timeStop", "int");
		_datamemberTypeMap.put("timeZone", "String");
	
	

	}


	private static String initErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime:  could not initialize:  ";
	protected static void init(RTIambassador rti) {
		if (_isInitialized) return;
		_isInitialized = true;
		
		SimulationControl.init(rti);
		
		boolean isNotInitialized = true;
		while(isNotInitialized) {
			try {
				_handle = rti.getInteractionClassHandle("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime");
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

		_classNameHandleMap.put("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime", get_handle());
		_classHandleNameMap.put(get_handle(), "InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime");
		_classHandleSimpleNameMap.put(get_handle(), "SimTime");

		
		isNotInitialized = true;
		while(isNotInitialized) {
			try {
							
				_timeScale_handle = rti.getParameterHandle("timeScale", get_handle());			
				_timeStart_handle = rti.getParameterHandle("timeStart", get_handle());			
				_timeStop_handle = rti.getParameterHandle("timeStop", get_handle());			
				_timeZone_handle = rti.getParameterHandle("timeZone", get_handle());
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
			
			
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime,timeScale", get_timeScale_handle());
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime,timeStart", get_timeStart_handle());
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime,timeStop", get_timeStop_handle());
		_datamemberNameHandleMap.put("InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime,timeZone", get_timeZone_handle());
			
			
		_datamemberHandleNameMap.put(get_timeScale_handle(), "timeScale");
		_datamemberHandleNameMap.put(get_timeStart_handle(), "timeStart");
		_datamemberHandleNameMap.put(get_timeStop_handle(), "timeStop");
		_datamemberHandleNameMap.put(get_timeZone_handle(), "timeZone");
		
	}

	private static boolean _isPublished = false;
	private static String publishErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime:  could not publish:  ";

	/**
	* Publishes the SimTime interaction class for a federate.
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

	private static String unpublishErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime:  could not unpublish:  ";
	/**
	* Unpublishes the SimTime interaction class for a federate.
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
	private static String subscribeErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime:  could not subscribe:  ";
	/**
	* Subscribes a federate to the SimTime interaction class.
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

	private static String unsubscribeErrorMessage = "Error:  InteractionRoot.C2WInteractionRoot.SimulationControl.SimTime:  could not unsubscribe:  ";
	/**
	* Unsubscribes a federate from the SimTime interaction class.
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
	* (that is, the SimTime interaction class).
	*
	* @param handle handle to compare to the value of the handle (RTI assigned) of
	* this class (the SimTime interaction class).
	* @return "true" if "handle" matches the value of the handle of this class
	* (that is, the SimTime interaction class).
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
		return "SimTime("
			
			
			+ "timeScale:" + get_timeScale()
			+ "," + "timeStart:" + get_timeStart()
			+ "," + "timeStop:" + get_timeStop()
			+ "," + "timeZone:" + get_timeZone()
			+ ")";
	}
	



	
	
	private double _timeScale = 0;
	
	private int _timeStart = 0;
	
	private int _timeStop = 0;
	
	private String _timeZone = "";

	
	
	/**
	* Set the value of the "timeScale" parameter to "value" for this parameter.
	*
	* @param value the new value for the "timeScale" parameter
	*/
	public void set_timeScale( double value ) { _timeScale = value; }
	
	/**
	* Returns the value of the "timeScale" parameter of this interaction.
	*
	* @return the value of the "timeScale" parameter
	*/
	public double get_timeScale() { return _timeScale; }
	
	
	/**
	* Set the value of the "timeStart" parameter to "value" for this parameter.
	*
	* @param value the new value for the "timeStart" parameter
	*/
	public void set_timeStart( int value ) { _timeStart = value; }
	
	/**
	* Returns the value of the "timeStart" parameter of this interaction.
	*
	* @return the value of the "timeStart" parameter
	*/
	public int get_timeStart() { return _timeStart; }
	
	
	/**
	* Set the value of the "timeStop" parameter to "value" for this parameter.
	*
	* @param value the new value for the "timeStop" parameter
	*/
	public void set_timeStop( int value ) { _timeStop = value; }
	
	/**
	* Returns the value of the "timeStop" parameter of this interaction.
	*
	* @return the value of the "timeStop" parameter
	*/
	public int get_timeStop() { return _timeStop; }
	
	
	/**
	* Set the value of the "timeZone" parameter to "value" for this parameter.
	*
	* @param value the new value for the "timeZone" parameter
	*/
	public void set_timeZone( String value ) { _timeZone = value; }
	
	/**
	* Returns the value of the "timeZone" parameter of this interaction.
	*
	* @return the value of the "timeZone" parameter
	*/
	public String get_timeZone() { return _timeZone; }
	


	protected SimTime( ReceivedInteraction datamemberMap, boolean initFlag ) {
		super( datamemberMap, false );
		if ( initFlag ) setParameters( datamemberMap );
	}
	
	protected SimTime( ReceivedInteraction datamemberMap, LogicalTime logicalTime, boolean initFlag ) {
		super( datamemberMap, logicalTime, false );
		if ( initFlag ) setParameters( datamemberMap );
	}


	/**
	* Creates an instance of the SimTime interaction class, using
	* "datamemberMap" to initialize its parameter values.
	* "datamemberMap" is usually acquired as an argument to an RTI federate
	* callback method, such as "receiveInteraction".
	*
	* @param datamemberMap data structure containing initial values for the
	* parameters of this new SimTime interaction class instance
	*/
	public SimTime( ReceivedInteraction datamemberMap ) {
		this( datamemberMap, true );
	}
	
	/**
	* Like {@link #SimTime( ReceivedInteraction datamemberMap )}, except this
	* new SimTime interaction class instance is given a timestamp of
	* "logicalTime".
	*
	* @param datamemberMap data structure containing initial values for the
	* parameters of this new SimTime interaction class instance
	* @param logicalTime timestamp for this new SimTime interaction class
	* instance
	*/
	public SimTime( ReceivedInteraction datamemberMap, LogicalTime logicalTime ) {
		this( datamemberMap, logicalTime, true );
	}

	/**
	* Creates a new SimTime interaction class instance that is a duplicate
	* of the instance referred to by SimTime_var.
	*
	* @param SimTime_var SimTime interaction class instance of which
	* this newly created SimTime interaction class instance will be a
	* duplicate
	*/
	public SimTime( SimTime SimTime_var ) {
		super( SimTime_var );
		
		
		set_timeScale( SimTime_var.get_timeScale() );
		set_timeStart( SimTime_var.get_timeStart() );
		set_timeStop( SimTime_var.get_timeStop() );
		set_timeZone( SimTime_var.get_timeZone() );
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
		
		
		
		if (  "timeScale".equals( datamemberName )  ) return new Double(get_timeScale());
		else if (  "timeStart".equals( datamemberName )  ) return new Integer(get_timeStart());
		else if (  "timeStop".equals( datamemberName )  ) return new Integer(get_timeStop());
		else if (  "timeZone".equals( datamemberName )  ) return get_timeZone();
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
		
				
		
		if ( get_timeScale_handle() == datamemberHandle ) return new Double(get_timeScale());
		else if ( get_timeStart_handle() == datamemberHandle ) return new Integer(get_timeStart());
		else if ( get_timeStop_handle() == datamemberHandle ) return new Integer(get_timeStop());
		else if ( get_timeZone_handle() == datamemberHandle ) return get_timeZone();
		else return super.getParameter( datamemberHandle );
	}
	
	protected boolean setParameterAux( int param_handle, String val ) {
		boolean retval = true;		
		
			
		
		if ( param_handle == get_timeScale_handle() ) set_timeScale( Double.parseDouble(val) );
		else if ( param_handle == get_timeStart_handle() ) set_timeStart( Integer.parseInt(val) );
		else if ( param_handle == get_timeStop_handle() ) set_timeStop( Integer.parseInt(val) );
		else if ( param_handle == get_timeZone_handle() ) set_timeZone( val );
		else retval = super.setParameterAux( param_handle, val );
		
		return retval;
	}
	
	protected boolean setParameterAux( String datamemberName, String val ) {
		boolean retval = true;
		
			
		
		if (  "timeScale".equals( datamemberName )  ) set_timeScale( Double.parseDouble(val) );
		else if (  "timeStart".equals( datamemberName )  ) set_timeStart( Integer.parseInt(val) );
		else if (  "timeStop".equals( datamemberName )  ) set_timeStop( Integer.parseInt(val) );
		else if (  "timeZone".equals( datamemberName )  ) set_timeZone( val );	
		else retval = super.setParameterAux( datamemberName, val );
		
		return retval;
	}
	
	protected boolean setParameterAux( String datamemberName, Object val ) {
		boolean retval = true;
		
		
		
		if (  "timeScale".equals( datamemberName )  ) set_timeScale( (Double)val );
		else if (  "timeStart".equals( datamemberName )  ) set_timeStart( (Integer)val );
		else if (  "timeStop".equals( datamemberName )  ) set_timeStop( (Integer)val );
		else if (  "timeZone".equals( datamemberName )  ) set_timeZone( (String)val );		
		else retval = super.setParameterAux( datamemberName, val );
		
		return retval;
	}

	protected SuppliedParameters createSuppliedDatamembers() {
		SuppliedParameters datamembers = super.createSuppliedDatamembers();

	
		
		
			datamembers.add( get_timeScale_handle(), Double.toString(get_timeScale()).getBytes() );
		
			datamembers.add( get_timeStart_handle(), Integer.toString(get_timeStart()).getBytes() );
		
			datamembers.add( get_timeStop_handle(), Integer.toString(get_timeStop()).getBytes() );
		
			datamembers.add( get_timeZone_handle(), get_timeZone().getBytes() );
		
	
		return datamembers;
	}

	
	public void copyFrom( Object object ) {
		super.copyFrom( object );
		if ( object instanceof SimTime ) {
			SimTime data = (SimTime)object;
			
			
				_timeScale = data._timeScale;
				_timeStart = data._timeStart;
				_timeStop = data._timeStop;
				_timeZone = data._timeZone;
			
		}
	}
}

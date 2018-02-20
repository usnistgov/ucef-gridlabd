package TestFederation;

import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A test federate that controls three house objects for the GridLAB-D wrapper. 
 */
public class TestFederate extends TestFederateBase {
    private final static Logger log = LogManager.getLogger(TestFederate.class);

    private double currentTime = 0;
    
    private boolean useCelsius;
    
    private boolean workSchedule = false;
    
    private CoolingControlObject house2 = null;
    
    private CoolingControlObject house3 = null;

    public TestFederate(TestFederateConfig params) throws Exception {
        super(params);
        this.useCelsius = params.useCelsius;
    }

    private void CheckReceivedSubscriptions(String s) {
        log.trace("CheckReceivedSubscriptions {}", s);
        
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof GlobalVariables) {
                handleInteractionClass((GlobalVariables) interaction);
            }
            else if (interaction instanceof House) {
                handleInteractionClass((House) interaction);
            }
        }
 
        ObjectReflector reflector = null;
        while ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof GlobalVariablesObject) {
                handleObjectClass((GlobalVariablesObject) object);
            }
            else if (object instanceof HouseObject) {
                handleObjectClass((HouseObject) object);
            }
        }
    }

    private void execute() throws Exception {
        if(super.isLateJoiner()) {
            currentTime = super.getLBTS() - super.getLookAhead();
            super.disableTimeRegulation();
        }
        
        AdvanceTimeRequest atr = new AdvanceTimeRequest(currentTime);
        putAdvanceTimeRequest(atr);

        if(!super.isLateJoiner()) {
            readyToPopulate();
        }
        
        sendInitialValues();
        sendSimTime();
        
        if(!super.isLateJoiner()) {
            readyToRun();
        }

        startAdvanceTimeThread();

        // this is the exit condition of the following while loop
        // it is used to break the loop so that latejoiner federates can
        // notify the federation manager that they left the federation
        boolean exitCondition = false;

        while (true) {
            currentTime += super.getStepSize();

            atr.requestSyncStart();
            enteredTimeGrantedState();
            
            log.info("t={}", getLRC().queryFederateTime());
            CheckReceivedSubscriptions("Main Loop");
            updateCoolingSetpoints();

            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // DO NOT MODIFY FILE BEYOND THIS LINE
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            AdvanceTimeRequest newATR = new AdvanceTimeRequest(currentTime);
            putAdvanceTimeRequest(newATR);
            atr.requestSyncEnd();
            atr = newATR;

            if(exitCondition) {
                break;
            }
        }

        // while loop finished, notify FederationManager about resign
        super.notifyFederationOfResign();
    }
    
    private void handleInteractionClass(GlobalVariables interaction) {
        log.info("received {}", interaction.toString());
        updateWorkHours(interaction.get_clock());
    }
    
    private void handleInteractionClass(House interaction) {
        log.info("received {}", interaction.toString());
    }
    
    private void handleObjectClass(GlobalVariablesObject object) {
        log.info("received {}", object.toString());
        updateWorkHours(object.get_clock());
    }
    
    private void handleObjectClass(HouseObject object) {
        log.info("received {}", object.toString());
    }
    
    private double getCoolingSetpoint1() {
        if (useCelsius) {
            return workSchedule ? 24 : 21;
        }
        return workSchedule ? 75 : 69;
    }
    
    private double getCoolingSetpoint2() {
        if (useCelsius) {
            return workSchedule ? 25 : 23;
        }
        return workSchedule ? 77 : 73;
    }
    
    private double getCoolingSetpoint3() {
        if (useCelsius) {
            return 22;
        }
        return 71;
    }
    
    private void sendInitialValues()
            throws Exception {
        log.trace("sendInitialValues");
        
        CoolingControl house1 = create_CoolingControl();
        house1.set_name("house1");
        house1.set_cooling_setpoint(getCoolingSetpoint1());
        house1.sendInteraction(getLRC());
        log.debug("sent {}", house1.toString());
        
        house2 = new CoolingControlObject();
        house2.registerObject(getLRC());
        house2.set_name("house2");
        house2.set_cooling_setpoint(getCoolingSetpoint2());
        house2.updateAttributeValues(getLRC());
        log.debug("created {}", house2.toString());
        
        house3 = new CoolingControlObject();
        house3.registerObject(getLRC());
        house3.set_name("house3");
        house3.set_cooling_setpoint(getCoolingSetpoint3());
        house3.updateAttributeValues(getLRC());
        log.debug("created {}", house3.toString());
    }
    
    private void sendSimTime()
            throws Exception {
        log.trace("sendSimTime");
        
        SimTime time = create_SimTime();
        time.set_unixTimeStart(1243915200); // 2009-06-02 00:00:00 ET
        time.set_unixTimeStop(1244001600);  // 2009-06-03 00:00:00 ET
        time.set_timeScale(900);            // 15 minutes
        time.set_timeZone("US/MD/Baltimore");
        time.sendInteraction(getLRC());
        log.info("sent {}", time.toString());
    }
    
    private void updateCoolingSetpoints()
            throws Exception {
        log.trace("updateCoolingSetpoints");
        
        double cooling_setpoint1 = getCoolingSetpoint1();
        double cooling_setpoint2 = getCoolingSetpoint2();
        double cooling_setpoint3 = getCoolingSetpoint3();
        
        CoolingControl coolingControl = create_CoolingControl();
        coolingControl.set_name("house1");
        coolingControl.set_cooling_setpoint(cooling_setpoint1);
        coolingControl.sendInteraction(getLRC(), currentTime);
        log.info("set house1.cooling_setpoint={}", cooling_setpoint1);
        
        house2.set_cooling_setpoint(cooling_setpoint2);
        house2.updateAttributeValues(getLRC(), currentTime);
        log.info("set house2.cooling_setpoint={}", cooling_setpoint2);
        
        house3.set_cooling_setpoint(cooling_setpoint3);
        house3.updateAttributeValues(getLRC(), currentTime);
        log.info("set house3.cooling_setpoint={}", cooling_setpoint3);
    }
    
    private void updateWorkHours(String clock) {
        log.trace("updateWorkHours {}", clock);
        
        boolean workHours = isDuringWorkHours(clock);
        if (workHours && !workSchedule) {
            log.info("turning on work schedule at {}", clock);
            workSchedule = true;
        } else if (!workHours && workSchedule) {
            log.info("turning off work schedule at {}", clock);
            workSchedule = false;
        }
    }
    
    private boolean isDuringWorkHours(String timestamp) {
        log.trace("isDuringWorkHours {}", timestamp);
        
        // timestamp format is 'YYYY-MM-DD hh:mm:ss ZZZ'
        int hourStartIndex = timestamp.indexOf(' ') + 1;
        int hourEndIndex   = timestamp.indexOf(':');
        String hourString  = timestamp.substring(hourStartIndex, hourEndIndex);
        log.debug("extracted hour={} from {}", hourString, timestamp);
        
        int hourValue = Integer.parseInt(hourString);
        return hourValue >= 8 && hourValue < 17;
    }
    
    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser = new FederateConfigParser();
            TestFederateConfig federateConfig = federateConfigParser.parseArgs(args, TestFederateConfig.class);
            TestFederate federate = new TestFederate(federateConfig);
            federate.execute();

            System.exit(0);
        } catch (Exception e) {
            log.error("There was a problem executing the TestFederate federate: {}", e.getMessage());
            log.error(e);

            System.exit(1);
        }
    }
}

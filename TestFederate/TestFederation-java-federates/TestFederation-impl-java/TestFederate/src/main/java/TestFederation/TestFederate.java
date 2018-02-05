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
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof GlobalVariables) {
                handleInteractionClass((GlobalVariables) interaction);
            }
            else if (interaction instanceof House) {
                handleInteractionClass((House) interaction);
            }
            log.info("Interaction received and handled: " + s);
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
            log.info("Object received and handled: " + s);
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
        
        createObjects();
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
            
            CheckReceivedSubscriptions("Main Loop");
            updateCoolingControl();

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
        log.trace("handleInteractionClass GlobalVariables");
        
        String clock = interaction.get_clock();
        log.info("clock interaction {}", clock);
        updateWorkHours(clock);
    }
    
    private void handleInteractionClass(House interaction) {
        log.trace("handleInteractionClass House");
        
        log.info("house interaction [name={}, air_temperature={}, compressor_count={}, compressor_on={}]",
                interaction.get_name(),
                interaction.get_air_temperature(),
                interaction.get_compressor_count(),
                interaction.get_compressor_on());
    }
    
    private void handleObjectClass(GlobalVariablesObject object) {
        log.trace("handleObjectClass GlobalVariablesObject");
        
        String clock = object.get_clock();
        log.info("clock object {}", clock);
        updateWorkHours(clock);
    }
    
    private void handleObjectClass(HouseObject object) {
        log.trace("handleObjectClass HouseObject");
        
        log.info("house object [name={}, air_temperature={}, compressor_count={}, compressor_on={}]",
                object.get_name(),
                object.get_air_temperature(),
                object.get_compressor_count(),
                object.get_compressor_on());
    }
    
    private void createObjects() {
        log.trace("createObjects");
        
        house2 = new CoolingControlObject();
        house2.registerObject(getLRC());
        house2.set_name("house2");
        house2.set_cooling_setpoint(useCelsius ? 24 : 75); // sadly we need this for CPSWT
        house2.updateAttributeValues(getLRC());
        log.debug("created CoolingControlObject for house2");
        
        house3 = new CoolingControlObject();
        house3.registerObject(getLRC());
        house3.set_name("house3");
        house3.set_cooling_setpoint(useCelsius ? 24 : 75); // sadly we need this for CPSWT
        house3.updateAttributeValues(getLRC());
        log.debug("created CoolingControlObject for house3");
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
        log.debug("sent {}", time.toString());
    }
    
    private void updateCoolingControl()
            throws Exception {
        log.trace("updateCoolingSetpoints");
        
        double cooling_setpoint1;
        double cooling_setpoint2;
        double cooling_setpoint3;
        
        if (useCelsius) {
            cooling_setpoint1 = (workSchedule ? 24 : 21);
            cooling_setpoint2 = (workSchedule ? 25 : 23);
            cooling_setpoint3 = 22; 
        } else {
            cooling_setpoint1 = (workSchedule ? 75 : 69);
            cooling_setpoint2 = (workSchedule ? 77 : 73);
            cooling_setpoint3 = 71; 
        }
        
        CoolingControl cooling = create_CoolingControl();
        cooling.set_name("house1");
        cooling.set_cooling_setpoint(cooling_setpoint1);
        cooling.sendInteraction(getLRC(), currentTime);
        log.debug("set cooling_setpoint1 to {}", cooling_setpoint1);
        
        house2.set_cooling_setpoint(cooling_setpoint2);
        house2.updateAttributeValues(getLRC(), currentTime);
        log.debug("set cooling_setpoint2 to {}", cooling_setpoint2);
        
        house3.set_cooling_setpoint(cooling_setpoint3);
        house3.updateAttributeValues(getLRC(), currentTime);
        log.debug("set cooling_setpoint3 to {}", cooling_setpoint3);
    }
    
    private boolean isDuringWorkHours(String timestamp) {
        log.trace("isDuringWorkHours {}", timestamp);
        
        // timestamp format is 'YYYY-MM-DD hh:mm:ss ZZZ'
        int hourStartIndex = timestamp.indexOf(' ') + 1;
        int hourEndIndex   = timestamp.indexOf(':');
        String hourString  = timestamp.substring(hourStartIndex, hourEndIndex);
        
        int hourValue = Integer.parseInt(hourString);
        return hourValue >= 8 && hourValue < 17;
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

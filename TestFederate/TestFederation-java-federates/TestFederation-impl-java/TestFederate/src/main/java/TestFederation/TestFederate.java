package TestFederation;

import org.cpswt.config.FederateConfig;
import org.cpswt.config.FederateConfigParser;
import org.cpswt.hla.base.ObjectReflector;
import org.cpswt.hla.ObjectRoot;
import org.cpswt.hla.InteractionRoot;
import org.cpswt.hla.base.AdvanceTimeRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A test federate that controls two house objects for the GridLAB-D wrapper. 
 */
public class TestFederate extends TestFederateBase {
    private final static Logger log = LogManager.getLogger(TestFederate.class);

    private double currentTime = 0;
    
    private boolean workSchedule = false;

    public TestFederate(FederateConfig params) throws Exception {
        super(params);
    }

    private void CheckReceivedSubscriptions(String caller) {
        InteractionRoot interaction = null;
        while ((interaction = getNextInteractionNoWait()) != null) {
            if (interaction instanceof GlobalVariables) {
                handleInteractionClass((GlobalVariables) interaction);
            }
            log.info("Interaction received and handled: " + caller);
        }
 
        ObjectReflector reflector = null;
        while ((reflector = getNextObjectReflectorNoWait()) != null) {
            reflector.reflect();
            ObjectRoot object = reflector.getObjectRoot();
            if (object instanceof House) {
                handleObjectClass((House) object);
            }
            log.info("Object received and handled: " + caller);
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
            sendHouse1();
            sendHouse2();

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
    
    private void sendSimTime()
            throws Exception {
        log.trace("sendSimTime");
        
        SimTime time = create_SimTime();
        time.set_unixTimeStart(1243915200); // 2009-06-02 00:00:00 ET
        time.set_unixTimeStop(1244001600);  // 2009-06-03 00:00:00 ET
        time.set_timeScale(900);            // 15 minutes
        time.set_timeZone("US/MD/Baltimore");
        time.sendInteraction(getLRC());
    }
    
    private void sendHouse1()
            throws Exception {
        log.trace("sendHouse1");
        
        HeatingControl heating = create_HeatingControl();
        heating.set_name("house1");
        heating.set_heating_setpoint(70);
        heating.sendInteraction(getLRC());
        
        CoolingControl cooling = create_CoolingControl();
        cooling.set_name("house1");
        cooling.set_cooling_setpoint(73);
        cooling.sendInteraction(getLRC());
    }
    
    private void sendHouse2()
            throws Exception {
        log.trace("sendHouse2");
        
        double heating_setpoint = (workSchedule ? 58 : 68);
        double cooling_setpoint = (workSchedule ? 85 : 75);
        
        HeatingControl heating = create_HeatingControl();
        heating.set_name("house2");
        heating.set_heating_setpoint(heating_setpoint);
        heating.sendInteraction(getLRC());
        
        CoolingControl cooling = create_CoolingControl();
        cooling.set_name("house2");
        cooling.set_cooling_setpoint(cooling_setpoint);
        cooling.sendInteraction(getLRC());
    }

    private void handleInteractionClass(GlobalVariables interaction) {
        log.trace("handleInteractionClass GlobalVariables");
        
        String clock = interaction.get_clock();
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
        
        int hourValue = Integer.parseInt(hourString);
        return hourValue >= 8 && hourValue < 17;
    }
    
    private void handleObjectClass(House object) {
        log.info("received house update {}", object.toString());
    }
    
    public static void main(String[] args) {
        try {
            FederateConfigParser federateConfigParser = new FederateConfigParser();
            FederateConfig federateConfig = federateConfigParser.parseArgs(args, FederateConfig.class);
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

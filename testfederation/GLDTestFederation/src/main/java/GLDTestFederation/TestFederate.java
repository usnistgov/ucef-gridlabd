package GLDTestFederation;

import c2w.hla.InteractionRoot;
import c2w.hla.ObjectRoot;
import c2w.hla.base.AdvanceTimeRequest;
import c2w.hla.base.ObjectReflector;

/**
 * The TestFederate federate for the GLDTestFederation federation designed in WebGME.
 *
 * This federate MUST join the federation before the federation manager starts the experiment.
 * This means that, when the federate joins, the federation logical time must be 0 and both
 * the readyToPopulate and the readyToRun synchronization points must be defined.
 */
public class TestFederate extends TestFederateBase {
  public TestFederate(String[] args) throws Exception {
    super(args);
  }

  private void execute() throws Exception {
    double logicalTime = 0;
    double installedDoors = 2;
    double builtStories = 1;
    InteractionRoot interaction;

    AdvanceTimeRequest atr = new AdvanceTimeRequest(logicalTime);
    putAdvanceTimeRequest(atr);

    readyToPopulate();
    F1_house_A8 a8 = new F1_house_A8();
    a8.registerObject(getRTI());
    // send RO interaction SimTime
    SimTime init = create_SimTime();
    init.set_timeStart(1243933200);
    init.set_timeStop(1244019600);
    init.set_timeScale(600);
    init.sendInteraction(getRTI());
    readyToRun();

    startAdvanceTimeThread();
    
    while (true) {
      logicalTime += 1.0;

      atr.requestSyncStart();
      
      // install 1 new door every 2 hours
      if (((int)logicalTime) % 12 == 0) {
          installedDoors += 1;
          F1_house_A15 a15 = create_F1_house_A15();
          a15.set_number_of_doors(installedDoors);;
          a15.sendInteraction(getRTI(), logicalTime);
      }
      
      // build 1 new story every 6 hours
      if (((int)logicalTime) % 36 == 0) {
          builtStories += 1;
          a8.set_number_of_stories(builtStories);
          a8.updateAttributeValues(getRTI(), logicalTime);
      }
      
      while ((interaction = getNextInteractionNoWait()) != null) {
          if (interaction instanceof F1_house_A11) {
              F1_house_A11 a11 = (F1_house_A11)interaction;
              double setpoint = a11.get_cooling_setpoint();
              double temperature = a11.get_air_temperature();
              System.out.println("House A11 : temperature=" + temperature + ", setpoint=" + setpoint);
          } else if (interaction instanceof F1_house_A15) {
              F1_house_A15 a15 = (F1_house_A15)interaction;
              double doors = a15.get_number_of_doors();
              System.out.println("House A15 : numberOfDoors=" + doors);
          }
      }
      
      ObjectReflector reflector;
      while ((reflector = getNextObjectReflectorNoWait()) != null) {
          // update and retrieve the object instance
          reflector.reflect();
          ObjectRoot object = reflector.getObjectRoot();
          
          if (object instanceof market) {
              market m = (market)object;
              int period = m.get_period();
              String unit = m.get_unit();
              long id = m.get_market_id();
              System.out.println("market : id=" + id + ", unit=" + unit + ", period=" + period);
          } else if (object instanceof F1_house_A8) {
              F1_house_A8 house = (F1_house_A8)object;
              double stories = house.get_number_of_stories();
              System.out.println("House A8 : numberOfStories=" + stories);
          }
      }
      
      AdvanceTimeRequest newATR = new AdvanceTimeRequest(logicalTime);
      putAdvanceTimeRequest(newATR);
      atr.requestSyncEnd();
      atr = newATR;
    }
  }

  public static void main(String[] args) {
    try {
      TestFederate TestInstance = new TestFederate(args);
      TestInstance.execute();
    } catch (Exception e) {
      System.err.println("Exception caught: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
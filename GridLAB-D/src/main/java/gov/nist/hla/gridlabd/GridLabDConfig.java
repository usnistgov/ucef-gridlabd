package gov.nist.hla.gridlabd;

import gov.nist.hla.gateway.GatewayFederateConfig;
import gov.nist.hla.gateway.exception.ValueNotSet;

/**
 * This class defines the structure of the GridLAB-D federate configuration file for use with Jackson.
 *
 * @author Thomas Roth
 */
public class GridLabDConfig extends GatewayFederateConfig {
    /**
     * The path to a GridLAB-D model.
     */
    private String modelFilePath;
    private boolean modelFilePathSet = false;
    
    /**
     * The directory where GridLAB-D will execute.
     */
    private String workingDirectory = null;
    
    /**
     * The desired port number for GridLAB-D server mode.
     */
    private int serverPortNumber = 6267;
    
    /**
     * The time to wait between connection attempts to GridLAB-D server mode.
     */
    private int waitTimeMs = 500;
    
    /**
     * If true, the GridLAB-D federate will not start until it receives {@link ExtendedObjectModel#SIM_TIME}. As a side
     * effect, all the configuration options related to time in the JSON configuration file will be ignored in favor of
     * the values defined in the received interaction.
     */
    private boolean useSimTime = true;
    
    /**
     * The start time of the GridLAB-D simulation.
     */
    private long unixTimeStart;
    private boolean unixTimeStartSet = false;
    
    /**
     * The stop time of the GridLAB-D simulation, where a value of -1 indicates run until the federation terminates.
     */
    private long unixTimeStop;
    private boolean unixTimestopSet = false;
    
    /**
     * The number of simulation seconds in 1 logical time step.
     */
    private double simulationTimeScale;
    private boolean simulationTimeScaleSet = false;
    
    /**
     * The time zone for the GridLAB-D simulation.
     */
    private String simulationTimeZone;
    private boolean simulationTimeZoneSet = false;

    public void setModelFilePath(String filePath) {
        this.modelFilePath = filePath;
        this.modelFilePathSet = true;
    }
    
    public String getModelFilePath() {
        if (!modelFilePathSet) {
            throw new ValueNotSet("modelFilePath");
        }
        return modelFilePath;
    }
    
    public void setWorkingDirectory(String filePath) {
        this.workingDirectory = filePath;
    }
    
    public String getWorkingDirectory() {
        return workingDirectory;
    }
    
    public void setServerPortNumber(int portNumber) {
        if (portNumber < 0 || portNumber > 65535) {
            throw new RuntimeException("invalid port number " + portNumber);
        }
        this.serverPortNumber = portNumber;
    }
    
    public int getServerPortNumber() {
        return serverPortNumber;
    }
    
    public void setWaitTimeMs(int milliseconds) {
        if (milliseconds < 0) {
            throw new RuntimeException("invalid wait time " + milliseconds);
        }
        this.waitTimeMs = milliseconds;
    }
    
    public int getWaitTimeMs() {
        return waitTimeMs;
    }
    
    public void setUseSimTime(boolean flag) {
        this.useSimTime = flag;
    }
    
    public boolean getUseSimTime() {
        return useSimTime;
    }
    
    public void setUnixTimeStart(long unixTime) {
        if (unixTime < 0) {
            throw new RuntimeException("invalid unix time " + unixTime);
        }
        this.unixTimeStart = unixTime;
        this.unixTimeStartSet = true;
    }
    
    public long getUnixTimeStart() {
        if (!unixTimeStartSet) {
            throw new ValueNotSet("unixTimeStart");
        }
        return unixTimeStart;
    }
    
    public void setUnixTimeStop(long unixTime) {
        // negative values are valid and indicate to run forever
        this.unixTimeStop = unixTime;
        this.unixTimestopSet = true;
    }
    
    public long getUnixTimeStop() {
        if (!unixTimestopSet) {
            throw new ValueNotSet("unixTimeStop");
        }
        return unixTimeStop;
    }
    
    public void setSimulationTimeScale(double scale) {
        if (scale <= 0) {
            throw new RuntimeException("invalid time scale " + scale);
        }
        this.simulationTimeScale = scale;
        this.simulationTimeScaleSet = true;
    }
    
    public double getSimulationTimeScale() {
        if (!simulationTimeScaleSet) {
            throw new ValueNotSet("simulationTimeScale");
        }
        return simulationTimeScale;
    }
    
    public void setSimulationTimeZone(String timeZone) {
        this.simulationTimeZone = timeZone;
        this.simulationTimeZoneSet = true;
    }
    
    public String getSimulationTimeZone() {
        if (!simulationTimeZoneSet) {
            throw new ValueNotSet("simulationTimeZone");
        }
        return simulationTimeZone;
    }
}

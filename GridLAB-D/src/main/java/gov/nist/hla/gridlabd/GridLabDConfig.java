package gov.nist.hla.gridlabd;

import gov.nist.hla.gateway.GatewayFederateConfig;

public class GridLabDConfig extends GatewayFederateConfig {
    private String modelFilepath;
    private boolean modelFilepathSet = false;
    
    private String workingDirectory = null;
    
    private int serverPortNumber = 6267;
    
    private double simulationTimeScale;
    private boolean simulationTimeScaleSet = false;
    
    // if negative, expects to receive InteractionRoot.C2WInteractionRoot.SimControl.SimTime
    private long unixTimeStart = -1;
    
    // if negative, will run until receiving InteractionRoot.C2WInteractionRoot.SimControl.SimEnd
    private long unixTimeStop = -1; 
    
    private int waitAdvanceTimeMs = 500;

    public void setModelFilepath(String filepath) {
        this.modelFilepath = filepath;
        this.modelFilepathSet = true;
    }
    
    public String getModelFilepath() {
        if (!modelFilepathSet) {
            throw new ValueNotSet("model_filepath");
        }
        return modelFilepath;
    }
    
    public void setWorkingDirectory(String filepath) {
        this.workingDirectory = filepath;
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
    
    public void setSimulationTimeScale(double scale) {
        if (scale <= 0) {
            throw new RuntimeException("invalid time scale " + scale);
        }
        this.simulationTimeScale = scale;
        this.simulationTimeScaleSet = true;
    }
    
    public double getSimulationTimeScale() {
        if (!simulationTimeScaleSet) {
            throw new ValueNotSet("simulaton_time_scale");
        }
        return simulationTimeScale;
    }
    
    public void setUnixTimeStart(long unixTime) {
        this.unixTimeStart = unixTime;
    }
    
    public long getUnixTimeStart() {
        return unixTimeStart;
    }
    
    public void setUnixTimeStop(long unixTime) {
        this.unixTimeStop = unixTime;
    }
    
    public long getUnixTimeStop() {
        return unixTimeStop;
    }
    
    public void setWaitAdvanceTimeMs(int wait) {
        if (wait < 0) {
            throw new RuntimeException("invalid time advance wait time " + wait);
        }
        this.waitAdvanceTimeMs = wait;
    }
    
    public int getWaitAdvanceTimeMs() {
        return waitAdvanceTimeMs;
    }
}

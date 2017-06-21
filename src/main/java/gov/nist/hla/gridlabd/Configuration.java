package gov.nist.hla.gridlabd;

public class Configuration {
    private String federateName = "gridlabd";
    
    private String federationName;
    private boolean federationNameSet = false;
    
    private String somFilepath;
    private boolean somFilepathSet = false;
    
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
    
    private int maxConnectionAttempts = 5;
    
    private int waitReconnectMs = 2000;
    
    private double logicalTimeStep = 1;
    
    private double lookahead = 0.1;
    
    private int waitAdvanceTimeMs = 500;
    
    public void setFederateName(String name) {
        this.federateName = name;
    }
    
    public String getFederateName() {
        return federateName;
    }
    
    public void setFederationName(String name) {
        this.federationName = name;
        this.federationNameSet = true;
    }
    
    public String getFederationName() {
        if (!federationNameSet) {
            throw new ValueNotSet("federation_name");
        }
        return federationName;
    }
    
    public void setSomFilepath(String filepath) {
        this.somFilepath = filepath;
        this.somFilepathSet = true;
    }
    
    public String getSomFilepath() {
        if (!somFilepathSet) {
            throw new ValueNotSet("som_filepath");
        }
        return somFilepath;
    }
    
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
    
    public void setMaxConnectionAttempts(int max) {
        if (max < 1) {
            throw new RuntimeException("invalid max connection attempts " + max);
        }
        this.maxConnectionAttempts = max;
    }
    
    public int getMaxConnectionAttempts() {
        return maxConnectionAttempts;
    }
    
    public void setWaitReconnectMs(int wait) {
        if (wait < 0) {
            throw new RuntimeException("invalid reconnection wait time " + wait);
        }
        this.waitReconnectMs = wait;
    }
    
    public int getWaitReconnectMs() {
        return waitReconnectMs;
    }
    
    public void setLogicalTimeStep(double timestep) {
        if (timestep <= 0) {
            throw new RuntimeException("invalid logical timestep " + timestep);
        }
        this.logicalTimeStep = timestep;
    }
    
    public double getLogicalTimeStep() {
        return logicalTimeStep;
    }
    
    public void setLookahead(double lookahead) {
        if (lookahead < 0) { // does not check if lookahead < logical time step
            throw new RuntimeException("invalid lookahead time " + lookahead);
        }
        this.lookahead = lookahead;
    }
    
    public double getLookahead() {
        return lookahead;
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

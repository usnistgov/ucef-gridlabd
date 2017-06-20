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
        this.serverPortNumber = portNumber;
    }
    
    public int getServerPortNumber() {
        return serverPortNumber;
    }
    
    public void setSimulationTimeScale(double scale) {
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
        this.maxConnectionAttempts = max;
    }
    
    public int getMaxConnectionAttempts() {
        return maxConnectionAttempts;
    }
    
    public void setWaitReconnectMs(int wait) {
        this.waitReconnectMs = wait;
    }
    
    public int getWaitReconnectMs() {
        return waitReconnectMs;
    }
    
    public void setLogicalTimeStep(double timestep) {
        this.logicalTimeStep = timestep;
    }
    
    public double getLogicalTimeStep() {
        return logicalTimeStep;
    }
    
    public void setLookahead(double lookahead) {
        // should check if lookahead < timestep
        this.lookahead = lookahead;
    }
    
    public double getLookahead() {
        return lookahead;
    }
    
    public void setWaitAdvanceTimeMs(int wait) {
        this.waitAdvanceTimeMs = wait;
    }
    
    public int getWaitAdvanceTimeMs() {
        return waitAdvanceTimeMs;
    }
}

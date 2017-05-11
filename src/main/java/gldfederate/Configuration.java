package gldfederate;

public class Configuration {
    private int waitReconnectMs;
    private int waitAdvanceTimeMs;
    private int maxConnectionAttempts;
    private int logicalTimeStep;
    private int simulationTimeStep;
    
    private long timeInit;
    private long timeStart;
    private long timeStop;
    
    private String modelFilepath;
    private String workingDirectory;
    
    public void setWaitReconnectMs(int wait) {
        this.waitReconnectMs = wait;
    }
    
    public int getWaitReconnectMs() {
        return waitReconnectMs;
    }
    
    public void setWaitAdvanceTimeMs(int wait) {
        this.waitAdvanceTimeMs = wait;
    }
    
    public int getWaitAdvanceTimeMs() {
        return waitAdvanceTimeMs;
    }
    
    public void setMaxConnectionAttempts(int max) {
        this.maxConnectionAttempts = max;
    }
    
    public int getMaxConnectionAttempts() {
        return maxConnectionAttempts;
    }
    
    public void setLogicalTimeStep(int step) {
        this.logicalTimeStep = step;
    }
    
    public int getLogicalTimeStep() {
        return logicalTimeStep;
    }
    
    public void setSimulationTimeStep(int step) {
        this.simulationTimeStep = step;
    }
    
    public int getSimulationTimeStep() {
        return simulationTimeStep;
    }
    
    public void setTimeInit(long unixTime) {
        this.timeInit = unixTime;
    }
    
    public long getTimeInit() {
        return timeInit;
    }
    
    public void setTimeStart(long unixTime) {
        this.timeStart = unixTime;
    }
    
    public long getTimeStart() {
        return timeStart;
    }
    
    public void setTimeStop(long unixTime) {
        this.timeStop = unixTime;
    }
    
    public long getTimeStop() {
        return timeStop;
    }
    
    public void setModelFilepath(String filepath) {
        this.modelFilepath = filepath;
    }
    
    public String getModelFilepath() {
        return modelFilepath;
    }
    
    public void setWorkingDirectory(String filepath) {
        this.workingDirectory = filepath;
    }
    
    public String getWorkingDirectory() {
        return workingDirectory;
    }
}

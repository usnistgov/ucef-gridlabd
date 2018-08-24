package gov.nist.hla.gridlabd;

import java.util.Objects;

public class ObjectInstanceInfo {
    private final String instanceName;
    
    private final String gridlabdName;
    
    private final String className;
    
    public ObjectInstanceInfo(String className, String instanceName, String gridlabdName) {
        this.className    = className;
        this.instanceName = instanceName;
        this.gridlabdName = gridlabdName;
    }
    
    public String getClassName() {
        return className;
    }
    
    public String getInstanceName() {
        return instanceName;
    }
    
    public String getGridLabDName() {
        return gridlabdName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ObjectInstanceInfo)) {
            return false;
        }
        
        ObjectInstanceInfo namePair = (ObjectInstanceInfo) o;
        return (Objects.equals(instanceName, namePair.instanceName) &&
                Objects.equals(gridlabdName, namePair.gridlabdName));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(instanceName, gridlabdName);
    }
    
    @Override
    public String toString() {
        return "(" + className + "." + instanceName + "," + gridlabdName + ")";
    }
}

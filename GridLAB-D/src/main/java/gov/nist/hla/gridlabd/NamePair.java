package gov.nist.hla.gridlabd;

import java.util.Objects;

public class NamePair {
    private final String instanceName;
    
    private final String gridlabdName;
    
    public NamePair(String instanceName, String gridlabdName) {
        this.instanceName = instanceName;
        this.gridlabdName = gridlabdName;
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
        if (!(o instanceof NamePair)) {
            return false;
        }
        
        NamePair namePair = (NamePair) o;
        return (Objects.equals(instanceName, namePair.instanceName) &&
                Objects.equals(gridlabdName, namePair.gridlabdName));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(instanceName, gridlabdName);
    }
    
    @Override
    public String toString() {
        return "(" + instanceName + "," + gridlabdName + ")";
    }
}

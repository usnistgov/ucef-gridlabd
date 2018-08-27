package gov.nist.hla.gridlabd;

import java.util.Objects;

/**
 * This class associates an HLA object instance with a GridLAB-D simulation object.
 *
 * @author Thomas Roth
 */
public class ObjectInstanceInfo {
    private final String className;
    
    private final String instanceName;
    
    private final String gridlabdName;
    
    /**
     * Create a new association between an HLA object instance and an object in the GridLAB-D simulation model.
     *
     * @param className The fully qualified name of the HLA object class.
     * @param instanceName The name of the object instance in the HLA federation.
     * @param gridlabdName The name of the equivalent object in the GridLAB-D simulation.
     */
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

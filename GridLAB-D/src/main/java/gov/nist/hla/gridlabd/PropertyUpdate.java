package gov.nist.hla.gridlabd;

import java.util.Objects;

/**
 * This class associates a GridLAB-D object property with the value it should be assigned.
 * 
 * @author Thomas Roth
 */
public class PropertyUpdate {
    private final String objectName;
    
    private final String propertyName;
    
    private final String propertyValue;
    
    private final String unit;
    
    /**
     * Create a new GridLAB-D property update.
     *
     * @param objectName The name of an object in the GridLAB-D simulation.
     * @param propertyName The name of some property of the GridLAB-D object.
     * @param propertyValue The value that should be associated with the GridLAB-D object's property.
     * @param unit An optional unit for the value, where null indicates use the GridLAB-D default unit.
     */
    public PropertyUpdate(String objectName, String propertyName, String propertyValue, String unit) {
        this.objectName = objectName;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.unit = unit;
    }
    
    public String getObjectName() {
        return objectName;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public String getPropertyValue() {
        return propertyValue;
    }
    
    public String getUnit() {
        return unit;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PropertyUpdate)) {
            return false;
        }
        
        PropertyUpdate propertyUpdate = (PropertyUpdate) o;
        return (Objects.equals(objectName, propertyUpdate.objectName) &&
                Objects.equals(propertyName, propertyUpdate.propertyName));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(objectName, propertyName);
    }
    
    @Override
    public String toString() {
        return objectName + ":" + propertyName + "=" + propertyValue + " [" + unit + "]";
    }
}

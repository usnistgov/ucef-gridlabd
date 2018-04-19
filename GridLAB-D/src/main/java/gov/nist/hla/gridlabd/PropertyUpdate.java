package gov.nist.hla.gridlabd;

import java.util.Objects;

public class PropertyUpdate {
    private final String objectName;
    
    private final String propertyName;
    
    private final String propertyValue;
    
    private final String unit;
    
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

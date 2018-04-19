package gov.nist.hla.gridlabd;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectState {
    private final String className;
    
    private final String instanceName;
    
    private Map<String, String> attributes = new HashMap<String, String>();
    
    public ObjectState(String className, String instanceName) {
        this.className = className;
        this.instanceName = instanceName;
    }
    
    public void reflectAttributes(Map<String, String> attributes) {
        this.attributes.putAll(attributes);
    }
    
    public String getClassName() {
        return className;
    }
    
    public String getInstanceName() { 
        return instanceName;
    }
    
    public String getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }
    
    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
    
    @Override
    public String toString() {
        return className + ":" + instanceName + "=" + attributes.toString();
    }
}

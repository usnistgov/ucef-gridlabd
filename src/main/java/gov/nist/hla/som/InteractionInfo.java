package gov.nist.hla.som;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class InteractionInfo {
    private String name;
    
    private Sharing sharing = null;
    
    private HashMap<String, ParameterInfo> parameters = new HashMap<String, ParameterInfo>();
    
    public InteractionInfo(String name, Sharing sharing, LinkedList<ParameterInfo> parameters, InteractionInfo parent)
            throws ObjectModelException {
        if (name == null || sharing == null || parameters == null) {
            throw new NullPointerException("InteractionInfo constructor received a null argument");
        }
        if (name.isEmpty()) {
            throw new ObjectModelException("interaction name is empty");
        }
        if (name.contains(".")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("interaction name contains the illegal character '.'");
        }
        if (name.contains(":")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("interaction name contains the illegal character ':'");
        }
        if (name.toLowerCase().equals("na")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("interaction name is the illegal string 'na'");
        }
        this.name = name;
        this.sharing = sharing;
        
        for (ParameterInfo param : parameters) {
            if (this.parameters.put(param.getName(), param) != null) {
                throw new ObjectModelException("interaction class defines multiple parameters with the same name");
            }
        }
        if (parent != null) {
            this.name = parent.name + "." + this.name;
            for (Map.Entry<String, ParameterInfo> entry : parent.parameters.entrySet()) {
                if (!this.parameters.containsKey(entry.getKey())) {
                    this.parameters.put(entry.getKey(), entry.getValue());
                } else {
                    // do nothing; we allow a child class to redefine parent parameters
                }
            }
        }
    }

    public String getName() {
        return name;
    }
    
    public Sharing getSharing() {
        return sharing;
    }
    
    public boolean isPublished() {
        return sharing.isPublished();
    }
    
    public boolean isSubscribed() {
        return sharing.isSubscribed();
    }
    
    public Set<String> getParameterSet() {
        return parameters.keySet();
    }
    
    public ParameterInfo getParameter(String parameter) {
        return parameters.get(parameter);
    }
}

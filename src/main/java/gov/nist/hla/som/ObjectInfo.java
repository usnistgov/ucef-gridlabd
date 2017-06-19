package gov.nist.hla.som;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class ObjectInfo {
    private String name;
    
    private Sharing sharing;
    
    private HashMap<String, AttributeInfo> attributes = new HashMap<String, AttributeInfo>();
    
    private HashSet<String> publishedAttributes = new HashSet<String>();
    
    private HashSet<String> subscribedAttributes = new HashSet<String>();
    
    public ObjectInfo(String name, LinkedList<AttributeInfo> attributes, ObjectInfo parent)
            throws ObjectModelException {
        if (name == null || attributes == null) {
            throw new NullPointerException("ObjectInfo constructor received a null argument");
        }
        if (name.isEmpty()) {
            throw new ObjectModelException("object name is empty");
        }
        if (name.contains(".")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("object name contains the illegal character '.'");
        }
        if (name.contains(":")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("object name contains the illegal character ':'");
        }
        if (name.toLowerCase().equals("na")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("object name is the illegal string 'na'");
        }
        this.name = name;
        this.sharing = new Sharing("neither");
        
        for (AttributeInfo attribute : attributes) {
            String attributeName = attribute.getName();
            if (this.attributes.put(attributeName, attribute) != null) {
                throw new ObjectModelException("object class defines multiple attributes with the same name");
            }
            if (attribute.isPublished()) {
                publishedAttributes.add(attributeName);
            }
            if (attribute.isSubscribed()) {
                subscribedAttributes.add(attributeName);
            }
            this.sharing = new Sharing(this.sharing, attribute.getSharing());
        }
        if (parent != null) {
            this.name = parent.name + "." + this.name;
            
            for (Map.Entry<String, AttributeInfo> entry : parent.attributes.entrySet()) {
                String attributeName = entry.getKey();
                AttributeInfo attribute = entry.getValue();
                if (!this.attributes.containsKey(attributeName)) {
                    this.attributes.put(attributeName, attribute);
                    if (attribute.isPublished()) {
                        publishedAttributes.add(attributeName);
                    }
                    if (attribute.isSubscribed()) {
                        subscribedAttributes.add(attributeName);
                    }
                    this.sharing = new Sharing(this.sharing, attribute.getSharing());
                } else {
                    // do nothing; we allow a child class to redefine parent attributes
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
    
    public Set<String> getAttributeSet() {
        return attributes.keySet();
    }
    
    public AttributeInfo getAttribute(String attribute) {
        return attributes.get(attribute);
    }
    
    public Set<String> getPublishedAttributes() {
        return Collections.unmodifiableSet(publishedAttributes);
    }
    
    public Set<String> getSubscribedAttributes() {
        return Collections.unmodifiableSet(subscribedAttributes);
    }
}

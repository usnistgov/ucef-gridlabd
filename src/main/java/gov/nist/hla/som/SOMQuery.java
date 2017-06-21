package gov.nist.hla.som;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SOMQuery {
    private static final Logger logger = LogManager.getLogger();
    
    private SOMReader simulationObjectModel;
    
    private HashMap<String, ObjectInfo> objectClasses = new HashMap<String, ObjectInfo>();
    private HashSet<String> publishedObjects = new HashSet<String>();
    private HashSet<String> subscribedObjects = new HashSet<String>();
    
    private HashMap<String, InteractionInfo> interactionClasses = new HashMap<String, InteractionInfo>();
    private HashSet<String> publishedInteractions = new HashSet<String>();
    private HashSet<String> subscribedInteractions = new HashSet<String>();
    
    public SOMQuery(SOMReader simulationObjectModel) {
        this.simulationObjectModel = simulationObjectModel;
        
        for (InteractionInfo interaction : this.simulationObjectModel.getInteractionClasses()) {
            String interactionName = interaction.getName();
            
            interactionClasses.put(interactionName, interaction);
            if (interaction.isPublished()) {
                publishedInteractions.add(interactionName);
            }
            if (interaction.isSubscribed()) {
                subscribedInteractions.add(interactionName);
            }
        }
        logger.info("published interactions: " + publishedInteractions.toString());
        logger.info("subscribed interactions: " + subscribedInteractions.toString());
        
        for (ObjectInfo object : this.simulationObjectModel.getObjectClasses()) {
            String objectName = object.getName();
            
            objectClasses.put(objectName, object);
            if (object.isPublished()) {
                publishedObjects.add(objectName);
            }
            if (object.isSubscribed()) {
                subscribedObjects.add(objectName);
            }
        }
        logger.info("published object classes: " + publishedObjects.toString());
        logger.info("subscribed object classes: " + subscribedObjects.toString());
    }
    
    public Set<String> getPublishedInteractions() {
        return Collections.unmodifiableSet(publishedInteractions);
    }
    
    public Set<String> getSubscribedInteractions() {
        return Collections.unmodifiableSet(subscribedInteractions);
    }
    
    public Set<String> getParameterSet(String interactionClass) {
        if (interactionClasses.containsKey(interactionClass)) {
            return interactionClasses.get(interactionClass).getParameterSet();
        }
        return null;
    }
    
    public String getParameterType(String interactionClass, String parameterName) {
        if (interactionClasses.containsKey(interactionClass)) {
            ParameterInfo parameter = interactionClasses.get(interactionClass).getParameter(parameterName);
            if (parameter != null) {
                return parameter.getDataType();
            }
        }
        return null;
    }
    
    public Set<String> getPublishedObjects() {
        return Collections.unmodifiableSet(publishedObjects);
    }
    
    public Set<String> getSubscribedObjects() {
        return Collections.unmodifiableSet(subscribedObjects);
    }
    
    public Set<String> getAttributeSet(String objectClass) {
        if (objectClasses.containsKey(objectClass)) {
            return objectClasses.get(objectClass).getAttributeSet();
        }
        return null;
    }
    
    public Set<String> getPublishedAttributes(String objectClass) {
        if (objectClasses.containsKey(objectClass)) {
            return objectClasses.get(objectClass).getPublishedAttributes();
        }
        return null;
    }
    
    public Set<String> getSubscribedAttributes(String objectClass) {
        if (objectClasses.containsKey(objectClass)) {
            return objectClasses.get(objectClass).getSubscribedAttributes();
        }
        return null;
    }
    
    public String getAttributeType(String objectClass, String attributeName) {
        if (objectClasses.containsKey(objectClass)) {
            AttributeInfo attribute = objectClasses.get(objectClass).getAttribute(attributeName);
            if (attribute != null) {
                return attribute.getDataType();
            }
        }
        return null;
    }
}

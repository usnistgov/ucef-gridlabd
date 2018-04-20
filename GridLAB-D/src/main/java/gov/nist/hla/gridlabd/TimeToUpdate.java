package gov.nist.hla.gridlabd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ieee.standards.ieee1516._2010.AttributeType;
import org.ieee.standards.ieee1516._2010.InteractionClassType;
import org.ieee.standards.ieee1516._2010.ObjectClassType;

public class TimeToUpdate {
    private static final Logger log = LogManager.getLogger();
    
    private Set<String> thingsToUpdate = new HashSet<String>();
    
    private Map<String, Double> updatePeriod = new HashMap<String, Double>();
    
    private Map<String, Double> nextUpdateTime = new HashMap<String, Double>();
    
    // what if something has no update period ?
    public TimeToUpdate(ExtendedObjectModel objectModel) {
        for (InteractionClassType interaction : objectModel.getPublishedInteractions()) {
            if (objectModel.isCoreInteraction(interaction)) {
                continue;
            }
            final String id = objectModel.getClassPath(interaction);
            final double period = objectModel.getUpdatePeriod(interaction);
            
            updatePeriod.put(id, period); // -1 if each time step
            
            if (period > 0) {
                thingsToUpdate.add(id); // for t=0.0
                nextUpdateTime.put(id, period);
            }
        }
        
        for (ObjectClassType object : objectModel.getPublishedObjects()) {
            if (objectModel.isCoreObject(object)) {
                continue;
            }
            for (AttributeType attribute : objectModel.getPublishedAttributes(object)) {
                if (!objectModel.isRelevantAttribute(attribute)) {
                    continue;
                }
                final String id = objectModel.getClassPath(object) + "." + attribute.getName().getValue();
                final double period = objectModel.getUpdatePeriod(attribute);
                
                updatePeriod.put(id, period); // -1 if each time step
                
                if (period > 0) {
                    thingsToUpdate.add(id); // for t=0.0
                    nextUpdateTime.put(id, period);
                }
            }
        }
    }
    
    public void step(double logicalTime) {
        log.trace("step {}", logicalTime);
        thingsToUpdate.clear();
        
        for (String id : updatePeriod.keySet()) {
            if (nextUpdateTime.containsKey(id)) {
                log.trace("on {}", id);
                double updateTime = nextUpdateTime.get(id);
                
                if (updateTime <= logicalTime) {
                    while (updateTime <= logicalTime) {
                        updateTime += updatePeriod.get(id);
                    }
                    nextUpdateTime.put(id, updateTime);
                    thingsToUpdate.add(id);
                    log.debug("next update for {} @ t={}", id, updateTime);
                }
            }
        }
    }
    
    public boolean isTimeToUpdate(String interactionClassPath) {
        return thingsToUpdate.contains(interactionClassPath);
    }
    
    public boolean isTimeToUpdate(String objectClassPath, String attributeName) {
        return thingsToUpdate.contains(objectClassPath + "." + attributeName);
    }
}

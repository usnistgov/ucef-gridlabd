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
    
    public TimeToUpdate(ExtendedObjectModel objectModel) {
        for (InteractionClassType interaction : objectModel.getPublishedInteractions()) {
            final String id = objectModel.getClassPath(interaction);
            final double period = objectModel.getUpdatePeriod(interaction);
            
            thingsToUpdate.add(id); // for t=0.0
            updatePeriod.put(id, period);
            nextUpdateTime.put(id, period);
        }
        
        for (ObjectClassType object : objectModel.getPublishedObjects()) {
            for (AttributeType attribute : objectModel.getPublishedAttributes(object)) {
                final String id = objectModel.getClassPath(object) + "." + attribute.getName().getValue();
                final double period = objectModel.getUpdatePeriod(attribute);
                
                thingsToUpdate.add(id); // for t=0.0
                updatePeriod.put(id, period);
                nextUpdateTime.put(id, period);
            }
        }
    }
    
    public void step(double logicalTime) {
        thingsToUpdate.clear();
        
        for (String id : nextUpdateTime.keySet()) {
            double updateTime = nextUpdateTime.get(id);
            
            if (updateTime <= logicalTime) {
                while (updateTime <= logicalTime) {
                    updateTime += updatePeriod.get(id);
                }
                nextUpdateTime.put(id, updateTime);
                thingsToUpdate.add(id);
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

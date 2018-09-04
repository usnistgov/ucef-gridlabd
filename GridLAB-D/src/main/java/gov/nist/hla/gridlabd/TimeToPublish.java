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

/**
 * This class parses the simulation object model to determine when each interaction and object attribute should next be
 * published to the federation. The method {@link #setLogicalTime} must be invoked each logical time step in order for
 * the public methods to return the correct value for the current logical time.
 *
 * @author Thomas Roth
 */
public class TimeToPublish {
    private static final Logger log = LogManager.getLogger();
    
    private Set<String> thingsToPublish = new HashSet<String>();
    
    private Map<String, Double> updatePeriod = new HashMap<String, Double>();
    
    private Map<String, Double> lastPublishedTime = new HashMap<String, Double>();
    
    private double lastLogicalTime = -1;
    
    /**
     * Create a new instance of this class associated with the given object model.
     * 
     * @param objectModel The simulation object model that defines the update periods.
     */
    public TimeToPublish(ExtendedObjectModel objectModel) {
        parseInteractions(objectModel);
        parseObjects(objectModel);
    }
    
    private void parseInteractions(ExtendedObjectModel objectModel) {
        for (InteractionClassType interaction : objectModel.getPublishedInteractions()) {
            if (objectModel.isCoreInteraction(interaction) || !objectModel.isGldObject(interaction)) {
                continue;
            }
            
            final String id = objectModel.getClassPath(interaction);
            final double period = objectModel.getUpdatePeriod(interaction);
            updatePeriod.put(id, period); // -1 if each time step
        }
    }
    
    private void parseObjects(ExtendedObjectModel objectModel) {
        for (ObjectClassType object : objectModel.getPublishedObjects()) {
            if (objectModel.isCoreObject(object) || !objectModel.isGldObject(object)) {
                continue;
            }
            
            for (AttributeType attribute : objectModel.getPublishedAttributes(object)) {
                if (!objectModel.isGldProperty(attribute)) {
                    continue;
                }
                
                final String id = objectModel.getClassPath(object) + "." + attribute.getName().getValue();
                final double period = objectModel.getUpdatePeriod(attribute);
                updatePeriod.put(id, period); // -1 if each time step
            }
        }
    }
    
    /**
     * Set the logical time used for the {@link #isTimeToPublish} methods. Consecutive calls to this method must use
     * monotonically increasing values for logical time.
     * 
     * @param logicalTime A monotonically increasing value for the next logical time step.
     */
    public void setLogicalTime(double logicalTime) {
        if (logicalTime < 0) {
            log.error("logical time cannot be negative: {}", logicalTime);
        } else if (logicalTime < lastLogicalTime) {
            log.error("cannot return to the previous time step {} after t = {}", logicalTime, lastLogicalTime);
        } else if (logicalTime > lastLogicalTime) {
            update(logicalTime);
            lastLogicalTime = logicalTime;
        } // ignore the case where logicalTime == previousLogicalTime
    }
    
    private void update(double logicalTime) {
        thingsToPublish.clear();
        
        for (Map.Entry<String, Double> entry : updatePeriod.entrySet()) {
            final String id = entry.getKey();
            final double period = entry.getValue();
            
            if (!lastPublishedTime.containsKey(id) // first update
                    || period < 0 // always update when period = -1
                    || logicalTime >= lastPublishedTime.get(id) + period) {
                thingsToPublish.add(id);
                lastPublishedTime.put(id, logicalTime);
            }
        }
    }
    
    /**
     * Check whether an interaction class should be published at the logical time given to {@link #setLogicalTime}.
     *
     * @param interactionClassPath The fully qualified class path of an HLA interaction class.
     * @return True if the interaction should be published.
     */
    public boolean isTimeToPublish(String interactionClassPath) {
        return thingsToPublish.contains(interactionClassPath);
    }
    
    /**
     * Check whether an object attribute should be published at the logical time given to {@link #setLogicalTime}.
     * 
     * @param objectClassPath The fully qualified class path of an HLA object class.
     * @param attributeName The name of an attribute of the HLA object class.
     * @return True if the attribute should be published.
     */
    public boolean isTimeToPublish(String objectClassPath, String attributeName) {
        return thingsToPublish.contains(objectClassPath + "." + attributeName);
    }
}

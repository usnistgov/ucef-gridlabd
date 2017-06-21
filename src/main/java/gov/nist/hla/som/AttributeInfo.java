package gov.nist.hla.som;

public class AttributeInfo{
    private String name;
    
    private String dataType;
    
    private Sharing sharing;
    
    public AttributeInfo(String name, String dataType, Sharing sharing)
            throws ObjectModelException {
        if (name == null || sharing == null) {
            throw new NullPointerException("AttributeInfo constructor received a null argument");
        }
        if (name.isEmpty()) {
            throw new ObjectModelException("attribute name is empty");
        }
        if (name.contains(".")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("attribute name contains the illegal character '.'");
        }
        if (name.contains(":")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("attribute name contains the illegal character ':'");
        }
        if (name.toLowerCase().equals("na")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("attribute name is the illegal string 'na'");
        }
        this.name = name;
        this.dataType = dataType;
        this.sharing = sharing;
    } 
    
    public String getName() {
        return name;
    }
    
    public String getDataType() {
        return dataType;
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
    
    @Override
    public String toString() {
        String result = "(name : " + name + ")(dataType : " + dataType + ")(sharing : " + sharing.toString() + ")";
        return result;
    }
}

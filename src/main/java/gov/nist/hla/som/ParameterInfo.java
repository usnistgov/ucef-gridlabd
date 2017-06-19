package gov.nist.hla.som;

public class ParameterInfo {
    private String name;
    
    private String dataType;
    
    public ParameterInfo(String name, String dataType)
            throws ObjectModelException {
        if (name == null) {
            throw new NullPointerException("ParameterInfo constructor received a null argument");
        }
        if (name.isEmpty()) {
            throw new ObjectModelException("parameter name is empty");
        }
        if (name.contains(".")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("parameter name contains the illegal character '.'");
        }
        if (name.contains(":")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("parameter name contains the illegal character ':'");
        }
        if (name.toLowerCase().equals("na")) { // object model template (OMT) section 3.3.1
            throw new ObjectModelException("parameter name is the illegal string 'na'");
        }
        this.name = name;
        this.dataType = dataType;
    } 
    
    public String getName() {
        return name;
    }
    
    public String getDataType() {
        return dataType;
    }
}

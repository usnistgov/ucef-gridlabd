package gov.nist.hla.gridlabd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gov.nist.hla.util.HTTPClient;
import gov.nist.hla.util.HTTPClientException;

public class GLDClient {
    private HTTPClient client;
    
    private SimpleDateFormat dateFormat;
    
    public GLDClient(String hostname, int port) {
        client = new HTTPClient(hostname + ":" + port);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public void shutdown()
            throws GLDClientException {
        try {
            client.get("/control/shutdown");
        } catch (HTTPClientException e) {
            throw new GLDClientException(e);
        }
    }
    
    public void advanceTime(int unixTime)
            throws GLDClientException {
        String date = unixTimeToDate(unixTime);
        try {
            // URLEncoder.encode(date, "UTF-8") produces characters GLD does not recognize
            client.get("/control/pauseat=" + date.replaceAll(" ", "%20"));
        } catch (HTTPClientException e) {
            throw new GLDClientException(e);
        }
    }
    
    public void setObjectProperty(String object, String property, String value)
            throws GLDClientException {
        try {
            client.get("/raw/" + object + "/" + property + "=" + value);
        } catch (HTTPClientException e) {
            throw new GLDClientException(e);
        }
    }
    
    public long getClockValue()
            throws GLDClientException {
        String currentDate;
        try {
            currentDate = client.get("/raw/clock");
        } catch (HTTPClientException e) {
            throw new GLDClientException(e);
        }
        return dateToUnixTime(currentDate);
    }
    
    public String getGlobalVariable(String variable)
            throws GLDClientException {
        try {
            return client.get("/raw/" + variable);
        } catch (HTTPClientException e) {
            throw new GLDClientException(e);
        }
    }
    
    public int getGlobalVariableAsInteger(String variable)
            throws GLDClientException {
        String value = getGlobalVariable(variable);
        return stringToInteger(value);
    }
    
    public double getGlobalVariableAsDouble(String variable)
            throws GLDClientException {
        String value = getGlobalVariable(variable);
        return stringToDouble(value);
    }
    
    public String getObjectProperty(String object, String property)
            throws GLDClientException {
        try {
            return client.get("/raw/" + object + "/" + property);
        } catch (HTTPClientException e) {
            throw new GLDClientException(e);
        }
    }
    
    public int getObjectPropertyAsInteger(String object, String property)
            throws GLDClientException {
        String value = getObjectProperty(object, property);
        return stringToInteger(value);
    }
    
    public double getObjectPropertyAsDouble(String object, String property)
            throws GLDClientException {
        String value = getObjectProperty(object, property);
        return stringToDouble(value);
    }
    
    private String unixTimeToDate(long unixTime) {
        return dateFormat.format(new Date(unixTime*1000));
    }
    
    private long dateToUnixTime(String date) {
        try {
            return dateFormat.parse(date).getTime()/1000;
        } catch (ParseException e) {
            throw new InvalidDateFormat(e);
        }
    }
    
    // throws NumberFormatException at runtime if the string cannot be converted
    private int stringToInteger(String str) {
        // GridLAB-D integers are formatted as "value [unit]" where value matches %hd, %ld, or %ll and unit is optional
        return (int) stringToDouble(str);
    }
    
    // throws NumberFormatException at runtime if the string cannot be converted
    private double stringToDouble(String str) {
        // GridLAB-D doubles are formatted as "value [unit]" where value matches %+lg and unit is optional
        int separatorIndex = str.indexOf(' ');
        if (separatorIndex < 0) {
            return Double.parseDouble(str);
        }
        return Double.parseDouble(str.substring(0, separatorIndex));
    }
}

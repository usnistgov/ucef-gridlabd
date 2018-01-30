package gov.nist.hla.gridlabd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.hla.util.HTTPClient;
import gov.nist.hla.util.HTTPClientException;

public class GridLabDClient {
    private static final Logger logger = LogManager.getLogger();
    
    private HTTPClient client;
    
    private SimpleDateFormat dateFormat;
    
    public GridLabDClient(String hostname, int port) {
        client = new HTTPClient(hostname + ":" + port);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public void shutdown()
            throws GLDException {
        try {
            client.get("/control/shutdown");
            logger.info(client.getAuthority() + " : sent shutdown command");
        } catch (HTTPClientException e) {
            throw new GLDException(e);
        }
    }
    
    public void pauseat(long unixTime)
            throws GLDException {
        String date = unixTimeToDate(unixTime);
        try {
            // URLEncoder.encode(date, "UTF-8") produces characters GLD does not recognize
            client.get("/control/pauseat=" + date.replaceAll(" ", "%20"));
            logger.info(client.getAuthority() + " : sent pauseat command for " + date);
        } catch (HTTPClientException e) {
            throw new GLDException(e);
        }
    }
    
    public long getUnixTime()
            throws GLDException {
        try {
            return dateToUnixTime(client.get("/raw/clock"));
        } catch (HTTPClientException e) {
            throw new GLDException(e);
        }
    }
    
    public String getGlobalVariable(String variable)
            throws GLDException {
        try {
            return client.get("/raw/" + variable);
        } catch (HTTPClientException e) {
            throw new GLDException(e);
        }
    }
    
    public long getGlobalVariableAsLong(String variable)
            throws GLDException {
        return stringToLong(getGlobalVariable(variable));
    }
    
    public double getGlobalVariableAsDouble(String variable)
            throws GLDException {
        return stringToDouble(getGlobalVariable(variable));
    }
    
    public String getObjectProperty(String object, String property)
            throws GLDException {
        try {
            return client.get("/raw/" + object + "/" + property);
        } catch (HTTPClientException e) {
            throw new GLDException(e);
        }
    }
    
    public long getObjectPropertyAsLong(String object, String property)
            throws GLDException {
        return stringToLong(getObjectProperty(object, property));
    }
    
    public double getObjectPropertyAsDouble(String object, String property)
            throws GLDException {
        return stringToDouble(getObjectProperty(object, property));
    }
    
    public void setObjectProperty(String object, String property, String value)
            throws GLDException {
        try {
            client.get("/raw/" + object + "/" + property + "=" + value);
        } catch (HTTPClientException e) {
            throw new GLDException(e);
        }
    }
    
    public String unixTimeToDate(long unixTime) {
        return dateFormat.format(new Date(unixTime*1000));
    }
    
    public long dateToUnixTime(String date)
            throws GLDException {
        try {
            return dateFormat.parse(date).getTime()/1000;
        } catch (ParseException e) {
            throw new GLDException(e);
        }
    }
    
    // throws NumberFormatException at runtime if the string cannot be converted
    private long stringToLong(String str) {
        // GridLAB-D integers are formatted as "value [unit]" where value matches %hd, %ld, or %ll and unit is optional
        return (long) stringToDouble(str);
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

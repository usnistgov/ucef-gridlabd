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
            throws GridLabDException {
        try {
            client.get("/control/shutdown");
            logger.info(client.getAuthority() + " : sent shutdown command");
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public void pauseat(long unixTime)
            throws GridLabDException {
        String date = unixTimeToDate(unixTime);
        try {
            // URLEncoder.encode(date, "UTF-8") produces characters GLD does not recognize
            client.get("/control/pauseat=" + date.replaceAll(" ", "%20"));
            logger.info(client.getAuthority() + " : sent pauseat command for " + date);
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public long getUnixTime()
            throws GridLabDException {
        try {
            return dateToUnixTime(client.get("/raw/clock"));
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public String getGlobalVariable(String variable)
            throws GridLabDException {
        try {
            return client.get("/raw/" + variable);
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public long getGlobalVariableAsLong(String variable)
            throws GridLabDException {
        return stringToLong(getGlobalVariable(variable));
    }
    
    public double getGlobalVariableAsDouble(String variable)
            throws GridLabDException {
        return stringToDouble(getGlobalVariable(variable));
    }
    
    public String getObjectProperty(String object, String property)
            throws GridLabDException {
        try {
            return client.get("/raw/" + object + "/" + property);
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public long getObjectPropertyAsLong(String object, String property)
            throws GridLabDException {
        return stringToLong(getObjectProperty(object, property));
    }
    
    public double getObjectPropertyAsDouble(String object, String property)
            throws GridLabDException {
        return stringToDouble(getObjectProperty(object, property));
    }
    
    public void setObjectProperty(String object, String property, String value)
            throws GridLabDException {
        try {
            client.get("/raw/" + object + "/" + property + "=" + value);
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public String unixTimeToDate(long unixTime) {
        return dateFormat.format(new Date(unixTime*1000));
    }
    
    public long dateToUnixTime(String date)
            throws GridLabDException {
        try {
            return dateFormat.parse(date).getTime()/1000;
        } catch (ParseException e) {
            throw new GridLabDException(e);
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

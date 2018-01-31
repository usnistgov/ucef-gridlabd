package gov.nist.hla.gridlabd;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.hla.util.HTTPClientException;
import gov.nist.hla.util.StatusCodeException;

public class GridLabDClient {
    private static final Logger log = LogManager.getLogger();
    
    final private SimpleDateFormat dateFormat;
    
    final private HttpClient client;
    
    final private String host;
    
    final private int port;
    
    public GridLabDClient(String host, int port) {
        // future GridLAB-D releases will continue to support GMT
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.client = HttpClients.createDefault();
        this.host = host;
        this.port = port;
        
    }
    
    public void shutdown()
            throws GridLabDException {
        try {
            get("/control/shutdown");
            log.info(toString() + " : sent shutdown command");
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public void pauseat(long unixTime)
            throws GridLabDException {
        String date = unixTimeToDate(unixTime);
        try {
            // URLEncoder.encode(date, "UTF-8") produces characters GLD does not recognize
            get("/control/pauseat=" + date.replaceAll(" ", "%20"));
            log.info(toString() + " : sent pauseat command for " + date);
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public long getUnixTime()
            throws GridLabDException {
        try {
            return dateToUnixTime(get("/raw/clock"));
        } catch (HTTPClientException e) {
            throw new GridLabDException(e);
        }
    }
    
    public String getGlobalVariable(String variable)
            throws GridLabDException {
        try {
            return get("/raw/" + variable);
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
            return get("/raw/" + object + "/" + property);
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
            get("/raw/" + object + "/" + property + "=" + value);
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
    
    public String get(String path)
            throws StatusCodeException,
                   HTTPClientException {
        String uri = "http://" + host + ":" + port + path;
        HttpGet request = new HttpGet(uri);
        HttpResponse response = sendRequest(request);

        HttpEntity entity = response.getEntity();
        if (entity == null) {
            throw new HTTPClientException("HTTP response contained no data");
        }

        try {
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new HTTPClientException("Unable to interpret HTTP response");
        }
    }

    private HttpResponse sendRequest(HttpUriRequest request)
            throws StatusCodeException,
                   HTTPClientException {
        log.debug(request.getMethod() + " request for URI: " + request.getURI());

        HttpResponse response;
        try {
            response = client.execute(request); // can this return null?
        } catch (IOException e) {
            throw new HTTPClientException(e);
        }
        
        StatusLine status = response.getStatusLine();
        if (status == null) {
            throw new StatusCodeException("HTTP response omitted status line");
        }

        int statusCode = status.getStatusCode();
        if (statusCode < 200 || statusCode >= 300) {
            throw new StatusCodeException(status.getReasonPhrase(), statusCode);
        }
        log.debug("received status code " + statusCode + ": " + status.getReasonPhrase());

        return response;
    }
    
    public String toString() {
        return host + ":"  + port;
    }
}

package gov.nist.hla.gridlabd;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.nist.hla.gridlabd.exception.StatusCodeException;

public class GridLabDClient {
    private static final Logger log = LogManager.getLogger();
    
    final private HttpClient client;
    
    final private String host;
    
    final private int port;
    
    public GridLabDClient(String host, int port) {
        this.client = createHttpClient();
        this.host = host;
        this.port = port;
    }
    
    public void pauseat(String timeStamp)
            throws IOException {
        log.trace("pauseat {}", timeStamp);

        // the timeStamp is included in the URL for the HTTP GET request
        // URLEncoder.encode(timeStamp, "UTF-8") does not work with GridLAB-D
        final String urlTimeStamp = timeStamp.replaceAll(" ", "%20");
        
        get("/control/pauseat=" + urlTimeStamp);
        log.debug("sent pauseat={} to {}:{}", urlTimeStamp, host, port);
    }
    
    public void resume()
            throws IOException {
        log.trace("resume");
        get("/control/resume");
        log.debug("sent resume to {}:{}", host, port);
    }
    
    public void shutdown()
            throws IOException {
        log.trace("shutdown");
        get("/control/shutdown");
        log.debug("sent shutdown to {}:{}", host, port);
    }
    
    public boolean isPaused()
            throws IOException {
        log.trace("isPaused");
        // mainloop_state = {INIT, RUNNING, PAUSED, DONE, LOCKED}
        return get("/raw/mainloop_state").equals("PAUSED");
    }
    
    public String getStringProperty(String objectName, String propertyName)
            throws IOException {
        log.trace("getStringProperty {}.{}", objectName, propertyName);
        return get("/raw/" + objectName + "/" + propertyName);
    }
    
    public double getDoubleProperty(String objectName, String propertyName)
            throws IOException {
        log.trace("getDoubleProperty {}.{}", objectName, propertyName);
        return convertValueToDouble(getStringProperty(objectName, propertyName));
    }
    
    // only properties of type double support unit conversion in GridLAB-D
    public double getDoubleProperty(String objectName, String propertyName, String unit)
            throws IOException {
        log.trace("getDoubleProperty {}.{} [{}]", objectName, propertyName, unit);
        
        if (unit == null || unit.isEmpty()) {
            log.trace("unit is null or empty");
            return getDoubleProperty(objectName, propertyName);
        }
        
        // GridLAB-D server mode requires the use of XML requests for unit conversion
        //  the XML request format is '/xml/object_name/property_name[unit]' with [ as %5B and ] as %5D
        String xmlResponse = get("/xml/" + objectName + "/" + propertyName + "%5B" + unit + "%5D");
        log.debug("XML response from GridLAB-D:\n{}", xmlResponse);
        
        String stringValue = getValueFromXml(xmlResponse);
        log.debug("read value element as {}", stringValue);
        
        return convertValueToDouble(stringValue);
    }
    
    public void setStringProperty(String objectName, String propertyName, String value)
            throws IOException {
        log.trace("setStringProperty {}.{}={}", objectName, propertyName, value);
        get("/raw/" + objectName + "/" + propertyName + "=" + value);
    }
    
    public void setDoubleProperty(String objectName, String propertyName, double value)
            throws IOException {
        log.trace("setDoubleProperty {}.{}={}", objectName, propertyName, value);
        setStringProperty(objectName, propertyName, Double.toString(value));
    }
    
    // only properties of type double support unit conversion in GridLAB-D
    public void setDoubleProperty(String objectName, String propertyName, double value, String unit)
            throws IOException {
        log.trace("setDoubleProperty {}.{}={} [{}]", objectName, propertyName, value, unit);
        
        if (unit == null || unit.isEmpty()) {
            log.trace("unit is null or empty");
            setDoubleProperty(objectName, propertyName, value);
        } else {
            // GridLAB-D server mode requires the use of XML requests for unit conversion
            //  the XML request format is '/xml/object_name/property_name=value unit' with the space as %20
            get("/xml/" + objectName + "/" + propertyName + "=" + value + "%20" + unit);
        }
    }
    
    public String toString() {
        return this.getClass().getName() + "[host=" + host + ", port=" + port + "]";
    }
    
    private HttpClient createHttpClient() {
        return HttpClients.createDefault();
    }
    
    private String get(String path)
            throws IOException, StatusCodeException {
        log.trace("get {}", path);
        
        // path must begin with a forward slash /
        final String uri = "http://" + host + ":" + port + path;
        HttpResponse response = client.execute(new HttpGet(uri));
        log.debug("sent GET {}", uri);
        
        int statusCode = response.getStatusLine().getStatusCode();
        String reasonPhrase = response.getStatusLine().getReasonPhrase();
        if (statusCode < 200 || statusCode >= 300) {
            throw new StatusCodeException(statusCode, reasonPhrase);
        }
        log.debug("received status line {}: {}", statusCode, reasonPhrase);

        HttpEntity entity = response.getEntity();
        if (entity == null) {
            throw new IOException("HTTP GET response did not include a message body");
        }
        return EntityUtils.toString(entity);
    }
    
    private double convertValueToDouble(String stringValue) {
        log.trace("convertValueToDouble {}", stringValue);
        
        // GridLAB-D doubles are formatted as "value [unit]" where value matches %+lg and unit is optional
        int unitSeperatorIndex = stringValue.indexOf(' ');
        if (unitSeperatorIndex < 0) {
            return Double.parseDouble(stringValue);
        }
        return Double.parseDouble(stringValue.substring(0, unitSeperatorIndex));
    }
    
    private String getValueFromXml(String xml)
            throws IOException {
        log.trace("getValueFromXml {}", xml);
        
        // GridLAB-D XML format:
        // <property>
        //   <object>object_name</object>
        //   <name>property_name</name>
        //   <value>value[ unit]</value>
        // </property>
        
        final String openingTag = "<value>";
        int beginIndex = xml.indexOf(openingTag);
        if (beginIndex == -1) {
            throw new IOException("XML did not include the " + openingTag + " element");
        }
        beginIndex += openingTag.length(); // first character after <value>
        
        final String closingTag = "</value>";
        int endIndex = xml.indexOf(closingTag, beginIndex);
        if (endIndex == -1) {
            throw new IOException("XML did not include the " + closingTag + " element");
        }
        return xml.substring(beginIndex, endIndex);
    }
}

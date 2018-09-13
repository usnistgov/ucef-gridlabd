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

/**
 * This class defines methods that interact with a GridLAB-D simulation running in server mode. Each public method will
 * send an HTTP GET request to the GridLAB-D server configured in the constructor. The current implementation creates a
 * new socket connection for each method call, which can cause performance issues when making a large number of queries
 * into the GridLAB-D simulation.
 *
 * @author Thomas Roth
 */
public class GridLabDClient {
    private static final Logger log = LogManager.getLogger();

    final private HttpClient client;

    final private String host;

    final private int port;

    /**
     * Creates a new GridLAB-D client configured to connect to a specific server. The GridLAB-D server does not have to
     * be running when the client is constructed with this method.
     *
     * @param host The hostname or IP address of the GridLAB-D server.
     * @param port The port number of the GridLAB-D server.
     */
    public GridLabDClient(String host, int port) {
        this.client = createHttpClient();
        this.host = host;
        this.port = port;
    }

    private HttpClient createHttpClient() {
        return HttpClients.createDefault();
    }

    /**
     * Advance the GridLAB-D simulation to the given time, and then pause its execution. If the GridLAB-D simulation is
     * already paused prior to this call, then GridLAB-D will resume until the given time.
     *
     * @param timeStamp A GridLAB-D time stamp with the format 'YYYY-MM-DD HH:MM:SS ZZZ'. The time zone must be either
     * the system time zone or GMT due to a limitation of GridLAB-D.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public void pauseat(String timeStamp)
            throws IOException {
        // the timeStamp becomes part of the URI and cannot contain spaces, but
        // URLEncoder.encode(timeStamp, "UTF-8") does not work with GridLAB-D
        final String urlTimeStamp = timeStamp.replaceAll(" ", "%20");
        get("/control/pauseat=" + urlTimeStamp);
        log.trace("sent pauseat={} to {}:{}", urlTimeStamp, host, port);
    }

    /**
     * Resume a paused GridLAB-D simulation and continue to execute until its stop time.
     *
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public void resume()
            throws IOException {
        get("/control/resume");
        log.trace("sent resume to {}:{}", host, port);
    }

    /**
     * Terminate a GridLAB-D simulation prior to its stop time. The current version of GridLAB-D terminates before it
     * sends a response to this request. As a result, the IOException will be thrown even if the call succeeds.
     *
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public void shutdown()
            throws IOException {
        get("/control/shutdown");
        log.trace("sent shutdown to {}:{}", host, port);
    }

    /**
     * Check whether the GridLAB-D simulation is paused.
     *
     * @return True if the current GridLAB-D state is paused.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public boolean isPaused()
            throws IOException {
        // mainloop_state = {INIT, RUNNING, PAUSED, DONE, LOCKED}
        return get("/raw/mainloop_state").equals("PAUSED");
    }

    /**
     * Get the string value of a global variable from GridLAB-D. The format of the returned value depends on the data
     * type of the global in GridLAB-D.
     *
     * @param variableName The name of a GridLAB-D global variable.
     * @return The string value for the global in the GridLAB-D simulation.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public String getGlobalVariable(String variableName)
            throws IOException {
        return get("/raw/" + variableName);
    }

    /**
     * Get the string value of an object property from GridLAB-D. The format of the returned value depends on the data
     * type of the property in GridLAB-D. If the property has type double, use the method {@link #getDoubleProperty}.
     *
     * @param objectName The name of an object in the GridLAB-D simulation.
     * @param propertyName The name of some property of the GridLAB-D object.
     * @return The string value for the object property in the GridLAB-D simulation.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public String getStringProperty(String objectName, String propertyName)
            throws IOException {
        return get("/raw/" + objectName + "/" + propertyName);
    }

    /**
     * Get the value of an object property with type double from GridLAB-D.
     *
     * @param objectName The name of an object in the GridLAB-D simulation.
     * @param propertyName The name of some property of the GridLAB-D object with type double.
     * @return The value for the object property in the GridLAB-D simulation.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public double getDoubleProperty(String objectName, String propertyName)
            throws IOException {
        return convertValueToDouble(getStringProperty(objectName, propertyName));
    }

    /**
     * Get the value of an object property with type double from GridLAB-D, and convert it to the given unit. Refer to
     * the GridLAB-D documentation on units (http://gridlab-d.shoutwiki.com/wiki/Units) for valid unit names.
     *
     * @param objectName The name of an object in the GridLAB-D simulation.
     * @param propertyName The name of some property of the GridLAB-D object with type double.
     * @param unit A GridLAB-D unit name to use for unit conversion of the returned value.
     * @return The value for the GridLAB-D object property converted into the given unit.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public double getDoubleProperty(String objectName, String propertyName, String unit)
            throws IOException {
        if (unit == null || unit.isEmpty()) {
            log.warn("unit name omitted when expected; using default unit");
            return getDoubleProperty(objectName, propertyName);
        }

        // unit conversion in GridLAB-D server mode only works with XML requests
        // the XML request format is '/xml/object_name/property_name[unit]' with [ as %5B and ] as %5D
        String xmlResponse = get("/xml/" + objectName + "/" + propertyName + "%5B" + unit + "%5D");
        return convertValueToDouble(getValueFromXml(xmlResponse));
    }

    private String getValueFromXml(String xml)
            throws IOException {
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

        final String value = xml.substring(beginIndex, endIndex);
        log.debug("extracted value={} from XML={}", value, xml);
        return value; // not a valid double; may contain a String unit
    }

    private double convertValueToDouble(String stringValue) {
        // GridLAB-D doubles are formatted as "value [unit]" where value matches %+lg and unit is optional
        int unitSeperatorIndex = stringValue.indexOf(' ');
        if (unitSeperatorIndex < 0) {
            return Double.parseDouble(stringValue);
        }
        return Double.parseDouble(stringValue.substring(0, unitSeperatorIndex));
    }

    /**
     * Set the value of an object property in GridLAB-D. The required string format for the value depends on the data
     * type of the property in GridLAB-D. If the property has type double, use the method {@link #setDoubleProperty}.
     *
     * @param objectName The name of an object in the GridLAB-D simulation.
     * @param propertyName The name of some property of the GridLAB-D object.
     * @param value The value to set for the GridLAB-D object property.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public void setStringProperty(String objectName, String propertyName, String value)
            throws IOException {
        get("/raw/" + objectName + "/" + propertyName + "=" + value);
    }

    /**
     * Set the value of an object property with type double in GridLAB-D.
     *
     * @param objectName The name of an object in the GridLAB-D simulation.
     * @param propertyName The name of some property of the GridLAB-D object with type double.
     * @param value The value to set for the GridLAB-D object property.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public void setDoubleProperty(String objectName, String propertyName, double value)
            throws IOException {
        setStringProperty(objectName, propertyName, Double.toString(value));
    }

    /**
     * Set the value of an object property with type double in GridLAB-D after converting it to the given unit. Refer
     * to the GridLAB-D documentation on units (http://gridlab-d.shoutwiki.com/wiki/Units) for valid unit names.
     *
     * @param objectName The name of an object in the GridLAB-D simulation.
     * @param propertyName The name of some property of the GridLAB-D object with type double.
     * @param value The value to set for the GridLAB-D object property.
     * @param unit A GridLAB-D unit name to use for unit conversion of the given value.
     * @throws IOException if the GridLAB-D server does not reply with a valid response.
     */
    public void setDoubleProperty(String objectName, String propertyName, double value, String unit)
            throws IOException {
        if (unit == null || unit.isEmpty()) {
            log.warn("unit name omitted when expected; using default unit");
            setDoubleProperty(objectName, propertyName, value);
            return;
        }

        // unit conversion in GridLAB-D server mode only works with XML requests
        // the XML request format is '/xml/object_name/property_name=value unit' with the space as %20
        get("/xml/" + objectName + "/" + propertyName + "=" + value + "%20" + unit);
    }

    private String get(String path)
            throws IOException, StatusCodeException {
        final String uri = "http://" + host + ":" + port + path; // the path must begin with a forward slash /
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

    /**
     * Format the state of this class as a String.
     *
     * @return The string representation of this class.
     */
    public String toString() {
        return this.getClass().getName() + "[host=" + host + ", port=" + port + "]";
    }
}

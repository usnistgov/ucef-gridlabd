package gov.nist.hla.gridlabd.exception;

import java.io.IOException;

/**
 * An exception that indicates a communication issue between the GridLAB-D federate and GridLAB-D server mode.
 *
 * @author Thomas Roth
 */
public class StatusCodeException extends IOException {
    private int statusCode;

    public StatusCodeException(int statusCode, String reasonPhrase) {
        super(reasonPhrase);
        this.statusCode = statusCode;
    }
    
    /**
     * Get the HTTP status code that caused the exception.
     *
     * @return A non-successful HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }
    
    /**
     * Get the HTTP reason phrase that caused the exception.
     *
     * @return A description for why the communication with server mode failed.
     */
    public String getReasonPhrase() {
        return getMessage();
    }
}

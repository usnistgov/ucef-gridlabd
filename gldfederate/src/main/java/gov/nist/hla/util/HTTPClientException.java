package gov.nist.hla.util;

public class HTTPClientException extends Exception {
    public HTTPClientException(String message) {
        super(message);
    }

    public HTTPClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public HTTPClientException(Throwable cause) {
        super(cause);
    }
}

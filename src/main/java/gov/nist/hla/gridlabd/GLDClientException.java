package gov.nist.hla.gridlabd;

public class GLDClientException extends Exception {
    public GLDClientException(String message) {
        super(message);
    }

    public GLDClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public GLDClientException(Throwable cause) {
        super(cause);
    }
}

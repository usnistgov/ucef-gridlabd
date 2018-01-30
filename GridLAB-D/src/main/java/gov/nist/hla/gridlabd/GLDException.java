package gov.nist.hla.gridlabd;

public class GLDException extends Exception {
    public GLDException(String message) {
        super(message);
    }

    public GLDException(String message, Throwable cause) {
        super(message, cause);
    }

    public GLDException(Throwable cause) {
        super(cause);
    }
}

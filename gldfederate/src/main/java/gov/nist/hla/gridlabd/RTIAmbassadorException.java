package gov.nist.hla.gridlabd;

public class RTIAmbassadorException extends RuntimeException {
    public RTIAmbassadorException(String message) {
        super(message);
    }

    public RTIAmbassadorException(String message, Throwable cause) {
        super(message, cause);
    }

    public RTIAmbassadorException(Throwable cause) {
        super(cause);
    }
}

package gov.nist.hla.gridlabd;

public class InvalidDateFormat extends RuntimeException {
    public InvalidDateFormat(String message) {
        super(message);
    }

    public InvalidDateFormat(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateFormat(Throwable cause) {
        super(cause);
    }
}

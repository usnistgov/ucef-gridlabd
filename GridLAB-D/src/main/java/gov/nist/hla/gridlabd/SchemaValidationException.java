package gov.nist.hla.gridlabd;

public class SchemaValidationException extends Exception {
    public SchemaValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchemaValidationException(Throwable cause) {
        super(cause);
    }
}
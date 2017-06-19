package gov.nist.hla.som;

public class ObjectModelException extends Exception {
    public ObjectModelException(String message) {
        super(message);
    }

    public ObjectModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectModelException(Throwable cause) {
        super(cause);
    }
}

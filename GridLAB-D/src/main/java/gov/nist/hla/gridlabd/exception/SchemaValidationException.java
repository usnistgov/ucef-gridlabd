package gov.nist.hla.gridlabd.exception;

/**
 * An exception that indicates the simulation object model (SOM) associated with the GridLAB-D federate doesn't conform
 * to the schema.
 *
 * @author Thomas Roth
 */
public class SchemaValidationException extends Exception {
    public SchemaValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchemaValidationException(Throwable cause) {
        super(cause);
    }
}

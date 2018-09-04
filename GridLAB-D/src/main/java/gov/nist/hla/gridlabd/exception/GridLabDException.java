package gov.nist.hla.gridlabd.exception;

/**
 * An exception that indicates an unrecoverable error during execution of the GridLAB-D federate.
 *
 * @author Thomas Roth
 */
public class GridLabDException extends RuntimeException {
    public GridLabDException(String message) {
        super(message);
    }

    public GridLabDException(String message, Throwable cause) {
        super(message, cause);
    }

    public GridLabDException(Throwable cause) {
        super(cause);
    }
}

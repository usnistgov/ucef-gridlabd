package gldfederate.exception;

public class InvalidDateFormat extends Exception {
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

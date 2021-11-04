package utils.exception;

/**
 * Exception throws when we put Illegal argument combination
 */
public class IllegalArgComboException extends RuntimeException {

    /**
     * Construct a IllegalArgComboException class
     * @param message the message we want to print out
     */
    public IllegalArgComboException(String message) {
        super(message);
    }
}

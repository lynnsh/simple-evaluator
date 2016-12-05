package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the value in the infix queue is invalid.
 * 
 * @author Alena Shulzhenko
 * @version 04/12/2016
 * @since 1.8
 */
public class InvalidInfixQueueException extends RuntimeException {
    private static final long serialVersionUID = 42051768871L;
    
    /**
     * Instantiates InvalidInfixQueueException.
     */
    public InvalidInfixQueueException() {
        super("The character in the infix queue is invalid.");
    }
    
    /**
     * Instantiates InvalidInfixQueueException with the custom message.
     * @param message the message associated with this exception.
     */
    public InvalidInfixQueueException(String message) {
        super(message);
    }
}

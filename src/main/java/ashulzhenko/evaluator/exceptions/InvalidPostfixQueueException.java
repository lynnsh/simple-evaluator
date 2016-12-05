package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the value in the postfix queue is invalid.
 * 
 * @author Alena Shulzhenko
 * @version 04/12/2016
 * @since 1.8
 */
public class InvalidPostfixQueueException extends RuntimeException {
    private static final long serialVersionUID = 42051768871L;
    
    /**
     * Instantiates InvalidPostfixQueueException.
     */
    public InvalidPostfixQueueException() {
        super("The character in the postfix queue is invalid.");
    }
    
    /**
     * Instantiates InvalidPostfixQueueException with the custom message.
     * @param message the message associated with this exception.
     */
    public InvalidPostfixQueueException(String message) {
        super(message);
    }
}

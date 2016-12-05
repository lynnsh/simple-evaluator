package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the operator in the queue is invalid
 * 
 * @author Alena Shulzhenko
 * @version 04/12/2016
 * @since 1.8
 */
public class InvalidOperatorException extends InvalidInfixQueueException {
    private static final long serialVersionUID = 42051768871L;
    
    /**
     * Instantiates InvalidOperatorException.
     */
    public InvalidOperatorException() {
        super("The operator is invalid.");
    }
    
    /**
     * Instantiates InvalidOperatorException with the custom message.
     * @param message the message associated with this exception.
     */
    public InvalidOperatorException(String message) {
        super(message);
    }
}

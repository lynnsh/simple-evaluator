package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the requested queue is empty if someone tries to pop a value.
 * 
 * @author Alena Shulzhenko
 * @version 04/12/2016
 * @since 1.8
 */
public class EmptyQueueException extends RuntimeException {
    private static final long serialVersionUID = 42051768871L;
    
    /**
     * Instantiates EmptyQueueException.
     */
    public EmptyQueueException() {
        super("The queue is empty.");
    }
    
    /**
     * Instantiates EmptyQueueException with the custom message.
     * @param message the message associated with this exception.
     */
    public EmptyQueueException(String message) {
        super(message);
    }
}

package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the requested queue is empty if someone tries to pop a value.
 */
public class EmptyQueueException extends RuntimeException {
    private static final long serialVersionUID = 42051768871L;
    
    public EmptyQueueException() {
        super("The queue is empty.");
    }
    
    public EmptyQueueException(String message) {
        super(message);
    }
}

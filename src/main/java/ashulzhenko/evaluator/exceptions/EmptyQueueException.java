package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the requested queue is empty if someone tries to pop a value.
 */
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {
        super("The queue is empty.");
    }
    
    public EmptyQueueException(String message) {
        super(message);
    }
}

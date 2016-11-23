package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the requested stack is empty if someone tries to peek/pop a value.
 */
public class EmptyStackException extends RuntimeException {
    public EmptyStackException() {
        super("The stack is empty.");
    }
    
    public EmptyStackException(String message) {
        super(message);
    }
}
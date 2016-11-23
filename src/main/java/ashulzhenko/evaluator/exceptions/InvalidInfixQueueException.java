package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the value in the infix queue is invalid.
 * @author 1242395
 */
public class InvalidInfixQueueException extends RuntimeException {
    public InvalidInfixQueueException() {
        super("The character in the infix queue is invalid.");
    }
    
    public InvalidInfixQueueException(String message) {
        super(message);
    }
}

package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the operator in the queue is invalid
 * @author 1242395
 */
public class InvalidOperatorException extends RuntimeException {
    public InvalidOperatorException() {
        super("The operator is invalid.");
    }
    
    public InvalidOperatorException(String message) {
        super(message);
    }
}

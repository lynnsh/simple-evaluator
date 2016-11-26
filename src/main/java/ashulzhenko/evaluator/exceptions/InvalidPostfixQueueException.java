package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the value in the postfix queue is invalid.
 * @author 1242395
 */
public class InvalidPostfixQueueException extends RuntimeException {
    private static final long serialVersionUID = 42051768871L;
    
    public InvalidPostfixQueueException() {
        super("The character in the postfix queue is invalid.");
    }
    
    public InvalidPostfixQueueException(String message) {
        super(message);
    }
}

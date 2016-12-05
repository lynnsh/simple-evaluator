package ashulzhenko.evaluator.exceptions;

/**
 * Signals that the requested stack is empty 
 * if someone tries to peek/pop a value.
 * 
 * @author Alena Shulzhenko
 * @version 04/12/2016
 * @since 1.8
 */
public class EmptyStackException extends RuntimeException {
    private static final long serialVersionUID = 42051768871L;
    
    /**
     * Instantiates EmptyStackException.
     */
    public EmptyStackException() {
        super("The stack is empty.");
    }
    
    /**
     * Instantiates EmptyStackException with the custom message.
     * @param message the message associated with this exception.
     */
    public EmptyStackException(String message) {
        super(message);
    }
}
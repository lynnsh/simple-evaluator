package ashulzhenko.evaluator.datastructures;

import java.util.ArrayList;
import java.util.List;
import ashulzhenko.evaluator.exceptions.EmptyStackException;

/**
 * The Stack (LIFO) data structure class that used for evaluating
 * an expression. It supports push, pop, peek, and isEmpty methods.
 * 
 * @author Alena Shulzhenko
 * @version 04/12/2016
 * @since 1.8
 */
public class EvaluatorStack<T> {
    private List<T> stack;
    private int pointer;
    
    /**
     * Instantiates EvaluatorStack object.
     */
    public EvaluatorStack() {
        stack = new ArrayList<>();
        pointer = -1;
    }
    
    /**
     * Verifies if the Stack is empty.
     * @return true if the Stack is empty; false otherwise.
     */
    public boolean isEmpty() {
        return pointer == -1;
    }
    
    /**
     * Pushes the value to the end of the Stack.
     * @param value the value to add to the Stack.
     */
    public void push(T value) {
        if(value == null)
            throw new NullPointerException("Null value passed to the stack.");
        pointer++;
        stack.add(value);
    }
    
    /**
     * Pops the value from the end of the Stack.
     * @return the value removed from the end of the Stack.
     * @throws EmptyStackException if the Stack is empty.
     */
    public T pop() {
        if(isEmpty())
            throw new EmptyStackException("There are no values in the stack to pop.");
        T value = stack.remove(pointer);
        pointer--;
        return value;
    }
    
    /**
     * Peeks at the current last value on the Stack.
     * @return the current last value on the Stack (without removing it).
     * @throws EmptyStackException if the Stack is empty.
     */
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException("There are no values in the stack.");
        return stack.get(pointer);
    }
}

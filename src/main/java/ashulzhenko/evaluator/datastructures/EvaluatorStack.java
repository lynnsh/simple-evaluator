package ashulzhenko.evaluator.datastructures;

import java.util.ArrayList;
import java.util.List;
import ashulzhenko.evaluator.exceptions.EmptyStackException;

/**
 *
 * @author 1242395
 */
public class EvaluatorStack<T> {
    private List<T> stack;
    private int pointer;
    
    public EvaluatorStack() {
        stack = new ArrayList<>();
        pointer = -1;
    }
    
    public boolean isEmpty() {
        return pointer == -1;
    }
    
    public void push(T value) {
        if(value == null)
            throw new NullPointerException("Null value passed to the stack.");
        pointer++;
        stack.add(value);
    }
    
    public T pop() {
        if(isEmpty())
            throw new EmptyStackException("There are no values in the stack to pop.");
        T value = stack.remove(pointer);
        pointer--;
        return value;
    }
    
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException("There are no values in the stack.");
        return stack.get(pointer);
    }
}

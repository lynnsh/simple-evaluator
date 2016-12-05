package ashulzhenko.evaluator.datastructures;

import java.util.ArrayList;
import java.util.List;
import ashulzhenko.evaluator.exceptions.EmptyQueueException;

/**
 * The Queue (FIFO) data structure class that used for evaluating
 * an expression. It supports push, pop, and isEmpty methods.
 * 
 * @author Alena Shulzhenko
 * @version 04/12/2016
 * @since 1.8
 */
public class EvaluatorQueue<T> {
    private List<T> queue;
    private int start;
    private int end;
    
    /**
     * Instantiates EvaluatorQueue object.
     */
    public EvaluatorQueue() {
        queue = new ArrayList<>();
        start = 0;
        end = -1;
    }
    
    /**
     * Verifies if the Queue is empty.
     * @return true if the Queue is empty; false otherwise.
     */
    public boolean isEmpty() {
        return start > end;
    }
    
    /**
     * Pushes the value to the end of the Queue.
     * @param value the value to add to the Queue.
     */
    public void push(T value) {
        if(value == null)
            throw new NullPointerException("Null value passed to the queue.");
        end++;
        queue.add(value);
    }
    
    /**
     * Pops the value from the beginning of the Queue.
     * @return the value removed from the beginning of the Queue.
     * @throws EmptyQueueException if the Queue is empty.
     */
    public T pop() {
        if(isEmpty())
            throw new EmptyQueueException("There are no values in the queue to pop.");
        T value = queue.get(start);
        start++;
        return value;
    }
}

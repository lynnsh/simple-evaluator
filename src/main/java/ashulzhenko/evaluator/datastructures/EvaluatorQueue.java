package ashulzhenko.evaluator.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ashulzhenko.evaluator.exceptions.EmptyQueueException;

/**
 *
 * @author 1242395
 */
public class EvaluatorQueue<T> {
    private List<T> queue;
    private int start;
    private int end;
    
    public EvaluatorQueue() {
        queue = new ArrayList<>();
        start = 0;
        end = -1;
    }
    
    public boolean isEmpty() {
        return start > end;
    }
    
    public void push(T value) {
        if(value == null)
            throw new NullPointerException("Null value passed to the queue.");
        end++;
        queue.add(value);
    }
    
    public T pop() {
        if(isEmpty())
            throw new EmptyQueueException("There are no values in the queue to pop.");
        T value = queue.get(start);
        start++;
        return value;
    }
    
    public String toString() {
        return Arrays.toString(queue.toArray());
    }
}

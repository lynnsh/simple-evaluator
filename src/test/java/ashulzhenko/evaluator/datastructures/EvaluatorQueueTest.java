package ashulzhenko.evaluator.datastructures;

import ashulzhenko.evaluator.exceptions.EmptyQueueException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author 1242395
 */
public class EvaluatorQueueTest {
    EvaluatorQueue<String> queue;
    
    @Test
    public void isEmptyTest() {
        assertTrue(queue.isEmpty());
    }
    
    @Test
    public void isEmptyWithAddingValuesTest() {
        queue.push("orange");
        queue.pop();
        assertTrue(queue.isEmpty());
    }
    
    @Test
    public void isEmptyWithValuesTest() {
        queue.push("orange");
        assertFalse(queue.isEmpty());
    }
    
    @Test
    public void pushTest() {
        queue.push("orange");
        assertEquals(queue.pop(), "orange");
    }
    
    @Test(expected=NullPointerException.class)
    public void pushNullTest() {
        queue.push(null);
        fail();
    }
    
    @Test
    public void pushMultipleValuesTest() {
        queue.push("orange");
        queue.push("mango");
        assertEquals(queue.pop(), "orange");
    }
    
        @Test
    public void popTest() {
        queue.push("orange");
        queue.pop();
        assertTrue(queue.isEmpty());
    }
    
    @Test(expected=EmptyQueueException.class)
    public void popEmptyQueueTest() {
        queue.pop();
        fail();
    }
    
    @Before
    public void init() {
        queue = new EvaluatorQueue<>();
    }
}

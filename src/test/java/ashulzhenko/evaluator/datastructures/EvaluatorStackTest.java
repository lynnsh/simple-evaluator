package ashulzhenko.evaluator.datastructures;

import ashulzhenko.evaluator.exceptions.EmptyStackException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author 1242395
 */
public class EvaluatorStackTest {
    EvaluatorStack<String> stack;
    
    @Test
    public void isEmptyTest() {
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void isEmptyWithAddingValuesTest() {
        stack.push("orange");
        stack.pop();
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void isEmptyWithValuesTest() {
        stack.push("orange");
        assertFalse(stack.isEmpty());
    }
    
    @Test
    public void pushTest() {
        stack.push("orange");
        assertEquals(stack.pop(), "orange");
    }
    
    @Test(expected=NullPointerException.class)
    public void pushNullTest() {
        stack.push(null);
        fail();
    }
    
    @Test
    public void pushMultipleValuesTest() {
        stack.push("orange");
        stack.push("mango");
        assertEquals(stack.pop(), "mango");
    }
    
    @Test
    public void popTest() {
        stack.push("orange");
        stack.pop();
        assertTrue(stack.isEmpty());
    }
    
    @Test(expected=EmptyStackException.class)
    public void popEmptyStackTest() {
        stack.pop();
        fail();
    }
    
    @Test
    public void peekTest() {
        stack.push("orange");
        assertEquals(stack.peek(), "orange");
        assertFalse(stack.isEmpty());
    }
    
    @Test(expected=NullPointerException.class)
    public void peekhNullTest() {
        stack.push(null);
        fail();
    }
    
    @Test
    public void peekhMultipleValuesTest() {
        stack.push("orange");
        stack.push("mango");
        assertEquals(stack.peek(), "mango");
    }
    
    @Before
    public void init() {
        stack = new EvaluatorStack<>();
    }
}

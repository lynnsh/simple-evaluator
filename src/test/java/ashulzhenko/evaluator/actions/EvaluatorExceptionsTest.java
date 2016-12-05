package ashulzhenko.evaluator.actions;

import ashulzhenko.evaluator.datastructures.EvaluatorQueue;
import ashulzhenko.evaluator.exceptions.InvalidInfixQueueException;
import ashulzhenko.evaluator.exceptions.InvalidOperatorException;
import ashulzhenko.evaluator.exceptions.InvalidPostfixQueueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Evaluator class with the invalid values.
 * @author Alena Shulzhenko
 */
public class EvaluatorExceptionsTest {
    private final Logger log = LogManager.getLogger(EvaluatorTest.class.getName());
    private Evaluator evaluator;
    private EvaluatorQueue<String> infix;
    private EvaluatorQueue<String> postfix;
    
    @Test(expected=InvalidPostfixQueueException.class)
    public void noOperandAfterOperatorTest() {
        fill("2 + 3 *");               
        postfix = evaluator.toPostfix(infix);
        evaluator.evaluate(postfix);
    }
    
    @Test(expected=InvalidInfixQueueException.class)
    public void invalidOperandTest() {
        fill("2 ^ 3 + 4");               
        postfix = evaluator.toPostfix(infix);
        evaluator.evaluate(postfix);
    }
    
    @Test(expected=InvalidPostfixQueueException.class)
    public void noOperatorAfterOperandTest() {
        fill("( ( 5 + 1 1 ) * 2 ) + 3");               
        postfix = evaluator.toPostfix(infix);
        evaluator.evaluate(postfix);
    }
    
    @Test(expected=InvalidPostfixQueueException.class)
    public void multipleOperandsTest() {
        fill("( 5 + + 1 ) * 2 + 3");               
        postfix = evaluator.toPostfix(infix);
        evaluator.evaluate(postfix);
    }
    
    @Test(expected=InvalidOperatorException.class)
    public void invalidNumberOfParenthesesTest() {
        fill("5 + ( 1 * 2 ) ) + 3");               
        postfix = evaluator.toPostfix(infix);
        evaluator.evaluate(postfix);
    }
    
    @Before
    public void init() {
        infix = new EvaluatorQueue<>();
        evaluator = new Evaluator();   
    }
    
    public void fill(String expression) {
        for(String str : expression.split(" "))
            infix.push(str);
    }
}

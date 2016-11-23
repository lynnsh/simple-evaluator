package ashulzhenko.evaluator.action;

import ashulzhenko.evaluator.actions.Evaluator;
import ashulzhenko.evaluator.datastructures.EvaluatorQueue;
import java.util.Arrays;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author aline
 */
@RunWith(Parameterized.class)
public class EvaluatorTest {
    private final Logger log = LogManager.getLogger(EvaluatorTest.class.getName());
    private Evaluator evaluator;
    private EvaluatorQueue<String> infix;
    private EvaluatorQueue<String> expected;
    private EvaluatorQueue<String> postfix;
    private int result;
    
    //http://scriptasylum.com/tutorials/infix_postfix/infix_postfix.html
    @Parameters(name = "{index} plan[{0}]={1}]")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            //1: 2+3*4
            {new String[]{"2", "+", "3", "*", "4"},
             new String[]{"2", "3", "4", "*", "+"},
             new String[]{"14"}},           
            //2: 2*3+4
            {new String[]{"2", "*", "3", "+", "4"},
             new String[]{"2", "3", "*", "4", "+"},
             new String[]{"10"}},           
            //3: ((5+1)*2)+3
            {new String[]{"(", "(", "5", "+", "1", ")", "*", "2", ")", "+", "3"},
             new String[]{"5", "1", "+", "2", "*", "3", "+"},
             new String[]{"15"}},  
            //4: (5+1)*2+3
            {new String[]{"(", "5", "+", "1", ")", "*", "2", "+", "3"},
             new String[]{"5", "1", "+", "2", "*", "3", "+"},
             new String[]{"15"}}, 
            //5: 5+(1*2)+3
            {new String[]{"5", "+", "(", "1", "*", "2", ")", "+", "3"},
             new String[]{"5", "1", "2", "*", "+", "3", "+"},
             new String[]{"10"}}, 
            //6: 10+(2*3)+(1+3)*5
            {new String[]{"10", "+", "(", "2", "*", "3", ")", "+", "(","1","+","3", ")", "*", "5"},
             new String[]{"10", "2", "3", "*", "+", "1", "3", "+", "5", "*", "+"},
             new String[]{"36"}}, 
            
        });
    }
    
    public EvaluatorTest(String[] expression, String[] expect, String[] results) {
        infix = new EvaluatorQueue<>();
        expected = new EvaluatorQueue<>();
        for(String str : expression)
            infix.push(str);
        for(String str : expect)
            expected.push(str);
        evaluator = new Evaluator();
        result = Integer.parseInt(results[0]);
    }
    
    @Test
    public void TestConvert() {
        log.debug("expect: " + expected.toString() + " postfix: " + postfix.toString());
        while(!postfix.isEmpty()) {
            assertEquals(expected.pop(), postfix.pop());
        }
    }
    
    @Test
    public void TestEvaluate() {      
        int rcvResult = evaluator.evaluate(postfix);
        log.debug("expect: " + result + " received: " + rcvResult);
        assertEquals(result, rcvResult);
    }
    
    @Before
    public void init() {
        postfix = evaluator.calculate(infix);
    }
}

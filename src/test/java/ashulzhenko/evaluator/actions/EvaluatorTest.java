package ashulzhenko.evaluator.actions;

import ashulzhenko.evaluator.datastructures.EvaluatorQueue;
import java.util.Arrays;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests Evaluator class.
 * @author Alena Shulzhenko
 */
@RunWith(Parameterized.class)
public class EvaluatorTest {
    private final Logger log = LogManager.getLogger(EvaluatorTest.class.getName());
    private Evaluator evaluator;
    private EvaluatorQueue<String> infix;
    private EvaluatorQueue<String> expected;
    private EvaluatorQueue<String> postfix;
    private double result;
    
    /*
     * Contains the test data.
     */
    @Parameters(name = "{index} plan[{0}]={1}]")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            //1: 2+3*4
            {"2 + 3 * 4", "2 3 4 * +", "14"},           
            //2: 2*3+4
            {"2 * 3 + 4", "2 3 * 4 +", "10"},           
            //3: ((5+1)*2)+3
            {"( ( 5 + 1 ) * 2 ) + 3", "5 1 + 2 * 3 +", "15"},  
            //4: (5+1)*2+3
            {"( 5 + 1 ) * 2 + 3", "5 1 + 2 * 3 +", "15"}, 
            //5: 5+(1*2)+3
            {"5 + ( 1 * 2 ) + 3", "5 1 2 * + 3 +", "10"}, 
            //6: 10+(2*3)+(1+3)*5
            {"10 + ( 2 * 3 ) + ( 1 + 3 ) * 5", "10 2 3 * + 1 3 + 5 * +", "36"},
            //7: 2*(3+2*7)+10
            {"2 * ( 3 + 2 * 7 ) + 10", "2 3 2 7 * + * 10 +", "44"},
            //8: 11+1-100*2/(50-25)+25
            {"11 + 1 - 100 * 2 / ( 50 - 25 ) + 25", "11 1 + 100 2 * 50 25 - / - 25 +", "29"},
            //9: 11+((1-100)*2/(50-25))+25
            {"11 + ( ( 1 - 100 ) * 2 / ( 50 - 25 ) ) + 25", "11 1 100 - 2 * 50 25 - / + 25 +", "28.08"},
            //10: 2.3*3+4.7
            {"2.3 * 3 + 4.7", "2.3 3 * 4.7 +", "11.6"},         
            //11: ((3+11)/(2+8)-3)*2
            {"( ( 3 + 11 ) / ( 2 + 8 ) - 3 ) * 2", "3 11 + 2 8 + / 3 - 2 *", "-3.2"},           
            //12: (2+8*2/4)-(3+7)/2+7
            {"( 2 + 8 * 2 / 4 ) - ( 3 + 7 ) / 2 + 7", "2 8 2 * 4 / + 3 7 + 2 / - 7 +", "8"},           
            //13: (-10*8)*100+21/7
            {"( -10 * 8 ) * 100 + 21 / 7", "-10 8 * 100 * 21 7 / +", "-7997"},  
            //14: (22/11+3-7)*12-(2+1)
            {"( 22 / 11 + 3 - 7 ) * 12 - ( 2 + 1 )", "22 11 / 3 + 7 - 12 * 2 1 + -", "-27"}, 
            //15: 100/10*2.5+(-20)
            {"100 / 10 * 2.5 + ( -20 )", "100 10 / 2.5 * -20 +", "5"}, 
            //16: 1/0.4+2.3-(-10)
            {"1 / 0.4 + 2.3 - ( -10 )", "1 0.4 / 2.3 + -10 -", "14.8"},
            //17: (1+(3+(4-5)*3)-5)-10
            {"( 1 + ( 3 + ( 4 - 5 ) * 3 ) - 5 ) - 10", "1 3 4 5 - 3 * + + 5 - 10 -", "-14"},
            //18: (((((2+7)-5*2)/2)-2)+10)*3
            {"( ( ( ( ( 2 + 7 ) - 5 * 2 ) / 2 ) - 2 ) + 10 ) * 3", "2 7 + 5 2 * - 2 / 2 - 10 + 3 *", "22.5"},
            //19: 10/2
            {"10 / 2", "10 2 /", "5"},
            //20: 2+2+2+2+2
            {"2 + 2 + 2 + 2 + 2", "2 2 + 2 + 2 + 2 +", "10"},          
            //21: 10-(2/2+3-(10+27))-5
            {"10 - ( 2 / 2 + 3 - ( 10 + 27 ) ) - 5", "10 2 2 / 3 + 10 27 + - - 5 -", "38"},           
            //22: 22/2+2*2-2+2
            {"22 / 2 + 2 * 2 - 2 + 2", "22 2 / 2 2 * + 2 - 2 +", "15"},           
            //23: ((12-10)/5+(33+11)-15)*23
            {"( ( 12 - 10 ) / 5 + ( 33 + 11 ) - 15 ) * 23", "12 10 - 5 / 33 11 + + 15 - 23 *", "676.2"},  
            //24: (13+27)*2-34/17+2*7
            {"( 13 + 27 ) * 2 - 34 / 17 + 2 * 7", "13 27 + 2 * 34 17 / - 2 7 * +", "92"}, 
            //25: 1.4*2-2.3+(-17.3)
            {"1.4 * 2 - 2.3 + ( -17.3 )", "1.4 2 * 2.3 - -17.3 +", "-16.8"}           
        });
    }
    
    /**
     * Initializes the parameters necessary for testing.
     * @param expression the expression to convert and evaluate.
     * @param expect the expected postfix expression.
     * @param results the expected evaluated result.
     */
    public EvaluatorTest(String expression, String expect, String results) {
        infix = new EvaluatorQueue<>();
        expected = new EvaluatorQueue<>();
        String[] array = expression.split(" ");
        for(String str : array)
            infix.push(str);
        array = expect.split(" ");
        for(String str : array)
            expected.push(str);
        evaluator = new Evaluator();
        result = Math.round(Double.parseDouble(results)*10.0)/10.0;
    }
    
    @Test
    public void convertTest() {
        while(!postfix.isEmpty()) {
            assertEquals(expected.pop(), postfix.pop());
        }
    }
    
    @Test
    public void evaluateTest() {      
        double rcvResult = Math.round(evaluator.evaluate(postfix)*10.0)/10.0;
        log.debug("expected: " + result + " received: " + rcvResult);
        //assertEquals is deprecated for doubles
        assertTrue(result == rcvResult);
    }
    
    @Before
    public void init() {
        postfix = evaluator.toPostfix(infix);
    }
}

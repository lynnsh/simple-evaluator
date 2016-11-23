package ashulzhenko.evaluator.actions;

import ashulzhenko.evaluator.datastructures.EvaluatorQueue;
import ashulzhenko.evaluator.datastructures.EvaluatorStack;
import java.util.regex.Pattern;
import ashulzhenko.evaluator.exceptions.InvalidInfixQueueException;
import ashulzhenko.evaluator.exceptions.InvalidOperatorException;
import ashulzhenko.evaluator.exceptions.InvalidPostfixQueueException;

/**
 *
 * @author 1242395
 */
public class Evaluator {
    private EvaluatorQueue<String> infix;
    private EvaluatorQueue<String> postfix;
    private EvaluatorStack<String> operators;
    private EvaluatorStack<String> values;
    private String numRegex = "^[0-9]+$";
    
    public EvaluatorQueue<String> calculate(EvaluatorQueue<String> infix) {
        this.infix = infix;
        postfix = new EvaluatorQueue<>();
        operators = new EvaluatorStack<>();
        values = new EvaluatorStack<>();
        convert();             
        return postfix;
    }
    
    public int evaluate(EvaluatorQueue<String> postfix) {
        int result = 0;
        while(!postfix.isEmpty()) {
            String value = postfix.pop();
            if(Pattern.matches(numRegex, value)) {
                values.push(value);
            }
            else if(isOperator(value)) {
                int rightV = getValue();
                int leftV = getValue();
                result = performOperation(leftV, value, rightV);
                values.push(result+"");
            }
            else {
                throw new InvalidPostfixQueueException();
            }
        }
        return result;
    }
    
    private int getValue() {
        if(values.isEmpty())
            throw new InvalidPostfixQueueException("The postfix queue is empty. An operand was expected");
        return Integer.parseInt(values.pop());
    }
    
    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-")
            || value.equals("*") || value.equals("/");
    }
    
    private void convert() {      
        int rightP = 0;
        int leftP = 0;
        
        while(!infix.isEmpty()) {
            String value = infix.pop();
            if(value.equals("(")) {
                leftP++;
                operators.push(value);
            }
            else if (value.equals(")")) {
                rightP++;
                handleRightBracket();              
            }
            else if(isOperator(value)) {
                handleOperator(value);
            }
            else if(Pattern.matches(numRegex, value)) {
                postfix.push(value);
            }
            else {
                throw new InvalidInfixQueueException();
            }
        }
        while(!operators.isEmpty()) {
            postfix.push(operators.pop());
        }
        if(rightP != leftP)
            throw new InvalidOperatorException("Parentheses do not match.");
    }
    
    private void handleOperator(String value) {
        if(!operators.isEmpty()) {
            String prevOp = operators.peek();
            if(!prevOp.equals("(") && needSwap(prevOp, value)) {
                postfix.push(prevOp);
                operators.pop();    
            }
        }
        operators.push(value);
    }
    
    private void handleRightBracket() {   
        String operator = operators.pop();
        if(!isOperator(operator))
            throw new InvalidOperatorException("Operator was expected in the queue: +, -, *, /.");
        else {
            postfix.push(operator);
            String parentheses = operators.pop();
            if(!parentheses.equals("("))
                throw new InvalidOperatorException("Left parentheses was expected: (.");
        }
    }
    
    //need swap if nextO <= prevO
    private boolean needSwap(String prevO, String nextO) {
        if(nextO.equals("+") || nextO.equals("-")) {
            return true;            
        }
        if(nextO.equals("*") || nextO.equals("/")) {
            return prevO.equals("*") || prevO.equals("/");
        }
        else
            throw new InvalidOperatorException("Operator was expected in the queue: +, -, *, /.");
    }

    private int performOperation(int leftV, String operator, int rightV) {
        switch (operator) {
            case "+":
                return leftV+rightV;
            case "*":
                return leftV*rightV;
            case "/":
                return leftV/rightV;
            case "-":
                return leftV-rightV;
            default:
                throw new InvalidOperatorException("Operator was expected in the queue: +, -, *, /.");
        }
    }

}

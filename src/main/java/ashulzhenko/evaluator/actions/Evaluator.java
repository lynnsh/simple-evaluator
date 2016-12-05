package ashulzhenko.evaluator.actions;

import ashulzhenko.evaluator.datastructures.EvaluatorQueue;
import ashulzhenko.evaluator.datastructures.EvaluatorStack;
import java.util.regex.Pattern;
import ashulzhenko.evaluator.exceptions.InvalidInfixQueueException;
import ashulzhenko.evaluator.exceptions.InvalidOperatorException;
import ashulzhenko.evaluator.exceptions.InvalidPostfixQueueException;

/**
 * The Evaluator class is responsible for evaluating the submitted expression.
 * First, the infix expression is converted to the postfix expression
 * and then it is evaluated.
 * 
 * @author Alena Shulzhenko
 * @version 04/12/2016
 * @since 1.8
 */
public class Evaluator {
    private EvaluatorQueue<String> infix;
    private EvaluatorQueue<String> postfix;
    private EvaluatorStack<String> operators;
    private EvaluatorStack<String> values;
    private String numRegex = "^[+-]?([0-9]*[.])?[0-9]+$";
    
    /**
     * Converts infix expression in the Queue to the postfix expression.
     * @param infix the infix expression in the Queue to convert.
     * @return the postfix expression in the Queue.
     */
    public EvaluatorQueue<String> toPostfix(EvaluatorQueue<String> infix) {
        this.infix = infix;
        postfix = new EvaluatorQueue<>();
        operators = new EvaluatorStack<>();
        values = new EvaluatorStack<>();
        convert();             
        return postfix;
    }
    
    /**
     * Evaluates the postfix expression.
     * @param postfix the postfix expression in the Queue to evaluate.
     * @return the result of the provided expression.
     * @throws InvalidPostfixQueueException if the provided expression is invalid.
     */
    public double evaluate(EvaluatorQueue<String> postfix) {
        double result = 0;
        while(!postfix.isEmpty()) {
            String value = postfix.pop();
            if(Pattern.matches(numRegex, value)) {
                values.push(value);
            }
            else if(isOperator(value)) {
                double rightValue = getValue();
                double leftValue = getValue();
                result = performOperation(leftValue, value, rightValue);
                values.push(result+"");
            }
            else {
                throw new InvalidPostfixQueueException();
            }
        }
        //pop the result value
        values.pop();
        if(!values.isEmpty())
            throw new InvalidPostfixQueueException("Invalid number of operands");
        return result;
    }
    
    /**
     * Gets the value from the postfix queue.
     * @return the value from the postfix queue.
     * @throws InvalidPostfixQueueException if the postfix queue is empty.
     */
    private double getValue() {
        if(values.isEmpty())
            throw new InvalidPostfixQueueException
                    ("The postfix queue is empty. An operand was expected");
        return Double.parseDouble(values.pop());
    }
    
    /**
     * Determines if the provided value is a valid operator.
     * @param value the value to verify.
     * @return true if the provided value is a valid operator; false otherwise.
     */
    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-")
            || value.equals("*") || value.equals("/");
    }
    
    /**
     * Converts infix expression to the postfix expression.
     */
    private void convert() {      
        int rightParentheses = 0;
        int leftParentheses = 0;
        
        while(!infix.isEmpty()) {
            String value = infix.pop();
            if(value.equals("(")) {
                leftParentheses++;
                operators.push(value);
            }
            else if (value.equals(")")) {
                rightParentheses++;
                handleRightParenthesis();              
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
        //pop the left operators from the stack
        while(!operators.isEmpty()) {
            postfix.push(operators.pop());
        }
        //verify that the expression has the same number of left and right parentheses
        if(rightParentheses != leftParentheses)
            throw new InvalidOperatorException("Parentheses do not match.");
    }
    
    /**
     * Adds operators to the queue or to the stack depending on their
     * level of precedence.
     * @param nextOperator the current operator in the infix queue.
     */
    private void handleOperator(String nextOperator) {     
        if(!operators.isEmpty()) {
            String prevOperator = operators.peek();
            while(!prevOperator.equals("(") && needSwap(prevOperator, nextOperator)) {
                postfix.push(prevOperator);
                checkOperators();
                operators.pop();
                prevOperator = operators.isEmpty() ? "(" : operators.peek();        
            }
        }
        operators.push(nextOperator);
    }
    
    /**
     * Handles operators in parentheses.
     */
    private void handleRightParenthesis() {   
        String operator = operators.pop();
        if(!operator.equals("(")) {
            if(!isOperator(operator))
                throw new InvalidOperatorException("Operator was expected in the queue: +, -, *, /.");
            else {
                postfix.push(operator);
                checkOperators();
                while(!operators.peek().equals("(")) {
                    String value = operators.pop();
                    postfix.push(value);
                    checkOperators();
                }           
                operators.pop();
            }
        }
    }
    
    /**
     * Checks if operators stack is empty.
     * @throws InvalidOperatorException if operators stack is empty.
     */
    private void checkOperators() {
        if(operators.isEmpty())
            throw new InvalidOperatorException("Expected an operator, but stack is empty.");
    }
    
    /**
     * Verifies if the previous operator needs to be pulled from operators stack.
     * If the level of precedence for the next operator is less than
     * or equals to the previous operator, the previous operator is popped from
     * the stack.
     * @param prevOperator the current last operator in operators stack.
     * @param nextOperator the current operator in the infix queue.
     * @return true if the previous operator needs to be pulled 
     *         from operators stack; false otherwise.
     * @throws InvalidOperatorException if the nextOperator value
     *         is not an operator.
     */
    private boolean needSwap(String prevOperator, String nextOperator) {
        if(nextOperator.equals("+") || nextOperator.equals("-")) {
            return true;            
        }
        if(nextOperator.equals("*") || nextOperator.equals("/")) {
            return prevOperator.equals("*") || prevOperator.equals("/");
        }
        else
            throw new InvalidOperatorException("Operator was expected in the queue: +, -, *, /.");
    }

    /**
     * Performs the operation corresponding to the provided operator.
     * @param leftValue the leftmost operand in the operation.
     * @param operator the operator to determine the operation.
     * @param rightValue the rightmost operand in the operation.
     * @return the result of the operation.
     * @throws InvalidOperatorException if the provided operator is invalid.
     */
    private double performOperation(double leftValue, String operator, double rightValue) {
        switch (operator) {
            case "+":
                return leftValue+rightValue;
            case "*":
                return leftValue*rightValue;
            case "/":
                return leftValue/rightValue;
            case "-":
                return leftValue-rightValue;
            default:
                throw new InvalidOperatorException("Operator was expected in the queue: +, -, *, /.");
        }
    }

}

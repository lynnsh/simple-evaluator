package ashulzhenko.evaluator.actions;

import ashulzhenko.evaluator.datastructures.EvaluatorQueue;
import ashulzhenko.evaluator.datastructures.EvaluatorStack;
import java.util.regex.Pattern;

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
                if(values.isEmpty())
                    throw new IllegalArgumentException("Invalid postfix queue.");
                int rightV = Integer.parseInt(values.pop());
                if(values.isEmpty())
                    throw new IllegalArgumentException("Invalid postfix queue.");
                int leftV = Integer.parseInt(values.pop());
                result = performOperation(leftV, value, rightV);
                values.push(result+"");
            }
            else {
                throw new IllegalArgumentException("Invalid character in postfix queue");
            }
        }
        return result;
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
                String operator = operators.pop();
                if(!isOperator(operator))
                    throw new IllegalArgumentException("Invalid operator.");
                else {
                    postfix.push(operator);
                    String parentheses = operators.pop();
                    if(!parentheses.equals("("))
                        throw new IllegalArgumentException("Invalid operator.");
                }
            }
            else if(isOperator(value)) {
                if(!operators.isEmpty()) {
                    String prevOp = operators.peek();
                    if(!prevOp.equals("(") && needSwap(prevOp, value)) {
                        postfix.push(prevOp);
                        operators.pop();    
                    }
                }
                operators.push(value);
            }
            else if(Pattern.matches(numRegex, value)) {
                postfix.push(value);
            }
            else {
                throw new IllegalArgumentException("Invalid character in infix queue");
            }
        }
        while(!operators.isEmpty()) {
            postfix.push(operators.pop());
        }
        if(rightP != leftP)
            throw new IllegalArgumentException("Parentheses do not match.");
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
            throw new IllegalArgumentException("Invalid operator.");
    }

    private int performOperation(int leftV, String operator, int rightV) {
        switch (operator) {
            case "+":
                return leftV+rightV;
            case "*":
                return leftV*rightV;
            case "/":
                return leftV/rightV;
            default:
                return leftV-rightV;
        }
    }

}

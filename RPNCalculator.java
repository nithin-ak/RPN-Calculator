import java.math.BigDecimal;

/**
 * Reverse polish notation calculator implementation.
 */

public class RPNCalculator {
    private final ShuntingYard sy;
    private String postfixStr;

    /**
     * Default constructor
     */
    public RPNCalculator() {
        this.postfixStr = null;
        this.sy = new ShuntingYard();
    }

    /**
     * Evaluates the math expression using Reverse Polish Notation.
     *
     * @param mathExpression a math expression in String format delimited using whitespaces.
     * @return {@link String} - message of the evaluation.
     */
    public String evaluate(String mathExpression) {
        // YOUR CODE HERE
        if (mathExpression == null || mathExpression.isEmpty()) {
            return "Math expression is empty";
        }

        if (!checkForBalancedBrackets(mathExpression)) {
            return "Unbalanced brackets in math expression";
        }

        try {
            postfixStr = sy.convertToPostfix(mathExpression);
            double result = calculate();
            return "Math Expression: " + mathExpression +
                    "\nPostfix Expression: " + postfixStr +
                    "\nCalculated Result: " + String.format("%.2f", result);
        } catch (ArithmeticException e) {
            return "ArithmeticException: " + e.getMessage();
        } catch (IllegalArgumentException e) {
            return "IllegalArgumentException: " + e.getMessage();
        }
    }

    /**
     * Calculates the value of the math expression using reverse polish notation.
     *
     * @return {@link Double} - the calculated value or null for invalid math expressions
     * @throws ArithmeticException when the factorial is a decimal number
     */
    private Double calculate() throws ArithmeticException {
        // YOUR CODE HERE
        if (postfixStr == null || postfixStr.isEmpty()) {
            throw new ArithmeticException("Postfix expression is empty. Cannot calculate RPN.");
        }

        Stack<Double> stack = new Stack<>();

        String[] tokens = postfixStr.split(" ");

        for (String token : tokens) {

            // When you encounter an operand, push it onto the stack
            if (sy.isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            }

            // When you encounter an operator, pop either the last two or last value/s from the stack,
            // apply the operation (thus evaluating the expression), then push the result back onto the stack
            else {
                Operator operator = Operator.getValueOfSymbol(token);
                if (operator == null) {
                    throw new IllegalArgumentException("Invalid operator: " + token);
                }
                if (operator == Operator.FACTORIAL) {
                    double num = stack.pop();
                    if (num < 0 || num != (int) num) {
                        throw new ArithmeticException("Factorial operand must be a non-negative integer");
                    }
                    stack.push(operator.calculate(BigDecimal.valueOf(num), null).doubleValue());
                } else {
                    BigDecimal num2 = BigDecimal.valueOf(stack.pop());
                    BigDecimal num1 = BigDecimal.valueOf(stack.pop());
                    stack.push(operator.calculate(num1, num2).doubleValue());
                }
            }
        }

        // When you have finished (been through the whole expression), pop the final answer from the stack and return it
        if (stack.getSize() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }
        return stack.pop();

    }

    /**
     * Check the expression for balanced pairs of brackets.
     *
     * @param exp math expression to check
     * @return boolean - true for successful, false otherwise
     */
    private boolean checkForBalancedBrackets(String exp) {
        // YOUR CODE HERE

        Stack<Character> stack = new Stack<>();

        // Add the expression to a char array.
        // If value is '(', push to stack
        // if value is ')' pop '(' from stack
        for (char c : exp.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }

        // If stack is empty, returns true
        return stack.isEmpty();
    }
}

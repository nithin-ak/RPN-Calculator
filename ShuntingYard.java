/**
 * Shunting yard algorithm to convert a conventional math expression into postfix notation for
 * reverse polish notation.
 */

public class ShuntingYard {
    /**
     * Default constructor
     */
    public ShuntingYard() {
    }

    /**
     * Shunting yard algorithm that converts the infix math expression into postfix
     * form.
     *
     * @param mathExp infix math expression
     * @return {@link String} - postfix expression
     * @throws IllegalArgumentException when the whitespaces delimiting the expression
     *                                  is not properly set.
     */
    public String convertToPostfix(String mathExp) {
        // YOUR CODE HERE

        Stack<String> outputStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        Operator currentOperator, previousOperator;

        String[] tokens = inputValidator(mathExp);

        for (String token : tokens) {

            // If number, add to output Stack
            if (isNumeric(token)) {
                outputStack.push(token);
            }

            // If factorial, add to output Stack
            else if (token.equals("!")) {
                outputStack.push(token);
            }

            // Left bracket - push to operator stack
            else if (token.equals("(")) {
                operatorStack.push("(");
            }

            // Right bracket - pop all operators from the operator stack into the output stack till the left bracket
            // pop the left bracket
            else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peekTop().equals("(")) {
                    outputStack.push(operatorStack.pop());
                }
                operatorStack.pop(); // Discard the "("
            }

            // If Operator, peek at the operator at the top of the operator stack
            // check precedence
            // if lower precedence, pop top operator and push it to output stack
            // else push Operator to operator stack
            else if (Operator.lookupSymbol(token)) {
                currentOperator = Operator.getValueOfSymbol(token);
                previousOperator = Operator.getValueOfSymbol(operatorStack.peekTop());

                // To prevent null pointer exception, pointing previous operator to current operator if null
                if (previousOperator == null)
                    previousOperator = currentOperator;

                // To change the associativity from left to right, change <= 0 to == -1
                while (!operatorStack.isEmpty() &&
                        !operatorStack.peekTop().equals("(") &&
                        currentOperator.comparePrecedence(previousOperator) <= 0) {
                    outputStack.push(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        // Move any remaining elements to output stack
        while (!operatorStack.isEmpty()) {
            outputStack.push(operatorStack.pop());
        }

        // Construct output string
        StringBuilder postfix = new StringBuilder();
        while (!outputStack.isEmpty()) {
            postfix.insert(0, outputStack.pop());
            postfix.insert(0, " ");
        }

        String finalPostfixExpression = postfix.toString().trim(); // Remove leading/trailing whitespaces

        // Check if the input provided is already a postfix expression
        if (finalPostfixExpression.equals(mathExp))
            throw new IllegalArgumentException("Invalid expression " + mathExp);

        return finalPostfixExpression;
    }

    /**
     * Helper method to validate input
     *
     * @param mathExp input math expression
     * @return a string array with input expressions split using whitespace as delimiter.
     */
    private static String[] inputValidator(String mathExp) {
        String[] tokens = mathExp.split(" ");

        if (!mathExp.matches("[-+*/%^!()0-9. ]+.* +.*") || // Check if input has all valid characters and spaces
                Operator.lookupSymbol(tokens[0])) // Check if first item is an operator
            throw new IllegalArgumentException("Invalid math expression: " + mathExp);

        // Check false operators
        for (int i = 0; i < tokens.length - 1; i++) {
            Boolean token = tokens[i].matches("[-+*/%^]");
            Boolean nextToken = tokens[i + 1].matches("[-+*/%^!]");

            // Checking for 2 consecutive operators
            if (token && nextToken) {
                throw new IllegalArgumentException("Invalid operator combination in math expression: " + tokens[i] + " and " + tokens[i + 1]);
            }
            // Check consecutive whitespaces
            if (tokens[i].isEmpty())
                throw new IllegalArgumentException("White Spaces for delimiting are not properly set");

            // Check if each token >1 in length is a Double
            String[] individualTokens = tokens[i].split("");
            if (individualTokens.length > 1) {
                try {
                    Double.parseDouble(tokens[i]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid math expression at: " + tokens[i]);
                }
            }
        }
        return tokens;
    }


    /**
     * Helper method to check if the input is numeric
     *
     * @param str input value
     * @return {@code true} if it's a numeric, else {@code false}
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

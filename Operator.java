/**
 * Enums of available operands.
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Each operator has a calculate method that does the calculation for that
 * particular operator.
 */
interface Operators {
    /**
     * New MathContext with precision of 6 and HALF_EVEN rounding mode
     */
    MathContext mc = new MathContext(6, RoundingMode.HALF_EVEN);

    BigDecimal calculate(BigDecimal a, BigDecimal b);
}

/**
 * Enum of available operators for the calculator:
 * <ul>
 *     <li>Addition (+) - accepts 2 inputs. Calculation to be done using BigDecimal.</li>
 *     <li>Subtraction (-) - accepts 2 inputs. Calculation to be done using BigDecimal.</li>
 *     <li>Division (/) - accepts 2 inputs. Calculation to be done using BigDecimal.</li>
 *     <li>Multiplication (*) - accepts 2 inputs. Calculation to be done using BigDecimal.</li>
 *     <li>Modulus (%) - accepts 2 inputs. Calculation to be done using BigDecimal.</li>
 *     <li>Power/Exponential (^) - accepts 2 inputs. Calculation to be done using BigDecimal.</li>
 *     <li>Factorial (!) - accepts 1 input, the other input can be a constant but
 *     it MUST NOT be used. Calculation to be done using BigInteger but calculated result
 *     is to be returned as a BigDecimal.</li>
 * </ul>
 */
public enum Operator implements Operators {
    // YOUR CODE HERE

    // enum values
    /**
     * Addition operation with precedence of 1
     */
    ADDITION("+", 1),
    /**
     * Subtraction operation with precedence of 1
     */
    SUBTRACTION("-", 1),
    /**
     * Multiplication operation with precedence of 2
     */
    MULTIPLICATION("*", 2),
    /**
     * Division operation with precedence of 2
     */
    DIVISION("/", 2),
    /**
     * Modulus operation with precedence of 2
     */
    MODULUS("%", 2),
    /**
     * Power operation with precedence of 3
     */
    POWER("^", 3),
    /**
     * Factorial operation with precedence of 4
     */
    FACTORIAL("!", 4);

    private final int precedence;
    private final String symbol;


    /**
     * A private constructor used by the enum to initialize each enum constant with its symbol and precedence.
     * @param sym symbol of the enum
     * @param precedence precedence or weightage of each operator
     */
    Operator(String sym, int precedence) {
        this.symbol = sym;
        this.precedence = precedence;
    }

    /**
     * Check if the symbol is part of the available operators
     *
     * @param sym symbol to check
     * @return boolean - true if exists, false otherwise
     */
    public static boolean lookupSymbol(String sym) {
        // YOUR CODE HERE

        // Iterate through enum and return true if found
        for (Operator operator : Operator.values()) {
            if (operator.symbol.equals(sym))
                return true;
        }
        return false;
    }

    /**
     * Get the enum based on the given symbol.
     *
     * @param sym symbol to get
     * @return {@link Operator} - the enum object
     */
    public static Operator getValueOfSymbol(String sym) {
        // YOUR CODE HERE

        // Iterate through enum and return operator if found
        for (Operator operator : Operator.values()) {
            if (operator.symbol.equals(sym))
                return operator;
        }
        return null;
    }

    /**
     * CompareTo method of Enum cannot be overridden thus this method is provided
     * specially for comparing precedence values only.
     *
     * @param op other operand whose precedence value to compare
     * @return -1 (less than), 0 (equal to), or 1 (greater than) the specified object
     */
    public int comparePrecedence(Operator op) {
        // YOUR CODE HERE

        // Compares precedence between 2 operators
        return Integer.compare(this.precedence, op.precedence);
    }

    /**
     * Method to calculate based on selected operator
     * @param a BigDecimal value of first variable
     * @param b BigDecimal value of second variable
     * @return calculated output as BigDecimal
     */
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        switch (this) {
            case ADDITION:
                return a.add(b, mc);
            case SUBTRACTION:
                return a.subtract(b, mc);
            case MULTIPLICATION:
                return a.multiply(b, mc);
            case DIVISION:
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a.divide(b, mc);
            case MODULUS:
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Modulus by zero");
                }
                return a.remainder(b, mc);
            case POWER: {

                // Calculate integer part of the power
                BigDecimal result = a.pow(b.intValue(), mc);

                // Compute the fractional part separately
                // Math.pow function used for remainder as bigDecimal power function can only accept integers
                // Assuming 'complex numbers' will not be used per question description, this line is unlikely to make any difference to the output.
                BigDecimal fractionalPart = BigDecimal.valueOf(Math.pow(a.doubleValue(), b.remainder(BigDecimal.ONE).doubleValue()));

                // Compute the final result by multiplying the integer part with the fractional part
                result = result.multiply(fractionalPart);

                return result;
            }
            case FACTORIAL:
                if (a.compareTo(BigDecimal.ZERO) < 0) {
                    throw new ArithmeticException("Factorial of negative number");
                }
                return factorial(a.toBigInteger());
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + this);
        }
    }

    /**
     * A helper method to calculate factorial
     *
     * @param i input number
     * @return calculated factorial value in BigDecimal format
     */
    private BigDecimal factorial(BigInteger i) {
        if (i.equals(BigInteger.ZERO)) {
            return BigDecimal.ONE;
        } else {
            return new BigDecimal(i).multiply(factorial(i.subtract(BigInteger.ONE)), mc);
        }
    }
}

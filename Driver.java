import java.util.Arrays;

/**
 * A driver class to run test cases
 */
public class Driver {
    /**
     * Main method
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        String[] mathExpression = new String[]{
                "( 2 + 1 ) * 3",
                "( 9 ! - 8 ! ) / ( 300 * 100 ) - 12",
                "(1+2*3",
                "(1+2)*3",
                "7 - 4 + 2",
                "5 -  3",
                "* 2 + 3",
                "1 2 + * 3",
                null,
                "a b x",
                "9.1 !",
                "( 2.588904 * 3.547896 )",
                "3 - ( 4 * 5 ) + 6",
                ") (",
                "1 2 +",
                "9.8 ^ 2.5",
                "-9 * 2",
                "9 ^ -1",
                "( 9 - 8 ) * ! 2",
                "( 9 - 8 ) ! * 2",
                "9 ^ 2.0",
                "5 - ( 6 * 7 ) + 8 * 9",
                "(",
                "( ( ) )",
                "2 - ( 3 * 4 ) + 5",
                "( 2)",
                "-1!",
                "( 1 + 2 ) ( 1 + 2 )",
                "(3 + 7 ) - ( 5 - 4 )"
        };

        for (String s : mathExpression) {
            RPNCalculator rpnCalculator = new RPNCalculator();
            System.out.println("---------- Test Case " + s + " ----------\n");
            System.out.println(rpnCalculator.evaluate(s));
        }
    }
}




# RPN-Calculator
Implementation of Reverse Polish Notation calculator using Java. An RPN calculator operates by placing operands before operators, allowing for efficient computation without the need for parentheses.

## About RPN
A Reverse Polish Notation (RPN) calculator operates by placing operands before their respective operators. Unlike conventional calculators that use infix notation (placing operators between operands), RPN calculators utilize postfix notation, which can offer more straightforward parsing and computation.

In an RPN expression, operators follow their operands. For instance, to add 3 and 4, instead of writing "3 + 4", you'd write "3 4 +". This format eliminates the need for parentheses to denote precedence, as operations are performed strictly left-to-right.

Here's how a typical RPN calculator works:

1. **Input Handling**: Users input numbers and operators one at a time.
2. **Operand Stack**: Numbers are pushed onto a stack.
3. **Operator Processing**: When an operator is encountered, it pops the necessary operands from the stack, performs the operation, and pushes the result back onto the stack.
4. **Repeat**: Steps 1-3 continue until the expression is fully evaluated.
5. **Result**: The final result is the only item left on the stack after all operations have been performed.

Advantages of RPN calculators include:

- **No Need for Parentheses**: RPN eliminates ambiguity in complex expressions by not requiring parentheses to specify the order of operations.
- **Reduced Keystrokes**: Users often find RPN calculators faster as they don't need to enter parentheses or operator keys as frequently.
- **Stack-Based Logic**: The use of a stack closely mirrors the way computers process mathematical expressions, making RPN calculators more intuitive for programmers and engineers.

While RPN calculators may initially seem unfamiliar to those accustomed to infix notation, they often become preferred for their efficiency and simplicity once users become accustomed to their unique input style.

## Usage
All files to be placed in the `src` folder of your java project.

The `Driver.Java` file includes test cases showing how to use the code.

**Example:**
```java
System.out.println(rpnCalculator.evaluate("( 2 + 1 ) * 3"));
```

The code is innately left associativity.

To change the associativity from left to right, change `<= 0` to `== -1` on line 72 in the `ShuntingYard.java` file.

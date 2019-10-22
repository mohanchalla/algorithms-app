package problems.stack;

import com.datastructors.Stack;

/**
 * 1] Read the next symbol from the input.
 * 2] If the symbol is an operand Push it onto the stack.
 * 3] If the symbol is an operator Pop the top 2 values from the stack.
 * 4] Create a string by concatenating the two operands and the operator in between them.
 * 5] string = (operand2 + operator + operand1)
 * 6] And push the resultant string back to Stack
 * 7] Repeat the above steps until end of Prefix expression. */
public class PostfixToInfixNotation {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        String expression = "ab*c+";
        for (int i = 0; i < expression.length(); i++) {
            String s = String.valueOf(expression.charAt(i));
            if (isOperand(s)) {
                stack.push(s);
            } else if (isOperator(s)) {
                if (!stack.isEmpty()) {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    stack.push("(" + operand2 + s + operand1 + ")");
                }
            }
        }
        System.out.println(stack.pop());
    }

    private static boolean isOperand(String value) {
        return !isOperator(value);
    }

    private static boolean isOperator(String value) {
        switch (value) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                return true;
            default:
                return false;
        }
    }
}

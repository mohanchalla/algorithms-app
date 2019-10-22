package problems.stack;

import com.datastructors.Stack;

/**
 * 1] Read the Prefix expression in reverse order (from right to left)
 * 2] If the symbol is an operand, then push it onto the Stack
 * 3] If the symbol is an operator, then pop two operands from the Stack
 * 4] Create a string by concatenating the two operands and the operator between them.
 * 5] string = (operand1 + operator + operand2)
 * 6] And push the resultant string back to Stack
 * 7] Repeat the above steps until end of Prefix expression.
 */
public class PrefixToInfixNotation {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        String expression = "*-A/BC-/AKL";
        for (int i = expression.length(); i > 0; i--) {
            String s = String.valueOf(expression.charAt(i-1));
            if (isOperand(s)) {
                stack.push(s);
            } else if (isOperator(s)) {
                if (!stack.isEmpty()) {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    stack.push("(" + operand1 + s + operand2 + ")");
                }
            }
        }
        if (!stack.isEmpty())
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

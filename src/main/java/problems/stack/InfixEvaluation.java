package problems.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Prefix Expression evaluation scan from right to left.
 * ex: - + * 2 3 * 5 4 9
 */
public class InfixEvaluation {
    private static final List<Character> operators;

    static {
        operators = new ArrayList<>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');
    }

    public static void main(String[] args) {
        String exp = "- + * 2 3 * 5 4 9";
        int result = evaluatePrefix(exp);
        System.out.println(result);
    }

    private static int evaluatePrefix(String exp) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = exp.split(" ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            char token = tokens[i].charAt(0);
            if (isOperand(token)) {
                stack.push(Character.getNumericValue(token));
            } else {
                int op1 = stack.pop();
                int op2 = stack.pop();
                int res = perform(op1, op2, token);
                stack.push(res);
            }
        }
        return stack.peek();
    }

    private static int perform(int op1, int op2, char token) {
        int result;
        switch (token) {
            case '+':
                result = op1 + op2;
                break;
            case '-':
                result = op1 - op2;
                break;
            case '*':
                result = op1 * op2;
                break;
            case '/':
                result = op1 / op2;
                break;
            default:
                throw new IllegalArgumentException("Invalid Operator!");
        }
        return result;
    }

    private static boolean isOperator(char token) {
        return operators.contains(token);
    }

    private static boolean isOperand(char token) {
        return !isOperator(token);
    }
}

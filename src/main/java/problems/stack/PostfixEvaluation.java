package problems.stack;

import com.datastructors.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Evaluation of postfix notation happen from left to right
 * ex: 2 3 * 5 4 * + 9 -
 * To evaluate it we need to use stack data structure we can also use List
 * but, from list also we have to take values from last two operands so stack is the best suitable data structor here.
 */
public class PostfixEvaluation {
    private static final List<Character> operators;

    static {
        operators = new ArrayList<>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');
    }

    public static void main(String[] args) {
        String exp = "2 3 * 5 4 * + 9 -";
        int result = evaluatePostfixExpression(exp);
        System.out.println(result);
    }

    private static int evaluatePostfixExpression(String exp) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = exp.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i].charAt(0);
            if (isOperand(token)) {
                stack.push(Character.getNumericValue(token));
            } else {
                int op2 = stack.pop();
                int op1 = stack.pop();
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
                throw new RuntimeException("Invalid operator!");

        }
        return result;
    }

    private static boolean isOperator(Character token) {
        return operators.contains(token);
    }

    private static boolean isOperand(Character token) {
        return !isOperator(token);
    }
}

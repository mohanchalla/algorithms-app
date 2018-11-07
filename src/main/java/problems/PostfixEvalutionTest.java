package problems;

import com.datastructors.Stack;

import java.util.ArrayList;
import java.util.List;

public class PostfixEvalutionTest {
    private static final List<Character> operators = new ArrayList<>();

    static {
        operators.add('*');
        operators.add('+');
        operators.add('-');
        operators.add('/');
    }

    public static void main(String[] args) {
        String expr = "23 3 * 5 4 * + 9 -";

        Stack<Integer> stack = new Stack<>();
        String[] tokenArray = expr.split(" ");
        char[] tokens = new char[expr.length()];
        for (int i =0; i < tokenArray.length; i++) {
            if (tokenArray[i].contains(" "))
                continue;
            tokens[i] = tokenArray[i].charAt(0);
        }
        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];
            if (operators.contains(token)) {
                int op2 = stack.pop();
                int op1 = stack.pop();
                int result = perform(token, op1, op2);
                stack.push(result);
            } else if (Character.isDigit(token)) {
                stack.push(Character.getNumericValue(token));
            }
        }
        if (!stack.isEmpty())
            System.out.println(stack.pop());
    }

    private static int perform(char c, int op1, int op2) {
        switch (c) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
            case '/':
                return op1 / op2;
            default:
                throw new IllegalArgumentException();
        }
    }
}

package problems.stack;

import com.datastructors.Stack;

public class InfixToPostfixNotation {

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<Character>();
        String expression = "a+b*(c^d-e)^(f+g*h)-i";
        StringBuffer sb = new StringBuffer();
        for (int i =0 ; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (isOperand(c)) {
                sb.append(c);
            } else if (isOpenParenthesis(c)) {
                stack.push(c);
            } else if (isClosedParenthesis(c)) {
                while (!stack.isEmpty() && !isOpenParenthesis(stack.peek())) {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else if (isOperator(c)) {
                if (!stack.isEmpty() && hasHigherPrecedence(stack.peek(), c)) {
                    while (!stack.isEmpty() && !isOpenParenthesis(stack.peek())) {
                        sb.append(stack.pop());
                    }
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        return precedenceLevel(op1) > precedenceLevel(op2);
    }

    private static int precedenceLevel(char operator) {
        switch (operator) {
            case '(':
                return -1;
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            case '^':
                return 2;
                default:
                    throw new RuntimeException("No matching operator found! " + operator);
        }
    }

    private static boolean isOpenParenthesis(char c) {
        return c == '(';
    }

    private static boolean isClosedParenthesis(char c) {
        return c == ')';
    }

    private static boolean isOperand(char value) {
        return !isOperator(value) && !isOpenParenthesis(value) && !isClosedParenthesis(value);
    }

    private static boolean isOperator(char value) {
        switch (value) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
            return true;
            default:
                return false;
        }
    }
}

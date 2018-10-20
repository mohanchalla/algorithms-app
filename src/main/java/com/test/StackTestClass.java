package com.test;

public class StackTestClass {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        int i = 2147483647;
        System.out.println(Integer.MAX_VALUE);
        System.out.println("Size of stack is:: " + stack.size());
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}

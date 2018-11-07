package com.test;

import com.datastructors.Stack;

public class StackTestClass {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 1; i <= 44; i ++) {
            stack.push(i);
        }
        System.out.println("done");
    }
}

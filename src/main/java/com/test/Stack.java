package com.test;

import java.util.Arrays;

public class Stack<E extends Comparable<E>> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private int size;
    private int top = -1;
    private Object[] data;// array to store the data

    public Stack() {
        data = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E element) {
        if (top == DEFAULT_INITIAL_CAPACITY -1) {
            growCapacity();
            //throw new ArrayIndexOutOfBoundsException("Stack is full!");
        }
        data[++top] = element;
        size++;
    }

    private void growCapacity() {
        Object[] temp = data;
        data = Arrays.copyOf(temp, DEFAULT_INITIAL_CAPACITY * 2);
    }

    public E pop() {
        if (top == -1) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty!");
        }
        size--;
        return (E) data[top--];
    }

    public E peek() {
        if (isEmpty()) {
         return null;
        }
        return (E) data[top];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

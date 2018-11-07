package com.datastructors;

public class LStack<E> {
    private LinkedList<E> data;

    public LStack() {
        this.data = new LinkedList<E>();
    }

    public void push(E data) {
        this.data.addFirst(data);
    }

    public E pop() {
        return this.data.removeFirst();
    }

    public E peek() {
        return this.data.getFirst();
    }

    public int size() {
        return this.data.size();
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

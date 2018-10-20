package com.test;

public class AQueue<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5; // the size of the array
    private int front;
    private int rear;
    private Object data[];
    private int size;

    public AQueue() {
        this.data = new Object[DEFAULT_INITIAL_CAPACITY];
        this.front = -1;
        this.rear = -1;
    }

    private boolean isFull() {
        // Because we are forming a circular array if rear+1 % size == front it means queue is full.
        return (this.rear + 1) % DEFAULT_INITIAL_CAPACITY == this.front;
    }

    public void enqueue(E data) {
        if (isFull()) {
            throw new IllegalArgumentException("queue is already full!");
        } else if (isEmpty()) {
            this.front = this.rear = ++this.rear;
        } else {
            // To go to the next index always say rear = (rear+1) % size (here the size of the array is DEFAULT_INITIAL_CAPACITY).
            this.rear = (this.rear + 1) % DEFAULT_INITIAL_CAPACITY;
        }
        this.data[this.rear] = data;
        size++;
    }

    public int size() {
        return size;
    }

    public E dequeue() {
        int index;
        if (isEmpty()) {
            // if array is empty we can't deque anything.
            throw new ArrayIndexOutOfBoundsException("Queue underflow error!");
        } else if (this.front == this.rear) {
            index = this.front;
            // if array contains only one element then when dequeuing set the front <- rear <- -1
            this.rear = this.front = -1;
        } else {
            index = this.front;
            this.front = (this.front + 1) % DEFAULT_INITIAL_CAPACITY;
        }
        size--;
        return (E) this.data[index];
    }

    public E front() {
        if (isEmpty())
            return (E) null;
        return (E) this.data[this.front];
    }

    public boolean isEmpty() {
        return this.front == -1 && this.rear == -1;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";

        int front = this.front;
        StringBuilder builder = new StringBuilder("[");

        while (front != this.rear) {
            builder.append(data[front] + ", ");
            front = (front+1) % DEFAULT_INITIAL_CAPACITY;
        }
        if (front == this.rear) {
            builder.append(data[front] + ", ");
        }
        return builder.substring(0, builder.lastIndexOf(",")) + "]";
    }
}

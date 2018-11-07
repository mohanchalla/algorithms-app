package com.datastructors;

public class AQueue<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5; // the size of the array
    private int front;
    private int rear;
    private Object elements[];
    private int size;
    private int capacity;

    public AQueue() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
        this.front = -1;
        this.rear = -1;
        capacity = DEFAULT_INITIAL_CAPACITY;
    }

    public AQueue(int capacity) {
        this.elements = new Object[capacity];
        this.front = -1;
        this.rear = -1;
        this.capacity = capacity;
    }

    private boolean isFull() {
        // Because we are forming a circular array if rear+1 % size == front it means queue is full.
        return (this.rear + 1) % capacity == this.front;
    }

    public void enqueue(E data) {
        if (isFull()) {
            growCapacity();
        } else if (isEmpty()) {
            this.front = this.rear = ++this.rear;
        } else {
            // To go to the next index always say rear = (rear+1) % size (here the size of the array is DEFAULT_INITIAL_CAPACITY).
            this.rear = (this.rear + 1) % capacity;
        }
        this.elements[this.rear] = data;
        size++;
    }

    private void growCapacity() {
        int p = front;
        int n = elements.length;
        int r = n - p;
        int newCapacity = n * 2;
        this.capacity = newCapacity;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, p, newArray, 0, r); // To copy elements from front pointer to end of the array (i.e `n`)
        System.arraycopy(elements, 0, newArray, r, p); // To copy elements from index `0` to rear if the array is stored in circular fashion.
        elements = newArray;
        this.front = 0;
        this.rear = n;
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
            this.front = (this.front + 1) % capacity;
        }
        size--;
        return (E) this.elements[index];
    }

    public E front() {
        if (isEmpty())
            return (E) null;
        return (E) this.elements[this.front];
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
            builder.append(elements[front] + ", ");
            front = (front+1) % capacity;
        }
        if (front == this.rear) {
            builder.append(elements[front] + ", ");
        }
        return builder.substring(0, builder.lastIndexOf(",")) + "]";
    }
}

package com.test;

public class LinkedListQueue<E> {
    private int size;
    private Node<E> front;
    private Node<E> rear;

    private static class Node<E> {
        E data;
        Node<E> next;
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public LinkedListQueue() {
        this.front = this.rear = null;
    }

    public boolean isEmpty() {
        return this.front == null && this.rear == null;
    }

    public void push(E element) {
        if (element == null)
            throw new IllegalArgumentException("null can't be pushed into queue.");
        Node<E> newNode = new Node<>(element, null);
        if (isEmpty()) {
            this.front = this.rear = newNode;
        } else {
            this.rear.next = newNode;
            this.rear = newNode;
        }
        size++;
    }

    public E pop() {
        Node<E> node;
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (this.front == this.rear) {
            node = this.front;
            this.front = this.rear = null;
        } else {
            node = this.front;
            this.front = this.front.next;
        }
        size--;
        return node.data;
    }

    public int size() {
        return size;
    }

    public E peek() {
        return isEmpty() ? null : this.front.data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        Node<E> front = this.front;
        StringBuilder builder = new StringBuilder("[");
        while (front != this.rear) {
            builder.append(front.data + ", ");
            front = front.next;
        }
        if (front == this.rear) {
            builder.append(front.data + ", ");
        }
        return builder.substring(0, builder.lastIndexOf(",")) + "]";
    }
}

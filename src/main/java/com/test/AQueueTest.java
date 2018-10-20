package com.test;

public class AQueueTest {
    public static void main(String[] args) {
        AQueue<Integer> aQueue = new AQueue();
        aQueue.enqueue(1);
        aQueue.enqueue(2);
        aQueue.enqueue(3);
        System.out.println(aQueue);
        System.out.println(aQueue.size());
        System.out.println(aQueue.front());
        aQueue.enqueue(11);
        aQueue.enqueue(12);
        System.out.println(aQueue.front());
        System.out.println(aQueue.size());
        System.out.println(aQueue);
    }
}

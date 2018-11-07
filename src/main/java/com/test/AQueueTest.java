package com.test;


import com.datastructors.AQueue;

public class AQueueTest {
    public static void main(String[] args) {
        AQueue<Integer> queue = new AQueue<>();
        for (int i = 1; i <= 100; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.size());
        System.out.println(queue);
    }
}

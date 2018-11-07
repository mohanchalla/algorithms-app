package com.test;

import com.datastructors.LinkedListQueue;

public class LinkedListQueueTest {
    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 1; i <= 100; i++) {
            queue.push(i);
        }
        System.out.println(queue.size());
        for (int i = 1; i <= 99; i++) {
            queue.pop();
        }
        queue.push(101);
        System.out.println(queue.peek());
    }
}

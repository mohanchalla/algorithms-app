package com.test;

import com.datastructors.LinkedListQueue;

public class LinkedListQueueTest {
    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.push(1);
        queue.push(3);
        queue.push(4);
        queue.push(7);
        queue.push(8);
        System.out.println(queue.size());
        System.out.println(queue);
        queue.pop();
        System.out.println(queue);
    }
}

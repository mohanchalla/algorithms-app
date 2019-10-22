package problems.stack;

import com.datastructors.AQueue;

/**
 * // x is the element to be pushed and s is stack
 * push(s, x)
 *   1) Let size of q be s.
 *   1) Enqueue x to q
 *   2) One by one Dequeue s items from queue and enqueue them.
 *
 * // Removes an item from stack
 * pop(s)
 *   1) Dequeue an item from q
 */
public class StackInOneQueue {
    public static void main(String[] args) {
        AQueue<Integer> queue = new AQueue<>();

        push(queue,10);
        push(queue,20);
        push(queue,30);
        System.out.println(pop(queue));
        push(queue,40);
        System.out.println(pop(queue));
        push(queue,50);
        push(queue,60);
        push(queue,70);
        System.out.println(pop(queue));
        System.out.println(pop(queue));
    }

    private static void push(AQueue<Integer> queue, Integer val) {
        // get previous size of queue
        int size = queue.size();
        // Add current element
        queue.enqueue(val);

        // Pop (or Dequeue) all previous
        // elements and put them after current element
        for(int i = 0; i < size; i++) {
            // this will add front element into rear of queue
            int ele = queue.dequeue();
            queue.enqueue(ele);
        }
    }

    private static Integer pop(AQueue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.dequeue();
    }
}

package problems;

import com.datastructors.Stack;
import problems.exception.QueueEmptyException;

public class QueueInOneStack<E extends Comparable<E>> {
    Stack<E> stack = new Stack<>();

    public void enqueue(E data) {
        stack.push(data);
    }

    public E dequeue() {
        if (stack.isEmpty())
            throw new QueueEmptyException();
        if (stack.size() == 1)
            return stack.pop();
        else {
            E x =  stack.pop();
            E res = dequeue();
            stack.push(x);
            return res;
        }
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) {
        QueueInOneStack<Integer> queue = new QueueInOneStack<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        queue.enqueue(5);
        System.out.println(queue);
    }
}

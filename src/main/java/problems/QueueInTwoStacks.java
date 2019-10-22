package problems;

import com.datastructors.Stack;
import problems.exception.QueueEmptyException;


/**
 * Algorithm:
 *
 */
public class QueueInTwoStacks<E extends Comparable<E>> {
    private Stack<E> stack1 = new Stack<>();
    private Stack<E> stack2 = new Stack<>();

    public void enqueue(E data) {
        stack1.push(data);
    }

    public E peek() {
        return stack2.peek();
    }

    public E dequeue() {
        if (stack1.isEmpty() && stack2.isEmpty())
            throw new QueueEmptyException();
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        QueueInTwoStacks<Integer> stacks = new QueueInTwoStacks<>();
        stacks.enqueue(1);
        stacks.enqueue(2);
        stacks.enqueue(3);
        stacks.enqueue(4);
        System.out.println(stacks.dequeue());
    }
}

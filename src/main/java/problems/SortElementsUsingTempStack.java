package problems;

import com.datastructors.Stack;

public class SortElementsUsingTempStack<E extends Comparable<E>> {
    private Stack<E> supportingStack = new Stack<>();

    public SortElementsUsingTempStack() { }

    public Stack<E> sort(Stack<E> stack) {
        while (!stack.isEmpty()) {
            E element = stack.pop();
            while (!supportingStack.isEmpty() && element.compareTo(supportingStack.peek()) > 0) {
                stack.push(supportingStack.pop());
            }
            supportingStack.push(element);
        }
        return supportingStack;
    }

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();
        integerStack.push(5);
        integerStack.push(3);
        integerStack.push(2);
        integerStack.push(1);
        integerStack.push(10);
        integerStack.push(8);

        SortElementsUsingTempStack<Integer> stack = new SortElementsUsingTempStack<>();
        integerStack = stack.sort(integerStack);
        System.out.println(integerStack);
    }
}

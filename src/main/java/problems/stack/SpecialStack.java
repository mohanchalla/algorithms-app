package problems.stack;

import com.datastructors.Stack;

/**
 * get the minimum element from the stack
 */
public class SpecialStack extends Stack<Integer> {
    Stack<Integer> supportingStack = new Stack<>();

    @Override
    public void push(Integer element) {
        super.push(element);
        if (supportingStack.isEmpty() || peek() < supportingStack.peek()) {
            supportingStack.push(element);
        }
    }

    @Override
    public Integer pop() {
        Integer element = super.pop();
        if (supportingStack.peek() == element) {
            supportingStack.pop();
        }
        return element;
    }

    public Integer getMin() {
        return supportingStack.peek();
    }

    public static void main(String[] args) {
        SpecialStack ss = new SpecialStack();
        ss.push(5);
        ss.push(22);
        ss.push(1);
        ss.push(7);
        ss.push(20);
        System.out.println(ss);
        System.out.println(ss.getMin());
        for (int i = 1; i <= 4; i++) {
            ss.pop();
        }
        System.out.println(ss.getMin());
        ss.push(2);
        System.out.println(ss);
        for (int i = 1; i <= 2; i++) {
            ss.pop();
        }
        System.out.println(ss.getMin());
    }
}

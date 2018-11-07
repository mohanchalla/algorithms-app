package problems;

import com.datastructors.Stack;
import com.exception.StackUndeFlowException;

public class SpecialStackWithoutExtraSpace {
    Stack<Integer> specialStack = new SpecialStack();
    private Integer minElement;

    /**
        Algorithm :
        1. If the stack is empty then push it into stack and update minElement = x.
        2. If the stack x is less than minElement then push 2*x - minElement into stack.
        (Actually you can multiply with any number if you follow this same order.)
     */
    public void push(Integer x) {
        if (specialStack.isEmpty()) {
            specialStack.push(x);
            minElement = x;
        } else if (x < minElement) {
            int res = 2*x - minElement;
            specialStack.push(res);
            minElement = x;
        } else {
            specialStack.push(x); // in all other cases just push into stack.
        }
    }

    /**
     * Algorithm:
     * 1. If the popped element y is greater than just return the popped element.
     * 2. If the popped element y is less than or equal to minElement then
     *          minElement = 2*minElement - y.
     * @return
     */
    public Integer pop() {
        if (specialStack.isEmpty()) {
            throw new StackUndeFlowException("Stack is empty!");
        }
        Integer y = specialStack.pop();
        if (y <= minElement) {
            minElement = 2*minElement - y;
        }
        return y;
    }

    public Integer getMin() {
        return minElement;
    }

    @Override
    public String toString() {
        return specialStack.toString();
    }

    public static void main(String[] args) {
        SpecialStackWithoutExtraSpace ss = new SpecialStackWithoutExtraSpace();
        ss.push(3);
        ss.push(5);
        System.out.println(ss.getMin());
        ss.push(2);
        System.out.println(ss.getMin());
        ss.push(1);
        ss.push(1);
        System.out.println(ss.getMin());
        ss.push(-1);
        System.out.println(ss.getMin());
        System.out.println(ss);

        System.out.println("------------------After popping---------------------");
        ss.pop();
        System.out.println(ss);
        System.out.println(ss.getMin());
        ss.pop();
        System.out.println(ss.getMin());
        ss.pop();
        System.out.println(ss.getMin());
        ss.pop();
        System.out.println(ss.getMin());
    }
}

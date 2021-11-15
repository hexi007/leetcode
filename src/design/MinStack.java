package design;

import java.util.Stack;

/**
 * description 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。 pop()—— 删除栈顶的元素。
 * top()—— 获取栈顶元素。 getMin() —— 检索栈中的最小元素。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/min-stack 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-11-10 16:33
 **/
public class MinStack {

    private final Stack<Integer> stack1, stack2;

    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int val) {
        stack1.push(val);
        if (stack2.isEmpty() || val <= stack2.peek()) {
            stack2.push(val);
        }
    }

    public void pop() {
        if (!stack1.isEmpty()) {
            if (!stack2.isEmpty() && stack1.peek().equals(stack2.peek())) {
                stack2.pop();
            }
            stack1.pop();
        }
    }

    public int top() {
        return !stack1.isEmpty() ? stack1.peek() : -1;
    }

    public int getMin() {
        return !stack2.isEmpty() ? stack2.peek() : -1;
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }
}
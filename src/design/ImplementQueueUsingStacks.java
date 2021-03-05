package design;

import java.util.Stack;

/**
 * description 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素 boolean
 * empty() 如果队列为空，返回 true ；否则，返回 false
 * 说明：  你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-05 17:19
 **/
public class ImplementQueueUsingStacks {

    /**
     * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
     * (内存消耗：36.4 MB, 在所有 Java 提交中击败了48.15%的用户)
     */
    static class MyQueue {

        Stack<Integer> stack1, stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if (stack2.isEmpty()) {
                transfer();
            }
            return stack2.pop();
        }

        public int peek() {
            if (stack2.isEmpty()) {
                transfer();
            }
            return stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        private void transfer() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.peek());
        System.out.println(obj.pop());
        System.out.println(obj.empty());
    }
}
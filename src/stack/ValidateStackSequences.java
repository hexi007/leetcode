package stack;

import java.util.Stack;

/**
 * description 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复
 * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；
 * 否则，返回 false 。
 *
 * @author 27771
 * create 2020-11-12 11:00
 **/
public class ValidateStackSequences {
    static class Solution {
        /**
         * 直接用栈模拟操作
         * (执行用时：2 ms, 在所有 Java 提交中击败了92.09%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了81.09%的用户)
         * @param pushed 入栈操作序列
         * @param popped 出栈操作序列
         * @return       出栈操作是否有效
         */
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> stack = new Stack<>();
            int push = 0, pop = 0;
            while (push < pushed.length) {
                // 当前入栈数和出栈数相同，跳过此次操作
                if (pushed[push] == popped[pop]) {
                    push++;
                    pop++;
                    continue;
                }
                // 入栈前先尝试出栈
                while (!stack.isEmpty()) {
                    int top = stack.peek();
                    if (popped[pop] == top) {
                        pop++;
                        stack.pop();
                    } else {
                        break;
                    }
                }
                // 入栈
                stack.push(pushed[push++]);
            }
            // 栈不空尝试剩余出栈操作
            while (!stack.isEmpty()) {
                int top = stack.peek();
                if (popped[pop] == top) {
                    pop++;
                    stack.pop();
                } else {
                    // 无法出栈返回 false
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,3,5,1,2};
        System.out.println(new Solution().validateStackSequences(pushed,
                popped));
    }
}

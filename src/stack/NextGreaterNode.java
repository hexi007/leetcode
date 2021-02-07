package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * description 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
 * 每个节点都可能有下一个更大值（next larger value）：
 * 对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，
 * 而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。
 * 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-greater-node-in-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-06 22:01
 **/
public class NextGreaterNode {

    static class Solution {

        /**
         * 只需要使用一个栈来存储链表中每个节点在 list 中的位置即可，并且栈中元素在集合list中对应的值从栈底到栈顶是递减的
         * 进一步优化是先求链表长度再初始数组而不是用数组，栈也用数组模拟
         * (执行用时：23 ms, 在所有 Java 提交中击败了50.16%的用户)
         * (内存消耗：42.1 MB, 在所有 Java 提交中击败了68.36%的用户)
         *
         * @param head 头节点
         * @return     每个节点都下一个更大值
         */
        public int[] nextLargerNodes(ListNode head) {
            List<Integer> list = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();

            for (ListNode node = head; node != null; node = node.next) {
                // 当前链表节点值大于栈顶位置在 list 中的数，出栈并将该数置为当前链表节点值
                while (!stack.isEmpty() && node.val > list.get(stack.peek())) {
                    list.set(stack.pop(), node.val);
                }
                // 栈放入当前 list size
                stack.push(list.size());
                // list 放当前链表节点值
                list.add(node.val);
            }

            int[] res = new int[list.size()];

            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }

            while (!stack.isEmpty()) {
                res[stack.pop()] = 0;
            }

            return res;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int[] input) {
            ListNode temp = this;
            for (int i = 0; i < input.length; i++) {
                temp.val = input[i];
                if (i != input.length - 1) {
                    temp.next = new ListNode();
                    temp = temp.next;
                }
            }
        }

        public void printNodeFromRoot() {
            ListNode temp = this;
            while (temp != null) {
                System.out.print(temp.val + " -> ");
                temp = temp.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{2,7,2,3,5});
        System.out.println(Arrays.toString(new Solution().nextLargerNodes(head)));
    }
}

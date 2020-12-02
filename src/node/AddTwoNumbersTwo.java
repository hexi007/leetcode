package node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * description 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 进阶：  如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * @author 27771
 * create 2020-12-01 11:08
 **/
public class AddTwoNumbersTwo {

    static class Solution {

        /**
         * 先求和再构造链表,尾插法
         * (执行用时：5 ms, 在所有 Java 提交中击败了49.88%的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了65.78%的用户)
         *
         * @param l1 链表 1
         * @param l2 链表 2
         * @return   相加后新的链表
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            Stack<Integer> stack1 = new Stack<>();
            ListNode temp = l1;
            while (temp != null) {
                stack1.push(temp.val);
                temp = temp.next;
            }
            Stack<Integer> stack2 = new Stack<>();
            temp = l2;
            while (temp != null) {
                stack2.push(temp.val);
                temp = temp.next;
            }
            List<Integer> list = new ArrayList<>();
            int carryBit = 0, sum;
            while (!stack1.isEmpty() || !stack2.isEmpty()) {
                if (!stack1.isEmpty() && !stack2.isEmpty()) {
                    sum = stack1.pop() + stack2.pop() + carryBit;
                } else if (!stack1.isEmpty()) {
                    sum = stack1.pop() + carryBit;
                } else {
                    sum = stack2.pop() + carryBit;
                }
                if (sum >= 10) {
                    sum %= 10;
                    carryBit = 1;
                } else {
                    carryBit = 0;
                }
                list.add(sum);
            }
            if (carryBit == 1) {
                list.add(1);
            }
            ListNode res = new ListNode();
            temp = res;
            for (int i = list.size() - 1; i >= 0; i--) {
                ListNode newNode = new ListNode(list.get(i));
                temp.next = newNode;
                temp = newNode;
            }
            return res.next;
        }

        /**
         * 先求和再构造链表,头插法
         * (执行用时：5 ms, 在所有 Java 提交中击败了49.88%的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了65.78%的用户)
         *
         * @param l1 链表 1
         * @param l2 链表 2
         * @return   相加后新的链表
         */
        public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
            Stack<Integer> stack1 = new Stack<>();
            ListNode temp = l1;
            while (temp != null) {
                stack1.push(temp.val);
                temp = temp.next;
            }
            Stack<Integer> stack2 = new Stack<>();
            temp = l2;
            while (temp != null) {
                stack2.push(temp.val);
                temp = temp.next;
            }
            int carryBit = 0, sum;
            ListNode res = null;
            while (!stack1.isEmpty() || !stack2.isEmpty()) {
                if (!stack1.isEmpty() && !stack2.isEmpty()) {
                    sum = stack1.pop() + stack2.pop() + carryBit;
                } else if (!stack1.isEmpty()) {
                    sum = stack1.pop() + carryBit;
                } else {
                    sum = stack2.pop() + carryBit;
                }
                if (sum >= 10) {
                    sum %= 10;
                    carryBit = 1;
                } else {
                    carryBit = 0;
                }
                ListNode newNode = new ListNode(sum);
                newNode.next = res;
                res = newNode;
            }
            if (carryBit == 1) {
                ListNode newNode = new ListNode(carryBit);
                newNode.next = res;
                res = newNode;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{5});
        ListNode l2 = new ListNode(new int[]{5});
        ListNode ret = new Solution().addTwoNumbers(l1,l2);
        ret.printNodeFromRoot();
        ListNode ret1 = new Solution().addTwoNumbers1(l1,l2);
        ret1.printNodeFromRoot();
    }
}

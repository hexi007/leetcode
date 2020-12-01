package node;

import java.util.Arrays;

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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int node1Length = 0;
            ListNode temp = l1;
            while (temp != null) {
                node1Length++;
                temp = temp.next;
            }
            int node2Length = 0;
            temp = l2;
            while (temp != null) {
                node2Length++;
                temp = temp.next;
            }
            int maxLength = Math.max(node1Length, node2Length);
            int minLength = Math.min(node1Length, node2Length);
            temp = node1Length > node2Length ? l1 : l2;
            ListNode temp1 = node1Length > node2Length ? l2 : l1;
            int[] carryBit = new int[maxLength + 1];

            ListNode res = new ListNode();
            ListNode tempRes = res;
            for (int i = 0; i < maxLength; i++) {
                ListNode temp2 = new ListNode();
                if (maxLength - minLength > i) {
                    temp2.val = temp.val;
                    temp = temp.next;
                } else {
                    System.out.println(temp.val + " " + temp1.val);
                    int sum = temp.val + temp1.val;
                    temp = temp.next;
                    temp1 = temp1.next;
                    if (sum >= 10) {
                        sum %= 10;
                        carryBit[i + 1] = 1;
                    }
                    temp2.val = sum;
                }
                tempRes.next = temp2;
                tempRes = tempRes.next;
            }
            System.out.println(Arrays.toString(carryBit));
            return res;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{7,5,4,3});
        ListNode l2 = new ListNode(new int[]{5,6,4});
        ListNode ret = new Solution().addTwoNumbers(l1,l2);
        ret.printNodeFromRoot();
    }
}

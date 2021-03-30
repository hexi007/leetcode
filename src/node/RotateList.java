package node;

/**
 * description 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 * @author 27771
 * create 2021-03-30 09:25
 **/
public class RotateList {

    static class Solution {

        /**
         * 先找最后一个节点构造循环链表，再确定旋转后头尾节点
         * (执行用时：1 ms, 在所有 Java 提交中击败了51.38%的用户)
         * (内存消耗：38.1 MB, 在所有 Java 提交中击败了7.75%的用户)
         *
         * @param head 头节点
         * @param k    向右移动 k 个位置
         * @return     旋转后头节点
         */
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }

            // 计算链表长度和找到尾节点
            int length = 1;
            ListNode tail = head;
            while (tail.next != null) {
                length++;
                tail = tail.next;
            }

            // 构造循环链表
            tail.next = head;
            // 确定旋转后头尾节点
            ListNode newTail = head, newHead;
            // 先确定新的尾节点所在
            for (int i = 0; i < length - 1 - (k % length); i++) {
                newTail = newTail.next;
            }
            // 新的头节点就是尾节点的后一个
            newHead = newTail.next;
            // 新尾节点之后置为空，断开循环链表
            newTail.next = null;
            return newHead;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        new Solution().rotateRight(head, 2).printNodeFromRoot();
    }
}

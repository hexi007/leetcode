package node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description 给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→...
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author 27771
 * create 2020-10-20 09:43
 **/
public class ReorderList {
    static class Solution {
        /**
         递归的将对最后一个夹在开头的后面
         (执行用时：513 ms, 在所有 Java 提交中击败了5.42%的用户)
         (内存消耗：41.9 MB, 在所有 Java 提交中击败了31.78%的用户)
         * @param head 头节点
         */
        public void reorderList(ListNode head) {
            if(head == null || head.next == null || head.next.next == null){
                return;
            }
            ListNode temp = head.next;
            head.next = getTail(head.next);
            head.next.next = temp;
            reorderList(head.next.next);
        }

        /**
         找最后一个节点，便注意断开他
         * @param head 开始的节点
         * @return 最后一个节点
         */
        private ListNode getTail(ListNode head) {
            ListNode temp = head;
            while (temp.next.next != null){
                temp = temp.next;
            }
            ListNode ret = temp.next;
            temp.next = null;
            return ret;
        }

        /**
         快慢指针找链表中点，反转后半段，再交叉拼接
         (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         (内存消耗：40.9 MB, 在所有 Java 提交中击败了97.55%的用户)
         * @param head 头节点
         */
        public void reorderList1(ListNode head){
            if(head == null  || head.next == null){
                return;
            }

            //快慢指针，lastSlow记录slow前一个节点，用来断链
            ListNode fast = head, slow = head, lastSlow = head;
            while (fast != null){
                if(fast.next == null){
                    fast = fast.next;
                } else {
                    fast = fast.next.next;
                }
                lastSlow = slow;
                slow = slow.next;
            }

            //断开链表
            lastSlow.next = null;

            //翻转后半段链表
            ListNode lastNode = null;
            while (slow != null){
                ListNode temp = slow.next;
                slow.next = lastNode;
                lastNode = slow;
                slow = temp;
            }

            //lastNode就是翻转后链表的头节点，以此拼接两个链表即可
            while(head != null && lastNode != null){
                ListNode temp = head.next;
                ListNode temp2 = lastNode.next;
                head.next = lastNode;
                head.next.next = temp;
                head = temp;
                lastNode = temp2;
            }
        }

        /**
         使用map使得消耗内存更小的方法
         * @param head 头节点
         */
        public void reorderList2(ListNode head) {
            if(head == null || head.next == null || head.next.next == null) {
                return ;
            }
            Map<ListNode,ListNode> m = new HashMap<>();
            ListNode p = head;
            while (p.next != null){
                m.put(p.next, p);
                p = p.next;
            }
            ListNode r = head;
            while(r != p && r != null){
                ListNode pre = m.get(p);
                pre.next = null;
                p.next = r.next;
                r.next = p;
                r = p.next;
                p = pre;
            }
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        new Solution().reorderList1(head);
        head.printNodeFromRoot();
    }
}

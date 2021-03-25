package node;

import java.util.HashMap;

/**
 * description 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * @author 27771
 * create 2021-01-05 10:57
 **/
public class RemoveDuplicatesFromSortedListTwo {

    static class Solution {

        /**
         * 暴力存储节点去重,用 hashmap 保存出现次数
         * (执行用时：2 ms, 在所有 Java 提交中击败了9.86%的用户)
         * (内存消耗：37.6 MB, 在所有 Java 提交中击败了92.26%的用户)
         *
         * @param head 头节点
         * @return     不带重复节点的链表
         */
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }

            HashMap<Integer, Integer> map = new HashMap<>(16);
            ListNode temp = head;
            while (temp != null) {
                map.put(temp.val, map.getOrDefault(temp.val, 0) + 1);
                temp = temp.next;
            }

            ListNode res = new ListNode(0), resNode = res;
            temp = head;
            while (temp != null) {
                if (map.get(temp.val) == 1) {
                    resNode.next = temp;
                    resNode = resNode.next;
                }
                temp = temp.next;
            }

            resNode.next = null;

            return res.next;
        }

        /**
         * 利用数组有序特性
         * (执行用时：0 ms, 在所有 Java 提交中击败了100%的用户)
         * (内存消耗：38 MB, 在所有 Java 提交中击败了19.85%的用户)
         *
         * @param head 头节点
         * @return     不带重复节点的链表
         */
        public ListNode deleteDuplicates1(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }

            if (head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                return deleteDuplicates1(head.next);
            } else {
                head.next = deleteDuplicates1(head.next);
                return head;
            }
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1,2,2});
        head = new Solution().deleteDuplicates(head);
        head.printNodeFromRoot();

        ListNode head1 = new ListNode(new int[]{1,2,2,2});
        head1 = new Solution().deleteDuplicates1(head1);

        head1.printNodeFromRoot();
    }
}
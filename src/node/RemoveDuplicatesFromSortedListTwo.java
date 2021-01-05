package node;

import java.util.ArrayList;

/**
 * description 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * @author 27771
 * create 2021-01-05 10:57
 **/
public class RemoveDuplicatesFromSortedListTwo {

    static class Solution {

        ArrayList<Integer> list;

        /**
         * 暴力存储节点去重
         * (执行用时：2 ms, 在所有 Java 提交中击败了9.86%的用户)
         * (内存消耗：37.6 MB, 在所有 Java 提交中击败了92.26%的用户)
         *
         * @param head 头节点
         * @return     不带重复节点的链表
         */
        public ListNode deleteDuplicates(ListNode head) {
            ListNode node1 = head;
            list = new ArrayList<>();

            while (node1 != null) {
                list.add(node1.val);
                node1 = node1.next;
            }

            node1 = new ListNode();
            ListNode res = node1;
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    node1.next = new ListNode(list.get(i));
                    node1 = node1.next;
                }
                if (i + 1 < list.size() && !list.get(i + 1).equals(list.get(i))) {
                    node1.next = new ListNode(list.get(i));
                    node1 = node1.next;
                } else {
                    while (i + 1 < list.size() && list.get(i + 1).equals(list.get(i))) {
                        i++;
                    }
                }

            }

            return res.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1,1});
        head = new Solution().deleteDuplicates(head);

        head.printNodeFromRoot();
    }
}
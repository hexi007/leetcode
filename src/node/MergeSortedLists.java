package node;

/**
 * description 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author 27771
 * create 2021-04-11 10:33
 **/
public class MergeSortedLists {

    static class Solution {

        /**
         * 归并思想，不断二分 lists 直到只剩两个时采用普通的合并有序链表算法合并，再合并所有
         * (执行用时：2 ms, 在所有 Java 提交中击败了85.98%的用户)
         * (内存消耗：40.4 MB, 在所有 Java 提交中击败了25.37%的用户)
         *
         * @param lists 链表数组
         * @return      合并后的链表
         */
        public ListNode mergeLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            } else if (lists.length == 1) {
                return lists[0];
            } else if (lists.length == 1 + 1) {
                return mergeTwoLists(lists[0], lists[1]);
            }

            int len = lists.length, mid = len / 2;

            // l1 保存 lists 前一半数组
            ListNode[] l1 = new ListNode[mid];
            System.arraycopy(lists, 0, l1, 0, mid);

            // // l2 保存 lists 后一半数组
            ListNode[] l2 = new ListNode[len - mid];
            System.arraycopy(lists, mid, l2, 0, len - mid);

            // 递归调用先将 l1 里所有链表合并好，再将 l2 里所有链表合并好
            // 最后再将这两个合并后链表用普通的合并有序链表算法合并
            return mergeTwoLists(mergeLists(l1), mergeLists(l2));
        }

        /**
         * 合并两个有序链表
         *
         * @param list1 有序链表 1
         * @param list2 有序链表 2
         * @return      合并后有序链表
         */
        private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode head = new ListNode(0), temp = head;
            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    temp.next = list1;
                    list1 = list1.next;
                } else {
                    temp.next = list2;
                    list2 = list2.next;
                }
                temp = temp.next;
            }

            if (list1 != null) {
                temp.next = list1;
            }

            if (list2 != null) {
                temp.next = list2;
            }

            return head.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{1, 2, 4});
        ListNode l2 = new ListNode(new int[]{1, 3, 4});
        ListNode l3 = new ListNode(new int[]{2, 6});
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        new Solution().mergeLists(lists).printNodeFromRoot();
    }
}

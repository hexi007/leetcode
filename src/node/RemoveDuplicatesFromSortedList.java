package node;

/**
 * description 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 *
 * @author 27771
 * create 2021-03-26 10:22
 **/
public class RemoveDuplicatesFromSortedList {

    static class Solution {

        /**
         * 先判断递归条件，再递归处理后一个节点，最后如果当前节点值和后一个节点值想等头节点就要后移
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.1 MB, 在所有 Java 提交中击败了19.83%的用户)
         *
         * @param head 头节点
         * @return     去重后按升序排列的结果链表
         */
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            head.next = deleteDuplicates(head.next);
            if (head.val == head.next.val) {
                head = head.next;
            }
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1,2,2,2});
        new Solution().deleteDuplicates(head).printNodeFromRoot();
    }
}

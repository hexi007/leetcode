package node;

/**
 * description 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/partition-list 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-03 12:15
 **/
public class PartitionList {

    static class Solution {

        /**
         * 双指针
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：37.8 MB, 在所有 Java 提交中击败了49.43%的用户)
         *
         * @param head 链表头
         * @param x    特定 x 值
         * @return     满足条件的新链表
         */
        public ListNode partition(ListNode head, int x) {
            ListNode small = new ListNode(), big = new ListNode();
            ListNode smallTemp = small, bigTemp = big;

            while (head != null) {
                ListNode temp = head.next;
                // 分别记录比 x 小和大的链表
                if (head.val < x) {
                    smallTemp.next = head;
                    smallTemp = head;
                } else {
                    bigTemp.next = head;
                    bigTemp = head;
                }
                head.next = null;
                head = temp;
            }
            // 将两个链表拼接起来
            smallTemp.next = big.next;
            return small.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 4, 3, 2, 5, 2});
        int x = 3;
        new Solution().partition(head, x).printNodeFromRoot();
    }
}

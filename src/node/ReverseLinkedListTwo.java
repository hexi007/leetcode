package node;

/**
 * description 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-18 22:45
 **/
public class ReverseLinkedListTwo {

    static class Solution {
        /**
         * 找到要反转位置，反转后前后相连
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%)
         * (的用户内存消耗：36 MB, 在所有 Java 提交中击败了60.45%的用户)
         *
         * @param head  头指针
         * @param left  反转左起始位置
         * @param right 反转右起始位置
         * @return      反转后的链表
         */
        public ListNode reverseBetween(ListNode head, int left, int right) {
            int i = 0;
            // newHead 反转起始前的一个节点，tempHead 反转开始节点
            ListNode newHead = new ListNode(0), tempHead = head;
            // 不断找正确位置
            while (i < left - 1) {
                newHead = tempHead;
                tempHead = tempHead.next;
                i++;
            }
            // newTail 反转终点后一个节点，tail 最终指向节点
            ListNode newTail = tempHead, tail = tempHead;
            // 不断找正确位置
            while (i < right) {
                tail = newTail;
                newTail = newTail.next;
                i++;
            }

            // tempForTail 记录反转开始节点，反转后需要这个节点指向反转终点后一个节点
            ListNode tempForTail = tempHead;
            // 为了方便反转，最终指向节点的后一个节点置为空
            tail.next = null;
            // 反转从 tempHead 开始的节点
            tempHead = reverse(tempHead);
            // 反转起始位置前一个节点的后一个节点就可以设置为 tempHead 了
            newHead.next = tempHead;
            // 再把反转后最后一个节点指向反转位置后的一个节点
            tempForTail.next = newTail;

            // 考虑从第一个位置开始反转，那么 head 已经被反转后面去了，要返回 newHead.next
            return left == 1 ? newHead.next : head;
        }

        /**
         * 反转链表
         *
         * @param head 头节点
         * @return     反转后头节点
         */
        private ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            ListNode newReverseHead = reverse(next);
            next.next = head;
            head.next = null;
            return newReverseHead;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        int left = 1, right = 5;
        new Solution().reverseBetween(head, left, right).printNodeFromRoot();
    }
}

package node;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * @author 27771
 * create 2020-10-18 20:08
 **/
public class RemoveNthNodeFromEndOfList {
    static class Solution {
        /**
         * Description  经典快慢指针
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：36.3 MB, 在所有 Java 提交中击败了99.04% 的用户)
         * @param head 头指针
         * @param n 倒数第n个
         * @return  修改后头节点
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode ret = head;
            ListNode first = head, second = head;
            while(n > 0){
                second = second.next;
                n--;
            }
            //考虑删除的就是头节点
            if(second == null){
                return first.next;
            }
            while(second.next != null){
                first = first.next;
                second = second.next;
            }
            first.next = first.next.next;
            return ret;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        int n = 2;
        ListNode ret = new Solution().removeNthFromEnd(head, n);
        ret.printNodeFromRoot();
    }
}
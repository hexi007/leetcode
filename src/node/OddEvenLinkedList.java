package node;

import java.util.List;

/**
 * description 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * @author 27771
 * create 2020-11-13 15:03
 **/
public class OddEvenLinkedList {

    static class Solution {
        /***
         * 从奇偶节点出发构造奇偶排列列表
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了84.52% 的用户)
         * @param head 头节点
         * @return     排列后头节点
         */
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode odd = head;
            ListNode even = head.next;
            // 保存偶开始节点
            ListNode even1 = even;
            ListNode temp = even.next;
            while (temp != null) {
                odd.next = temp;
                even.next = temp.next;
                odd = temp;
                even = temp.next;
                if (even == null ) {
                    break;
                }
                temp = even.next;
            }
            // 奇末尾节点之后是偶节点
            odd.next = even1;
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{2,1,3,5,6,4,7});
        head.printNodeFromRoot();
        head = new Solution().oddEvenList(head);
        head.printNodeFromRoot();
    }
}
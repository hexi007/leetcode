package node;

/**
 * description 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * 说明：不允许修改给定的链表。  进阶：  你是否可以使用 O(1) 空间解决此题？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-29 18:24
 **/
public class LinkedListCycleTwo {

    static class Solution {

        /**
         * 先判断是否有环，再找有环时的入环第一个节点
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了33.40%的用户)
         *
         * @param head 头节点
         * @return     有环时的入环第一个节点
         */
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }

            // 快慢指针判断是否有环
            ListNode slow = head, fast = head;
            boolean cycle = false;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    cycle = true;
                    break;
                }
            }

            if (cycle) {
                // 有环时一个指针从头开始走，一个从 slow 开始走
                // 最终在入环第一个节点相遇
                ListNode temp = head;
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return temp;
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{3, 2, 0, -4});
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = head.next;
        System.out.println(new Solution().detectCycle(head).val);
    }
}

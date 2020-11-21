package node;

/**
 * description 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶: 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * @author 27771
 * create 2020-11-21 09:24
 **/
public class SortList {

    static class Solution {
        
        /** 
         * 为了满足进阶要求使用合并排序，但没有使用自底向上，所以不是常数级空间复杂度
         * (执行用时：11 ms, 在所有 Java 提交中击败了20.78%的用户)
         * (内存消耗：44.9 MB, 在所有 Java 提交中击败了27.30%的用户)
         *
         * @param head 头节点
         * @return     排序后头节点
         */
        public ListNode sortList(ListNode head) {
            //判读头节点各种情况
            if (head == null) {
                return null;
            } else if (head.next == null) {
                return head;
            } else if (head.next.next == null) {
                if (head.val > head.next.val) {
                    ListNode temp = head.next;
                    temp.next = head;
                    head.next = null;
                    head = temp;
                }
                return head;
            } else {
                // 快慢指针找中点
                ListNode fast = head, slow = head, tail = head;
                while (fast != null) {
                    if (fast.next == null) {
                        break;
                    }
                    fast = fast.next.next;
                    tail = slow;
                    slow = slow.next;
                }
                // 拆分链表
                tail.next = null;

                // 递归
                head = sortList(head);
                slow = sortList(slow);

                // 合并有序链表
                head = mergeTwoLists(head, slow);
                return head;
            }
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1 == null) {
                return l2;
            }
            if(l2 == null) {
                return l1;
            }
            if(l1.val <= l2.val){
                l1.next = mergeTwoLists(l1.next, l2);
                return  l1;
            } else {
                l2.next = mergeTwoLists(l2.next, l1);
                return  l2;
            }
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{-1,5,3,4,0});
        new Solution().sortList(head).printNodeFromRoot();
    }
}

package node;

/**
 * description 从第一个元素开始，该链表可以被认为已经部分排序。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 * @author 27771
 * create 2020-11-20 09:43
 **/
public class InsertionSortList {

    static class Solution {

        /**
         * 插入排序
         * (执行用时：3 ms, 在所有 Java 提交中击败了98.81%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了78.10%的用户)
         *
         * @param head 头节点
         * @return     排序后头节点
         */
        public ListNode insertionSortList(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode tail = head;
            while (tail.next != null) {
                // 默认 tail 及之后节点已经排好序
                if (tail.next.val >= tail.val) {
                    tail = tail.next;
                    continue;
                }
                // temp 待插入节点
                ListNode temp = tail.next;
                // 修改尾指针
                tail.next = temp.next;
                // 如果待插入节点比头节点还小，则待插入节点为新的头节点
                if (temp.val < head.val) {
                    temp.next = head;
                    head = temp;
                    continue;
                }
                // 如果待插入节点比头节点大，不断找插入位置
                ListNode headTemp = head;
                while (temp.val >= headTemp.next.val) {
                    headTemp = headTemp.next;
                }
                temp.next = headTemp.next;
                headTemp.next = temp;
            }
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{});
        new Solution().insertionSortList(head).printNodeFromRoot();
    }
}

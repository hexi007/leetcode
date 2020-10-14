package node;

/**
 * description 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字
 * @author 27771
 * create 2020-10-14 10:24
 **/
public class AddTwoNumbers {
    static class Solution {
        ListNode ret;
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ret = new ListNode();

            return ret;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{2,4,3});
        ListNode l2 = new ListNode(new int[]{5,6,4});
        Solution solution = new Solution();
        ListNode ret = solution.addTwoNumbers(l1,l2);
        ret.printNodeFromRoot();
    }
}

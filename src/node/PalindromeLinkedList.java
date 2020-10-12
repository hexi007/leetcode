package node;

import java.util.Stack;

public class PalindromeLinkedList {

    static class Solution {

        //又慢又占内存多
        public boolean isPalindrome(ListNode head) {
            Stack<Integer> stack = new Stack<Integer>();
            ListNode temp = head;
            while(temp != null){
                stack.push(temp.val);
                temp = temp.next;
            }
            temp = head;
            while(temp != null){
                int stack_temp = stack.pop();
                if(temp.val != stack_temp)  return false;
                temp = temp.next;
            }
            return true;
        }

        //找到中间节点后反转后续链表
        public boolean isPalindrome1(ListNode head) {
            ListNode mid = getMid(head);
            ListNode subMid = reverse(mid);

            while(subMid != null) {
                if(head.val != subMid.val)  return false;
                head = head.next;
                subMid = subMid.next;
            }
            return true;
        }

        //快慢指针找中间节点（偶数个节点slow指向右中位数）
        private ListNode getMid(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null){
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        //
        private ListNode reverse(ListNode head) {
            //前指针初始为空
            ListNode pre = null;
            //当前指针指向头
            ListNode cur = head;
            while (cur != null){
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            return pre;
        }
    }
    
    public static void main(String[] args) {
        ListNode l = new ListNode(new int[]{1, 2, 2, 1});
        Solution s = new Solution();
        boolean ret = s.isPalindrome1(l);
        System.out.println(ret);
    }
}

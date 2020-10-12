package node;

public class MergeTwoSortedLists {

    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode temp = new ListNode();
            ListNode ret = temp;
            while(l1 != null && l2 != null){
                if(l1.val <= l2.val) {
                    ListNode tempNode = new ListNode(l1.val);
                    temp.next = tempNode;
                    temp = temp.next;
                    l1 = l1.next;
                } else {
                    ListNode tempNode = new ListNode(l2.val);
                    temp.next = tempNode;
                    temp = temp.next;
                    l2 = l2.next;
                }
            }
            while (l1 != null) {
                ListNode tempNode = new ListNode(l1.val);
                temp.next = tempNode;
                temp = temp.next;
                l1 = l1.next;
            }
            while (l2 != null) {
                ListNode tempNode = new ListNode(l2.val);
                temp.next = tempNode;
                temp = temp.next;
                l2 = l2.next;
            }
            return ret.next;
        }

        public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
            ListNode dummyHead  = new ListNode(0);
            ListNode ret = dummyHead;
            while(l1 != null && l2 != null){
                if(l1.val <= l2.val){
                    dummyHead.next = l1;
                    l1 = l1.next;
                } else {
                    dummyHead.next = l2;
                    l2 = l2.next;
                }
                dummyHead = dummyHead.next;
            }
            dummyHead.next = l1 == null ? l2 : l1;
            return ret.next;
        }

        public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
            if(l1 == null)  return l2;
            if(l2 == null)  return l1;
            if(l1.val <= l2.val){
                l1.next = mergeTwoLists2(l1.next, l2);
                return  l1;
            } else {
                l2.next = mergeTwoLists2(l2.next, l1);
                return  l2;
            }
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{1, 2, 4});
        ListNode l2 = new ListNode(new int[]{1, 3, 4});
        Solution s = new Solution();
        ListNode ret = s.mergeTwoLists2(l1,l2);
        ret.printNodeFromRoot();
    }
}

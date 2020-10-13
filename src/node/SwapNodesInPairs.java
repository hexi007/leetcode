package node;

/**
 * description 两两交换链表中的节点
 * @author 27771
 * create 2020-10-13 20:19
 **/
public class SwapNodesInPairs {
    static class Solution {
        /**
         * 设置3个指针，，
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：36.1 MB, 在所有 Java 提交中击败了98.40% 的用户)
         * @param head
         * @return
         */
        public ListNode swapPairs(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }
            //ptrBehind指向两两相邻节点的前一个
            ListNode ptrBehind = head;
            //ptrAfter指向后一个
            ListNode ptrAfter = head.next;
            //head永远指向ptrAfter，因为它会被交换到前面
            head = ptrAfter;
            //temp保存下一个节点，防止断链
            ListNode temp = ptrAfter.next;
            //接下来两行交换两两相邻节点
            ptrAfter.next = ptrBehind;
            ptrBehind.next = temp;
            while(temp != null){
                //链表长为奇数直接break
                if(temp.next == null){
                    break;
                }
                //temp继续保存下一个节点
                temp = temp.next;
                //ptrBehind指向下一组两两相邻节点的前一个
                ptrBehind = ptrBehind.next;
                //防止断链
                ptrAfter.next.next = temp;
                //ptrAfter指向下一组两两相邻节点的后一个
                ptrAfter = ptrBehind.next;
                //temp继续保存下一个节点
                temp = temp.next;
                //接下来两行交换两两相邻节点
                ptrAfter.next = ptrBehind;
                ptrBehind.next = temp;
            }
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(new int[]{1, 2, 3, 4, 5});
        Solution s = new Solution();
        l = s.swapPairs(l);
        l.printNodeFromRoot();
    }
}
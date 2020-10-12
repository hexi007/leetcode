package node;

public class DeleteMiddleNodeIcci {

    static class Solution {
        //运行不可能再快了，但内存消耗大
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    public static void main(String[] args){
        ListNode root = new ListNode(1);
        ListNode temp = root;
        ListNode deleteNode = null;
        for (int i = 2 ; i < 10; i++){
            temp.next = new ListNode(i );
            temp = temp.next;
            if(i == 3) deleteNode = temp;
        }
        Solution s = new Solution();
        System.out.println(deleteNode.val);
        s.deleteNode(deleteNode);
        root.printNodeFromRoot();
    }
}

package node;

/**
 * description 链表节点类
 *
 * @author 27771
 * create 2020-11-13 15:03
 **/
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }

    ListNode (int[] input){
        ListNode temp = this;
        for(int i = 0; i < input.length; i++){
            temp.val = input[i];
            if(i != input.length - 1) {
                temp.next = new ListNode();
                temp = temp.next;
            }
        }
    }

    public void printNodeFromRoot(){
        ListNode temp = this;
        while(temp != null){
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }



    public static void main(String[] args) {
        ListNode root = new ListNode(new int[]{1, 2, 3, 4});
        root.printNodeFromRoot();
    }
}

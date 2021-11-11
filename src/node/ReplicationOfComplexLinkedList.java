package node;

/**
 * description 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-11-11 19:11
 **/
public class ReplicationOfComplexLinkedList {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }

        public static void printNode(Node head) {
            Node cur = head;
            while (cur != null) {
                System.out.print(cur + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    static class Solution {
        public Node copyRandomList(Node head) {
            Node cur = head;
            while (cur != null) {
                Node temp = new Node(cur.val);
                temp.next = cur.next;
                cur.next = temp;
                cur = temp.next;
            }
            cur = head;
            while(cur != null) {
                if(cur.random != null) {
                    cur.next.random = cur.random.next;
                }
                cur = cur.next.next;
            }
            Node tail = new Node(-1), curNode = tail;
            cur = head;
            while (cur != null) {
                curNode.next = cur.next;
                curNode = curNode.next;
                cur.next = cur.next.next;
                cur = cur.next;
            }
            return tail.next;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.next = node2;
        node1.random = node2;
        node2.random = node2;
        Node.printNode(new Solution().copyRandomList(node1));
    }
}

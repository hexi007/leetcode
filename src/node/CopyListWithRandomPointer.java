package node;

/**
 * description 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-30 18:59
 **/
public class CopyListWithRandomPointer {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    static class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            //将拷贝节点放到原节点后面，例如1->2->3这样的链表就变成了这样1->1'->2->2'->3->3'
            for (Node node = head, copy; node != null; node = node.next.next) {
                copy = new Node(node.val);
                copy.next = node.next;
                node.next = copy;
            }
            //把拷贝节点的random指针安排上
            for (Node node = head; node != null; node = node.next.next) {
                if (node.random != null) {
                    node.next.random = node.random.next;
                }
            }
            //分离拷贝节点和原节点，变成1->2->3和1'->2'->3'两个链表，后者就是答案
            Node newHead = head.next;
            for (Node node = head, temp; node.next != null;) {
                temp = node.next;
                node.next = temp.next;
                node = temp;
            }

            return newHead;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.next = node2;
        node1.random = node2;
        Node res = new Solution().copyRandomList(node1);
        System.out.println(res.val + " " + res.next.val);
    }
}

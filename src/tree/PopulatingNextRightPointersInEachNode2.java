package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * description 给定一个二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 *
 * @author 27771
 * create 2020-10-15 09:41
 **/
public class PopulatingNextRightPointersInEachNode2 {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    static class Solution {
        /**
         * @Description  层序遍历即可
         * (执行用时：2 ms, 在所有 Java 提交中击败了56.26%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了98.63%的用户)
         * @param root 树根节点
         * @return  树根节点
         */
        public Node connect(Node root) {
            if(root == null) {
                return null;
            }
            Queue<Node> deque = new LinkedList<>();
            deque.offer(root);
            while (!deque.isEmpty()){
                int curSize = deque.size();
                //tempRight初始为空
                Node tempRight = null;
                while(curSize > 0){
                    Node tempLeft = deque.poll();
                    //注意先进右孩子再进左孩子
                    if(tempRight != null){
                        tempLeft.next = tempRight;
                    }
                    if(tempLeft.right != null){
                        deque.offer(tempLeft.right);
                    }
                    if(tempLeft.left != null){
                        deque.offer(tempLeft.left);
                    }
                    tempRight = tempLeft;
                    curSize--;
                }
            }
            return root;
        }

        /**
         * @Description  递归处理每个节点
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了97.59%的用户)
         * @param root 树根节点
         * @return  树根节点
         */
        public Node connect1(Node root){
            if(root == null){
                return null;
            }
            //左右孩子都有，直接连接
            if(root.left != null && root.right != null){
                root.left.next = root.right;
            }
            //只有左孩子，那么左孩子从root.next找到的连接
            if(root.left != null && root.right == null){
                root.left.next = getNext(root.next);
            }
            //只有右孩子，那么左孩子也从root.next找到的连接
            if(root.right != null){
                root.right.next = getNext(root.next);
            }
            //递归处理左右孩子，注意要先处理右孩子，防止信息断链（部分节点中间next断开）！！！
            connect1(root.right);
            connect1(root.left);
            return root;
        }

        /**
         * @Description 找应该提供连接的节点
         * @param root 1 要处理的节点
         * @return  从该节点找到的可被连接的节点
         */
        private Node getNext(Node root) {
            if(root == null) {
                return null;
            }
            //优先返回左孩子
            if(root.left != null){
                return root.left;
            }
            //实在不行再返回右孩子
            if(root.right != null){
                return root.right;
            }
            //该节点没孩子，那么递归调用自己
            if(root.next != null){
                return getNext(root.next);
            }
            //怎么也找不到只能返回null
            return null;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.right = node5;

        Solution solution = new Solution();
        Node ret = solution.connect(root);
        System.out.println(ret.val);
    }
}

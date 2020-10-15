package tree;

/**
 * description 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 *
 * @author 27771
 * create 2020-10-15 09:06
 **/
public class PopulatingNextRightPointersInEachNode {

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
         * @Description  非常基本的递归，不用递归层序遍历也行
         * (执行用时：1 ms, 在所有 Java 提交中击败了59.84%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了99.39%的用户)
         * @param root 树根节点
         * @return  根节点
         */
        public Node connect(Node root) {
            if(root == null){
                return null;
            }
            if(root.left != null && root.right != null){
                //调用递归程序处理左右孩子的连接
                connectChild(root.left,root.right);
            }
            return root;
        }

        /**
         * @Description  递归处理传入节点
         * @param left 传入的左节点
         * @param right 传入的右节点
         * @return  空
         */
        private void connectChild(Node left, Node right) {
            //将传入节点相连
            left.next = right;
            //left的孩子还需要处理
            if(left.left != null){
                connectChild(left.left,left.right);
            }
            //right的孩子还需呀处理
            if(right.left != null){
                connectChild(right.left, right.right);
            }
            //！还要处理传入左节点的右孩子和传入右节点的左孩子
            if(left.right != null && right.left != null){
                connectChild(left.right, right.left);
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        root.left = left;
        root.right = right;

        Solution solution = new Solution();
        Node ret = solution.connect(root);
        System.out.println(ret.val);
    }
}

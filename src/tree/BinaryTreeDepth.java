package tree;

/**
 * @author 27771
 * @description 二叉树深度
 * @create 2020-10-13 11:20
 **/
public class BinaryTreeDepth {
    static class Solution {
        /**
         * 如果节点为空则深度为0，否则比较左右孩子节点深度，该节点是左右孩子深度再加上1
         * @param root 需要判断深度的节点
         * @return 该节点的深度
         */
        public int maxDepth(TreeNode root) {
            if(root == null){
                return 0;
            }
            return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(20);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(7);
        root.left = treeNode1;
        root.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;

        Solution s = new Solution();
        int ret = s.maxDepth(root);
        System.out.println(ret);
    }
}

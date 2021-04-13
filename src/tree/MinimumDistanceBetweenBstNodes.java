package tree;

/**
 * description 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * @author 27771
 * create 2021-04-13 09:32
 **/
public class MinimumDistanceBetweenBstNodes {

    static class Solution {

        int minDiff = Integer.MAX_VALUE, before = -1;

        /**
         * 二叉搜索树的中序遍历就是有序的
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.1 MB, 在所有 Java 提交中击败了39.65%的用户)
         *
         * @param root 二叉搜索树的根节点
         * @return     任意两不同节点值之间的最小差值
         */
        public int minDiffInBst(TreeNode root) {
            inorderTraversal(root);
            return minDiff;
        }

        /**
         * 中序遍历, 用 before 存前一个节点值
         *
         * @param root 当前根节点
         */
        private void inorderTraversal(TreeNode root) {
            if (root == null) {
                return;
            }
            inorderTraversal(root.left);
            if (before != -1) {
                minDiff = Math.min(minDiff, Math.abs(root.val - before));
            }
            before = root.val;
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(4);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(6);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(3);
        root.left = treeNode1;
        root.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;

        System.out.println(new Solution().minDiffInBst(root));
    }
}

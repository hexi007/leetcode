package tree;

/**
 * description 给定一个二叉树，请计算节点值之和最大的路径的节点值之和是多少。 这个路径的开始节点和结束节点可以是二叉树中的任意节点
 *
 * @author 27771
 * create 2021-09-16 16:01
 **/
public class MaximumPathSumOfBinaryTree {

    static class Solution {

        private int maxSum;

        public int maxPathSum (TreeNode root) {
            maxSum = Integer.MIN_VALUE;
            handle(root);
            return maxSum;
        }

        /**
         * 递归从左右找最大路径和
         *
         * @param node 节点
         * @return 当前节点和左右最大路径和之和
         */
        private int handle(TreeNode node) {
            if (node == null) {
                return 0;
            }
            // 都至少要比 0 大才有意义
            int left = Math.max(0, handle(node.left));
            int right = Math.max(0, handle(node.right));
            // 更新 maxSum
            maxSum = Math.max(maxSum, left + node.val + right);
            // 只能返回左右子树中较大值加上root.val, 表示一条路径
            return Math.max(0, node.val + Math.max(left, right));
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2);
        root.right = new TreeNode(-3);
        Solution solution = new Solution();
        System.out.println(solution.maxPathSum(root));
    }
}
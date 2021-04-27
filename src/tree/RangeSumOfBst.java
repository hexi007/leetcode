package tree;

/**
 * description 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 * @author 27771
 * create 2021-04-27 10:15
 **/
public class RangeSumOfBst {

    static class Solution {

        int sum = 0;

        /**
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：46.4 MB, 在所有 Java 提交中击败了71.32%的用户)
         *
         * @param root 叉搜索树的根结点
         * @param low  范围左边界
         * @param high 范围右边界
         * @return     位于范围 [low, high] 之间的所有结点的值的和
         */
        public int rangeSumBst(TreeNode root, int low, int high) {
            dfs(root, low, high);
            return sum;
        }

        /**
         * 二叉搜索树的遍历
         *
         * @param node 二叉搜索树的当前结点
         * @param low  范围左边界
         * @param high 范围右边界
         */
        private void dfs(TreeNode node, int low, int high) {
            if (node == null){
                return;
            }
            if (node.val > high) {
                dfs(node.left, low, high);
            } else if (node.val < low) {
                dfs(node.right, low, high);
            } else {
                sum += node.val;
                dfs(node.left, low, high);
                dfs(node.right, low, high);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.mkTree("[10,5,15,3,7,null,18]");
        int low = 7, high = 15;
        System.out.println(new Solution().rangeSumBst(root, low, high));
    }
}

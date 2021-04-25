package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * description  给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * @author 27771
 * create 2021-04-25 18:54
 **/
public class IncreasingOrderSearchTree {

    static class Solution {

        List<TreeNode> list = new ArrayList<>();

        /**
         * 二叉搜索树的中序遍历即是有序数组
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.2 MB, 在所有 Java 提交中击败了16.50%的用户)
         *
         * @param root 二叉搜索树根节点
         * @return     递增顺序搜索树
         */
        public TreeNode increasingBinarySearchTree(TreeNode root) {
            dfs(root);
            TreeNode head = new TreeNode(0), temp = head;
            for (TreeNode node : list) {
                temp.right = node;
                node.left = null;
                temp = node;
            }
            return head.right;
        }

        /**
         * 中序遍历
         *
         * @param node 当前树节点
         */
        private void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            dfs(node.left);
            list.add(node);
            dfs(node.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.mkTree("[5,3,6,2,4,null,8]");
        TreeNode res = new  Solution().increasingBinarySearchTree(root);
        System.out.println(new BinaryTreePreorderTraversal.Solution()
                .preorderTraversal3(res));
    }
}

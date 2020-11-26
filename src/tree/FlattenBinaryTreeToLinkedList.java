package tree;

import java.util.List;

/**
 * description 给定一个二叉树，原地将它展开为一个单链表。
 *
 * @author 27771
 * create 2020-11-25 11:13
 **/
public class FlattenBinaryTreeToLinkedList {

    static class Solution {

        /**
         * 递归，先展开左孩子链表，再拼接右孩子链表
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了60.14%的用户)
         *
         * @param root 根节点
         */
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            // 展开左孩子链表,之后可以将左孩子看成已展开的链表
            flatten(root.left);
            // 保存右孩子节点
            TreeNode right = root.right;
            // 左孩子已到右孩子
            root.right = root.left;
            // 左孩子置为空
            root.left = null;
            // 不断寻找 右孩子节点插入位置
            while (root.right != null) {
                root = root.right;
            }
            // 插入右孩子
            root.right = right;
            // 再展开右孩子链表
            flatten(root.right);
        }
    }

    public static void main(String[] args) {
        String str = "[1,2,5,3,4,null,6]";
        TreeNode root = TreeNode.mkTree(str);
        new Solution().flatten(root);
        BinaryTreePreorderTraversal.Solution s = new
                BinaryTreePreorderTraversal.Solution();
        List<Integer> ret = s.preorderTraversal3(root);
        s.printList(ret);
    }
}

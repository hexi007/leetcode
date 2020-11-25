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
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                return;
            }
            if (root.left == null) {
                return;
            }
            myFlatten(root.right);
        }

        private TreeNode myFlatten(TreeNode root) {
            return root;
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

package tree;

import java.util.Arrays;
import java.util.List;

/**
 * description 根据一棵树的前序遍历与中序遍历构造二叉树。  注意: 你可以假设树中没有重复的元素。
 *
 * @author 27771
 * create 2021-03-31 15:15
 **/
public class TreeFromPreorderAndInorder {

    static class Solution {

        /**
         * 暴力拷贝数组
         *
         * @param preorder 前序遍历
         * @param inorder  中序遍历
         * @return         树根节点
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }

            int len = preorder.length;
            TreeNode root = new TreeNode(preorder[0]);
            for (int i = 0; i < len; i++) {
                if (preorder[0] == inorder[i]) {
                    root.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1),
                            Arrays.copyOfRange(inorder, 0, i));
                    root.right = buildTree(Arrays.copyOfRange(preorder, i + 1, len),
                            Arrays.copyOfRange(inorder, i + 1, len));
                    break;
                }
            }
            return  root;
        }

        /**
         * 不拷贝数组，改用左右区间表示左右区间
         * (执行用时：4 ms, 在所有 Java 提交中击败了50.05%的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了29.24%的用户)
         *
         * @param preorder 前序遍历
         * @param inorder  中序遍历
         * @return         树根节点
         */
        public TreeNode buildTree1(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            return myBuildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
        }

        private TreeNode myBuildTree(int[] preorder, int left, int right, int[] inorder, int left1, int right1) {
            if (left == right) {
                return null;
            }

            TreeNode root = new TreeNode(preorder[left]);
            for (int i = left1; i <= right1; i++) {
                if (preorder[left] == inorder[i]) {
                    int leftNum = i - left1;

                    root.left = myBuildTree(preorder, left + 1, left + leftNum + 1,
                            inorder,  left1, i);
                    root.right = myBuildTree(preorder, left + leftNum + 1, right, inorder,  i + 1, right1);
                    break;
                }
            }
            return root;
        }
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = new Solution().buildTree(preorder, inorder);
        List<Integer> res = new BinaryTreePostorderTraversal.Solution().postorderTraversal1(root);
        System.out.println(res);

        TreeNode root1 = new Solution().buildTree1(preorder, inorder);
        res = new BinaryTreePostorderTraversal.Solution().postorderTraversal1(root1);
        System.out.println(res);
    }
}
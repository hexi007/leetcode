package tree;

import java.util.List;

/**
 * @author 27771
 */
public class MaximumBinaryTree {

    static class Solution {
        //运行快，内存消耗也低，普遍都是这思路
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            TreeNode root = myTree(nums, 0,  nums.length - 1);
            return root;
        }

        //递归调用生成最大树
        public TreeNode myTree(int[] nums, int left, int right){
            if(left > right){
                return null;
            }
            if(left == right){
                TreeNode root = new TreeNode(nums[left]);
                return root;
            }
            int index = left;
            int max = nums[left];
            for(int i = left; i <= right; i++){
                if(nums[i] > max){
                    max = nums[i];
                    index = i;
                }
            }
            TreeNode root = new TreeNode(max);
            root.left = myTree(nums, left, index - 1);
            root.right = myTree(nums, index  + 1, right);
            return root;
        }
    }

    public static void main(String[] args) {
        int[] input = {3,2,1,6,0,5};
        Solution s = new Solution();
        TreeNode root = s.constructMaximumBinaryTree(input);
        BinaryTreePreorderTraversal.Solution s1 = new BinaryTreePreorderTraversal.Solution();
        List<Integer> ret = s1.preorderTraversal(root);
        s1.printList(ret);
    }
}

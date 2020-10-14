package tree;

/**
 * description 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * @author 27771
 * create 2020-10-14 19:11
 **/
public class PathSum {
    static class Solution {
        /**
         * Description  递归左右子树找路径和等于目标和（递归思想类似对称树）
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了98.03% 的用户)
         * @param root 树根节点
         * @param sum 目标和
         * return  存在路径返回true，否指false
         */
        public boolean hasPathSum(TreeNode root, int sum) {
            if(root == null){
                return false;
            }
            if(root.left == null && root.right == null && root.val == sum){
                return true;
            }
            return realHasPathSum(root.left, sum, root.val) ||
                    realHasPathSum(root.right, sum, root.val);
        }

        /**
         * Description  递归左右子树
         * @param root 当前节点
         * @param sum 目标和
         * @param currentSum 到该结点之前路径和
         * return  是否存在这样的路径
         */
        public boolean realHasPathSum(TreeNode root, int sum, int currentSum){
            if(root == null){
                return false;
            }
            if(root.left == null && root.right == null){
                return root.val + currentSum == sum;
            }
            return realHasPathSum(root.left, sum, root.val + currentSum) ||
                    realHasPathSum(root.right, sum, root.val + currentSum);
        }

        /**
         * Description  上面方法想复杂了，直接递归自身即可
         * @param root 树根节点
         * @param sum 目标和
         * return  是否存在这样的路径
         */
        public boolean hasPathSum1(TreeNode root, int sum){
            if(root == null){
                return false;
            }
            if(root.left == null && root.right == null){
                return root.val == sum;
            }
            return hasPathSum1(root.left, sum - root.val) ||
                    hasPathSum1(root.right, sum - root.val);
        }

    }

    public static void main(String[] args) {
        String str = "[1,2,2,3,4,5,6]";
        TreeNode root = TreeNode.mkTree(str);
        Solution solution = new Solution();
        System.out.println(solution.hasPathSum(root, 5));
    }
}
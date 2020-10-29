package tree;

/**
 * description 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。  计算从根到叶子节点生成的所有数字之和。
 *
 * @author 27771
 * create 2020-10-29 10:07
 **/
public class SumRootToLeafNumbers {
    static class Solution {
        int res = 0;
        /**
         非常标准的递归
         (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         (内存消耗：36.1 MB, 在所有 Java 提交中击败了92.99%的用户)
         * @param root 根节点
         * @return  从根到叶子节点生成的所有数字之和
         */
        public int sumNumbers(TreeNode root) {
            if(root == null){
                return 0;
            }
            if (root.left == null && root.right == null){
                return root.val;
            }
            if(root.left != null){
                getNumber(root.left, root.val);
            }
            if(root.right != null){
                getNumber(root.right, root.val);
            }
            return res;
        }

        /**
         计算到当前节点的数字
         * @param node 当前节点
         * @param val 到上层节点的数字
         */
        private void getNumber(TreeNode node, int val) {
            int temp = val * 10 + node.val;
            if(node.left == null && node.right == null){
                res += temp;
            }
            if(node.left != null){
                getNumber(node.left, temp);
            }
            if(node.right != null){
                getNumber(node.right, temp);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        System.out.println(new Solution().sumNumbers(root));

    }
}

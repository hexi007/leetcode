package tree;

/**
 * description 给你一棵二叉树，每个节点的值为 1 到 9 。
 * 我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
 * 请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
 *
 * @author 27771
 * create 2020-11-16 14:19
 **/
public class PseudoPalindromicPathsInBinaryTree {

    static class Solution {

        int res = 0;
        int[] count = new int[10];
        int odd = 0;

        /**
         * 深度遍历，count 保存每个数出现次数，叶子节点时通过出现次数为奇数的的个数判断是否存在伪回文
         * (执行用时：5 ms, 在所有 Java 提交中击败了68.70% 的用户)
         * (内存消耗：55.9 MB, 在所有 Java 提交中击败了94.06% 的用户)
         * @param root 当前节点
         * @return     从根到叶子节点的所有路径中伪回文路径的数目
         */
        public int pseudoPalindromicPaths (TreeNode root) {
            // count 可以考虑一个数字表示的位数组
            count[root.val]++;
            // 判断如何改动到当前节点出现奇数的的个数
            if ((count[root.val] & 1) == 1) {
                odd++;
            } else {
                odd--;
            }
            if (root.left == null && root.right == null) {
                if (odd <= 1) {
                    res++;
                }
            }
            if (root.left != null) {
                pseudoPalindromicPaths(root.left);
            }
            if (root.right != null) {
                pseudoPalindromicPaths(root.right);
            }
            // 记得回溯时清除 count ， odd 操作
            if ((count[root.val] & 1) == 1) {
                odd--;
            } else {
                odd++;
            }
            count[root.val]--;
            return res;
        }

    }

    public static void main(String[] args) {
        String str = "[2,1,1,1,3,null,null,null,null,null,1]";
        TreeNode root = TreeNode.mkTree(str);
        System.out.println(new Solution().pseudoPalindromicPaths(root));
    }
}
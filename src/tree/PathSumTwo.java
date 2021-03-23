package tree;

import java.util.*;

/**
 * description 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/path-sum-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-21 20:17
 **/
public class PathSumTwo {

    static class Solution {

        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new ArrayDeque<>();

        /**
         * 深搜树的所有路径
         * (执行用时：2 ms, 在所有 Java 提交中击败了39.46%的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了45.35%的用户)
         *
         * @param root      根节点
         * @param targetSum 路径整数目标和
         * @return          所有从根节点到叶子节点路径总和等于给定目标和的路径
         */
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return res;
            }

            dfs(root, 0, targetSum);
            return res;
        }

        /**
         * 深搜，在叶子节点处停止
         *
         * @param root      根节点
         * @param curSum    当前路径和
         * @param targetSum 路径整数目标和
         */
        private void dfs(TreeNode root, int curSum, int targetSum) {
            if (root == null) {
                return;
            }
            path.addLast(root.val);
            curSum += root.val;

            if (root.left == null && root.right == null && curSum == targetSum) {
                res.add(new LinkedList<>(path));
            }

            dfs(root.left, curSum, targetSum);
            dfs(root.right, curSum, targetSum);

            path.removeLast();

        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        for (List<Integer> list : new Solution().pathSum(root, root.val + left.val)) {
            System.out.println(list);
        }
    }
}
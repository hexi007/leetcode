package dynamicprogramming;

import tree.TreeNode;

/**
 * description 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。  计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/house-robber-iii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-15 19:06
 **/
public class HouseRobberThree {

    static class Solution {

        /**
         * 获得根节点打劫情况
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了29.90%的用户)
         *
         * @param root 入口
         * @return     能够盗取的最高金额
         */
        public int rob(TreeNode root) {
            int[] res = dfs(root);
            return Math.max(res[0], res[1]);
        }

        /**
         * 对每个节点，要么不抢这个节点则从左右节点获取最高金额之和
         * 要么打劫这个节点，那么左右节点就不能再抢
         *
         * @param node 当前节点
         * @return     第 一/二 个元素表示 不抢/抢 这个节点能够盗取的最高金额，
         */
        private int[] dfs(TreeNode node) {
            int[] res = new int[2];
            if (node == null) {
                return res;
            }
            int[] left = dfs(node.left), right = dfs(node.right);
            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            res[1] = node.val + left[0] + right[0];
            return res;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.mkTree("[3,2,3,null,3,null,1]");
        System.out.println(new Solution().rob(root));
    }
}

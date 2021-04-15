package dynamicprogramming;

/**
 * description 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/house-robber-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-15 18:14
 **/
public class HouseRobberTwo {

    static class Solution {

        /**
         * 考首尾不可得兼，分两种情况讨论
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35.8 MB, 在所有 Java 提交中击败了63.14%的用户)
         *
         * @param nums 每间房内的现金
         * @return     能够偷窃到的最高金额
         */
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 1 + 1) {
                return Math.max(nums[0], nums[1]);
            }
            if (nums.length == 1 + 1 + 1) {
                return Math.max(Math.max(nums[0], nums[1]), nums[2]);
            }

            int len = nums.length - 1;
            return Math.max(robHelper(nums, 0, len - 1), robHelper(nums, 1, len));
        }

        /**
         * 指定区间内能够偷窃到的最高金额
         *
         * @param nums  每间房内的现金
         * @param start 区间开始位置
         * @param end   区间结束位置
         * @return      能够偷窃到的最高金额
         */
        private int robHelper(int[] nums, int start, int end) {
            int dp1 = Math.max(nums[start], nums[start + 1]), dp2 = nums[start], dp = 0;
            for (int i = start + 1 + 1; i < end; i++) {
                dp = Math.max(dp1, dp2 + nums[i]);
                dp2 = dp1;
                dp1 = dp;
            }
            return dp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        System.out.println(new Solution().rob(nums));
    }
}

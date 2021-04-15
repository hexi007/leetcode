package dynamicprogramming;

/**
 * description 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/house-robber 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-15 12:45
 **/
public class HouseRobber {

    static class Solution {

        /**
         * dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.1 MB, 在所有 Java 提交中击败了5.03%的用户)
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

            int dp1 = Math.max(nums[0], nums[1]), dp2 = nums[0], dp = 0;
            for (int i = 2, len = nums.length; i < len; i++) {
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

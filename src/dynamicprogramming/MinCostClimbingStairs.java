package dynamicprogramming;

/**
 * description 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2020-12-21 18:20
 **/
public class MinCostClimbingStairs {

    static class Solution {

        /**
         * 基本动态规划
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.68%的用户)
         * (内存消耗：38.1 MB, 在所有 Java 提交中击败了80.84%的用户)
         *
         * @param cost 非负数的体力花费值
         * @return     到达到楼层顶部的最低花费
         */
        public int minCostClimbingStairs(int[] cost) {
            int len = cost.length;
            int[] dp = new int[len];
            dp[0] = cost[0];
            dp[1] = cost[1];
            for (int i = 2; i < len; i++) {
                dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
            }
            return Math.min(dp[len -2], dp[len - 1]);
        }

        /**
         * 这个动态规划也可以不用数组
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.68%的用户)
         * (内存消耗：38 MB, 在所有 Java 提交中击败了87.65%的用户)
         *
         * @param cost 非负数的体力花费值
         * @return     到达到楼层顶部的最低花费
         */
        public int minCostClimbingStairs1(int[] cost) {
            int first = cost[0], second = cost[1];
            for (int i = 2; i < cost.length; i++) {
                int temp = Math.min(first + cost[i], second + cost[i]);
                first = second;
                second = temp;
            }
            return Math.min(first, second);
        }
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println(new Solution().minCostClimbingStairs(cost));
        System.out.println(new Solution().minCostClimbingStairs1(cost));
    }
}

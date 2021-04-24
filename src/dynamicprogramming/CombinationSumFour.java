package dynamicprogramming;

import java.util.Arrays;

/**
 * description 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。  题目数据保证答案符合 32 位整数范围。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/combination-sum-iv 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-24 10:19
 **/
public class CombinationSumFour {

    static class Solution {

        /**
         * 动态规划
         * (执行用时：1 ms, 在所有 Java 提交中击败了98.30%的用户)
         * (内存消耗：35.9 MB, 在所有 Java 提交中击败了25.45%的用户)
         *
         * @param nums   数组
         * @param target 目标整数
         * @return       总和为 target 的元素组合的个数
         */
        public int combinationSum4(int[] nums, int target) {
            Arrays.sort(nums);
            // dp[i] 表示目标是 i 时的素组合个数
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int i = 1; i <= target; i++) {
                // 对每个 i 遍历 nums
                for (int num : nums) {
                    // i > num 时
                    if (i >= num) {
                        // 更新之后的 dpi 由原来的 dpi 与 dp[i - num] 组成
                        dp[i] += dp[i - num];
                    }
                }
            }
            return dp[target];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        int target = 3;
        System.out.println(new Solution().combinationSum4(nums, target));
    }
}

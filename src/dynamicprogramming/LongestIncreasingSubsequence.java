package dynamicprogramming;

import java.util.Arrays;

/**
 * description 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-04 10:36
 **/
public class LongestIncreasingSubsequence {

    static class Solution {

        /**
         * 动态规划
         * (执行用时：76 ms, 在所有 Java 提交中击败了32.39%的用户)
         * (内存消耗：37.9 MB, 在所有 Java 提交中击败了91.27%的用户)
         * @param nums 整数数组
         * @return     最长严格递增子序列的长度
         */
        public int lengthOfLIS(int[] nums) {
            int len = nums.length;
            // dp[i] 表示以当前数结尾的最长递增子序列长度
            int[] dp = new int[len];
            Arrays.fill(dp, 1);

            int res = 1;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    // 每个数和之前比较
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(new Solution().lengthOfLIS(nums));
    }
}

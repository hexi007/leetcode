package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * description 给你一个整数数组 arr 和一个整数 difference，
 * 请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-11-05 11:34
 **/
public class LongestArithmeticSubsequenceOfGivenDifference {

    static class Solution {
        public int longestSubsequence(int[] arr, int difference) {
            if (arr == null || arr.length <= 0) {
                return -1;
            }
            int len = arr.length;
            // dp[i] : arr[i] ~ arr[len - 1] 的最长等差子序列的长度
            int[] dp = new int[len];
            // k : 当前数                v : 下一个与当前数相差 difference 的数的位置
            Map<Integer, Integer> map = new HashMap<>(16);
            int ans = 1;
            for (int i = len - 1; i >= 0; i--) {
                dp[i] = dp[map.getOrDefault(arr[i], 0)] + 1;
                ans = Math.max(ans, dp[i]);
                map.put(arr[i] - difference, i);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] arr = {7,7,7,7,7,7,7};
        int difference = 0;
        System.out.println(new Solution().longestSubsequence(arr, difference));
    }
}

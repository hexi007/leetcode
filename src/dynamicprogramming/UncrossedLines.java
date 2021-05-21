package dynamicprogramming;

/**
 * description 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 * nums1[i] == nums2[j] 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/uncrossed-lines 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-21 09:44
 **/
public class UncrossedLines {

    static class Solution {

        /**
         * 类似最长公共子序列
         * (执行用时：9 ms, 在所有 Java 提交中击败了6.11%的用户)
         * (内存消耗：38.1 MB, 在所有 Java 提交中击败了26.88%的用户)
         *
         * @param nums1 数组 1
         * @param nums2 数组 2
         * @return      可以绘制的最大连线数
         */
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            int len1 = nums1.length, len2 = nums2.length;
            int[][] dp = new int[len1 + 1][len2 + 2];
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                    }
                }
            }
            return dp[len1][len2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 5, 1, 2, 5};
        int[] nums2 = {10, 5, 2, 1, 5, 2};
        System.out.println(new Solution().maxUncrossedLines(nums1, nums2));
    }
}

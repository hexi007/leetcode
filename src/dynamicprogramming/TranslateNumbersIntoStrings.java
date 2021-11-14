package dynamicprogramming;

import java.util.Arrays;

/**
 * description 有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
 * 现在给一串数字，返回有多少种可能的译码结果  数据范围：字符串长度满足 0 < n \le 900<n≤90
 * 进阶：空间复杂度 O(n)，时间复杂度 O(n)
 *
 * @author 27771
 * create 2021-11-14 21:17
 **/
public class TranslateNumbersIntoStrings {

    static class Solution {
        /**
         * 解码
         * @param nums string字符串 数字串
         * @return int整型
         */
        public int solve (String nums) {
            if (nums == null || "".equals(nums)) {
                return 0;
            }
            char[] cs = nums.toCharArray();
            int len = cs.length;
            int[] dp = new int[len + 2];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i < len + 2; i++) {
                if (cs[i - 2] == '0') {
                    // 有 0 时要特别判断是否合法
                    if (i - 3 >= 0 && (cs[i - 3] <= '0' || cs[i - 3] >= '3')) {
                        return 0;
                    }
                    dp[i] = dp[i - 2];
                } else if (i == 2) {
                    dp[i] = 1;
                    // 当前字符单独编码，就和 dp[i - 1] 一样
                } else {
                    dp[i] = dp[i - 1];
                    // 当前字符单独编码，就和 dp[i - 1] 一样
                    // 可以和前一个字符进行比较
                    if (cs[i - 3] == '1' || (cs[i - 3] == '2' && cs[i - 2] <= '6')) {
                        // （前一个字符为 1 或 （2 且 后一个字符字符小于等于 6） 时
                        dp[i] = dp[i - 2] + dp[i - 1];
                        // 就为两种方案之和
                    }
                }
            }
            return dp[len + 1];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solve("7256224241262422162912798142114"));
    }
}

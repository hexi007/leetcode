package dynamicprogramming;

import java.util.Arrays;

/**
 * description  给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：
 * 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列
 *
 * @author 27771
 * create 2021-04-03 19:03
 **/
public class LongestCommonSubsequence {

    static class Solution {
        /**
         * 经典动态规划
         * (执行用时：7 ms, 在所有 Java 提交中击败了93.80%的用户)
         * (内存消耗：42.1 MB, 在所有 Java 提交中击败了75.30%的用户)
         *
         * @param text1 字符串 1
         * @param text2 字符串 2
         * @return      两个字符串的最长公共子序列的长度
         */
        public int longestCommonSubsequence(String text1, String text2) {
            char[] s1 = text1.toCharArray(), s2 = text2.toCharArray();
            int len1 = s1.length, len2 = s2.length;
            int[][] dp = new int[len1 + 1][len2 + 1];

            for (int i = 1; i < len1 + 1; i++) {
                for (int j = 1; j < len2 + 1; j++) {
                    // 根据末尾字符是否相同决定 dp
                    if (s1[i - 1] == s2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            return dp[len1][len2];
        }

        public String getLcsString(String text1, String text2) {
            char[] cs1 = text1.toCharArray(), cs2 = text2.toCharArray();
            int len1 = cs1.length, len2 = cs2.length;
            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (cs1[i - 1] == cs2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int[] a : dp) {
                System.out.println(Arrays.toString(a));
            }
            int i = len1, j = len2;
            while (i > 0 && j > 0) {
                System.out.println(i + " " + j);
                if (cs1[i - 1] == cs2[j - 1]) {
                    sb.append(cs1[i - 1]);
                    i--;
                    j--;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
            return sb.reverse().toString();
        }
    }

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        System.out.println(new Solution().longestCommonSubsequence(text1, text2));
        System.out.println(new Solution().getLcsString(text1, text2));
    }
}
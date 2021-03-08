package dynamicprogramming;

/**
 * description  给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。  返回符合要求的 最少分割次数 。
 *
 * @author 27771
 * create 2021-03-08 19:45
 **/
public class PalindromePartitioningTwo {

    static class Solution {

        int len;
        // dp 判断 dp[i][j] 即从 i 开始到 j 结束的字串是否是回文串的动态规划数组
        boolean[][] dp;
        char[] chars;

        /**
         * 暴力搜会超时，用动态规划预处理
         * (执行用时：14 ms, 在所有 Java 提交中击败了62.92%的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了49.17%的用户)
         *
         * @param s 字符串
         * @return  要求的最少分割次数
         */
        public int minCut(String s) {
            len = s.length();
            if (len == 0) {
                return 0;
            }

            chars = s.toCharArray();
            dp = new boolean[len][len];
            for (int right = 0; right < len; right++) {
                for (int left = 0; left <= right; left++) {
                    // 判断 dp[left][right] 是否为 true 需要两个条件
                    // 1. chars[left] 和 chars[right] 要相等
                    // 2. 要么 right 和 left 直接长度小于等于二（就不需要考虑两者之间是否回文）
                    //    要么从 left + 1 开始到 right - 1 之间也是回文
                    boolean isPalindrome = chars[left] == chars[right]
                            && (right - left <= 2 || dp[left + 1][right - 1]);
                    if (isPalindrome) {
                        dp[left][right] = true;
                    }
                }
            }

            // count count[i] 表示从 0 到 i 之间最小分割次数
            int[] count = new int[len];
            for (int i = 1; i < len; i++) {
                if (dp[0][i]) {
                    // dp[0][i] == true 表示从 0 到 i 就是一个回文子串
                    count[i] = 0;
                } else {
                    // 先让 count[i] 为 count[i - 1] + 1，因为总可以先分从 0 到 i - 1，s[i] 单独作为回文子串
                    count[i] = count[i - 1] + 1;
                    // j = 1 开始找分割回文子串的点
                    for (int j = 1; j < i; j++) {
                        if (dp[j][i]) {
                            // 从 j 到 i 构成了回文子串，那么新的方案是从 j 分割从 0 到 i 的子串
                            // 这种分割方案产生的分割数就是 count[j - 1](j 之前分割数) + 1
                            // 再和自身(count[i])作比较
                            count[i] = Math.min(count[i], count[j - 1] + 1);
                        }
                    }
                }
            }

            return count[len - 1];
        }
    }

    public static void main(String[] args) {
        String s = "a";
        System.out.println(new Solution().minCut(s));
    }
}
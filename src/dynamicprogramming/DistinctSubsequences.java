package dynamicprogramming;

/**
 * description 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）  题目数据保证答案符合 32 位带符号整数范围。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/distinct-subsequences 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-17 09:31
 **/
public class DistinctSubsequences {

    static class Solution {

        /**
         * 只在两子字符串末尾相同时计算一次结果
         * (执行用时：2 ms, 在所有 Java 提交中击败了97.64%的用户)
         * (内存消耗：37.1 MB, 在所有 Java 提交中击败了8.69%的用户)
         *
         * @param s 字符串
         * @param t 字符串
         * @return  在 s 的子序列中 t 出现的个数
         */
        public int numDistinct(String s, String t) {

            char[] charsS = s.toCharArray();
            char[] charsT = t.toCharArray();
            int lenS = charsS.length, lenT = charsT.length;
            // 在 s 和 t 之前加一个空格
            int[][] dp = new int[lenT + 1][lenS + 1];

            for (int i = 0; i < lenS + 1; i++) {
                dp[0][i] = 1;
            }
            for (int i = 1; i < lenT + 1; i++) {
                dp[i][0] = 0;
            }

            for (int i = 1; i < lenT + 1; i++) {
                for (int j = 1; j < lenS + 1; j++) {
                    if (charsT[i - 1] == charsS[j - 1]) {
                        // 末尾字符相同，则当前出现次数为不考虑这个末尾字符（dp[i][j - 1]）和
                        // 考虑末尾字符，那么这两子串都去掉末尾字符的次数（dp[i - 1][j - 1]）之和
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                    } else {
                        // 末尾字符不同，当前出现次数和不考虑这个末尾字符（dp[i][j - 1]）是一样的
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }

            return dp[lenT][lenS];

        }
    }

    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        System.out.println(new Solution().numDistinct(s, t));
    }
}
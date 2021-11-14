package dynamicprogramming;

/**
 * description 给定两个字符串str1和str2,输出两个字符串的最长公共子串
 *
 * @author 27771
 * create 2021-11-14 19:11
 **/
public class LongestCommonSubString {

    static class Solution {
        /**
         * longest common substring
         * @param str1 string字符串 the string
         * @param str2 string字符串 the string
         * @return string字符串
         */
        public String lcs (String str1, String str2) {
            int len1 = str1.length(), len2 = str2.length();
            char[] cs1 = str1.toCharArray(), cs2 = str2.toCharArray();
            int[][] dp = new int[len1 + 1][len2 + 1];
            int indexI = -1, indexJ = -1, maxLen = 0;
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (cs1[i - 1] == cs2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        if (dp[i][j] > maxLen) {
                            indexI = i;
                            indexJ = j;
                            maxLen = dp[i][j];
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            while (indexI > 0 && indexJ > 0 && cs1[indexI - 1] == cs2[indexJ - 1]) {
                sb.append(cs1[indexI - 1]);
                indexI--;
                indexJ--;
            }
            return sb.reverse().toString();
        }
    }

    public static void main(String[] args) {
        String str1 = "1AB2345CD", str2 = "12345EF";
        System.out.println(new Solution().lcs(str1, str2));
    }
}

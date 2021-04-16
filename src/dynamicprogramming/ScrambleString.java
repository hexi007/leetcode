package dynamicprogramming;

/**
 * description 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止 如果字符串的长度 > 1 ，执行下述步骤： 在一个随机下标处将字符串分割成两个非空的子字符串。
 * 即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。
 * 即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * 如果是，返回 true ；否则，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/scramble-string 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-16 21:12
 **/
public class ScrambleString {

    static class Solution {

        /**
         * 3 维动态规划
         * (执行用时：9 ms, 在所有 Java 提交中击败了36.88%的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了89.48%的用户)
         *
         * @param s1 字符串 1
         * @param s2 字符串 2
         * @return   s2 是否是 s1 的扰乱字符串
         */
        public boolean isScramble(String s1, String s2) {
            char[] chars1 = s1.toCharArray(), chars2 = s2.toCharArray();
            int len = s1.length(), len1 = s2.length();
            if (len != len1) {
                return false;
            }

            // dp[i][j][len] 表示从字符串 SS 中 ii 开始长度为 len 的字符串
            // 是否能变换为从字符串 TT 中 jj 开始长度为 len 的字符串

            boolean[][][] dp = new boolean[len][len][len + 1];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    dp[i][j][1] = (chars1[i] == chars2[j]);
                }
            }

            // 枚举区间长度 2～n
            for (int l = 2; l <= len; l++) {
                // 枚举 s1 中的起点位置
                for (int i = 0; i <= len - l; i++) {
                    // 枚举 s2 中的起点位置
                    for (int j = 0; j <= len - l; j++) {
                        // 枚举划分位置
                        for (int k = 1; k <= l - 1; k++) {
                            if (dp[i][j][k] && dp[i + k][j + k][l - k]) {
                                dp[i][j][l] = true;
                                break;
                            }
                            if (dp[i][j + l - k][k] && dp[i + k][j][l - k]) {
                                dp[i][j][l] = true;
                                break;
                            }
                        }
                    }
                }
            }

            return dp[0][0][len];
        }
    }

    public static void main(String[] args) {
        String s1 = "great", s2 = "rgeat";
        System.out.println(new Solution().isScramble(s1, s2));
    }
}

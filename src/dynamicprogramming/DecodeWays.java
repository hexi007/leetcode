package dynamicprogramming;

/**
 * description 一条包含字母 A-Z 的消息通过以下映射进行了
 * 编码 ：  'A' -> 1 'B' -> 2 ... 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
 * 例如，"11106" 可以映射为：  "AAJF" ，将消息分组为 (1 1 10 6) "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/decode-ways 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-21 12:13
 **/
public class DecodeWays {

    static class Solution {

        /**
         * 对于字符串 s 的任意位置 i 而言，其存在三种情况：
         *
         * 只能由位置 i 的单独作为一个 item，设为 a，转移的前提是 a 的数值范围为 [1,9]，
         * 转移逻辑为 f[i] = f[i - 1]f[i]=f[i−1]。
         *
         * 只能由位置 i 的与前一位置（i-1）共同作为一个 item，设为 b，转移的前提是 b 的数值范围为 [10,26]，
         * 转移逻辑为 f[i] = f[i - 2]f[i]=f[i−2]。
         *
         * 位置 i 既能作为独立 item 也能与上一位置形成 item，转移逻辑为 f[i] = f[i - 1] + f[i - 2]。
         *
         * (执行用时：2 ms, 在所有 Java 提交中击败了27.65%的用户)
         * (内存消耗：36.9 MB, 在所有 Java 提交中击败了18.02%的用户)
         *
         * @param s 待解码字符串
         * @return  解码后字符串
         */
        public int numsDecoding(String s) {
            int len = s.length();
            s = " " + s;
            char[] chars = s.toCharArray();
            int[] dp = new int[len + 1];
            dp[0] = 1;

            for (int i = 1; i <= len; i++) {
                int a = chars[i] - '0', b = (chars[i - 1] - '0') * 10 + a;
                if (a >= 1 && a <= 9) {
                    dp[i] = dp[i - 1];
                }
                if (b >= 10 && b <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

            return dp[len];
        }
    }

    public static void main(String[] args) {
        String s = "226";
        System.out.println(new Solution().numsDecoding(s));
    }
}

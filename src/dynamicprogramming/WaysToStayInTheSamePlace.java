package dynamicprogramming;

/**
 * description 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/
 * number-of-ways-to-stay-in-the-same-place-after-some-steps 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-13 10:36
 **/
public class WaysToStayInTheSamePlace {

    static class Solution {

        /**
         * dp[s][l] 表示剩余 s 次操作，所在位置为 j 的方案数
         * 由「原地」操作到达当前状态，消耗一次操作，此时由状态 dp[s + 1][l] 转移而来
         * 由「向左」操作到达当前状态，消耗一次操作，此时由状态 dp[s + 1][l + 1] 转移而来
         * 由「向右」操作到达当前状态，消耗一次操作，此时由状态 dp[s + 1][l - 1] 转移而来
         *
         * (执行用时：12 ms, 在所有 Java 提交中击败了90.35%的用户)
         * (内存消耗：37.8 MB, 在所有 Java 提交中击败了78.07%的用户)
         *
         * @param steps  操作次数
         * @param arrLen 数组长度
         * @return       恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数
         */
        public int numWays(int steps, int arrLen) {
            // steps 次数操作最远也不会移动超过 steps / 2 这个位置
            int maxLen = Math.min(arrLen - 1, steps / 2);
            int[][] dp = new int[steps + 1][maxLen + 1];
            dp[steps][0] = 1;

            int mod = (int)1e9 + 7;
            for (int s = steps - 1; s >= 0; s--) {
                // 随着可操作次数的减少，可达到的最远位置下标也会逐步缩小
                int curMaxLen = Math.min(s, maxLen);
                for (int l = 0; l <= curMaxLen; l++) {
                    dp[s][l] = (dp[s][l] + dp[s + 1][l]) % mod;
                    if (l - 1 >= 0) {
                        dp[s][l] = (dp[s][l] + dp[s + 1][l - 1]) % mod;
                    }
                    if (l + 1 <= maxLen) {
                        dp[s][l] = (dp[s][l] + dp[s + 1][l + 1]) % mod;
                    }
                }
            }

            return dp[0][0];
        }
    }

    public static void main(String[] args) {
        int steps = 3, arrLen = 2;
        System.out.println(new Solution().numWays(steps, arrLen));
    }
}
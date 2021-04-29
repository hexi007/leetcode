package dynamicprogramming;

/**
 * description 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。
 * 青蛙可以跳上石子，但是不可以跳入水中。
 * 给你石子的位置列表 stones（用单元格序号 升序 表示），请判定（即能否在最后一步跳至最后一块石子上）。
 * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。
 * 另请注意，青蛙只能向前方（终点的方向）跳跃。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com青蛙能否成功过河/problems/frog-jump 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-29 10:13
 **/
public class FrogJump {

    static class Solution {

        /**
         * 动态规划
         * 解法作者：AC_OIer
         * 链接：https://leetcode-cn.com/problems/frog-jump/
         * solution/gong-shui-san-xie-yi-ti-duo-jie-jiang-di-74fw/
         * (执行用时：42 ms, 在所有 Java 提交中击败了81.20%的用户)
         * (内存消耗：44.2 MB, 在所有 Java 提交中击败了45.51%的用户)
         *
         * @param stones 石子的位置列表
         * @return       青蛙能否成功过河
         */
        public boolean canCross(int[] stones) {
            int len = stones.length;
            if (stones[1] != 1) {
                return false;
            }

            // dp[i][k] 当前在第 i 个位置，并且是以步长 k 跳到位置 i 时，是否到达最后一块石子
            boolean[][] dp = new boolean[len][len];
            // 起始状态是「经过步长为 1」的跳跃到达「位置 1」，如果从起始状态出发，存在一种方案到达最后一块石子的话，
            // 那么必然存在一条反向路径，它是以从「最后一块石子」开始，并以「某个步长 kk」开始跳跃，最终以回到位置 1
            dp[1][1] = true;
            for (int i = 2; i < len; i++) {
                for (int j = 1; j < i; j++) {
                    int k = stones[i] - stones[j];
                    if (k <= j + 1) {
                        dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                    }
                }
            }

            for (int i = 1; i < len; i++) {
                if (dp[len - 1][i]) {
                    return true;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        int[] stones = {0,1,3,5,6,8,12,17};
        System.out.println(new Solution().canCross(stones));
    }
}

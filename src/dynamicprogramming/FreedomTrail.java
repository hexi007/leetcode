package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * description 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，
 * 并使用表盘拼写特定关键词才能开门。  给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，
 * 表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，
 * 然后按下中心按钮，以此逐个拼写完 key 中的所有字符。  旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。
 * 旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。
 * 按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * @author 27771
 * create 2020-11-11 09:44
 **/
public class FreedomTrail {

    static class Solution {
        /**
         * 动态规划
         * (执行用时：15 ms, 在所有 Java 提交中击败了51.37%的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了84.67%的用户)
         * @param ring 外环上的编码字符串
         * @param key  需要拼写的关键词
         * @return     所需最小步骤
         */
        public int findRotateSteps(String ring, String key) {
            int n = ring.length(), m = key.length();
            int listSize = 26;
            /*
             * pos 记录 ring 中每个字母出现的位置
             */
            ArrayList<Integer>[] pos = new ArrayList[listSize];
            for (int i = 0; i < listSize; i++) {
                pos[i] = new ArrayList<>();
            }
            for (int i = 0; i < n; i++) {
                pos[ring.charAt(i) - 'a'].add(i);
            }

            // dp[i][j] 表示拼写从前往后拼写出 key 的第 i 个字符，ring 的第 j 个字符与 12:00
            // 方向对齐的最少步数（下标均从 0 开始）
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[i], 0x3f3f3f);
            }
            char[] charKeys = key.toCharArray();

            // 动态规划
            // 先计算拼写第一个关键字符
            for (int i : pos[charKeys[0] - 'a']) {
                dp[0][i] = Math.min(i , n - i) + 1;
            }
            // 计算 完整 dp 数组
            // 从 key 的 i 下标开始计算
            for (int i = 1; i < m; i++) {
                // j 是 在 ring 中所有当前关键字符的位置
                for (int j : pos[charKeys[i] - 'a']) {
                    // k 是 在 ring 中所有前一个关键字符的位置
                    for (int k : pos[charKeys[i - 1] - 'a']) {
                        // 动态转移方程
                        // 对于 dp[i][j], 考虑从 dp[i - 1][k] 出发
                        // 比较所有前一个位置 k 和 当前位置 j 之间的距离再加上按下操作
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] +
                                Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                    }
                }
            }

            // dp[m - 1]最小值为答案
            int res = Integer.MAX_VALUE;
            for (int i : dp[m - 1]) {
                res = Math.min(res, i);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        String ring = "good";
        String key = "gd";
        System.out.println(new Solution().findRotateSteps(ring, key));
    }
}

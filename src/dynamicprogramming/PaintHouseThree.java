package dynamicprogramming;

/**
 * description 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。
 * 有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。  我们将连续相同颜色尽可能多的房子称为一个街区。
 * （比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区  [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）
 * 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
 * houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。 cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
 * 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。
 * 如果没有可用的涂色方案，请返回 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/paint-house-iii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-04 09:16
 **/
public class PaintHouseThree {

    static class Solution {

        int inf = 0x3f3f3f3f;

        /**
         * dp[i][j][k] 为考虑前 i 间房子，且第 i 间房子的颜色编号为 j，前 i 间房子形成的分区数量为 k 的所有方案中的「最小上色成本」
         *
         * 作者：AC_OIer
         * 链接：https://leetcode-cn.com/problems/paint-house-iii/
         * solution/gong-shui-san-xie-san-wei-dong-tai-gui-h-ud7m/
         *
         * (执行用时：21 ms, 在所有 Java 提交中击败了93.94%的用户)
         * (内存消耗：39.2 MB, 在所有 Java 提交中击败了37.88%的用户)
         *
         * @param houses houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色
         * @param cost   是将第 i 个房子涂成颜色 j+1 的花费
         * @param m      房子数
         * @param n      颜色数
         * @param target 目标街区数
         * @return       房子涂色方案的最小总花费
         */
        public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
            int[][][] dp = new int[m + 1][n + 1][target + 1];

            // 不存在分区数量为 0 的状态
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    dp[i][j][0] = inf;
                }
            }

            for (int i = 1; i <= m; i++) {
                int color = houses[i - 1];
                for (int j = 1; j <= n; j++) {
                    for (int k = 1; k <= target; k++) {
                        // 形成分区数量大于房子数量，状态无效
                        if (k > i) {
                            dp[i][j][k] = inf;
                            continue;
                        }

                        // 第 i 间房间已经上色
                        if (color != 0) {
                            if (j == color) { // 只有与「本来的颜色」相同的状态才允许被转移
                                int tmp = inf;
                                // 先从所有「第 i 间房形成新分区」方案中选最优（即与上一房间颜色不同）
                                for (int p = 1; p <= n; p++) {
                                    if (p != j) {
                                        tmp = Math.min(tmp, dp[i - 1][p][k - 1]);
                                    }
                                }
                                // 再结合「第 i 间房不形成新分区」方案中选最优（即与上一房间颜色相同）
                                dp[i][j][k] = Math.min(dp[i - 1][j][k], tmp);

                            } else { // 其余状态无效
                                dp[i][j][k] = inf;
                            }

                            // 第 i 间房间尚未上色
                        } else {
                            int u = cost[i - 1][j - 1];
                            int tmp = inf;
                            // 先从所有「第 i 间房形成新分区」方案中选最优（即与上一房间颜色不同）
                            for (int p = 1; p <= n; p++) {
                                if (p != j) {
                                    tmp = Math.min(tmp, dp[i - 1][p][k - 1]);
                                }
                            }
                            // 再结合「第 i 间房不形成新分区」方案中选最优（即与上一房间颜色相同）
                            // 并将「上色成本」添加进去
                            dp[i][j][k] = Math.min(tmp, dp[i - 1][j][k]) + u;
                        }
                    }
                }
            }

            // 从「考虑所有房间，并且形成分区数量为 t」的所有方案中找答案
            int res = inf;
            for (int i = 1; i <= n; i++) {
                res = Math.min(res, dp[m][i][target]);
            }
            return res == inf ? -1 : res;
        }
    }

    public static void main(String[] args) {
        int[] houses = {0, 0, 0, 0, 0};
        int[][] cost = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        int m = 5, n = 2, target = 3;
        System.out.println(new Solution().minCost(houses, cost, m, n, target));
    }
}

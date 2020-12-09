package dynamicprogramming;

/**
 * description 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 * @author 27771
 * create 2020-12-09 09:37
 **/
public class UniquePaths {

    static class Solution {

        int[][] grid;

        /**
         * 动态规划
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35.5 MB, 在所有 Java 提交中击败了39.49%的用户)
         *
         * @param m 网格行数
         * @param n 网格列数
         * @return  从左上角到右下角不同路径
         */
        public int uniquePaths(int m, int n) {
            if (m == 1 || n == 1) {
                return 1;
            }

            grid = new int[m][n];
            // 初始化第一行和第一列
            for (int i = 0; i < m; i++) {
                grid[i][0] = 1;
            }
            for (int i = 0; i < n; i++) {
                grid[0][i] = 1;
            }

            // 对于剩下每一格，到达该位置的路径为到达上方和左方的路径数之和
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
                }
            }

            return grid[m - 1][n - 1];
        }

        /**
         * 从左上角到右下角的过程中，我们需要移动 m + n - 2 次，其中有 m - 1 次向下移动，n - 1次向右移动。
         * 因此路径的总数，就等于从 m + n - 2 次移动中选择 m − 1 次向下移动的方案数,
         * 即组合数 C(m - 1, m + n - 2)
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35.3 MB, 在所有 Java 提交中击败了59.86%的用户)
         *
         * @param m 网格行数
         * @param n 网格列数
         * @return  从左上角到右下角不同路径
         */
        public int uniquePaths1(int m, int n) {
            long ans = 1;
            for (int x = n, y = 1; y < m; ++x, ++y) {
                ans = ans * x / y;
            }
            return (int)ans;
        }
    }

    public static void main(String[] args) {
        int m = 7, n = 3;
        System.out.println(new Solution().uniquePaths(m, n));
        System.out.println(new Solution().uniquePaths1(m, n));
    }
}

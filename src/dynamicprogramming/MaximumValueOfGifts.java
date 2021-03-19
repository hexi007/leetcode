package dynamicprogramming;

/**
 * description 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-19 17:20
 **/
public class MaximumValueOfGifts {

    static class Solution {
        /**
         * 动态规划
         * (执行用时：3 ms, 在所有 Java 提交中击败了81.64%的用户)
         * (内存消耗：41.4 MB, 在所有 Java 提交中击败了12.43%的用户)
         *
         * @param grid 棋盘
         * @return     最多礼物价值
         */
        public int maxValue(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int rows = grid.length, cols = grid[0].length;
            int[][] dp = new int[rows][cols];
            dp[0][0] = grid[0][0];
            for (int i = 1; i < cols; i++) {
                dp[0][i] = dp[0][i - 1] + grid[0][i];
            }
            for (int i = 1; i < rows; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }

            for (int i = 1; i < rows; i++) {
                for (int j = 1; j < cols; j++) {
                    // 从上面过来还是从左边过来
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
                }
            }

            return dp[rows - 1][cols - 1];
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1},{1, 5 , 1}, {4, 2, 1}};
        System.out.println(new Solution().maxValue(grid));
    }
}

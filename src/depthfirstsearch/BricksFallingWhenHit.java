package depthfirstsearch;

import java.util.Arrays;

/**
 * description 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
 * 一块砖直接连接到网格的顶部，或者 至少有一块相邻（4个方向之一）砖块 稳定 不会掉落时 给你一个数组 hits ，
 * 这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，
 * 然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
 * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
 * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/bricks-falling-when-hit 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-16 20:44
 **/
public class BricksFallingWhenHit {

    static class Solution {
        // 题解区大佬解法

        // 标记与顶部相连
        private static final int TOP = 2;
        // 标记有砖块
        private static final int BRICK = 1;
        private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        /**
         * 逆序思维
         * (执行用时：10 ms, 在所有 Java 提交中击败了74.76%的用户)
         * (内存消耗：53.4 MB, 在所有 Java 提交中击败了11.76%的用户)
         *
         * @param grid 砖块位置
         * @param hits 打击位置
         * @return     每次打击后掉落砖块数
         */
        public int[] hitBricks(int[][] grid, int[][] hits) {
            int[] res = new int[hits.length];
            // 移除所有hits位置的砖块
            for (int[] hit : hits) {
                grid[hit[0]][hit[1]]--;
            }

            // 把所有与top相连的标记为2
            for (int i = 0; i < grid[0].length; i++) {
                dfs(0, i, grid);
            }

            // Add back the hited Bricks
            for (int i = hits.length - 1; i >= 0; i--) {
                int x = hits[i][0], y = hits[i][1];
                grid[x][y]++;
                // 加回去之后的情况为0或1，为1说明原来这里确实有砖块
                if (grid[x][y] == BRICK && isConnected(x, y, grid)) {
                    // 当前位置有砖块，而且与顶部相连，做dfs
                    res[i] = dfs(x, y, grid) - 1;
                }
            }

            return res;
        }

        private int dfs(int i, int j, int[][] grid) {
            // grid[i][j] == BRICK 代表有砖块
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != BRICK) {
                return 0;
            }
            grid[i][j] = 2;
            return dfs(i + 1, j, grid)
                    + dfs(i - 1, j, grid)
                    + dfs(i, j + 1, grid)
                    + dfs(i, j - 1, grid) + 1;
        }
        // isConnected用来判断当前坐标是否和顶部相连
        private boolean isConnected(int i, int j, int[][] grid) {
            // 在第0行必然相连
            if (i == 0) {
                return true;
            }
            for (int[] d : DIRS) {
                int x = i + d[0], y = j + d[1];
                // 如果周围的四个点有与顶部相连的，那这个点也是与顶部相连的
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == TOP) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{1,1,1,0}};
        int[][] hits = {{1,0}};
        System.out.println(Arrays.toString(new Solution().hitBricks(grid, hits)));
    }
}

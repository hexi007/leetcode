package array;

/**
 * description 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。
 * 整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。
 * 网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * @author 27771
 * create 2020-10-30 10:05
 **/
public class IslandPerimeter {
    static class Solution {
        /**
         * 暴力解法 ，遍历判断每个方格周围的水域
         * (执行用时：8 ms, 在所有 Java 提交中击败了75.57% 的用户)
         * (内存消耗：39.6 MB, 在所有 Java 提交中击败了60.02% 的用户)
         * @param grid 维网格地图
         * @return  岛屿的周长
         */
        public int islandPerimeter(int[][] grid) {
            int res = 0;
            for(int i = 0; i < grid.length;i++){
                for (int j = 0; j < grid[i].length; j++){
                    if(grid[i][j] == 1){
                        if(i == 0 || grid[i - 1][j] == 0){
                            res++;
                        }
                        if(i == grid.length - 1 || grid[i + 1][j] == 0){
                            res++;
                        }
                        if(j == 0 || grid[i][j - 1] == 0){
                            res++;
                        }
                        if(j == grid[i].length - 1 || grid[i][j + 1] == 0){
                            res++;
                        }
                    }
                }
            }
            return res;
        }

        /**
         * 有相邻就 -2
         * (执行用时：6 ms, 在所有 Java 提交中击败了99.90% 的用户)
         * (内存消耗：39.9 MB, 在所有 Java 提交中击败了30.67% 的用户)
         * @param grid 维网格地图
         * @return  岛屿的周长
         */
        public int islandPerimeter1(int[][] grid) {
            int rsp = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        rsp += 4;
                        if (i > 0 && grid[i - 1][j] == 1) {
                            rsp -= 2;
                        }
                        if (j > 0 && grid[i][j - 1] == 1) {
                            rsp -= 2;
                        }
                    }
                }
            }
            return rsp;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(new Solution().islandPerimeter(grid));
    }
}
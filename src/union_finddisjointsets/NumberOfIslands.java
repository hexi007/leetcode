package union_finddisjointsets;

/**
 * description 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * @author 27771
 * create 2020-11-19 15:40
 **/
public class NumberOfIslands {
    static class Solution {
        /**
         * 并查集求岛屿数量
         * (执行用时：6 ms, 在所有 Java 提交中击败了16.56%的用户)
         * (内存消耗：40.9 MB, 在所有 Java 提交中击败了84.57%的用户)
         * @param grid 地图网格
         * @return     岛屿数量
         */
        public int numIslands(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            UnionFind unionFind = new UnionFind(grid, m, n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 将陆地合并
                    if (j + 1 < n && grid[i][j] == '1' && grid[i][j + 1] == '1') {
                        unionFind.joinElement(i * n + j, i * n + j + 1);
                    }
                    if (i + 1 < m && grid[i][j] == '1' && grid[i + 1][j] == '1') {
                        unionFind.joinElement(i * n + j, (i + 1) * n + j);
                    }
                }
            }
            int res = 0;
            // 统计不含水的集合数
            for (int i = 0; i < unionFind.parent.length; i++) {
                if (unionFind.parent[i] == i && unionFind.parent[i] != unionFind.zero) {
                    res++;
                }
            }
            return res;
        }

        /**
         * 并查集
         */
        static class UnionFind {
            private final int[] parent;
            private final int[] weight;
            private int zero = -1;

            public UnionFind(char[][] grid, int m, int n) {
                parent = new int[m * n];
                weight = new int[m * n];

                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        // 所有水以第一个水的位置为父节点
                        if (grid[i][j] == '0') {
                            if (zero == -1) {
                                zero = i * n + j;
                            }
                            parent[i * n + j] = zero;
                        } else {
                            parent[i * n + j] = i * n + j;
                        }
                        weight[i * n + j] = 1;
                    }
                }
            }

            public int find(int element) {
                while (element != parent[element]) {
                    parent[element] = parent[parent[element]];
                    element = parent[element];
                }
                return element;
            }

            public void joinElement(int firstElement, int secondElement) {
                int firstRoot = find(firstElement);
                int secondRoot = find(secondElement);

                if (firstRoot == secondRoot) {
                    return;
                }

                if (weight[firstRoot] > weight[secondRoot]) {
                    parent[secondRoot] = firstRoot;
                    weight[firstRoot] += weight[secondRoot];
                } else {
                    parent[firstRoot] = secondRoot;
                    weight[secondRoot] += weight[firstRoot];
                }
            }
        }

        int islandNum = 0;

        /**
         * dfs 求岛屿数量
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：41 MB, 在所有 Java 提交中击败了77.59%的用户)
         * @param grid 地图网格
         * @return     岛屿数量
         */
        public int numIslands1(char[][] grid) {
            int m = grid.length, n = grid[0].length;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);
                        islandNum++;
                    }
                }
            }
            return islandNum;
        }

        private void dfs(char[][] grid, int i, int j) {
            if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == '0') {
                return;
            }
            grid[i][j] = '0';
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},
                {'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(new Solution().numIslands(grid));
        System.out.println(new Solution().numIslands1(grid));
    }
}
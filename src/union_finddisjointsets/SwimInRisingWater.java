package union_finddisjointsets;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，
 * 但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。
 * 当然，在你游泳的时候你必须待在坐标方格里面。
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/swim-in-rising-water 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-30 10:24
 **/
public class SwimInRisingWater {

    static class Solution {

        /**
         * 将 grid 所有点相连的边按相连两顶点最大高度排序，在并查集中不断合并权值最小的边，直到起点和终点相连
         * (执行用时：23 ms, 在所有 Java 提交中击败了30.71%的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了60.67%的用户)
         *
         * @param grid 坐标方格
         * @return     从左上到右下时间
         */
        public int swimInWater(int[][] grid) {
            int rows = grid.length, columns = grid[0].length;
            // edgesSize 边数
            int edgesSize = 2 * rows * columns - rows - columns, edgeIndex = 0;
            Edge[] edges = new Edge[edgesSize];

            // 初始化所有体力消耗边
            for (int i = 0;i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // 当前顶点右边是否有条边
                    if (j + 1 < columns) {
                        edges[edgeIndex++] = new Edge(i * columns + j, i * columns + j + 1,
                                Math.max( grid[i][j + 1], grid[i][j]));
                    }
                    // 当前顶点下边是否有条边
                    if (i + 1 < rows) {
                        edges[edgeIndex++] = new Edge(i * columns + j, i * columns + columns + j,
                                Math.max(grid[i + 1][j], grid[i][j]));
                    }
                }
            }

            // 按边权值从小到大排序
            Arrays.sort(edges, Comparator.comparingInt(o -> o.high));

            int size = rows * columns, minTime = 0;
            UnionFind unionFind = new UnionFind(size);

            for (Edge edge : edges) {
                // 不断合并权值最小的边
                unionFind.joinElement(edge.from, edge.to);
                // 更新所需最小时间
                minTime = Math.max(minTime, edge.high);
                // 起点和终点相连时退出
                if (unionFind.isConnected(0, size - 1)) {
                    break;
                }
            }

            return minTime;
        }

        // 体力消耗边类
        static class Edge {
            int from, to, high;

            public Edge(int from, int to, int high) {
                this.from = from;
                this.to = to;
                this.high = high;
            }
        }

        static class UnionFind {
            // parent 每个节点的父节点，可以是自身
            private final int[] parent;
            // 每个节点的重量（包括自身）
            private final int[] weight;

            /**
             * 初始化
             *
             * @param size 并查集大小
             */
            public UnionFind(int size) {
                parent = new int[size];
                weight = new int[size];

                // 开始时每个节点的父节点是自身，重量为 1
                for (int i = 0; i < size; i++) {
                    parent[i] = i;
                    weight[i] = 1;
                }
            }

            /**
             * 找最终父节点
             *
             * @param element 自身节点
             * @return 最终父节点
             */
            public int find(int element) {
                // 当前父节点不是自身，即自己不是最终父节点
                while (element != parent[element]) {
                    // 压缩路径，将父节点替换为父节点的父节点
                    parent[element] = parent[parent[element]];
                    element = parent[element];
                }
                return element;
            }

            /**
             * 合并集合
             *
             * @param firstElement  元素 1
             * @param secondElement 元素 2
             */
            public void joinElement(int firstElement, int secondElement) {
                int firstRoot = find(firstElement);
                int secondRoot = find(secondElement);

                // 先判断是否在同一个集合
                if (firstRoot == secondRoot) {
                    return;
                }

                // 重量大的最终父节点作为合并后的最终父节点，同时更新重量
                if (weight[firstRoot] > weight[secondRoot]) {
                    parent[secondRoot] = firstRoot;
                    weight[firstRoot] += weight[secondRoot];
                } else {
                    parent[firstRoot] = secondRoot;
                    weight[secondRoot] += weight[firstRoot];
                }
            }

            /**
             * 判断两个元素是否在同一个集合
             * @param firstElement  元素 1
             * @param secondElement 元素 2
             * @return              是否在一个集合
             */
            public boolean isConnected(int firstElement, int secondElement) {
                // 通过两元素最终父节点是否是同一个
                return find(firstElement) == find(secondElement);
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1},{2,3}};
        System.out.println(new Solution().swimInWater(grid));
    }
}

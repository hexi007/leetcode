package union_finddisjointsets;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description 你准备参加一场远足活动。给你一个二维brows x columns 的地图 heights ，
 * 其中 heights[row][col] 表示格子 (row, col) 的高度。
 * 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-29 10:12
 **/
public class PathWithMinimumEffort {

    static class Solution {

        /**
         * 将 heights 所有点相连的边按体力消耗排序，在并查集中不断合并消耗体力最小的边，直到起点和终点相连
         * (执行用时：96 ms, 在所有 Java 提交中击败了60.11%的用户)
         * (内存消耗：39.5 MB, 在所有 Java 提交中击败了31.45%的用户)
         *
         * @param heights 地图
         * @return        从左上角走到右下角的最小体力消耗值
         */
        public int minimumEffortPath(int[][] heights) {
            int rows = heights.length, columns = heights[0].length;
            // edgesSize 体力消耗边数
            int edgesSize = 2 * rows * columns - rows - columns, edgeIndex = 0;
            Edge[] edges = new Edge[edgesSize];

            // 初始化所有体力消耗边
            for (int i = 0;i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // 当前顶点右边是否有条边
                    if (j + 1 < columns) {
                        edges[edgeIndex++] = new Edge(i * columns + j, i * columns + j + 1,
                                Math.abs(heights[i][j + 1] - heights[i][j]));
                    }
                    // 当前顶点下边是否有条边
                    if (i + 1 < rows) {
                        edges[edgeIndex++] = new Edge(i * columns + j, i * columns + columns + j,
                                Math.abs(heights[i + 1][j] - heights[i][j]));
                    }
                }
            }

            // 按体力消耗从小到大排序
            Arrays.sort(edges, Comparator.comparingInt(o -> o.strength));

            int size = rows * columns, minStrength = 0;
            UnionFind unionFind = new UnionFind(size);

            for (Edge edge : edges) {
                // 断合并消耗体力最小的边
                unionFind.joinElement(edge.from, edge.to);
                // 更新最小体力消耗值
                minStrength = Math.max(minStrength, edge.strength);
                // 起点和终点相连时退出
                if (unionFind.isConnected(0, size - 1)) {
                    break;
                }
            }

            return minStrength;
        }

        // 体力消耗边类
        static class Edge {
            int from, to, strength;

            public Edge(int from, int to, int strength) {
                this.from = from;
                this.to = to;
                this.strength = strength;
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
        int[][] heights = {{4,3,4,10,5,5,9,2},{10,8,2,10,9,7,5,6},{5,8,10,10,10,7,4,2},
                {5,1,3,1,1,3,1,9},{6,4,10,6,10,9,4,6}};
        System.out.println(new Solution().minimumEffortPath(heights));
    }
}

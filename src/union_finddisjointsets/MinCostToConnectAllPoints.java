package union_finddisjointsets;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：
 * |xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。  请你返回将所有点连接的最小总费用。
 * 只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-31 17:38
 **/
public class MinCostToConnectAllPoints {

    static class Solution {

        /**
         * 暴力，Kruskal + 并查集
         * (执行用时：890 ms, 在所有 Java 提交中击败了7.81%的用户)
         * (内存消耗：65.3 MB, 在所有 Java 提交中击败了8.89%的用户)
         *
         * @param points 点数组
         * @return       将所有点连接的最小总费用
         */
        public int minCostConnectPoints(int[][] points) {
            int pointCount = points.length;
            int edgesSize = pointCount * (pointCount - 1);
            Edge[] edges = new Edge[edgesSize];
            int edgeIndex = 0;

            for (int i = 0; i < pointCount; i++) {
                for (int j = 0; j < pointCount; j++) {
                    if (j != i) {
                        edges[edgeIndex++] = new Edge(i, j ,
                                Math.abs(points[i][0] - points[j][0]) +
                                Math.abs(points[i][1] - points[j][1]));
                    }
                }
            }

            Arrays.sort(edges, Comparator.comparingInt(o -> o.distance));

            UnionFind unionFind = new UnionFind(pointCount);
            int connections = pointCount - 1, res = 0;

            for (int i = 0; i < edgesSize && connections > 0; i++) {
                if (!unionFind.isConnected(edges[i].from, edges[i].to)) {
                    unionFind.joinElement(edges[i].from, edges[i].to);
                    res += edges[i].distance;
                    connections--;
                }

            }

            return res;
        }

        // 曼哈顿距离边类
        static class Edge {
            int from, to, distance;

            public Edge(int from, int to, int distance) {
                this.from = from;
                this.to = to;
                this.distance = distance;
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
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        System.out.println(new Solution().minCostConnectPoints(points));
    }
}

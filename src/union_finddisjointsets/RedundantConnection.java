package union_finddisjointsets;

import java.util.Arrays;

/**
 * description 在本问题中, 树指的是一个连通且无环的无向图。
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
 * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。  结果图是一个以边组成的二维数组。
 * 每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。
 * 答案边 [u, v] 应满足相同的格式 u < v。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/redundant-connection 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-13 19:43
 **/
public class RedundantConnection {

    static class Solution {

        /**
         * 并查集连接没有相连的点，出现重复就返回结果
         * (执行用时：1 ms, 在所有 Java 提交中击败了87.99%的用户)
         * (内存消耗：38.8 MB, 在所有 Java 提交中击败了38.31%的用户)
         *
         * @param edges 图的所有边
         * @return      一条附加的边
         */
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            // 图顶点从 1 开始标号
            UnionFind unionFind = new UnionFind(n + 1);
            for (int[] edge : edges) {
                if (!unionFind.isConnected(edge[0], edge[1])) {
                    unionFind.joinElement(edge[0], edge[1]);
                } else {
                    return edge;
                }
            }
            return null;
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

            /**
             * 统计并查集有多少个集合
             *
             * @return  并查集集合数
             */
            public int count() {
                int res = 0;
                for (int i = 0; i < this.parent.length; i++) {
                    // 只有 parent 是自己的才算一个集合
                    if (this.parent[i] == i) {
                        res++;
                    }
                }
                return res;
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(new Solution().findRedundantConnection(edges)));
    }
}

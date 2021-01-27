package union_finddisjointsets;

/**
 * description Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：
 * 类型 1：只能由 Alice 遍历。 类型 2：只能由 Bob 遍历。 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typeI, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typeI 的双向边。
 * 请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。
 * 如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/
 * remove-max-number-of-edges-to-keep-graph-fully-traversable 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-27 10:44
 **/
public class KeepGraphFullyTraversable {

    static class Solution {

        /**
         * 优先考虑类型 3 的边也就是公共边，再分别考虑其他类型的边
         * (执行用时：15 ms, 在所有 Java 提交中击败了87.63%的用户)
         * (内存消耗：96.4 MB, 在所有 Java 提交中击败了79.38%的用户)
         *
         * @param n     节点数
         * @param edges 所有边
         * @return      可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1
         */
        public int maxNumEdgesToRemove(int n, int[][] edges) {
            // 边为类型 3 的并查集
            UnionFind typeThree = new UnionFind(n);
            // 边为类型 3 的并查集的拷贝，这个个并查集之和分别给 Alice 和 Bob 用
            UnionFind typeThreeCopy = new UnionFind(n);
            // res 统计过程中可以删除的边
            int res = 0;

            for (int[] edge : edges) {
                // 所有顶点序号从 0 开始
                --edge[1];
                --edge[2];

                // 先考虑所有类型为 3 的边
                if (edge[0] == 3) {
                    // 如果该边两顶点已经联通，则当前边可删除，否则合并这两个点
                    if (typeThree.isConnected(edge[1], edge[2])) {
                        res++;
                    } else {
                        typeThree.joinElement(edge[1], edge[2]);
                        // 拷贝并查集
                        typeThreeCopy.joinElement(edge[1], edge[2]);
                    }
                }
            }

            // 并查集 typeThree 给类型为 1 的边用
            for (int[] edge : edges) {
                if (edge[0] == 1) {
                    // typeThree 里该边两顶点已经联通，则当前边可删除，否则合并这两个点
                    if (typeThree.isConnected(edge[1], edge[2])) {
                        res++;
                    } else {
                        typeThree.joinElement(edge[1], edge[2]);
                    }
                }
            }

            // 并查集 typeThreeCopy 给类型为 2 的边用
            for (int[] edge : edges) {
                if (edge[0] == 2) {
                    // typeThreeCopy 里该边两顶点已经联通，则当前边可删除，否则合并这两个点
                    if (typeThreeCopy.isConnected(edge[1], edge[2])) {
                        res++;
                    } else {
                        typeThreeCopy.joinElement(edge[1], edge[2]);
                    }
                }
            }

            // 只有并查集 typeThree 和 并查集 typeThreeCopy 都可以完全遍历时才返回可删除边数
            if (typeThree.count() == 1 && typeThreeCopy.count() == 1) {
                return res;
            }
            return -1;
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
        int[][] edges = {{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
        int n = 4;
        System.out.println(new Solution().maxNumEdgesToRemove(n, edges));
    }
}

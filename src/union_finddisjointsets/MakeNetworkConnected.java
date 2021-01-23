package union_finddisjointsets;

/**
 * description 用以太网线缆将 n 台计算机连接成一个网络，
 * 计算机的编号从 0 到 n - 1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 。
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。
 * 请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 - 1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-23 08:34
 **/
public class MakeNetworkConnected {

    static class Solution {

        /**
         * 需要改动的操作就是让连通分支之间相连，所以关键是计算联通分支数
         * (执行用时：5 ms, 在所有 Java 提交中击败了77.28%的用户)
         * (内存消耗：52.4 MB, 在所有 Java 提交中击败了68.71%的用户)
         *
         * @param n           计算机数
         * @param connections 所有线缆
         * @return            使所有计算机都连通所需的最少操作次数
         */
        public int makeConnected(int n, int[][] connections) {
            // 线缆太少则怎么操作也不能让计算机相连
            if (connections.length < n - 1) {
                return -1;
            }

            UnionFind unionFind = new UnionFind(n);
            for (int[] connection : connections) {
                unionFind.joinElement(connection[0], connection[1]);
                System.out.println(unionFind.count());
            }

            // 操作数为连通分支数减一
            return unionFind.count() - 1;
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
        int n = 4;
        int[][] connections = {{0,1},{0,2},{1,2}};
        System.out.println(new Solution().makeConnected(n, connections));
    }
}

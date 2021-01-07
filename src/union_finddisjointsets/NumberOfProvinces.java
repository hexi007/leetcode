package union_finddisjointsets;

import java.util.Stack;

/**
 * description 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，
 * 且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。  返回矩阵中 省份 的数量。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-provinces 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-07 12:14
 **/
public class NumberOfProvinces {

    static class Solution {

        // visited 记录图中哪些点被访问过
        boolean[] visited;
        // n 图中顶点数
        int n;

        /**
         * 遍历图获取连通分支个数
         * (执行用时：6 ms, 在所有 Java 提交中击败了20.20%的用户)
         * (内存消耗：39.3 MB, 在所有 Java 提交中击败了72.72%的用户)
         *
         * @param isConnected 图矩阵
         * @return            连通分支个数
         */
        public int findCircleNum(int[][] isConnected) {
            n = isConnected.length;
            visited = new boolean[n];
            int count = 0;

            for (int i = 0; i < n; i++) {
                // 只有没被访问过的节点才进行深度优先遍历
                if (!visited[i]) {
                    dfs(isConnected, i);
                    // 连通分支个数加一
                    count++;
                }
            }

            return count;
        }

        /**
         * 深度优先搜索
         * @param isConnected 图矩阵
         * @param start       搜索起点
         */
        public void dfs(int[][] isConnected, int start) {
            visited[start] = true;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                // 与起点相连且没有被访问过的节点放入栈中
                if (isConnected[start][i] == 1 && !visited[i]) {
                    stack.push(i);
                }
            }
            // 栈不空继续搜索
            while (!stack.isEmpty()) {
                int now = stack.pop();
                visited[now] = true;
                // 同理，与当前图顶点相连且没有被访问过的节点放入栈中
                for (int i = 0; i < n; i++) {
                    if (isConnected[now][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }

        /**
         * 并查集获取连通分支个数
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.49%的用户)
         * (内存消耗：39.2 MB, 在所有 Java 提交中击败了87.29%的用户)
         *
         * @param isConnected 图矩阵
         * @return            连通分支个数
         */
        public int findCircleNum1(int[][] isConnected) {
            n = isConnected.length;
            UnionFind unionFind = new UnionFind(n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    // 相连的顶点合并
                    if (isConnected[i][j] == 1) {
                        unionFind.joinElement(i, j);
                    }
                }
            }
            return unionFind.count();
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
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(new Solution().findCircleNum(isConnected));
        System.out.println(new Solution().findCircleNum1(isConnected));
    }
}

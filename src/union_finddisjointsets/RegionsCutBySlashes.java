package union_finddisjointsets;

/**
 * description 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。
 * 这些字符会将方块划分为一些共边的区域。  （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 * 返回区域的数目。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-25 10:15
 **/
public class RegionsCutBySlashes {

    static class Solution {

        /**
         * 每个格子划分为 4 部分，按顺时针方向划分为 0，1，2，3
         * 一个格子中最下方的三角形，必然和下面的格子（如果存在）中最上方的三角形连通；
         * 一个格子中最右方的三角形，必然和右边的格子（如果存在）中最左方的三角形连通。
         * 求联通分量
         * (执行用时：7 ms, 在所有 Java 提交中击败了51.55%的用户)
         * (内存消耗：37.9 MB, 在所有 Java 提交中击败了31.93%的用户)
         *
         * @param grid 网格
         * @return     区域的数目
         */
        public int regionsBySlashes(String[] grid) {
            int len = grid.length;
            UnionFind unionFind = new UnionFind(len * len * 4);

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    int index = i * len + j;
                    // i < len - 1 则下方还有一个格子
                    if (i < len - 1) {
                        int bottom = index + len;
                        unionFind.joinElement(index * 4 + 2, bottom * 4);
                    }
                    // j < len - 1 则右方还有一个格子
                    if (j < len - 1) {
                        int right = index + 1;
                        unionFind.joinElement(index * 4 + 1, right * 4 + 3);
                    }
                    char[] chars = grid[i].toCharArray();
                    if (chars[j] == '/') {
                        // '/' 表示格在的 0 和 3 相连， 1 和 2 相连
                        unionFind.joinElement(index * 4, index * 4 + 3);
                        unionFind.joinElement(index * 4 + 1, index * 4 + 2);
                    } else if (chars[j] == '\\') {
                        // '\\' 表示格在的 0 和 1 相连， 2 和 3 相连
                        unionFind.joinElement(index * 4, index * 4 + 1);
                        unionFind.joinElement(index * 4 + 2, index * 4 + 3);
                    } else {
                        // 否在该格在为空格，格子内所有区域联通
                        unionFind.joinElement(index * 4, index * 4 + 1);
                        unionFind.joinElement(index * 4 + 1, index * 4 + 2);
                        unionFind.joinElement(index * 4 + 2, index * 4 + 3);
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
        String[] grid = {" /","/ "};
        System.out.println(new Solution().regionsBySlashes(grid));
    }
}

package union_finddisjointsets;

/**
 * description N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。
 * 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，
 * 第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 * 这些情侣的初始座位 row[i] 是由最初始坐在第 i 个座位上的人决定的。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/couples-holding-hands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-14 08:52
 **/
public class CouplesHoldingHands {

    static class Solution {

        /**
         * 坐错了位置、逻辑上连在一起的情侣拆成所有的情侣都能彼此牵手的 「最少交换次数 = 情侣对数 - 1」
         * 至少交换的次数 = 所有情侣的对数 - 并查集里连通分量的个数
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35.9 MB, 在所有 Java 提交中击败了50.14%的用户)
         *
         * @param row 情侣作为数组
         * @return    最少交换座位的次数
         */
        public int minSwapsCouples(int[] row) {
            int len = row.length, half = 2;
            int couples = len / 2;
            UnionFind unionFind = new UnionFind(couples);
            for (int i = 0; i < len; i += half) {
                unionFind.joinElement(row[i] / 2, row[i + 1] / 2);
            }
            return couples - unionFind.getCount();
        }
    }

    private static class UnionFind {
        // parent 每个节点的父节点，可以是自身
        private final int[] parent;
        // 每个节点的重量（包括自身）
        private final int[] weight;
        // 并查集大小
        private int count;

        /**
         * 初始化
         *
         * @param size 并查集大小
         */
        public UnionFind(int size) {
            this.count = size;
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

            count--;
        }

        /**
         * 统计并查集有多少个集合
         *
         * @return 并查集集合数
         */
        public int getCount() {
            return count;
        }

    }

    public static void main(String[] args) {
        int[] row = {0, 2, 1, 3};
        System.out.println(new Solution().minSwapsCouples(row));
    }
}

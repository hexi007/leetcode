package union_finddisjointsets;

import java.util.Arrays;

/**
 * description 并查集模板：基于重量
 *
 * @author 27771
 * create 2020-11-19 15:16
 **/
public class UnionFindDisjointSets {

    static class UnionFind {
        // parent 每个节点的父节点，可以是自身
        private final int[] parent;
        // 每个节点的重量（包括自身）
        private final int[] weight;

        /**
         * 初始化
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
         * @param element 自身节点
         * @return        最终父节点
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
         * 合并集合
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

    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(10);
        unionFind.joinElement(3, 5);
        unionFind.joinElement(5, 8);
        System.out.println(Arrays.toString(unionFind.parent));
        System.out.println(Arrays.toString(unionFind.weight));
        System.out.println(unionFind.isConnected(3, 8));
    }

}
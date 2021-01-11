package union_finddisjointsets;

import java.util.*;

/**
 * description  给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，
 * 其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 * @author 27771
 * create 2021-01-11 09:41
 **/
public class SmallestStringWithSwaps {

    static class Solution {

        /**
         * 并查集获取索引对中连通的节点，使用 PriorityQueue 排序
         * (执行用时：58 ms, 在所有 Java 提交中击败了46.88%的用户)
         * (内存消耗：86.7 MB, 在所有 Java 提交中击败了51.63%的用户)
         *
         * @param s     字符串
         * @param pairs 字符串中的一些「索引对」数组
         * @return      交换索引对顺序后 s 按字典序最小的字符串
         */
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            int len = s.length();
            UnionFind unionFind = new UnionFind(len);

            for (List<Integer> l : pairs) {
                unionFind.joinElement(l.get(0), l.get(1));
            }

            // map key 是并查集中每一个集合的祖宗节点， value 是该集合对应的所有字符
            // 使用 PriorityQueue 是为了方便排序，也可手写桶排序，实现速度会更快
            Map<Integer, PriorityQueue<Character>> map = new HashMap<>(16);
            char[] sChars = s.toCharArray();

            for (int i = 0; i < len; i++) {
                int parent = unionFind.find(i);
                // 当 map 中不含该祖宗节点时添加该节点及其优先队列
                if (!map.containsKey(parent)) {
                    PriorityQueue<Character> queue = new PriorityQueue<>();
                    map.put(parent, queue);
                }
                // 该祖宗节点的优先队列添加当前位置的字符
                map.get(parent).add(sChars[i]);
            }

            StringBuilder sB = new StringBuilder();

            for (int i = 0; i < len; i++) {
                // 找到当前位置的祖宗节点对应的优先队列
                PriorityQueue<Character> queue = map.get(unionFind.find(i));
                // 取出优先队列的一个字符添加至 StringBuilder
                sB.append(queue.poll());
            }

            return sB.toString();
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
        }
    }

    public static void main(String[] args) {
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(1, 2));
        System.out.println(new Solution().smallestStringWithSwaps(s, pairs));
    }
}

package union_finddisjointsets;

/**
 * description 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。
 * 如果这两个字符串本身是相等的，那它们也是相似的。  例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)；
 * "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。
 * 注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，
 * 只需要这个词和该组中至少一个单词相似。
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。
 * 请问 strs 中有多少个相似字符串组？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/similar-string-groups 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-31 10:48
 **/
 class SimilarStringGroups {

    static class Solution {

        /**
         * 在并查集合并字母异位词
         * (执行用时：100 ms, 在所有 Java 提交中击败了58.33%的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了5.68%的用户)
         *
         * @param strs 字符串数组
         * @return     strs中有多少个相似字符串组
         */
        public int numSimilarGroups(String[] strs) {
            int size = strs.length;
            UnionFind unionFind = new UnionFind(size);

            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++ ) {
                    if (check(strs[i], strs[j])) {
                        unionFind.joinElement(i, j);
                    }
                }
            }

            return unionFind.count();
        }

        /**
         * 判断两个字符串是否是字母异位词
         *
         * @param strI 待比较字符串 I
         * @param strJ 待比较字符串 J
         * @return     是否是字母异位词
         */
        private boolean check(String strI, String strJ) {
            char[] charsI = strI.toCharArray(), charsJ = strJ.toCharArray();
            // differ 保存第一个位置不同的两个字符
            char[] differ = new char[2];
            // compare 不同的位置出现次数
            int compare = 0;

            for (int i = 0; i < strI.length(); i++) {
                if (charsI[i] != charsJ[i]) {
                    if (compare == 0) {
                        differ[0] = charsI[i];
                        differ[1] = charsJ[i];
                        compare++;
                    } else if (compare == 1) {
                        if (charsI[i] != differ[1] || charsJ[i] != differ[0]) {
                            return false;
                        }
                        compare++;
                    } else {
                        return false;
                    }
                }
            }

            return true;
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
        String[] strs = {"tars","rats","arts","star"};
        System.out.println(new Solution().numSimilarGroups(strs));
    }
}

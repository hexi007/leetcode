package union_finddisjointsets;

/**
 * description  给你一个由一些多米诺骨牌组成的列表 dominoes。
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *
 * @author 27771
 * create 2021-01-26 10:23
 **/
public class NumberOfEquivalentDominoPairs {

    static class Solution {

        /**
         * 暴力找等价骨牌会超时
         *
         * @param dominoes 多米诺骨牌组成的列表
         * @return         等价的骨牌对的数量
         */
        public int numEquivDominoPairs(int[][] dominoes) {
            int count = 0;
            for (int i = 0; i < dominoes.length; i++) {
                for (int j = i + 1; j < dominoes.length; j++) {
                    boolean find = (dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1])
                            || (dominoes[i][0] == dominoes[j][1] && dominoes[i][1] == dominoes[j][0]);
                    if (find) {
                        count++;
                    }
                }
            }
            return count;
        }

        /**
         * 二元对中的元素均不大于 99，因此我们可以将每一个二元对拼接成一个两位的正整数，
         * 即 (x, y) -> 10 * x + y
         * (执行用时：3 ms, 在所有 Java 提交中击败了85.51%的用户)
         * (内存消耗：47.7 MB, 在所有 Java 提交中击败了48.76%的用户)
         *
         * @param dominoes 多米诺骨牌组成的列表
         * @return         等价的骨牌对的数量
         */
        public int numEquivDominoPairs1(int[][] dominoes) {
            int[] num = new int[100];
            int res = 0;
            for (int[] domino : dominoes) {
                int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1]
                        : domino[1] * 10 + domino[0];
                res += num[val];
                num[val]++;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] dominoes = {{1,2},{1,2},{1,1},{1,2},{2,2}};
        System.out.println(new Solution().numEquivDominoPairs(dominoes));
        System.out.println(new Solution().numEquivDominoPairs1(dominoes));
    }
}

package dynamicprogramming;

import java.util.Arrays;

/**
 * description 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
 * 计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * @author 27771
 * create 2021-03-03 09:14
 **/
public class CountingBits {

    static class Solution {

        /**
         * 暴力
         * (执行用时：16 ms, 在所有 Java 提交中击败了5.38%的用户)
         * (内存消耗：42.7 MB, 在所有 Java 提交中击败了32.44%的用户 )
         *
         * @param num 非负整数
         * @return    每个数字二进制数中 1 的个数
         */
        public int[] countBits(int num) {
            int[] res = new int[num + 1];
            int count = 0;
            for (int i = 1; i < num + 1; i++) {
                int temp = i;
                while (temp  > 0) {
                    if ( (temp & 1) == 1) {
                        count++;
                    }
                    temp /= 2;
                }
                res[i] = count;
                count = 0;
            }
            return res;
        }

        /**
         * 动态规划
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.95%的用户)
         * (内存消耗：42.8 MB, 在所有 Java 提交中击败了13.52%的用户 )
         *
         * @param num 非负整数
         * @return    每个数字二进制数中 1 的个数
         */
        public int[] countBits1(int num) {
            int[] res = new int[num + 1];
            for (int i = 1; i < num + 1; i++) {
                // 对于每一个 i ， i / 2 的二进制数中 1 的个数都是计算过的
                // 只要再加上 i 对 2 的余数即可
                res[i] = res[i >> 1] + (i & 1);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int num = 5;
        System.out.println(Arrays.toString(new Solution().countBits(num)));
        System.out.println(Arrays.toString(new Solution().countBits1(num)));
    }
}

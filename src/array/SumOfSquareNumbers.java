package array;

/**
 * description 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a ^ 2 + b ^ 2 = c 。
 *
 * @author 27771
 * create 2021-04-28 09:30
 **/
public class SumOfSquareNumbers {

    static class Solution {

        /**
         * 暴力
         * (执行用时：12 ms, 在所有 Java 提交中击败了9.84%的用户)
         * (内存消耗：35.4 MB, 在所有 Java 提交中击败了21.90%的用户)
         *
         * @param c 非负整数
         * @return  否存在两个整数 a 和 b，使得 a ^ 2 + b ^ 2 = c
         */
        public boolean judgeSquareSum(int c) {
            if (Math.sqrt(c) % 1 == 0) {
                return true;
            }

            for (int a = 1; a * a <= c; a++) {
                if (Math.sqrt(c - a * a) % 1 == 0) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 当且仅当一个自然数的质因数分解中，满足 4k+3 形式的质数次方数均为偶数时，该自然数才能被表示为两个平方数之和。
         * (执行用时：1 ms, 在所有 Java 提交中击败了98.42%的用户)
         * (内存消耗：35.2 MB, 在所有 Java 提交中击败了61.57%的用户)
         *
         * @param c 非负整数
         * @return  否存在两个整数 a 和 b，使得 a ^ 2 + b ^ 2 = c
         */
        public boolean judgeSquareSum1(int c) {
            for (int base = 2; base * base <= c; base++) {
                int cnt = 0;
                while (c % base == 0) {
                    cnt++;
                    c /= base;
                }
                if (base % 4 == 3 && cnt % 2 != 0) {
                    return false;
                }
            }
            return c % 4 != 3;
        }
    }

    public static void main(String[] args) {
        int c = 2;
        System.out.println(new Solution().judgeSquareSum(c));
        System.out.println(new Solution().judgeSquareSum1(c));
    }
}

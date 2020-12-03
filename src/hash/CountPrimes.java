package hash;

import java.util.Arrays;

/**
 * description 统计所有小于非负整数 n 的质数的数量。
 *
 * @author 27771
 * create 2020-12-03 12:23
 **/
public class CountPrimes {

    static class Solution {

        /**
         * 暴力会超出时间限制
         *
         * @param n 非负整数
         * @return  所有小于非负整数 n 的质数的数量
         */
        public int countPrimes(int n) {
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime(i)) {
                    count++;
                }
            }
            return count;
        }

        /**
         * 判断是否为质数
         *
         * @param num 非负整数
         * @return    是否为质数
         */
        private boolean isPrime(int num) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 使用厄拉多塞筛法,基本方法是从2开始，把2作为素数，然后把N以内的所有2的倍数全部除去。
         * 此时下一个数字为3，将3作为素数，然后把N以内的所有3的倍数全部除去。
         * 一直这样下去，直到N^0.5，此时剩下的没有被除去的数全都是素数，把这些数和之前的所有素数相加即可得到结果。
         * (执行用时：17 ms, 在所有 Java 提交中击败了63.37%的用户)
         * (内存消耗：37 MB, 在所有 Java 提交中击败了65.93%的用户)
         *
         * @param n 非负整数
         * @return  所有小于非负整数 n 的质数的数量
         */
        public int countPrimes1(int n) {
            System.out.println("--");
            int count = 0;
            boolean[] notPrime = new boolean[n + 1];
            int i = 2;
            for (; i + i <= n; i++) {
                if (notPrime[i]) {
                    continue;
                }
                count++;
                // 所有倍数 notPrime 置为 true
                for (int j = 1; j * i <= n; j++) {
                    notPrime[j * i] = true;
                }
            }
            // 搜索剩余的素数
            for (; i < n; i++) {
                if (!notPrime[i]) {
                    count++;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(new Solution().countPrimes(n));
        System.out.println(new Solution().countPrimes1(n));
    }
}
package dynamicprogramming;

/**
 * description 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0，F(1) = 1 F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fibonacci-number 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-04 09:47
 **/
public class FibonacciNumber {

    static class Solution {

        /**
         * 递归
         * (执行用时：10 ms, 在所有 Java 提交中击败了17.23%的用户)
         * (内存消耗：35.4 MB, 在所有 Java 提交中击败了18.26%的用户)
         *
         * @param n n
         * @return  F(n)
         */
        public int fib(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            return fib(n - 1) + fib(n - 2);
        }

        int[] p = new int[31];

        /**
         * 动态规划
         * (执行用时：0 ms, 在所有 Java 提交中击败了100%的用户)
         * (内存消耗：35 MB, 在所有 Java 提交中击败了81.91%的用户)
         *
         * @param n n
         * @return  F(n)
         */
        public int fib1(int n) {
            int sum, first = 0, second = 1;
            for(int i = 0; i < n; i++){
                sum = first + second;
                first = second;
                second = sum;
            }
            return first;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(new Solution().fib(n));
        System.out.println(new Solution().fib1(n));
    }
}

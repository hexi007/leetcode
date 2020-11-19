package binarysearch;

/**
 * description 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * @author 27771
 * create 2020-11-19 09:34
 **/
public class Pow {

    static class Solution {
        
        /** 
         * 考虑 n 的正负
         * @param x 底数
         * @param n 幂数
         * @return  x 的 n 次幂
         */
        public double myPow(double x, int n) {
            return (long) n > 0 ? pow(x, n) : pow(x, -(long) n);
        }

        /**
         * 快速幂算法，直接把上一次的结果进行平方
         * @param x 底数
         * @param n 幂数
         * @return  x 的 n 次幂
         */
        private double pow(double x, long n) {
            if (n == 0) {
                return 1;
            }
            double res = pow(x, n / 2);
            return (n & 1) == 0 ? res * res : res * res * x;
        }

        /**
         * 快速幂非递归
         * @param x 底数
         * @param n 幂数
         * @return  x 的 n 次幂
         */
        double pow1(double x, long n) {
            double res = 1.0;
            // 贡献的初始值为 x
            double xContribute = x;
            // 在对 N 进行二进制拆分的同时计算答案
            while (n > 0) {
                if (n % 2 == 1) {
                    // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                    res *= xContribute;
                }
                // 将贡献不断地平方
                xContribute *= xContribute;
                // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
                n /= 2;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        double x = 2.00000;
        int n = 10;
        System.out.println(new Solution().myPow(x, n));
    }
}

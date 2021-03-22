package array;

/**
 * description 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 *
 * @author 27771
 * create 2021-03-22 09:23
 **/
public class NumberOfOneBits {

    static public class Solution {

        /**
         * n~\&~(n - 1)n & (n−1) 把 n 的二进制位中的最低位的 1 变为 0
         * (执行用时：1 ms, 在所有 Java 提交中击败了95.76%的用户)
         * (内存消耗：35.6 MB, 在所有 Java 提交中击败了9.44%的用户)
         *
         * @param n 无符号整数
         * @return  二进制表达式中数字位数为 '1' 的个数
         */
        public int hammingWeight(int n) {
            int ret = 0;
            while (n != 0) {
                n &= n - 1;
                ret++;
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        int n = 00000000000000000000000000001011;
        System.out.println(new Solution().hammingWeight(n));
    }
}
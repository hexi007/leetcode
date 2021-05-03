package array;

/**
 * description  给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * @author 27771
 * create 2021-05-03 12:09
 **/
public class ReverseInteger {

    static class Solution {

        /**
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35.6 MB, 在所有 Java 提交中击败了44.86%的用户)
         *
         * @param x 32 位的有符号整数
         * @return  将 x 中的数字部分反转后的结果
         */
        public int reverse(int x) {
            int rev = 0;
            while (x != 0) {
                if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                int digit = x % 10;
                x /= 10;
                rev = rev * 10 + digit;
            }
            return rev;
        }
    }

    public static void main(String[] args) {
        int x = 123;
        System.out.println(new Solution().reverse(x));
    }
}

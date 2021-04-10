package array;

/**
 * description 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/ugly-number 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-10 12:18
 **/
public class UglyNumber {

    static class Solution {

        /**
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35.5 MB, 在所有 Java 提交中击败了49.74%的用户)
         *
         * @param n 整数
         * @return  n 是否为 丑数
         */
        public boolean isUgly(int n) {
            if (n <= 0) {
                return false;
            }

            n = division(n, 2);
            n = division(n, 3);
            n = division(n, 5);

            return n == 1;
        }

        private int division(int n, int qualityFactor) {
            while (n % qualityFactor == 0) {
                n /= qualityFactor;
            }
            return n;
        }
    }

    public static void main(String[] args) {
        int n = 8;
        System.out.println(new Solution().isUgly(n));
    }
}

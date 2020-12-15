package greedyalgorithm;

/**
 * description 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * @author 27771
 * create 2020-12-15 09:40
 **/
public class MonotoneIncreasingDigits {

    static class Solution {

        /**
         * 从数的低位往高位找第一个逆序对，将逆序对左边数减一，右边数及其以后都变为 9
         * (执行用时：1 ms, 在所有 Java 提交中击败了97.96%的用户)
         * (内存消耗：35.5 MB, 在所有 Java 提交中击败了52.44%的用户)
         *
         * @param n 非负整数
         * @return  小于或等于 n 且单调递增的最大的整数
         */
        public int monotoneIncreasingDigits(int n) {
            // digit 从高位到地位保存数 n 的每一位
            // 官方使用 char[] strN = Integer.toString(N).toCharArray()， 效果都一样
            int[] digit = new int[9];
            // digitLen digit 数组长度
            int digitLen = 0, tempN = n;
            while (tempN > 0){
                digit[digitLen++] = tempN % 10;
                tempN = tempN / 10;
            }
            // 从 digit 右往左遍历，也就是从 n 的低位往高位遍历
            for (int i = digitLen - 1; i >= 1; i--) {
                // 找第一个逆序对
                if (digit[i] > digit[i - 1]) {
                    // 如果逆序对左边有重复，一直找到不重复的为止
                    while (i + 1 < digitLen && digit[i + 1] == digit[i]) {
                        i++;
                    }
                    // 逆序对左边数减一
                    digit[i] -= 1;
                    // 逆序对右边数及其以后都变为 9
                    i--;
                    while (i >= 0) {
                        digit[i] = 9;
                        i--;
                    }
                    // 计算最终结果
                    int res = 0;
                    for (i = digitLen - 1; i >= 0; i--) {
                        res = (res * 10) + digit[i];
                    }
                    return res;
                }
            }

            // 如果没有这样的逆序对，表示原数就是等于 n 且单调递增的最大的整数
            return n;
        }
    }

    public static void main(String[] args) {
        int n = 332;
        System.out.println(new Solution().monotoneIncreasingDigits(n));
    }
}

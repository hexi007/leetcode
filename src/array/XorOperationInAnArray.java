package array;

/**
 * description  给你两个整数，n 和 start 。
 * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
 * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
 *
 * @author 27771
 * create 2021-05-07 09:30
 **/
public class XorOperationInAnArray {

    static class Solution {

        /**
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35 MB, 在所有 Java 提交中击败了89.64%的用户)
         *
         * @param n     整数
         * @param start 整数
         * @return      nums 中所有元素按位异或（XOR）后得到的结果
         */
        public int xorOperation(int n, int start) {
            int temp = start;
            for (int i = 1; i < n; i++) {
                temp = temp ^ (start + 2 * i);
                System.out.println(temp + " " + (start + 2 * i));
            }
            return temp;
        }
    }

    public static void main(String[] args) {
        int n = 4, start = 3;
        System.out.println(new Solution().xorOperation(n, start));
    }
}

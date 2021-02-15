package array;

/**
 * description  给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * @author 27771
 * create 2021-02-15 09:17
 **/
public class MaxConsecutiveOnes {

    static class Solution {

        /**
         * (执行用时：2 ms, 在所有 Java 提交中击败了90.66%的用户)
         * (内存消耗：39.9 MB, 在所有 Java 提交中击败了74.95%的用户)
         *
         * @param nums 二进制数组
         * @return     最大连续1的个数
         */
        public int findMaxConsecutiveOnes(int[] nums) {
            int res = 0, count = 0;
            for (int num : nums) {
                if (num == 1) {
                    count++;
                } else {
                    res = Math.max(res, count);
                    count = 0;
                }
            }
            res = Math.max(res, count);
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        System.out.println(new Solution().findMaxConsecutiveOnes(nums));
    }
}

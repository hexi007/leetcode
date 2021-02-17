package array;

import java.util.Arrays;

/**
 * description 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对,
 * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 * 返回该 最大总和 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/array-partition-i 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-16 09:22
 **/
public class ArrayPartition {

    static class Solution {

        /**
         * (执行用时：5 ms, 在所有 Java 提交中击败了99.84%的用户)
         * (内存消耗：40.7 MB, 在所有 Java 提交中击败了16.26%的用户)
         *
         * @param nums 整数数组
         * @return     数对最大总和
         */
        public int arrayPairSum(int[] nums) {
            Arrays.sort(nums);
            int res = 0, i = 0;
            while (i < nums.length) {
                res += nums[i];
                i += 2;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {6,2,6,5,1,2};
        System.out.println(new Solution().arrayPairSum(nums));
    }
}

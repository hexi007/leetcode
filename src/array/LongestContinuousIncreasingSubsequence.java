package array;

/**
 * description 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-24 09:59
 **/
public class LongestContinuousIncreasingSubsequence {

    static class Solution {

        /**
         * 遍历数组统计递增子序列
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.83%的用户)
         * (内存消耗：39.4 MB, 在所有 Java 提交中击败了21.21%的用户)
         *
         * @param nums 未经排序的整数数组
         * @return     最长且连续递增的子序列长度
         */
        public int findLengthOfLCIS(int[] nums) {
            if(nums.length <= 1){
                return nums.length;
            }

            int res = 1, count  = 0, prev = Integer.MIN_VALUE;
            for (int num : nums) {
                if (num > prev) {
                    count++;
                } else {
                    res = Math.max(res, count);
                    // 除了第一次计算长度，之后每次都重置 count 为 1
                    count = 1;
                }
                prev = num;
            }
            res = Math.max(res, count);

            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,4,2,3,4,5};
        System.out.println(new Solution().findLengthOfLCIS(nums));
    }
}

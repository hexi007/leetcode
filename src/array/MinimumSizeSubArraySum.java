package array;

/**
 * description 给定一个含有 n 个正整数的数组和一个正整数 s <br/>
 * 找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。 <br/>
 * 如果不存在符合条件的子数组，返回 0。   <br/>
 *
 * @author 27771
 * create 2020-11-07 10:40
 **/
public class MinimumSizeSubArraySum {
    static class Solution {
        /**
         * 前缀和暴力法，非常蠢笨
         * (执行用时：634 ms, 在所有 Java 提交中击败了5.03%的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了90.11%的用户)
         * @param s    区间最小和值
         * @param nums 正整数数组
         * @return     符合条件的子数组长度
         */
        public int minSubArrayLen(int s, int[] nums) {
            if(nums == null || nums.length == 0){
                return 0;
            }
            int len = nums.length, min = Integer.MAX_VALUE;
            for (int i = 0; i < len; i++) {
                int temp = nums[i];
                if(temp >= s){
                    return 1;
                }
                for (int j = i + 1; j < len; j++) {
                    temp += nums[j];
                    //System.out.println(i + " " + j + " " + temp);
                    if(temp >= s){
                        min = Math.min(min, j - i + 1);
                    }
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        /**
         * 滑动窗口
         * (执行用时：2 ms, 在所有 Java 提交中击败了86.30%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了93.67%的用户)
         * @param s    区间最小和值
         * @param nums 正整数数组
         * @return     符合条件的子数组长度
         */
        public int minSubArrayLen1(int s, int[] nums) {
            if(nums == null || nums.length == 0){
                return 0;
            }
            int len = nums.length, min = Integer.MAX_VALUE;
            int start = 0, end = 0, sum = 0;
            while (end < len) {
                sum += nums[end];
                // 如果 sum 大于 s， 就从左缩小滑动窗口
                while (sum >= s) {
                    min = Math.min(min, end - start + 1);
                    // 先减去 nums[start]
                    sum -= nums[start];
                    // 再缩小滑动窗口
                    start++;
                }
                // 向右扩大滑动窗口
                end++;
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(new Solution().minSubArrayLen(s, nums));
        System.out.println(new Solution().minSubArrayLen1(s, nums));
    }
}

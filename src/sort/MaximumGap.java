package sort;

import java.util.Arrays;

/**
 * description 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * 如果数组元素个数小于 2，则返回 0。
 *
 * @author 27771
 * create 2020-11-26 09:30
 **/
public class MaximumGap {

    static class Solution {

        /**
         * 暴力法，库函数排序后求最大差值
         * (执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了60.00%的用户)
         *
         * @param nums 无序数组
         * @return     相邻元素之间最大的差值
         */
        public int maximumGap(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length == 1) {
                return 0;
            }
            Arrays.sort(nums);
            int maxGap = Integer.MIN_VALUE;
            for (int i = 1; i < nums.length; i++) {
                maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
            }
            return maxGap;
        }

        /**
         * 桶排序
         * (执行用时：3 ms, 在所有 Java 提交中击败了62.08%的用户）
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了73.68%的用户)
         *
         * @param nums 无序数组
         * @return     相邻元素之间最大的差值
         */
        public int maximumGap1(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length == 1) {
                return 0;
            }

            // 找出最小最大值
            int minNum = Integer.MAX_VALUE, maxNum = Integer.MIN_VALUE;
            for (int n : nums) {
                minNum = Math.min(minNum, n);
                maxNum = Math.max(maxNum, n);
            }
            // 元素全部相等返回 0
            if (minNum == maxNum) {
                return 0;
            }

            // 初始化桶
            int len = nums.length;
            // 只需要知道那些桶放了元素
            boolean[] bucket = new boolean[len];
            // 每个桶的最小元素
            int[] bucketMin = new int[len];
            Arrays.fill(bucketMin, Integer.MAX_VALUE);
            // 每个桶的最大元素
            int[] bucketMax = new int[len];
            Arrays.fill(bucketMax, Integer.MIN_VALUE);

            // 每个桶长度，向上取整
            int bucketLength = (int) Math.ceil((double)(maxNum - minNum) / (len - 1));
            for (int n : nums) {
                // 确定每个元素所在桶
                int location = (n - minNum) / bucketLength;
                // 更新桶
                bucket[location] = true;
                bucketMin[location] = Math.min(bucketMin[location], n);
                bucketMax[location] = Math.max(bucketMax[location], n);
            }

            // maxGap 最大差值， tempMax 前一个桶最大值
            int maxGap = Integer.MIN_VALUE, tempMax = Integer.MAX_VALUE;
            // 先找第一个 tempMax
            int i = 0;
            for (; i < len; i++) {
                if (bucket[i]) {
                    tempMax = bucketMax[i];
                    break;
                }
            }
            // 不断比较当前桶最小值和前一个桶最大值的差值,与 maxGap 比较
            for (i++; i < len; i++) {
                if (bucket[i]) {
                    maxGap = Math.max(maxGap, bucketMin[i] - tempMax);
                    // 更新前一个桶最大值
                    tempMax = bucketMax[i];
                }
            }

            return maxGap;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,5,5,5,5,5};
        System.out.println(new Solution().maximumGap(Arrays.copyOf(nums, nums.length)));
        System.out.println(new Solution().maximumGap1(Arrays.copyOf(nums, nums.length)));
    }
}

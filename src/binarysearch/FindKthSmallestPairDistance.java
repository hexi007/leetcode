package binarysearch;

import java.util.Arrays;

/**
 * description 给定一个整数数组，返回所有数对之间的第 k 个最小距离。
 * 一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 *
 * @author 27771
 * create 2020-12-16 10:06
 **/
public class FindKthSmallestPairDistance {

    static class Solution {

        /**
         * 二分查找
         * (执行用时：4 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了83.18%的用户)
         *
         * @param nums 整数数组
         * @param k    第 k 小
         * @return     有数对之间的第 k 个最小距离
         */
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int len = nums.length;
            // low high 为距离二分查找的区间
            int low = 0, high = nums[len - 1] - nums[0];

            while (low < high) {
                int mid = (low + high) >>> 1;
                // count 记录 nums 中有多少个小于等于 mid 距离的数对
                // left  遍历 nums 双指针中的左指针
                int count = 0, left = 0;
                // right 遍历 nums 双指针中的右指针
                for (int right = 0; right < len; right++) {
                    // nums[right] 和 nums[left] 距离大于 mid 就不断移动左指针
                    while (nums[right] - nums[left] > mid) {
                        left++;
                    }
                    // 这时 nums[right] 和 nums[left] 距离是小于等于 mid 的
                    // 从 left 到 right 之间有 right - left 个我们要的数对
                    count += right - left;
                }
                // 二分查找更改左右查找区间
                if (count >= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            return high;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,1};
        int k = 1;
        System.out.println(new Solution().smallestDistancePair(nums, k));
    }
}

package binarysearch;

import java.util.Arrays;

/**
 * description 给定一个包含 n + 1 个整数的数组 nums
 * 其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 *
 * @author 27771
 * create 2020-11-20 10:19
 **/
public class FindTheDuplicateNumber {

    static class Solution {

        /**
         * 先排序后查找,不符合题目不改动原数组的要求
         * (执行用时：4 ms, 在所有 Java 提交中击败了20.24%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了69.70%的用户)
         *
         * @param nums 一个包含 n + 1 个整数的数组 nums
         * @return     重复的数
         */
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            return find(nums, 0, nums.length - 1);
        }

        private int find(int[] nums, int left, int right) {
            if (left == right) {
                return 0;
            }
            int mid = (left + right) >>> 1;
            if (nums[mid] == nums[mid + 1]) {
                return nums[mid];
            }
            int findLeft = find(nums, left, mid);
            if (findLeft != 0) {
                return findLeft;
            }
            return find(nums, mid + 1, right);
        }

        /**
         * nums.length = n + 1,所以数都在 1 ~ n 中，设置查找范围 left 和 right，
         * 初始 left 为 1, right 为 n ,记录比中位数 mid 小的个数 count
         * 如果 count 比 中位数 mid 大，那么根据鸽笼原理重复的数在左边
         * 否在在右边找重复出现的数
         * (执行用时：3 ms, 在所有 Java 提交中击败了58.47%的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了88.09%
         * 的用户)
         *
         * @param nums 一个包含 n + 1 个整数的数组 nums
         * @return     重复的数
         */
        public int findDuplicate1(int[] nums) {
            int len = nums.length, left = 1, right = len - 1;
            while (left < right) {
                int mid = (left + right) >>> 1;

                // 记录比中位数 mid 小的个数 count
                int count = 0;
                for (int num : nums) {
                    if (num <= mid) {
                        count++;
                    }
                }

                // 如果 count 比 中位数 mid 大，那么根据鸽笼原理重复的数在左边
                // 否在在右边找重复出现的数
                if (count > mid) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,3,1,4,2};
        System.out.println(new Solution().findDuplicate(nums));
        System.out.println(new Solution().findDuplicate1(nums));
    }
}

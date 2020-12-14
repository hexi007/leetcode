package sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * description 给定一个整数数组，判断是否存在重复元素。
 * 如果任意一值在数组中出现至少两次，函数返回 true 。
 * 如果数组中每个元素都不相同，则返回 false 。
 *
 * @author 27771
 * create 2020-12-13 12:34
 **/
public class ContainsDuplicate {

    static class Solution {

        Set<Integer> set;

        /**
         * 直接使用 HashMap
         * (执行用时：9 ms, 在所有 Java 提交中击败了38.15%的用户)
         * (内存消耗：44.6 MB, 在所有 Java 提交中击败了38.14%的用户)
         *
         * @param nums 整数数组
         * @return     是否存在重复元素
         */
        public boolean containsDuplicate(int[] nums) {
            set = new HashSet<>();

            for (int num : nums) {
                if (!set.contains(num)) {
                    set.add(num);
                } else {
                    return true;
                }
            }

            return false;
        }

        /**
         * 利用归并排序找重复元素
         * (执行用时：3 ms, 在所有 Java 提交中击败了99.58%的用户)
         * (内存消耗：41.6 MB, 在所有 Java 提交中击败了94.01%的用户)
         *
         * @param nums 整数数组
         * @return     是否存在重复元素
         */
        public boolean containsDuplicate1(int[] nums) {
            return mergeSort(nums, 0, nums.length - 1);
        }

        /**
         * 递归调用自身并合并已经排好序的数组
         *
         * @param nums 整数数组
         * @param low  待排序数组开始位置
         * @param high 待排序数组结束位置
         * @return     是否存在重复元素
         */
        private boolean mergeSort(int[] nums, int low, int high) {
            int mid = (low + high) >>> 1;
            if (low < high) {
                // 排序过程中有重复元素直接返回
                if (mergeSort(nums, low, mid)) {
                    return true;
                }
                if (mergeSort(nums, mid + 1, high)) {
                    return true;
                }
                return merge(nums, low, mid, high);
            }
            return false;
        }

        /**
         * 合并排好序的数组
         *
         * @param nums 整数数组
         * @param low  待排序数组开始位置
         * @param mid  中间位置
         * @param high 待排序数组结束位置
         * @return     是否存在重复元素
         */
        private boolean merge(int[] nums, int low, int mid, int high) {
            int lowIndex = low, highIndex = mid + 1, tempIndex = 0;
            int[] tempArray = new int[high - low + 1];

            // 将较小的数保存在临时数组中
            while (lowIndex <= mid && highIndex <= high) {
                // 发现有重复就返回 true
                if (nums[lowIndex] == nums[highIndex]) {
                    return true;
                }
                if (nums[lowIndex] < nums[highIndex]) {
                    tempArray[tempIndex++] = nums[lowIndex++];
                } else {
                    tempArray[tempIndex++] = nums[highIndex++];
                }
            }

            // 如果左半部分还有剩余就将其保存到临时数组
            while (lowIndex <= mid) {
                tempArray[tempIndex++] = nums[lowIndex++];
            }

            // 如果右半部分还有剩余就将其保存到临时数组
            while (highIndex <= high) {
                tempArray[tempIndex++] = nums[highIndex++];
            }

            // 将临时数组写回 nums
            System.arraycopy(tempArray, 0, nums, low, tempArray.length);

            return false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,3};
        System.out.println(new Solution().containsDuplicate(Arrays.copyOf(nums, nums.length)));
        System.out.println(new Solution().containsDuplicate1(Arrays.copyOf(nums, nums.length)));
    }
}

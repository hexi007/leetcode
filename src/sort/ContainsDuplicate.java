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

        public boolean containsDuplicate1(int[] nums) {
            return mergeSort(nums, 0, nums.length - 1);
        }

        private boolean mergeSort(int[] nums, int low, int high) {
            int mid = (low + high) >>> 1;

            return false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(new Solution().containsDuplicate(Arrays.copyOf(nums, nums.length)));
        System.out.println(new Solution().containsDuplicate1(Arrays.copyOf(nums, nums.length)));
    }
}

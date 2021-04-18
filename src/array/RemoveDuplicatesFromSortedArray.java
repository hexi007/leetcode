package array;

/**
 * description  给你一个有序数组 nums ，请你 原地 删除重复出现的元素，
 * 使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author 27771
 * create 2021-04-18 15:11
 **/
public class RemoveDuplicatesFromSortedArray {

    static class Solution {

        /**
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.6 MB, 在所有 Java 提交中击败了99.67%的用户)
         *
         * @param nums 有序数组
         * @return     删除重复后数组的新长度
         */
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int index = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[index]) {
                    nums[++index] = nums[i];
                }
            }
            return index + 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int len = new Solution().removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }
}

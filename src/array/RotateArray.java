package array;

import java.util.Arrays;

/**
 * description 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * @author 27771
 * create 2021-01-08 11:07
 **/
public class RotateArray {

    static class Solution {

        /**
         * 翻转前 len - k - 1 部分，再翻转后 k 部分，再整体翻转
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39 MB, 在所有 Java 提交中击败了64.03%的用户)
         *
         * @param nums 数组
         * @param k    右移动 k 个位置
         */
        public void rotate(int[] nums, int k) {
            int len = nums.length;
            // 确保 k 在 0 到 len 之间
            k = k % len;
            reverse(nums, 0, len - k - 1);
            reverse(nums, len - k, len - 1);
            reverse(nums, 0, len - 1);
        }

        /**
         * 翻转部分数组
         *
         * @param nums  数组
         * @param start 翻转起始位置
         * @param end   翻转结束位置
         */
        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        new Solution().rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}

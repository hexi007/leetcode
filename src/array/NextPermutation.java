package array;

import java.util.Arrays;

/**
 * description 实现获取下一个排列的函数 <br/>
 * 算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 <br/>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。  <br/>
 * 必须原地修改，只允许使用额外常数空间。   <br/>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。  <br/>
 * 1,2,3 → 1,3,2  <br/>
 * 3,2,1 → 1,2,3  <br/>
 * 1,1,5 → 1,5,1  <br/>
 *
 * @author 27771
 * create 2020-11-10 09:37
 **/
public class NextPermutation {

    static class Solution {
        /**
         * 关键思想是先找一个逆序对，逆序对左边就是要找的较小数          <br/>
         * 从右边开始找一个大于最小数的所有数中最小的数，这个就是较大数   <br/>
         * 交换最小数和最大数                                      <br/>
         * 因为开始时一直想找逆序对，所以逆序对右边都是降序的           <br/>
         * 为了使修改后增加的幅度尽可能的小，将右边翻转即可升序排列      <br/>
         * (执行用时：1 ms, 在所有 Java 提交中击败了98.72%的用户)    <br/>
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了87.13%的用户) <br/>
         * @param nums 待排列数组
         */
        public void nextPermutation(int[] nums) {
            int i = nums.length - 1;
            for (; i > 0; i--) {
                // 不断寻找逆序对
                if (nums[i - 1] < nums[i]) {
                    int j = i;
                    // 找到一个比最小数相等或者要小的数
                    for (; j < nums.length; j++) {
                        if  (nums[j] <= nums[i - 1]) {
                            // 交换最小数和最大数
                            swap(nums, i - 1, j - 1);
                            // 翻转右侧
                            reverse(nums, i , nums.length - 1);
                            return;
                        }
                    }
                    // 有可能最小数右侧所有数都比最小数大
                    if (j == nums.length) {
                        // 最小数和最后一个互换
                        swap(nums, i - 1, j - 1);
                        // 翻转右侧
                        reverse(nums, i , nums.length - 1);
                        return;
                    }
                }
            }
            // 没有逆序对，说明整个数组都是降序那么也就是最大排列，翻转整个数组即可
            if ( i== 0) {
                reverse(nums, 0 , nums.length - 1);
            }
        }

        /**
         * 从 i 到 j 翻转数组
         * @param nums 数组
         * @param i    起始位置
         * @param j    终止位置
         */
        private void reverse(int[] nums, int i, int j) {
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        /**
         * 交换元素
         * @param nums 数组
         * @param i    元素之一下标
         * @param j    另一个元素下标
         */
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,5,2,6,3,1};
        new Solution().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        int[] nums1 = {3,2,1};
        new Solution().nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));
        int[] nums2 = {1,1,5};
        new Solution().nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}

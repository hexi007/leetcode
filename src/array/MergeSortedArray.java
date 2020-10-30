package array;

import java.util.Arrays;

/**
 * description 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * @author 27771
 * create 2020-10-30 11:03
 **/
public class MergeSortedArray {
    static class Solution {
        /**
         * 暴力法
         * @param nums1 数组 1
         * @param m 数组 1 长度
         * @param nums2 数组 2
         * @param n 数组 2 长度
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for(int i = m ,j = 0; i < m + n; i++){
                nums1[i] = nums2[j++];
            }
            Arrays.sort(nums1);
        }

        /**
         * 双指针从后往前，将较大的填入
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了91.61% 的用户)
         * @param nums1 数组 1
         * @param m 数组 1 长度
         * @param nums2 数组 2
         * @param n 数组 2 长度
         */
        public void merge1(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1 ,j = n - 1, k = m + n - 1;
            while ( i >= 0 && j >= 0){
                nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
            }
            System.arraycopy(nums2, 0 , nums1, 0, j + 1);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        new Solution().merge1(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));

    }
}
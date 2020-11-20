package binarysearch;

/**
 * description 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的中位数。
 *
 * @author 27771
 * create 2020-11-20 11:19
 **/
public class MedianOfTwoSortedArrays {

    static class Solution {

        int[] all;
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            all = new int[200001];
            for (int num : nums1) {
                all[num + 100000]++;
            }
            for (int num : nums2) {
                all[num + 100000]++;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,3};
        System.out.println(new Solution().findMedianSortedArrays(nums1, nums2));
    }
}

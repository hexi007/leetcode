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

        /**
         * 二分查找，使用找第 K 小的数找中位数
         * (执行用时：3 ms, 在所有 Java 提交中击败了79.22%的用户)
         * (内存消耗：40 MB, 在所有 Java 提交中击败了32.24%的用户)
         *
         * @param nums1 数组 1
         * @param nums2 数组 2
         * @return      两个正序数组的中位数
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            // left 中位数靠左的数 right 中位数靠右的数
            int left = (n + m + 1) / 2;
            int right = (n + m + 2) / 2;
            //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
            return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) +
                    getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
        }

        /**
         * 两个数组中查找第 k 小的数
         * @param nums1  数组 1
         * @param start1 数组 1 查找左边界
         * @param end1   数组 1 查找右边界
         * @param nums2  数组 2
         * @param start2 数组 2 左边界
         * @param end2   数组 2 右边界
         * @param k      第 K 小
         * @return       两个数组中第 k 小的数
         */
        private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            int len1 = end1 - start1 + 1;
            int len2 = end2 - start2 + 1;
            //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
            if (len1 > len2) {
                return getKth(nums2, start2, end2, nums1, start1, end1, k);
            }
            // len1 为 0，则中位数一定在 nums2 中
            if (len1 == 0) {
                return nums2[start2 + k - 1];
            }
            // 要找第 1 小的数字，所以只需判断两个数组中第一个数字哪个小就可以
            if (k == 1) {
                return Math.min(nums1[start1], nums2[start2]);
            }

            // 比较位置
            int i = start1 + Math.min(len1, k / 2) - 1;
            int j = start2 + Math.min(len2, k / 2) - 1;
            // 不断舍弃比较小的区间
            if (nums1[i] > nums2[j]) {
                return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
            }
            else {
                return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,3};
        System.out.println(new Solution().findMedianSortedArrays(nums1, nums2));
    }
}

package sort;

import java.util.Arrays;

/**
 * description 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/merge-sorted-array 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-05 10:16
 **/
public class MergeSortedArray {

    static class Solution {

        /**
         * 从末尾往前排，先选大的
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.8 MB, 在所有 Java 提交中击败了10.12%的用户)
         *
         * @param nums1 有序整数数组 1
         * @param m     nums1 元素数量
         * @param nums2 有序整数数组 2
         * @param n     nums2 元素数量
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (n == 0) {
                return;
            }

            int index = nums1.length - 1;
            m--;
            n--;
            while (m >= 0 && n >= 0) {
                if (nums1[m] > nums2[n]) {
                    nums1[index--] = nums1[m--];
                } else {
                    nums1[index--] = nums2[n--];
                }
            }

//            // 不需要考虑 m 还有的情况， 若 m >= 0 ,那么 nums1 剩下的都比 nums2 小而又都在数组里
//            while (m >= 0) {
//                nums1[index--] = nums1[m];
//                m--;
//            }

            while (n >= 0) {
                nums1[index--] = nums2[n];
                n--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {0}, nums2 = {1};
        int m = 0,  n = 1;
        new Solution().merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}

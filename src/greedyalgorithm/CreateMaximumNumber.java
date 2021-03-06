package greedyalgorithm;

import java.util.Arrays;

/**
 * description 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * @author 27771
 * create 2020-12-02 10:54
 **/
public class CreateMaximumNumber {

    static class Solution {

        /**
         * (执行用时：8 ms, 在所有 Java 提交中击败了97.00%的用户)
         * (内存消耗：39.4 MB, 在所有 Java 提交中击败了27.47%的用户)
         *
         * @param nums1 数组 1
         * @param nums2 数组 2
         * @param k     最大长度
         * @return      表示最大数的长度为 k 的数组
         */
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            int m = nums1.length, n = nums2.length;
            // 最大序列
            int[] maxSubsequence = new int[k];
            // start,end 含义是指从 nums1 中可取数的范围
            // 如果 k > n,则 nums1 至少要取 k - n 个
            // 同时如果 k > m， 那 nums1 最多也就能取 m 个数
            int start = Math.max(0, k - n), emd = Math.min(k, m);
            for (int i = start; i <= emd; i++) {
                // nums1 中选 i 个数
                int[] subSequence1 = maxSubsequence(nums1, i);
                // nums2 中选 k - i 个数
                int[] subSequence2 = maxSubsequence(nums2, k - i);
                // 合并 subSequence1，subSequence2
                int[] currSubSequence = merge(subSequence1, subSequence2);
                // 比较是否比 maxSubsequence 大
                if (compare(currSubSequence, 0, maxSubsequence, 0) > 0) {
                    System.arraycopy(currSubSequence, 0, maxSubsequence, 0, k);
                }
            }
            return maxSubsequence;
        }

        /**
         * 单调递增栈从数组中选 k 个数，使得 k 个数序列最大
         *
         * @param nums 数组
         * @param k    k 个数
         * @return     选出序列最大的 k 个数
         */
        private int[] maxSubsequence(int[] nums, int k) {
            int length = nums.length;
            int[] stack = new int[k];
            int stackIndex = -1;
            int remain = length - k;
            for (int num : nums) {
                // remain 确保还有足够的数可以入栈
                while (stackIndex >= 0 && stack[stackIndex] < num && remain > 0) {
                    stackIndex--;
                    remain--;
                }
                if (stackIndex < k - 1) {
                    stack[++stackIndex] = num;
                } else {
                    remain--;
                }
            }
            return stack;
        }

        /**
         * 合并两个序列
         *
         * @param subSequence1 序列 1
         * @param subSequence2 序列 2
         * @return             合并后序列
         */
        private int[] merge(int[] subSequence1, int[] subSequence2) {
            int x = subSequence1.length, y = subSequence2.length;
            if (x == 0) {
                return subSequence2;
            }
            if (y == 0) {
                return subSequence1;
            }
            int mergeLength = x + y;
            int[] merged = new int[mergeLength];
            int index1 = 0, index2 = 0;
            for (int i = 0; i < mergeLength; i++) {
                // 选两个序列当前位置最大的元素
                if (compare(subSequence1, index1, subSequence2, index2) > 0) {
                    merged[i] = subSequence1[index1++];
                } else {
                    merged[i] = subSequence2[index2++];
                }
            }
            return merged;
        }

        /**
         * 比较两个序列
         * @param subSequence1 序列 1
         * @param index1       序列 1 当前位置
         * @param subSequence2 序列 2
         * @param index2       序列 2 当前位置
         * @return             序列 1 是否比 序列 2 大
         */
        private int compare(int[] subSequence1, int index1, int[] subSequence2, int index2) {
            int x = subSequence1.length, y = subSequence2.length;
            while (index1 < x && index2 < y) {
                int differ = subSequence1[index1] - subSequence2[index2];
                if (differ != 0) {
                    return differ;
                }
                index1++;
                index2++;
            }
            // 有一个序列位置到了末尾
            return (x - index1) - (y - index2);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {3,4,5,6};
        int[] nums2 = {9,1,2,5,8,3};
        int k = 5;
        int[] res = new Solution().maxNumber(nums1, nums2, k);
        System.out.println(Arrays.toString(res));
    }
}

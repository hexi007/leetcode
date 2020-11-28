package sort;

/**
 * description 给定一个数组 nums
 * 如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * @author 27771
 * create 2020-11-28 10:12
 **/
public class ReversePairs {

    static class Solution {

        /**
         * 归并排序求重要翻转对
         * (执行用时：57 ms, 在所有 Java 提交中击败了69.06%的用户)
         * (内存消耗：48.7 MB, 在所有 Java 提交中击败了32.26%的用户)
         *
         * @param nums 数组
         * @return     重要翻转对的数量
         */
        public int reversePairs(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            // numsSorted 保存中间排序结果
            int[] numsSorted = new int[nums.length];
            return mergeSort(nums, numsSorted, 0, nums.length - 1);
        }

        /**
         * 归并排序
         *
         * @param nums       数组
         * @param numsSorted 中间排序数组
         * @param left       左边界
         * @param right      右边界
         * @return           排序后重要翻转对数量
         */
        private int mergeSort(int[] nums, int[] numsSorted, int left, int right) {
            if (left == right) {
                return 0;
            }
            int mid = (left + right) >>> 1;
            // 递归求解左右两边并求跨域重要翻转对数量
            int res = mergeSort(nums, numsSorted, left, mid) +
                    mergeSort(nums, numsSorted, mid + 1, right) +
                    findReversedPairs(nums, left, right);

            // 合并两个有序数组，分别从 left , mid + 1 开始
            // numsSorted 保存中间排序结果，从 left 开始
            int i = left, j = mid + 1, k = left;
            while (i <= mid && j <= right) {
                // 哪个比较小，numsSorted 就先保存哪一个
                if (nums[i] <= nums[j]) {
                    numsSorted[k++] = nums[i++];
                } else {
                    numsSorted[k++] = nums[j++];
                }
            }
            // 左边有剩余
            while (i <= mid) {
                numsSorted[k++] = nums[i++];
            }
            // 右边有剩余
            while (j <= right) {
                numsSorted[k++] = nums[j++];
            }
            // 将 numsSorted 写回 nums
            if (right + 1 - left >= 0) {
                System.arraycopy(numsSorted, left, nums, left, right + 1 - left);
            }
            return res;
        }

        /**
         * 比较有序数组的重要翻转对数量
         * @param nums  有序数组
         * @param left  左边界
         * @param right 右边界
         * @return      重要翻转对数量
         */
        private int findReversedPairs(int[] nums, int left, int right) {
            // 从中间分为两个子数组
            int res = 0, mid = (left + right) >>> 1, multiple = 2;
            // 如果 nums[i] > 2 * (long)nums[j] ，也就是说从 nums[i] 到 nums[mid] 都和 nums[j] 构成了重要翻转对
            // 那么 共出现 mid - i + 1 个重要翻转对，此时考虑 j++ ,如果 nums[i] 和 nums[j] 同样是重要翻转对
            // 则重复上述步骤
            // 否则 nums[i] 无论如何也不可能可从 nums[j] 到 nums[right] 中找到重要翻转对
            // 此时考虑 i++， 重复最开始的步骤
            for (int i = left, j = mid + 1; i <= mid; i++) {
                while (j <= right && (long)nums[i] > multiple * (long)nums[j]) {
                    res += mid - i + 1;
                    j++;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,4,3,5,1};
        System.out.println(new Solution().reversePairs(nums));
    }
}

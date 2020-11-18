package binarysearch;

/**
 * description 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * @author 27771
 * create 2020-11-18 10:16
 **/
public class FindPeakElement {

    static class Solution {

        /**
         * 二分查找注意边界问题
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.1 MB, 在所有 Java 提交中击败了82.42%的用户)
         * @param nums 数组
         * @return     返回峰值所在下标
         */
        public int findPeakElement(int[] nums) {
            // 考虑数组长度为 1 或 2 的情况
            if (nums.length == 1) {
                return 0;
            }
            int two = 2;
            if (nums.length == two) {
                return nums[0] > nums[1] ? 0 : 1;
            }
            int left = 0, right = nums.length - 1;
            // 保证查找空间至少有两个元素
            while (left < right) {
                int mid = (left + right) >>> 1;
                // 此时边界一定是峰值
                if (mid == 0 || mid == nums.length - 1) {
                    return mid;
                }
                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                    return mid;
                }
                // 比较 mid 左右两端，峰值在较大的那一边
                if (nums[mid - 1] <= nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1};
        System.out.println(new Solution().findPeakElement(nums));
    }
}

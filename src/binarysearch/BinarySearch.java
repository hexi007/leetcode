package binarysearch;

/**
 * description 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * @author 27771
 * create 2020-11-17 10:33
 **/
public class BinarySearch {
    static class Solution {
        /**
         * 递归的二分查找
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.5 MB, 在所有 Java 提交中击败了75.97%的用户)
         * @param nums   元素有序的（升序）整型数组 nums
         * @param target 目标值
         * @return       标值存在为该元素下标，否则为 -1
         */
        public int search(int[] nums, int target) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        private int binarySearch(int[] nums, int target, int left, int right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            // 没找到
            if (left > right) {
                return -1;
            }
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                return binarySearch(nums, target, mid + 1, right);
            } else {
                return binarySearch(nums, target, left, mid - 1);
            }
        }

        /**
         * 非递归的二分查找
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.5 MB, 在所有 Java 提交中击败了77.37%的用户)
         * @param nums   元素有序的（升序）整型数组 nums
         * @param target 目标值
         * @return       标值存在为该元素下标，否则为 -1
         */
        public int search1(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                // int mid = (right + left) >>> 1 无符号右移也是可以的
                // 不过牺牲了可读性
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return  -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 100;
        System.out.println(new Solution().search(nums, target));
        System.out.println(new Solution().search1(nums, target));
    }
}

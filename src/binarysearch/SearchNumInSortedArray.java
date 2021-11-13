package binarysearch;

/**
 * description 统计一个数字在排序数组中出现的次数。
 *
 * @author 27771
 * create 2021-11-13 15:41
 **/
public class SearchNumInSortedArray {

    static class Solution {
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (nums[right] != target) {
                return 0;
            }
            int leftBound = right;
            left = 0; right = nums.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2 + 1;
                if (nums[mid] <= target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            int rightBound = right;
            return rightBound - leftBound + 1;
        }

        public int search1(int[] nums, int target) {
            return searchRightBound(nums, target) - searchRightBound(nums, target - 1);
        }

        private int searchRightBound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return right;
        }
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(new Solution().search(nums, target));
        System.out.println(new Solution().search1(nums, target));
    }
}

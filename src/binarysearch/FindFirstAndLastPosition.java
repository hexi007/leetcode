package binarysearch;

import java.util.Arrays;

/**
 * description 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * @author 27771
 * create 2020-11-18 10:59
 **/
public class FindFirstAndLastPosition {

    static class Solution {
        int start = -1, end = -1;
        public int[] searchRange(int[] nums, int target) {

            int two = 2;
            if ( nums.length <= two) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == target) {
                        if (start == -1) {
                            start = i;
                        }
                        end = i;
                    }
                }
                return new int[] {start, end};
            }

            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (nums[mid] == target) {
                    //System.out.println(mid);
                    start = mid;
                    end = mid;
                    findStart(nums, target, left, mid);
                    findEnd(nums, target, mid, right);
                    return new int[] {start, end};
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return new int[] {start, end};
        }

        private void findStart(int[] nums, int target, int left, int right) {
            if (left > right) {
                return;
            }
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                boolean find =mid - 1 < left || (mid - 1 >= left && nums[mid - 1] != target);
                if (find) {
                    start = mid;
                } else {
                    findStart(nums, target, left, mid - 1);
                }
            } else {
                findStart(nums, target, mid + 1, right);
            }
        }

        private void findEnd(int[] nums, int target, int left, int right) {
            if (left > right) {
                return;
            }
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                boolean find = mid + 1 > right || (mid + 1 <= right && nums[mid + 1] != target);
                if (find) {
                    end = mid;
                } else {
                    findEnd(nums, target, mid + 1, right);
                }
            } else {
                findEnd(nums, target, left, mid - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        //int[] nums = {8,8};
        int target = 8;
        int[] res = new Solution().searchRange(nums, target);
        System.out.println(Arrays.toString(res));
    }
}

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
        public int[] searchRange(int[] nums, int target) {
            int begin = -1, end = -1;
            if (nums.length <= 2) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == target) {
                        if (begin == -1) {
                            begin = i;
                        }
                        end = i;
                    }
                }
                return new int[]{begin, end};
            }

            int left = 0, right = nums.length - 1;
            while (left + 1 < right) {
                int mid = (left + right) >>> 1;
                if (nums[mid] == target) {
                    int temp = mid;
                    while (temp - 1 >= 0 && nums[temp - 1] == target) {
                        temp--;
                    }
                    begin = temp;
                    temp = mid;
                    if (temp < nums.length && nums[temp] == target) {
                        temp++;
                    }
                    end = temp;
                    System.out.println(left + " " + right + " " + mid + " " + begin + " " + end);
                    return new int[]{begin, end};
                } else if (nums[mid] < target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }

            if (nums[left] == target) {
                return new int[]{left, left};
            } else if (nums[right] == target){
                return new int[]{right, right};
            }
            return new int[]{begin, end};
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

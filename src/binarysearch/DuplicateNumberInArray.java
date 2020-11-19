package binarysearch;

/**
 * description 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 *
 * @author 27771
 * create 2020-11-19 11:07
 **/
public class DuplicateNumberInArray {

    static class Solution {

        /**
         * 空间换时间
         * (执行用时：2 ms, 在所有 Java 提交中击败了68.30%的用户)
         * (内存消耗：46.5 MB, 在所有 Java 提交中击败了59.98%的用户)
         * @param nums 数组
         * @return     数组中任意一个重复的数字
         */
        public int findRepeatNumber(int[] nums) {
            int[] map = new int[100001];
            for (int num : nums) {
                if (map[num] == 1) {
                    return num;
                }
                map[num]++;
            }
            return -1;
        }

        /** 
         * 如果没有重复数字，那么正常排序后，数字 i 应该在下标为 i 的位置.
         * 所以思路是重头扫描数组，遇到下标为i的数字如果不是i的话，（假设为m),那么我们就拿与下标m的数字交换。
         * 在交换过程中，如果有重复的数字发生，那么终止返回ture
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：46.2 MB, 在所有 Java 提交中击败了82.73%的用户)
         * @param nums 数组
         * @return     数组中任意一个重复的数字
         */
        public int findRepeatNumber1(int[] nums) {
            for(int i = 0; i < nums.length; i++){
                while (nums[i] != i){
                    if(nums[i] == nums[nums[i]]){
                        return nums[i];
                    }
                    int temp = nums[i];
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,4,5,1,3,1};
        System.out.println(new Solution().findRepeatNumber(nums));
        System.out.println(new Solution().findRepeatNumber1(nums));
    }
}

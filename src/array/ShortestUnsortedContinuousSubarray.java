package array;

import java.util.Arrays;

/**
 * description 给定一个整数数组，你需要寻找一个连续的子数组
 * 如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * @author 27771
 * create 2020-11-25 10:35
 **/
public class ShortestUnsortedContinuousSubarray {

    static class Solution {

        /**
         * 排序后左右两端比较
         * (执行用时：10 ms, 在所有 Java 提交中击败了23.05%的用户)
         * (内存消耗：39.9 MB, 在所有 Java 提交中击败了66.61%的用户)
         *
         * @param nums 整数数组
         * @return     最长子数组长度
         */
        public int findUnsortedSubarray(int[] nums) {
            int[] temp = new int[nums.length];
            System.arraycopy(nums, 0, temp, 0, nums.length);
            Arrays.sort(temp);
            int left = 0;
            for(; left < temp.length; left++) {
                if(temp[left] != nums[left]) {
                    break;
                }
            }
            int right = temp.length - 1;
            for(; right >= 0; right--) {
                if(temp[right] != nums[right]) {
                    break;
                }
            }
            return right > left ? right - left + 1 : 0;
        }

        /**
         * 手动两端比较，效果更差
         * (执行用时：239 ms, 在所有 Java 提交中击败了8.12%的用户）
         * （内存消耗：40.3 MB, 在所有 Java 提交中击败了13.06%的用户)
         *
         * @param nums 整数数组
         * @return     最长子数组长度
         */
        public int findUnsortedSubarray1(int[] nums) {
            int left = 0;
            boolean isBreak = false;
            for (; left < nums.length; left++) {
                int i = left + 1;
                for (; i < nums.length; i++) {
                    if (nums[i] < nums[left]){
                        isBreak = true;
                        break;
                    }
                }
                if (isBreak) {
                    break;
                }
            }
            int right = nums.length - 1;
            isBreak = false;
            for (; right >= 0; right--) {
                int i = right - 1;
                for (; i >= 0; i--) {
                    if (nums[i] > nums[right]) {
                        isBreak = true;
                        break;
                    }
                }
                if (isBreak) {
                    break;
                }
            }
            return right > left ? right - left + 1 : 0;
        }

        /**
         * 使用前缀和
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.3 MB, 在所有 Java 提交中击败了98.42%的用户)
         *
         * @param nums 整数数组
         * @return     最长子数组长度
         */
        public int findUnsortedSubarray2(int[] nums) {
            int[] leftMax = new int[nums.length];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                max = Math.max(nums[i], max);
                leftMax[i] = max;
            }
            int[] rightMin = new int[nums.length];
            int min = Integer.MAX_VALUE;
            for (int i = nums.length-1; i >= 0; i--) {
                min = Math.min(nums[i], min);
                rightMin[i] = min;
            }
            int leftIndex = -1;
            for (int i = 0; i < nums.length; i++) {
                if (i+1<nums.length && nums[i]<=rightMin[i+1]){
                    leftIndex++;
                }else {
                    break;
                }
            }
            int rightIndex = nums.length;
            for (int i = nums.length-1; i >=0 ; i--) {
                if (i>0 && nums[i]>=leftMax[i-1]){
                    rightIndex--;
                }else {
                    break;
                }
            }
            return leftIndex == nums.length - 2 ? 0 : rightIndex - leftIndex-1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2};
        System.out.println(new Solution().findUnsortedSubarray(nums));
        System.out.println(new Solution().findUnsortedSubarray1(nums));
        System.out.println(new Solution().findUnsortedSubarray2(nums));
    }
}

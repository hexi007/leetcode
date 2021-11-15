package array;

/**
 * description 给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
 * 1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
 * 2.假设 nums[-1] = nums[n] = −∞
 * 3.对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * 4.你可以使用O(logN)的时间复杂度实现此问题吗？
 *
 * @author 27771
 * create 2021-11-15 16:02
 **/
public class FindPeak {

    static class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         *
         * @param nums int整型一维数组
         * @return int整型
         */
        public int findPeakElement (int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int leftNum = mid != 0 ? nums[mid - 1] : Integer.MIN_VALUE;
                int rightNum = mid != nums.length - 1 ? nums[mid + 1] : Integer.MIN_VALUE;
                if (nums[mid] > leftNum && nums[mid] > rightNum) {
                    return mid;
                }
                if (leftNum > rightNum) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 6};
        System.out.println(new Solution().findPeakElement(nums));
    }
}
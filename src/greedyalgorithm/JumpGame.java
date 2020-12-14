package greedyalgorithm;

/**
 * description 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * @author 27771
 * create 2020-12-14 16:26
 **/
public class JumpGame {

    static class Solution {

        /**
         * 每次确定最大可跳跃位置
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.97%的用户)
         * (内存消耗：40.5 MB, 在所有 Java 提交中击败了58.75%的用户)
         *
         * @param nums 负整数数组
         * @return     是否能够到达最后一个位置
         */
        public boolean canJump(int[] nums) {
            // maxJump 最大可跳跃位置
            int maxJump = 0;
            for (int i = 0; i < nums.length; i++) {
                int tempJump = i + nums[i];
                if (i <= maxJump && tempJump > maxJump) {
                    maxJump = tempJump;
                }
                if (maxJump >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(new Solution().canJump(nums));
    }
}
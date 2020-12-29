package greedyalgorithm;

/**
 * description 给定一个已排序的正整数数组 nums，和一个正整数 n 。
 * 从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
 * 请输出满足上述要求的最少需要补充的数字个数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/patching-array 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2020-12-29 15:40
 **/
public class PatchingArray {

    static class Solution {

        /**
         * 如果 [1, x - 1] 被全部覆盖且 x 在数组中，则 [1, 2 * x - 1]也全部被覆盖
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：37.9 MB, 在所有 Java 提交中击败了87.37%的用户)
         *
         * @param nums 正整数数组
         * @param n    正整数
         * @return     最少需要补充的数字个数
         */

        public int minPatches(int[] nums, int n) {
            int len = nums.length;
            // bound 已经完全覆盖的数组右边界
            long bound = 1;
            // 记录补充到 nums 中的次数
            int count = 0;
            int index = 0;

            // 完全覆盖的数组右边界小于等于 n 时继续循环
            while (bound <= n) {
                // 如果 nums[index] <= bound 则表示当前 nums[index] 在已覆盖数组中
                // 此时已完全覆盖的数组右边界 bound 扩大为 bound + nums[index], index++
                if (index < len && nums[index] <= bound) {
                    bound += nums[index];
                    index++;
                } else {
                    // 不满足条件则扩大已完全覆盖的数组右边界为原来的二倍
                    bound *= 2;
                    // 相当于添加了一个数到 num 中，计数加一
                    count++;
                }
            }

            return count;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,5,10};
        int n = 20;
        System.out.println(new Solution().minPatches(nums, n));
    }
}
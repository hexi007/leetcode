package array;

/**
 * description 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/non-decreasing-array 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-07 09:41
 **/
public class NonDecreasingArray {

    static class Solution {

        /**
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.54%的用户)
         * (内存消耗：39.9 MB, 在所有 Java 提交中击败了45.21%的用户)
         *
         * @param nums 整数数组
         * @return     在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列
         */
        public boolean checkPossibility(int[] nums) {
            if (nums.length < 3) {
                return true;
            }

            // 改变数字次数
            int changes = 0;
            // 前两个数字不符合非递减定义，那么第一个数要等于第二个
            if (nums[0] > nums[1]) {
                nums[0] = nums[1];
                changes++;
            }

            for (int i = 1; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    changes++;
                    if (changes > 1) {
                        return false;
                    }
                    if (nums[i - 1] > nums[i + 1]) {
                        // nums[i - 1], nums[i], nums[i + 1] 类似于 2, 3, 1 的情况
                        // 那么让 nums[i + 1] 变为 nums[i]
                        nums[i + 1] = nums[i];
                    } else {
                        // nums[i - 1], nums[i], nums[i + 1] 类似于 1，3，2的情况
                        // 那么让 nums[i] 变为 nums[i - 1]
                        nums[i] = nums[i - 1];
                    }
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,2,3};
        System.out.println(new Solution().checkPossibility(nums));
    }
}

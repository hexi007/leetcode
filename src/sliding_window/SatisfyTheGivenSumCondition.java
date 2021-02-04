package sliding_window;

import java.util.Arrays;

/**
 * description 给你一个整数数组 nums 和一个整数 target 。
 * 请你统计并返回 nums 中能满足其最小元素与最大元素的 和 小于或等于 target 的 非空 子序列的数目。
 * 由于答案可能很大，请将结果对 10^9 + 7 取余后返回。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/
 * number-of-subsequences-that-satisfy-the-given-sum-condition 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-03 12:17
 **/
public class SatisfyTheGivenSumCondition {

    static class Solution {
        public int numSubsequences(int[] nums, int target) {
            Arrays.sort(nums);
            int left = 0, right = 0, len = nums.length;

            return 0;
        }

        public static void main(String[] args) {
            int[] num = {2,3,3,4,6,7};
            int target = 12;
            System.out.println(new Solution().numSubsequences(num, target));
        }
    }


}

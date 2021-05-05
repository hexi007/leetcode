package dynamicprogramming;

import java.util.Arrays;

/**
 * description 给你一个整数数组 nums  ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/delete-and-earn 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-05 10:12
 **/
public class DeleteAndEarn {

    static class Solution {

        /**
         * 统计每个数出现次数，再 dp
         * (执行用时：2 ms, 在所有 Java 提交中击败了77.98%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了49.56%的用户)
         *
         * @param nums 整数数组
         * @return     通过这些操作获得的最大点数
         */
        public int deleteAndEarn(int[] nums) {
            int len = nums.length;
            Arrays.sort(nums);

            int[] dp = new int[nums[len - 1] + 1];
            dp[nums[0]] = nums[0];

            int[] count = new int[nums[len - 1] + 1];
            for (int num : nums) {
                count[num]++;
            }

            dp[1] = count[1];
            for (int i = 2; i <= nums[len - 1]; i++) {
                if (count[i] == 0) {
                    dp[i] = dp[i - 1];
                } else {
                    // 是否删除当前数作为 dp 条件
                    dp[i] = Math.max(count[i] * i + dp[i - 2], dp[i - 1]);
                }
            }

            //System.out.println(Arrays.toString(dp));
            return dp[nums[len - 1]];
        }

        /**
         * 没必要排序再 dp, 只要知道最大数就好
         * (执行用时：1 ms, 在所有 Java 提交中击败了100%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了49.56%的用户)
         *
         * @param nums 整数数组
         * @return     通过这些操作获得的最大点数
         */
        public int deleteAndEarn1(int[] nums) {
            if(nums.length == 1) {
                return nums[0];
            }

            int max = 0;
            int[] count = new int[10001];
            for (int num : nums) {
                max = Math.max(max, num);
                count[num]++;
            }

            int[] dp = new int[max + 1];
            dp[1] = count[1];
            for (int i = 2; i < dp.length; i++) {
                // 是否删除当前数作为 dp 条件
                dp[i] = Math.max(count[i] * i + dp[i - 2], dp[i - 1]);
            }

            return dp[max];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2};
        System.out.println(new Solution().deleteAndEarn(nums));
        System.out.println(new Solution().deleteAndEarn1(nums));
    }
}

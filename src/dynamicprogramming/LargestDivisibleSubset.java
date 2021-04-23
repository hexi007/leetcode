package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description 给你一个由 无重复 正整数组成的集合 nums ，
 * 请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或 answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/largest-divisible-subset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-23 10:42
 **/
public class LargestDivisibleSubset {

    static class Solution {

        /**
         * 排序后动态规划求出当前每个数作为最大数的整除子集大小，再统计最大子集
         * (执行用时：20 ms, 在所有 Java 提交中击败了87.78%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了71.31%的用户)
         *
         * @param nums 无重复正整数组成的集合
         * @return     最大的整除子集
         */
        public List<Integer> largestDivisibleSubset(int[] nums) {
            Arrays.sort(nums);
            int len = nums.length;
            int[] dp = new int[len];
            // 每个数都可以单独最为一个整除子集
            Arrays.fill(dp, 1);
            // maxSize 整除子集最大size lastIndex 当前最大整除子集最后一个数所在位置
            int maxSize = 1, lastIndex = 0;

            // 每个数都和之前的所有数比较
            for (int i = 1; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] % nums[j] == 0) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

                if (dp[i] > maxSize) {
                    maxSize = dp[i];
                    lastIndex = i;
                }
            }

            List<Integer> res = new ArrayList<>();

            // 找到可整除的再减小 maxSize
            for (int i = lastIndex; i >= 0; i--) {
                if (dp[i] == maxSize && nums[lastIndex] % nums[i] == 0) {
                    res.add(nums[i]);
                    lastIndex = i;
                    maxSize--;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,4,7,16,9,12,8,18};
        System.out.println(new Solution().largestDivisibleSubset(nums));
    }
}

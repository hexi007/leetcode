package array;

/**
 * description 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/degree-of-an-array 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-20 09:02
 **/
public class DegreeOfAnArray {

    static class Solution {

        /**
         * 先统计出现次数最多的数，从左往右遍历求出总数第一次出现位置，再从右往左找最短区间
         * (执行用时：10 ms, 在所有 Java 提交中击败了92.62%的用户)
         * (内存消耗：42.2 MB, 在所有 Java 提交中击败了57.61%的用户)
         *
         * @param nums 非空且只包含非负数的整数数组
         * @return     与 nums 度相同的最短连续子数组
         */
        public int findShortestSubArray(int[] nums) {
            int[] counts = new int[50_000];
            for (int num : nums) {
                counts[num]++;
            }
            int maxCount = 0;
            for (int count : counts) {
                maxCount = Math.max(maxCount, count);
            }
            if (maxCount == 1) {
                return 1;
            }

            // index 每个最多出现次数的数起始位置
            int[] index = new int[50_000];
            for (int i = 0; i < nums.length; i++) {
                if (counts[nums[i]] == maxCount && index[nums[i]] == 0) {
                    // 因为 index 默认都是 0 是所以位置从 1 开始数起
                    index[nums[i]] = i + 1;
                }
            }
            int minLen = 50_000;
            for (int i = nums.length - 1; i >= 0; i--) {
                if (counts[nums[i]] == maxCount && index[nums[i]] != -1) {
                    minLen = Math.min(minLen, i - index[nums[i]] + 2);
                    // 找一个符合条件的子数组就将该数起始位置置为 -1
                    index[nums[i]] = -1;
                }
            }

            return minLen;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 1};
        System.out.println(new Solution().findShortestSubArray(nums));
    }
}

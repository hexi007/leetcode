package sliding_window;

/**
 * description 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 * @author 27771
 * create 2021-02-04 09:14
 **/
public class MaximumAverageSubarray {

    static class Solution {

        /**
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：42.7 MB, 在所有 Java 提交中击败了41.69%的用户)
         *
         * @param nums 整数数组
         * @param k    子数组长度
         * @return     子数组最大平均数
         */
        public double findMaxAverage(int[] nums, int k) {
            // int 计算比 double 快，中间过程尽量用 int
            int sum = 0, maxSum;

            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }

            maxSum = sum;

            for (int i = 1; i < nums.length - k + 1; i++) {
                sum = sum - nums[i - 1] + nums[i + k - 1];
                maxSum = Math.max(maxSum, sum);
            }

            return (double) maxSum / k;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(new Solution().findMaxAverage(nums, k));
    }
}

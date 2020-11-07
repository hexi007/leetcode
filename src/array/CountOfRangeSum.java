package array;

/**
 * description 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数 <br/>
 * 包含 lower 和 upper。  <br/>
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。  <br/>
 *
 * @author 27771
 * create 2020-11-07 09:43
 **/
public class CountOfRangeSum {
    static class Solution {
        /**
         * 暴力法计算所有区间和
         * (执行用时：167 ms, 在所有 Java 提交中击败了27.92%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了91.92%的用户)
         * @param nums  整数数组
         * @param lower 区间和最小值
         * @param upper 区间和最大值
         * @return      区间数
         */
        public int countRangeSum(int[] nums, int lower, int upper) {
            int len = nums.length, res = 0;
            for (int i = 0; i < len; i++) {
                // temp 保存区间（i,当前下标）区间和
                long temp = nums[i];
                if (temp >= lower && temp <= upper) {
                    res++;
                }
                for (int j = i + 1; j < len; j++) {
                    temp += nums[j];
                    if (temp >= lower && temp <= upper) {
                        res++;
                    }
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2,5,-1};
        int lower = -2, upper = 2;
        System.out.println(new Solution().countRangeSum(nums, lower, upper));
    }
}

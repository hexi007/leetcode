package stack;

/**
 * description 给定一个整数序列：a1, a2, ..., an，一个 132 模式的子序列 ai, aj, ak 被定义为：
 * 当 i < j < k 时，ai < ak < aj。
 * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有 132 模式的子序列。
 *
 * @author 27771
 * create 2020-11-12 19:53
 **/
public class OneThreeTwoPattern {

    static class Solution {
        /**
         * 先求前缀最小值，再从右往左找 132 模式
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了93.19% 的用户)
         * @param nums 整数序列
         * @return     是否有 132 模式
         */
        public boolean find132pattern(int[] nums) {
            int baseLength = 3;
            if (nums.length < baseLength) {
                return false;
            }

            int len = nums.length;
            int[] min = new int[len];
            min[0] = nums[0];
            // 求最小前缀
            for (int i = 1; i < len; i++) {
                min[i] = Math.min(min[i - 1], nums[i]);
            }

            // 数组模拟栈
            int[] stack = new int[len];
            int top = 0;
            for (int j = len - 1; j >= 0; j--) {
                // 只有当前数小于最小前缀两者之间才可能存在 132 模式，即首先满足条件 3 > 1
                if (nums[j] > min[j]) {
                    // 确保条件 2 > 1 ,只要有 2 <= 1 的情况，直接出栈
                    while (top != 0 && stack[top - 1] <= min[j]) {
                        top--;
                    }
                    // 只要栈非空，说明同时满足 3 > 1 且 2 > 1，一旦满足 2 < 3，返回true
                    if (top != 0 && stack[top - 1] < nums[j]) {
                        return true;
                    }
                    // 入栈
                    stack[top++] = nums[j];
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2};
        System.out.println(new Solution().find132pattern(nums));
    }
}
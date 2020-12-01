package array;

import java.util.Arrays;

/**
 * description 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 * 在子序列 a 和子序列 b 第一个不相同的位置上，
 * 如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。
 * 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 *
 * @author 27771
 * create 2020-12-01 09:35
 **/
public class FindTheMostCompetitiveSubsequence {

    static class Solution {

        /**
         * 单调栈
         * (执行用时：7 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：54.8 MB, 在所有 Java 提交中击败了100.00%的用户)
         *
         * @param nums 数组
         * @param k    最具竞争力子序列长度
         * @return     最具竞争力子序列
         */
        public int[] mostCompetitive(int[] nums, int k) {
            int[] stack = new int[k + 1];
            int stackIndex = 0;
            stack[stackIndex++] = -1;

            for (int i = 0; i < nums.length; i++) {
                // 只有比栈顶小且数组剩余还能填满栈才出栈
                while (nums[i] < stack[stackIndex - 1]
                        && k - stackIndex + 1 < nums.length - i) {
                    stackIndex--;
                }
                if (stackIndex < k + 1) {
                    stack[stackIndex++] = nums[i];
                }
            }

            int[] res = new int[k];
            while (k > 0) {
                res[--k] = stack[--stackIndex];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,4,3,3,5,4,9,6};
        int k = 4;
        System.out.println(Arrays.toString(new Solution().mostCompetitive(nums, k)));
    }
}

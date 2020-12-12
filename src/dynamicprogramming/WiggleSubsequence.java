package dynamicprogramming;

/**
 * description 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
 * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
 * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列。
 * 第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。
 * 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 *
 * @author 27771
 * create 2020-12-12 10:55
 **/
public class WiggleSubsequence {

    static class Solution {

        /**
         * 动态规划维护两个子序列
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36 MB, 在所有 Java 提交中击败了70.72%的用户)
         *
         * @param nums 整数序列
         * @return     最长摆动序列
         */
        public int wiggleMaxLength(int[] nums) {
            int len = nums.length;
            if (len == 0 || len == 1) {
                return len;
            }

            // up[i] 表示 nums 1 到 i 之间最长摆动序列的以最后一个数是上升趋势的序列
            int[] up = new int[len];
            // up[i] 表示 nums 1 到 i 之间最长摆动序列的以最后一个数是下降趋势的序列
            int[] down = new int[len];
            // 单独一个数是摆动序列，既是上升也是下降
            up[0] = down[0] = 1;

            for (int i = 1; i < len; i++) {
                if (nums[i] < nums[i - 1]) {
                    // nums[i] < nums[i - 1] 时，nums[i] 不会让最长上升摆动序列出现变化
                    up[i] = up[i - 1];
                    // 最长下降摆动序列可能会更新为 up[i - 1] + 1
                    down[i] = Math.max(down[i - 1], up[i - 1] + 1);
                } else if (nums[i] > nums[i - 1]) {
                    // nums[i] > nums[i - 1] 时，nums[i] 不会让最长下降摆动序列出现变化
                    down[i] = down[i - 1];
                    // 最长上升摆动序列可能会更新为 down[i - 1] + 1
                    up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                } else {
                    // nums[i] ==  nums[i - 1] 时，最长上升摆动序列和最长下降摆动序列都不会发生变化
                    up[i] = up[i - 1];
                    down[i] = down[i - 1];
                }
            }

            // 返回最长上升摆动序列和最长下降摆动序列两者之间更长的那个序列长度
            return Math.max(up[len - 1], down[len - 1]);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,17,5,10,13,15,10,5,16,8};
        System.out.println(new Solution().wiggleMaxLength(nums));
    }
}

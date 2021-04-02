package array;

/**
 * description 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author 27771
 * create 2021-04-02 09:56
 **/
public class TrappingRainWater {

    static class Solution {

        /**
         * 一个数组记录当前位置左边最大值，一个记录右边最大值，取两者较小的与当前位置的高度差为当前位置能接到的水
         * (执行用时：2 ms, 在所有 Java 提交中击败了50.54%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了46.25%的用户)
         *
         * @param height 高度图
         * @return       下雨之后能接多少雨水
         */
        public int trap(int[] height) {
            if (height == null|| height.length == 0) {
                return 0;
            }

            int len = height.length;
            int[] left = new int[len];
            left[0] = height[0];
            for (int i = 1; i < len; i++) {
                left[i] = Math.max(left[i - 1], height[i]);
            }

            int[] right = new int[len];
            right[len - 1] = height[len - 1];
            for (int i = len - 1 - 1; i >= 0; i--) {
                right[i] = Math.max(right[i + 1], height[i]);
            }

            int res = 0;
            for (int i = 0; i < len; i++) {
                res += Math.min(left[i], right[i]) - height[i];
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new Solution().trap(height));
    }
}

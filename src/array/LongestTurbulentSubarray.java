package array;

/**
 * description  当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 返回 A 的最大湍流子数组的长度。
 *
 * @author 27771
 * create 2021-02-08 11:37
 **/
public class LongestTurbulentSubarray {

    static class Solution {

        /**
         * 遍历数组并求出最后一个数是上升或下降趋势的子数组长度
         * (执行用时：5 ms, 在所有 Java 提交中击败了97.24%的用户)
         * (内存消耗：41.7 MB, 在所有 Java 提交中击败了72.00%的用户)
         *
         * @param arr 数组
         * @return    最大湍流子数组的长度
         */
        public int maxTurbulenceSize(int[] arr) {
            // up   最后一个数是上升趋势的子数组长度
            // down 最后一个数是下降趋势的子数组长度
            int up = 1, down = 1;
            int res = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > arr[i - 1]) {
                    // 最后一个数是上升趋势，up 变为最后一个数是下降趋势子数组长度加一
                    up = down + 1;
                    // down 恢复初始状态
                    down = 1;
                    // res 和 up 作比较
                    res = Math.max(res, up);
                } else if (arr[i] < arr[i - 1]) {
                    // 最后一个数是下降趋势，down 变为最后一个数是上升趋势子数组长度加一
                    down = up + 1;
                    // up 恢复初始状态
                    up = 1;
                    // res 和 down 作比较
                    res = Math.max(res, down);
                } else {
                    // 两数相等从新开始计算 up 和 down
                    up = 1;
                    down = 1;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,4,2,10,7,8,8,1,9};
        System.out.println(new Solution().maxTurbulenceSize(arr));
    }
}

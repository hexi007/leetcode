package array;

import java.util.Arrays;

/**
 * description 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-19 20:49
 **/
public class BuildingProductArray {

    static class Solution {

        /**
         * (执行用时：2 ms, 在所有 Java 提交中击败了80.07%)的用户
         * (内存消耗：51.2 MB, 在所有 Java 提交中击败了44.23%的用户)
         *
         * @param a 数组
         * @return  乘积数组
         */
        public int[] constructArr(int[] a) {
            if (a == null || a.length <= 0) {
                return new int[0];
            }

            int len = a.length;
            // res 先用于计算左边乘积
            // res[i] = res[0] * res[1] * ... * res[i - 1]
            int[] res = new int[len];
            res[0] = 1;
            for (int i = 1; i < len; i++) {
                res[i] = res[i - 1] * a[i - 1];
            }
            // temp 保存从右边过来的乘积
            int temp = 1, half = 2;
            for (int i = len - half; i >= 0; i--) {
                // temp 与 右边一个数相乘更新自己
                temp = temp * a[i + 1];
                // res[i] (位置 i 左边的乘积) * temp (右边的乘积) 就是位置 i 最终的乘积
                res[i] = res[i] * temp;
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(new Solution().constructArr(a)));
    }
}

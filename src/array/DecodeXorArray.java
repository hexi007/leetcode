package array;

import java.util.Arrays;

/**
 * description 未知 整数数组 arr 由 n 个非负整数组成。
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。
 * 例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。
 * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/decode-xored-array 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-06 09:27
 **/
public class DecodeXorArray {

    static class Solution {

        /**
         * 1 <= i <= encoded.length 时，encode[i - 1] = arr[i - 1] xor arr[i]
         * 两边同时异或 arr[i - 1], 得 arr[i] = encoded[i - 1]  xor arr[i - 1]
         * 以此计算 arr
         * (执行用时：2 ms, 在所有 Java 提交中击败了73.79%的用户)
         * (内存消耗：39.2 MB, 在所有 Java 提交中击败了85.51%的用户)
         *
         * @param encoded 编码后的数组
         * @param first   原数组 arr 的第一个元素
         * @return        原数组 arr
         */
        public int[] decode(int[] encoded, int first) {
            int len = encoded.length;
            int[] arr = new int[len + 1];
            arr[0] = first;
            for (int i = 1; i <= len; i++) {
                arr[i] = encoded[i - 1]  ^ arr[i - 1];
            }
            return arr;
        }
    }

    public static void main(String[] args) {
        int[] encoded = {1,2,3};
        int first = 1;
        System.out.println(Arrays.toString(new Solution().decode(encoded, first)));
    }
}

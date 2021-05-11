package array;

import java.util.Arrays;

/**
 * description 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。
 * 比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/decode-xored-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-11 10:06
 **/
public class DecodeXorPermutation {

    static class Solution {

        /**
         * 充分利用 perm 是 n 个正整数的排列这一条件
         * 记 total = perm[0] xor perm[1] ... xor perm[n - 1]
         * 在 DecodeXorArray 这问题中我们知道要解这类问题，需要知道原数组第一个数，也就是 perm[0]
         * 那么利用 encoded， 记 odd = encoded[1] xor encoded[3] ... xor encoded[n - 2]
         * = perm[1] xor perm[2] ... xor perm[n - 1]
         * 所以 perm[0] = total xor odd,再按照 DecodeXorArray 解码得出原数组
         *
         * (执行用时：3 ms, 在所有 Java 提交中击败了98.67%的用户)
         * (内存消耗：59.5 MB, 在所有 Java 提交中击败了97.33%的用户)
         *
         * @param encoded 编码后的数组
         * @return        编码后的数组
         */
        public int[] decode(int[] encoded) {
            int n = encoded.length + 1, total = 1;
            for (int i = 2; i <= n; i++) {
                total ^= i;
            }

            int odd = encoded[1];
            for (int i = 3, two = 2; i < n - 1; i += two) {
                odd ^= encoded[i];
            }

            int perm0 = total ^ odd;
            int[] perm = new int[n];
            perm[0] = perm0;
            for (int i = 1; i < n; i++) {
                perm[i] = encoded[i - 1]  ^ perm[i - 1];
            }

            return perm;
        }
    }

    public static void main(String[] args) {
        int[] encoded = {6,5,4,6};
        System.out.println(Arrays.toString(new Solution().decode(encoded)));
    }
}

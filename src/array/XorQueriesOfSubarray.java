package array;

import java.util.Arrays;

/**
 * description 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 * 并返回一个包含给定查询 queries 所有结果的数组。
 * 来源：力扣（LeetCode）链接：https://leetcode-cn.com/problems/xor-queries-of-a-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-12 09:53
 **/
public class XorQueriesOfSubarray {

    static class Solution {

        /**
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：49.6 MB, 在所有 Java 提交中击败了6.47%的用户)
         *
         * @param arr     正整数数组
         * @param queries 查询数组
         * @return        包含给定查询 queries 所有结果的数组
         */
        public int[] xorQueries(int[] arr, int[][] queries) {
            int arrLen = arr.length;
            // dp dp[i] 表示 arr[0] xor arr[1] ... xor[i]
            int[] dp = new int[arrLen];
            dp[0] = arr[0];
            for (int i = 1; i < arrLen; i++) {
                dp[i] = dp[i - 1] ^ arr[i];
            }

            int queriesLen = queries.length;
            int[] res = new int[queriesLen];
            int index = 0;
            for (int[] query : queries) {
                int start = query[0], end = query[1];
                // arr[0] xor arr[1] ..xor[start - 1] xor [start] .... xor arr[end] = dp[end]
                // [start] .... xor arr[end] = dp[end] xor arr[0] xor arr[1] ..xor[start - 1]
                res[index++] = start == 0 ? dp[end] : dp[end] ^ dp[start - 1];
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,8};
        int[][] queries = {{1,2}};
        System.out.println(Arrays.toString(new Solution().xorQueries(arr, queries)));
    }
}

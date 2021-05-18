package array;

/**
 * description 给你一个整数数组 arr 。  现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：  a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。  请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/
 * count-triplets-that-can-form-two-arrays-of-equal-xor 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-18 10:40
 **/
public class CountTripletsXor {

    static class Solution {

        /**
         * a == b 则 a ^ b == 0，也就是找
         * arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] ^ arr[j] ^ arr[j + 1] ^ ... ^ arr[k] == 0
         * 的区间
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35.6 MB, 在所有 Java 提交中击败了100.00%的用户)
         *
         * @param arr 整数数组
         * @return    够令 a == b 成立的三元组 (i, j , k) 的数目
         */
        public int countTriplets(int[] arr) {
            int res = 0;
            for (int i = 0, len = arr.length; i < len - 1; i++) {
                int sum = 0;
                for (int k = i; k < len; k++) {
                    sum ^= arr[i];
                    if (sum == 0 && k > i) {
                        res += (k - i);
                    }
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,6,7};
        System.out.println(new Solution().countTriplets(arr));
    }
}
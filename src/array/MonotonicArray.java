package array;

/**
 * description 如果数组是单调递增或单调递减的，那么它是单调的。
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。
 * 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/monotonic-array 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-28 14:45
 **/
public class MonotonicArray {

    static class Solution {

        /**
         * 先遍历直到当前数和后数不同，再确定是单调递增还是单调递减
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：46.3 MB, 在所有 Java 提交中击败了86.68%的用户)
         *
         * @param a 数组
         * @return  数组是否单调
         */
        public boolean isMonotonic(int[] a) {
            int i = 0, len = a.length;
            // 遍历直到当前数和后数不同
            while (i + 1 < len && a[i] == a[i + 1]) {
                i++;
            }
            if (i == len - 1) {
                return true;
            }

            // 确定是单调递增还是单调递减
            if (a[i] < a[i + 1]) {
                while (i + 1 < len) {
                    if (a[i] > a[i + 1]) {
                        return false;
                    }
                    i++;
                }
            } else {
                while (i + 1 < len) {
                    if (a[i] < a[i + 1]) {
                        return false;
                    }
                    i++;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,4,4};
        System.out.println(new Solution().isMonotonic(a));
    }
}

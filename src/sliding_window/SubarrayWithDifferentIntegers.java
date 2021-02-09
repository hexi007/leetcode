package sliding_window;

/**
 * description 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，
 * 则称 A 的这个连续、不一定独立的子数组为好子数组。
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）  返回 A 中好子数组的数目。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-09 10:36
 **/
public class SubarrayWithDifferentIntegers {

    static class Solution {

        /**
         * 子数组恰好有 k 个不同的个数，
         * 等价于 最多包含 k 个不同整数的子数组个数 - 包含 k - 1 个不同整数的子数组个数
         * (执行用时：5 ms, 在所有 Java 提交中击败了86.55%的用户)
         * (内存消耗：41.6 MB, 在所有 Java 提交中击败了79.10%的用户)
         *
         * @param a 正整数数组
         * @param k 不同整数最多个数
         * @return  满足条件子数组个数
         */
        public int subarrayWithDistinct(int[] a, int k) {
            return mostDistinct(a, k) - mostDistinct(a, k - 1);
        }

        /**
         * 最多包含 k 个不同整数的子数组个数
         *
         * @param a 正整数数组
         * @param k 不同整数最多个数
         * @return  满足条件子数组个数
         */
        private int mostDistinct(int[] a, int k) {
            int len = a.length;
            // freq 纪律每个数出现次数
            int[] freq = new int[len + 1];
            int left = 0, right = 0;
            // count 记录子数组内不同数的个数 res 统计最终个数
            int count = 0, res = 0;

            while (right < len) {
                // freq[a[right]] == 0 表示进入了一个新的整数
                if (freq[a[right]] == 0) {
                    count++;
                }
                freq[a[right]]++;
                right++;

                // 只要 count > k 窗口边界不断左移
                while (count > k) {
                    freq[a[left]]--;
                    // freq[a[right]] == 0 表示当前数最后一次出现
                    if (freq[a[left]] == 0) {
                        count--;
                    }
                    left++;
                }

                // 子数组个数就是滑动窗口的长度
                res += right - left;
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,1,2,3};
        int k = 2;
        System.out.println(new Solution().subarrayWithDistinct(a, k));
    }
}

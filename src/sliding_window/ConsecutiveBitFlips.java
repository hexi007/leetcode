package sliding_window;

/**
 * description 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，
 * 同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-18 09:59
 **/
public class ConsecutiveBitFlips {

    static class Solution {

        /**
         * 使用差分数组的思想，即原始数组的相邻元素之间的差值，令 d[i]=a[i+1]-a[i]
         * 通过累加差分数组可以得到当前位置需要翻转的次数
         * (执行用时：7 ms, 在所有 Java 提交中击败了80.75%的用户)
         * (内存消耗：46.9 MB, 在所有 Java 提交中击败了11.25%的用户)
         *
         * @param a 仅包含 0 和 1 的数组
         * @param k 一次翻转长度
         * @return  所需的 K 位翻转的最小次数
         */
        public int minBitFlips(int[] a, int k) {
            int len = a.length;
            int[] diff = new int[len + 1];
            // revCount 当前位置需要翻转的次数
            int res = 0, revCount = 0;
            for (int i = 0; i < len; i++) {
                revCount += diff[i];

                // revCount + a[i] 为偶数表示当前位置需要翻转
                if ((revCount + a[i]) % 2 == 0) {
                    if (i + k > len) {
                        return -1;
                    }
                    // 一次翻转操作
                    res++;
                    // 翻转累计次数加加
                    revCount++;
                    // 翻转相当于所有子数组加一也就是翻转子数组最右边减一
                    diff[i + k]--;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] a = {0,0,0,1,0,1,1,0};
        int k = 3;
        System.out.println(new Solution().minBitFlips(a, k));
    }
}

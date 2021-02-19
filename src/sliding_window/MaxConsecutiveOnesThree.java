package sliding_window;

/**
 * description 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 * @author 27771
 * create 2021-02-19 09:08
 **/
public class MaxConsecutiveOnesThree {

    static class Solution {

        /**
         * 滑动窗口扫描
         * (执行用时：3 ms, 在所有 Java 提交中击败了94.07%的用户)
         * (内存消耗：39.7 MB, 在所有 Java 提交中击败了67.60%的用户)
         *
         * @param a 0 和 1 组成的数组
         * @param k 最多可以将 0 变成 1 的次数
         * @return  仅包含 1 的最长（连续）子数组的长度
         */
        public int longestOnes(int[] a, int k) {
            // count 记录已经改了多少个 0
            int left = 0, right = 0, len = a.length, count = 0, maxLen = 0;
            while (right < len) {
                if (a[right] == 1) {
                    right++;
                    maxLen = Math.max(maxLen, right - left);
                } else {
                    if (count < k) {
                        count++;
                        right++;
                        maxLen = Math.max(maxLen, right - left);
                    } else {
                        // 滑动窗口左边界是 0 要使 count 减一
                        if (a[left] == 0) {
                            count--;
                        }
                        left++;
                    }
                }
            }

            return maxLen;
        }
    }

    public static void main(String[] args) {
        int[] a = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;
        System.out.println(new Solution().longestOnes(a, k));
    }
}

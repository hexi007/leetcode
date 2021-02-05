package sliding_window;

/**
 * description 给你两个长度相同的字符串，s 和 t。
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），
 * 也就是两个字符的 ASCII 码值的差的绝对值。
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-05 09:24
 **/
public class SubstringsWithinBudget {

    static class Solution {

        /**
         * 保证滑动窗口内开销之和小于 maxCost
         * (执行用时：4 ms, 在所有 Java 提交中击败了98.84%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了52.69%的用户)
         *
         * @param s       字符串 s
         * @param t       字符串 t
         * @param maxCost 变更字符串的最大预算
         * @return        可以转化的最大长度
         */
        public int equalSubstring(String s, String t, int maxCost) {
            int len = s.length();
            char[] charsS = s.toCharArray(), charsT = t.toCharArray();
            int[] cost = new int[len];

            for (int i = 0; i < len; i++) {
                cost[i] = Math.abs(charsS[i] - charsT[i]);
            }

            int sumOfCost = 0, left = 0, right = 0, maxLength = 0;

            while (right < len) {
                // 先加上滑动窗口右边界的 cost
                sumOfCost += cost[right];
                if (sumOfCost <= maxCost) {
                    maxLength = Math.max(maxLength, right - left + 1);
                } else {
                    // 滑动窗口内所有 cost 之和大于 maxCost 时
                    // sumOfCost 不断减小滑动窗口左边界的值再左指针右移
                    while (left <= right && sumOfCost > maxCost) {
                        sumOfCost -= cost[left];
                        left++;
                    }
                }
                right++;
            }

            return maxLength;
        }
    }

    public static void main(String[] args) {
        String s = "abcd", t = "bcdf";
        int maxCost = 3;
        System.out.println(new Solution().equalSubstring(s, t, maxCost));
    }
}

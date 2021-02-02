package array;

/**
 * description  给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
 * 总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过 104。
 *
 * @author 27771
 * create 2021-02-02 10:31
 **/
public class LongestRepeatingCharacterReplacement {

    static class Solution {

        /**
         * 双指针
         * (执行用时：7 ms, 在所有 Java 提交中击败了46.40%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了38.45%的用户)
         *
         * @param s 仅由大写英文字母组成的字符串
         * @param k 最多操作次数
         * @return  多次替换后包含重复字母的最长子串的长度
         */
        public int characterReplacement(String s, int k) {
            int left = 0, right = 0, len = s.length();
            // 记录左右指针之间每个字符出现次数
            int[] count = new int[26];
            char[] chars = s.toCharArray();
            // 左右指针之间最多字符出现次数
            int maxLetters = 0;

            while (right < len) {
                // 扫描到了一个新字符
                count[chars[right] - 'A']++;
                // 更新 maxLetters
                maxLetters = Math.max(maxLetters, count[chars[right] - 'A']);
                // 如果左右指针之内除了出现次数最多的那一类字符之外
                // 剩余的字符（即非最长重复字符）数量超过 k 个
                if (right - left + 1 - maxLetters > k) {
                    // 左指针所在字符被舍弃
                    count[chars[left] - 'A']--;
                    // 左指针右移
                    left++;
                }
                // 右指针右移包含重复字母的最长子串的长度
                right++;
            }

            // 左右指针宽度就是
            return right - left;
        }
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println(new Solution().characterReplacement(s, k));
    }
}

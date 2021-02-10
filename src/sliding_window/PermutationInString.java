package sliding_window;

/**
 * description 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * @author 27771
 * create 2021-02-10 10:27
 **/
public class PermutationInString {

    static class Solution {

        /**
         * (执行用时：4 ms, 在所有 Java 提交中击败了97.54%的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了31.56%的用户)
         *
         * @param s1 字符串 s1
         * @param s2 字符串 s2
         * @return   s2 是否包含 s1 的排列
         */
        public boolean checkInclusion(String s1, String s2) {
            int len1 = s1.length();
            // letters1 记录 s1 所有字符
            int[] letters1 = new int[26];
            char[] chars = s1.toCharArray();
            for (char c : chars) {
                letters1[c - 'a']++;
            }

            chars = s2.toCharArray();
            int left = 0, right = 0, len2 = chars.length;
            // letters2 记录 s2 所有字符
            int[] letters2 = new int[26];
            // count 记录 s1 中字符出现在 s2 中的次数
            int count = 0;
            while (right < len2) {
                letters2[chars[right] - 'a']++;
                if (letters2[chars[right] - 'a'] <= letters1[chars[right] - 'a']) {
                    // 只有对应小于的情况 count 才能加一
                    count++;
                }
                right++;

                while (count == len1) {
                    // 出现次数符合且长度也符合情况时返回 true
                    if (right - left == len1) {
                        return true;
                    }
                    // chars[left] 是 s1 字符时
                    if (letters1[chars[left] - 'a'] > 0) {
                        letters2[chars[left] - 'a']--;
                        if (letters2[chars[left] - 'a'] < letters1[chars[left] - 'a']) {
                            count--;
                        }
                    }
                    left++;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(new Solution().checkInclusion(s1, s2));
    }
}

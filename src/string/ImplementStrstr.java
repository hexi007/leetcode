package string;

/**
 * description 实现 strStr() 函数。
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * （下标从 0 开始）。如果不存在，则返回  -1 。
 * 说明：  当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 str() 以及 Java 的 indexOf() 定义相符。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/implement-strstr 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-20 11:58
 **/
public class ImplementStrstr {

    static class Solution {

        /**
         * 暴力字符串匹配
         * (执行用时：1 ms, 在所有 Java 提交中击败了71.02%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了22.79%的用户)
         *
         * @param haystack 待匹配字符串
         * @param needle   匹配字符串
         * @return         在 haystack 字符串中 needle 字符串出现的第一个位置
         */
        public int strStr(String haystack, String needle) {
            if (needle == null || "".equals(needle)) {
                return 0;
            }

            char[] haystackChars = haystack.toCharArray();
            char[] needleChars = needle.toCharArray();
            int len1 = haystackChars.length, len2 = needleChars.length;

            for (int i = 0; i < len1; i++) {
                if (haystackChars[i] == needleChars[0]) {
                    if (i + len2 - 1 < len1) {
                        boolean isEqual = true;
                        for (int j = i, k = 0; j <= i + len2 - 1; j++, k++) {
                            if (haystackChars[j] != needleChars[k]) {
                                isEqual = false;
                                break;
                            }
                        }
                        if (isEqual) {
                            return i;
                        }
                    } else {
                        return -1;
                    }
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
        System.out.println(new Solution().strStr(haystack, needle));
    }

}

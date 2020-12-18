package hash;

/**
 * description 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 *
 * @author 27771
 * create 2020-12-18 09:20
 **/
public class FindTheDifference {

    static class Solution {

        /**
         * 存放 s 中各字符出现次数， 再和 t 比较
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.8 MB, 在所有 Java 提交中击败了69.03%的用户)
         *
         * @param s 字符串
         * @param t 字符串
         * @return   t 中被添加的字母
         */
        public char findTheDifference(String s, String t) {
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            char[] map = new char[26];

            for (char c : sChars) {
                map[c - 'a']++;
            }
            for (char c : tChars) {
                if (map[c - 'a']-- == 0) {
                    return c;
                }
            }

            return tChars[1];
        }

        /**
         * 对 s 和 t 所有字母进行异或运算就会剩下那个被添加的字符
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户）
         * (内存消耗：36.5 MB, 在所有 Java 提交中击败了96.70%的用户)
         *
         * @param s 字符串
         * @param t 字符串
         * @return   t 中被添加的字母
         */
        public char findTheDifference1(String s, String t) {
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();

            int res = 0;
            for (char c : sChars) {
                res ^= c;
            }
            for (char c : tChars) {
                res ^= c;
            }

            return (char) res;
        }
    }

    public static void main(String[] args) {
        String s = "abcd", t = "abcde";
        System.out.println(new Solution().findTheDifference(s, t));
        System.out.println(new Solution().findTheDifference1(s, t));
    }
}

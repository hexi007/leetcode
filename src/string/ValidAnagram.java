package string;

/**
 * description 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * @author 27771
 * create 2020-11-22 10:34
 **/
public class ValidAnagram {

    static class Solution {

        /**
         * 保存 s 出现字母频率，再和 t 比较
         * @param s 字符串 s
         * @param t 字符串 s
         * @return  t 是否是 s 的字母异位词
         */
        public boolean isAnagram(String s, String t) {
            char[] sToChar = s.toCharArray();
            char[] tToChar = t.toCharArray();
            int[] map = new int[26];

            for (char c : sToChar) {
                map[c - 'a']++;
            }
            for (char c : tToChar) {
                map[c - 'a']--;
                if (map[c - 'a'] == -1) {
                    return false;
                }
            }
            for (int m : map) {
                if (m > 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        String s = "qa";
        String t = "aq";
        System.out.println(new Solution().isAnagram(s, t));
    }
}

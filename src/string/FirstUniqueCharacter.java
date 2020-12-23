package string;

/**
 * description 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * @author 27771
 * create 2020-12-23 09:18
 **/
public class FirstUniqueCharacter {

    static class Solution {

        char[] sChars;

        /**
         * hash 存储每个字符出现次数，返回出现次数为 1 的下标
         * (执行用时： 4 ms, 在所有 Java 提交中击败了97.02%的用户)
         * (内存消耗：39 MB, 在所有 Java 提交中击败了58.16%的用户)
         *
         * @param s 字符串
         * @return  第一个不重复的字符的索引
         */
        public int firstUniqueChar(String s) {
            int[] letter = new int[26];
            sChars = s.toCharArray();

            for (char c : sChars) {
                letter[c - 'a']++;
            }

            for (int i = 0; i < sChars.length; i++) {
                if (letter[sChars[i] - 'a'] == 1) {
                    return i;
                }
            }

            return -1;
        }

        /**
         * 使用 indexOf 和 lastIndexOf() 找元素出现下标
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.2 MB, 在所有 Java 提交中击败了38.64%的用户)
         *
         * @param s 字符串
         * @return  第一个不重复的字符的索引
         */
        public int firstUniqueChar1(String s) {
            int result = s.length();
            char begin = 'a', end = 'z';
            for (char c = begin; c <= end ; c++) {
                int firstIndex = s.indexOf(c);
                int lastIndex = s.lastIndexOf(c);

                //如果相等表示不重复
                if(lastIndex == firstIndex && firstIndex != -1 ){
                    // 选最靠左也就是最先出现的不重复的字符
                    result = Math.min(firstIndex,result);
                }
            }
            if(result != s.length()){
                return result;
            }
            return -1;
        }

    }

    public static void main(String[] args) {
        String s = "aadadaadj";
        System.out.println(new Solution().firstUniqueChar(s));
        System.out.println(new Solution().firstUniqueChar1(s));
    }
}

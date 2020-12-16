package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * description 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * @author 27771
 * create 2020-12-16 09:43
 **/
public class WordPattern {

    static class Solution {

        Map<Character, String> map;

        /**
         * map 储存 pattern 对应规则，挨个对比即可
         * (执行用时：1 ms, 在所有 Java 提交中击败了98.94%的用户)
         * (内存消耗：36.6 MB, 在所有 Java 提交中击败了58.73%的用户)
         *
         * @param pattern 规律
         * @param s 2     字符串
         * @return        字符串是否遵循相同的规律
         */
        public boolean wordPattern(String pattern, String s) {
            String [] words = s.split(" ");
            if (pattern.length() != words.length) {
                return false;
            }

            map = new HashMap<>(16);
            char[] patternChars = pattern.toCharArray();

            for (int i = 0; i < patternChars.length; i++) {
                if (!map.containsKey(patternChars[i])) {
                    // 如果 map 里没有 pattern 当前 字符，还要先判断是否还有相同的值
                    if (map.containsValue(words[i])){
                        return false;
                    }
                    map.put(patternChars[i], words[i]);
                } else {
                    if (!map.get(patternChars[i]).equals(words[i])) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        String patten = "abba", s = "dog cat cat dog";
        System.out.println(new Solution().wordPattern(patten, s));
    }
}

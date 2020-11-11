package string;

import java.util.ArrayList;
import java.util.List;

/**
 * description 给定一个字符串，逐个翻转字符串中的每个单词。
 * 说明：  无空格字符构成一个 单词 。 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * @author 27771
 * create 2020-11-10 10:49
 **/
public class ReverseWordsInString {

    static class Solution {
        /**
         * 使用 split 分割，再逆序排列
         * (执行用时：9 ms, 在所有 Java 提交中击败了30.58%的用户)
         * (内存消耗：38.8 MB, 在所有 Java 提交中击败了75.22%的用户)
         * @param s 字符串
         * @return  逐个翻转每个单词后的字符串
         */
        public String reverseWords(String s) {
            String[] strings = s.split("\\s+ ");
            for (int i = 0; i < strings.length / 2; i++) {
                String temp = strings[i];
                strings[i] = strings[strings.length - i - 1];
                strings[strings.length - i - 1] = temp;
            }
            StringBuilder sb = new StringBuilder();
            for (String string : strings) {
                if (string.trim().length() < 1) {
                    continue;
                }
                sb.append(string).append(" ");
            }
            String res = sb.toString();
            return res.substring(0, res.length() - 1);
        }

        /**
         * 手动拆分字符串，在逆序构造字符串
         * (执行用时：3 ms, 在所有 Java 提交中击败了81.56% 的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了94.53% 的用户)
         * @param s 字符串
         * @return  逐个翻转每个单词后的字符串
         */
        public String reverseWords1(String s) {
            List<String> list = new ArrayList<>();
            char[] chars = s.toCharArray();
            int len = chars.length;
            int left = 0;
            while (left < len) {
                while (left < len && chars[left] == ' ') {
                    left++;
                }
                int right = left + 1;
                while (right < len && chars[right] != ' ') {
                    right++;
                }
                if (left < len) {
                    list.add(s.substring(left, right));
                }
                left = right + 1;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = list.size() - 1; i >= 0; i--) {
                if ( i != 0) {
                    stringBuilder.append(list.get(i)).append(" ");
                } else {
                    stringBuilder.append(list.get(i));
                }
            }
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        String s = "    the   ";
        System.out.println(new Solution().reverseWords1(s));
    }
}

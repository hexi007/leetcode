package string;

/**
 * description 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * 1.从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 2.从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 3.重复步骤 2 ，直到你没法从 s 中选择字符。
 * 4.从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 5.从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在结果字符串后面。
 * 6.重复步骤 5 ，直到你没法从 s 中选择字符。
 * 7.重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个，你可以选择其中任意一个，并将其添加到结果字符串。
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 *
 * @author 27771
 * create 2020-11-25 09:33
 **/
public class IncreasingDecreasingString {

    static class Solution {

        /**
         * 计数排序后模拟
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了79.09%的用户)
         *
         * @param s 字符串
         * @return  重新排序后的结果字符串
         */
        public String sortString(String s) {

            if (s == null || s.length() == 0) {
                return "";
            }

            int len = s.length();
            int[] counting = new int[26];
            for (char c : s.toCharArray()) {
                counting[c - 'a']++;
            }

            char[] chars = new char[len];
            int n = 0;
            outer:
            while (true) {
                for (int i = 0; i < counting.length; i++) {
                    if (counting[i] == 0) {
                        continue;
                    }
                    chars[n++] = (char) ('a' + i);
                    counting[i]--;
                    if (n == len) {
                        break outer;
                    }
                }
                for (int i = counting.length - 1; i >= 0; i--) {
                    if (counting[i] == 0) {
                        continue;
                    }
                    chars[n++] = (char) ('a' + i);
                    counting[i]--;
                    if (n == len) {
                        break outer;
                    }
                }
            }
            return String.valueOf(chars);
        }
    }

    public static void main(String[] args) {
        String s = "java";
        System.out.println(new Solution().sortString(s));
    }
}

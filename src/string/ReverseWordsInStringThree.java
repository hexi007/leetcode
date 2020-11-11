package string;

/**
 * description 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * create 2020-11-11 16:40
 *
 * @author 27771
 **/
public class ReverseWordsInStringThree {

    static class Solution {

        /**
         * 跳过空格，依次翻转每个单词，用 StringBuilder 会慢很多
         * (执行用时：3 ms, 在所有 Java 提交中击败了99.96% 的用户)
         * (内存消耗：39 MB, 在所有 Java 提交中击败了95.59% 的用户)
         * @param s 字符串
         * @return  翻转每个单词后的字符串
         */
        public String reverseWords(String s) {
            char[] chars = s.toCharArray();
            int len = s.length(), left;
            char space = ' ';
            for (int i = 0; i < len; i++) {
                if (chars[i] != space){
                    left = i;
                    int right = left + 1;
                    while (right < len && chars[right] != space){
                        right++;
                    }
                    right--;
                    i = right;
                    // 逆序单词
                    while (right >= left) {
                        char temp = chars[right];
                        chars[right] = chars[left];
                        chars[left] = temp;
                        left++;
                        right--;
                    }
                }
            }
            return new String(chars);
        }
    }

    public static void main(String[] args) {
        String s = "Let's take Leetcode contest";
        System.out.println(new Solution().reverseWords(s));
    }
}
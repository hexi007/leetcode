package string;

/**
 * description 给出一个字符串 s（仅含有小写英文字母和括号）。
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 注意，您的结果中 不应 包含任何括号。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-26 20:28
 **/
public class ReverseSubstringsBetweenEachPairOfParentheses {

    static class Solution {

        /**
         * 读到 ） 将从最近的 （ 到 ） 之间的字符串翻转放回
         * (执行用时：1 ms, 在所有 Java 提交中击败了98.96%的用户)
         * (内存消耗：36.3 MB, 在所有 Java 提交中击败了98.27%的用户)
         *
         * @param s 字符串
         * @return  逐层反转每对匹配括号中的字符串后结果
         */
        public String reverseParentheses(String s) {
            int len = s.length();
            char[] chars = s.toCharArray();
            char[] deque = new char[len + 1];
            int heda = 0, tail = -1;
            char[] path = new char[len + 1];

            for (int i = 0; i < len; i++) {
                char c = chars[i];
                if (c == ')') {
                    int index = 0;
                    while (tail >= heda) {
                        if (deque[tail] == '(') {
                            tail--;
                            for (int j = 0; j < index; j++) {
                                deque[++tail] = path[j];
                            }
                            break;
                        } else {
                            path[index++] = deque[tail--];
                        }
                    }
                } else {
                    deque[++tail] = c;
                }
            }

            StringBuilder sb = new StringBuilder();
            while (tail >= heda) {
                sb.append(deque[heda++]);
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String s = "(u(love)i)";
        System.out.println(new Solution().reverseParentheses(s));
    }
}

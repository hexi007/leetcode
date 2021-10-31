package backtrackingalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * description 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-10-31 20:38
 **/
public class LetterCombinationsOfPhoneNumber {

    static class Solution {
        private final char[][] phone = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        private final List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        public List<String> letterCombinations(String digits) {
            if (digits == null || "".equals(digits)) {
                return ans;
            }
            bt(digits, 0);
            return ans;
        }

        private void bt(String digits, int index) {
            if (index == digits.length()) {
                ans.add(sb.toString());
                return;
            }
            char[] phoneNumbers = phone[digits.charAt(index) - '2'];
            for (char c : phoneNumbers) {
                sb.append(c);
                bt(digits, index + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(new Solution().letterCombinations(digits));
    }
}

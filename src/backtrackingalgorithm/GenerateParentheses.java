package backtrackingalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * description 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 *
 * @author 27771
 * create 2021-11-03 16:41
 **/
public class GenerateParentheses {

    static class Solution {

        List<String> ans = new ArrayList<>();
        public List<String> generateParenthesis(int n) {
            if (n <= 1) {
                return ans;
            }
            bt(n, n);
            return ans;
        }

        private final StringBuilder sb = new StringBuilder();

        private void bt(int l, int r) {
            if (l == 0 && r == 0) {
                ans.add(sb.toString());
                return;
            }
            if (l > 0) {
                sb.append("(");
                bt(l - 1, r);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (r > 0 && l < r) {
                sb.append(")");
                bt(l, r - 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3));
    }
}

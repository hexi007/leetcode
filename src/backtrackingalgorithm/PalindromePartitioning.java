package backtrackingalgorithm;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * description 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。  返回 s 所有可能的分割方案。
 *
 * @author 27771
 * create 2021-03-07 10:53
 **/
public class PalindromePartitioning {

    static class Solution {

        int len;
        // dp 判断 dp[i][j] 即从 i 开始到 j 结束的字串是否是回文串的动态规划数组
        boolean[][] dp;
        char[] chars;
        // path 回溯过程中分割方案
        Deque<String> path;
        List<List<String>> res;

        /**
         * (执行用时：12 ms, 在所有 Java 提交中击败了46.75%的用户)
         * (内存消耗：62.3 MB, 在所有 Java 提交中击败了5.00%的用户)
         *
         * @param s 字符串
         * @return  s 所有可能的分割方案
         */
        public List<List<String>> partition(String s) {
            len = s.length();
            res = new LinkedList<>();
            if (len == 0) {
                return res;
            }

            chars = s.toCharArray();
            dp = new boolean[len][len];
            path = new LinkedList<>();

            for (int right = 0; right < len; right++) {
                for (int left = 0; left <= right; left++) {
                    // 判断 dp[left][right] 是否为 true 需要两个条件
                    // 1. chars[left] 和 chars[right] 要相等
                    // 2. 要么 right 和 left 直接长度小于等于二（就不需要考虑两者之间是否回文）
                    //    要么从 left + 1 开始到 right - 1 之间也是回文
                    boolean isPalindrome = chars[left] == chars[right]
                            && (right - left <= 2 || dp[left + 1][right - 1]);
                    if (isPalindrome) {
                        dp[left][right] = true;
                    }
                }
            }

            // 从位置 0 开始回溯
            backtrack(0);

            return res;
        }

        /**
         * 回溯
         *
         * @param index 回溯开始位置
         */
        private void backtrack(int index) {
            // 回溯开始位置到结尾时表示出现了一种合理分配方案
            if (index == len) {
                res.add(new LinkedList<>(path));
                return;
            }

            // 从 index 开始找回文子串
            for (int i = index; i < len; i++) {
                if (dp[index][i]) {
                    // 将从 index 到 i 之间的字串加入到 path 末尾
                    path.addLast(new String(chars, index, i - index + 1));
                    // 继续回溯
                    backtrack(i + 1);
                    // 跳出回溯时删除 path 末尾子串
                    path.removeLast();
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> res = new Solution().partition(s);
        for (List<String> list : res) {
            System.out.println(list);
        }
    }
}
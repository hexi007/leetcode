package dynamicprogramming;

import java.util.*;

/**
 * description 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：  分隔时可以重复使用字典中的单词。 你可以假设字典中没有重复的单词。
 *
 * @author 27771
 * create 2020-11-01 10:44
 **/
public class WordBreakTwo {

    static class Solution {

        /**
         * wordSet 单词集合
         * res     结果集合
         * dp      动态规划数组
         */
        Set<String> wordSet;
        List<String> res;
        boolean[] dp;

        /**
         动态规划加回溯算法 ，先用动态规划求原始输入字符串的任意长度的 前缀子串 是否可以拆分为单词集合中的单词
         在使用回溯算法求出所有可能解
         (执行用时：4 ms, 在所有 Java 提交中击败了94.32%的用户)
         (内存消耗：38.9 MB, 在所有 Java 提交中击败了86.05%的用户)
         * @param s        字符串
         * @param wordDict 单词字典
         * @return         所有可能的句子
         */
        public List<String> wordBreak(String s, List<String> wordDict) {
            wordSet = new HashSet<>(wordDict);
            int len = s.length();

            //dp[i] 表示「长度」为 i 的 s 前缀子串可以拆分成 wordDict 中的单词
            //长度包括 0 ，因此状态数组的长度为 len + 1
            dp = new boolean[len + 1];
            //0 这个值需要被后面的状态值参考，如果一个单词正好在 wordDict 中，dp[0] 设置成 true 是合理的
            dp[0] = true;

            //从后向前遍历
            for(int right = 1; right <=len; right++){
                //一种略微优化的方式是记录单词的最长长度 maxWordLength,确保 left >= right - maxWordLength
                //这样使得当前 right 不是前缀可分时快速退出当前小循环
                for(int left = right - 1; left >= 0; left--){
                    //只有 left 前缀可分且 left 和 right 之间字符串为一个单词时，right 前缀才是可分的
                    if(dp[left] && wordSet.contains(s.substring(left, right))){
                        dp[right] = true;
                        //注意 break
                        break;
                    }
                }
            }

            //回溯求所有解
            res = new ArrayList<>();
            //如果最后一个位置前缀不可分，那么整个字符串就不是句子可分的
            if(dp[len]){
                //path 记录从根到各个节点的单词
                Deque<String> path = new ArrayDeque<>();
                dfs(s, len, path);
            }
            return res;
        }

        /**
          回溯算法，如果 s[0, len) 之间存在可分单词，就把结果存到 res 中
         * @param s    字符串
         * @param len  当前求解字符串长度
         * @param path 记录从根到各个节点的单词
         */
        private void dfs(String s, int len, Deque<String> path) {
            //长度为 0，用空格隔开从根节点到叶节点的路径上的所有单词
            if(len == 0){
                res.add(String.join(" ", path));
                return;
            }

            //从 len - 1 开始向左遍历
            for(int i = len - 1; i >= 0; i--){
                //只有位置 i 前缀可分且从 i 到 len 是一个单词时，才将该单词加入 path 中
                if(dp[i] && wordSet.contains(s.substring(i, len))){
                    //添加该单词，addFirst 保证添加顺序，上一个节点单词在右边
                    path.addFirst(s.substring(i, len));
                    //不断深入
                    dfs(s, i, path);
                    //不断回溯
                    path.removeFirst();
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<>((Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        List<String> res = new Solution().wordBreak(s, wordDict);
        for(String r : res){
            System.out.println(r);
        }
    }
}

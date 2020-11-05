package breadthfirstsearch;

import java.util.*;

/**
 * description 给定两个单词（beginWord 和 endWord）和一个字典 <br/>
 * 找到从 beginWord 到 endWord 的最短转换序列的长度。 <br/>
 * 转换需遵循如下规则：每次转换只能改变一个字母。 <br/>
 * 转换过程中的中间单词必须是字典中的单词。 <br/>
 * 说明:  如果不存在这样的转换序列，返回 0。所有单词具有相同的长度。  <br/>
 * 所有单词只由小写字母组成。 字典中不存在重复的单词。  <br/>
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。  <br/>
 *
 * @author 27771
 * create 2020-11-05 09:55
 **/
public class WordLadder {

    static class Solution {

        HashSet<String> wordSet;
        Queue<String> queue;
        HashSet<String> visited;

        /**
         * 深搜而不是回溯，因为不是求所有转换可能，只是求最短路径
         * (执行用时：81 ms, 在所有 Java 提交中击败了59.95%的用户)
         * (内存消耗：41.8 MB, 在所有 Java 提交中击败了39.02%的用户)
         * @param beginWord 初始字符串
         * @param endWord   最终字符串
         * @param wordList  字典
         * @return          最小转换步骤
         */
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            wordSet = new HashSet<>(wordList);
            if(wordSet.size() == 0 || !wordSet.contains(endWord)){
                return 0;
            }
            //去掉初始字符串，没必要重新走回来
            wordSet.remove(beginWord);

            queue = new LinkedList<>();
            queue.offer(beginWord);
            visited = new HashSet<>();
            visited.add(beginWord);

            //初始化步数为 1
            int step = 1;
            while (!queue.isEmpty()) {
                int curSize = queue.size();
                while (curSize-- > 0) {
                    String curWord = queue.poll();
                    assert curWord != null;
                    //如果只改变一个单词就可以到达 endWord ，则返回步数加一
                    if(changeWordOnlyOneLetter(curWord, endWord)){
                        return step + 1;
                    }
                }
                step++;
            }
            return 0;
        }

        /**
         * 判断只改变 curWord 一个单词是否可以变成 endWord
         * @param curWord 当前字符串
         * @param endWord 最终字符串
         * @return        可以只改变一个单词就完成转变，返回 true，否则 false
         */
        private boolean changeWordOnlyOneLetter(String curWord, String endWord) {
            char[] charArray = curWord.toCharArray();

            for(int i = 0; i < charArray.length; i++){
                //保存改变之前的单词
                char originChar = charArray[i];
                char beginChar = 'a', endChar = 'z';
                for(char c = beginChar; c <= endChar; c++){
                    //c 和 原来单词相同就跳过
                    if(c == originChar){
                        continue;
                    }
                    charArray[i] = c;
                    //得到修改之后的单词
                    String nextWord = String.valueOf(charArray);
                    if(wordSet.contains(nextWord)){
                        if(nextWord.equals(endWord)){
                            return true;
                        }
                        if(!visited.contains(nextWord)){
                            queue.offer(nextWord);
                            //记得添加进队列的要标记为访问
                            visited.add(nextWord);
                        }
                    }
                }
                //注意修改回来原单词
                charArray[i] = originChar;
            }
            return false;
        }

        HashSet<String> wordSet1;
        HashSet<String> visited1;
        HashSet<String> beginVisited;
        HashSet<String> endVisited;

        /**
         * 双向深度搜索
         * (执行用时：14 ms, 在所有 Java 提交中击败了96.49%的用户)
         * (内存消耗：39.7 MB, 在所有 Java 提交中击败了67.63%的用户)
         * @param beginWord 初始字符串
         * @param endWord   最终字符串
         * @param wordList  字典
         * @return          最小转换步骤
         */
        public int ladderLength1(String beginWord, String endWord, List<String> wordList){
            wordSet1 = new HashSet<>(wordList);
            if(wordSet1.size() == 0 || !wordSet1.contains(endWord)){
                return 0;
            }

            visited1 = new HashSet<>();
            //分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
            beginVisited = new HashSet<>();
            beginVisited.add(beginWord);
            endVisited = new HashSet<>();
            endVisited.add(endWord);

            int step = 1;
            while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
                //确保 beginVisited 小于 endVisited
                if(beginVisited.size() > endVisited.size()){
                    HashSet<String> temp = beginVisited;
                    beginVisited = endVisited;
                    endVisited = temp;
                }

                //nextLevelVisited 用来代替 beginVisited
                HashSet<String> nextLevelVisited = new HashSet<>();
                for(String curWord : beginVisited){
                    if(changeWordOnlyOneLetter(curWord, nextLevelVisited)){
                        return step + 1;
                    }
                }

                beginVisited = nextLevelVisited;
                step++;
            }
            return 0;
        }

        /**
         * 尝试对 word 修改每一个字符，看看是不是能落在 endVisited 中 <br/>
         * 扩展得到的新的 word 添加到 nextLevelVisited 里  <br/>
         * @param curWord          当前单词
         * @param nextLevelVisited 下一层访问的所有字符串
         * @return                 修改后是否可以在 endVisited 中找到
         */
        private boolean changeWordOnlyOneLetter(String curWord, HashSet<String> nextLevelVisited) {
            char[] charArray = curWord.toCharArray();

            for(int i = 0; i < charArray.length; i++){
                //保存改变之前的单词
                char originChar = charArray[i];
                char beginChar = 'a', endChar = 'z';
                for(char c = beginChar; c <= endChar; c++){
                    //c 和 原来单词相同就跳过
                    if(c == originChar){
                        continue;
                    }
                    charArray[i] = c;
                    //得到修改之后的单词
                    String nextWord = String.valueOf(charArray);
                    if(wordSet1.contains(nextWord)){
                        //修改后的字符串在 endVisited ，结束
                        if(endVisited.contains(nextWord)){
                            return true;
                        }
                        if(!visited1.contains(nextWord)){
                            //添加进 nextLevelVisited
                            nextLevelVisited.add(nextWord);
                            //记得添加进队列的要标记为访问
                            visited1.add(nextWord);
                        }
                    }
                }
                //注意修改回来原单词
                charArray[i] = originChar;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(new Solution().ladderLength(beginWord, endWord,
                wordList));
        System.out.println(new Solution().ladderLength1(beginWord, endWord,
                wordList));
    }
}

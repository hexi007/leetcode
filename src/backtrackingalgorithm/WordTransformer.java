package backtrackingalgorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * description 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。
 * 每一步得到的新词都必须能在字典中找到。
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/word-transformer-lcci 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-11-02 16:01
 **/
public class WordTransformer {

    static class Solution {
        private final List<String> ans = new ArrayList<>();
        private boolean[] isVisited;
        private int size;

        public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
            if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {
                return ans;
            }
            size = wordList.size();
            isVisited = new boolean[size];
            for (int index = 0; index < size; index++) {
                if (!isVisited[index] && isNext(beginWord, wordList.get(index))) {
                    bt(wordList, endWord, index);
                    if (find) {
                        if (!beginWord.equals(wordList.get(index))) {
                            ans.add(0, beginWord);
                        }
                        return ans;
                    }
                }
            }
            return new ArrayList<>();
        }

        private boolean find = false;
        private final Deque<String> list = new LinkedList<>();

        private void bt(List<String> wordList, String endWord, int index) {
            if(find) {
                return;
            }
            String word = wordList.get(index);
            isVisited[index] = true;
            list.addLast(word);
            if (word.equals(endWord)) {
                find = true;
                ans.addAll(list);
                return;
            }
            for (int i = 0; i < size; i++) {
                // 不构建图矩阵速度更快
                if (!isVisited[i] && isNext(word, wordList.get(i))) {
                    bt(wordList, endWord, i);
                }
            }
            list.removeLast();
        }

        private boolean isNext(String word1, String word2) {
            int count = 0;
            for (int i = 0, len = word1.length(); i < len; i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    if (count == 1) {
                        return false;
                    }
                    count++;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String beginWord = "qa", endWord = "sq";
        String[] words = {"si","go","se","cm","so","ph","mt","db","mb","sb",
                "kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba",
                "to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma",
                "re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr",
                "nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li",
                "ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an",
                "me","mo","na","la","st","er","sc","ne","mn","mi","am","ex",
                "pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
        List<String> wordList = Arrays.stream(words).collect(Collectors.toList());
        System.out.println(new Solution().findLadders(beginWord, endWord, wordList));
    }
}

package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * 字谜的迷面puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * 单词 word 中包含谜面 puzzle 的第一个字母。 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage",
 * 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组 answer，
 * 数组中的每个元素 answer[i]  是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-26 16:02
 **/
public class WordsForEachPuzzle {

    static class Solution {

        /**
         * 状态压缩
         * (执行用时：140 ms, 在所有 Java 提交中击败了15.22%的用户)
         * (内存消耗：55.3 MB, 在所有 Java 提交中击败了16.30%的用户)
         *
         * @param words   字谜的谜面
         * @param puzzles 字谜的谜底
         * @return        答案数组
         */
        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            // map 所有 words 对应二进制次数
            Map<Integer, Integer> map = new HashMap<>(16);
            for (String word : words) {
                int bin = getBin(word);
                map.put(bin, map.getOrDefault(bin, 0) + 1);
            }

            List<Integer> res = new ArrayList<>();
            for (String puzzle : puzzles) {
                // 统计每个 puzzle 有多少个谜底
                res.add(getCnt(map, puzzle));
            }

            return res;
        }

        /**
         * 计算每个 puzzle 有多少个谜底
         *
         * @param map 所有 words 对应二进制次数
         * @param str puzzle
         * @return    每个 puzzle 谜底数
         */
        private Integer getCnt(Map<Integer, Integer> map, String str) {
            int res = 0, len = str.length();
            char[] chars = str.toCharArray();
            // first puzzle 首字符位置
            int first = chars[0] - 'a';
            // 枚举「除首个字母」以外的所有组合
            // 因为我们需要先固定 puzzle 的首位字母，然后枚举剩余的 6 位是什么
            // 由于是二进制，每一位共有 0 和 1 两种选择，因此共有 2^6 种可能性，也就是 2^6 = 1 << (7 - 1) = 64 种
            for (int i = 0; i < (1 << (len - 1)); i++) {
                // 先将首字母提取出来
                int unicode = 1 << first;
                // 枚举「除首个字母」之后的每一位，结合上面的首个字母
                // 其实就是枚举以 str 首字母开头的字符有多少种（枚举子集）
                for (int j = 1; j < len; j++) {
                    if (((i >> (j - 1)) & 1) != 0) {
                        unicode += 1 << (chars[j] - 'a');
                    }
                }
                // 查询这样的字符是否出现在 words 中，出现了多少次
                if (map.containsKey(unicode)) {
                    res += map.get(unicode);
                }
            }

            return res;
        }

        /**
         * 求每个 word 对应二进制
         *
         * @param str word
         * @return    word 对应二进制
         */
        private int getBin(String str) {
            int bin = 0;
            char[] chars = str.toCharArray();
            for (char c : chars) {
                // 每一位字符所对应二进制数字中哪一位
                int unicode = c - 'a';
                // 如果当前位置为 0，代表还没记录过，则进行记录 (不重复记录)
                if ((bin >> unicode & 1) == 0) {
                    bin += 1 << unicode;
                }
            }

            return bin;
        }
    }

    public static void main(String[] args) {
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        System.out.println(new Solution().findNumOfValidWords(words, puzzles));
    }
}

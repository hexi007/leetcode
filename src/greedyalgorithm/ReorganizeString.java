package greedyalgorithm;

import java.util.PriorityQueue;

/**
 * description 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * @author 27771
 * create 2020-11-30 09:42
 **/
public class ReorganizeString {

    static class Solution {

        /**
         * 构造大顶堆，不断选最大出现次数的字符构造字符串
         * (执行用时：2 ms, 在所有 Java 提交中击败了58.78%的用户)
         * (内存消耗：36.5 MB, 在所有 Java 提交中击败了89.36%的用户)
         *
         * @param S 字符串
         * @return  若可重排，输出任意可行的结果。若不可重排，返回空字符串
         */
        public String reorganizeString(String S) {
            char[] chars = S.toCharArray();

            // 统计所有字符出现次数
            int[] count = new int[26];
            for (char c : chars) {
                count[c - 'a']++;
            }

            // 建立大顶堆
            PriorityQueue<NewChar> pQ = new PriorityQueue<>(26, (o1, o2) -> o2.count - o1.count);
            for (int i = 0; i < count.length; i++) {
                // 如果一个字符出现的次数超过半数，则该字符串是不可重排的
                if (count[i] > 0 && count[i] <= (S.length() + 1) / 2) {
                    pQ.add(new NewChar((char) (i + 'a'), count[i]));
                } else if (count[i] > (S.length() + 1) / 2){
                    return "";
                }
            }

            StringBuilder sb = new StringBuilder();
            // 每次取出现次数最大的两个字符，所以要保证大顶堆内元素数量大于 1
            while (pQ.size() > 1) {
                NewChar char1 = pQ.poll();
                NewChar char2 = pQ.poll();
                sb.append(char1.letter);
                assert char2 != null;
                sb.append(char2.letter);
                // 修改取出元素的出现次数，如果修改后出现次数大于 0 则将其再放回大顶堆
                if (--char1.count > 0) {
                    pQ.add(char1);
                }
                if (--char2.count > 0) {
                    pQ.add(char2);
                }
            }
            // 大顶堆还有剩余,sb 添加即可
            if (!pQ.isEmpty()) {
                sb.append(pQ.poll().letter);
            }

            return sb.toString();
        }

        /**
         * 字符及其出现次数组成的数据结构
         */
        private static class NewChar {
            char letter;
            int count;

            public NewChar(char letter, int count) {
                this.letter = letter;
                this.count = count;
            }
        }
    }

    public static void main(String[] args) {
        String S = "aabbbc";
        System.out.println(new Solution().reorganizeString(S));
    }
}

package hash;

import java.util.*;

/**
 * description 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * @author 27771
 * create 2021-05-20 12:41
 **/
public class TopKsFrequentWords {

    static class Solution {

        /**
         * 先统计各字符串出现次数，再选前 k 个
         * (执行用时：7 ms, 在所有 Java 提交中击败了91.95%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了80.91%的用户)
         *
         * @param words 非空的单词列表
         * @param k     前 k 个
         * @return      前 k 个出现次数最多的单词
         */
        public List<String> topKsFrequent(String[] words, int k) {
            Map<String, Integer> map = new HashMap<>(words.length);
            // 建立哈希表统计单词频率
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            // 小顶堆，相同频率下，字母顺序高的在前，方便入栈
            PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> {
                Integer o1Count = map.get(o1);
                Integer o2Count = map.get(o2);
                if (o1Count.equals(o2Count)) {
                    return o2.compareTo(o1);
                } else {
                    return o1Count - o2Count;
                }
            });
            // 维持topK频率的单词
            for (String word : map.keySet()) {
                queue.offer(word);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            // 利用栈特性
            LinkedList<String> stack = new LinkedList<>();
            while (!queue.isEmpty()) {
                stack.push(queue.poll());
            }
            return stack;
        }
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(new Solution().topKsFrequent(words, k));
    }
}

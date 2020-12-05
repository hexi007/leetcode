package greedyalgorithm;

/**
 * description 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 *
 * @author 27771
 * create 2020-12-05 09:50
 **/
public class TaskScheduler {

    static class Solution {

        /**
         * 考虑将任务以桶的形式排列，任务按出现次数入桶，从左到右，从上到下
         * (执行用时：2 ms, 在所有 Java 提交中击败了97.81%的用户)
         * (内存消耗：39.9 MB, 在所有 Java 提交中击败了61.92%的用户)
         *
         * @param tasks 字符数组
         * @param n     人物之间冷却时间
         * @return      完成所有任务所需要的最短时间
         */
        public int leastInterval(char[] tasks, int n) {
            int[] count = new int[26];
            for (char task : tasks) {
                count[task - 'A']++;
            }

            // max 桶的高度，是由任务最大出现次数决定的
            int max = 0;
            for (int c : count) {
                max = Math.max(max, c);
            }

            //  maxCount 任务出现次数最多的任务有多少个
            int maxCount = 0;
            for (int c : count) {
                if (c == max) {
                    maxCount++;
                }
            }

            // 任务是否能填满要看 maxCount 是否 >= n + 1
            // 如果满足条件则表示桶能靠同样最大出现次数的几个任务直接填满桶宽（n + 1）
            // 那么剩下的任务再从左到右，从上往下排都不会在冷切时间内被安排
            // 则完成所有任务所需要的最短时间就是任务数量
            // 不满足条件按照桶的规则，先排 桶宽（n + 1) * (max - 1),
            // 剩下再填多出来的任务出现次数最多的几个任务，也就是 maxCount
            return Math.max((n + 1) * (max - 1) + maxCount, tasks.length);
        }

    }

    public static void main(String[] args) {
//        char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        char[] tasks = {'A','A','A','B','B','B','C','C','C','D','D','E'};
        int n = 2;
        System.out.println(new Solution().leastInterval(tasks, n));
    }
}

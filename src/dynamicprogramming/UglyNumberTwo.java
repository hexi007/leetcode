package dynamicprogramming;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * description 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * @author 27771
 * create 2021-04-11 09:52
 **/
public class UglyNumberTwo {

    static class Solution {

        /**
         * 堆的思想，每次出最小的，再不断加入新的丑数，过程中注意去重
         * (执行用时：65 ms, 在所有 Java 提交中击败了19.33%的用户)
         * (内存消耗：38.1 MB, 在所有 Java 提交中击败了15.63%的用户)
         *
         * @param n 整数
         * @return  第 n 个丑数
         */
        public int nthUglyNumber(int n) {
            // 最小堆
            PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(1L);

            HashSet<Long> hashSet = new HashSet<>();
            hashSet.add(1L);

            // 质因子数组
            int[] factors = {2, 3, 5};
            long currentUgly = 1L;

            for (int i = 0; i < n; i++) {
                assert priorityQueue.peek() != null;
                currentUgly = priorityQueue.poll();
                for (int factor : factors) {
                    Long tempUgly = currentUgly * factor;
                    if (!hashSet.contains(tempUgly)) {
                        hashSet.add(tempUgly);
                        priorityQueue.add(tempUgly);
                    }
                }
            }

            return (int)currentUgly;
        }

        /**
         * 动态规划 + 三指针
         * 用一个有序数组dp记录前n个丑数。三个指针l2，l3和l5指向dp中的元素，
         * 最小的丑数只可能出现在dp[l2]的2倍、dp[l3]的3倍和dp[l5]的5倍三者中间。
         * 通过移动三个指针，就能保证生成的丑数是有序的。
         * (执行用时：3 ms, 在所有 Java 提交中击败了81.63%的用户)
         * (内存消耗：37.3 MB, 在所有 Java 提交中击败了78.97%的用户)
         *
         * @param n 整数
         * @return  第 n 个丑数
         */
        public int nthUglyNumber1(int n) {
            int[] dp = new int[n];
            dp[0] = 1;
            int l2 = 0, l3 = 0, l5 = 0;

            for (int i = 1; i < n; i++) {
                dp[i] = Math.min(Math.min(dp[l2] * 2, dp[l3] * 3), dp[l5] * 5);
                if (dp[i] == dp[l2] * 2) {
                    ++l2;
                }
                if (dp[i] == dp[l3] * 3) {
                    ++l3;
                }
                if (dp[i] == dp[l5] * 5) {
                    ++l5;
                }
            }

            return dp[n - 1];
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(new Solution().nthUglyNumber(n));
        System.out.println(new Solution().nthUglyNumber1(n));
    }
}

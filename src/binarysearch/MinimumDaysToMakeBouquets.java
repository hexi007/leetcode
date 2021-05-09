package binarysearch;

/**
 * description 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-09 09:46
 **/
public class MinimumDaysToMakeBouquets {

    static class Solution {

        int m, k, len;

        /**
         * 折半确定最少哪一天可以完成任务
         * (执行用时：20 ms, 在所有 Java 提交中击败了72.94%的用户)
         * (内存消耗：47.1 MB, 在所有 Java 提交中击败了69.41%的用户)
         *
         * @param bloomDay 第 i 朵花会在 bloomDay[i] 时盛开
         * @param m        花束数
         * @param k        需要使用花园中 相邻的 k 朵花
         * @return         从花园中摘 m 束花需要等待的最少的天数
         */
        public int minDays(int[] bloomDay, int m, int k) {
            this.len = bloomDay.length;
            if (k * m > len) {
                return -1;
            }

            this.m = m;
            this.k = k;
            int low = 1, high = 1;
            for (int day : bloomDay) {
                high = Math.max(high, day);
            }
            while (low < high) {
                int day = low + (high - low) / 2;
                if (check(bloomDay, day)) {
                    high = day;
                } else {
                    low = day + 1;
                }
            }

            return low;
        }

        /**
         * 统计连续 k 个小于 day 的次数，即最后制成花束数是否大于等于 m
         * @param bloomDay 第 i 朵花会在 bloomDay[i] 时盛开
         * @param day      当前尝试在第几天完成任务
         * @return         是否可以在第 day 天完成任务
         */
        private boolean check(int[] bloomDay, int day) {
            // flowers 临时记录现在有多少个连续小于 k 的 数
            int flowers = 0;
            // bouquets 现有花束数
            int bouquets = 0;
            for (int i = 0; i < len; i++) {
                if (bloomDay[i] <= day) {
                    flowers++;
                    if (flowers == k) {
                        bouquets++;
                        flowers = 0;
                    }
                } else {
                    flowers = 0;
                }
            }

            return bouquets >= m;
        }
    }

    public static void main(String[] args) {
        int[] bloomDay = {1,10,3,10,2};
        int m = 3, k = 1;
        System.out.println(new Solution().minDays(bloomDay, m, k));
    }
}

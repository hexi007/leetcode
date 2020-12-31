package greedyalgorithm;

import java.util.Arrays;

/**
 * description 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:  可以认为区间的终点总是大于它的起点。 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * @author 27771
 * create 2020-12-14 17:03
 **/
public class NonOverlappingIntervals {

    static class Solution {

        /**
         * 按区间终点升序排序后从左往右找尽可能不重复的区间
         * (执行用时：3 ms, 在所有 Java 提交中击败了83.29%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了49.32%的用户)
         *
         * @param intervals 区间集合
         * @return          需要移除区间的最小数量
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, (o1, o2) -> {
                // 区间终点一样就按起点升序排序
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            });

            // right 上一个区间终点，初始为最小值
            // count 需要移除区间的数量
            int right = intervals[0][0], count = 0;
            for (int[] interval : intervals) {
                // 只有当前区间终点大于上一个区间终点才不重叠，否则需要移除区间的数量加一
                if (interval[0] >= right) {
                    right = interval[1];
                } else {
                    count++;
                }
            }

            return count;
        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(new Solution().eraseOverlapIntervals(intervals));
    }


}
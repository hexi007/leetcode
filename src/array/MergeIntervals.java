package array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * description 给出一个区间的集合，请合并所有重叠的区间。
 *
 * @author 27771
 * create 2020-11-08 17:22
 **/
public class MergeIntervals {

    static class Solution {
        /**
         * 先排序再合并区间，手动排序更快
         * (执行用时：=7 ms=, 在所有 Java 提交中击败了=87.93%=的用户)
         * (内存消耗：41.2 MB, 在所有 Java 提交中击败了79.44%的用户)
         * @param intervals 区间集合
         * @return          合并后区间集合
         */
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length <= 1) {
                return intervals;
            }
            Arrays.sort(intervals, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            });
            int left = intervals[0][0], right = intervals[0][1];
            ArrayList<int[]> res = new ArrayList<>();
            for (int i = 1 ; i < intervals.length; i++) {
                if (intervals[i][0] > right) {
                    res.add(new int[]{left, right});
                    left = intervals[i][0];
                    right = intervals[i][1];
                } else if (intervals[i][1] > right) {
                    right = intervals[i][1];
                }
            }
            res.add(new int[]{left, right});
            return res.toArray(new int[0][]);
        }

        private static class pair {
            int left, right;

            public pair(int left, int right) {
                this.left = left;
                this.right = right;
            }
        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = new Solution().merge(intervals);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }


}

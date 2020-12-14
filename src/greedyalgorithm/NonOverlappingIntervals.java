package greedyalgorithm;

/**
 * description 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:  可以认为区间的终点总是大于它的起点。 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * @author 27771
 * create 2020-12-14 17:03
 **/
public class NonOverlappingIntervals {

    static class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {

        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{1,3}};
        System.out.println(new Solution().eraseOverlapIntervals(intervals));
    }
}
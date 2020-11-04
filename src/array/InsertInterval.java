package array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * description 给出一个无重叠的 ，按照区间起始端点排序的区间列表。<br/>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠 <br/>
 *（如果有必要的话，可以合并区间）。<br/>
 *
 * @author 27771
 * create 2020-11-04 09:30
 **/
public class InsertInterval {

    static class Solution {
        /**
         * 寻找左右插入区间，并记录是否有超出的情况
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.65%的用户)
         * (内存消耗：40.9 MB, 在所有 Java 提交中击败了70.16%的用户)
         * @param intervals   区间列表
         * @param newInterval 新区间
         * @return            插入后无重叠的区间列表
         */
        public int[][] insert(int[][] intervals, int[] newInterval) {
            ArrayList<int[]> res = new ArrayList<>();
            if(intervals == null || intervals.length <= 0){
                res.add(newInterval);
                return res.toArray(new int[0][]);
            }

            //寻找左侧插入区间，并判断左侧是否有超出
            int left = -1;
            boolean leftNotOverlap = false;
            for(int i = 0; i < intervals.length; i++){
                if(intervals[i][1] >= newInterval[0]){
                    left = i;
                    //leftOverlap 为 true 表示新区间左侧没有超出
                    leftNotOverlap = newInterval[0] >= intervals[i][0];
                    break;
                }
                res.add(intervals[i]);
            }

            //寻找右侧插入区间，并判断右侧是否有超出
            int right = -1;
            boolean rightNotOverlap = false;
            //保存右侧独立区间
            ArrayList<int[]> rightIntervals = new ArrayList<>();
            for(int i = intervals.length - 1; i >= 0; i--){
                if(intervals[i][0] <= newInterval[1]){
                    right = i;
                    //rightOverlap 为 true 表示新区间右侧没有超出
                    rightNotOverlap = newInterval[1] <= intervals[i][1];
                    break;
                }
                rightIntervals.add(0, intervals[i]);
            }

            //newInterval 区间与 intervals 的任意一区间都不相交
            if(left == -1 || right ==  -1){
                res.add(newInterval);
                res.addAll(rightIntervals);
                return res.toArray(new int[0][]);
            }

            //合并重叠部分
            //如果没有超出的话，该侧用原来区间那一侧
            //否则用新区间那一侧
            int curLeft, curRight;
            if(leftNotOverlap){
                curLeft = intervals[left][0];
            } else {
                curLeft = newInterval[0];
            }
            if(rightNotOverlap){
                curRight = intervals[right][1];
            } else {
                curRight = newInterval[1];
            }

            //添加处理后区间
            res.add(new int[]{curLeft, curRight});
            //添加剩余部分
            res.addAll(rightIntervals);
            return res.toArray(new int[0][]);
        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        int[][] res = new Solution().insert(intervals,
                newInterval);
        for(int[] re : res){
            System.out.println(Arrays.toString(re));
        }
    }
}

package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description  在二维空间中有许多球形的气球。
 * 对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
 * 由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。
 * 开始坐标总是小于结束坐标。  一支弓箭可以沿着 x 轴从不同点完全垂直地射出。
 * 在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
 * 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 *
 * @author 27771
 * create 2020-11-23 20:06
 **/
public class MinimumNumberOfArrowsToBurstBalloons {

    static class Solution {

        /**
         * 先排序再遍历
         * (执行用时：24 ms, 在所有 Java 提交中击败了35.02%的用户)
         * (内存消耗：46 MB, 在所有 Java 提交中击败了81.38%的用户)
         * @param points 气球坐标
         * @return       最小弓箭数
         */
        public int findMinArrowShots(int[][] points) {
            if (points == null || points[0].length == 0) {
                return 0;
            }
            // 防止溢出
            Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
            int res = 1, end = points[0][1];
            for (int[] point : points) {
                if (point[0] > end) {
                    res++;
                    end = point[1];
                }
            }
            return res;
        }

        /**
         * 单纯更快的写法 ，不用 idea 优化编辑格式速度更快
         * (执行用时：19 ms, 在所有 Java 提交中击败了96.54%的用户)
         * (内存消耗：46.1 MB, 在所有 Java 提交中击败了78.09%的用户)
         * @param points 气球坐标
         * @return       最小弓箭数
         */
        public int findMinArrowShots1(int[][] points) {
            if (points == null || points.length <= 0) {
                return 0;
            }

            int result = 1;
            // 根据 开始位置，从小到大 排序原数组
            Arrays.sort(points, (point1, point2) -> {
                return Integer.compare(point1[1], point2[1]);
            });

            /*
                贪心，遍历数组，计算结果
            */
            int curEnd = points[0][1];
            for (int[] point : points) {
                if (point[0] > curEnd) {
                    result++;
                    curEnd = point[1];
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(new Solution().findMinArrowShots(points));
    }
}
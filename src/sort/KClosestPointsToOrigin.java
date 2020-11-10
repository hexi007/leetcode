package sort;

import java.util.Arrays;

/**
 * description 我们有一个由平面上的点组成的列表 points。
 * 需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * 这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * create 2020-11-09 16:17
 *
 * @author 27771
 **/
public class KClosestPointsToOrigin {

    static class Solution {
        //记录点坐标和距离
        static class Point {
            int x, y;
            int distance;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
                this.distance = x*x + y*y;
            }

            /**
             * 点比较函数
             * @param other 另一个点
             * @return      是否比它离原点进
             */
            boolean less(Point other) {
                return distance < other.distance;
            }

            @Override
            public String toString() {
                return "Point{" +
                        "x=" + x +
                        ", y=" + y +
                        ", distance=" + distance +
                        '}';
            }
        };

        /**
         * 建堆调整堆，取前 k 个
         * (执行用时：17 ms, 在所有 Java 提交中击败了72.37% 的用户)
         * (内存消耗：47.1 MB, 在所有 Java 提交中击败了57.05% 的用户)
         * @param points 点集合
         * @param K      前 k 个
         * @return       前 k 个点集合
         */
        public int[][] kClosest(int[][] points, int K) {
            int len = points.length;
            Point[] arr = new Point[len];
            for (int i = 0; i < len; ++i) {
                arr[i] = new Point(points[i][0], points[i][1]);
            }
            for(int i = arr.length / 2 - 1; i >= 0; i--){
                adjustHeap(arr, i, arr.length);
            }
            int[][] res = new int[K][2];
            int k = 0;
            //取前 k 个
            for(int i = arr.length - 1; i >= 0 && k < K; i--){
                res[k++] = new int[]{arr[0].x, arr[0].y};
                swap(arr, 0, i);
                adjustHeap(arr, 0, i);
            }
            return res;
        }

        /**
          调整堆
         * @param a      点对象数组
         * @param index  当前节点
         * @param length 点对象数组长度
         */
        private void adjustHeap(Point[] a, int index, int length) {
            Point temp = a[index];
            for(int k = 2 * index + 1; k < length; k = 2 * k + 1){
                if(k + 1 < length && a[k + 1].less(a[k])){
                    k++;
                }
                if(a[k].less(a[index])) {
                    swap(a, k, index);
                    index = k;
                } else {
                    break;
                }
            }
            a[index] = temp;
        }

        private void swap(Point[] a, int i, int j) {
            Point temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] points = {{68,97},{34,-84},{60,100},{2,31},{-27,-38},{-73,-74},{-55,-39},{62,91},{62,92},{-57,-67}};
        int K = 5;
        int[][] res = new Solution().kClosest(points, K);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }
}
package array;

import java.util.Arrays;

/**
 * description 给定由一些正数（代表长度）组成的数组 A
 * 返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * 如果不能形成任何面积不为零的三角形，返回 0。
 *
 * @author 27771
 * create 2020-11-29 12:34
 **/
public class LargestPerimeterTriangle {

    static class Solution {

        /**
         * 排序后从右开始考虑构成三角形的组合
         * (执行用时：9 ms, 在所有 Java 提交中击败了82.92%的用户)
         * (内存消耗：39 MB, 在所有 Java 提交中击败了84.82%的用户)
         *
         * @param A 数组
         * @return  三角形的最大周长
         */
        public int largestPerimeter(int[] A) {
            Arrays.sort(A);
            for (int i = A.length - 1; i > 1; i--) {
                if (A[i - 2] + A[i - 1] > A[i]) {
                    return A[i - 2] + A[i - 1] + A[i];
                }
            }
            return 0;
        }

        /**
         * 不断找最大的三个数，考虑是否构成三角形
         * (执行用时：4 ms, 在所有 Java 提交中击败了98.45%的用户)
         * (内存消耗：39.1 MB, 在所有 Java 提交中击败了72.35%的用户)
         *
         * @param A 数组
         * @return  三角形的最大周长
         */
        public int largestPerimeter1(int[] A) {
            int a = getMax(A), b = getMax(A), c = getMax(A);
            for (int i = 0; i < A.length; i++) {
                if (b + c > a) {
                    return a + b + c;
                }
                a = b;
                b = c;
                c = getMax(A);
            }
            return 0;
        }

        /**
         * 找最大数
         *
         * @param A 数组
         * @return  最大数
         */
        private int getMax(int[] A) {
            int max = -1, index = -1;
            for (int i = 0; i < A.length; i++) {
                if (A[i] > max) {
                    max = A[i];
                    index = i;
                }
            }

            // 不重复
            if (index != -1) {
                A[index] = -1;
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[] A = {3,2,3,6};
        System.out.println(new Solution().largestPerimeter(Arrays.copyOf(A, A.length)));
        System.out.println(new Solution().largestPerimeter1(Arrays.copyOf(A, A.length)));
    }
}

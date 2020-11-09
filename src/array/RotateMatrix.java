package array;

import java.util.Arrays;

/**
 * description 给你一幅由 N × N 矩阵表示的图像，
 * 其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 * create 2020-11-09 19:43
 *
 * @author 27771
 **/
public class RotateMatrix {

    static class Solution {
        /**
         * 先左右交换，在 / 对角线交换
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了89.01% 的用户)
         * @param matrix 矩阵
         */
        public void rotate(int[][] matrix) {
            int len = matrix.length;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len / 2; j++) {
                    swap(matrix, i, j, i, len - j - 1);
                }
            }
            for (int i = 0; i < len - 1; i++) {
                for (int j = len - i - 1; j >= 0; j--) {
                    swap(matrix, i, j, len - j - 1, len - i - 1);
                }
            }
        }

        private void swap(int[][] matrix, int i, int j, int i1, int j1) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[i1][j1];
            matrix[i1][j1] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        new Solution().rotate(matrix);
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }
}
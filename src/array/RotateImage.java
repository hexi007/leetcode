package array;

import java.util.Arrays;

/**
 * description 给定一个 n × n 的二维矩阵表示一个图像。  将图像顺时针旋转 90 度。
 * 说明：  你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。
 * 请不要使用另一个矩阵来旋转图像。
 *
 * @author 27771
 * create 2020-12-19 19:43
 **/
public class RotateImage {

    static class Solution {

        /**
         * 先左右翻转，再对角线翻转
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了77.36%的用户)
         *
         * @param matrix 矩阵
         */
        public void rotate(int[][] matrix) {
            // n 矩阵变成
            int n = matrix.length;

            // 左右翻转
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < (n >>> 1); j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][n - j - 1];
                    matrix[i][n - j - 1] = temp;
                }
            }

            // 沿从左下到右上的镜像翻转
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n - j - 1][n - i - 1];
                    matrix[n - j - 1][n - i - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        new Solution().rotate(matrix);
        for(int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

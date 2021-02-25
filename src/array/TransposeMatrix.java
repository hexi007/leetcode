package array;

import java.util.Arrays;

/**
 * description 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 * @author 27771
 * create 2021-02-25 09:17
 **/
public class TransposeMatrix {

    static class Solution {

        /**
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.5 MB, 在所有 Java 提交中击败了22.80%的用户)
         *
         * @param matrix 二维整数数组
         * @return       matrix 的 转置矩阵
         */
        public int[][] transpose(int[][] matrix) {
            int row = matrix.length, col = matrix[0].length;
            int[][] res = new int[col][row];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    res[j][i] = matrix[i][j];
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6}};
        int[][] res = new Solution().transpose(matrix);
        for (int[] arr : res) {
            System.out.println(Arrays.toString(arr));
        }
    }
}

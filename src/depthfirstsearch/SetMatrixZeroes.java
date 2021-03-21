package depthfirstsearch;

import java.util.Arrays;

/**
 * description 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 进阶：  一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 你能想出一个仅使用常量空间的解决方案吗？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/set-matrix-zeroes 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-21 15:59
 **/
public class SetMatrixZeroes {

    static class Solution {

        /**
         * 记录行列有 0 的情况。O(m + n) 的额外空间
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.91%的用户)
         * (内存消耗：40.1 MB, 在所有 Java 提交中击败了48.28%的用户)
         *
         * @param matrix m x n 的矩阵
         */
        public void setZeroes(int[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length;
            int[] zero = new int[rows + cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] == 0) {
                        zero[i] = 1;
                        zero[rows + j] = 1;
                    }
                }
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (zero[i] == 1 || zero[rows + j] == 1) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        new Solution().setZeroes(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
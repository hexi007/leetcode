package array;

/**
 * description  编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。
 * 该矩阵具有以下特性：  每行的元素从左到右升序排列。 每列的元素从上到下升序排列。
 *
 * @author 27771
 * create 2021-03-17 17:37
 **/
public class SearchMatrixTwo {

    static class Solution {

        /**
         * 从右上角出发，如果当前数比目标值小就往下走否则往左走
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：44.1 MB, 在所有 Java 提交中击败了77.00%的用户)
         *
         * @param matrix m x n 矩阵
         * @param target 目标值
         * @return       矩阵是否有目标值
         */
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }

            int rows = matrix.length, cols = matrix[0].length;
            int row = 0, col = cols - 1;
            while (row < rows && col >= 0) {
                if (matrix[row][col] == target) {
                    return true;
                }
                if (matrix[row][col] < target) {
                    row++;
                } else {
                    col--;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15},{2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},{10, 13, 14, 17, 24},{18, 21, 23, 26, 30}};
        int target = 5;
        System.out.println(new Solution().searchMatrix(matrix, target));
    }
}

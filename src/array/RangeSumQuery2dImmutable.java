package array;

/**
 * description 给定一个二维矩阵，计算其子矩形范围内元素的总和，
 * 该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 *
 * @author 27771
 * create 2021-03-02 09:13
 **/
public class RangeSumQuery2dImmutable {

    /**
     * 注意边界
     * (执行用时：15 ms, 在所有 Java 提交中击败了60.40%的用户)
     * (内存消耗：44.2 MB, 在所有 Java 提交中击败了32.62%的用户)
     */
    static class NumMatrix {

        // sum[i][j] 记录从 matrix[0][0] 到 matrix[i][j] 这个子矩阵的元素和
        int[][] sum;

        public NumMatrix(int[][] matrix) {
            if(matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            int rows = matrix.length, cols = matrix[0].length;
            sum = new int[rows + 1][cols + 1];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] +  matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum[row2 + 1][col2 + 1] - sum[row1][col2 + 1]
                    - sum[row2 + 1][col1] + sum[row1][col1];
        }
    }

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][] {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}

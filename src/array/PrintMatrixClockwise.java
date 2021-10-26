package array;

import java.util.Arrays;

/**
 * description 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * @author 27771
 * create 2021-10-22 11:22
 **/
public class PrintMatrixClockwise {

    static class Solution {
        public int[] spiralOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return new int[]{};
            }
            int rows = matrix.length, cols = matrix[0].length;
            int[] res = new int[rows * cols];
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int i = 0, j = 0, k = 0, count = rows * cols, index = 0;
            boolean[][] isVisited = new boolean[rows][cols];
            while (count > 0) {
                res[index++] = matrix[i][j];
                isVisited[i][j] = true;
                count--;
                int nextI = i + directions[k][0];
                int nextJ = j + directions[k][1];
                if (nextI < 0 || nextI == rows || nextJ < 0 || nextJ == cols
                        || isVisited[nextI][nextJ]) {
                    k = (k + 1) % 4;
                    nextI = i + directions[k][0];
                    nextJ = j + directions[k][1];
                }
                i = nextI;
                j = nextJ;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(Arrays.toString(new Solution().spiralOrder(matrix)));
    }
}

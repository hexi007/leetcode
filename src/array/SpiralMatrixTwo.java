package array;

import java.util.Arrays;

/**
 * description 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * @author 27771
 * create 2021-03-16 09:23
 **/
public class SpiralMatrixTwo {

    static class Solution {

        /**
         * 思路和 SpiralMatrix 几乎一样
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.5 MB, 在所有 Java 提交中击败了63.57%的用户)
         *
         * @param n 正整数
         * @return  按顺时针生成的数组
         */
        public int[][] generateMatrix(int n) {
            int[][] direction = {{0, 1},{1, 0},{0, -1},{-1, 0}};
            int[][] res = new int[n][n];
            int row = 0, col = 0, index = 0, num = 1;

            while (true) {
                res[row][col] = num++;
                int tempRow = row + direction[index][0];
                int tempCol = col + direction[index][1];

                if (tempRow < 0 || tempRow >= n || tempCol < 0 || tempCol >= n
                        || res[tempRow][tempCol] != 0) {
                    // 同方向不可行，换个方向
                    index = (index + 1) % 4;
                    tempRow = row + direction[index][0];
                    tempCol = col + direction[index][1];
                }

                if (tempRow < 0 || tempRow >= n || tempCol < 0 || tempCol >= n
                        || res[tempRow][tempCol] != 0) {
                    break;
                }

                row = tempRow;
                col = tempCol;
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] res = new Solution().generateMatrix(n);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }
}
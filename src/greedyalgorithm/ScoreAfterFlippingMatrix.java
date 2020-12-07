package greedyalgorithm;

import java.util.Arrays;

/**
 * description 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * 返回尽可能高的分数。
 *
 * @author 27771
 * create 2020-12-07 09:59
 **/
public class ScoreAfterFlippingMatrix {

    static class Solution {

        /**
         * 直截了当的贪心，先让移动后第一列都为 0，再让剩下每一列的 1 都尽可能的多
         * (执行用时：1 ms, 在所有 Java 提交中击败了29.27%的用户)
         * (内存消耗：36.5 MB, 在所有 Java 提交中击败了50.85%的用户)
         *
         * @param matrix 二维矩阵
         * @return       移动后可能高的分数
         */
        public int matrixScore(int[][] matrix) {
            int row = matrix.length, column = matrix[0].length;
            for (int i = 0; i < row; i++) {
                // 行首不为 1 则移动整行
                if (matrix[i][0] == 0) {
                    for (int j = 0; j < column; j++) {
                        matrix[i][j] = 1 - matrix[i][j];
                    }
                }
            }

            for (int j = 1; j < column; j++) {
                // count1 计算该列 1 的数量
                int count1 = 0;
                for (int[] a : matrix) {
                    if (a[j] == 1) {
                        count1++;
                    }
                }
                // 该列 1 的数量小于一半时移动整列
                if (count1 < (row + 1 )/ 2) {
                    for (int i = 0; i < row; i++) {
                        matrix[i][j] = 1 - matrix[i][j];
                    }
                }
            }

            int res = 0;
            for (int[] a : matrix) {
                int temp = 1;
                // 计算该行按照二进制计算结果
                for (int i = column - 1; i >= 0; i--) {
                    if (a[i] == 1) {
                        res += temp;
                    }
                    temp *= 2;
                }
            }

            return res;
        }

        /**
         * 大致思想和前一个方法一样，不同的是不进行移动，考虑每次“移动”对结果带来的影响
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.1 MB, 在所有 Java 提交中击败了86.02%的用户)
         *
         * @param matrix 二维矩阵
         * @return       移动后可能高的分数
         */
        public int matrixScore1(int[][] matrix) {
            int row = matrix.length, column = matrix[0].length;
            int res = 0;
            // 对于第一列移动后行首都是 1，那么每个 1 对最终结果的贡献就是 2 的 （column - 1） 次方
            // 总共 row 行，就是 row * （2 的 （column - 1） 次方）
            res += row * (1 << (column - 1));

            for (int j = 1; j < column; j++) {
                // count1 计算该列 1 的数量
                int count1 = 0;
                for (int[] a : matrix) {
                    // 对于每一行，如果行首是 0，那么该行其实是需要移动的
                    // 所以该位置为 0 的时候 count1 才加一
                    if (a[0] == 0) {
                        count1 += (1 - a[j]);
                    } else {
                        count1 += a[j];
                    }
                }
                // max 每列最多可以有多少个 1
                int max = Math.max(count1, row - count1);
                // 总共贡献 max * （2 的 （column - j - 1） 次方）
                res += max * (1 << (column - j - 1));
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        System.out.println(new Solution().matrixScore(matrix));
        int[][] matrix1 = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        System.out.println(new Solution().matrixScore1(matrix1));
    }
}

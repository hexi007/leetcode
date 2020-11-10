package array;

import java.util.Arrays;

/**
 * description 给定一个含有 M x N 个元素的矩阵（M 行，N 列） <br/>
 * 请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。 <br/>
 * create 2020-11-09 20:40
 *
 * @author 27771
 **/
public class DiagonalTraverse {

    static class Solution {
        /** 
         * 先设方向右上，如果下一个位置不可达就先考虑是否可以向右走
         * 向右不可行就向下并设置方向向左下
         * 方向左下，如果下一个位置不可达就先考虑是否可以向下走
         * 向下不可行就向右并设置方向向右上
         * @param matrix 矩阵
         * @return       对角线遍历数组
         */
        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return new int[0];
            }
            boolean up = true;

            /*
             * lenX 矩阵行数
             * lenY 矩阵列数
             */
            int lenX = matrix.length, lenY = matrix[0].length;
            int[] res = new int[lenX * lenY];
            int k = 0;
            res[k++] = matrix[0][0];

            for (int i = 0, j = 0; k < lenX * lenY; k++) {
                int x, y;
                if (up) {
                    x = i - 1;
                    y = j + 1;
                    // 位置不可行
                    if (x == -1 || y == lenY) {
                        // 向右可行
                        if (y < lenY) {
                            x = i;
                            y = j + 1;
                        } else {
                            x = i + 1;
                            y = j;
                        }
                        up = false;
                    }
                } else {
                    x = i + 1;
                    y = j - 1;
                    // 位置不可行
                    if (x == lenX || y == -1) {
                        // 向下可行
                        if (i + 1 < lenX) {
                            x = i + 1;
                            y = j;
                        } else {
                            x = i;
                            y = j + 1;
                        }
                        up = true;
                    }
                }

                //加入对角线数组
                res[k] = matrix[x][y];
                //更新位置
                i = x;
                j = y;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[] res = new Solution().findDiagonalOrder(matrix);
        System.out.println(Arrays.toString(res));
    }
}
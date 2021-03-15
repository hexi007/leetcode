package array;

import java.util.LinkedList;
import java.util.List;

/**
 * description 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * @author 27771
 * create 2021-03-15 16:40
 **/
public class SpiralMatrix {

    static class Solution {

        /**
         * 先添加当前遍历元素，考虑同方向是否可行，否则换个方向再考虑一遍可行性（此时依然不可行则遍历结束）
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.5 MB, 在所有 Java 提交中击败了79.94%的用户)
         *
         * @param matrix 矩阵
         * @return       请按顺时针螺旋顺序遍历结果
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            int[][] direction = {{0, 1},{1, 0},{0, -1},{-1, 0}};
            int rows = matrix.length, cols = matrix[0].length;
            List<Integer> list = new LinkedList<>();
            int row = 0, col = 0, index = 0;

            while (true) {
                // 先添加当前遍历元素
                list.add(matrix[row][col]);
                // 防止再次访问
                matrix[row][col] = 101;
                // tempRow， tempCol 下一个元素位置
                int tempRow = row + direction[index][0];
                int tempCol = col + direction[index][1];

                if (tempRow < 0 || tempRow >= rows || tempCol < 0 || tempCol >= cols
                        || matrix[tempRow][tempCol] == 101) {
                    // 同方向不可行，换个方向
                    index = (index + 1) % 4;
                    tempRow = row + direction[index][0];
                    tempCol = col + direction[index][1];
                }

                // 再考虑一遍可行性（此时依然不可行则遍历结束）
                if (tempRow < 0 || tempRow >= rows || tempCol < 0 || tempCol >= cols
                        || matrix[tempRow][tempCol] == 101) {
                    break;
                }

                row = tempRow;
                col = tempCol;
            }

            return list;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(new Solution().spiralOrder(matrix));
    }
}
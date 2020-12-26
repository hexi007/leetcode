package array;

/**
 * description 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，
 * 找出只包含 1 的最大矩形，并返回其面积。
 *
 * @author 27771
 * create 2020-12-26 09:53
 **/
public class MaximalRectangle {

    static class Solution {

        int[][] width;

        /**
         * 暴力计算每个数字 1 为矩阵右下角时矩阵面积，效果不是很好
         * (执行用时：7 ms, 在所有 Java 提交中击败了66.83%的用户)
         * (内存消耗：41.9 MB, 在所有 Java 提交中击败了 39.80% 的用户)
         *
         * @param matrix 二维二进制矩阵
         * @return       只包含 1 的最大矩形面积
         */
        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }

            // width 记录每个位置左往右数有多少个 1
            // 比如 width[i][j] 表示矩阵第 i 行在 j 这个位置从左往右数有多少个 1
            width = new int[matrix.length][matrix[0].length];
            int maxArea = 0;

            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    // 如果 matrix[row][col] 为 '1' 就考虑计算 width[row][col] 的值
                    if (matrix[row][col] == '1') {
                        // 初始列对应的 width[row][col] 都是 1， 其他列为前一个元素加一
                        if (col == 0) {
                            width[row][col] = 1;
                        } else {
                            width[row][col] = width[row][col - 1] + 1;
                        }
                    } else {
                        width[row][col] = 0;
                    }

                    // currentWidth 保存当前位置从左往右数矩阵宽度
                    int currentWidth = width[row][col];
                    // 不断向上遍历,行数小于 0 和 该行从左往右数矩阵宽度为 0 时退出
                    for (int upRow = row; upRow >= 0 && width[upRow][col] != 0; upRow--) {
                        // 矩阵最小宽度是由之前矩阵最小宽度和当前位置从左往右数矩阵宽度最小值决定的
                        currentWidth = Math.min(currentWidth, width[upRow][col]);
                        // 当前矩形面积为 长（currentWidth） * 宽（row - upRow + 1）
                        maxArea = Math.max(maxArea, currentWidth * (row - upRow + 1));
                    }
                }
            }

            return maxArea;
        }
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},
                {'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(new Solution().maximalRectangle(matrix));
    }
}

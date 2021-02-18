package array;

import java.util.Arrays;

/**
 * description 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，
 * 但保留其原始数据。  给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reshape-the-matrix 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-17 09:42
 **/
public class ReshapeTheMatrix {

    static class Solution {

        /**
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：40 MB, 在所有 Java 提交中击败了5.05%的用户)
         *
         * @param nums 原始矩阵
         * @param r    重构后矩阵的行数
         * @param c    重构后矩阵的列数
         * @return     重构可行则输出新的重塑矩阵；否则，输出原始矩阵
         */
        public int[][] matrixReshape(int[][] nums, int r, int c) {
            int row = nums.length, col = nums[0].length;
            if (row * col != r * c) {
                return nums;
            }

            int[][] res = new int[r][c];
            int indexRow = 0, indexCol = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (indexCol >= col) {
                        indexRow++;
                        indexCol = 0;
                    }
                    res[i][j] = nums[indexRow][indexCol++];
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2},{3,4}};
        int r = 1, c = 4;
        int[][] res = new Solution().matrixReshape(nums, r, c);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }
}

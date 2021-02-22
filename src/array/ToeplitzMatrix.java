package array;

/**
 * description 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/toeplitz-matrix 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-22 09:25
 **/
public class ToeplitzMatrix {

    static class Solution {

        /**
         * 除首行和第一个元素外每个元素都与左上角相等的矩阵就是托普利茨矩阵
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了83.59%的用户)
         *
         * @param matrix m x n 的矩阵 matrix
         * @return       是否是托普利茨矩阵
         */
        public boolean isToeplitzMatrix(int[][] matrix) {
            for (int i = 1;i < matrix.length; i++) {
                for (int j = 1; j < matrix[i].length; j++) {
                    if (matrix[i][j] != matrix[i - 1][j - 1]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        System.out.println(new Solution().isToeplitzMatrix(matrix));
    }
}

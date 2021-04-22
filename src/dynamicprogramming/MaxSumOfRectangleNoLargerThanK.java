package dynamicprogramming;

/**
 * description 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。  题目数据保证总会存在一个数值和不超过 k 的矩形区域。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-22 10:15
 **/
public class MaxSumOfRectangleNoLargerThanK {

    static class Solution {

        /**
         * 暴力，先求矩阵二维前缀和，再暴力枚举子矩阵四个角坐标
         * (执行用时：260 ms, 在所有 Java 提交中击败了32.06%的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了91.99%的用户)
         *
         * @param matrix 矩阵
         * @param k      最大目标值
         * @return       矩阵内部矩形区域的不超过 k 的最大数值和
         */
        public int maxSumSubMatrix(int[][] matrix, int k) {
            int rows = matrix.length, cols = matrix[0].length;
            int[][] sum = new int[rows + 1][cols + 1];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] +  matrix[i][j];
                }
            }

            int res = Integer.MIN_VALUE;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    for (int m = i; m <= rows; m++) {
                        for (int n = j; n <= cols; n++) {
                            int temp = sum[m][n] + sum[i - 1][j - 1]
                                    - sum[i - 1][n] - sum[m][j - 1];
                            if (temp <= k) {
                                res = Math.max(res, temp);
                            }
                        }
                    }
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 0 ,1}, {0, 2, 3}};
        int k = 3;
        System.out.println(new Solution().maxSumSubMatrix(matrix, k));
    }
}

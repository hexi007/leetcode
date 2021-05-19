package array;

import java.util.PriorityQueue;

/**
 * description 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素
 * matrix[i][j]（下标从 0 开始计数）执行异或运算得到。  请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-19 09:16
 **/
public class FindKthLargestXorCoordinateValue {

    static class Solution {

        /**
         * 动态规划计算当前位置 (a, b) 的 值，使用优先队列求第 k 大的值
         * (执行用时：202 ms, 在所有 Java 提交中击败了70.91%的用户)
         * (内存消耗：166.7 MB, 在所有 Java 提交中击败了32.72%的用户)
         *
         * @param matrix 二维矩阵
         * @param k      第 k 大的
         * @return       matrix 的所有坐标中第 k 大的值
         */
        public int kthLargestValue(int[][] matrix, int k) {
            int m = matrix.length, n = matrix[0].length;
            // dp[i][j] 就是 (i, j) 值
            int[][] dp = new int[m][n];
            dp[0][0] = matrix[0][0];
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.add(dp[0][0]);
            for (int i = 1; i < n; i++) {
                dp[0][i] = dp[0][i - 1] ^ matrix[0][i];
                if (queue.size() < k) {
                    queue.add(dp[0][i]);
                } else {
                    assert !queue.isEmpty();
                    if (dp[0][i] > queue.peek()) {
                        queue.poll();
                        queue.add(dp[0][i]);
                    }
                }
            }
            for (int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] ^ matrix[i][0];
                if (queue.size() < k) {
                    queue.add(dp[i][0]);
                } else {
                    assert !queue.isEmpty();
                    if (dp[i][0] > queue.peek()) {
                        queue.poll();
                        queue.add(dp[i][0]);
                    }
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j - 1] ^ dp[i - 1][j] ^ dp[i][j - 1] ^ matrix[i][j];
                    if (queue.size() < k) {
                        queue.add(dp[i][j]);
                    } else {
                        assert !queue.isEmpty();
                        if (dp[i][j] > queue.peek()) {
                            queue.poll();
                            queue.add(dp[i][j]);
                        }
                    }
                }
            }

            assert !queue.isEmpty();
            return queue.peek();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{5,2},{1,6}};
        int k = 2;
        System.out.println(new Solution().kthLargestValue(matrix, k));
    }
}

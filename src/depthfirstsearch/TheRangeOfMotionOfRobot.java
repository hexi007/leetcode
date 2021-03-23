package depthfirstsearch;

/**
 * description 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-20 10:26
 **/
public class TheRangeOfMotionOfRobot {

    static class Solution {

        boolean[][] visited;
        int count = 0;

        /**
         * (执行用时：1 ms, 在所有 Java 提交中击败了85.13%的用户)
         * (内存消耗：35.6 MB, 在所有 Java 提交中击败了42.53%的用户)
         * @param m 行数
         * @param n 列数
         * @param k 行坐标和列坐标的数位之和最大值
         * @return  机器人能够到达多少个格子
         */
        public int movingCount(int m, int n, int k) {
            visited = new boolean[m][n];
            callDfs(0, 0, k);
            return count;
        }

        /**
         * 深搜注意限制条件
         *
         * @param i 当前行数
         * @param j 当前列数
         * @param k 行坐标和列坐标的数位之和最大值
         */
        private void callDfs(int i, int j, int k) {
            if (i < 0 || i == visited.length || j < 0 || j == visited[0].length
                    || visited[i][j]) {
                return;
            }
            int sum = 0, tempI = i, tempJ = j;
            while (tempI > 0) {
                sum += tempI % 10;
                tempI /= 10;
            }
            while (tempJ > 0) {
                sum += tempJ % 10;
                tempJ /= 10;
            }
            if (sum > k) {
                return;
            }
            count++;
            visited[i][j] = true;
            callDfs(i - 1, j, k);
            callDfs(i + 1, j, k);
            callDfs(i, j - 1, k);
            callDfs(i, j + 1, k);
        }
    }

    public static void main(String[] args) {
        int m = 2, n = 3, k = 1;
        System.out.println(new Solution().movingCount(m, n, k));
    }
}
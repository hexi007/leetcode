package depthfirstsearch;

/**
 * description 给出一个二维数组 a，每个单元格为 0（代表海）或 1（代表陆地）。
 * 移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。
 * 返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-enclaves 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-12 08:37
 **/
public class NumberOfEnclaves {

    static class Solution {

        /**
         * 数组边界优先遍历，再统计所有的 1
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：46.8 MB, 在所有 Java 提交中击败了33.02%的用户)
         *
         * @param a 二维数组
         * @return  网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量
         */
        public int numEnclaves(int[][] a) {
            int row = a.length, col = a[0].length;
            // 数组边界调用优先遍历
            for (int i = 0; i < col; i++) {
                callDfs(a, 0, i);
                callDfs(a, row - 1, i);
            }
            for (int i = 1; i < row - 1; i++) {
                callDfs(a, i , 0);
                callDfs(a, i, col - 1);
            }

            int count = 0;
            for (int[] array : a) {
                for (int num : array) {
                    count += num;
                }
            }

            return count;
        }

        /**
         * 深度优先遍历
         *
         * @param a 二维数组
         * @param i 横坐标位置
         * @param j 纵坐标位置
         */
        private void callDfs(int[][] a, int i, int j) {
            if (i < 0 || i == a.length || j < 0 || j == a[i].length || a[i][j] == 0) {
                return;
            }
            a[i][j] = 0;
            callDfs(a, i - 1, j);
            callDfs(a, i + 1, j);
            callDfs(a, i, j - 1);
            callDfs(a, i, j + 1);
        }
    }

    public static void main(String[] args) {
        int[][] a = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        System.out.println(new Solution().numEnclaves(a));
    }
}

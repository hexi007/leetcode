package breadthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * description 在给定的二维二进制数组 a 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1 。）
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/shortest-bridge 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-11 14:52
 **/
public class ShortestBridge {

    static class Solution {

        int rows, cols;
        // deque 保存碰到的第一个岛的所有格子
        Deque<int[]> deque;

        /**
         * 先深搜找碰到的第一个岛的所有格子，再从这个岛广度遍历知道碰到另一个岛
         * (执行用时：8 ms, 在所有 Java 提交中击败了90.92%的用户)
         * (内存消耗：39.7 MB, 在所有 Java 提交中击败了9.72%的用户)
         *
         * @param a 二维二进制数组
         * @return  必须翻转的 0 的最小数目
         */
        public int shortestBridge(int[][] a) {
            rows = a.length;
            cols = a[0].length;
            deque = new ArrayDeque<>();

            // findOne 找到第一个 1 后深搜，之后不用再找 1 了
            boolean findOne = false;
            for (int i = 0; i < rows && !findOne; i++) {
                for (int j = 0; j < cols; j++) {
                    if (a[i][j] == 1) {
                        callDfs(a, i, j);
                        findOne = true;
                        break;
                    }
                }
            }

            int res = -1;
            // directions 上下左右四个方向
            int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
            // 广度优先遍历
            while (!deque.isEmpty()) {
                int size = deque.size();
                res++;
                for (int i = 0; i < size; i++) {
                    int[] node = deque.pop();
                    for (int[] direction : directions) {
                        int x = node[0] + direction[0];
                        int y = node[1] + direction[1];

                        // 碰到边界或已经访问过继续循环
                        if (x < 0 || x == rows || y < 0 || y == cols || a[x][y] == 2) {
                            continue;
                        }
                        // 找到 1 个 1 表示找到了最短的桥
                        if (a[x][y] == 1) {
                            return res;
                        }
                        deque.add(new int[]{x, y});
                        // 设置 x, y 被访问
                        a[x][y] = 2;
                    }
                }
            }

            return res;
        }

        /**
         * 深度优先遍历
         *
         * @param a 二维数组
         * @param i 横坐标位置
         * @param j 纵坐标位置
         */
        private void callDfs(int[][] a, int i, int j) {
            if (i < 0 || i == rows || j < 0 || j == cols || a[i][j] != 1) {
                return;
            }
            deque.add(new int[]{i, j});
            // 设置 x, y 被访问
            a[i][j] = 2;
            callDfs(a, i - 1, j);
            callDfs(a, i + 1, j);
            callDfs(a, i, j - 1);
            callDfs(a, i, j + 1);
        }

    }

    public static void main(String[] args) {
        int[][] a = {{0,1,0,0,0},{0,1,0,1,1},{0,0,0,0,1},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println(new Solution().shortestBridge(a));
    }
}
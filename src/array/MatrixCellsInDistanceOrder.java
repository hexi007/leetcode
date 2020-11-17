package array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * description 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
 * 其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。
 * （你可以按任何满足此条件的顺序返回答案。）
 *
 * @author 27771
 * create 2020-11-17 09:51
 **/
public class MatrixCellsInDistanceOrder {

    static class Solution {

        /**
         * 队列实现的深度优先搜索
         * (执行用时：17 ms, 在所有 Java 提交中击败了54.56%的用户)
         * (内存消耗：40.2 MB, 在所有 Java 提交中击败了98.03%的用户)
         * @param R  矩阵行
         * @param C  矩阵列
         * @param r0 基础坐标行
         * @param c0 基础坐标列
         * @return   按离基础坐标距离排序的所有单元格
         */
        public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
            int[][] res = new int[R * C][2];
            boolean[][] visited = new boolean[R][C];
            int i = 0;
            Queue<Point> queue = new LinkedList<Point>();
            queue.offer(new Point(r0, c0));
            while (!queue.isEmpty()) {
                Point temp = queue.poll();
                int x = temp.x;
                int y = temp.y;
                if (visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                res[i++] = new int[]{x, y};
                // 上下左右试探
                if (x - 1 >= 0 && !visited[x - 1][y]) {
                    queue.offer(new Point(x - 1, y));
                }
                if (x + 1 < R && !visited[x + 1][y]) {
                    queue.offer(new Point(x + 1, y));
                }
                if (y - 1 >= 0 && !visited[x][y - 1]) {
                    queue.offer(new Point(x, y - 1));
                }
                if (y + 1 < C && !visited[x][y + 1]) {
                    queue.offer(new Point(x, y + 1));
                }
            }
            return res;
        }

        static class Point {
            int x, y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString() {
                return "Point{" +
                        "x=" + x +
                        ", y=" + y +
                        '}';
            }
        }
    }

    public static void main(String[] args) {
        int R = 2, C = 3, r0 = 1, c0 = 2;
        int[][] res = new Solution().allCellsDistOrder(R, C, r0, c0);
        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
    }
}

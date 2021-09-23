package breadthfirstsearch;

import java.util.*;

/**
 * description 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * @author 27771
 * create 2021-09-23 18:21
 **/
public class SurroundedArea {

    static class Solution {

        private int rows, cols;
        private boolean[][] visited;

        public void solve(char[][] board) {
            if (board == null || board.length == 0) {
                return;
            }
            rows = board.length;
            cols = board[0].length;
            visited = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 'O' && !visited[i][j]) {
                        callBfs(board, i, j);
                    }
                }
            }
        }

        private void callBfs(char[][] board, int i, int j) {
            visited[i][j] = true;
            Deque<int[]> queue = new LinkedList<>();
            queue.add(new int[]{i, j});
            int[][] directions = {{-1, 0}, {1, 0}, {-1, 0}, {1, 0}};
            List<int[]> list = new ArrayList<>();
            list.add(new int[]{i, j});
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    int[] index = queue.pop();
                    for (int[] direction : directions) {
                        int x = index[0] + direction[0];
                        int y = index[1] + direction[1];
                        if (x < 0 || x >= rows || y < 0 || y >= cols
                                || board[x][y] == 'X' || visited[x][j]) {
                            continue;
                        }
                        queue.add(new int[]{x, y});
                        list.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            for(int[] index : list) {
                System.out.println(Arrays.toString(index));
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'}, {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        Solution solution = new Solution();
        solution.solve(board);
        for (char[] c : board) {
            System.out.println(c);
        }
    }
}
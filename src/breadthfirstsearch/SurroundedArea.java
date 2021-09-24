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
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            List<int[]> list = new ArrayList<>();
            list.add(new int[]{i, j});
            boolean isSurrounded = true;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    int[] index = queue.pop();
                    for (int[] direction : directions) {
                        int x = index[0] + direction[0];
                        int y = index[1] + direction[1];
                        if (x < 0 || x >= rows || y < 0 || y >= cols) {
                            isSurrounded = false;
                            continue;
                        }
                        if (board[x][y] == 'X' || visited[x][y]) {
                            continue;
                        }
                        queue.add(new int[]{x, y});
                        list.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            if (isSurrounded) {
                for (int[] index : list) {
                    board[index[0]][index[1]] = 'X';
                }
            }
        }

        public void solve1(char[][] board) {
            if (board == null || board.length == 0) {
                return;
            }
            rows = board.length;
            cols = board[0].length;
            for (int i = 0; i < cols; i++) {
                if (board[0][i] == 'O') {
                    bfs(board, 0, i);
                }
                if (board[rows - 1][i] == 'O') {
                    bfs(board, rows - 1, i);
                }
            }
            for (int i = 0; i < rows; i++) {
                if (board[i][0] == 'O') {
                    bfs(board, i, 0);
                }
                if (board[i][cols - 1] == 'O') {
                    bfs(board, i, cols - 1);
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == 'Q') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private void bfs(char[][] board, int i, int j) {
            Deque<int[]> deque = new LinkedList<>();
            deque.push(new int[]{i, j});
            board[i][j] = 'Q';
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int k = 0; k < size; k++) {
                    int[] index = deque.pop();
                    for (int[] direction : directions) {
                        int x = index[0] + direction[0];
                        int y = index[1] + direction[1];
                        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
                                || board[x][y] == 'X' || board[x][y] == 'Q') {
                            continue;
                        }
                        board[x][y] = 'Q';
                        deque.push(new int[]{x, y});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'}, {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        Solution solution = new Solution();
        solution.solve(board);
        solution.solve1(board.clone());
        for (char[] c : board) {
            System.out.println(c);
        }
    }
}
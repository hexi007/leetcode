package backtrackingalgorithm;

/**
 * description 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-11-04 16:23
 **/
public class WordSearch {

    static class Solution {
        private int rows, cols;
        private boolean[][] isVisited;

        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
                return false;
            }
            rows = board.length;
            cols = board[0].length;
            isVisited = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        if (bt(board, i, j, word, 0)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean bt(char[][] board, int i, int j, String word, int k) {
            if (k == word.length()) {
                return true;
            }
            if (i < 0 || i >= rows || j < 0 || j >= cols || isVisited[i][j]) {
                return false;
            }
            if (board[i][j] == word.charAt(k)) {
                isVisited[i][j] = true;
                if (bt(board, i - 1, j, word, k + 1) ||
                        bt(board, i + 1, j, word, k + 1) ||
                        bt(board, i, j - 1, word, k + 1) ||
                        bt(board, i, j + 1, word, k + 1)) {
                    return true;
                }
                isVisited[i][j] = false;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'}
        };
        String word = "abcced";
        System.out.println(new Solution().exist(board, word));
    }
}
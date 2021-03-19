package depthfirstsearch;

/**
 * description 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，
 * 那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * [["a","b","c","e"],
 *  ["s","f","c","s"],
 *  ["a","d","e","e"]]
 *  但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 *  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-17 21:15
 **/
public class WordSearch {

    static class Solution {

        char[] chars;
        int rows, cols;
        boolean[][] visited;

        /**
         * 找到开始字符就深搜
         * (执行用时：5 ms, 在所有 Java 提交中击败了98.22%的用户)
         * (内存消耗：40.3 MB, 在所有 Java 提交中击败了56.74%的用户)
         *
         * @param board 矩阵
         * @param word  目标字符串
         * @return      矩阵中是否存在一条包含某字符串所有字符的路径
         */
        public boolean exist(char[][] board, String word) {
            chars = word.toCharArray();
            rows = board.length;
            cols = board[0].length;
            visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == chars[0]) {
                        // 从 i ， j 开始深搜
                        if (callDfs(board, i, j, 0)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        /**
         * 深度优先搜索
         *
         * @param board 矩阵
         * @param i     当前横坐标
         * @param j     当前纵坐标
         * @param index 当前要对比字符位置
         * @return      从 index 之后的深搜是否存在可行的路径
         */
        private boolean callDfs(char[][] board, int i, int j, int index) {
            // index 等于chars.length 说明已经找到可行路径
            if (index == chars.length) {
                return true;
            }
            // 当前位置不可行
            if (i < 0 || i == rows || j < 0 || j == cols
                    || visited[i][j] || board[i][j] != chars[index]) {
                return false;
            }

            // 设置访问
            visited[i][j] = true;
            // 看上下左右是否有可行路径
            boolean res = callDfs(board, i - 1, j, index + 1)
                    || callDfs(board, i + 1, j, index + 1)
                    || callDfs(board, i, j - 1, index + 1)
                    || callDfs(board, i, j + 1, index + 1);
            // 清除访问
            visited[i][j] = false;

            return res;
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},
                {'A','D','E','F'}};
        String word = "ABCCED";
        System.out.println(new Solution().exist(board, word));
    }
}

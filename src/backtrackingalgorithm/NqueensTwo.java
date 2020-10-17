package backtrackingalgorithm;

/**
 * description n-queens
 *
 * @author 27771
 * create 2020-10-17 09:52
 **/
class NQueensTwo {
    static class Solution {

        /**
         * 记录哪些行有皇后
         */
        private boolean[] col;

        /**
         * ↘对角线是否有皇后（某条对角线对应的摆放位置为 x - y + n - 1）
         */
        private boolean[] dia1;

        /**
         * ↗对角线是否有皇后（某条对角线对应的摆放位置为 x + y）
         */
        private boolean[] dia2;

        /**
         * 初始化
         * (执行用时：1 ms, 在所有 Java 提交中击败了81.33%的用户)
         * (内存消耗：34.9 MB, 在所有 Java 提交中击败了99.96%的用户)
         * @param n 棋盘大小
         * @return 所有可行皇后布局数
         */
        public int totalNQueens(int n) {
            col = new boolean[n];
            dia1 = new boolean[2 * n - 1];
            dia2 = new boolean[2 * n - 1];
            return putQueen(n , 0);
        }

        /**
         *
         * @param n 需要摆放的皇后数
         * @param index 已经摆放的皇后数
         * @return 可行皇后布局数
         */
        private int putQueen(int n, int index) {
            int ret = 0;
            if(index == n){
                return 1;
            }
            for(int i = 0 ;i < n; i++){
                // 表示在 index 行的第 i 列尝试摆放皇后
                if(!col[i] && !dia1[index - i + n -1] && !dia2[index + i]){
                    col[i] = true;
                    dia1[index - i + n -1] = true;
                    dia2[index + i] = true;
                    ret += putQueen(n , index + 1);
                    col[i] = false;
                    dia1[index - i + n -1] = false;
                    dia2[index + i] = false;
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        int ret = new Solution().totalNQueens(7);
        System.out.println(ret);
    }
}

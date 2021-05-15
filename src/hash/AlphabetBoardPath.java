package hash;

/**
 * description 我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
 * 在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]
 * 我们可以按下面的指令规则行动：
 * 如果方格存在，'U' 意味着将我们的位置上移一行；
 * 如果方格存在，'D' 意味着将我们的位置下移一行；
 * 如果方格存在，'L' 意味着将我们的位置左移一列；
 * 如果方格存在，'R' 意味着将我们的位置右移一列；
 * '!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。
 * （注意，字母板上只存在有字母的位置。）
 * 返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/alphabet-board-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/alphabet-board-path 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-15 10:14
 **/
public class AlphabetBoardPath {

    static class Solution {

        /**
         * 由于 z 字符的特性，依次考虑向 上，左，下，右 四个方向
         * (执行用时：1 ms, 在所有 Java 提交中击败了69.54%的用户)
         * (内存消耗：36.2 MB, 在所有 Java 提交中击败了96.55%的用户)
         *
         * @param target 目标字符串
         * @return       任何达成目标的路径
         */
        public String alphabetBoardPath(String target) {
            StringBuilder sb = new StringBuilder();
            char[] chars = target.toCharArray();
            int curRow = 0, curCol = 0, targetRow, targetCol;
            for (char c : chars) {
                targetRow = (c - 'a') / 5;
                targetCol = (c - 'a') % 5;
                // 先考虑往上走
                while (targetRow < curRow) {
                    sb.append("U");
                    curRow--;
                }
                // 再考虑往左走
                while (targetCol < curCol) {
                    sb.append("L");
                    curCol--;
                }
                // 之后考虑往下走
                while (targetRow > curRow) {
                    sb.append("D");
                    curRow++;
                }
                // 最后考虑往右走
                while (targetCol > curCol) {
                    sb.append("R");
                    curCol++;
                }
                sb.append("!");
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String target = "leeze";
        System.out.println(new Solution().alphabetBoardPath(target));
    }
}

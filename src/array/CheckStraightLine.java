package array;

/**
 * description 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
 * 其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-17 20:35
 **/
public class CheckStraightLine {

    static class Solution {

        /**
         * 直线一般式：Ax+By+C=0(A、B不同时为0)【适用于所有直线】
         * A = y2 - y1;
         * B = x1 - x2;
         * C = x2 * y1 - x1 * y2;
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：37.7 MB, 在所有 Java 提交中击败了97.46%的用户)
         *
         * @param coordinates 点数组
         * @return            所有点是否在一条直线上
         */
        public boolean checkStraightLine(int[][] coordinates) {
            int len = coordinates.length;
            int a = coordinates[1][1] - coordinates[0][1];
            int b = coordinates[0][0] - coordinates[1][0];
            int c = (coordinates[1][0] * coordinates[0][1])
                    - (coordinates[0][0] * coordinates[1][1]);
            for (int i = 2; i < len; i++) {
                if (a * coordinates[i][0] + b * coordinates[i][1] + c != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        int[][] coordinates = {{1,2},{2,3},{3,4}};
        System.out.println(new Solution().checkStraightLine(coordinates));
    }
}

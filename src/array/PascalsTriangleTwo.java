package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * @author 27771
 * create 2020-11-11 11:07
 **/
public class PascalsTriangleTwo {

    static class Solution {

        List<Integer> list;
        /**
         * 找数学规律,索引为 n - 1 的行为 n ,且该行共有 n 个值(n > 1),得出第 n 行每个值为:
         * 后一个值等于前一个值然后乘以(n - i) / i
         * 即 numVal = (n - i) / i * res.get(i - 1)
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.3 MB, 在所有 Java 提交中击败了50.72%的用户)
         * @param rowIndex 非负索引 k
         * @return         杨辉三角的第 k 行
         */
        public List<Integer> getRow(int rowIndex) {
            list = new ArrayList<>();
            int n = rowIndex + 1;
            int prior = 1;
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    list.add(1);
                } else {
                    // 加上 (long) 防止溢出
                    long temp = (long)prior * (n - i) / i;
                    prior = (int) temp;
                    list.add(prior);
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
        int rowIndex = 3;
        List<Integer> list = new Solution().getRow(rowIndex);
        System.out.println(Arrays.toString(list.toArray()));
    }
}

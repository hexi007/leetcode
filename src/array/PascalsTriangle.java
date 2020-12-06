package array;

import java.util.ArrayList;
import java.util.List;

/**
 * description 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * @author 27771
 * create 2020-12-06 12:24
 **/
public class PascalsTriangle {

    static class Solution {
        List<List<Integer>> res;

        /**
         * 用上一行结果求下一行
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.2 MB, 在所有 Java 提交中击败了82.47%的用户)
         *
         * @param numRows 行数
         * @return        杨辉三角的前 numRows 行
         */
        public List<List<Integer>> generate(int numRows) {
            res = new ArrayList<>();

            for (int i = 0; i < numRows; ++i) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        temp.add(1);
                    } else {
                        temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                    }
                }
                res.add(temp);
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> res = new Solution().generate(numRows);
        System.out.println("[");
        for (List<Integer> l : res) {
            System.out.print("[");
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }
}

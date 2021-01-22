package array;

import java.util.LinkedList;
import java.util.List;

/**
 * description 对于非负整数 X而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。
 * 例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X + K 的数组形式。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-22 10:34
 **/
public class AddToArrayFormOfInteger {

    static class Solution {

        /**
         * 从末位开始加
         * (执行用时：3 ms, 在所有 Java 提交中击败了98.45%的用户)
         * (内存消耗：40.4 MB, 在所有 Java 提交中击败了25.82%的用户)
         *
         * @param a 数组
         * @param k 非负整数
         * @return  X + K 的数组形式
         */
        public List<Integer> addToArrayForm(int[] a, int k) {
            LinkedList<Integer> res = new LinkedList<>();
            int index = a.length - 1, carry = 0;
            while (index >= 0 && k > 0) {
                int temp = a[index] + k % 10 + carry;
                if (temp >= 10) {
                    res.addFirst(temp % 10);
                    carry = 1;
                } else {
                    res.addFirst(temp);
                    carry = 0;
                }
                index--;
                k = k / 10;
            }
            while (index >= 0) {
                int temp = a[index] + carry;
                if (temp >= 10) {
                    res.addFirst(temp % 10);
                    carry = 1;
                } else {
                    res.addFirst(temp);
                    carry = 0;
                }
                index--;
            }
            while (k > 0) {
                int temp = k % 10 + carry;
                if (temp >= 10) {
                    res.addFirst(temp % 10);
                    carry = 1;
                } else {
                    res.addFirst(temp);
                    carry = 0;
                }
                k = k / 10;
            }
            if (carry == 1) {
                res.addFirst(1);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] a = {2,1,5};
        int k = 806;
        System.out.println(new Solution().addToArrayForm(a, k));
    }
}

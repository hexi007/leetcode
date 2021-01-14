package array;

import java.util.ArrayList;
import java.util.List;

/**
 * description 给定由若干 0 和 1 组成的数组 A。
 * 我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-14 10:57
 **/
public class BinaryPrefixDivisibleByFive {

    static class Solution {

        /**
         *每次操作只需要保留最后一项即可
         * (执行用时：4 ms, 在所有 Java 提交中击败了92.76%的用户)
         * (内存消耗：39.3 MB, 在所有 Java 提交中击败了26.32%的用户)
         *
         * @param  a 0 和 1 组成的数组 A
         * @return N_i 是否可以被 5 整除
         */
        public List<Boolean> prefixesDivBy5(int[] a) {
            List<Boolean> res = new ArrayList<>();
            int sum = 0;
            for (int currA : a) {
                // 每次读取新的一位就乘2，相当于二进制的进位
                sum = ((sum << 1) + currA) % 5;
                res.add(sum == 0);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] a = {0,1,1};
        System.out.println(new Solution().prefixesDivBy5(a));
    }
}

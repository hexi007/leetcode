package array;

/**
 * description  通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。
 * 例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
 * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：
 * 乘法(*)，除法(/)，加法(+)和减法(-)。  例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。
 * 然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
 * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
 * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
 *
 * @author 27771
 * create 2021-04-01 09:48
 **/
public class ClumsyFactorial {

    static class Solution {

        /**
         * 找规律
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：35.2 MB, 在所有 Java 提交中击败了78.71%的用户)
         *
         * @param n 正整数
         * @return  n 的笨阶乘
         */
        public int clumsy(int n) {
            // n > 4, 对 n % 4 讨论
            // 0 : n + 1 + 5 - 5 = n + 1
            // 1 : n + 1 + 2 - 1 = n + 2
            // 2 : n + 1 + 3 - 2 = n + 2
            // 3 : n + 1 + 4 - 6 = n - 1
            int[] nGreaterThanFour = {1, 2, 2, -1};
            // n <= 4, 提前计算结果
            int[] nNotGreaterThanFour = {0, 1, 2, 6, 7};
            return n > 4 ? n + nGreaterThanFour[n % 4] : nNotGreaterThanFour[n % 4];
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(new Solution().clumsy(n));
    }
}

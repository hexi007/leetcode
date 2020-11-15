package string;

/**
 * description 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小.
 *
 * @author 27771
 * create 2020-11-15 12:37
 **/
public class RemoveDigits {

    static class Solution {
        /**
         * 用数组模拟一个单调栈，当前数大于栈顶不断退栈，最后整理结果
         * (执行用时：3 ms, 在所有 Java 提交中击败了97.04%的用户)
         * (内存消耗：38.1 MB, 在所有 Java 提交中击败了99.42%的用户)
         * @param num 非负整数字符串
         * @param k   删除 k 个数
         * @return    移除 num 中的 k 位数字后剩下的数字字符串最小
         */
        public String removeKdigits(String num, int k) {
            char[] chars = num.toCharArray();
            char[] stack = new char[10003];
            int stackIndex = 0;
            for (char c : chars) {
                // 当前数大于栈顶不断退栈
                while (stackIndex > 0 && c < stack[stackIndex - 1] && k > 0) {
                    stackIndex--;
                    k--;
                }
                // 单调进栈
                stack[stackIndex++] = c;
            }

            // 栈处理完还没有删足够的数，还不断从栈中删除数
            while (k > 0 && stackIndex > 0) {
                stackIndex--;
                k--;
            }

            StringBuilder sb = new StringBuilder();
            // 逆序出栈，注意开头是否为 0
            boolean leadingZero = true;
            for (int i = 0; i < stackIndex; i++) {
                // 开头为 0 且当前数还是 0 ，不放入结果
                if (leadingZero && stack[i] == '0') {
                    continue;
                }
                // 只要出现不为 0 的数，以后都不考虑开头为 0 的情况
                leadingZero = false;
                sb.append(stack[i]);
            }
            // 考虑最后空串的情况
            return sb.length() == 0 ? "0" : sb.toString();
        }
    }

    public static void main(String[] args) {
        String num = "1234567890";
        int  k = 9;
        System.out.println(new Solution().removeKdigits(num, k));
    }
}

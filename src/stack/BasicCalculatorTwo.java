package stack;

/**
 * description 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。  整数除法仅保留整数部分。
 *
 * @author 27771
 * create 2021-03-11 09:48
 **/
public class BasicCalculatorTwo {

    static class Solution {

        /**
         * 减号做负数处理，乘除直接算
         * (执行用时：8 ms, 在所有 Java 提交中击败了94.48%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了77.22%的用户)
         *
         * @param s 字符串表达式
         * @return  表达式值
         */
        public int calculate(String s) {
            char[] sChar = s.toCharArray();
            int len = sChar.length;
            int[] stack = new int[len];
            int num = 0, stackIndex = 0;
            // op 保存前一个符号
            char op = '+';

            for (int i = 0; i <= len; i++) {
                if (i < len && sChar[i] == ' ') {
                    continue;
                }
                if (i < len && Character.isDigit(sChar[i])) {
                    num = sChar[i] - '0';
                    while (i + 1 < len && Character.isDigit(sChar[i + 1])) {
                        num = num * 10 + sChar[++i] - '0';
                    }
                } else {
                    if (op == '+') {
                        stack[stackIndex++] = num;
                    } else if (op == '-') {
                        stack[stackIndex++] = -num;
                    } else {
                        int numberPeek = stack[--stackIndex];
                        if (op == '*') {
                            stack[stackIndex++] = numberPeek * num;
                        } else if (op == '/') {
                            stack[stackIndex++] = numberPeek / num;
                        }
                    }

                    if (i < len) {
                        op = sChar[i];
                    }
                }
            }

            // 栈内所有数求和就是结果
            int res = stack[--stackIndex];
            while (stackIndex > 0) {
                res += stack[--stackIndex];
            }

            return  res;
        }
    }

    public static void main(String[] args) {
        String s = " 3/2";
        System.out.println(new Solution().calculate(s));
    }
}
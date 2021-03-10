package stack;

import java.util.Stack;

/**
 * description  实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * @author 27771
 * create 2020-12-10 10:08
 **/
public class BasicCalculator {

    static class Solution {

        Stack<Integer> stack;
        char[] chars;

        /**
         * 栈求表达式值
         * (执行用时：7 ms, 在所有 Java 提交中击败了93.33%的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了94.16%的用户)
         *
         * @param s 字符串表达式
         * @return  字符串表达式的值
         */
        public int calculate(String s) {
            stack = new Stack<>();
            // sign 表示正负号 res 保存结果
            int sign = 1, res = 0;
            chars = s.toCharArray();
            int len = chars.length;

            for (int  i = 0; i  < len; i++) {
                char c = chars[i];
                if (Character.isDigit(c)) {
                    // 当前字符是数字
                    int temp = c - '0';
                    // 取出完整数字
                    while (i + 1 < len && Character.isDigit(chars[i + 1])) {
                        temp = temp * 10 + chars[++i] - '0';
                    }
                    // 根据正负号保存 res
                    res = res + sign * temp;
                } else if (c == '+') {
                    // 修正正负号
                    sign = 1;
                } else if (c == '-') {
                    // 修正正负号
                    sign = -1;
                } else if (c == '(') {
                    // 遇到左括号，保存 res
                    stack.push(res);
                    // 为了计算括号内结果，res 置零
                    res = 0;
                    // 保存正负号
                    stack.push(sign);
                    // 括号开始时默认正负号为正
                    sign = 1;
                } else if (c == ')') {
                    // 遇到右括号计算括号内的值（stack.pop() * res）和 之前保存的值之和
                    // 其中一开始取的栈顶是正负号， 所以 stack.pop() * res 是括号内的值
                    // 下一个 stack.pop() 是括号之前保存的结果
                    res = stack.pop() * res + stack.pop();
                }
            }

            return res;
        }

        int[] arrayStack;
        int stackIndex = 0;

        /**
         * 数组模拟栈实现
         * (执行用时：7 ms, 在所有 Java 提交中击败了93.33%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了68.25%的用户)
         *
         * @param s 字符串表达式
         * @return  字符串表达式的值
         */
        public int calculate1(String s) {
            // sign 表示正负号 res 保存结果
            int sign = 1, res = 0;
            chars = s.toCharArray();
            int len = chars.length;
            arrayStack = new int[len];

            for (int  i = 0; i  < len; i++) {
                char c = chars[i];
                if (Character.isDigit(c)) {
                    // 当前字符是数字
                    int temp = c - '0';
                    // 取出完整数字
                    while (i + 1 < len && Character.isDigit(chars[i + 1])) {
                        temp = temp * 10 + chars[++i] - '0';
                    }
                    // 根据正负号保存 res
                    res = res + sign * temp;
                } else if (c == '+') {
                    // 修正正负号
                    sign = 1;
                } else if (c == '-') {
                    // 修正正负号
                    sign = -1;
                } else if (c == '(') {
                    // 遇到左括号，保存 res
                    arrayStack[stackIndex++] = res;
                    // 为了计算括号内结果，res 置零
                    res = 0;
                    // 保存正负号
                    arrayStack[stackIndex++] = sign;
                    // 括号开始时默认正负号为正
                    sign = 1;
                } else if (c == ')') {
                    // 遇到右括号计算括号内的值（stack.pop() * res）和 之前保存的值之和
                    // 其中一开始取的栈顶是正负号， 所以 stack.pop() * res 是括号内的值
                    // 下一个 stack.pop() 是括号之前保存的结果
                    int pop = arrayStack[--stackIndex];
                    res = pop * res + arrayStack[--stackIndex];
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(new Solution().calculate(s));
        System.out.println(new Solution().calculate1(s));
    }
}

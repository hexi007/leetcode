package stack;

import java.lang.reflect.Array;
import java.util.Stack;

/**
 * description 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。
 * 整数除法仅保留整数部分。 示例 : 输入: "3+2*2" 输出: 7 输入:"3+5/2" 输出: 5
 *
 * @author 27771
 * create 2020-12-29 16:59
 **/
public class BasicCalculate {

    static class Solution {

        /**
         * 计算器
         * @param s string字符串 公式
         * @return  long长整型
         */
        public long calculate (String s) {
            Stack<Long> numbers  = new Stack<>();
            Stack<Character> operators =  new Stack<>();
            char[] sChar = s.toCharArray();
            int len = sChar.length;

            for (int i = 0; i < len; i++) {
                char c = sChar[i];
                if (c == ' ') {
                    continue;
                }
                if (Character.isDigit(c)) {
                    long temp = c - '0';
                    while (i + 1 < len && Character.isDigit(sChar[i + 1])) {
                        temp = temp * 10 + sChar[++i] - '0';
                    }
                    if (operators.isEmpty()) {
                        numbers.push(temp);
                    } else if (operators.peek() == '+' || operators.peek() == '-') {
                        numbers.push(temp);
                    } else {
                        long numberPeek = numbers.pop();
                        if (operators.peek() == '*') {
                            numbers.push(numberPeek * temp);
                        } else if (operators.peek() == '/'){
                            numbers.push(numberPeek / temp);
                        }
                        operators.pop();
                    }
                } else {
                    operators.push(c);
                }
            }


//            System.out.println(numbers);
//            System.out.println(operators);

            long res = numbers.pop();
            while (!operators.isEmpty()) {
                long numberPeek = numbers.pop();
                if (operators.pop() == '+') {
                    res = numberPeek + res;
                } else {
                    res = numberPeek - res;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        String s = "3+5 / 2";
        System.out.println(new Solution().calculate(s));
    }
}
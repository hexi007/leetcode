package stack;

import java.util.Stack;

/**
 * description 给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
 * 有效的表达式需遵循以下约定：  " t "，运算结果为 True " f "，运算结果为 False
 * "! ( expr )"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
 * "& ( expr1, expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
 * "| ( expr1, expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）
 *
 * @author 27771
 * create 2020-12-03 13:44
 **/
public class ParsingBooleanExpression {

    static class Solution {

        // operands 操作数栈
        Stack<Character> operands;
        // operators 操作符栈
        Stack<Character> operators;

        /**
         * 模拟表达式求值
         * (执行用时：14 ms, 在所有 Java 提交中击败了40.51%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了73.06%的用户)
         *
         * @param expression 布尔表达式
         * @return           运算结果
         */
        public boolean parseBoolExpr(String expression) {
            operands = new Stack<>();
            operators = new Stack<>();

            char[] chars = expression.toCharArray();
            for (int i = 0; i < expression.length(); i++) {

                // 不是 ) 直接入操作数栈或操作符栈
                if (chars[i] == '!' || chars[i] == '&' || chars[i] == '|') {
                    operators.push(chars[i]);
                } else if (chars[i] == '(' || chars[i] == 'f' || chars[i] == 't') {
                    operands.push(chars[i]);
                } else if (chars[i] == ')'){
                    // 获取括号内初始结果
                    boolean temp = operands.pop() == 't';

                    // 获取对当前括号内容的操作符
                    char operator = operators.pop();
                    // 根据操作符的不同对括号内进行不同的运算
                    if (operator == '&') {
                        while (!operands.isEmpty()) {
                            char operand = operands.pop();
                            if (operand == 't' || operand == 'f') {
                                // 不断进行 & 运算
                                temp &= operand == 't';
                            } else {
                                break;
                            }
                        }
                    } else if (operator == '|') {
                        while (!operands.isEmpty()) {
                            char operand = operands.pop();
                            if (operand == 't' || operand == 'f') {
                                // 不断进行 | 运算
                                temp |= operand == 't';
                            } else {
                                break;
                            }
                        }
                    } else if (operator == '!') {
                        // 操作符是 '!' 时取反
                        temp = !temp;
                        operands.pop();
                    }
                    // 将最终结果放回操作数栈
                    operands.push(temp ? 't' : 'f');
                }
            }
            // 根据操作数栈内最终结果确定返回结果
            return operands.peek() == 't';
        }
    }

    public static void main(String[] args) {
        String expression = "|(&(t,f,t),!(t))";
        System.out.println(new Solution().parseBoolExpr(expression));
    }
}
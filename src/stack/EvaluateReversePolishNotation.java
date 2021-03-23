package stack;

/**
 * description 根据 逆波兰表示法，求表达式的值。  有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：  整数除法只保留整数部分。 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-20 09:41
 **/
public class EvaluateReversePolishNotation {

    static class Solution {

        /**
         * 遇到操作符就连取两个栈顶计算，否则操作数直接入栈
         * (执行用时：3 ms, 在所有 Java 提交中击败了99.81%的用户)
         * (内存消耗：38 MB, 在所有 Java 提交中击败了91.43%的用户)
         *
         * @param tokens 逆波兰表达式
         * @return       表达式运算结果
         */
        public int evalRrn(String[] tokens) {
            int len = tokens.length;
            int[] stack = new int[len];
            int stackIndex = -1;

            for (String s : tokens) {
                switch (s) {
                    case "+" :
                        stack[--stackIndex] += stack[stackIndex + 1];
                        break;
                    case "-" :
                        stack[--stackIndex] -= stack[stackIndex + 1];
                        break;
                    case "*" :
                        stack[--stackIndex] *= stack[stackIndex + 1];
                        break;
                    case "/" :
                        stack[--stackIndex] /= stack[stackIndex + 1];
                        break;
                    default :
                        stack[++stackIndex] = Integer.parseInt(s);
                        break;
                }
            }

            return stack[0];
        }
    }

    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(new Solution().evalRrn(tokens));
    }
}
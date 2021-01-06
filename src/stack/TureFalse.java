package stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * description 给出一个布尔表达式的字符串，比如：true or false and false，表达式只包含true，false，and和or，
 * 现在要对这个表达式进行布尔求值，计算结果为真时输出true、为假时输出false，不合法的表达时输出error（比如：true true）。
 * 表达式求值是注意 and 的优先级比 or 要高，比如：true or false and false，等价于 true or (false and false)，计算结果是 true。
 *
 * @author 27771
 * create 2021-01-06 15:01
 **/
public class TureFalse {

    static class Solution {
        public String calculate(String input) {
            String[] words = input.split(" ");
            //System.out.println(Arrays.toString(words));
            Stack<String> stack = new Stack<>();

            for (String word : words) {
                if (stack.isEmpty()) {
                    if ("and".equals(word) || "or".equals(word)) {
                        return "error";
                    }
                    stack.push(word);
                    //System.out.println("push " + word);
                } else {
                    if ("true".equals(word) || "false".equals(word)) {
                        if ("and".equals(stack.peek())) {
                            stack.pop();
                            String beforeWord = stack.pop();
                            if (beforeWord != null) {
                                if ("true".equals(word) && "true".equals(beforeWord)) {
                                    stack.push("true");
                                } else {
                                    stack.push("false");
                                }
                            } else {
                                return "error";
                            }
                        } else if ("or".equals(stack.peek())) {
                            stack.push(word);
                        } else {
                            return "error";
                        }
                    } else {
                        if ("true".equals(stack.peek()) || "false".equals(stack.peek())) {
                            stack.push(word);
                            //System.out.println("push " + word);
                        } else {
                            return "error";
                        }
                    }
                }
            }

            //System.out.println(stack);
            if ((stack.size() & 1) == 0) {
                return "error";
            }
            for (String word : stack) {
                if ("true".equals(word)) {
                    return "true";
                }
            }

            return "false";
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(new Solution().calculate(input));
    }
}
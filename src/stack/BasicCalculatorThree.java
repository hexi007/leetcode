package stack;

import java.util.Stack;

/**
 * description 请写一个整数计算器，支持加减乘三种运算和括号。
 * 保证计算结果始终在整型范围内
 * 要求：空间复杂度： O(n)O(n)，时间复杂度 O(n)O(n)
 *
 * @author 27771
 * create 2021-10-14 12:26
 **/
public class BasicCalculatorThree {

    static class Solution {


        public int calculate (String s) {
            Stack<Character> ops = new Stack<>();
            Stack<Integer> nums = new Stack<>();
            char[] ch = s.toCharArray();
            for(int i = 0;i < ch.length;i++){
                if(ch[i] == '(' || ch[i] == '*' ) {
                    ops.push(ch[i]);
                } else if(Character.isDigit(ch[i])) {
                    int num = 0;
                    while(i<ch.length && Character.isDigit(ch[i])) {
                        num = num * 10 + ch[i++] - '0';
                    }
                    nums.push(num);
                    i--;
                }
                else if(ch[i] == '+' || ch[i] == '-' ){
                    calculate(ops, nums);
                    ops.push(ch[i]);
                }
                else if(ch[i] == ')'){
                    calculate(ops, nums);
                    ops.pop();
                }
            }
            while(!ops.empty()){
                calculate(ops, nums);
            }
            return nums.pop();
        }

        public void calculate(Stack<Character> ops, Stack<Integer> nums) {
            char leftParentheses = '(';
            while(!ops.empty() && ops.peek() != leftParentheses) {
                int num1 = nums.pop();
                int num2 = nums.pop();
                int res = 0;
                char op = ops.pop();
                if(op == '+') {
                    res = num2 + num1;
                }
                if(op == '-') {
                    res = num2 - num1;
                }
                if(op == '*') {
                    res = num2 * num1;
                }
                nums.push(res);
            }
        }
    }

    public static void main(String[] args) {
        String s = "( 3+2*3*4-1)";
        System.out.println(new Solution().calculate(s));
    }
}

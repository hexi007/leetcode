package stack;

import java.util.Stack;

public class BaseballGame {

    static class Solution {
        public int calPoints(String[] ops) {
            Stack<Integer> stack = new Stack<Integer>();
            for (String op : ops) {
                switch (op) {
                    case "C":
                        stack.pop();
                        break;
                    case "D":
                        Integer stackPeek = stack.peek();
                        stack.push(stackPeek * 2);
                        break;
                    case "+":
                        Integer stackPopFirst = stack.pop();
                        Integer stackPopSecond = stack.pop();
                        stack.push(stackPopSecond);
                        stack.push(stackPopFirst);
                        stack.push(stackPopFirst + stackPopSecond);
                        break;
                    default:
                        stack.push(Integer.valueOf(op));
                }
            }
            int ret = 0;
            while (!stack.isEmpty())    ret += stack.pop();
            return ret;
        }

        //速度飞快
        public int calPoints1(String[] ops) {
            // 先把"C"清理干净，"C"的作用是取消其左边最近的一个数（"D"和"+"都可以看作一个数）
            // 形如["-60","D","-36","D","13","C","C","-33","+","79"]
            // 第一个"C"取消"13"，第二个"C"取消"D"
            // 目标就是把这两个"C"和"13"、"D"都从原数组中移除
            // 得到只包含数字和"D"、"+"的数组 ["-60","D","-36","-33","+","79"]
            //过程中“C”和要取消的数或操作都会被替换为“#”
            int i,j=ops.length-1,count=0;
            // j从数组最后面开始往前扫描到"C"字符，i从j往前扫描到"C"要取消的字符
            while (j>=0){
                while(j>=0 && !ops[j].equals("C")) j--;
                i=j-1;
                while (i>=0 && (ops[i].equals("C")||ops[i].equals("#"))) i--;
                if(i>=0) {
                    // 把这两个位置，一个是"C"，一个是"C"要取消的字符
                    // 都变成"#"，并记录替换了多少次
                    ops[i]="#";
                    count++;
                }
                if(j>=0) ops[j]="#";
            }

            // 新建一个新的短数组,把不是"#"的字符按顺序放进去
            String[] newArray = new String[ops.length-count*2];
            int f=0;
            for(int u=0;u<ops.length;u++){
                if(!ops[u].equals("#")) {
                    newArray[f]=ops[u];
                    f++;
                }
            }

            // 把["-60","D","-36","-33","+","79"]赋值给ops
            ops=newArray;

            int round1=0; // 前一轮的前一轮得分
            int round2=0; // 前一轮得分
            int sum = 0;  // 所有轮次的分数和

            for(int p=0;p<ops.length;p++){
                // 分数
                int round = 0;
                if(!("+".equals(ops[p])) && !("D".equals(ops[p]))) {
                    round = Integer.valueOf(ops[p]); // 数字为本轮得分
                    sum+=round;
                }

                if("+".equals(ops[p])){
                    round = round1+round2; // "+"表示本轮得分为前两轮之和
                    sum+=round;
                }

                if("D".equals(ops[p])){
                    round = (2)*(round2); // "D"表示本轮得分为前一轮两倍
                    sum+=round;
                }

                //刷新得分记录
                round1=round2;
                round2=round;
            }

            return sum;
        }
    }

    public static void main(String[] args){
        Solution s = new Solution();
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        int ret = s.calPoints1(ops);
        System.out.println(ret);
    }
}

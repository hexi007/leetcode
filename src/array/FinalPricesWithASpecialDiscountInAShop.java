package array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FinalPricesWithASpecialDiscountInAShop {
    static class Solution {
        //运行很快，内存消耗多
        public int[] finalPrices(int[] prices) {
            int[] ret = new int[prices.length];
            for (int i = 0; i < prices.length; i++) {
                int j = i + 1;
                for (; j < prices.length; j++) {
                    if (prices[j] <= prices[i]) break;
                }
                if (j < prices.length) ret[i] = prices[i] - prices[j];
                else ret[i] = prices[i];
            }
            return ret;
        }

        //速度慢，内存也没有节省多少
        public int[] finalPrices1(int[] prices) {
            for (int i = 0; i < prices.length; i++) {
                int j = i + 1;
                for (; j < prices.length; j++) {
                    if (prices[j] <= prices[i]) break;
                }
                if (j < prices.length) prices[i] = prices[i] - prices[j];
                else prices[i] = prices[i];
            }
            return prices;
        }

        //单调栈，运行时间长，但消耗内存很低
        public int[] finalPrices2(int[] prices) {
            //注意栈内存放的是价格下标 i
            Stack<Integer> stack = new Stack<>();
            for(int i=0; i<prices.length; i++) {
                while(!stack.isEmpty() && prices[stack.peek()]>=prices[i]) {
                    //此处 pop 取得是当前栈顶价格在 price 中的位置
                    int pop = stack.pop();
                    prices[pop] = prices[pop]-prices[i];
                }
                stack.push(i);
            }
            while(!stack.isEmpty()) {
                int pop = stack.pop();
                prices[pop] = prices[pop];
            }
            return prices;
        }
    }

    public static void main(String[] args){
        Solution s = new Solution();
        int[] prices = {8,7,4,2,8,1,7,7,10,1};
        int[] ret = s.finalPrices2(prices);
        for (int i : ret)   System.out.print(i + " ");
    }
}

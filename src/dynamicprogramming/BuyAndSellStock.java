package dynamicprogramming;

/**
 * description 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 *
 * @author 27771
 * create 2021-03-19 16:16
 **/
public class BuyAndSellStock {

    static class Solution {

        /**
         * 记录之前的股票最小价格
         * (执行用时：2 ms, 在所有 Java 提交中击败了66.58%的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了45.16%的用户)
         *
         * @param prices 股票的价格
         * @return       买卖该股票一次可能获得的最大利润
         */
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }

            int minPrice = prices[0], len = prices.length;
            int[] dp = new int[len];
            dp[0] = 0;
            for (int i = 1; i < len; i++) {
                // 更新 minPrice
                minPrice = Math.min(minPrice, prices[i]);
                dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
            }

            return dp[len - 1];
        }
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(new Solution().maxProfit(prices));
    }
}

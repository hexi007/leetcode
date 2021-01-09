package dynamicprogramming;

/**
 * description 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-09 09:59
 **/
public class BuyAndSellStockThree {

    static class Solution {
        // buy sell 购买和出售股票数组
        // buy[i][j] 表示在 price[0...i] 之间进行 j 次交易并持有股票的最大利润
        // sell[i][j] 表示在 price[0...i] 之间进行 j 次交易并不持有股票的最大利润
        int[][] buy, sell;

        /**
         * 动态规划
         * (执行用时：48 ms, 在所有 Java 提交中击败了42.73%的用户)
         * (内存消耗：53.4 MB, 在所有 Java 提交中击败了73.38%的用户)
         *
         * @param prices 股票当天价格
         * @return       最大利润
         */
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            int k = 2;
            // maxTransactions 实际最多交易次数，因为 k 过大时最多只能交易 len / 2 次
            int maxTransactions = Math.min(k, len / 2);

            buy = new int[len][maxTransactions + 1];
            sell = new int[len][maxTransactions + 1];

            // 初始化 buy 和 sell
            buy[0][0] = -prices[0];
            sell[0][0] = 0;
            for (int i = 1; i < maxTransactions; i++) {
                // 第一天的交易均属非法，设为一个很小的值
                buy[0][i] = Integer.MIN_VALUE / 2;
                sell[0][i] = Integer.MIN_VALUE / 2;
            }

            // 动态规划求解
            for (int i = 1; i < len; i++) {
                // 初始化 buy[i][0]
                buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
                for (int j = 1; j <= maxTransactions; j++) {
                    // 动态规划转移方程
                    buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                    sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
                }
            }

            int maxProfit = 0;
            // 最大利润就是 sell[len - 1] 中最大值
            for (int profit : sell[len - 1]) {
                maxProfit = Math.max(maxProfit, profit);
            }

            return maxProfit;
        }
    }

    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(new Solution().maxProfit(prices));
    }
}

package greedyalgorithm;

/**
 * description 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；
 * 非负整数 fee 代表了交易股票的手续费用。  你可以无限次地完成交易，但是你每笔交易都需要付手续费。
 * 如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。  注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-se
 * ll-stock-with-transaction-fee 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2020-12-17 14:58
 **/
public class StockWithTransactionFee {

    static class Solution {

        /**
         * 动态规划
         * (执行用时：7 ms, 在所有 Java 提交中击败了47.73%的用户)
         * (内存消耗：48.8 MB, 在所有 Java 提交中击败了5.01%的用户)
         *
         * @param prices 股票价格数组
         * @param fee    手续费
         * @return       获得利润的最大值
         */
        public int maxProfit(int[] prices, int fee) {
            int len = prices.length;
            // dpNoStock 第 i 天没有股票
            int[] dpNoStock = new int[len + 1];
            dpNoStock[0] = -prices[0];
            // dpNoStock 第 i 天有股票
            int[] dpHaveStock = new int[len + 1];

            // 动态规划转移方程
            for (int i = 1; i < len; i++) {
                dpNoStock[i] = Math.max(dpNoStock[i - 1], dpHaveStock[i - 1] - prices[i]);
                dpHaveStock[i] = Math.max(dpHaveStock[i - 1], dpNoStock[i - 1] + prices[i] - fee);
            }

            return dpHaveStock[len - 1];
        }
    }

    public static void main(String[] args) {
        int[] prices = {1,3,2,8,4,9};
        int fee = 2;
        System.out.println(new Solution().maxProfit(prices, fee));
    }
}
package sliding_window;

/**
 * description  几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。  你的点数就是你拿到手中的所有卡牌的点数之和。
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 *
 * @author 27771
 * create 2021-02-06 09:25
 **/
public class MaximumPointsFromCards {

    static class Solution {

        /**
         * 先只从左边拿卡牌，在考虑从右边拿
         * (执行用时：2 ms, 在所有 Java 提交中击败了95.77%的用户)
         * (内存消耗：47.8 MB, 在所有 Java 提交中击败了21.36%的用户)
         *
         * @param cardPoints 卡牌点数数组
         * @param k          k 张牌
         * @return           可以获得的最大点数
         */
        public int maxScore(int[] cardPoints, int k) {
            int sum = 0;

            // 从左边拿 k 张卡牌获得的点数
            for (int i = 0; i < k; i++) {
                sum += cardPoints[i];
            }

            int maxSum = sum, len = cardPoints.length;

            if (len == k) {
                return sum;
            }

            // 左边回退右边拿
            for (int i = 0; i < k; i++) {
                sum += cardPoints[len - 1 - i] - cardPoints[k - 1 - i];
                maxSum = Math.max(maxSum, sum);
            }

            return maxSum;
        }
    }

    public static void main(String[] args) {
        int[] cardPoints = {1,2,3,4,5,6,1};
        int k = 3;
        System.out.println(new Solution().maxScore(cardPoints, k));
    }
}

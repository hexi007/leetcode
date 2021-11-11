package dynamicprogramming;

import java.util.Arrays;

/**
 * description 给你一个下标从 0 开始的正整数数组 candiesCount ，其中 candiesCount[i] 表示你拥有的第 i 类糖果的数目。
 * 同时给你一个二维数组 queries ，其中 queries[i] = [favoriteTypei, favoriteDayi, dailyCapi] 。
 * 你按照如下规则进行一场游戏：  你从第 0 天开始吃糖果。 你在吃完 所有 第 i - 1 类糖果之前，不能 吃任何一颗第 i 类糖果。
 * 在吃完所有糖果之前，你必须每天 至少 吃 一颗 糖果。 请你构建一个布尔型数组 answer ，用以给出 queries 中每一项的对应答案。
 * 此数组满足：  answer.length == queries.length 。answer[i] 是 queries[i] 的答案。
 * answer[i] 为 true 的条件是：在每天吃 不超过 dailyCapi 颗糖果的前提下，
 * 你可以在第 favoriteDayi 天吃到第 favoriteTypei 类糖果；否则 answer[i] 为 false 。
 * 注意，只要满足上面 3 条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。  请你返回得到的数组 answer 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-11-07 17:22
 **/
public class FavoriteCandyOnFavoriteDay {

    static class Solution {
        public boolean[] canEat(int[] candiesCount, int[][] queries) {
            int count = candiesCount.length;
            long[] sum = new long[count];
            sum[0] = candiesCount[0];
            for (int i = 1; i < count; i++) {
                sum[i] = sum[i - 1] + candiesCount[i];
            }
            boolean[] ans = new boolean[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int[] query = queries[i];
                long lowBound = (query[0] != 0 ? sum[query[0] - 1] : 0) + 1;
                long highBound = sum[query[0]];
                if ((query[1] + 1) * (long)query[2] < lowBound) {
                    continue;
                }
                if (query[1] + 1 > highBound) {
                    continue;
                }
                ans[i] = true;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] candiesCount = {5,2,6,4,1};
        int[][] queries = {{3, 1, 2}, {4, 10, 3}, {3, 10 ,100}, {4, 100, 30}, {1, 3, 1}};
        System.out.println(Arrays.toString(new Solution().canEat(candiesCount, queries)));
    }
}

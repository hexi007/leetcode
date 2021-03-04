package dynamicprogramming;

import java.util.Arrays;

/**
 * description  给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 说明: 不允许旋转信封。
 *
 * @author 27771
 * create 2021-03-04 10:03
 **/
public class RussianDollEnvelopes {

    static class Solution {

        /**
         * 先排序再动态规划求解，和最长递增子序列相似
         * (执行用时：265 ms, 在所有 Java 提交中击败了32.03%的用户)
         * (内存消耗：39.3 MB, 在所有 Java 提交中击败了74.62%的用户)
         *
         * @param envelopes 所有信封
         * @return          最多能有多少个信封能组成一组“俄罗斯套娃”信封
         */
        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes.length == 0) {
                return 0;
            }

            // 按长度升序宽度降序排序
            Arrays.sort(envelopes, (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            });

            int n = envelopes.length;
            int[] dp = new int[n];
            // dp 所有元素设置为 1
            Arrays.fill(dp, 1);
            int res = 1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    // 每个信封都和之前的比较，不断更新自己的 dp
                    if (envelopes[j][1] < envelopes[i][1]) {
                        dp[i] = Math.max(dp[j] + 1, dp[i]);
                    }
                }
                res = Math.max(res, dp[i]);
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[][] envelopes = {{5,4},{6,4},{2,3},{6,7}};
        System.out.println(new Solution().maxEnvelopes(envelopes));
    }
}

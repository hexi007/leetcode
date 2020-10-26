package dynamicprogramming;

/**
 * description 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * @author 27771
 * create 2020-10-26 10:17
 **/
public class OnesAndZeroes {
    static class Solution {
        /**
         暴力背包双物品dp
         第一步，要明确两点，[状态]和[选择]。
         状态有三个， [背包对1的容量]、[背包对0的容量]和 [可选择的字符串]；选择就是把字符串[装进背包]或者[不装进背包]。
         第二步，要明确dp数组的定义：
         首先，[状态]有三个，所以需要一个三维的dp数组。
         dp[i][j][k]的定义：若只使用前i个物品，当背包容量为j个0，k个1时，能够容纳的最多字符串数。
         第三步，根据选择，思考状态转移的逻辑：
         注意，这是一个0-1背包问题，每个字符串只有一个选择机会，要么选择装，要么选择不装。
         如果你不把这第 i 个物品装入背包（等同于容量不足，装不下去），也就是说你不使用strs[i]这一个字符串。
         那么当前的字符串数dp[i][j][k]应该等于dp[i - 1][j][k],继承之前的结果。
         如果你把这第 i 个物品装入了背包，也就是说你使用 strs[i] 这个字符串，那么 dp[i][j] 应该等于
         Max(dp[i - 1][j][k], dp[i][j - count01[0]][k - count01[1]] + 1)。(count01 是根据strs[i]计算出来的。)
         (执行用时：74 ms, 在所有 Java 提交中击败了27.67%的用户)
         (内存消耗：67.4 MB, 在所有 Java 提交中击败了22.06%的用户)
         * @param strs 字符串数组
         * @param m 最多 0 个数
         * @param n 最多 1 个数
         * @return  最大子集大小
         */
        public int findMaxForm(String[] strs, int m, int n) {
            int strsSize = strs.length;
            int[][][] dp = new int[strsSize + 1][m + 1][n + 1];

            for(int i  = 1; i <= strsSize; i++){
                int[] count01 = count(strs[i - 1]);

                for(int j = 0; j <= m; j++){
                    for(int k = 0; k <= n; k++){
                        //考虑是否能装进去
                        if(j < count01[0] || k < count01[1]){
                            dp[i][j][k] = dp[i - 1][j][k];
                        } else {
                            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - count01[0]][k - count01[1]] + 1);
                        }
                    }
                }
            }
            return dp[strsSize][m][n];
        }

        private int[] count(String s) {
            int[] ret = new int[2];
            for (char c : s.toCharArray()){
                ret[c - '0']++;
            }
            return ret;
        }

        /**
         发现空间可以进一步压缩，每次dp都只用到了前一个状态
         (执行用时：28 ms, 在所有 Java 提交中击败了99.81%的用户)
         (内存消耗：37.2 MB, 在所有 Java 提交中击败了99.69%的用户)
         * @param strs 字符串数组
         * @param m 最多 0 个数
         * @param n 最多 1 个数
         * @return  最大子集大小
         */
        public int findMaxForm1(String[] strs, int m, int n){
            int[][] dp = new int[m + 1][n + 1];

            for(String s : strs){
                int zero = 0, one = 0;
                for(int i = 0; i < s.length(); i++){
                    if(s.charAt(i) == '0'){
                        zero++;
                    }else{
                        one++;
                    }
                }

                //注意 i 从 m ，j 从 n 开始迭代
                //只有当前对 0 和 1 的容量大于当前字符串 01 数量时才考虑是否装进背包
                for(int i = m; i >= zero; i--){
                    for(int j = n; j >= one; j--){
                        boolean putIn = (i == zero && j == one) || dp[i - zero][j - one] != 0;
                        if(putIn){
                            dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                        }
                    }
                }
            }
            int max  = 0;
            for(int i = 0; i <= m; i++){
                for(int j = 0; j <= n; j++){
                    max = Math.max(max, dp[i][j]);
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        System.out.println(new Solution().findMaxForm1(strs, 5, 3));
    }
}

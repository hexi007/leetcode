package greedyalgorithm;

/**
 * description 给你一个字符串 s 和一个整数数组 cost ，其中 cost[i] 是从 s 中删除字符 i 的代价。
 * 返回使字符串任意相邻两个字母不相同的最小删除成本。  请注意，删除一个字符后，删除其他字符的成本不会改变。
 *
 * @author 27771
 * create 2020-12-14 15:35
 **/
public class MinimumDeletionCost {

    static class Solution {

        /**
         * 去掉重复的 cost 中最大的数
         * (执行用时：8 ms, 在所有 Java 提交中击败了76.01%的用户)
         * (内存消耗：47.4 MB, 在所有 Java 提交中击败了94.00%的用户)
         *
         * @param s    字符串
         * @param cost 成本数组
         * @return     最小删除成本
         */
        public int minCost(String s, int[] cost) {
            char[] chars = s.toCharArray();
            int len = chars.length , res = 0;
            for (int i = 0; i < len; i++) {
                // maxCost 重复字符串中最大删除成本
                // allCost 所有重复的删除成本之和
                int maxCost = cost[i],  allCost = cost[i];
                while (i + 1 < len && chars[i + 1] == chars[i]) {
                    allCost += cost[i + 1];
                    if (cost[i + 1] > cost[i]) {
                        if (maxCost < cost[i + 1]) {
                            maxCost = cost[i + 1];
                        }
                    } else {
                        if (maxCost < cost[i]) {
                            maxCost = cost[i];
                        }
                    }
                    i++;
                }
                res += allCost - maxCost;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        String s = "aabaa";
        int[] cost = {1,2,3,4,1};
        System.out.println(new Solution().minCost(s, cost));
    }
}
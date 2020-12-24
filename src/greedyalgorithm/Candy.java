package greedyalgorithm;

/**
 * description 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：  每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。 那么这样下来，老师至少需要准备多少颗糖果呢？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/candy 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2020-12-24 10:00
 **/
public class Candy {

    static class Solution {

        int[] candies;

        /**
         * 从左扫描一次再从右扫描一次，每次扫描的规则是右边分数比左边高，右边糖就比左边糖多一个
         * (执行用时：2 ms, 在所有 Java 提交中击败了99.87%的用户)
         * (内存消耗：39.2 MB, 在所有 Java 提交中击败了91.13%的用户)
         *
         * @param ratings 评分数组
         * @return        老师需要准备的糖果总数
         */
        public int candy(int[] ratings) {
            int len = ratings.length;
            // candies 每个孩子获得糖果数组
            candies = new int[len];
            // 开始时每个孩子都只有一个糖果
            for (int i = 0; i < len; i++) {
                candies[i] = 1;
            }

            // 从左往右扫描
            for (int i = 0; i < len - 1; i++) {
                // 孩子 i + 1 评分比孩子 i 高，则孩子 i + 1 的糖果比孩子 i 多一个
                if (ratings[i + 1] > ratings[i]) {
                    candies[i + 1] = candies[i] + 1;
                }
            }
            // 从右往左扫描
            for (int i = len - 1; i > 0; i--) {
                // 孩子 i - 1 评分比孩子 i 高，则孩子 i - 1 的糖果比孩子 i 多一个
                if (ratings[i - 1] > ratings[i]) {
                    // 为了不重复创建每次扫描需要的 candies 数组，
                    // 从右往左扫描时比较更新后的 candies[i - 1] 也就是 candies[i] + 1
                    // 和上面从左往右的原来糖果数 candies[i - 1] 做比较
                    // 选其中最大的作为孩子 i - 1 最终糖果数
                    candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1);
                }
            }

            int res = 0;
            // 所有孩子糖果数之和就是最终结果
            for (int candy : candies) {
                res += candy;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] ratings = {1,2,2};
        System.out.println(new Solution().candy(ratings));
    }
}

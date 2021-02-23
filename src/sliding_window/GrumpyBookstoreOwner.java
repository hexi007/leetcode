package sliding_window;

/**
 * description 今天，书店老板有一家店打算试营业 customers.length 分钟。
 * 每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-23 10:22
 **/
public class GrumpyBookstoreOwner {

    static class Solution {
        
        /**
         * 分两步先求原本就满意的客户数，再求使用秘密技巧作为滑动窗口后最多满意客户数
         * (执行用时：3 ms, 在所有 Java 提交中击败了79.44%的用户)
         * (内存消耗：41.3 MB, 在所有 Java 提交中击败了5.11%的用户)
         *
         * @param customers 每分钟客户数
         * @param grumpy    老板生气时间
         * @param x         连续不生气时长
         * @return          最多满意客户数
         */
        public int maxSatisfied(int[] customers, int[] grumpy, int x) {
            int len = customers.length, initSatisfiedCustomers = 0;
            for (int i = 0; i < len; i++) {
                if (grumpy[i] == 0) {
                    initSatisfiedCustomers += customers[i];
                    // 将原本就满意客户的置为 0
                    customers[i] = 0;
                }
            }

            int currCustomers = 0, max = 0;
            for (int i = 0, j = 0; i < len; i++) {
                currCustomers += customers[i];
                // i, j 间距为 x 时是一个滑动窗口
                if (i - j + 1 > x) {
                    currCustomers -= customers[j++];
                }
                max = Math.max(max, currCustomers);
            }

            return initSatisfiedCustomers + max;
        }
    }

    public static void main(String[] args) {
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        int x = 3;
        System.out.println(new Solution().maxSatisfied(customers, grumpy, x));
    }
}

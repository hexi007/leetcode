package greedyalgorithm;

/**
 * description 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。
 * 你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 注意，一开始你手头没有任何零钱。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *
 * @author 27771
 * create 2020-12-10 09:37
 **/
public class LemonadeChange {

    static class Solution {

        /**
         * 模拟找零钱即可
         * (执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户)
         * (内存消耗：39.6 MB, 在所有 Java 提交中击败了59.66%的用户)
         *
         * @param bills 所有账单
         * @return      是否可以正确找零
         */
        public boolean lemonadeChange(int[] bills) {
            int count5 = 0, count10 = 0;
            for (int bill : bills) {
                switch (bill) {
                    case 5 :
                        count5++;
                        break;
                    case 10 :
                        if (count5 > 0) {
                            count5--;
                            count10++;
                        } else {
                            return false;
                        }
                        break;
                    case 20 :
                        if (count5 > 0 && count10 > 0) {
                            count5--;
                            count10--;
                        } else if (count10 <= 0 && count5 > 2) {
                            count5 -= 3;
                        } else {
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }
            return true;
        }

        /**
         * 无论顾客花费多少钱买柠檬水我们要想顺利找零，手中必须要有 5 美元的零钱
         * 最终只需判断 5 美元是否小于 0 即可
         * (执行用时：2 ms, 在所有 Java 提交中击败了99.72%的用户)
         * (内存消耗：39.7 MB, 在所有 Java 提交中击败了43.93%的用户)
         *
         * @param bills 所有账单
         * @return      是否可以正确找零
         */
        public boolean lemonadeChange1(int[] bills) {
            int count5 = 0, count10 = 0;
            for (int bill : bills) {
                if (bill == 5) {
                    count5++;
                } else if (bill == 10) {
                    count5--;
                    count10++;
                } else if (count10 > 0) {
                    count5--;
                    count10--;
                } else {
                    count5 -= 3;
                }
                if (count5 < 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[] bills = {5,5,5,5,20,20,5,5,5,5};
        System.out.println(new Solution().lemonadeChange(bills));
        System.out.println(new Solution().lemonadeChange1(bills));
    }
}

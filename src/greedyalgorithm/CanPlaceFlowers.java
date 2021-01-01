package greedyalgorithm;

/**
 * description 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。
 * 可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/can-place-flowers 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-01 09:46
 **/
public class CanPlaceFlowers {

    static class Solution {

        /**
         * 扫描花坛数组根据连续 0 的数量种花，特别考虑两端为 0 的情况
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.9 MB, 在所有 Java 提交中击败了70.89%的用户)
         *
         * @param flowerbed 花坛数组
         * @param n         种入 n 朵花
         * @return          能否种下 n 朵花
         */
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            // flowers 纪录种下了多少朵花
            int flowers = 0;
            // countZero 记录扫描过程中有多少个 0 ，开头时 0 时初始化为 1
            int countZero = flowerbed[0] == 1 ? 0 : 1;

            for (int flower : flowerbed) {
                if (flower == 0) {
                    countZero++;
                } else {
                    // 连续 countZero 个 0 表示可以种 (countZero - 1) / 2 朵花
                    flowers += (countZero - 1) / 2;
                    // countZero 置 0
                    countZero = 0;
                }
            }

            // 考虑以 0 结尾的情况
            if (flowerbed[flowerbed.length - 1] == 0) {
                // 0 结尾要多数一个 0
                countZero++;
                flowers += (countZero - 1) / 2;
            }

            return flowers >= n;
        }
    }

    public static void main(String[] args) {
        int[] flowerbed = {1,0,0,0,1};
        int n = 2;
        System.out.println(new Solution().canPlaceFlowers(flowerbed, n));
    }
}

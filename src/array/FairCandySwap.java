package array;

import java.util.Arrays;

/**
 * description 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，
 * B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。
 * （一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fair-candy-swap 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-01 09:42
 **/
public class FairCandySwap {

    static class Solution {

        /**
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.2 MB, 在所有 Java 提交中击败了98.27%的用户)
         *
         * @param a 爱丽丝拥有的所有糖果棒的大小
         * @param b 鲍勃拥有的所有糖果棒的大小
         * @return  爱丽丝与鲍勃交换的糖果
         */
        public int[] fairCandySwap(int[] a, int[] b) {
            boolean[] map = new boolean[100001];
            int sum = 0;

            for (int j : a) {
                sum += j;
            }

            int sumA = sum;

            for (int j : b) {
                sum += j;
                map[j] = true;
            }

            int avg = sum / 2;

            for (int sugarA : a) {
                // 对于爱丽丝当前的糖果 sugarA, 如果要使交换后两者糖果总量一致
                // 则鲍勃需要提供糖果总和的一半加上 sugarA 减去爱丽丝所有糖果总量的糖果大小
                int findInB = avg + sugarA - sumA;
                if (findInB >= 1 && findInB <= 100001 && map[findInB]) {
                    return new int[]{sugarA, findInB};
                }
            }

            return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        int[] a = {2}, b = {1, 3};
        System.out.println(Arrays.toString(new Solution().fairCandySwap(a, b)));
    }
}

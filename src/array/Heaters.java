package array;

import java.util.Arrays;

/**
 * description 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 *
 * @author 27771
 * create 2020-11-01 18:34
 **/
public class Heaters {
    static class Solution {
        /**
         二分查找离当前房屋最近的供暖器距离，所求就是这些房屋最大的这个距离
         (执行用时：20 ms, 在所有 Java 提交中击败了52.63%的用户)
         (内存消耗：41.6 MB, 在所有 Java 提交中击败了86.24%的用户)
         * @param houses  房屋位置
         * @param heaters 供暖器位置
         * @return        可以覆盖所有房屋的最小加热半径
         */
        public int findRadius(int[] houses, int[] heaters) {
            //二分查找需要对象有序
            Arrays.sort(heaters);
            int maxR = 0;
            for(int house : houses){
                //需所有房屋最小供暖器距离最大值
                maxR = Math.max(maxR, search(house, heaters));
            }
            return maxR;
        }

        /**
         二分查找
         * @param house   当前房屋位置
         * @param heaters 供暖器位置
         * @return        当前房屋最近供暖器距离
         */
        private int search(int house, int[] heaters) {
            int left = 0, right = heaters.length - 1;
            while (left <= right) {
                //折半查找
                int mid = left + (right - left) / 2;
                //当前位置有供暖器那么这个距离就为 0
                if(heaters[mid] == house){
                    return 0;
                } else if(heaters[mid] > house){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            //如果一直找最后发现 left 出了 heaters 右边界，说明当前房屋位置是大于最大供暖器位置的，直接相减
            if(left > heaters.length - 1){
                return house - heaters[heaters.length - 1];
            }
            //如果一直找最后发现 right 小于 0，说明当前房屋位置是小于最小供暖器位置的，直接相减
            if(right < 0){
                return heaters[0] - house;
            }
            //最后分别在该房屋两边各找到了最近的供暖器，那么取这两个供暖器到当前房屋最近距离即可
            return Math.min(heaters[left] - house,house -heaters[right]);
        }
    }

    public static void main(String[] args) {
        int[] houses = {1,5};
        int[] heaters = {2};
        System.out.println(new Solution().findRadius(houses, heaters));
    }
}

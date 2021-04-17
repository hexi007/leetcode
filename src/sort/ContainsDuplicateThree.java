package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description  给你一个整数数组 nums 和两个整数 k 和 t 。
 * 请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 *
 * @author 27771
 * create 2021-04-17 12:36
 **/
public class ContainsDuplicateThree {

    static class Solution {

        private static class Num {
            int num, index;

            public Num(int num, int index) {
                this.num = num;
                this.index = index;
            }

            @Override
            public String toString() {
                return "Num{" +
                        "num=" + num +
                        ", index=" + index +
                        '}';
            }
        }

        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            int len = nums.length;
            Num[] numsObject = new Num[len];
            for (int i = 0; i < len; i++) {
                numsObject[i] = new Num(nums[i], i);
            }
            Arrays.sort(numsObject, Comparator.comparingInt(o -> o.num));
            System.out.println(Arrays.toString(numsObject));
            return false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int k = 3, t = 0;
        System.out.println(new Solution().containsNearbyAlmostDuplicate(nums, k , t));
    }
}

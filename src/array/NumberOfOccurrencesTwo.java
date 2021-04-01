package array;

import java.util.HashMap;
import java.util.Map;

/**
 * description 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * @author 27771
 * create 2021-04-01 20:55
 **/
public class NumberOfOccurrencesTwo {

    static class Solution {

        /**
         * hashmap 暴力
         * (执行用时：15 ms, 在所有 Java 提交中击败了40.10%的用户)
         * (内存消耗：39.9 MB, 在所有 Java 提交中击败了11.12%的用户)
         *
         * @param nums 数组
         * @return     只出现一次的数字
         */
        public int singleNumber(int[] nums) {
            HashMap<Integer, Integer> count = new HashMap<>(16);
            for (int num : nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            for(Map.Entry<Integer,Integer> entry : count.entrySet()) {
                if (entry.getValue() == 1) {
                    return entry.getKey();
                }
            }

            return -1;
        }

        /**
         * 每个数用二进制表示，则各二进制位出现的次数都是 3 的倍数，对每位取余剩下的就是单独出现的数的二进制
         * (执行用时：7 ms, 在所有 Java 提交中击败了65.25%的用户)
         * (内存消耗：39.1 MB, 在所有 Java 提交中击败了94.05%的用户)
         *
         * @param nums 数组
         * @return     只出现一次的数字
         */
        public int singleNumber1(int[] nums) {
            int[] counts = new int[32];
            for(int num : nums) {
                for(int j = 0; j < counts.length; j++) {
                    counts[j] += num & 1;
                    num >>>= 1;
                }
            }
            int res = 0, m = 3;
            for(int i = 0; i < counts.length; i++) {
                res <<= 1;
                res |= counts[31 - i] % m;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {9,1,7,9,7,9,7};
        System.out.println(new Solution().singleNumber(nums));
        System.out.println(new Solution().singleNumber1(nums));
    }
}

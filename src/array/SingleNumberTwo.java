package array;

import java.util.*;

/**
 * description 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。
 * 找出那个只出现了一次的元素。
 * 说明：  你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * @author 27771
 * create 2020-11-27 11:16
 **/
public class SingleNumberTwo {

    static class Solution {
        Map<Integer, Integer> map;

        /**
         * hashmap 储存出现次数
         * (执行用时：7 ms, 在所有 Java 提交中击败了13.74%的用户)
         * (内存消耗：38.2 MB, 在所有 Java 提交中击败了81.99%的用户)
         *
         * @param nums 非空整数数组
         * @return     只出现了一次的元素
         */
        public int singleNumber(int[] nums) {
             map = new HashMap<>(16);
             for (int num : nums) {
                 map.put(num, map.getOrDefault(num, 0) + 1);
             }

            for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                if (entry.getValue() == 1) {
                    return entry.getKey();
                }
            }

            return 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,0};
        System.out.println(new Solution().singleNumber(nums));
    }
}

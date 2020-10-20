package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * description 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 *
 * @author 27771
 * create 2020-10-20 11:06
 **/
public class HowManyNumbersAreSmallerThanTheCurrentNumber {
    static class Solution {
        int[] ret;
        /**
         暴力迭代
         (执行用时：20 ms, 在所有 Java 提交中击败了12.97%的用户)
         (内存消耗：38.2 MB, 在所有 Java 提交中击败了99.77%的用户)
         * @param nums 输入数组
         * @return  数组中比它小的所有数字的数目
         */
        public int[] smallerNumbersThanCurrent(int[] nums) {
            ret = new int[nums.length];
            for(int i = 0; i < nums.length; i++){
                for (int j = 0; j < nums.length; j++){
                    if(i == j){
                        continue;
                    }
                    if(nums[j] < nums[i]){
                        ret[i]++;
                    }
                }
            }
            return ret;
        }

        Map<Integer, Integer> map;
        public int[] smallerNumbersThanCurrent1(int[] nums){
            map = new HashMap<>(nums.length);
            ret = new int[nums.length];
            for(int i = 0; i < nums.length ;i++){
                if(map.containsKey(nums[i])){
                    int temp = map.get(nums[i]);
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        if(entry.getValue() < nums[i]){

                        }
                    }
                } else {
                    map.put(nums[i], 0);
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        int[] nums = {6, 5, 4, 8};
        int[] ret = new Solution().smallerNumbersThanCurrent(nums);
        for(Integer i : ret){
            System.out.println(i);
        }
    }
}

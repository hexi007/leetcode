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

        /**
         暴力迭代
         (执行用时：20 ms, 在所有 Java 提交中击败了12.97%的用户)
         (内存消耗：38.2 MB, 在所有 Java 提交中击败了99.77%的用户)
         * @param nums 输入数组
         * @return  数组中比它小的所有数字的数目
         */
        public int[] smallerNumbersThanCurrent(int[] nums) {
            int[] ret = new int[nums.length];
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


        /**
         * 先用index数组保存nums每个元素的位置和出现次数，再用count数组保存比当前元素小的元素个数
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了99.73% 的用户)
         * @param nums 1
         * @return
         */
        public int[] smallerNumbersThanCurrent1(int[] nums){
            int[] index = new int[101];
            for(int i = 0; i < nums.length; i++){
                index[nums[i]]++;
            }

            //preCount记录比当前元素小的个数
            int preCount = 0;
            int[] count = new int[index.length];
            for(int i = 0; i < index.length; i++){
                if(index[i] == 0){
                    continue;
                }
                count[i] = preCount;
                //更新preCount
                preCount += index[i];
            }

            //根据nums[i]将数据保存到ret中
            int[] ret = new int[nums.length];
            for(int i = 0; i < nums.length; i++){
                ret[i] = count[nums[i]];
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        int[] nums = {6, 5, 4, 8};
        int[] ret = new Solution().smallerNumbersThanCurrent1(nums);
        for(Integer i : ret){
            System.out.println(i);
        }
    }
}

package greedyalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * description 给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格 大于未包含在该子序列中的各元素之和。
 * (执行用时：2 ms, 在所有 Java 提交中击败了99.65% 的用户)
 * (内存消耗：38.2 MB, 在所有 Java 提交中击败了99.92% 的用户)
 *
 * @author 27771
 * create 2020-10-20 20:48
 **/
public class MinimumSubsequenceInNonIncreasingOrder {

    static class Solution {
        public List<Integer> minSubsequence(int[] nums) {
            List<Integer> ret = new ArrayList<>();
            if(nums.length == 1){
                ret.add(nums[0]);
                return ret;
            }

            //max储存nums中最大元素值，sum储存nums元素之和，index数组保存nums每个元素的位置和出现次数
            int max = nums[0], sum = 0;
            int[] index = new int[101];
            for(int i = 0; i < nums.length; i++){
                if(max < nums[i]){ max = nums[i];}
                sum += nums[i];
                index[nums[i]]++;
            }

            //tempSum储存返回集合ret所有元素之和
            int tempSum = 0;
            //从最大元素往回走
            for(int i = max; i >= 0; i--){
                if(index[i] == 0) {continue;}
                int temp = index[i];
                while(temp > 0){
                    tempSum += i;
                    ret.add(i);

                    if(tempSum > (sum - tempSum)) {return ret;}
                    temp--;
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 4, 7, 6, 7};
        List<Integer> ret = new Solution().minSubsequence(nums);
        for(Integer i : ret){
            System.out.print(i + " ");
        }
    }
}
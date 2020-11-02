package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * description 给定两个数组，编写一个函数来计算它们的交集。
 *
 * @author 27771
 * create 2020-11-02 18:31
 **/
public class IntersectionOfTwoArrays {
    static class Solution {

        /**
         *  用 HashSet 去重复并判断交集
         *  (执行用时：3 ms, 在所有 Java 提交中击败了95.82% 的用户)
         *  (内存消耗：38.3 MB, 在所有 Java 提交中击败了97.15% 的用户)
         * @param nums1 数组1
         * @param nums2 数组2
         * @return      交集
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            for(int num : nums1){
                set.add(num);
            }
            Set<Integer> resSet = new HashSet<>();
            for(int num : nums2){
                if(set.contains(num)){
                    resSet.add(num);
                }
            }
            int[] res = new int[resSet.size()];
            int i = 0;
            for(int num : resSet){
                res[i++] = num;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] ret = new Solution().intersection(nums1, nums2);
        System.out.println(Arrays.toString(ret));
    }
}
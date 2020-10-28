package array;

import java.util.HashSet;
import java.util.Set;

/**
 * description 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 *
 * @author 27771
 * create 2020-10-28 10:30
 **/
public class UniqueNumberOfOccurrences {

    static class Solution {
        /** 
         直观想法，保存每个元素出现次数，再用 set 判断出现次数是否重复
         (执行用时：2 ms, 在所有 Java 提交中击败了91.43%的用户)
         (内存消耗：37.8 MB, 在所有 Java 提交中击败了13.36%的用户)
         * @param arr 整数数组
         * @return  如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
         */
        public boolean uniqueOccurrences(int[] arr) {
            int[] n = new int[2002];
            for (int a : arr) {
                n[a + 1000]++;
            }
            Set<Integer> set = new HashSet<>();
            for (int i : n) {
                if (i != 0) {
                    if (set.contains(i)) {
                        return false;
                    }
                    set.add(i);
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[] arr = {-3,0,1,-3,1,1,1,-3,10,0};
        System.out.println(new Solution().uniqueOccurrences(arr));
    }
}

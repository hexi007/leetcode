package greedyalgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * description 给你一个按升序排序的整数数组 num（可能包含重复数字），
 * 请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 * @author 27771
 * create 2020-12-04 10:42
 **/
public class SplitArrayIntoConsecutiveSubsequences {

    static class Solution {
        // 记录每个数出现次数
        Map<Integer, Integer> countNum;
        // 记录每个数作结尾的序列数量
        Map<Integer, Integer> tail;

        /**
         * 每次比较数是否能和前面的数构成新的序列，如果不能再考虑能否构造新的序列
         * 上述方法都不可行代表数组不可分割
         * (执行用时：37 ms, 在所有 Java 提交中击败了36.70%的用户)
         * (内存消耗：39.6 MB, 在所有 Java 提交中击败了69.39%的用户)
         *
         * @param nums 升序排序的整数数组
         * @return     可以完成上述分割，返回 true ；否则，返回 false
         */
        public boolean isPossible(int[] nums) {
            countNum = new HashMap<>(16);
            // 记录每个数出现次数
            for (int num : nums) {
                countNum.put(num, countNum.getOrDefault(num, 0) + 1);
            }

            tail = new HashMap<>(16);
            for (int num : nums) {
                // count 每个数出现次数
                int count = countNum.getOrDefault(num, 0);
                // 该数没有剩余跳过
                if (count <= 0) {
                    continue;
                }
                if (tail.getOrDefault(num - 1, 0) > 0) {
                    // 如果前一个数能够作为一个序列的结尾，则将当前数添加到这个序列结尾
                    // 当前数出现次数减一
                    countNum.put(num, count - 1);
                    // 前一个数作为序列结尾的次数减一
                    tail.put(num - 1, tail.get(num - 1) - 1);
                    // 当前数作为序列结尾的次数加一
                    tail.put(num, tail.getOrDefault(num, 0) + 1);
                } else if (countNum.getOrDefault(num + 1, 0) > 0
                        && countNum.getOrDefault(num + 2, 0) > 0) {
                    // 如果当前数的后两个数可以作为一个长度为 3 的序列，构造这个序列
                    // 当前数出现次数减一
                    countNum.put(num, count - 1);
                    // 后面第一个数出现次数减一
                    countNum.put(num + 1, countNum.get(num + 1) - 1);
                    // 后面第二个数出现次数减一
                    countNum.put(num + 2, countNum.get(num + 2) - 1);
                    // 后面第二个数作为结尾的次数加一
                    tail.put(num + 2, tail.getOrDefault(num + 2, 0) + 1);
                } else {
                    // 如果当前数有剩余，但是前一个数既不能作为结尾，又不能从后两个数构造出一个新的序列
                    // 那么这个数就不属于任何一个满足要求的序列之中
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,4,5};
        System.out.println(new Solution().isPossible(nums));
    }
}

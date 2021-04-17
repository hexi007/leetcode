package sort;

import java.util.HashMap;
import java.util.Map;

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

        // size 每个桶大小
        long size;

        /**
         * 判断每个书否在桶内或相邻的桶内元素满足限制条件，否则添加新桶
         * (执行用时：32 ms, 在所有 Java 提交中击败了53.90%的用户)
         * (内存消耗：41.4 MB, 在所有 Java 提交中击败了19.95%的用户)
         *
         * @param nums 整数数组
         * @param k    abs(i - j) <= k
         * @param t    abs(nums[i] - nums[j]) <= t
         * @return     存在则返回 true，不存在返回 false
         */
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            int len = nums.length;
            Map<Long, Long> map = new HashMap<>(16);
            size = t + 1L;

            for (int i = 0; i < len; i++) {
                long num = nums[i];
                long index = getIndex(num);
                // 目标桶已存在（桶不为空），说明前面已有 [num - t, num + t] 范围的数字
                if (map.containsKey(index)) {
                    return true;
                }

                // 检查相邻的桶
                long left = index - 1, right = index + 1;
                if (map.containsKey(left) && num - map.get(left) <= t) {
                    return true;
                }
                if (map.containsKey(right) && map.get(right) - num <= t) {
                    return true;
                }

                // 建立目标桶
                map.put(index, num);
                // 移除下标范围不在 [max(0, i - k), i) 内的桶
                if (i >= k) {
                    map.remove(getIndex(nums[i - k]));
                }
            }
            return false;
        }

        long getIndex(long u) {
            return u >= 0 ? u / size : (u + 1) / size - 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1};
        int k = 1, t = 0;
        System.out.println(new Solution().containsNearbyAlmostDuplicate(nums, k , t));
    }
}

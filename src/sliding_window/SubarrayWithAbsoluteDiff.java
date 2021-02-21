package sliding_window;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * description 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。  如果不存在满足条件的子数组，则返回 0 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/
 * longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-21 09:10
 **/
public class SubarrayWithAbsoluteDiff {

    static class Solution {

        /**
         * 维护两个优先队列分别按最大最小排序
         * (执行用时：249 ms, 在所有 Java 提交中击败了10.22%的用户)
         * (内存消耗：56.9 MB, 在所有 Java 提交中击败了40.42%的用户)
         *
         * @param nums  整数数组
         * @param limit 两个元素之间的绝对差限制
         * @return      最长连续子数组的长度
         */
        public int longestSubarray(int[] nums, int limit) {
            PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.naturalOrder());
            PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
            int left = 0, right = 0, len = nums.length, res = 0;
            while (right < len) {
                max.add(nums[right]);
                min.add(nums[right]);

                if (Math.abs(max.element() - min.element()) <= limit) {
                    res = Math.max(res, right - left + 1);
                } else {
                    max.remove(nums[left]);
                    min.remove(nums[left]);
                    left++;
                }
                right++;
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {10,1,2,4,7,2};
        int limit = 5;
        System.out.println(new Solution().longestSubarray(nums, limit));
    }
}

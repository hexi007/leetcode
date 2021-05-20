package array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * description 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * @author 27771
 * create 2020-10-27 10:48
 **/
public class KthLargestElementInAnArray {
    static class Solution {
        /**
         直接排序后输出
         (执行用时：3 ms, 在所有 Java 提交中击败了64.31%的用户)
         (内存消耗：38.8 MB, 在所有 Java 提交中击败了91.93%的用户)
         * @param nums 数组
         * @param k 第 k 大
         * @return  第 k 大的数
         */
        public int findKthLargest(int[] nums, int k) {
            Arrays.sort(nums);
            return nums[nums.length - k];
        }

        public int findKthLargest1(int[] nums, int k) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int num : nums) {
                if (queue.size() < k) {
                    queue.add(num);
                } else {
                    assert !queue.isEmpty();
                    if (num > queue.peek()) {
                        queue.poll();
                        queue.add(num);
                    }
                }
            }
            assert !queue.isEmpty();
            return queue.peek();
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        System.out.println(new Solution().findKthLargest(nums, 2));
        System.out.println(new Solution().findKthLargest1(nums, 2));
    }
}

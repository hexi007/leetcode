package array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * description 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。  返回滑动窗口中的最大值。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sliding-window-maximum 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-02 15:18
 **/
public class SlidingWindowMaximum {

    static class Solution {

        /**
         * 建堆暴力法
         * (执行用时：82 ms, 在所有 Java 提交中击败了11.24%的用户)
         * (内存消耗：59 MB, 在所有 Java 提交中击败了8.87%的用户)
         *
         * @param nums 整数数组
         * @param k    滑动窗口大小
         * @return     滑动窗口中的最大值
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
                // 大顶堆
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            });

            // 先将 nums 中前 K 个数和其位置进入优先队列
            for (int i = 0; i < k; i++) {
                queue.offer(new int[]{nums[i], i});
            }

            int[] res = new int[nums.length - k + 1];
            // 初始堆顶就是第一滑动窗口最大值
            res[0] = queue.peek()[0];
            // 扫描完接下来的所有滑动窗口
            for (int i = k; i < nums.length; i++) {
                // 都先添加至优先队列
                queue.offer(new int[]{nums[i], i});
                // 如果堆顶元素也就是最大值的位置小于等于 i - k,表示这个最大值不在新的滑动窗口中了
                while (queue.peek()[1] <= i - k) {
                    // 将这个最大值移出优先队列
                    queue.poll();
                }
                // 处理完后堆顶就是当前滑动窗口最大值
                res[i - k + 1] = queue.peek()[0];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(nums, k)));
    }
}

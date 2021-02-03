package sliding_window;

import java.util.*;

/**
 * description  中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；
 * 此时中位数是最中间的两个数的平均数。
 * 例如：  [2,3,4]，中位数是 3 [2,3]，中位数是 (2 + 3) / 2 = 2.5 给你一个数组 nums，
 * 有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 * @author 27771
 * create 2021-02-03 09:26
 **/
public class SlidingWindowMedian {

    static class Solution {

        /**
         * 暴力法超时
         *
         * @param nums 数组
         * @param k    滑动窗口大小
         * @return     滑动窗口中位数组成的数组
         */
        public double[] medianSlidingWindow(int[] nums, int k) {
            int len = nums.length;
            double[] res = new double[len - k + 1];
            int resIndex = 0;

            for (int i = 0; i < len - k + 1; i++) {
                double[] temp = new double[k];
                int index = 0;
                for (int j = i; j < i + k; j++) {
                    temp[index++] = nums[j];
                }
                Arrays.sort(temp);
                if ((k & 1) == 1) {
                    res[resIndex++] = temp[k / 2];
                } else {
                    res[resIndex++] = (temp[k / 2 - 1] + temp[k / 2]) / 2.0;
                }
            }

            return res;
        }

        /**
         * 考虑进出滑动窗口的元素
         * (执行用时：161 ms, 在所有 Java 提交中击败了27.15%的用户)
         * (内存消耗：41 MB, 在所有 Java 提交中击败了13.24%的用户)
         *
         * @param nums 数组
         * @param k    滑动窗口大小
         * @return     滑动窗口中位数组成的数组
         */
        public double[] medianSlidingWindow1(int[] nums, int k) {
            int len = nums.length - k + 1;
            double[] res = new double[len];
            List<Integer> list = new LinkedList<>();

            for (int i = 0; i < k; i++) {
                list.add(nums[i]);
            }

            list.sort(Comparator.comparingInt(o -> o));

            for (int i = 0; i < len; i++) {
                if (i > 0) {
                    // 先移除出滑动窗口的元素
                    list.remove(Integer.valueOf(nums[i - 1]));
                    // 再插入进滑动窗口的元素
                    insert(list, nums[i + k - 1]);
                }

                if ((k & 1) == 1) {
                    res[i] = list.get(k / 2);
                } else {
                    res[i] = ((double)list.get(k / 2 - 1) + (double)list.get(k / 2)) / 2;
                }
            }

            return res;
        }

        /**
         * 二分查找插入新进入滑动窗口的元素
         *
         * @param list 滑动窗口
         * @param num  新加入滑动窗口的数
         */
        private void insert(List<Integer> list, int num) {
            int count = list.size();
            int left = 0, right = count - 1;

            if (count == 0 || num >= list.get(right)) {
                list.add(num);
                return;
            }

            if (num <= list.get(0)) {
                list.add(0, num);
                return;
            }

            // 二分
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid) < num) {
                    left = mid + 1;
                } else if (list.get(mid) > num) {
                    right = mid - 1;
                } else {
                    list.add(mid, num);
                    return;
                }
            }

            list.add(left, num);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(new Solution().medianSlidingWindow(nums, k)));
        System.out.println(Arrays.toString(new Solution().medianSlidingWindow1(nums, k)));
    }
}

package binarysearch;

/**
 * description 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2] 若旋转 4 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
 * 请你找出并返回数组中的 最小元素 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-08 09:16
 **/
public class FindMinimumInRotatedSortedArray {

    static class Solution {

        /**
         * 二分查找
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：37.9 MB, 在所有 Java 提交中击败了47.67%的用户)
         *
         * @param nums 旋转排序数组
         * @return     数组最小值
         */
        public int findMin(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (nums[mid] > nums[right]) {
                    // [3, 4, 5, 1, 2]，mid 以及 mid 的左边一定不是最小数字
                    // 下一轮搜索区间是 [mid + 1, right]
                    left = mid + 1;
                } else if (nums[mid] == nums[right]) {
                    // 只能把 right 排除掉，下一轮搜索区间是 [left, right - 1]
                    right = right - 1;
                } else {
                    // 此时 numbers[mid] < numbers[right]
                    // mid 的右边一定不是最小数字，mid 有可能是，下一轮搜索区间是 [left, mid]
                    right = mid;
                }
            }

            // 最小数字一定在数组中，因此不用后处理
            return nums[left];
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,4,5,1,2};
        System.out.println(new Solution().findMin(nums));
    }
}

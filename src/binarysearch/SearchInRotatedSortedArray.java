package binarysearch;

/**
 * description 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转
 * ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
 * （下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-07 16:48
 **/
public class SearchInRotatedSortedArray {

    static class Solution {

        /**
         * 先找反转头，再二分查找
         * (执行用时：1 ms, 在所有 Java 提交中击败了88.78%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了6.78%的用户)
         *
         * @param nums   元素有序的（升序）整型数组 nums
         * @param target 目标值
         * @return       nums 中是否存在这个目标值 target
         */
        public boolean search(int[] nums, int target) {
            int len = nums.length, head = 0;
            for (int i = 1; i < len; i++) {
                if (nums[i - 1] > nums[i]) {
                    head = i;
                    break;
                }
            }

            if (head == 0) {
                return binarySearch(nums, target, 0, len - 1);
            } else if (nums[head] == target) {
                return true;
            } else if (nums[0] <= target && target <= nums[head - 1]) {
                return binarySearch(nums, target, 0, head - 1);
            } else {
                return binarySearch(nums, target, head, len - 1);
            }
        }

        /**
         * 递归的二分查找
         *
         * @param nums   元素有序的（升序）整型数组 nums
         * @param target 目标值
         * @param left   左边界
         * @param right  右边界
         * @return       nums 中是否存在这个目标值 target
         */
        private boolean binarySearch(int[] nums, int target, int left, int right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            // 没找到
            if (left > right) {
                return false;
            }
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < target) {
                return binarySearch(nums, target, mid + 1, right);
            } else {
                return binarySearch(nums, target, left, mid - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,5,6,0,0,1,2};
        int target = 0;
        System.out.println(new Solution().search(nums, target));
    }
}

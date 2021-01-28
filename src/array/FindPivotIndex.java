package array;

/**
 * description 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-pivot-index 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-28 15:42
 **/
public class FindPivotIndex {

    static class Solution {

        /**
         * (执行用时：2 ms, 在所有 Java 提交中击败了55.66%的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了96.65%的用户)
         *
         * @param nums 整数类型的数组
         * @return     中心索引位置
         */
        public int pivotIndex(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            int left = 0;
            for (int i = 0; i < nums.length; i++) {
                if (sum - nums[i] - left == left) {
                    return i;
                }
                left += nums[i];
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println(new Solution().pivotIndex(nums));
    }
}

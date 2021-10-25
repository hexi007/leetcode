package array;

/**
 * description 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * @author 27771
 * create 2021-10-25 16:54
 **/
public class FirstMissingPositive {

    static class Solution {

        //其实就是原地排序
        public int firstMissingPositive(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0 && nums[i] <= nums.length) {
                    int index= nums[i];
                    nums[i] = -1;
                    while (index > 0 && index <= nums.length && nums[index - 1] != index) {
                        int temp = nums[index - 1];
                        nums[index - 1] = index;
                        index = temp;
                    }
                } else {
                    nums[i] = -1;
                }
            }
            int ans = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == -1) {
                    ans = i + 1;
                    break;
                }
            }
            return ans != -1 ? ans : nums.length + 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1};
        System.out.println(new Solution().firstMissingPositive(nums));
    }
}
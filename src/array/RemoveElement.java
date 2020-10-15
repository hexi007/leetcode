package array;

/**
 * description 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * @author 27771
 * create 2020-10-15 11:17
 **/
public class RemoveElement {
    static class Solution {
        public int removeElement(int[] nums, int val) {
            int indexFirst = 0, indexSecond = 0;
            //for(;indexFirst)
            return val;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        Solution solution = new Solution();
        int ret = solution.removeElement(nums, 3);
        System.out.println(ret);
        for(int i = 0 ;i < ret; i++){
            System.out.println(nums[i]);
        }
    }
}

package array;

/**
 * description 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * @author 27771
 * create 2020-10-15 11:17
 **/
public class RemoveElement {
    static class Solution {
        /**
         * Description 基础的双指针，将不是val的值从头开始复制
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：37 MB, 在所有 Java 提交中击败了97.58% 的用户)
         * @param nums 待移除元素的数组
         * @param val 需要被移除的元素值
         * return  移除后数组的新长度
         */
        public int removeElement(int[] nums, int val) {
            int indexFirst = 0;
            for(int j = 0; j < nums.length; j++){
                if(nums[j] != val){
                    nums[indexFirst] = nums[j];
                    indexFirst++;
                }
            }
            return indexFirst;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        Solution solution = new Solution();
        int ret = solution.removeElement(nums, 2);
        System.out.println(ret);
        for(int i = 0 ;i < ret; i++){
            System.out.println(nums[i]);
        }
    }
}

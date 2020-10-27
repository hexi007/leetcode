package array;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * @author 27771
 * create 2020-10-16 21:04
 **/
public class RemoveDuplicatesFromSortedArrayTwo {
    static class Solution {
        /**
         先记录头两个，再将后面的依次与倒数第2个比较，这样最多有两个重复
         (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         (内存消耗：38.4 MB, 在所有 Java 提交中击败了95.28%的用户)
         * @param nums 数组
         * @return  去重之后数组长度
         */
        public int removeDuplicates(int[] nums) {
            int i = 0;
            for(int n : nums){
                if(i < 2 || n != nums[i -2]){
                    nums[i++] = n;
                }
            }
            return i;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2};
        Solution solution = new Solution();
        int ret = solution.removeDuplicates(nums);
        System.out.println(ret);
    }
}
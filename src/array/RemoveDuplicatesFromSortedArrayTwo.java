package array;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * @author 27771
 * create 2020-10-16 21:04
 **/
public class RemoveDuplicatesFromSortedArrayTwo {
    static class Solution {
        public int removeDuplicates(int[] nums) {
            int minSize = 2;
            if (nums.length <= minSize) {
                return nums.length;
            }
            int indexFirst = 0, numberOfRepetitions = 0;
            for (int temp = 0; temp < nums.length; temp++) {
                if (nums[indexFirst] == nums[temp] && numberOfRepetitions > 1) {
                    indexFirst++;
                    numberOfRepetitions--;
                } else if (nums[indexFirst] == nums[temp] && numberOfRepetitions <= 1) {
                    numberOfRepetitions++;
                } else if(numberOfRepetitions > 0){
                    indexFirst++;
                    temp--;
                    numberOfRepetitions--;
                } else {
                    nums[indexFirst] = nums[temp];
                    indexFirst++;
                    numberOfRepetitions = 1;
                }

            }
            return indexFirst;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        Solution solution = new Solution();
        int ret = solution.removeDuplicates(nums);
        System.out.println(ret);
        for(int i = 0 ;i < ret; i++){
            System.out.println(nums[i]);
        }
    }
}
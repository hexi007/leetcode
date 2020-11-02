package array;

import java.util.Arrays;

/**
 * description 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * create 2020-11-02 19:46
 *
 * @author 27771
 **/
public class TwoSumTwoInputArrayIsSorted {

    static class Solution {

        /**
         *  固定第一个数位置，从右边二分查找符合目标的数的位置
         * (执行用时：4 ms, 在所有 Java 提交中击败了26% 的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了78% 的用户)
         * @param numbers 有序数组
         * @param target  目标数
         * @return        两个数的下标值
         */
        public int[] twoSum(int[] numbers, int target) {
            int[] res = new int[2];
            for(int i = 0; i < numbers.length - 1; i++){
                //从右边开始找
                int left = i + 1, right = numbers.length - 1;
                while (left <= right){
                    int mid = left + (right - left) / 2;
                    if(numbers[i] + numbers[mid] == target){
                        res[0] = i + 1;
                        res[1] = mid + 1;
                        return res;
                    }
                    if(numbers[i] + numbers[mid] < target){
                        left = mid + 1;
                    } else if(numbers[i] + numbers[mid] > target){
                        right = mid - 1;
                    }
                }
            }
            return res;
        }

        /**
         * 使用双指针的实质是缩小查找范围。那么会不会把可能的解过滤掉？答案是不会 。
         * 假设 numbers[i]+numbers[j]=target 是唯一解，其中 0≤i<j≤numbers.length−1 。
         * 初始时两个指针分别指向下标 0 和下标 numbers.length−1，左指针指向的下标小于或等于 i，右指针指向的下标大于或等于 j。
         * 除非初始时左指针和右指针已经位于下标 i 和 j，否则一定是左指针先到达下标 i 的位置或者右指针先到达下标 j 的位置。
         *
         * 如果左指针先到达下标 i 的位置，此时右指针还在下标 j 的右侧，sum > target，因此一定是右指针左移，左指针不可能移到 i 的右侧。
         * 如果右指针先到达下标 j 的位置，此时左指针还在下标 i 的左侧，sum < target，因此一定是左指针右移，右指针不可能移到 j 的左侧。
         *
         * 由此可见，在整个移动过程中，左指针不可能移到 i 的右侧，右指针不可能移到 j 的左侧，因此不会把可能的解过滤掉。
         * 由于题目确保有唯一的答案，因此使用双指针一定可以找到答案。
         *
         * @param numbers 有序数组
         * @param target  目标数
         * @return        两个数的下标值
         */
        public int[] twoSum1(int[] numbers, int target) {
            int i = 0, j = numbers.length - 1;
            while (true) {
                if(numbers[i] + numbers[j] == target){
                    break;
                } else if (numbers[i] + numbers[j] < target){
                    i++;
                } else {
                    j--;
                }
            }
            return new int[]{i + 1, j + 1};
        }
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(new Solution().twoSum1(numbers, target)));
    }
}
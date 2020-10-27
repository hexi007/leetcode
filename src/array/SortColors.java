package array;

import java.util.Arrays;

/**
 * description 给定一个包含红色、白色和蓝色，一共 n 个元素的数组
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * @author 27771
 * create 2020-10-27 09:55
 **/
public class SortColors {

    static class Solution {
        /**
         直观想法，先排序再重写
         (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         (内存消耗：36.9 MB, 在所有 Java 提交中击败了96.69%的用户)
         * @param nums 数组
         */
        public void sortColors(int[] nums) {
            int count0 = 0, count1 = 0, count2 = 0;
            for (int num : nums) {
                switch (num) {
                    case 0:
                        count0++;
                        break;
                    case 1:
                        count1++;
                        break;
                    case 2:
                        count2++;
                        break;
                    default:
                        break;
                }
            }

            for (int i = 0; i < nums.length; i++){
                if (count0-- > 0){
                    nums[i] = 0;
                    continue;
                }
                if (count1-- > 0){
                    nums[i] = 1;
                    continue;
                }
                if (count2-- > 0){
                    nums[i] = 2;
                    continue;
                }
            }
        }

        /**
         设置两个变量 r1, r2 ，含义是 r1,左边(包含r1)的变量值都小于 1， r2 左边(包含 r2 )的变量值都小于 2。
         那么初始时他俩都是 -1 (实际上是左边界 -1 )，代表他俩所包裹的范围是空。
         当前数字比 2 小，就拓展 r2 范围
         拓展之后 r2 所指比 1 小，就拓展 r1 范围
         (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         (内存消耗：36.8 MB, 在所有 Java 提交中击败了97.85%的用户)
         * @param nums 数组
         */
        public void sortColors1(int[] nums) {
            int r1 = -1, r2 = -1;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] < 2){
                    r2++;
                    swap(nums, r2, i);
                    if(nums[r2] < 1){
                        r1++;
                        swap(nums, r1, r2);
                    }
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        new Solution().sortColors1(nums);
        System.out.println(Arrays.toString(nums));
    }
}

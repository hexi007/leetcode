package array;

import java.util.LinkedList;
import java.util.List;

/**
 * description 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，
 * 数组中的元素一些出现了两次，另一些只出现一次。  找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems
 * /find-all-numbers-disappeared-in-an-array 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-13 20:32
 **/
public class FindAllNumbersDisappeared {

    static class Solution {

        /**
         * 将数组每个元素对应位置的数置为负数，在统计正数下标
         * (执行用时：7 ms, 在所有 Java 提交中击败了58.75%的用户)
         * (内存消耗：46.6 MB, 在所有 Java 提交中击败了91.55%的用户)
         *
         * @param nums 整型数组
         * @return     所有在 [1, n] 范围之间没有出现在数组中的数字
         */
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);
            }

            List<Integer> res = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                if (nums[i] > 0) {
                    res.add(i + 1);
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(new Solution().findDisappearedNumbers(nums));
    }
}

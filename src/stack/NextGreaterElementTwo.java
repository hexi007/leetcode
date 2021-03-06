package stack;

import java.util.Arrays;

/**
 * description 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-06 09:27
 **/
public class NextGreaterElementTwo {

    static class Solution {

        /**
         * 单调栈
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：41 MB, 在所有 Java 提交中击败了5.04%的用户)
         *
         * @param nums 循环数组
         * @return     每个元素的下一个更大元素
         */
        public int[] nextGreaterElements(int[] nums) {
            int len = nums.length;
            int[] res = new int[len];

            // 栈内存放的是元素所在地址
            int[] stack = new int[len];
            int stackIndex = 0;
            for (int i = 0; i < len; i++) {
                // 栈空元素地址直接入栈
                if (stackIndex == 0) {
                    stack[stackIndex++] = i;
                    continue;
                }
                // 循环判断当前数是否比栈顶元素所在数组地址的数大
                while (stackIndex != 0 && nums[i] > nums[stack[stackIndex - 1]]) {
                    res[stack[stackIndex - 1]] = nums[i];
                    stackIndex--;
                }
                // 新元素再入栈
                stack[stackIndex++] = i;
            }

            // 因为是循环数组再从开头找起
            for (int i = 0; i < len && stackIndex != 0; i++) {
                // 数组当前数比栈顶元素所在数组地址的数大
                if (nums[i] > nums[stackIndex - 1] && i < stackIndex - 1) {
                    res[stackIndex - 1] = nums[i];
                    stackIndex--;
                    // 当前数位置不变继续和栈顶比较（如果栈不空）
                    i--;
                }
            }

            // 栈中还剩的元素表示找不到比它还大的数
            for (int i = 0; i < stackIndex; i++) {
                res[stack[i]] = -1;
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        System.out.println(Arrays.toString(new Solution().nextGreaterElements(nums)));
    }
}

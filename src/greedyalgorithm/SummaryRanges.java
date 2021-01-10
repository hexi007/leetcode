package greedyalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * description 给定一个无重复元素的有序整数数组 nums 。  返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。
 * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：  "a->b" ，如果 a != b "a" ，如果 a == b
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/summary-ranges 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-10 19:11
 **/
public class SummaryRanges {

    static class Solution {

        /**
         * 从左往右遍历即可
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36 MB, 在所有 Java 提交中击败了99.95%的用户)
         *
         * @param nums 无重复元素的有序整数数组
         * @return     覆盖数组中所有数字的最小有序区间范围列表
         */
        public List<String> summaryRanges(int[] nums) {
            List<String> res = new ArrayList<>();
            int len = nums.length;

            if (len == 0) {
                return res;
            }
            for (int i = 0; i < len; i++) {
                int begin = nums[i], end = nums[i];
                int j = i + 1;
                // 不断往右找不能覆盖的位置
                for (; j < len; j++) {
                    if (nums[j] != end + 1) {
                        break;
                    }
                    end = nums[j];
                }
                StringBuilder builder = new StringBuilder().append(begin);
                if (begin != end) {
                    builder.append("->").append(end);
                }
                res.add(builder.toString());
                i = j - 1;
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,2,3,4,6,8,9};
        System.out.println(new Solution().summaryRanges(nums));
    }
}

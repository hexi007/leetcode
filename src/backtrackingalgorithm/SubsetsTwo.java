package backtrackingalgorithm;

import java.util.*;

/**
 * description 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/subsets-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-31 10:27
 **/
public class SubsetsTwo {

    static class Solution {

        // set 去重
        Set<List<Integer>> set = new HashSet<>();
        // cur 当前子集
        List<Integer> cur = new ArrayList<>();

        /**
         * 用 set 去重，回溯计算结果
         * (执行用时：3 ms, 在所有 Java 提交中击败了24.13%的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了21.63%的用户)
         *
         * @param nums 整数数组
         * @return     数组所有可能的子集（幂集）
         */
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            // 先排序保证单调性
            Arrays.sort(nums);
            back(nums, 0);
            return new ArrayList<>(set);
        }

        /**
         * 回溯
         *
         * @param nums  整数数组
         * @param index 当前选择的在 nums 中的位置
         */
        private void back(int[] nums, int index) {
            if (index == nums.length) {
                // 选择到了最后就将这次结果放入 set 中去重
                set.add(new ArrayList<>(cur));
                return;
            }

            // cur 添加当前位置数
            cur.add(nums[index]);
            back(nums, index + 1);

            // cur 不添加当前位置数
            cur.remove(cur.size() - 1);
            back(nums, index + 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> res = new Solution().subsetsWithDup(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}

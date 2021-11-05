package backtrackingalgorithm;

import java.util.*;

/**
 * description 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，
 * 找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/combination-sum 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-11-05 20:19
 **/
public class CombinationSum {

    static class Solution {
        private final List<List<Integer>> ans = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            for (int i = 0; i < candidates.length && candidates[i] <= target; i++) {
                path.addLast(candidates[i]);
                bt(candidates, target - candidates[i], i);
                path.removeLast();
            }
            return ans;
        }

        private final Deque<Integer> path = new LinkedList<>();

        private void bt(int[] candidates, int target, int index) {
            if (target == 0) {
                ans.add(new ArrayList<>(path));
                return;
            }
            if (target < 0) {
                return;
            }
            for (int i = index; i < candidates.length && candidates[i] <= target; i++) {
                path.addLast(candidates[i]);
                bt(candidates, target - candidates[i], i);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 5};
        int target = 8;
        System.out.println(new Solution().combinationSum(candidates, target));
    }
}

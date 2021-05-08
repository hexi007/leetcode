package dynamicprogramming;

/**
 * description 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。
 * 工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。
 * 请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-08 19:38
 **/
public class FindMinimumTimeToFinishAllJobs {

    static class Solution {

        int[] jobs;
        // sum sum[i] 表示第 i 个员工工作花费时间的总和
        int[] sum;
        int k, res = Integer.MAX_VALUE;

        /**
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.43%的用户)
         * (内存消耗：35.6 MB, 在所有 Java 提交中击败了90.80%的用户)
         *
         * @param jobs 每项工作时间
         * @param k    员工数
         * @return     分配方案中尽可能 最小 的 最大工作时间
         */
        public int minimumTimeRequired(int[] jobs, int k) {
            this.jobs = jobs;
            this.k = k;
            sum = new int[k];

            dfs(0, 0, 0);
            return res;
        }

        /**
         * 剪枝回溯并尽可能的想让没工作的员工工作
         *
         * @param curJob 当前待分配工作
         * @param curK   当前空闲员工
         * @param max    当前的最大工作时间
         */
        private void dfs(int curJob, int curK, int max) {
            // max 已经大于之前计算的最好结果，剪枝
            if (max >= res) {
                return;
            }

            // 当前所有工作分配完了，更新 res
            if (curJob == jobs.length) {
                res = max;
                return;
            }

            // 如果当前空闲员工还有空闲，就让没工作的员工工作
            if (curK < k) {
                sum[curK] = jobs[curJob];
                dfs(curJob + 1, curK + 1, Math.max(max, sum[curK]));
                // 回溯
                sum[curK] = 0;
            }

            // 正常深搜，从当前空闲员工中选一个再分配工作
            for (int i = 0; i < curK; i++) {
                sum[i] += jobs[curJob];
                dfs(curJob + 1, curK, Math.max(max, sum[i]));
                // 回溯
                sum[i] -= jobs[curJob];
            }
        }
    }

    public static void main(String[] args) {
        int[] jobs = {1,2,4,7,8};
        int k = 2;
        System.out.println(new Solution().minimumTimeRequired(jobs, k));
    }
}

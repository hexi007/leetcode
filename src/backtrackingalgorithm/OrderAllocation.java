package backtrackingalgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description 打车派单场景, 假定有N个订单， 待分配给N个司机。每个订单在匹配司机前，会对候选司机进行打分，
 * 打分的结果保存在N*N的矩阵A， 其中Aij 代表订单i司机j匹配的分值。
 * 假定每个订单只能派给一位司机，司机只能分配到一个订单。求最终的派单结果，
 * 使得匹配的订单和司机的分值累加起来最大，并且所有订单得到分配。
 *
 * @author 27771
 * create 2021-01-07 20:17
 **/
public class OrderAllocation {

    static class Solution {

        // mark 评分矩阵
        double[][] mark;
        // n 订单数
        int n;
        // col 哪一列司机已有订单
        private boolean[] col;
        // allocateRecord 最终订单分配方案
        int[] allocateRecord;
        // tempRecord 临时分配方案
        int[] tempRecord;
        // maxGrade 最大订单和司机的分值累加和
        double maxGrade = -Double.MAX_VALUE;

        /**
         * 回溯法暴力求解
         *
         * @param mark 评分矩阵
         */
        public void getMaxGrade(double[][] mark) {
            this.mark = mark;
            this.n = mark.length;
            col = new boolean[n];
            allocateRecord = new int[n];
            tempRecord = new int[n];
            allocate(0);
            System.out.printf("%.2f\n",maxGrade);
            for (int i = 0; i < n - 1; i++) {
                System.out.println((i + 1) + " " + (allocateRecord[i] + 1));
            }
            System.out.println(n + " " + (allocateRecord[n - 1] + 1));
        }

        // grade 分配过程中订单和司机的分值累加和
        double grade;

        /**
         * 回溯法暴力分配司机和订单
         *
         * @param raw 当前行，也就是当前订单行
         */
        private void allocate(int raw) {
            if (raw == n) {
                // 行数为 n 表示订单分配完毕，比较当前得分和最大得分
                if (grade > maxGrade) {
                    maxGrade = grade;
                    // 保存这时的分配方案
                    allocateRecord = Arrays.copyOf(tempRecord, n);
                }
            } else {
                // 当前订单考虑分给每一个司机的情况
                for(int i = 0 ;i < n; i++) {
                    // 只有当前实际没有被分过订单时才进一步考虑
                    if (!col[i]) {
                        // 该司机设置为不可再接受订单
                        col[i] = true;
                        // 分配后修改分数
                        grade += mark[raw][i];
                        // 临时保存分配方案
                        tempRecord[raw] = i;
                        allocate(raw + 1);
                        // 回溯时减去这个分数
                        grade -= mark[raw][i];
                        // 回溯时让该司机可以接受订单
                        col[i] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double[][] mark = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j< n; j++) {
                mark[i][j] = in.nextDouble();
            }
        }
        new Solution().getMaxGrade(mark);
    }
}

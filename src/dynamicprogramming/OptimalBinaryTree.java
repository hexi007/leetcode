package dynamicprogramming;

import java.util.Scanner;

/**
 * description 小团有一个由N个节点组成的二叉树，每个节点有一个权值。
 * 定义二叉树每条边的开销为其两端节点权值的乘积，二叉树的总开销即每条边的开销之和。
 * 小团按照二叉树的中序遍历依次记录下每个节点的权值，即他记录下了N个数，第i个数表示位于中序遍历第i个位置的节点的权值。
 * 之后由于某种原因，小团遗忘了二叉树的具体结构。在所有可能的二叉树中，总开销最小的二叉树被称为最优二叉树。
 * 现在，小团请小美求出最优二叉树的总开销。
 *
 * 5
 * 7 6 5 1 3
 * @author 27771
 * create 2021-03-13 15:49
 **/
public class OptimalBinaryTree {

    static class Solution {
        public int getMinimumCost(int[] trees) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] trees = new int[n];
        for (int i = 0; i < n; i++) {
            trees[i] = input.nextInt();
        }
        System.out.println(new Solution().getMinimumCost(trees));
    }
}
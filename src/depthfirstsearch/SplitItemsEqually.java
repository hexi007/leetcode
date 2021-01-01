package depthfirstsearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description 现在有n个物品，每一个物品都有一个价值，现在想将这些物品分给两个人，
 * 要求这两个人每一个人分到的物品的价值总和相同（个数可以不同，总价值相同即可），剩下的物品就需要扔掉，
 * 现在想知道最少需要扔多少价值的物品才能满足要求分给两个人。
 *
 * @author 27771
 * create 2021-01-01 15:45
 **/
public class SplitItemsEqually {

    static class Solution {

        int sum = 0, min = 0;
        public int minValueAfterSplit(int[] values) {
            sum = Arrays.stream(values).sum();
            min = sum;
            dfs(values, 0, 0, 0);
            return min;
        }

        private void dfs(int[] values, int value1, int value2, int index) {
            if (index >= values.length) {
                if (value1 == value2) {
                    min = Math.min(min, sum - value1 - value2);
                }
                return;
            }
            dfs(values, value1 + values[index], value2, index + 1);
            dfs(values, value1, value2 + values[index], index + 1);
            dfs(values, value1, value2, index + 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Scanner input=new Scanner(System.in);
        int t = input.nextInt();
        while (t-- > 0) {
            int n = input.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = input.nextInt();
            }
            System.out.println(solution.minValueAfterSplit(a));
        }
    }
}
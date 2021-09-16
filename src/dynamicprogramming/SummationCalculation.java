package dynamicprogramming;

import java.util.Scanner;

/**
 * description 多多路上从左到右有N棵树（编号1～N），其中第i个颗树有和谐值Ai。
 * 多多鸡认为，如果一段连续的树，它们的和谐值之和可以被M整除，那么这个区间整体看起来就是和谐的。
 * 现在多多鸡想请你帮忙计算一下，满足和谐条件的区间的数量。
 *
 * @author 27771
 * create 2021-09-16 10:08
 **/
public class SummationCalculation {

    /*
5 2
1 2 3 4 5

6

     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        handle(a, n, m);
    }

    /**
     * 内存超限
     * @param a a
     * @param n b
     * @param m m
     */
    private static void handle(int[] a, int n, int m) {
        int[][] sum = new int[n][n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            sum[i][i] = a[i] % m;
            res += (sum[i][i] == 0 ? 1 : 0);
        }
        for (int k = 1; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                int j = i + k;
                sum[i][j] = (sum[i][j - 1] + a[j]) % m;
                res += (sum[i][j] == 0 ? 1 : 0);
            }
        }
        System.out.println(res);
    }
}

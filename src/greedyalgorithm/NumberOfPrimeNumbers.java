package greedyalgorithm;

import java.util.Scanner;

/**
 * description 牛牛现在有一个包含 n 个正整数的数组 a ，牛牛可以将其中的每个数 a[i] 都拆成若干个和为 a[i] 的正整数，
 * 牛牛想知道拆后（也可以一个数都不拆）这个数组最多能有多少个素数。
 *
 * @author 27771
 * create 2021-01-01 16:23
 **/
public class NumberOfPrimeNumbers {

    static class Solution {

        public Long numberOfPrimeNumbers(int[] nums) {
            long res = 0;
            for (int num : nums) {
                res += (num / 2);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Scanner input=new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        System.out.println(solution.numberOfPrimeNumbers(a));
    }
}
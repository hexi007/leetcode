package array;

import java.util.Scanner;

/**
 * description 小团的蛋糕铺长期霸占着美团APP中“蛋糕奶茶”栏目的首位，因此总会吸引各路食客前来探店。
 * 小团一天最多可以烤n个蛋糕，每个蛋糕有一个正整数的重量。  早上，糕点铺已经做好了m个蛋糕。
 * 现在，有一个顾客要来买两个蛋糕，他希望买这一天糕点铺烤好的最重的和最轻的蛋糕，并且希望这两个蛋糕的重量恰好为a和b。
 * 剩余的n-m个蛋糕可以现烤，请问小团能否满足他的要求？
 *
 * @author 27771
 * create 2021-03-16 20:18
 **/
public class Pastry {

    static class Solution {

        public void cook(int n, int m, int a, int b, int[] done) {
            
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        int a = input.nextInt(), b = input.nextInt();
        int[] done = new int[m];
        for (int i = 0; i < m; i++) {
            done[i] = input.nextInt();
        }
        new Solution().cook(n, m, a, b, done);

    }
}
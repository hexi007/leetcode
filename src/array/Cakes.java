package array;

import java.util.Scanner;

/**
 * description 小团的蛋糕铺长期霸占着美团APP中“蛋糕奶茶”栏目的首位，因此总会吸引各路食客前来探店。
 * 小团一天最多可以烤n个蛋糕，每个蛋糕有一个正整数的重量。  早上，糕点铺已经做好了m个蛋糕。
 * 现在，有一个顾客要来买两个蛋糕，他希望买这一天糕点铺烤好的最重的和最轻的蛋糕，并且希望这两个蛋糕的重量恰好为a和b。
 * 剩余的n-m个蛋糕可以现烤，请问小团能否满足他的要求？
 *
 * @author 27771
 * create 2021-09-17 09:29
 **/
public class Cakes {

    private static int n, m ,a, b;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            n = in.nextInt();
            m = in.nextInt();
            a = in.nextInt();
            b = in.nextInt();
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            int[] cakes = new int[m];
            for (int i = 0; i < m; i++) {
                cakes[i] = in.nextInt();
            }
            handle(cakes);
        }
    }

    private static void handle(int[] cakes) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int cake : cakes) {
            min = Math.min(min, cake);
            max = Math.max(max, cake);
        }
        if (min == a && max == b) {
            System.out.println("YES");
            return;
        }
        if (min < a || max > b) {
            System.out.println("NO");
            return;
        }
        if (n == m) {
            System.out.println("NO");
        } else if (n - 1 == m) {
            if (min == a || max == b) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else {
            System.out.println("YES");
        }
    }
}

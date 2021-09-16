package array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description 多多君最近在研究字符串之间的变换，可以对字符串进行若干次变换操作:
 * 交换任意两个相邻的字符，代价为0。
 * 将任意一个字符a修改成字符b，代价为 |a - b|（绝对值）。
 * 现在有两个长度相同的字符串X和Y，多多君想知道，如果要将X和Y变成两个一样的字符串，需要的最少的代价之和是多少。
 *
 * @author 27771
 * create 2021-09-16 09:57
 **/
public class CharacterTransformation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s1 = in.next();
        String s2 = in.next();
        handle(s1, s2, n);
    }

    private static void handle(String s1, String s2, int n) {
        char[] cs1 = s1.toCharArray();
        Arrays.sort(cs1);
        char[] cs2 = s2.toCharArray();
        Arrays.sort(cs2);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(cs1[i] - cs2[i]);
        }
        System.out.println(res);
    }
}

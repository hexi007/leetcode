package dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description 小美喜欢字母E，讨厌字母F。在小美生日时，小团送了小美一个仅包含字母E和F的字符串，
 * 小美想从中选出一个包含字母E数量与字母F数量之差最大的子串。
 * *子串：从字符串前面连续删去若干个字符，从后面连续删去若干个字符剩下的字符串（也可以一个都不删），
 * 例如abcab是fabcab的子串，而不是abcad的子串。我们将空串看作所有字符串的子串。
 *
 * @author 27771
 * create 2021-09-15 15:59
 **/
public class PreferLetters {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String s = in.next();
        handle(s, len);
    }

    private static void handle(String s, int len) {
        char[] cs = s.toCharArray();
        int[] a = new int[len];
        for (int i = 0; i < len; i++) {
            a[i] = (cs[i] == 'E' ? 1 : -1);
        }
        int res = 0;
        int[] dp = new int[len];
        dp[0] = Math.max(a[0], 0);
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(a[i], dp[i - 1] + a[i]);
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }
}
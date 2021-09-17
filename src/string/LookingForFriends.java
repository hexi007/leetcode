package string;

import java.util.Scanner;

/**
 * description 小美将自己朋友的名字写在了一块，惊讶地发现她写出的那个字符串S有一个惊人的性质：
 * 一个人是小美的朋友当且仅当她/他的名字是那个字符串的子序列。现在小团想根据那个字符串判断一个人是不是小美的朋友。
 * *子序列：一个字符串A是另外一个字符串B的子序列，当且仅当可以通过在B中删除若干个字符（也可能一个都不删），
 * 其他字符保留原来顺序，使得形成的新字符串B’与A串相等。例如，ABC是AABDDC的子序列，而ACB不是AABDDC的子序列。
 *
 *
 * @author 27771
 * create 2021-09-17 16:29
 **/
public class LookingForFriends {

    private static int n, m;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            n = in.nextInt();
            m = in.nextInt();
            String s = in.next(), t = in.next();
            handle(s, t);
        }
    }

    private static void handle(String s, String t) {
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int i = 0, j = 0;
        // 累加时注意数可能溢出
        long count = 0;
        while (i < m && j < n) {
            while (j < n && ct[i] != cs[j]) {
                j++;
            }
            if (j == n) {
                break;
            }
            count += j + 1;
            i++;
            j++;
        }
        if (i == m) {
            System.out.println("Yes");
            System.out.println(count);
        } else {
            System.out.println("No");
        }
    }
}
package array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description 某比赛已经进入了淘汰赛阶段,已知共有n名选手参与了此阶段比赛，他们的得分分别是a_1,a_2….a_n,小美作为比赛的裁判希望设定一个分数线m，使得所有分数大于m的选手晋级，其他人淘汰。  但是为了保护粉丝脆弱的心脏，小美希望晋级和淘汰的人数均在[x,y]之间。  显然这个m有可能是不存在的，也有可能存在多个m，如果不存在，请你输出-1，如果存在多个，请你输出符合条件的最低的分数线。
 *
 * @author 27771
 * create 2021-09-08 09:11
 **/
public class EliminationScore {

    private static int handle(int[] a, int n, int x, int y) {
        Arrays.sort(a);
        int res;
        for (int i = 0; i < n; i++) {
            res = a[i];
            int j = i + 1;
            while (j < n && a[j] == a[i]) {
                j++;
            }
            int m1 = j, m2 = n - j;
            if (m1 >= x && m1 <= y && m2 >= x && m2 <= y ) {
                return res;
            }
        }
        return -1;
    }

    /**
     6 2 3
     1 2 3 4 5 6

     3
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), x = input.nextInt(), y = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        System.out.println(handle(a, n, x, y));
    }
}

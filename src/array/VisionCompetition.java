package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * description 小Q在进行一场竞技游戏,这场游戏的胜负关键就在于能否能争夺一条长度为L的河道,即可以看作是[0,L]的一条数轴。
 * 这款竞技游戏当中有n个可以提供视野的道具−真视守卫,第i个真视守卫能够覆盖区间[xi,yi]。
 * 现在小Q想知道至少用几个真视守卫就可以覆盖整段河道。
 *
 * @author 27771
 * create 2021-09-15 10:30
 **/
public class VisionCompetition {

    /*
4 6
3 6
2 4
0 2
4 7

3
    */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), l = input.nextInt();
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = input.nextInt();
            a[i][1] = input.nextInt();
        }
        handle(a, n, l);
    }

    private static void handle(int[][] a, int n, int l) {
        for (int i = 0; i < n; i++) {
            a[i][0] = Math.min(a[i][0], l);
            a[i][1] = Math.min(a[i][1], l);
        }
        Arrays.sort(a, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o2[0]);
        int start = 0, end = -1, res = 0;
        for (int i = 0; i < n; i++) {
            if (a[i][0] > start) {
                System.out.println(-1);
                return;
            }
            while (i < n && a[i][0] <= start) {
                end = Math.max(end, a[i][1]);
                if (end == l) {
                    res++;
                    System.out.println(res);
                    return;
                }
                i++;
            }
            if (i == n) {
                System.out.println(-1);
                return;
            }
            i--;
            res++;
            start = end;
        }
        System.out.println(res);
    }
}

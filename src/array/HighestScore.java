package array;

import java.util.Scanner;

/**
 * 老师想知道从某某同学当中，分数最高的是多少，现在请你编程模拟老师的询问。当然，老师有时候需要更新某位同学的成绩
 *
 * description 输入包括多组测试数据。
 * 每组输入第一行是两个正整数N和M（0 < N <= 30000,0 < M < 5000）,分别代表学生的数目和操作的数目。
 * 学生ID编号从1编到N。 第二行包含N个整数，代表这N个学生的初始成绩，其中第i个数代表ID为i的学生的成绩
 * 接下来又M行，每一行有一个字符C（只取‘Q’或‘U’），和两个正整数A,B,
 * 当C为'Q'的时候, 表示这是一条询问操作，他询问ID从A到B（包括A,B）的学生当中，成绩最高的是多少
 * 当C为‘U’的时候，表示这是一条更新操作，要求把ID为A的学生的成绩更改为B。
 *
 * @author 27771
 * create 2021-09-13 10:11
 **/
public class HighestScore {

    /*
5 7
1 2 3 4 5
Q 1 5
U 3 6
Q 3 4
Q 4 5
U 4 5
U 2 9
Q 1 5

5
6
5
9
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 多组输入
        while (input.hasNext()) {
            int n = input.nextInt(), m =  input.nextInt();
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = input.nextInt();
            }
            for (int i = 0; i < m; i++) {
                String s = input.next();
                char c = s.charAt(0);
                int x = input.nextInt(), y = input.nextInt();
                // x , y 不保证顺序
                int left = Math.min(x, y), right = Math.max(x, y);
                if (c == 'Q') {
                    int max = a[left];
                    for (int j = left + 1; j <= right; j++) {
                        max = Math.max(max, a[j]);
                    }
                    System.out.println(max);
                } else if (c == 'U') {
                    a[x] = y;
                }
            }
        }
    }
}

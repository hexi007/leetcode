package array;

import java.util.Scanner;

/**
 * description 多多君最近在研究某种数字组合： 定义为：每个数字的十进制表示中(0~9)，
 * 每个数位各不相同且各个数位之和等于N。 满足条件的数字可能很多，找到其中的最小值即可。
 * 多多君还有很多研究课题，于是多多君找到了你--未来的计算机科学家寻求帮助。
 *
 * @author 27771
 * create 2021-09-16 09:50
 **/
public class DigitalCombinations {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), maxN = 45;
        if (n > maxN) {
            System.out.println(-1);
            return;
        }
        int res = 0, i = 9, j = 1;
        while (n > 0) {
            res += Math.min(n, i) * j;
            n -= i;
            i--;
            j *= 10;
        }
        System.out.println(res);
    }
}

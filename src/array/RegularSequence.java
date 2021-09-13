package array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description 我们称一个长度为n的序列为正则序列，当且仅当该序列是一个由1~n组成的排列，
 * 即该序列由n个正整数组成，取值在[1,n]范围，且不存在重复的数，同时正则序列不要求排序
 * 有一天小团得到了一个长度为n的任意序列，他需要在有限次操作内，将这个序列变成一个正则序列，
 * 每次操作他可以任选序列中的一个数字，并将该数字加一或者减一。
 * 请问他最少用多少次操作可以把这个序列变成正则序列？
 *
 * @author 27771
 * create 2021-09-08 09:25
 **/
public class RegularSequence {

    private static int handle(int[] a, int n) {
        Arrays.sort(a);
        int index = 1, res = 0;
        for (int num : a) {
            res += Math.abs(index - num);
            index++;
        }
        return res;
    }

    /*
    5
    -1 2 3 10 100

    103
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        System.out.println(handle(a, n));
    }
}

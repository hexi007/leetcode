package sort;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * description 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，
 * 他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，
 * 不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。
 * 请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据(用于不同的调查)，希望大家能正确处理)。
 * 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。  当没有新的输入时，说明输入结束。
 *
 * @author 27771
 * create 2021-09-10 09:39
 **/
public class MingRandomNumber {

    private final static BufferedWriter WRITE = new BufferedWriter(new OutputStreamWriter(System.out));

    /*
3
2
2
1
11
10
20
40
32
67
40
20
89
300
400
15

1
2
10
15
20
32
40
67
89
300
400
     */

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = input.nextInt();
            }
            handle(a);
        }
        WRITE.flush();
    }

    private static void handle(int[] a) throws IOException {
        System.out.println(Arrays.toString(a));
        List<Integer> list = Arrays.stream(a).boxed()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        for (Integer num : list) {
            WRITE.write(String.valueOf(num));
            WRITE.newLine();
        }
    }
}

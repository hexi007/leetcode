package array;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * description 小M要制作一种黑暗饮料，这种饮料的原料有n种，编号为1-n，
 * 已知小M的容器最多容纳V升材料，黑暗料理的各种原料配比为 a1 : a2 : a3 : ... : an,
 * 每种原料分别有b1，b2，... bn升。  问小M最多可以制作多少升这种饮料。小M使用的各种原料体积和不能超过V。
 *
 * @author 27771
 * create 2021-09-09 09:24
 **/
public class DarkCookingRatio {

    private static final BufferedWriter WRITER = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final DecimalFormat DF = new DecimalFormat("#.0000");

    /*
1 100
1
40

40
     */

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), v = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = input.nextInt();
        }
        handle(n, v, a, b);
        WRITER.flush();
    }

    private static void handle(int n, int v, int[] a, int[] b) throws IOException {
        int sumA = Arrays.stream(a).sum();
        double maxV = (double)b[0] / a[0] * sumA;
        for (int i = 1; i < n; i++) {
            maxV = Math.min(maxV, (double)b[i] / a[i] * sumA);
        }
        maxV = Math.min(maxV, v);
        WRITER.write(DF.format(maxV));
    }
}

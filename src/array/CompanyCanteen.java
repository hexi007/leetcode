package array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * description 小美和小团所在公司的食堂有N张餐桌，从左到右摆成一排，
 * 每张餐桌有2张餐椅供至多2人用餐，公司职员排队进入食堂用餐。小美发现职员用餐的一个规律并告诉小团：
 * 当男职员进入食堂时，他会优先选择已经坐有1人的餐桌用餐，只有当每张餐桌要么空着要么坐满2人时，他才会考虑空着的餐桌；
 * 当女职员进入食堂时，她会优先选择未坐人的餐桌用餐，只有当每张餐桌都坐有至少1人时，她才会考虑已经坐有1人的餐桌；
 * 无论男女，当有多张餐桌供职员选择时，他会选择最靠左的餐桌用餐。
 * 现在食堂内已有若干人在用餐，另外M个人正排队进入食堂，小团会根据小美告诉他的规律预测排队的每个人分别会坐哪张餐桌。
 *
 * @author 27771
 * create 2021-09-08 09:44
 **/
public class CompanyCanteen {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while (t-- > 0) {
            int n = input.nextInt();
            String s = input.next();
            int m = input.nextInt();
            String people = input.next();
            int[] res = handle1(n, s, m, people);
            for (int num : res) {
                System.out.println(num);
            }
        }
    }

    /*
1
5
01102
6
MFMMFF

2
1
1
3
4
4
     */

    /**
     * 暴力模拟，超时
     *
     * @return int[]
     */
    private static int[] handle(int n, String s, int m, String people) {
        char[] table = s.toCharArray(), cP = people.toCharArray();
        int[] res = new int[m];
        for (int i = 0, index = 0; i < m; i++) {
            char c = cP[i];
            boolean choose = false;
            if (c == 'M') {
                for (int j = 0; j < n; j++) {
                    if (table[j] == '1') {
                        table[j] = '2';
                        res[index++] = j + 1;
                        choose = true;
                        break;
                    }
                }
                if (!choose) {
                    for (int j = 0; j < n; j++) {
                        if (table[j] == '0') {
                            table[j] = '1';
                            res[index++] = j + 1;
                            break;
                        }
                    }
                }
            } else {
                for (int j = 0; j < n; j++) {
                    if (table[j] == '0') {
                        table[j] = '1';
                        res[index++] = j + 1;
                        choose = true;
                        break;
                    }
                }
                if (!choose) {
                    for (int j = 0; j < n; j++) {
                        if (table[j] == '1') {
                            table[j] = '2';
                            res[index++] = j + 1;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 使用小根堆，但这不是关键，关键是卡输入输出
     *
     * BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
     * BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
     *
     * for (int r : res) {
     *                 writer.write(Integer.toString(r));
     *                 writer.newLine();
     * }
     *
     * 最后 writer.flush();
     *
     * @return int[]
     */
    private static int[] handle1(int n, String s, int m, String people) {
        char[] table = s.toCharArray(), p = people.toCharArray();
        int[] res = new int[m];
        PriorityQueue<Integer> table0 = new PriorityQueue<>();
        PriorityQueue<Integer> table1 = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (table[i] == '0') {
                table0.add(i);
            }
            if (table[i] == '1') {
                table1.add(i);
            }
        }
        int index = 0;
        for (int i = 0; i < m; i++) {
            char c = p[i];
            if (c == 'M') {
                if (!table1.isEmpty()) {
                    int temp = table1.poll();
                    res[index++] = temp + 1;
                } else {
                    assert !table0.isEmpty();
                    int temp = table0.poll();
                    res[index++] = temp + 1;
                    table1.add(temp);
                }
            } else {
                if (!table0.isEmpty()) {
                    int temp = table0.poll();
                    res[index++] = temp + 1;
                    table1.add(temp);
                } else {
                    assert !table1.isEmpty();
                    int temp = table1.poll();
                    res[index++] = temp + 1;
                }
            }
        }
        return res;
    }
}

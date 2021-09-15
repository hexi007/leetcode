package union_finddisjointsets;

import java.util.Scanner;

/**
 * description 现在有10^7个用户，编号为1- 10^7，现在已知有m对关系，
 * 每一对关系给你两个数x和y，代表编号为x的用户和编号为y的用户是在一个圈子中，
 * 例如：A和B在一个圈子中，B和C在一个圈子中，那么A,B,C就在一个圈子中。现在想知道最多的一个圈子内有多少个用户。
 *
 * @author 27771
 * create 2021-09-14 09:23
 **/
public class WechatMoments {

    private static final int N = 10000002;
    private static final int[] PARENT = new int[N];
    private static final int[] WEIGHT = new int[N];

    /*
2
4
1 2
3 4
5 6
1 6
4
1 2
3 4
5 6
7 8

4
2
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while (t-- > 0) {
            int m = input.nextInt();
            int[][] relations = new int[100001][2];
            for (int i = 0;i < m; i++) {
                relations[i][0] = input.nextInt();
                relations[i][1] = input.nextInt();
            }
            handle(relations, m);
        }
    }

    private static void handle(int[][] relations, int m) {

        for (int i = 0;i < N; i++) {
            PARENT[i] = i;
            WEIGHT[i] = 1;
        }
        for (int i = 0; i < m; i++) {
            int firstElement = relations[i][0], secondElement = relations[i][1];
            if (firstElement == secondElement) {
                continue;
            }
            join(firstElement, secondElement);
        }
        int maxWeight = 1;
        for (int i = 0; i < N; i++) {
            maxWeight = Math.max(maxWeight, WEIGHT[i]);
        }
        System.out.println(maxWeight);
    }

    private static int find(int element) {
        while (PARENT[element] != element) {
            PARENT[element] = PARENT[PARENT[element]];
            element = PARENT[element];
        }
        return element;
    }

    private static void join (int firstElement, int secondElement) {
        int firstParent = find(firstElement), secondParent = find(secondElement);
        if (firstParent == secondParent) {
            return;
        }
        if (WEIGHT[firstParent] >= WEIGHT[secondParent]) {
            PARENT[secondParent] = firstParent;
            WEIGHT[firstParent] += WEIGHT[secondParent];
        } else {
            PARENT[firstParent] = secondParent;
            WEIGHT[secondParent] += WEIGHT[firstParent];
        }
    }
}
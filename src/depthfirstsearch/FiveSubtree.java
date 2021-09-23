package depthfirstsearch;

import java.util.*;

/**
 * description 图中找不重复5节点的子数
 *
 * @author 27771
 * create 2021-09-19 18:52
 **/
public class FiveSubtree {

    private static int n, count;
    private static final int FIVE = 5;
    private static int[] visited;
    private static List<List<Integer>> res;
    private static List<Integer> list;

    /*
6 7
1 2
2 3
3 4
3 6
4 5
5 6
6 1

6
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            n = in.nextInt();
            visited = new int[n + 1];
            int m = in.nextInt();
            int[][] graph = new int[n + 1][n + 1];
            for (int i = 0; i < m; i++) {
                int x = in.nextInt(), y = in.nextInt();
                graph[x][y] = 1;
                graph[y][x] = 1;
            }
            res = new ArrayList<>();
            list = new ArrayList<>();
            count = 0;
            handle(graph);
        }
    }

    private static void handle(int[][] graph) {
        for (int i = 1; i <= n; i++) {
            dfs(graph, i);
        }
        System.out.println(res.size());
    }

    private static void dfs(int[][] graph, int i) {
        if (visited[i] == 1) {
            return;
        }
        visited[i] = 1;
        list.add(i);
        count++;

        if (count == FIVE) {
            List<Integer> temp = new ArrayList<>(list);
            Collections.sort(temp);
            if (!contain(temp)) {
                res.add(temp);
            }
            count--;
            list.remove(list.size() - 1);
            visited[i] = 0;
            return;
        }

        for (int j = 1; j <= n; j++) {
            if (graph[i][j] == 1) {
                dfs(graph, j);
            }
        }

        count--;
        list.remove(list.size() - 1);
        visited[i] = 0;
    }

    private static boolean contain(List<Integer> temp) {
        for (List<Integer> list : res) {
            if (list.equals(temp)) {
                return true;
            }
        }
        return false;
    }
}

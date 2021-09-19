package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * description 小美和小团合作开发了一款新游戏！他们相信这款游戏一定可以大火。
 * 游戏规则是这样的，现在有一个方格地图，你控制一个机器人位于初始位置(x, y)，然后你可以向上下左右的地块移动。
 * 其中一些地块上会有得分点，经过这些点可以获得分数。
 * 当然，路上还会有一些陷阱点，如果想要通过陷阱点，就需要付出一定的分数来清除这个陷阱点。
 * 注意陷阱点付出分数清除后就会变成普通的地块。即反复经过陷阱点只需付出一次代价。同样的，得分点也只能获得一次分数。
 * 小美想到了一个策划案来让这个游戏变得难一些。小美把地图和机器人的初始位置给了小团，并且告诉了小团他操控机器人的行进路线。
 * 小美想试试小团能不能算出来他的最终得分。
 * 小团完美地完成了这个任务。现在，小美和小团想找一些测试人员看看这款游戏的难度如何。
 * 他们找到了你，希望你帮他们测试一下这个游戏。而你能否挑战成功呢？     注意分数允许为负。初始分数为0.
 *
 * 第一行四个整数N，M，P，Q，表示这张地图是N行M列的，得分点的得分是P，陷阱点清除的代价是Q。
 *
 * 接下来N行，每行M个字符，表示这张地图。其中，字符S表示初始机器人位置。
 * 字符#表示墙壁，字符O代表得分点。字符X代表陷阱点。字符+代表普通的地块。
 *
 * 接下来一行一个连续的字符串表示机器人的移动路线，只由大写字母WASD构成，W向上，A向左，S向下，D向右。
 * 机器人可以上下左右移动。不能超出地图边界。也不能走到墙壁之上。
 * 试图走出边界和走到墙壁的行动会停留在原来的位置不动。
 *
 * @author 27771
 * create 2021-09-17 11:11
 **/
public class XiaomeiNewGame {

    private static int n, m, p, q;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            n = in.nextInt();
            m = in.nextInt();
            p = in.nextInt();
            q = in.nextInt();
            char[][] map = new char[n][m];
            int indexI = -1, indexJ = -1;
            for (int i = 0; i < n; i++) {
                String s = in.next();
                for (int j = 0; j < m; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == 'S') {
                        indexI = i;
                        indexJ = j;
                    }
                }
            }
            String command = in.next();
            handle(map, command, indexI, indexJ);
        }
    }

    private static void handle(char[][] map, String command, int i, int j) {
        char[] cs = command.toCharArray();
        long res = 0;
        for (char c : cs) {
            int[] index = run(i, j, c);
            int nextI = index[0];
            int nextJ = index[1];
            if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m) {
                continue;
            }
            if (map[nextI][nextJ] == '#') {
                continue;
            }
            if (map[nextI][nextJ] == 'O') {
                res += p;
                map[nextI][nextJ] = '+';
            } else if (map[nextI][nextJ] == 'X') {
                res -= q;
                map[nextI][nextJ] = '+';
            }
            i = nextI;
            j = nextJ;
        }
        System.out.println(res);
    }

    private static int[] run(int i, int j, char c) {
        Map<Character, int[]> map = new HashMap<>(16);
        map.put('W', new int[]{-1, 0});
        map.put('A', new int[]{0, -1});
        map.put('S', new int[]{1, 0});
        map.put('D', new int[]{0, 1});
        int[] res = new int[2], direction = map.get(c);
        res[0] = i + direction[0];
        res[1] = j + direction[1];
        return res;
    }
}
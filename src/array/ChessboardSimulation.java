package array;

import java.util.*;

/**
 * description 模拟每个棋子的运行
 *
 * @author 27771
 * create 2021-09-23 09:43
 **/
public class ChessboardSimulation {

    private static int xMax, yMax;

    /*
2
3 4 3 3
1 2 3
1 1
1 3
2 2
3 1
0 1 1 1
1 1

2 1
2 2
2 1
3 1
1 1

     */

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt(), m = in.nextInt();
            xMax = in.nextInt();
            yMax = in.nextInt();
            List<Cmd> cmdX = new ArrayList<>(), cmdY = new ArrayList<>();
            Cmd cmd = new Cmd(0, 0);
            for (int i = 0; i < n; i++) {
                int c = in.nextInt();
                if (cmd.direction == 0) {
                    cmd.direction = c;
                    cmd.count = 1;
                    continue;
                }
                if (c != cmd.direction) {
                    if ((cmd.direction & 1) == 0) {
                        cmdX.add(cmd);
                    } else {
                        cmdY.add(cmd);
                    }
                    cmd = new Cmd(c, 1);
                } else {
                    cmd.count = cmd.count + 1;
                }
            }
            if ((cmd.direction & 1) == 0) {
                cmdX.add(cmd);
            } else {
                cmdY.add(cmd);
            }
            int[][] pieces = new int[m][2];
            for (int i = 0; i < m; i++) {
                pieces[i][0] = in.nextInt();
                pieces[i][1] = in.nextInt();
            }
            handle(cmdX, cmdY, pieces);
        }
    }

    private static void handle(List<Cmd> cmdX, List<Cmd> cmdY, int[][] pieces) {
        Map<Integer, Integer> mapX = new HashMap<>(16), mapY = new HashMap<>(16);
        for (int[] piece : pieces) {
            int x = piece[0];
            if (!mapX.containsKey(x)) {
                int xEnd = x;
                for (Cmd cmd : cmdY) {
                    if (cmd.direction == 1) {
                        xEnd = Math.max(1, xEnd - cmd.count);
                    } else if (cmd.direction == 3) {
                        xEnd = Math.min(xMax, xEnd + cmd.count);
                    }
                }
                mapX.put(x, xEnd);
            }
            int y = piece[1];
            if (!mapY.containsKey(y)) {
                int yEnd = y;
                for (Cmd cmd : cmdX) {
                    if (cmd.direction == 2) {
                        yEnd = Math.max(1, yEnd - cmd.count);
                    } else if (cmd.direction == 4) {
                        yEnd = Math.min(yMax, yEnd + cmd.count);
                    }
                }
                mapY.put(y, yEnd);
            }
        }
        for (int[] piece : pieces) {
            System.out.println(mapX.get(piece[0]) + " " + mapY.get(piece[1]));
        }
    }

    private static class Cmd {
        int direction, count;

        public Cmd(int direction, int count) {
            this.direction = direction;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Cmd{" +
                    "direction=" + direction +
                    ", count=" + count +
                    '}';
        }
    }
}
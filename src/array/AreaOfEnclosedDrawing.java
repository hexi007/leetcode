package array;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * description 求抛物线 y^2=2Ax  与直线 y=  Bx+C  所围成的封闭图形面积.若图形不存在,则输出
 *
 * @author 27771
 * create 2021-09-14 18:10
 **/
public class AreaOfEnclosedDrawing {

    private static LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while (t-- > 0) {
            queue.clear();
            int n = input.nextInt();
            while(n-- >= 0) {
                String in = input.nextLine();
                String[] s = in.split(" ");
                String op = s[0];
                if ("PUSH".equals(op)) {
                    Integer value = Integer.valueOf(s[1]);
                    queue.addLast(value);
                } else if ("TOP".equals(op)) {
                    if (queue.size() == 0) {
                        System.out.println(-1);
                    } else {
                        System.out.println(queue.getFirst());
                    }
                } else if ("POP".equals(op)) {
                    if (queue.size() == 0) {
                        System.out.println(-1);
                    } else {
                        queue.removeFirst();
                    }
                } else if ("SIZE".equals(op)) {
                    System.out.println(queue.size());
                } else if ("CLEAR".equals(op)) {
                    queue.clear();
                }
            }
        }
    }
}

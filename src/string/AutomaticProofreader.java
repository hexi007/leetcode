package string;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * description 我叫王大锤，是一家出版社的编辑。
 * 我负责校对投稿来的英文稿件，这份工作非常烦人，因为每天都要去修正无数的拼写错误。
 * 但是，优秀的人总能在平凡的工作中发现真理。我发现一个发现拼写错误的捷径：
 * 1. 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
 * 2. 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
 * 3. 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC
 * 我特喵是个天才！我在蓝翔学过挖掘机和程序设计，按照这个原理写了一个自动校对器，工作效率从此起飞。
 * 用不了多久，我就会出任CEO，当上董事长，迎娶白富美，走上人生巅峰，想想都有点小激动呢！
 * …… 万万没想到，我被开除了，临走时老板对我说：
 * “做人做事要兢兢业业、勤勤恳恳、本本分分，人要是行，干一行行一行。
 * 一行行行行行；要是不行，干一行不行一行，一行不行行行不行。” 我现在整个人红红火火恍恍惚惚的……
 * 请听题：请实现大锤的自动校对程序
 *
 * @author 27771
 * create 2021-09-09 10:21
 **/
public class AutomaticProofreader {

     private final static BufferedWriter WRITE = new BufferedWriter(new OutputStreamWriter(System.out));

     /*
2
helloo
wooooooow

hello
woow
      */

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        while (n-- > 0) {
            String s = input.next();
            handle(s);
        }
        WRITE.flush();
    }

    private static void handle(String str) throws IOException {
        for (int i = 0, two = 2; i < str.length() - two; i++) {
            char num1 = str.charAt(i);
            char num2 = str.charAt(i + 1);
            char num3 = str.charAt(i + 2);
            if (num1 == num2) {
                if (num3 == num2) {
                    str = str.substring(0, i + 1) + str.substring(i + 2);
                    i = i - 1;
                } else if (i + 3 < str.length()) {
                    char num4 = str.charAt(i + 3);
                    if (num3 == num4) {
                        str = str.substring(0, i + 2) + str.substring(i + 3);
                        i = i - 1;
                    }
                }
            }
        }
        WRITE.write(str);
        WRITE.newLine();
    }
}

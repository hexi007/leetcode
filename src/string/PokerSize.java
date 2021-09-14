package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * description 扑克牌游戏大家应该都比较熟悉了，一副牌由54张组成，含3~A，2各4张，小王1张，大王1张。
 * 牌面从小到大用如下字符和字符串表示（其中，小写joker表示小王，大写JOKER表示大王）:)
 * 3 4 5 6 7 8 9 10 J Q K A 2 joker JOKER 输入两手牌，两手牌之间用“-”连接，每手牌的每张牌以空格分隔，“-”两边没有空格，
 * 如：4 4 4 4-joker JOKER 请比较两手牌大小，输出较大的牌，如果不存在比较关系则输出ERROR
 * 基本规则：
 * （1）输入每手牌可能是个子，对子，顺子（连续5张），三个，炸弹（四个）和对王中的一种，不存在其他情况，
 * 由输入保证两手牌都是合法的，顺子已经从小到大排列；
 * （2）除了炸弹和对王可以和所有牌比较之外，其他类型的牌只能跟相同类型的存在比较关系（如，对子跟对子比较，三个跟三个比较）
 * ，不考虑拆牌情况（如：将对子拆分成个子）
 * （3）大小规则跟大家平时了解的常见规则相同，个子，对子，三个比较牌面大小；顺子比较最小牌大小；
 * 炸弹大于前面所有的牌，炸弹之间比较牌面大小；对王是最大的牌；
 * （4）输入的两手牌不会出现相等的情况。
 *
 * @author 27771
 * create 2021-09-13 16:15
 **/
public class PokerSize {

    /*
4 4 4 4-joker JOKER

joker JOKER
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        handle(s);
    }

    static Map<String, Integer> map;
    static {
        map = new HashMap<>();
        int priority = 1;
        for (int i = 3; i <= 10; i++) {
            map.put(String.valueOf(i), priority++);
        }
        map.put("J", priority++);
        map.put("Q", priority++);
        map.put("K", priority++);
        map.put("A", priority++);
        map.put("2", priority++);
        map.put("joker", priority++);
        map.put("JOKER", priority);
    }


    private static void handle(String input) {
        String[] s = input.split("-");
        String s1 = s[0], s2 = s[1];
        String[] player1 = s1.split(" ");
        String[] player2 = s2.split(" ");
        if (player1.length == 2 && ("joker".equals(player1[0]) || "JOKER".equals(player1[0]))) {
            outputResult(player1);
        } else if (player2.length == 2 && ("joker".equals(player2[0]) || "JOKER".equals(player2[0]))) {
            outputResult(player2);
        } else if (player1.length == 4) {
            if (player2.length == 4) {
                comparePriority(player1, player2);
            } else {
                outputResult(player1);
            }
        } else if (player2.length == 4) {
            outputResult(player2);
        } else if (player1.length == 1 || player1.length == 2
                || player1.length == 3 || player1.length == 5) {
            if (player1.length != player2.length) {
                System.out.println("ERROR");
            } else {
                comparePriority(player1, player2);
            }
        }
    }

    private static void comparePriority(String[] player1, String[] player2) {
        if (map.get(player1[0]) > map.get(player2[0])) {
            outputResult(player1);
        } else {
            outputResult(player2);
        }
    }

    private static void outputResult(String[] player) {
        StringBuilder sb = new StringBuilder();
        for (String card : player) {
            sb.append(card).append(" ");
        }
        System.out.println(sb.toString());
    }

}
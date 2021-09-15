package stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * description 小Q想要给他的朋友发送一个神秘字符串，但是他发现字符串的过于长了，
 * 于是小Q发明了一种压缩算法对字符串中重复的部分进行了压缩，
 * 对于字符串中连续的m个相同字符串S将会压缩为[m|S](m为一个整数且1<=m<=100)，
 * 例如字符串ABCABCABC将会被压缩为[3|ABC]，现在小Q的同学收到了小Q发送过来的字符串，
 * 你能帮助他进行解压缩么？
 *
 * @author 27771
 * create 2021-09-14 19:27
 **/
public class CompressionAlgorithm {

    /*
HG[3|B[2|CA]]F

HGBCACABCACABCACAF
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.next();
        handle(s);
    }

    private static void handle(String s) {
        char[] cs = s.toCharArray();
        Stack<String> stringStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0, len = cs.length; i < len; i++) {
            char c = cs[i];
            if (c == '[') {
                stringStack.push(temp.toString());
                temp = new StringBuilder();
                stringStack.push("[");
            } else if (Character.isDigit(c)) {
                int num = c - '0';
                i++;
                while (i < len && Character.isDigit(cs[i])) {
                    num = num * 10 + cs[i] - '0';
                    i++;
                }
                intStack.push(num);
            } else if (c == ']') {
                stringStack.push(temp.toString());
                temp = new StringBuilder();
                StringBuilder unzip = new StringBuilder();
                int count = intStack.pop();
                StringBuilder str = new StringBuilder();
                while (!"[".equals(stringStack.peek())) {
                    str.append(reverse(stringStack.pop()));
                }
                stringStack.pop();
                String s1 = reverse(str.toString());
                while (count-- > 0) {
                   unzip.append(s1);
                }
                stringStack.push(unzip.toString());
            } else {
                temp.append(c);
            }
        }
        stringStack.push(temp.toString());
        StringBuilder tempRes = new StringBuilder();
        while (!stringStack.isEmpty()) {
            tempRes.append(reverse(stringStack.pop()));
        }
        System.out.println(reverse(tempRes.toString()));
    }

    private static String reverse(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0, len = cs.length, half = 2; i < len / half; i++) {
            char tempC = cs[i];
            cs[i] = cs[len - i - 1];
            cs[len - i - 1] = tempC;
        }
        return String.valueOf(cs);
    }
}

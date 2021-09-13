package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * description 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 *
 * 先确定第i个字符（从i到最后完成遍历枚举），然后对i+1~N-1个字符递归使用全排列（缩小范围）。
 *
 * @author 27771
 * create 2021-09-13 09:39
 **/
public class Permutation {

    private static final List<String> RES = new ArrayList<>();

    public static void main(String[] args) {
        String s = "abc";
        char[] cs = s.toCharArray();
        handle(cs, 0);
        System.out.println(RES);
    }

    //对chs[i~length-1]范围内的字符数组完成全排列,并将结果存入res中
    private static void handle(char[] cs, int i) {
        if (i == cs.length) {
            RES.add(new String(cs));
        }
        for (int j = i; j < cs.length; j++) {
            swap(cs, i, j);
            handle(cs, i + 1);
            swap(cs, i, j);
        }
    }

    private static void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
}

package string;

/**
 * description 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
 * 这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内
 *
 * @author 27771
 * create 2020-10-30 19:25
 **/
public class RomanToInteger {
    static class Solution {
        /**
         暴力判断，非常直观，特殊情况两个算一组
         (执行用时：4 ms, 在所有 Java 提交中击败了99.98%的用户)
         (内存消耗：38.4 MB, 在所有 Java 提交中击败了93.56%的用户)
         * @param s 罗马数字
         * @return  罗马数字转成的整数
         */
        public int romanToInt(String s) {
            int res = 0;
            char[] sc = s.toCharArray();
            for(int i = 0; i < sc.length; i++){
                if(sc[i] == 'I'){
                    if(i + 1 < sc.length){
                        if(sc[i + 1] == 'V' ){
                            res += 4;
                            i++;
                        } else if(sc[i + 1] == 'X' ){
                            res += 9;
                            i++;
                        } else {
                            res++;
                        }
                    } else {
                        res++;
                    }
                } else if(sc[i] == 'X'){
                    if(i + 1 < sc.length){
                        if(sc[i + 1] == 'L' ){
                            res += 40;
                            i++;
                        } else if(sc[i + 1] == 'C' ){
                            res += 90;
                            i++;
                        } else {
                            res += 10;
                        }
                    } else {
                        res += 10;
                    }
                } else if(sc[i] == 'C'){
                    if(i + 1 < sc.length){
                        if(sc[i + 1] == 'D' ){
                            res += 400;
                            i++;
                        } else if(sc[i + 1] == 'M' ){
                            res += 900;
                            i++;
                        } else {
                            res += 100;
                        }
                    } else {
                        res += 100;
                    }
                } else if(sc[i] == 'V'){
                    res += 5;
                } else if(sc[i] == 'L'){
                    res += 50;
                } else if(sc[i] == 'D'){
                    res += 500;
                } else if(sc[i] == 'M'){
                    res += 1000;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(new Solution().romanToInt(s));
    }
}

package string;

/**
 * description 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * @author 27771
 * create 2021-10-25 15:55
 **/
public class MultiplyStrings {

    static class Solution {
        public String multiply(String num1, String num2) {
            if ("0".equals(num1) || "0".equals(num2)) {
                return "0";
            }
            int l1 = num1.length(), l2 = num2.length();
            char[] s1 = num1.toCharArray(), s2 = num2.toCharArray();
            int[] res = new int[l1 + l2];
            for (int i = l1 - 1, index = res.length - 1; i >= 0; i--, index--) {
                int n1 = s1[i] - '0', temp = 0, tempIndex= index;
                for (int j = l2 - 1; j >= 0; j--) {
                    int n2 = s2[j] - '0';
                    int result = n1 * n2 + res[tempIndex] + temp;
                    if (result >= 10) {
                        res[tempIndex] = result % 10;
                        temp = result / 10;
                    } else {
                        res[tempIndex] = result;
                        temp = 0;
                    }
                    tempIndex--;
                }
                if (temp > 0) {
                    res[tempIndex] = temp;
                }
            }
            StringBuilder sb = new StringBuilder();
            int start = 0;
            while (res[start] == 0) {
                start++;
            }
            while (start < res.length) {
                sb.append(res[start]);
                start++;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String num1 = "99", num2 = "99";
        System.out.println(new Solution().multiply(num1, num2));
    }
}
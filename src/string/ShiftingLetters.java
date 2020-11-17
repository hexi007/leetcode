package string;

/**
 * description 有一个由小写字母组成的字符串 S，和一个整数数组 shifts。
 * 我们将字母表中的下一个字母称为原字母的 移位（由于字母表是环绕的， 'z' 将会变成 'a'）。
 * 例如·，shift('a') = 'b'， shift('t') = 'u',， 以及 shift('z') = 'a'。
 * 对于每个 shifts[i] = x ， 我们会将 S 中的前 i+1 个字母移位 x 次。
 * 返回将所有这些移位都应用到 S 后最终得到的字符串。
 *
 * @author 27771
 * create 2020-11-16 19:36
 **/
public class ShiftingLetters {

    static class Solution {

        /**
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户)
         * (内存消耗：40.6 MB, 在所有 Java 提交中击败了75.95% 的用户)
         * @param S      字符串
         * @param shifts 移位操作整数数组
         * @return       所有移位操作后的字符串
         */
        public String shiftingLetters(String S, int[] shifts) {
            char[] chars = S.toCharArray();
            int[] myShifts = new int[shifts.length];
            // 处理每个操作防止溢出
            for (int i = 0; i < shifts.length; i++) {
                shifts[i] %= 26;
            }
            // 从右往左计算每个字符移位次数
            char[] charMap={'a','b','c','d','e','f','g','h','i','j','k','l','m',
                    'n','o','p','q','r','s','t','u','v','w','x','y','z'};
            myShifts[shifts.length - 1] = shifts[shifts.length - 1];
            // 每次都计算移位后最终字符
            chars[shifts.length - 1] = charMap[(chars[shifts.length - 1] - 97
                    + myShifts[shifts.length - 1]) % 26];
            for (int i = (shifts.length - 1) - 1; i >= 0 ; i--) {
                myShifts[i] = shifts[i] + myShifts[i + 1];
                chars[i] = charMap[(chars[i] - 97 + myShifts[i]) % 26];
            }
            return new String(chars);
        }
    }

    public static void main(String[] args) {
        String S = "abc";
        int[] shifts = {3,5,9};
        System.out.println(new Solution().shiftingLetters(S, shifts));
    }
}
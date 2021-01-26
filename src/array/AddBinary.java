package array;

/**
 * description 给你两个二进制字符串，返回它们的和（用二进制表示）。  输入为 非空 字符串且只包含数字 1 和 0。
 *
 * @author 27771
 * create 2021-01-26 20:26
 **/
public class AddBinary {

    static class Solution {

        /**
         * (执行用时：2 ms, 在所有 Java 提交中击败了97.99%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了13.56%的用户)
         *
         * @param a 二进制字符串
         * @param b 二进制字符串
         * @return  二进制之和
         */
        public String addBinary(String a, String b) {
            char[] charsA = a.toCharArray(), charsB = b.toCharArray();
            int lenA = a.length(), lenB = b.length();
            int resLength = Math.max(lenA, lenB);
            char[] temp = new char[resLength + 1];
            int carryBit = 0, index = resLength;

            while (lenA > 0 && lenB > 0) {
                int intA = (int)charsA[lenA - 1] - (int)('0');
                int intB = (int)charsB[lenB - 1] - (int)('0');
                if (intA + intB + carryBit == 3) {
                    temp[index] = '1';
                    carryBit = 1;
                } else if (intA + intB + carryBit == 2) {
                    temp[index] = '0';
                    carryBit = 1;
                } else if (intA + intB + carryBit == 1) {
                    temp[index] = '1';
                    carryBit = 0;
                } else {
                    temp[index] = '0';
                    carryBit = 0;
                }
                lenA--;
                lenB--;
                index--;
            }

            while (lenA > 0) {
                int intA = (int)charsA[lenA - 1] - (int)('0');
                if (intA + carryBit == 2) {
                    temp[index] = '0';
                    carryBit = 1;
                } else if (intA + carryBit == 1) {
                    temp[index] = '1';
                    carryBit = 0;
                } else {
                    temp[index] = '0';
                    carryBit = 0;
                }
                lenA--;
                index--;
            }

            while (lenB > 0) {
                int intB = (int)charsB[lenB - 1] - (int)('0');
                if (intB + carryBit == 2) {
                    temp[index] = '0';
                    carryBit = 1;
                } else if (intB + carryBit == 1) {
                    temp[index] = '1';
                    carryBit = 0;
                } else {
                    temp[index] = '0';
                    carryBit = 0;
                }
                lenB--;
                index--;
            }

            if (carryBit == 1) {
                temp[index] = '1';
                return new String(temp);
            } else {
                return new String(temp, 1, resLength);
            }
        }
    }

    public static void main(String[] args) {
        String a = "0011", b = "1";
        System.out.println(new Solution().addBinary(a, b));
    }
}

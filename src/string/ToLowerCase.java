package string;

/**
 * description 实现函数 ToLowerCase()，该函数接收一个字符串参数 str。 <br/>
 * 将该字符串中的大写字母转换成小写字母，之后返回新的字符串。 <br/>
 *
 * @author 27771
 * create 2020-11-04 11:11
 **/
public class ToLowerCase {
    static class Solution {
        /**
         *  依次处理每个字符
         *  str.toLowerCase() 消耗内存最小
         *  (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         *  (内存消耗：36.3 MB, 在所有 Java 提交中击败了90.51%的用户)
         * @param str 字符串
         * @return    大写转小写的字符串
         */
        public String toLowerCase(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            for (char ch : str.toCharArray()) {
                // a-z：97-122  A-Z：65-90  0-9：48-57
                if (ch >= 'A' && ch <= 'Z') {
                    sb.append((char)(ch + 32));
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }

        /**
         * 位运算 <br/>
         * 大写变小写、小写变大写：字符 ^= 32;
         * 大写变小写、小写变小写：字符 |= 32;
         * 大写变大写、小写变大写：字符 &= 33;
         * <br/><br/>
         * ASCII 码表中大写的 A 是 65 ，小写的 a 是 97，它们的差是 32
         * 65 | 32 转为二进制（按 8 位来算）可以得到 0100 0001 | 0010 0000 = 0110 0001 = 97 = a
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.5 MB, 在所有 Java 提交中击败了54.85%的用户)
         *
         * @param str 字符串
         * @return    大写转小写的字符串
         */
        public String toLowerCase1(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }
            char[] ch = str.toCharArray();
            for (int i = 0; i < str.length(); i++) {
                ch[i] |= 32;
            }
            return String.valueOf(ch);
        }
    }

    public static void main(String[] args) {
        String str = "Hello";
        System.out.println(new Solution().toLowerCase(str));
    }
}

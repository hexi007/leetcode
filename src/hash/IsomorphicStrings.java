package hash;

/**
 * description 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。
 * 两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/isomorphic-strings 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2020-12-27 09:33
 **/
public class IsomorphicStrings {

    static class Solution {

        char[] sChars, tChars;
        // isContain 为了确保两个字符不能映射到同一个字符上，用数组保存哪些字符已经被映射过了
        boolean[] isContain;

        /**
         * 存储映射关系再一一比较
         * (执行用时：2 ms, 在所有 Java 提交中击败了 100.00% 的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了 57.27% 的用户)
         *
         * @param s 字符串
         * @param t 字符串
         * @return  两个字符串是否同构
         */
        public boolean isIsomorphic(String s, String t) {
            // match 保存 s 到 t 的映射关系
            char[] match = new char[127];
            isContain = new boolean[127];
            if (s.length() != t.length()) {
                return false;
            }

            sChars = s.toCharArray();
            tChars = t.toCharArray();
            for (int i = 0; i < sChars.length; i++) {
                // match 里没有对应映射则考虑添加映射，有则比较是否满足映射关系
                if (match[sChars[i]] == 0) {
                    // 先判断 tChars[i] 是否已经被映射过
                    if (!isContain[tChars[i]]) {
                        match[sChars[i]] = tChars[i];
                        // 添加 tChars[i] 已经被映射的信息
                        isContain[tChars[i]] = true;
                    } else {
                        return false;
                    }
                } else if (match[sChars[i]] != tChars[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        String s = "12", t = "34";
        System.out.println(new Solution().isIsomorphic(s, t));
    }
}

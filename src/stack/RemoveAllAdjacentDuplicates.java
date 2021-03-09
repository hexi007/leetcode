package stack;

/**
 * description 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。  在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-09 09:24
 **/
public class RemoveAllAdjacentDuplicates {

    static class Solution {

        /**
         * (执行用时：4 ms, 在所有 Java 提交中击败了99.59%的用户)
         * (内存消耗：39.2 MB, 在所有 Java 提交中击败了34.30%的用户)
         *
         * @param s 由小写字母组成的字符串
         * @return  完成所有重复项删除操作后返回最终的字符串
         */
        public String removeDuplicates(String s) {
            int len = s.length();
            if (len <= 1) {
                return s;
            }

            char[] stack = new char[len];
            int stackSize = 0;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (stackSize > 0 && c == stack[stackSize - 1]) {
                    stackSize--;
                } else {
                    stack[stackSize++] = c;
                }
            }

            return new String(stack, 0, stackSize);
        }
    }

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(new Solution().removeDuplicates(s));
    }
}

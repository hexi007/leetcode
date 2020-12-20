package greedyalgorithm;

/**
 * description 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * @author 27771
 * create 2020-12-20 09:34
 **/
public class RemoveDuplicateLetters {

    static class Solution {

        char[] sChars;

        /**
         * 用栈实现，关键是比较栈顶和新进元素大小和栈顶元素在新进元素之后是否还存在
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：37 MB, 在所有 Java 提交中击败了98.28%的用户)
         *
         * @param s 字符串
         * @return  去除重复后字典序最小的字符串
         */
        public String removeDuplicateLetters(String s) {
            sChars = s.toCharArray();
            // lsatIndex 记录 s 中每个字符最后出现位置，用于判断在栈顶时判断后面是否还有重复
            int[] lsatIndex = new int[26];
            for (int i = 0; i < sChars.length; i++) {
                lsatIndex[sChars[i] - 'a'] = i;
            }

            // 字符数组模拟栈
            char[] stack = new char[26];
            int stackIndex = 0;
            // stackContain 记录栈中某字符是否存在
            boolean[] stackContain = new boolean[26];

            for (int i = 0; i < sChars.length; i++) {
                // 如果栈中有 sChars[i] 就跳过
                if (stackContain[sChars[i] - 'a']) {
                    continue;
                }
                // 当栈不空且栈顶元素大于 sChars[i] 且栈顶元素在当前位置 i 之后还出现时出栈顶
                while (stackIndex != 0 && stack[stackIndex - 1] > sChars[i]
                        && lsatIndex[stack[stackIndex - 1] - 'a'] > i) {
                    stackIndex--;
                    // 顺便将 stackContain 中该字符记为 false
                    stackContain[stack[stackIndex] - 'a'] = false;
                }
                // sChars[i] 入栈
                stack[stackIndex++] = sChars[i];
                // 将 stackContain 中该字符记为 true
                stackContain[sChars[i] - 'a'] = true;
            }

            char[] res = new char[stackIndex];
            // 将 stack 拷贝至 res
            if (stackIndex >= 0) {
                System.arraycopy(stack, 0, res, 0, stackIndex);
            }

            return String.valueOf(res);
        }

    }

    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(new Solution().removeDuplicateLetters(s));
    }
}

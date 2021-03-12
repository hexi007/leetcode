package tree;

/**
 * description 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
 * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * 二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如"1,,3" 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-12 20:19
 **/
public class VerifyPreorderSerialization {

    static class Solution {

        String[] pre;

        /**
         * 数字直接入栈，遇 # 只要栈顶是 # 就推两个再入 #， 最终栈内只有一个元素
         * (执行用时：5 ms, 在所有 Java 提交中击败了38.91%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了49.26%的用户)
         *
         * @param preorder 序列
         * @return         序列是否可以重构出树
         */
        public boolean isValidSerialization(String preorder) {
            pre = preorder.split(",");
            int len = pre.length;
            // 序列长度为偶数或者长度只有一个但开头不是 # 就绝不可能序列化成一个二叉树
            boolean illegal = (len & 1) == 0 || (len == 1 && !"#".equals(pre[0]));
            if (illegal) {
                return false;
            }

            char[] stack = new char[pre.length];
            int stackIndex = 0;

            for (String s : pre) {
                char c = s.charAt(0);
                if (!"#".equals(s)) {
                    // 数字直接入栈
                    stack[stackIndex++] = c;
                } else {
                    // 栈元素大于 1 时遇 # 只要栈顶是 # 就推两个再入 # (此时 c 一定是 #)
                    while (stackIndex > 1 && stack[stackIndex - 1] == c) {
                        stackIndex--;
                        // 注意退的第二个栈顶一定不能是 #
                        if ('#' == stack[stackIndex - 1]) {
                            return false;
                        }
                        stackIndex--;
                    }
                    stack[stackIndex++] = '#';
                }
            }

            // 栈内只有一个元素才可序列化
            return stackIndex == 1;
        }
    }

    public static void main(String[] args) {
        String preorder = "#";
        System.out.println(new Solution().isValidSerialization(preorder));
    }
}
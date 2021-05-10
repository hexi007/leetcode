package tree;

/**
 * description 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 * 来源：力扣（LeetCode）链接：https://leetcode-cn.com/problems/leaf-similar-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-10 09:39
 **/
public class LeafSimilarTrees {

    static class Solution {

        int[] leaf1 = new int[201], leaf2 = new int[201];
        int count1 = 0, count2 = 0;

        /**
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.1 MB, 在所有 Java 提交中击败了84.09%的用户)
         *
         * @param root1 根结点 1
         * @param root2 根结点 2
         * @return      两棵二叉树的叶值序列是否相同
         */
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            preorderTraversal(root1, 1);
            preorderTraversal(root2, 2);
            if (count1 != count2) {
                return false;
            }
            for (int i = 0; i < count1; i++) {
                if (leaf1[i] != leaf2[i]) {
                    return false;
                }
            }
            return true;
        }

        private void preorderTraversal(TreeNode node, int flag) {
            if(node.left == null && node.right == null) {
                if (flag == 1) {
                    leaf1[count1++] = node.val;
                } else {
                    leaf2[count2++] = node.val;
                }
                return;
            }
            if(node.left != null) {
                preorderTraversal(node.left,flag);
            }
            if(node.right != null) {
                preorderTraversal(node.right,flag);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.mkTree("[1,3,2]");
        TreeNode root2 = TreeNode.mkTree("[1,2,3]");
        System.out.println(new Solution().leafSimilar(root1, root2));
    }
}

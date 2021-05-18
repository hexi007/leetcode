package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * description 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-18 09:45
 **/
public class CousinsInBinaryTree {

    static class Solution {

        /**
         * (执行用时：1 ms, 在所有 Java 提交中击败了58.57%的用户)
         * (内存消耗：36.3 MB, 在所有 Java 提交中击败了26.92%的用户)
         *
         * @param root 二叉树中根节点
         * @param x    节点 x
         * @param y    节点 y
         * @return     x 和 y 对应的节点是否是堂兄弟节点
         */
        public boolean isCousins(TreeNode root, int x, int y) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            boolean containX = false, containY = false;
            int[] parent = new int[101];
            parent[root.val] = -1;
            while (!queue.isEmpty()) {
                for (int i = 0, levelSize = queue.size(); i < levelSize; i++) {
                    TreeNode temp = queue.poll();
                    assert temp != null;
                    if (temp.val == x) {
                        containX = true;
                    }
                    if (temp.val == y) {
                        containY = true;
                    }
                    if (temp.left != null) {
                        queue.offer(temp.left);
                        parent[temp.left.val] = temp.val;
                    }
                    if (temp.right != null) {
                        queue.offer(temp.right);
                        parent[temp.right.val] = temp.val;
                    }
                }

                if (containX) {
                    return containY && parent[x] != parent[y];
                }
                if (containY) {
                    return false;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.mkTree("[1,2,3,4,null,null,null]");
        System.out.println(new Solution().isCousins(root, 2, 3));
    }
}
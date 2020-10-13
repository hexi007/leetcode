package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 27771
 * @description 二叉树深度
 * @create 2020-10-13 11:20
 **/
public class BinaryTreeDepth {
    static class Solution {
        /**
         * 如果节点为空则深度为0，否则比较左右孩子节点深度，该节点是左右孩子深度再加上1
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.3 MB, 在所有 Java 提交中击败了99.01%的用户)
         * @param root 需要判断深度的节点
         * @return 该节点的深度
         */
        public int maxDepth(TreeNode root) {
            if(root == null){
                return 0;
            }
            return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
        }

        /**
         * 使用层序遍历起始也就是广度优先遍历求最大深度
         * (执行用时：1 ms, 在所有 Java 提交中击败了16.36% 的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了96.30% 的用户)
         * @param root 树根节点
         * @return 树最大深度
         */
        public int maxDepth1(TreeNode root) {
            if(root == null){
                return 0;
            }
            int depth = 0;
            Deque<TreeNode> deque = new LinkedList<>();
            deque.offer(root);
            while(!deque.isEmpty()){
                int currentLevelSize = deque.size();
                //只有遍历完一层，深度才加1
                while(currentLevelSize > 0){
                    root = deque.poll();
                    if(root.left  != null){
                        deque.offer(root.left);
                    }
                    if(root.right != null){
                        deque.offer(root.right);
                    }
                    currentLevelSize--;
                }
                depth++;
            }
            return depth;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(20);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(7);
        root.left = treeNode1;
        root.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;

        Solution s = new Solution();
        int ret = s.maxDepth1(root);
        System.out.println(ret);
    }
}

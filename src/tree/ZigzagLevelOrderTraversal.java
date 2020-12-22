package tree;

import java.util.*;

/**
 * description 给定一个二叉树，返回其节点值的锯齿形层序遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author 27771
 * create 2020-12-22 09:24
 **/
public class ZigzagLevelOrderTraversal {

    static class Solution {

        List<List<Integer>> ret;

        /**
         * 遍历每层时考虑是否逆序保存遍历结果
         * (执行用时：1 ms, 在所有 Java 提交中击败了98.42%的用户)
         * (内存消耗：38.4 MB, 在所有 Java 提交中击败了71.87%的用户)
         *
         * @param root 根节点
         * @return     树节点值的锯齿形层序遍历
         */
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            //使用队列存储当前层所有节点
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // currentLevel 当前层数
            int currentLevel = 1;
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();
                int currentLevelSize = queue.size();
                for (int i = 0; i < currentLevelSize; ++i) {
                    TreeNode node = queue.poll();
                    assert node != null;
                    level.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                // 偶数层逆序 level
                if ((currentLevel & 1) == 0) {
                    Collections.reverse(level);
                }
                ret.add(level);
                currentLevel++;
            }
            return ret;
        }

        /**
         * 不使用 Collections 内存消耗更多了，可能进一步优化是递归吧
         * (执行用时：1 ms, 在所有 Java 提交中击败了98.42%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了58.12%的用户)
         *
         * @param root 根节点
         * @return     树节点值的锯齿形层序遍历
         */
        public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
            ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            //使用队列存储当前层所有节点
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // currentLevel 当前层数
            int currentLevel = 1;
            while (!queue.isEmpty()) {
                int currentLevelSize = queue.size();
                List<Integer> level = new ArrayList<>(currentLevelSize);
                for (int i = 0; i < currentLevelSize; ++i) {
                    TreeNode node = queue.poll();
                    assert node != null;
                    if ((currentLevel & 1) == 0) {
                        level.add(0, node.val);
                    } else {
                        level.add(node.val);
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                ret.add(level);
                currentLevel++;
            }
            return ret;
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

        List<List<Integer>> ret = new Solution().zigzagLevelOrder(root);
        new BinaryTreeLevelOrderTraversal.Solution().printList(ret);

        List<List<Integer>> ret1 = new Solution().zigzagLevelOrder1(root);
        new BinaryTreeLevelOrderTraversal.Solution().printList(ret1);
    }
}

package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * description 给你二叉树的根节点 root 和一个整数 distance 。
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * 返回树中 好叶子节点对的数量 。
 *
 * @author 27771
 * create 2020-11-17 20:11
 **/
public class NumberOfGoodLeafNodesPairs {

    static class Solution {

        /**
         * 朴素的想法，分别计算当前节点的左右子树的叶子深度，寻找符合条件的节点对，递归左右孩子
         * (执行用时：81 ms, 在所有 Java 提交中击败了10.48%的用户)
         * (内存消耗：38.9 MB, 在所有 Java 提交中击败了71.13%的用户)
         * @param root     当前节点
         * @param distance 最短路径长度
         * @return         好叶子节点对的数量
         */
        public int countPairs(TreeNode root, int distance) {
            if (root == null) {
                return 0;
            }

            // 计算当前节点的左右子树的叶子深度
            List<Integer> lefts = new LinkedList<>();
            countDepth(root.left , 0, lefts);
            List<Integer> rights = new LinkedList<>();
            countDepth(root.right , 0, rights);

            // 寻找符合条件的节点对
            int res = 0;
            for (int left : lefts) {
                for (int right : rights) {
                    if (left + right + 2 <= distance) {
                        res++;
                    }
                }
            }

            // 递归左右孩子
            res += countPairs(root.left, distance);
            res += countPairs(root.right, distance);

            return res;
        }

        /**
         *  计算当前节点在子树的深度
         * @param root         当前节点
         * @param currentDepth 当前深度
         * @param list         叶子节点深度列表
         */
        private void countDepth(TreeNode root, int currentDepth, List<Integer> list) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                list.add(currentDepth);
                return;
            }
            countDepth(root.left , currentDepth + 1, list);
            countDepth(root.right , currentDepth + 1, list);
        }
    }

    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,7]";
        TreeNode root = TreeNode.mkTree(str);
        int distance = 3;
        System.out.println(new Solution().countPairs(root, distance));
    }
}
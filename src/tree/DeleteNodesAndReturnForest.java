package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * description 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 *
 * @author 27771
 * create 2020-11-17 18:20
 **/
public class DeleteNodesAndReturnForest {

    static class Solution {

        List<TreeNode> res;
        boolean[] map;

        /**
         * 带父节点的后序遍历
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
         * (内存消耗：39 MB, 在所有 Java 提交中击败了93.28%的用户)
         * @param root      根节点
         * @param to_delete 待删除节点
         * @return          森林中的每棵树
         */
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            res = new ArrayList<>();
            map = new boolean[1001];
            for (int delete : to_delete) {
                map[delete] = true;
            }
            if (!map[root.val]) {
                res.add(root);
            }
            dfs(null, root);
            return res;
        }

        /**
         *  后续遍历
         * @param parent 父节点
         * @param root   当前节点
         */
        private void dfs(TreeNode parent, TreeNode root) {
            if (root.left != null) {
                dfs(root,root.left);
            }
            if (root.right != null) {
                dfs(root,root.right);
            }
            if (map[root.val]) {
                if (root.left != null) {
                    res.add(root.left);
                }
                if (root.right != null) {
                    res.add(root.right);
                }
                // 根节点没法删除
                if (parent != null) {
                    if (parent.left == root) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,7]";
        TreeNode root = TreeNode.mkTree(str);
        int[] to_delete = {1};
        List<TreeNode> res = new Solution().delNodes(root, to_delete);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
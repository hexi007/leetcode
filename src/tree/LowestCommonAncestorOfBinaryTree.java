package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * description 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * @author 27771
 * create 2020-10-14 20:18
 **/
public class LowestCommonAncestorOfBinaryTree {
    static class Solution {
        /**
         * Description  递归解决问题
         * (执行用时：7 ms, 在所有 Java 提交中击败了99.90% 的用户)
         * (内存消耗：40.6 MB, 在所有 Java 提交中击败了94.20% 的用户)
         * @param root 树根节点
         * @param p 指定节点之一
         * @param q 指定节点之一
         * return 最近公共祖先
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(root == null || root == p || root == q){
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p , q);
            TreeNode right = lowestCommonAncestor(root.right, p , q);
            //左右孩子都有祖先时，自己必是最近公共祖先
            if(left != null && right != null){
                return root;
            }
            //只有左孩子有祖先的可能，那么最近公共祖先在左边
            if(left != null){
                return left;
            }
            //或者在右边
            if(right != null){
                return right;
            }
            return null;
        }

        Map<Integer, TreeNode> parent;
        Set<Integer> visited;
        /**
         * Description  先用HashMap存每个节点的父节点，再扫描p,q最先的祖先节点
         * @param root 树根节点
         * @param p 指定节点之一
         * @param q 指定节点之一
         * return  最近祖先节点
         */
        public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q){
            parent = new HashMap<>();
            visited = new HashSet<>();
            dfs(root);
            while (p != null){
                visited.add(p.val);
                p = parent.get(p.val);
            }
            while (q != null){
                if(visited.contains(q.val)){
                    return q;
                }
                q = parent.get(q.val);
            }
            return null;
        }

        /**
         * Description  求所有节点的父节点，注意树根节点没有父节点
         * @param root 当前节点
         * return  空
         */
        private void dfs(TreeNode root) {
            if(root.left != null){
                parent.put(root.left.val, root);
                dfs(root.left);
            }
            if(root.right != null){
                parent.put(root.right.val, root);
                dfs(root.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(3);
        root.left = p;
        root.right = q;
        Solution solution = new Solution();
        TreeNode ret = solution.lowestCommonAncestor(root, p, q);
        System.out.println(ret.val);
    }
}
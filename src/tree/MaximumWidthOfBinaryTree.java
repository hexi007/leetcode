package tree;

import java.util.*;

/**
 * description 给定一个二叉树，编写一个函数来获取这个树的最大宽度。
 * 树的宽度是所有层中的最大宽度。
 * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * @author 27771
 * create 2020-11-24 10:52
 **/
public class MaximumWidthOfBinaryTree {

    static class Solution {
        int max;
        Map<Integer,Integer> leftMostIndex;

        /**
         * 深度遍历求最大宽度
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.84%的用户)
         * (内存消耗：38 MB, 在所有 Java 提交中击败了80.57%的用户)
         *
         * @param root 根节点
         * @return     最大宽度
         */
        public int widthOfBinaryTree(TreeNode root) {
            if(root==null) {
                return 0;
            }
            // 记录每个节点深度
            leftMostIndex = new HashMap<>(100);
            dfs(root,0,1);
            return max;
        }

        /**
         * 深度优先遍历
         * @param root  根节点
         * @param level 层数
         * @param index 位置
         */
        private void dfs(TreeNode root,int level,int index){
            if(root == null) {
                return;
            }
            leftMostIndex.putIfAbsent(level,index);
            int minIndex = leftMostIndex.get(level);
            max = Math.max(max,index-minIndex+1);
            dfs(root.left,level+1,index*2);
            dfs(root.right,level+1,index*2+1);
        }
    }

    public static void main(String[] args) {
        String str = "[1,3,2,5,3,null,9]";
        TreeNode root = TreeNode.mkTree(str);
        System.out.println(new Solution().widthOfBinaryTree(root));
    }
}

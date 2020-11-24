package tree;

/**
 * description 给出一个完全二叉树，求出该树的节点个数。
 * 说明：  完全二叉树的定义如下：
 * 在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1 ~ 2h 个节点。
 *
 * @author 27771
 * create 2020-11-24 09:22
 **/
public class CountCompleteTreeNodes {

    static class Solution {

        int count = 0;
        /**
         * 先序遍历
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：40.6 MB, 在所有 Java 提交中击败了96.71%的用户)
         *
         * @param root 根节点
         * @return     节点数
         */
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            count++;
            countNodes(root.left);
            countNodes(root.right);
            return count;
        }

        /**
         * 它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
         * 比较左右层数
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：40.9 MB, 在所有 Java 提交中击败了85.73%的用户)
         *
         * @param root 根节点
         * @return     节点数
         */
        public int countNodes1(TreeNode root) {
            if(root == null){
                return 0;
            }
            int left = countLevel(root.left);
            int right = countLevel(root.right);
            if(left == right){
                // 层数相同，树节点数等于左节点数（2 ^ left - 1) + 根节点（1） + 右孩子节点数
                return countNodes(root.right) + (1<<left);
            }else{
                // 层数不同，左边一定是满的，倒数第二层已经满了，可以直接得到右子树的节点个数
                return countNodes(root.left) + (1<<right);
            }
        }

        /**
         * 计算树层数
         * @param root 根节点
         * @return     树层数
         */
        private int countLevel(TreeNode root){
            // 初始节点层数为 0
            int level = 0;
            while(root != null){
                level++;
                root = root.left;
            }
            return level;
        }
    }

    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,null]";
        TreeNode root = TreeNode.mkTree(str);
        System.out.println(new Solution().countNodes(root));
        System.out.println(new Solution().countNodes1(root));
    }
}

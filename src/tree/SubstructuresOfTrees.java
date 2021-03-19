package tree;

/**
 * description 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)  B是A的子结构，
 * 即 A中有出现和B相同的结构和节点值。
 *
 * @author 27771
 * create 2021-03-19 19:20
 **/
public class SubstructuresOfTrees {

    static class Solution {

        /**
         * 先找到根节点一样的位置，再判断 b 是否是 a 的子结构
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：40.3 MB, 在所有 Java 提交中击败了41.07%的用户)
         *
         * @param a 树 a 根节点
         * @param b 树 b 根节点
         * @return  B是不是A的子结构
         */
        public boolean isSubStructure(TreeNode a, TreeNode b) {
            if (a == null || b == null) {
                return false;
            }
            if (a.val == b.val) {
                // 只有两个根节点值相等才判断 b 是否是 a 的子结构
                if (dfs(a, b)) {
                    return true;
                }
            }
            // 条件不满足从 a 的两个孩子找
            return isSubStructure(a.left, b) || isSubStructure(a.right, b);
        }

        /**
         * 比较查看 b 是否是 a 的子结构
         *
         * @param a 树 a 当前节点
         * @param b 树 b 当前节点
         * @return  b 是否是 a 的子结构
         */
        private boolean dfs(TreeNode a, TreeNode b) {
            if (b == null) {
                // b 为空表示 b 这个树已经走完了那么之前都是和 a 一样的
                // 就代表 b 是 a 的子结构
                return true;
            }
            if (a == null  || a.val != b.val) {
                // a 走到头了或者 两节点值不同就不存咋子结构关系
                return false;
            }
            // 当前节点值相同还需要左右子树也满足子结构性质
            return dfs(a.left, b.left) && dfs(a.right, b.right);
        }
    }

    public static void main(String[] args) {
        TreeNode a = TreeNode.mkTree("[3,4,5,1,2,null,null]");
        TreeNode b = TreeNode.mkTree("[4,1,null]");
        System.out.println(new Solution().isSubStructure(a, b));
    }
}

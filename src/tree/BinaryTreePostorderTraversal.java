package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 给定一个二叉树，返回它的 后序 遍历。
 * @author: hexi
 * @create: 2020-10-13 09:24
 **/
public class BinaryTreePostorderTraversal {
    static class Solution {

        /**
         * ret 存储遍历结果
         */
        List<Integer> ret;

        /**
         * 递归的后续遍历
         * (执行用时: 0 ms, 在所有 Java 提交中击败了100%的用户)
         * (内存消耗: 37.1 MB, 在所有 Java 提交中击败了49%的用户)
         * @param root 树根节点
         * @return 后序遍历结果
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            ret = new ArrayList<>();
            if(root == null) {
                return ret;
            }
            realPostorderTraversal(ret, root);
            return ret;
        }

        /**
         * 真正的后序递归调用
         * @param ret 遍历保存结果
         * @param root 传入的当前节点
         */
        private void realPostorderTraversal(List<Integer> ret, TreeNode root) {
            if(root.left != null){
                realPostorderTraversal(ret, root.left);
            }
            if(root.right != null){
                realPostorderTraversal(ret, root.right);
            }
            ret.add(root.val);
        }

        /**
         * 后序遍历的非递归实现
         * 对于初始节点先执行入栈操作，之后重复下面循环直到栈空
         *      栈顶节点出栈，并将其值赋给ret第一个位置
         *      栈顶节点左孩子不空，则其左孩子节点入栈
         *      右孩子同理
         * 返回后序遍历结果
         * (执行用时：1 ms, 在所有 Java 提交中击败了51.38%的用户)
         * (内存消耗：36.5 MB, 在所有 Java 提交中击败了99.86%的用户)
         * @param root 树根节点
         * @return 后序遍历结果
         */
        public List<Integer> postorderTraversal1(TreeNode root) {
            ret = new ArrayList<>();
            if(root == null) {
                return ret;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()){
                root = stack.pop();
                ret.add(0, root.val);
                if(root.left != null){
                    stack.push(root.left);
                }
                if(root.right != null){
                    stack.push(root.right);
                }
            }
            return ret;
        }

        /**
         * 打印ret
         * @param ret
         */
        public void printList(List<Integer> ret) {
            for(int i : ret){
                System.out.print(i +  " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        root.right = treeNode2;
        treeNode2.left = treeNode3;

        Solution s = new Solution();
        List<Integer> ret = s.postorderTraversal1(root);
        s.printList(ret);
    }
}

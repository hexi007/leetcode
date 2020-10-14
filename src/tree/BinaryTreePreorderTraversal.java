package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 27771
 */
public class BinaryTreePreorderTraversal {

    static class Solution {

        public void printList(List<Integer> list) {
            for (int i : list) System.out.print(i  + " ");
            System.out.println();
        }

        List<Integer> ret = new ArrayList<>();

        //递归
        public List<Integer> preorderTraversal(TreeNode root) {
            if(root == null)    return ret;
            ret.add(root.val);
            if(root.left != null)   this.preorderTraversal(root.left);
            if(root.right != null)  this.preorderTraversal(root.right);
            return ret;
        }

        //用stack实现非递归
        public List<Integer> preorderTraversal1(TreeNode root){
            if(root == null)    return ret;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()){
                TreeNode temp = stack.pop();
                ret.add(temp.val);
                //注意先进右边
                if(temp.right != null) stack.push(temp.right);
                if(temp.left != null) stack.push(temp.left);
            }
            return ret;
        }

        //下面这种写法Leetcode更喜欢
        List<Integer> res;
        public List<Integer> preorderTraversal3(TreeNode root) {
            res = new ArrayList<>();
            dfs(root);
            return res;
        }

        private void dfs(TreeNode root){
            if(root == null) return;
            res.add(root.val);
            dfs(root.left);
            dfs(root.right);
        }

    }

    public static void main(String[] args) {
        String str = "[1,null,2,null,null,3,null]";
        TreeNode root = TreeNode.mkTree(str);
        Solution s = new Solution();
        List<Integer> ret = s.preorderTraversal3(root);
        s.printList(ret);
    }
}

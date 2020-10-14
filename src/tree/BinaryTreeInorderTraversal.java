package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * @author Hexi
 */
public class BinaryTreeInorderTraversal {


    static class Solution{

        List<Integer> ret;

        //递归的终止条件是当前节点是否为空。首先递归调用遍历左子树，然后访问当前节点，最后递归调用右子树。
        //快消耗内存还少
        public List<Integer> inorderTraversal(TreeNode root) {
            ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            realInorderTraversal(ret,root);
            return ret;
        }

        //List<Integer> 直接传参数内存消耗会小很多
        private void realInorderTraversal(List<Integer> ret, TreeNode root) {
            if(root.left != null) {
                realInorderTraversal(ret, root.left);
            }
            ret.add(root.val);
            if(root.right != null) {
                realInorderTraversal(ret, root.right);
            }
        }

        //在迭代方法中，从根节点开始找二叉树的最左节点，将走过的节点保存在一个栈中
        //找到最左节点后访问，对于每个节点来说，它都是以自己为根的子树的根节点，访问完之后就可以转到右儿子上了。
        //慢且耗内存多
        public List<Integer> inorderTraversal1(TreeNode root) {
            ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            TreeNode temp = root;
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || temp != null){
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
                temp = stack.pop();
                ret.add(temp.val);
                temp = temp.right;
            }
            return ret;
        }

        //Morris法
        //基本思路就是将所有右儿子为NULL的节点的右儿子指向后继节点（对于右儿子不为空的节点，右儿子就是接下来要访问的节点）
        //1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
        //2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
        //      a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
        //      b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
        //   重复以上1、2直到当前节点为空
        public List<Integer> inorderTraversal2(TreeNode root) {
            return ret;
        }

        public void printList(List<Integer> ret) {
            for (int i : ret) {
                System.out.print(i + " ");
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
        List<Integer> ret = s.inorderTraversal1(root);
        s.printList(ret);
    }
}

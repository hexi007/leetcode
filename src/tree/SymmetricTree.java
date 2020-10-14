package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * description 给定一个二叉树，检查它是否是镜像对称的
 *
 * @author 27771
 * create 2020-10-14 10:34
 **/
public class SymmetricTree {
    static class Solution {
        /**
        * 1.怎么判断一棵树是不是对称二叉树？
        *  答案：如果所给根节点，为空，那么是对称。如果不为空的话，当他的左子树与右子树对称时，他对称
        *  2.那么怎么知道左子树与右子树对不对称呢？在这我直接叫为左树和右树
        * 答案：如果左树的左孩子与右树的右孩子对称，左树的右孩子与右树的左孩子对称，那么这个左树和右树就对称。
        * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
        * (内存消耗：36.6 MB, 在所有 Java 提交中击败了99.70%的用户)
        * @param  root 根节点
        * @return 是否对称
        */
        public boolean isSymmetric(TreeNode root) {
            if(root == null){
                return true;
            }
            return childIsSymmetric(root.left, root.right);
        }

        //判断左树和右数是否对称
        public boolean childIsSymmetric(TreeNode left, TreeNode right){
            if(left == null && right == null){
                return true;
            }
            if(left == null || right == null){
                return false;
            }
            return (left.val == right.val) && childIsSymmetric(left.left, right.right) &&
                    childIsSymmetric(left.right , right.left);
        }

        /**
        * @Description: 使用层序遍历，在空位置插入值为Integer.MAX_VALUE的节点，比较每一层是否对称
        * (执行用时：2 ms, 在所有 Java 提交中击败了8.20%的用户)
        * (内存消耗：38.1 MB, 在所有 Java 提交中击败了17.97%的用户)
        * @param root 树根节点
        * @return 是否对称
        */
        public boolean isSymmetric1(TreeNode root) {
            if(root == null) {
                return true;
            }
            //使用队列存储当前层所有节点
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                List<Integer> level = new ArrayList<>();
                int currentLevelSize = queue.size();
                for(int i = 0; i < currentLevelSize; ++i){
                    root = queue.poll();
                    level.add(root.val);
                    //如果节点值为Integer.MAX_VALUE，表示是为了判断是否对称而插入的节点，跳过
                    if(root.val == Integer.MAX_VALUE){
                        continue;
                    }
                    if(root.left != null){
                        queue.offer(root.left);
                    } else {
                        TreeNode temp = new TreeNode(Integer.MAX_VALUE);
                        queue.offer(temp);
                    }
                    if(root.right != null){
                        queue.offer(root.right);
                    } else {
                        TreeNode temp = new TreeNode(Integer.MAX_VALUE);
                        queue.offer(temp);
                    }
                }
                //判断当前层是否为空
                int half = 2;
                for(int i = 0; i < currentLevelSize / half; i++){
                    if(!level.get(i).equals(level.get(currentLevelSize - i - 1))){
                        return false;
                    }
                }
            }
            return true;
        }

    }

    public static void main(String[] args) {
        String str = "[1,2,2,3,4,4,3]";
        TreeNode root = TreeNode.mkTree(str);
        Solution solution = new Solution();
        System.out.println(solution.isSymmetric1(root));
    }
}

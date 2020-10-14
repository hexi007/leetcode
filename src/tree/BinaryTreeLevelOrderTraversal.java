package tree;

import javax.sound.midi.SoundbankResource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description 二叉树的层序遍历
 * @author 27771
 * @create 2020-10-13 10:02
 **/
public class BinaryTreeLevelOrderTraversal {

    static class Solution {
        /**
         * 层序遍历结果
         */
        List<List<Integer>> ret;

        /**
         * 层序遍历
         * (执行用时：1 ms, 在所有 Java 提交中击败了92.97%的用户)
         * (内存消耗：39 MB, 在所有 Java 提交中击败了55.61%的用户)
         * @param root 树根节点
         * @return 层序遍历结果
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            ret = new ArrayList<List<Integer>>();
            if(root == null) {
                return ret;
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
                    if(root.left != null){
                        queue.offer(root.left);
                    }
                    if(root.right != null){
                        queue.offer(root.right);
                    }
                }
                ret.add(level);
            }
            return ret;
        }

        /**
        * 层序遍历的递归形式
        * @Param  root 树根节点
        * @return  层序遍历结果
        */
        public List<List<Integer>> levelOrder1(TreeNode root){
            return levelOrder(root, null, 0);
        }

        /**
         * 处理传入的节点，并递归的调用自己处理该节点的左右孩子
         * (执行用时：1 ms, 在所有 Java 提交中击败了92.97%的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了98.31%的用户)
         * @param root 传入节点
         * @param ret 保存遍历结果的数据结构
         * @param level 当前层级
         * @return 每次操作遍历后更新的结果
         */
        private List<List<Integer>> levelOrder(TreeNode root, List<List<Integer>> ret, int level) {
            //只做一次，初始化最终返回结果ret
            if(ret == null){
                ret = new ArrayList<List<Integer>>();
            }
            //该节点为空则直接返回
            if(root == null){
                return ret;
            }
            //如果ret中还没有当前层List,就在ret中新增储存该层的List，每层只做一次
            if(level == ret.size()){
                ret.add(new ArrayList<>());
            }
            //在对应层加入该节点数据
            ret.get(level).add(root.val);
            //递归的处理该节点左右孩子
            if(root.left != null){
                levelOrder(root.left, ret, level + 1);
            }
            if(root.right != null){
                levelOrder(root.right, ret, level + 1);
            }
            return ret;
        }

        /**
         * 打印遍历结果
         * @param ret 层序遍历结果
         */
        public void printList(List<List<Integer>> ret) {
            System.out.println('[');
            for(List<Integer> lRet : ret){
                System.out.print("\t[ ");
                for(int i : lRet){
                    System.out.print(i + " ");
                }
                System.out.println("]");
            }
            System.out.println(']');
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(20);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(7);
        root.left = treeNode1;
        root.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        
        Solution s = new Solution();
        List<List<Integer>> ret = s.levelOrder1(root);
        s.printList(ret);
    }
}

package tree;

import java.util.*;

/**
 * description 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * create 2020-11-11 20:45
 *
 * @author 27771
 **/
public class SerializeAndDeserializeBinaryTree {
    static class Codec {

        /** 
         * 使用层序遍历序列化数组，“|“  分隔每一层
         * (执行用时：25 ms, 在所有 Java 提交中击败了45.91% 的用户)
         * (内存消耗：41.2 MB, 在所有 Java 提交中击败了37.24% 的用户)
         * @param root 树根节点
         * @return     树序列化后字符串
         */
        public String serialize(TreeNode root) {
            if(root == null) {
                return "";
            }

            List<String> list = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            list.add(String.valueOf(root.val));
            list.add("|");
            // 层序遍历树
            while(!queue.isEmpty()){
                int currentLevelSize = queue.size();
                for(int i = 0; i < currentLevelSize; ++i){
                    root = queue.poll();
                    assert root != null;
                    // 保存遍历结果
                    if(root.left != null){
                        queue.offer(root.left);
                        list.add(String.valueOf(root.left.val));
                    } else {
                        list.add("null");
                    }
                    if(root.right != null){
                        queue.offer(root.right);
                        list.add(String.valueOf(root.right.val));
                    } else {
                        list.add("null");
                    }
                }
                list.add("|");
            }

            return String.join(",",  list.toArray(new String[0]));
        }

        /** 
         * 使用两个队列反序列化字符串
         * @param data 树序列化后字符串
         * @return     树根节点
         */
        public TreeNode deserialize(String data) {
            if ("".equals(data)) {
                return null;
            }

            String[] split = data.split(",");
            TreeNode root = new TreeNode();
            // level 树当前层
            Queue<String> level = new LinkedList<>();
            // buildTree 构造的树的当前层
            Queue<TreeNode> buildTree = new LinkedList<>();
            for (int i = 0; i < split.length; i++) {
                // 构造树根
                if (i == 0) {
                    root.val = Integer.parseInt(split[0]);
                    buildTree.offer(root);
                    i++;
                    continue;
                }
                // 遇到 "|" 才开始构造树的新一层
                if (!"|".equals(split[i])) {
                    level.add(split[i]);
                } else {
                    // 和层序遍历类似
                    int buildTreeSize = buildTree.size();
                    for (int j = 0; j < buildTreeSize; j++) {
                        TreeNode top = buildTree.poll();
                        assert top != null;
                        // 先添加左孩子
                        String left = level.poll();
                        if(!"null".equals(left)) {
                            assert left != null;
                            TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                            top.left = leftNode;
                            // 将孩子添加至下一层
                            buildTree.offer(leftNode);
                        }
                        // 再添加右孩子
                        String right = level.poll();
                        if(!"null".equals(right)) {
                            assert right != null;
                            TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                            top.right = rightNode;
                            // 将孩子添加至下一层
                            buildTree.offer(rightNode);
                        }
                    }
                }
            }
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        Codec codec = new Codec();
        String s = codec.serialize(node1);
        System.out.println("s : " + s);
        TreeNode root = codec.deserialize(s);
        List<Integer> ret = new BinaryTreePreorderTraversal.Solution().preorderTraversal3(root);
        System.out.println(ret.toString());
    }
}
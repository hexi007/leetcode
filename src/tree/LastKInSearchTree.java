package tree;

/**
 * description 在二叉排序数中寻找倒数第k个数
 *
 * @author 27771
 * create 2021-10-17 16:01
 **/
public class LastKInSearchTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);
        k = 4;
        lastK(root);
        System.out.println(res);
    }

    private static int k = -1, res = -1;

    private static void lastK(TreeNode node) {
        if (node == null || k == 0) {
            return;
        }
        lastK(node.right);
        if (k-- == 1) {
            res = node.val;
        }
        lastK(node.left);
    }
}
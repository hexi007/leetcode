package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * description 给一个二叉查找树（Binary Search Tree）的前序遍历结果数组，打印出所有的叶子节点。
 *
 * @author 27771
 * create 2021-09-16 11:10
 **/
public class LeafNodeOfBinarySearchTree {

    private static List<Integer> list;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        list = new ArrayList<>();
        while (in.hasNext()) {
            list.add(in.nextInt());
        }
        handle();
    }

    private static void handle() {
        System.out.println(list.size());
    }
}

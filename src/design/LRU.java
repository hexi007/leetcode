package design;

import java.util.*;

/**
 * description 设计LRU(最近最少使用)缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * 1. set(key, value)：将记录(key, value)插入该结构
 * 2. get(key)：返回key对应的value值
 * 提示:
 * 1.某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的，然后都会刷新缓存。
 * 2.当缓存的大小超过K时，移除最不经常使用的记录。
 * 3.输入一个二维数组与K，二维数组每一维有2个或者3个数字，第1个数字为opt，第2，3个数字为key，value
 *
 * 若opt=1，接下来两个整数key, value，表示set(key, value)
 * 若opt=2，接下来一个整数key，表示get(key)，若key未出现过或已被移除，则返回-1
 *
 * 对于每个opt=2，输出一个答案
 * 4.为了方便区分缓存里key与value，下面说明的缓存里key用""号包裹
 * 进阶:你是否可以在O(1)的时间复杂度完成set和get操作
 *
 * @author 27771
 * create 2021-09-13 14:15
 **/
public class LRU {

    static class Node {
        int key, value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static int[] LRU (int[][] operators, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Node> map = new HashMap<>(k);
        LinkedList<Node> list = new LinkedList<>();
        int count = 0;
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                int key = operator[1], value = operator[2];
                if (map.containsKey(key)) {
                    Node node = map.get(key);
                    node.value = value;
                    list.remove(node);
                    list.addFirst(node);
                } else {
                    if (count == k) {
                        Node temp = list.getLast();
                        map.remove(temp.key);
                        list.removeLast();
                        count--;
                    }
                    Node node = new Node(key, value);
                    list.addFirst(node);
                    map.put(key, node);
                    count++;
                }
            } else if (operator[0] == 2) {
                int key = operator[1];
                if (map.containsKey(key)) {
                    Node node = map.get(key);
                    res.add(node.value);
                    list.remove(node);
                    list.addFirst(node);
                } else {
                    res.add(-1);
                }
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void main(String[] args) {
        int[][] operators = {{1, 1, 1}, {1, 2, 2},{1, 3, 2}, {2, 1, 0}, {1, 4, 4}, {2, 2, 2}};
        int k = 3;
        int[] res = LRU(operators, k);
        System.out.println(Arrays.toString(res));
    }
}
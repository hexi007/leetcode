package design;

/**
 * description 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * 实现 MyHashSet 类：
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/design-hashset 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-13 09:22
 **/
public class DesignHashset {

    /**
     * (执行用时：15 ms, 在所有 Java 提交中击败了99.77%的用户)
     * (内存消耗：46.9 MB, 在所有 Java 提交中击败了19.93%的用户)
     */
    static class MyHashSet {

        boolean[] set;

        /** Initialize your data structure here. */
        public MyHashSet() {
            set = new boolean[100001];
        }

        public void add(int key) {
            set[key] = true;
        }

        public void remove(int key) {
            set[key] = false;
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            return set[key];
        }
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);
        myHashSet.add(2);
        System.out.println(myHashSet.contains(1));
        System.out.println(myHashSet.contains(3));
        myHashSet.add(2);
        System.out.println(myHashSet.contains(2));
        myHashSet.remove(2);
        System.out.println(myHashSet.contains(2));

    }
}
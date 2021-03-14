package design;

/**
 * description 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 * 实现 MyHashMap 类：
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。
 * 如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/design-hashmap 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-14 09:26
 **/
public class DesignHashmap {

    /**
     * (执行用时：28 ms, 在所有 Java 提交中击败了48.51%的用户
     * (内存消耗：46.4 MB, 在所有 Java 提交中击败了6.26%的用户)
     */
    static class MyHashMap {

        int[] map;

        /** Initialize your data structure here. */
        public MyHashMap() {
            map = new int[1000001];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            map[key] = value + 1;
        }

        /** Returns the value to which the specified key is mapped
         * or -1 if this map contains no mapping for the key
         * */
        public int get(int key) {
            return map[key] - 1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            map[key] = 0;
        }
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map.get(1));
        System.out.println(map.get(3));
        map.put(2, 1);
        System.out.println(map.get(2));
        map.remove(2);
        System.out.println(map.get(2));
    }
}

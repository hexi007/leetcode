package design;

import java.util.*;

/**
 * description 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。注意: 允许出现重复元素。
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 *
 * @author 27771
 * create 2020-10-31 09:53
 **/
public class InsertDeleteGetRandomO1DuplicatesAllowed {
    static class RandomizedCollection {
        /**
         核心思想是将要删除的元素和最后一个元素互换，在删除最后一个元素
         (执行用时：17 ms, 在所有 Java 提交中击败了39.21%的用户)
         (内存消耗：45.4 MB , 在所有 Java 提交中击败了51.52%的用户)
         * index HashMap key 是 元素值， value 是元素所在位置集合
         * nums 元素数组
         */
        Map<Integer, Set<Integer>> index;
        List<Integer> nums;
        public RandomizedCollection() {
            index = new HashMap<>();
            nums = new ArrayList<>();
        }

        public boolean insert(int val) {
            nums.add(val);
            Set<Integer> set = index.getOrDefault(val, new HashSet<>());
            //set 中添加这个元素当前位置
            set.add(nums.size() - 1);
            index.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if(!index.containsKey(val)){
                return false;
            }
            // valNeedRemoveIndex 要被删除元素位置
            int valNeedRemoveIndex = index.get(val).iterator().next();
            //最后一个元素位置
            int lastNum = nums.get(nums.size() - 1);
            //让被删除元素位置上放最后一个元素
            nums.set(valNeedRemoveIndex, lastNum);
            //删除需要被删除元素集合中那个位置
            index.get(val).remove(valNeedRemoveIndex);
            //删除最后一个元素在最后的那个位置
            index.get(lastNum).remove(nums.size() - 1);
            //如果被删除元素位置小于元素数组位置，最后一个元素位置集合就添加上这个位置
            if(valNeedRemoveIndex < nums.size() - 1){
                index.get(lastNum).add(valNeedRemoveIndex);
            }
            //如果删除后被删除元素的位置集合为0，就在 map 中删除这个key
            if(index.get(val).size() == 0){
                index.remove(val);
            }
            //最后才删除元素数组最后一个元素
            nums.remove(nums.size() - 1);
            return true;
        }

        /**
         * @return  元素数组中的一个随机坐标下的元素
         */
        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }

    public static void main(String[] args) {
        // 初始化一个空的集合。
        RandomizedCollection collection = new RandomizedCollection();

        // 向集合中插入 1 。返回 true 表示集合不包含 1 。
        collection.insert(1);

        // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
        collection.insert(1);

        // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
        collection.insert(2);

        // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
        System.out.println(collection.getRandom());

        // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
        System.out.println(collection.remove(1));

        // getRandom 应有相同概率返回 1 和 2 。
        System.out.println(collection.getRandom());
    }
}

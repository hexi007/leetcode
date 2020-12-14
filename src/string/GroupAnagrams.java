package string;

import java.util.*;

/**
 * description 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * @author 27771
 * create 2020-12-14 09:51
 **/
public class GroupAnagrams {

    static class Solution {

        List<List<String>> res;
        Set<String> set;

        /**
         * 暴力法非常差
         * (执行用时：95 ms, 在所有 Java 提交中击败了6.52%的用户）
         * (内存消耗：42.1 MB, 在所有 Java 提交中击败了23.52%的用户)
         *
         * @param strs 字符串数组
         * @return     字母异位词组合
         */
        public List<List<String>> groupAnagrams(String[] strs) {
            res = new ArrayList<>();
            set = new HashSet<>();

            for (String str : strs) {
                char[] chars = str.toCharArray();
                // 将每个字符串都排序
                Arrays.sort(chars);
                String charsToString = String.valueOf(chars);
                // set 没有排序后字符串这个字符串不是某个字符串的异位词
                if (!set.contains(charsToString)) {
                    List<String>  temp = new ArrayList<>();
                    // 添加进结果
                    temp.add(str);
                    res.add(temp);
                    // 添加进 set
                    set.add(charsToString);
                } else {
                    // 否则遍历每个list
                    for (List<String> list : res) {
                        // 取每个 list 第一个元素
                        char[] tempChars = list.get(0).toCharArray();
                        // 排序第一个元素
                        Arrays.sort(tempChars);
                        // 比较当前字符串排序后和第一个元素排序后是否相等
                        if (String.valueOf(tempChars).equals(charsToString)) {
                            // 相等则添加进去
                            list.add(str);
                        }
                    }
                }
            }

            return res;
        }

        Map<String, List<String>> map;

        /**
         * 官方暴力法
         * (执行用时：9 ms, 在所有 Java 提交中击败了49.30%的用户)
         * (内存消耗：41.4 MB, 在所有 Java 提交中击败了77.64%的用户)
         *
         * @param strs 字符串数组
         * @return     字母异位词组合
         */
        public List<List<String>> groupAnagrams1(String[] strs){
            map = new HashMap<>(16);
            for (String str : strs) {
                char[] array = str.toCharArray();
                // 同样要排序
                Arrays.sort(array);
                String key = new String(array);
                // 从 map 里找异位词
                List<String> list = map.getOrDefault(key, new ArrayList<>());
                list.add(str);
                // 放回 map
                map.put(key, list);
            }
            return new ArrayList<>(map.values());
        }

        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43,
                47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        Map<Long, List<String>> hashMap;

        /**
         * 使用质数相乘来 hash
         * (执行用时：7 ms, 在所有 Java 提交中击败了95.84%的用户)
         * (内存消耗：41.4 MB, 在所有 Java 提交中击败了77.30%的用户)
         *
         * @param strs 字符串数组
         * @return     字母异位词组合
         */
        public List<List<String>> groupAnagrams2(String[] strs) {
            hashMap = new HashMap<>(16);
            int mod = 1000000007;

            for (String str : strs) {
                char[] chars = str.toCharArray();
                long hash = 1;
                for (char c : chars) {
                    // 对每次质数相乘结果取余
                    hash = (hash * prime[c - 'a']) % mod;
                }

                // 根据 hashMap 中 key 是否有 hash 值决定下一步操作
                if (hashMap.containsKey(hash)) {
                    hashMap.get(hash).add(str);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(str);
                    hashMap.put(hash, list);
                }

            }

            return new ArrayList<>(hashMap.values());
        }
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = new Solution().groupAnagrams(strs);
        for (List<String> list : res) {
            System.out.println(list);
        }
        List<List<String>> res1 = new Solution().groupAnagrams1(strs);
        for (List<String> list : res1) {
            System.out.println(list);
        }
        List<List<String>> res2 = new Solution().groupAnagrams2(strs);
        for (List<String> list : res2) {
            System.out.println(list);
        }
    }
}

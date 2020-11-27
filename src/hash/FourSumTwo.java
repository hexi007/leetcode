package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * description 给定四个包含整数的数组列表A , B , C , D ,计算有多少个元组 (i, j, k, l)
 * 使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2 ^ 28 到 2 ^ 28 - 1 之间，最终结果不会超过 2 ^ 31 - 1 。
 *
 * @author 27771
 * create 2020-11-27 09:40
 **/
public class FourSumTwo {

    static class Solution {
        Map<Integer, Integer> map;

        /**
         * map 存 A B 集合元素和及出现次数，与 C D 集合元素比较
         * 一种速度更快的方法是手动链表实现 map
         * (执行用时：74 ms, 在所有 Java 提交中击败了75.70%的用户)
         * (内存消耗：56.9 MB, 在所有 Java 提交中击败了81.69%的用户)
         *
         * @param A 数组列表
         * @param B 数组列表
         * @param C 数组列表
         * @param D 数组列表
         * @return  满足条件的元组数
         */
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            map = new HashMap<>(16);
            for (int a : A) {
                for (int b : B) {
                    // 记录加和及出现次数
                    map.put(a + b, map.getOrDefault(a + b, 0) + 1);
                }
            }
            int count = 0;
            for (int c : C) {
                for (int d : D) {
                    // 与 -（c + d) 做比较
                    if (map.containsKey(-(c + d))) {
                        count += map.get(-(c + d));
                    }
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[] A = {1,2};
        int[] B = {-2,-1};
        int[] C = {-1,2};
        int[] D = {0,2};
        System.out.println(new Solution().fourSumCount(A, B, C, D));
    }
}

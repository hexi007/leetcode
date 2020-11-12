package array;

import java.util.*;

/**
 * description 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * @author 27771
 * create 2020-11-12 09:30
 **/
public class SortArrayByParityTwo {

    static class Solution {

        /**
         * 暴力法，非常差
         * (执行用时：2208 ms, 在所有 Java 提交中击败了5.49%的用户)
         * (内存消耗：42.3 MB, 在所有 Java 提交中击败了6.77%的用户)
         * @param A 非负整数数组 A
         * @return  奇偶排序后数组
         */
        public int[] sortArrayByParityII(int[] A) {
            int len = A.length;
            Set<Integer> odd = new HashSet<>();
            Set<Integer> even = new HashSet<>();
            for (int i = 0; i < len; i++) {
                // i 是偶数
                if ((i & 1) != 1) {
                    if ((A[i] & 1) == 1) {
                        odd.add(i);
                    }
                } else {
                    if ((A[i] & 1) != 1) {
                        even.add(i);
                    }
                }
            }
            for (Integer oddIndex : odd ) {
                for (Integer evenIndex : even) {
                    int temp = A[oddIndex];
                    A[oddIndex] = A[evenIndex];
                    A[evenIndex] = temp;
                }
            }
            return A;
        }

        /**
         * 双指针，分别从 0，1 位置开始找放错位置的数，交换两者位置
         * (执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39.7 MB, 在所有 Java 提交中击败了89.53%的用户)
         * @param A 非负整数数组 A
         * @return  奇偶排序后数组
         */
        public int[] sortArrayByParityII1(int[] A) {
            // evenIndex 偶数下标
            // oddIndex  奇数下标
            int evenIndex = 0, oddIndex = 1, len = A.length;
            for (;evenIndex < len; evenIndex += 2) {
                if ((A[evenIndex] & 1) == 1) {
                    while ((A[oddIndex] & 1) == 1) {
                        oddIndex += 2;
                    }
                    int temp = A[evenIndex];
                    A[evenIndex] = A[oddIndex];
                    A[oddIndex] = temp;
                }
            }
            return A;
        }

    }

    public static void main(String[] args) {
        int[] A = {4,2,5,7};
        int[] res = new Solution().sortArrayByParityII1(A);
        System.out.println(Arrays.toString(res));
    }
}

package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description 给你两个数组，arr1 和 arr2，
 * arr2 中的元素各不相同 arr2 中的每个元素都出现在 arr1 中 对 arr1 中的元素进行排序
 * 使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 * @author 27771
 * create 2020-11-14 09:27
 **/
public class RelativeSortArray {

    static class Solution {

        /**
         * 统计 arr2 中出现顺序，对 arr1 排序
         * (执行用时：6 ms, 在所有 Java 提交中击败了16.45%的用户)
         * (内存消耗：38.6 MB, 在所有 Java 提交中击败了40.68%的用户)
         * @param arr1 数组 1
         * @param arr2 数组 2
         * @return     给定排序后的数组 1
         */
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            Integer[] list = Arrays.stream(arr1).boxed().toArray(Integer[]::new);
            int[] arr2Exist = new int[1001];
            for (int i = 1; i <= arr2.length; i++) {
                arr2Exist[arr2[i - 1]] = i;
            }
            Arrays.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (arr2Exist[o1] != 0 && arr2Exist[o2] != 0) {
                        return arr2Exist[o1] - arr2Exist[o2];
                    } else if (arr2Exist[o1] == 0 && arr2Exist[o2] != 0) {
                        return 1;
                    } else if (arr2Exist[o1] != 0 && arr2Exist[o2] == 0) {
                        return -1;
                    } else {
                        return o1 - o2;
                    }
                }
            });
            return Arrays.stream(list).mapToInt(Integer::valueOf).toArray();
        }

        /**
         * 统计 arr2 出现的数，再统计 arr1 对应出现频率，根据频率输出并对未出现数升序排序
         * (执行用时：1 ms, 在所有 Java 提交中击败了53.98%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了51.77%的用户)
         * @param arr1 数组 1
         * @param arr2 数组 2
         * @return     给定排序后的数组 1
         */
        public int[] relativeSortArray1(int[] arr1, int[] arr2) {
            // 统计 arr2 出现的数
            int[] arr2Exist = new int[1001];
            for (int a : arr2) {
                arr2Exist[a] = 1;
            }
            //统计 arr1 对应出现频率
            int[] countArr1 = new int[1001];
            int  j = arr1.length - 1;
            for (int i = 0; i < arr1.length; i++) {
                // 没出现的交换到最后
                if (arr2Exist[arr1[i]] != 1) {
                    if (j <= i) {
                        break;
                    }
                    // 之和出现过的交换
                    while (arr2Exist[arr1[j]] == 0 && j > i) {
                        j--;
                    }
                    int temp = arr1[i];
                    arr1[i] = arr1[j];
                    arr1[j] = temp;
                    j--;
                }
                countArr1[arr1[i]]++;
            }
            int i = 0;
            for (int a : arr2) {
                int temp = countArr1[a];
                // 按顺序输出对应个数的数
                while (temp-- > 0) {
                    arr1[i++] = a;
                }
            }
            // 对末尾排序
            Arrays.sort(arr1, i , arr1.length );
            return arr1;
        }

        /**
         * 统计 arr1 个数出现频率，再根据 arr2 输出，再输出剩余的
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了38.44%的用户)
         * @param arr1 数组 1
         * @param arr2 数组 2
         * @return     给定排序后的数组 1
         */
        public int[] relativeSortArray2(int[] arr1, int[] arr2) {
            //统计 arr1 个数出现频率
            int[] countArr1 = new int[1001];
            int[] res = new int[arr1.length];
            for (int a : arr1) {
                countArr1[a]++;
            }

            //根据 arr2 输出
            int i = 0;
            for (int a : arr2) {
                while (countArr1[a]-- > 0) {
                    res[i++] = a;
                }
            }

            // 此时 countArr1 不为 0 的项时剩余元素，且 j 有序
            for (int j = 0; j < countArr1.length; j++) {
                while (countArr1[j]-- > 0) {
                    res[i++] = j;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {28,44,17,22,6,8,6,17};
        int[] arr2 = {22,28,8,6};
        System.out.println(Arrays.toString(new Solution().relativeSortArray2(arr1, arr2)));
    }
}

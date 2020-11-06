package array;

import java.util.*;

/**
 * description 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序. <br/>
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。  请你返回排序后的数组. <br/>
 *
 * @author 27771
 * create 2020-11-06 09:28
 **/
public class SortIntegersByTheNumberOf1Bits {
    static class Solution {
        /**
         * 用 countBit 记录每个数二进制中 1 的数目，对 list 排序载输出
         * (执行用时：10 ms, 在所有 Java 提交中击败了41%的用户)
         * (内存消耗：38.8 MB, 在所有 Java 提交中击败了83%的用户)
         * @param arr 数组
         * @return    按数字二进制中 1 的数目 排序输出的数组
         */
        public int[] sortByBits(int[] arr) {
            int[] countBit = new int[10001];
            List<Integer> list = new ArrayList<>();
            for(int a : arr){
                list.add(a);
                countBit[a] = Integer.bitCount(a);
            }
            //如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
            list.sort((o1, o2) -> countBit[o1] != countBit[o2] ?
                    countBit[o1] - countBit[o2] : o1 - o2);
            for(int i = 0; i < list.size(); i++){
                arr[i] = list.get(i);
            }
            return arr;
        }

        /**
         * 核心思想扩大数中二进制数 1 个数的权重
         * (执行用时：3 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.8 MB, 在所有 Java 提交中击败了87.61%的用户)
         * @param arr 数组
         * @return    按数字二进制中 1 的数目 排序输出的数组
         */
        public int[] sortByBits1(int[] arr){
            int len = arr.length;
            for(int i = 0; i < len; i++){
                //扩大数中二进制数 1 个数的权重
                //保留 arr[i] 使得数字二进制中 1 的数目相同时，将它们按照数值大小升序排列
                arr[i] = Integer.bitCount(arr[i]) * 10_0000 + arr[i];
            }
            Arrays.sort(arr);
            //还原数组
            for(int i = 0; i < len; i++){
                arr[i] %= 10_0000;
            }
            return arr;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1024,512,256,128,64,32,16,8,4,2,1};
        int[] res = new Solution().sortByBits(arr);
        int[] res1 = new Solution().sortByBits1(arr);
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(res1));
    }
}

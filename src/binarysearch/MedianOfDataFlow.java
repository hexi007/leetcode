package binarysearch;

import java.util.*;

/**
 * description 有一个源源不断的吐出整数的数据流，假设你有足够的空间来保存吐出的数。
 * 请设计一个名叫MedianHolder的结构，MedianHolder可以随时取得之前吐出所有数的中位数。
 * [要求] 1. 如果MedianHolder已经保存了吐出的N个数，那么将一个新数加入到MedianHolder的过程，其时间复杂度是O(logN)。
 * 2. 取得已经吐出的N个数整体的中位数的过程，时间复杂度为O(1)
 * 每行有一个整数opt表示操作类型
 * 若opt=1，则接下来有一个整数N表示将N加入到结构中。
 * 若opt=2，则表示询问当前所有数的中位数
 *
 * @author 27771
 * create 2021-09-16 18:07
 **/
public class MedianOfDataFlow {

    static class Solution {

        public double[] flowMedian (int[][] operations) {
            List<Double> list = new ArrayList<>();
            PriorityQueue<Integer> big = new PriorityQueue<>((o1, o2) -> o2 - o1);
            PriorityQueue<Integer> small = new PriorityQueue<>();
            for (int i = 0, count = 0; i < operations.length; i++) {
                if (operations[i][0] == 1) {
                    int num = operations[i][1];
                    if (big.isEmpty() || num < big.peek()) {
                        big.add(num);
                    } else {
                        small.add(num);
                    }
                    if (big.size() > small.size() + 1) {
                        small.add(big.poll());
                    } else if (small.size() > big.size() + 1) {
                        big.add(small.poll());
                    }
                    count++;
                } else {
                    if (count == 0) {
                        list.add((double) -1);
                        continue;
                    }
                    assert !small.isEmpty() && !big.isEmpty();
                    if ((count & 1) == 0) {
                        list.add(((double) small.peek() + big.peek()) / 2);
                    } else {
                        list.add(big.size() > small.size() ? (double)big.peek() : (double)small.peek());
                    }
                }
            }
            double[] res = new double[list.size()];
            for (int i = 0;i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
        }

    }

    public static void main(String[] args) {
        int[][] operations = {{1, 5}, {2,- 1}, {1, 3}, {2, -1}, {1, 6}, {2, -1}, {1, 7}, {2, -1}};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.flowMedian(operations)));
    }
}
package greedyalgorithm;

import java.util.ArrayList;

/**
 * description 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * @author 27771
 * create 2020-11-18 09:14
 **/
public class GasStation {

    static class Solution {

        /**
         * 模拟执行
         * (执行用时：36 ms, 在所有 Java 提交中击败了27.18%的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了83.20%的用户)
         * @param gas  加油站汽油量
         * @param cost 每站消耗
         * @return     是否右环绕一周起点
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            ArrayList<Integer> begins = new ArrayList<>();
            for (int i = 0; i < gas.length; i++) {
                if (gas[i] >= cost[i]) {
                    begins.add(i);
                }
            }

            // begins.sort((o1, o2) -> o2 - o1) 反而更慢了
            for (int begin : begins) {
                // g 当前油量， i 当前位置， temp 临时保存起始位置加油站油量
                int g = gas[begin], i = begin, temp = gas[begin];
                // 将初始位置油量置为 0
                gas[begin] = 0;
                do {
                    // 当前位置油量不支持开往下一个加油站
                    if (g < cost[i]) {
                        break;
                    }
                    // 是否到达最后一个加油站
                    if (i + 1 == gas.length) {
                        g += (gas[0] - cost[i]);
                        i = -1;
                    } else {
                        g += (gas[i + 1] - cost[i]);
                    }
                    // 当前油量 为负退出循环
                    if (g < 0) {
                        break;
                    }
                    i++;
                } while (i != begin);
                // 达到终点且油量大于等于 0
                if (i == begin && g >= 0) {
                    gas[begin] = temp;
                    return begin;
                }
                // 当前位置不是最终答案，回复当前位置加油站油量
                gas[begin] = temp;
            }

            return -1;
        }

        /**
         * 计算总剩余汽油量，记录油量最低的位置
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.7 MB, 在所有 Java 提交中击败了81.14%的用户)
         * @param gas  加油站汽油量
         * @param cost 每站消耗
         * @return     是否右环绕一周起点
         */
        public int canCompleteCircuit1(int[] gas, int[] cost) {
            int g = 0, minG = Integer.MAX_VALUE, minIndex = 0;
            for (int i = 0; i < gas.length; i++) {
                g += (gas[i] - cost[i]);
                if (g < minG) {
                    minG = g;
                    minIndex = i;
                }
            }
            return g >=0 ? (minIndex + 1) % gas.length : -1;
        }
    }

    public static void main(String[] args) {
        int[] gas = {5,1,2,3,4};
        int[] cost = {4,4,1,5,1};
        System.out.println(new Solution().canCompleteCircuit(gas, cost));
        System.out.println(new Solution().canCompleteCircuit1(gas, cost));
    }
}

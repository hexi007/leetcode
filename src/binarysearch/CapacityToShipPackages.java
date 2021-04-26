package binarysearch;

import java.util.PriorityQueue;

/**
 * description 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。
 * 我们装载的重量不会超过船的最大运载重量。  返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-14 20:58
 **/
public class CapacityToShipPackages {

    static class Solution {

        /**
         * 确定船最低和最高运载能力，再二分查找
         * (执行用时：8 ms, 在所有 Java 提交中击败了98%的用户)
         * (内存消耗：41.5 MB, 在所有 Java 提交中击败了93.58%的用户)
         *
         * @param weights 包裹的重量数组
         * @param d       目标天数
         * @return        在 D 天内将传送带上的所有包裹送达的船的最低运载能力
         */
        public int shipWithinDays(int[] weights, int d) {
            int len = weights.length;

            // 船运载能力至少要比最重的货物还要大
            int left = 0;
            for (int weight : weights) {
                left = Math.max(left, weight);
            }

            // len 个货物, d 天送走, 那么至少一次要送 len / d（向上取整）个货物
            // 让最重的 len / d 个货物一次性送完， right 就是这几个最重之和
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            int queueSize = len % d == 0 ? len / d : len / d + 1;
            for (int i = 0; i < queueSize; i++) {
                queue.offer(weights[i]);
            }
            for (int i = queueSize; i < len; i++) {
                if (!queue.isEmpty() && queue.peek() < weights[i]) {
                    queue.poll();
                    queue.offer(weights[i]);
                }
            }
            int right = 0;
            for (int weight : queue) {
                right += weight;
            }

            // 二分查找
            while (left < right) {
                int mid = left + (right - left) / 2;
                int tempWeight = 0, dayCount = 1;
                for (int weight : weights) {
                    if (tempWeight + weight > mid) {
                        dayCount++;
                        tempWeight = 0;
                    }
                    tempWeight += weight;
                }
                if (dayCount <= d) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    public static void main(String[] args) {
        int[] weights = {1,2,3,1,1};
        int d = 4;
        System.out.println(new Solution().shipWithinDays(weights, d));
    }
}

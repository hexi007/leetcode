package binarysearch;

import java.util.Collections;
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
        public int shipWithinDays(int[] weights, int d) {
            int len = weights.length;

            System.out.println(len % d == 0 ? len / d : len / d + 1);
            PriorityQueue<Integer> queue = new PriorityQueue<>(2,
                    Collections.reverseOrder());
            for (int weight : weights) {
                queue.offer(weight);
            }
            System.out.println(queue);

            return 0;
        }
    }

    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int d = 5;
        System.out.println(new Solution().shipWithinDays(weights, d));
    }
}

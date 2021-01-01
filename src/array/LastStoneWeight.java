package array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * description 有一堆石头，每块石头的重量都是正整数。  每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。
 * 假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/last-stone-weight 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2020-12-30 09:30
 **/
public class LastStoneWeight {

    static class Solution {
        Queue<Integer> queue;

        /**
         * 优先队列实现
         * (执行用时：2 ms, 在所有 Java 提交中击败了41.83%的用户)
         * (内存消耗：35.9 MB, 在所有 Java 提交中击败了55.65%的用户)
         *
         * @param stones 石头重量数组
         * @return       最后如果剩下一块石头则返回此石头的重量，否则返回 0。
         */
        public int lastStoneWeight(int[] stones) {
            // 定义优先队列
            queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int stone : stones) {
                queue.add(stone);
            }

            while (queue.size() > 1) {
                Integer y = queue.poll(), x = queue.poll();
                assert x != null;
                // 基本数据类型的包装类比较
                if (!x.equals(y)) {
                    queue.add(y - x);
                }
            }

            if (queue.size() == 1) {
                return queue.poll();
            } else {
                return 0;
            }
        }

        /**
         * 递归实现
         * (执行用时：1 ms, 在所有 Java 提交中击败了100%的用户)
         * (内存消耗：35.8 MB, 在所有 Java 提交中击败了64.32%的用户)
         *
         * @param stones 石头重量数组
         * @return       最后如果剩下一块石头则返回此石头的重量，否则返回 0。
         */
        public int lastStoneWeight1(int[] stones) {
            if (stones.length == 1) {
                return stones[0];
            }
            return crushStones(stones);
        }

        /**
         * 粉碎石头函数
         *
         * @param stones 石头重量数组
         * @return       最后如果剩下一块石头则返回此石头的重量，否则继续调用自身。
         */
        private int crushStones(int[] stones) {
            Arrays.sort(stones);
            // lastOne lastTwo 倒数第一（二）个石头，也就是最重（第二重）的石头
            int lastOne = stones.length - 1, lastTwo = stones.length - 2;
            // 如果倒数第二个石头为 0，则表示只剩一个石头
            if (stones[lastTwo] == 0) {
                return stones[lastOne];
            }
            // 修改最重的石头重量
            stones[lastOne] = stones[lastOne] - stones[lastTwo];
            // 第二重的石头被完全粉碎
            stones[lastTwo] = 0;
            return crushStones(stones);
        }
    }

    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        System.out.println(new Solution().lastStoneWeight(stones));
        System.out.println(new Solution().lastStoneWeight1(stones));
    }
}

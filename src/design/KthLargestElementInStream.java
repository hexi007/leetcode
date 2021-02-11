package design;

import java.util.PriorityQueue;

/**
 * description 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest 类：  KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-11 09:29
 **/
public class KthLargestElementInStream {

    static class KthLargest {

        int k;
        PriorityQueue<Integer> priorityQueue;

        /**
         * 大小为 K 的最小堆，不断维护使堆顶为第 k 大
         *
         * @param k    第 k 大
         * @param nums 初始数据流
         */
        public KthLargest(int k, int[] nums) {
            this.k = k;
            priorityQueue = new PriorityQueue<>(k);

            for (int num : nums) {
                add(num);
            }
        }

        /**
         * 堆不满元素直接入堆，否则只有元素大于堆顶才删除堆顶入新元素
         *
         * @param val 新元素
         * @return    当前数据流中第 k 大的元素
         */
        public int add(int val) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(val);
            } else if (!priorityQueue.isEmpty() && val > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.offer(val);
            }

            return !priorityQueue.isEmpty() ? priorityQueue.peek() : -1;
        }
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }
}

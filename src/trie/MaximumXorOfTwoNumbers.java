package trie;

/**
 * description 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-16 10:01
 **/
public class MaximumXorOfTwoNumbers {

    static class Solution {

        static class Node {
            Node[] children = new Node[2];
        }

        Node root = new Node();
        // 数字 32 位长
        int numLength = 32;

        /**
         * 数字插入字典树
         *
         * @param num 待插入数字
         */
        void add(int num) {
            Node temp = root;
            for (int i = numLength - 1; i >= 0; i--) {
                int flag = (num >> i) & 1;
                if (temp.children[flag] == null) {
                    temp.children[flag] = new Node();
                }
                temp = temp.children[flag];
            }
        }

        /**
         * 在字典树上选与数字当前位相反的路径走
         *
         * @param num 被比较数字
         */
        int getValue(int num) {
            int res = 0;
            Node temp = root;
            for (int i = numLength - 1; i >= 0; i--) {
                int a = (num >> i) & 1, b = 1 - a;
                if (temp.children[b] != null) {
                    res |= (b << i);
                    temp = temp.children[b];
                } else {
                    res |= (a << i);
                    temp = temp.children[a];
                }
            }
            return res;
        }

        /**
         * 每个数插入字典树，在字典树上尽量找与其异或后最大的数
         * (执行用时：33 ms, 在所有 Java 提交中击败了90.68%的用户)
         * (内存消耗：46.6 MB, 在所有 Java 提交中击败了51.86%的用户)
         *
         * @param nums 整数数组
         * @return     两数最大异或值
         */
        public int findMaximumXor(int[] nums) {
            int res = 0;
            for (int num : nums) {
                add(num);
                int value = getValue(num);
                res = Math.max(res, num ^ value);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,10,5,25,2,8};
        System.out.println(new Solution().findMaximumXor(nums));
    }
}

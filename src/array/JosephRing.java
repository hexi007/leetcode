package array;

import org.w3c.dom.Node;

/**
 * description 编号为 11 到 nn 的 nn 个人围成一圈。从编号为 11 的人开始报数，报到 mm 的人离开。
 * 下一个人继续从 11 开始报数。 n-1n−1 轮结束以后，只剩下一个人，问最后留下的这个人编号是多少？
 *
 * @author 27771
 * create 2021-09-27 09:53
 **/
public class JosephRing {

    static class Solution {
        /**
         * ysf
         *
         * @param n int整型
         * @param m int整型
         * @return int整型
         */
        public int ysf (int n, int m) {
            // write code here
            Node head = new Node(1), cur = head;
            for (int i = 2; i <= n; i++) {
                Node temp = new Node(i);
                cur.next = temp;
                cur = temp;
            }
            cur.next = head;
            cur = head;
            int count = 1;
            while (n > 0) {
                if (count + 1 < m) {
                    cur = cur.next;
                    count++;
                } else {
                    cur.next = cur.next.next;
                    count = 0;
                    n--;
                }
            }
            return cur.value;
        }

        private static class Node {
            int value;
            Node next;

            public Node (int value) {
                this.value = value;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().ysf(5, 2));
    }
}
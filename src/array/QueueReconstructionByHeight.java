package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description 假设有打乱顺序的一群人站成一个队列。
 * 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。
 * 编写一个算法来重建这个队列。
 *
 * @author 27771
 * create 2020-11-16 16:40
 **/
public class QueueReconstructionByHeight {

    static class Solution {

        /**
         * 身高升序，k 降序，再依次插入
         * (执行用时：21 ms, 在所有 Java 提交中击败了10.05% 的用户)
         * (内存消耗：39.8 MB, 在所有 Java 提交中击败了33.95% 的用户)
         * @param people 人队列
         * @return       重建后的队列
         */
        public int[][] reconstructQueue(int[][] people) {
            if (people.length == 0) {
                return new int[0][0];
            }
            // 身高升序，k 降序
            Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
            int[][] res = new int[people.length][people[0].length];
            boolean[] index = new boolean[people.length];
            // 确定插入位置
            for (int[] p : people) {
                int i = p[1], j = 0;
                do {
                    if (!index[j]) {
                        i--;
                    }
                    j++;
                } while (i >= 0);
                res[j - 1] = p;
                index[j - 1] = true;
            }
            return res;
        }

        /**
         * 按照 h 降序 K 升序排序，再依次插入
         * (执行用时：7 ms, 在所有 Java 提交中击败了99.62% 的用户)
         * (内存消耗：39.4 MB, 在所有 Java 提交中击败了83.12% 的用户)
         * @param people 人队列
         * @return       重建后的队列
         */
        public int[][] reconstructQueue1(int[][] people) {
            if (0 == people.length) {
                return new int[0][0];
            }
            //按照 h 降序 K 升序排序
            Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
            List<int[]> list = new ArrayList<>();
            //K 值定义为排在 h 前面且身高大于或等于 h 的人数
            //因为从身高降序开始插入，此时所有人身高都大于等于 h
            //因此 K 值即为需要插入的位置
            for (int[] i : people) {
                list.add(i[1], i);
            }
            return list.toArray(new int[list.size()][]);
        }
    }

    public static void main(String[] args) {
        int[][] people = {};
        int[][] res = new Solution().reconstructQueue(people);
        Arrays.stream(res).forEach((x) -> System.out.print(Arrays.toString(x) + " "));
        System.out.println();
        int[][] res1 = new Solution().reconstructQueue1(people);
        Arrays.stream(res1).forEach((x) -> System.out.print(Arrays.toString(x) + " "));
        System.out.println();
    }
}
package greedyalgorithm;

import java.util.Scanner;

/**
 * description 疫情逐步缓和后，电影院终于开业了，但是由于当前仍处于疫情期间，应尽量保持人群不聚集的原则。
 * 所以当小易来电影院选定一排后，尽量需要选择一个远离人群的位置。
 * 已知由0和1组成的数组表示当前排的座位情况,其中1表示已被选座，0表示空座 请问小易所选座位和最近人的距离座位数最大是多少？
 * 有如下假设：至少有一个人已选座，至少有一个空座位，且座位数限制为 2 <= length < 1000
 *
 * @author 27771
 * create 2021-01-14 19:24
 **/
public class CinemaSeatSelection {

    static class Solution {
        /**
         * 数连续个 1 ，注意开头和结尾
         *
         * @param a 座位数组
         * @return  和最近人的距离
         */
        public int maxDistance(int[] a) {
            int distance = 0, maxDistance = 0;
            boolean first = true;
            for (int d : a) {
                if (d == 1) {
                    if (first && a[0] == 0) {
                        maxDistance = Math.max(maxDistance, distance);
                        first = false;
                    } else {
                        maxDistance = Math.max(maxDistance,
                                distance % 2 == 0 ? distance / 2 : distance / 2 + 1);
                    }
                    distance = 0;
                } else {
                    distance++;
                }
            }
            if (a[a.length - 1] == 0) {
                maxDistance = Math.max(maxDistance, distance);
            }
            return maxDistance;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] s = input.split(" ");
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        System.out.println(new Solution().maxDistance(a));
    }
}

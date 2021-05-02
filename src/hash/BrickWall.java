package hash;

import java.util.*;

/**
 * description 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。
 * 每一行砖块的宽度之和应该相等。  你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。
 * 如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 * 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。
 * 你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/brick-wall 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-05-02 12:34
 **/
public class BrickWall {

    static class Solution {

        /**
         * 用 hash 统计每个砖块右边位置出现次数，在出现次数最多的位置画线
         * (执行用时：13 ms, 在所有 Java 提交中击败了94.69%的用户)
         * (内存消耗：41.6 MB, 在所有 Java 提交中击败了50.89%的用户)
         *
         * @param wall 一堵矩形的、由 n 行砖块组成的砖墙
         * @return     穿过的最少砖块数量
         */
        public int leastBricks(List<List<Integer>> wall) {
            HashMap<Integer, Integer> map = new HashMap<>(16);

            for (List<Integer> wallLine : wall) {
                int len = wallLine.size(), sum = 0;
                for (int i = 0; i < len - 1; i++) {
                    sum += wallLine.get(i);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }

            // maxCount 右边界最多出现次数
            int maxCount = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                maxCount = Math.max(maxCount, entry.getValue());
            }

            return wall.size() - maxCount;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> wall = new ArrayList<>();
        wall.add(Arrays.asList(1,2,2,1));
        wall.add(Arrays.asList(3,1,2));
        wall.add(Arrays.asList(1,3,2));
        wall.add(Arrays.asList(2,4));
        wall.add(Arrays.asList(3,1,2));
        wall.add(Arrays.asList(1,3,1,1));

        System.out.println(new Solution().leastBricks(wall));
    }
}

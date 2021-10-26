package graph;

import java.util.*;

/**
 * description 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
 * 给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修bi 。
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。
 * 如果不可能完成所有课程，返回 一个空数组 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/course-schedule-ii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-10-26 09:41
 **/
public class CourseScheduleTwo {

    static class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] in = new int[numCourses];
            Map<Integer, List<Integer>> out = new HashMap<>(16);
            for (int[] prerequisite : prerequisites) {
                in[prerequisite[0]]++;
                if (!out.containsKey(prerequisite[1])) {
                    List<Integer> list = new LinkedList<>();
                    list.add(prerequisite[0]);
                    out.put(prerequisite[1], list);
                } else {
                    out.get(prerequisite[1]).add(prerequisite[0]);
                }
            }
            Queue<Integer> courses = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (in[i] == 0) {
                    courses.add(i);
                }
            }
            int[] ans = new int[numCourses];
            int index = 0;
            while (courses.size() > 0) {
                int course = courses.poll();
                ans[index++] = course;
                List<Integer> list = out.get(course);
                if (list != null) {
                    for(int outCourse : list) {
                        in[outCourse]--;
                        if (in[outCourse] == 0) {
                            courses.add(outCourse);
                        }
                    }
                }
            }
            return index == numCourses ? ans : new int[]{};
        }
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        System.out.println(Arrays.toString(new Solution().findOrder(numCourses, prerequisites)));
    }
}

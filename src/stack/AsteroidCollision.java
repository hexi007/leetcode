package stack;

import java.util.Arrays;

/**
 * description 给定一个整数数组 asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。
 * 每一颗行星以相同的速度移动。  找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
 * 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/asteroid-collision 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-02 15:42
 **/
public class AsteroidCollision {

    static class Solution {

        /**
         * 最有前一个向左后一个向右的才会碰撞
         * (执行用时：2 ms, 在所有 Java 提交中击败了98.40%的用户)
         * (内存消耗：39.3 MB, 在所有 Java 提交中击败了39.19%的用户)
         *
         * @param asteroids 同一行的行星
         * @return          碰撞后剩下的所有行星
         */
        public int[] asteroidCollision(int[] asteroids) {
            int[] stack = new int[asteroids.length];
            int stackIndex = 0;
            for (int asteroid : asteroids) {
                if (stackIndex == 0) {
                    stack[stackIndex++] = asteroid;
                } else {
                    // asteroidLeft 最终 asteroid 是否需要保存
                    boolean asteroidLeft = true;
                    while (stackIndex != 0 && stack[stackIndex - 1] > 0 && asteroid < 0) {
                        if (stack[stackIndex - 1] < -1 * asteroid) {
                            stackIndex--;
                        } else {
                            if (stack[stackIndex - 1] == -1 * asteroid) {
                                stackIndex--;
                            }
                            // 两行星一样重或后一个比前一个轻，则 asteroid 不需要保存
                            asteroidLeft = false;
                            break;
                        }
                    }
                    if (asteroidLeft) {
                        stack[stackIndex++] = asteroid;
                    }
                }
            }

            return Arrays.copyOfRange(stack, 0, stackIndex);
        }
    }

    public static void main(String[] args) {
        int[] asteroids = {1,-2,-2,-2};
        System.out.println(Arrays.toString(new Solution().asteroidCollision(asteroids)));
    }
}

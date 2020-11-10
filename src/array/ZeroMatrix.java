package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * description 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * create 2020-11-09 20:13
 *
 * @author 27771
 **/
public class ZeroMatrix {

    static class Solution {


        Set<Integer> setX;
        Set<Integer> setY;
        /**
         * 用 Set 保存为 0 的行和列，在置 0
         * (执行用时：5 ms, 在所有 Java 提交中击败了5.96% 的用户)
         * (内存消耗：39.9 MB, 在所有 Java 提交中击败了89.82% 的用户)
         * @param matrix 矩阵
         */
        public void setZeroes(int[][] matrix) {
            setX = new HashSet<>();
            setY = new HashSet<>();
            int lenX = matrix.length, lenY = matrix[0].length;
            for (int i = 0; i < lenX; i++) {
                for (int j = 0; j < lenY; j++) {
                    if (matrix[i][j] == 0) {
                        setX.add(i);
                        setY.add(j);
                    }
                }
            }

            for (int i = 0; i < lenX; i++) {
                for (int j = 0; j < lenY; j++) {
                    if (setX.contains(i) || setY.contains(j)) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        /**
         * 用 ArrayList 保存为 0 的行和列，统计后将对应行列置 0
         * (执行用时：1 ms, 在所有 Java 提交中击败了99.97%的用户)
         * (的用户内存消耗：40.2 MB, 在所有 Java 提交中击败了61.31%的用户)
         * @param matrix 矩阵
         */
        public void setZeroes1(int[][] matrix) {
            ArrayList<Integer> list = new ArrayList<>();
            int lenX = matrix.length, lenY = matrix[0].length;
            for (int i = 0; i < lenX; i++) {
                for (int j = 0; j < lenY; j++) {
                    if (matrix[i][j] == 0) {
                        list.add(i);
                        list.add(j);
                    }
                }
            }

            while (!list.isEmpty()) {
                int x = list.get(0), y = list.get(1);
                for (int i = 0; i < lenY; i++) {
                    matrix[x][i] = 0;
                }
                for (int i = 0; i < lenX; i++) {
                    matrix[i][y] = 0;
                }
                list.remove(1);
                list.remove(0);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        new Solution().setZeroes1(matrix);
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
    }
}
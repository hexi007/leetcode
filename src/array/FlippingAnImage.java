package array;

import java.util.Arrays;

/**
 * description 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/flipping-an-image 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-02-24 09:21
 **/
public class FlippingAnImage {

    static class Solution {

        /**
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.8 MB, 在所有 Java 提交中击败了21.60%的用户)
         *
         * @param a 二进制矩阵
         * @return  处理后矩阵
         */
        public int[][] flipAndInvertImage(int[][] a) {
            int row = a.length, col = a[0].length, half = 2;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col / half; j++) {
                    // 左右翻转时 0 1 互换
                    int temp = a[i][j];
                    a[i][j] = 1 - a[i][col - j - 1];
                    a[i][col - j - 1] = 1 - temp;
                }
                // 奇数列时反转中间一列
                if ((col & 1) == 1) {
                    a[i][col / 2] = 1 - a[i][col / 2];
                }
            }
            return a;
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1,1,0},{1,0,1},{0,0,0}};
        a = new Solution().flipAndInvertImage(a);
        for (int[] arr : a) {
            System.out.println(Arrays.toString(arr));
        }
    }
}

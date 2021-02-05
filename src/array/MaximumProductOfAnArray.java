package array;

import java.util.Scanner;

/**
 * description 给定一个整型数组，移除数组的某个元素使其剩下的元素乘积最大，如果数组出现相同的元素 ，请输出第一次出现的元素
 *
 * @author 27771
 * create 2021-02-04 11:28
 **/
public class MaximumProductOfAnArray {

    static class Solution {

        /**
         * 先统计非负数和负数个数，再根据非负数和负数个数决定移除哪个数
         *
         * @param numbers 整型数组
         * @return        被移除数第一次出现的位置
         */
        public int index(int[] numbers) {
            // minus 负数个数 positives 正数个数
            int minus = 0, positives = 0;
            // minPositiveNumber 最小非负数
            int minPositiveNumber = Integer.MAX_VALUE;
            // maxMinusNumber 最大负数 minMinusNumber 最小负数
            int maxMinusNumber = Integer.MIN_VALUE, minMinusNumber = 0;

            for (int number : numbers) {
                if (number >= 0) {
                    minPositiveNumber = Math.min(minPositiveNumber, number);
                    positives++;
                } else {
                    maxMinusNumber = Math.max(maxMinusNumber, number);
                    minMinusNumber = Math.min(minMinusNumber, number);
                    minus++;
                }
            }

            // 全是非负数，移除最小非负数
            if (minus == 0) {
                for (int i = 0; i < numbers.length; i++) {
                    if (numbers[i] == minPositiveNumber) {
                        return i;
                    }
                }
            }

            // 全是负数，移除最小负数
            if (positives == 0) {
                for (int i = 0; i < numbers.length; i++) {
                    if (numbers[i] == minMinusNumber) {
                        return i;
                    }
                }
            }

            // 负数个数为奇数，移除最小非负数
            if ((minus & 1) == 0) {
                for (int i = 0; i < numbers.length; i++) {
                    if (numbers[i] == minPositiveNumber) {
                        return i;
                    }
                }
            }

            // 负数个数为奇数，移除最大负数
            if ((minus & 1) == 1) {
                for (int i = 0; i < numbers.length; i++) {
                    if (numbers[i] == maxMinusNumber) {
                        return i;
                    }
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String[] temp = s.split(",");
        int[] a = new int[temp.length];

        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(temp[i]);
        }

        System.out.println(new Solution().index(a));
    }
}

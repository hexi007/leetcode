package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * description 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * @author 27771
 * create 2021-02-20 20:41
 **/
public class MaximumSwap {

    static class Solution {


        public int maximumSwap(int num) {
            ArrayList<Integer> list = new ArrayList<>();
            int numCopy = num;
            while (numCopy > 0) {
                list.add(numCopy % 10);
                numCopy /= 10;
            }
            int len = list.size();
            int[] numArray = new int[len];
            for (int i = len - 1, j = 0; i >= 0; i--) {
                numArray[j++] = list.get(i);
            }

            int[] numCopyArray = Arrays.copyOf(numArray, len);
            list.sort(Comparator.comparingInt(o -> o));
            for (int i = len - 1, j = 0; i >= 0; i--) {
                numCopyArray[j++] = list.get(i);
            }

            for (int i = 0; i < len; i++) {
                if (numArray[i] != numCopyArray[i]) {
                    for (int j = len - 1; j > i; j--) {
                        if (numArray[j] == numCopyArray[i]) {
                            int temp = numArray[i];
                            numArray[i] = numCopyArray[i];
                            numArray[j] = temp;

                            int res = 0;
                            for (int k = 0; k < len; k++) {
                                res = res * 10 + numArray[k];
                            }
                            return res;
                        }
                    }
                }
            }

            return num;
        }
    }

    public static void main(String[] args) {
        int num = 1993;
        System.out.println(new Solution().maximumSwap(num));
    }
}

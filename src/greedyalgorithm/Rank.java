package greedyalgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description 给定长度为 m 的序列 T ,求一个长度为 n 且字典序最小的排列.
 * 并且要求序列 T 为所求排列的子序列.题目保证这样的排列一定存在.
 * S 是 T 的子序列,当且仅当 S 是 T 通过删除任意数量元素所得到的.
 * 字典序是单词在字典中的排列顺序，先比较第一个字母，然后比较第二个字母，依次类推。
 *
 * @author 27771
 * create 2021-01-01 16:53
 **/
public class Rank {

    static class Solution {

        public int[] rank(int[] sequence, int n) {
            int[] map = new int[100001];
            for (int m : sequence) {
                map[m] = 1;
            }
            int[] res = new int[n + 1];
            int index = 0, indexSequence = 0;
            for (int i = 1; index < n; i++) {
                if (i > sequence[indexSequence] || map[i] == 1) {
                    res[index++] = sequence[indexSequence++];
                    i--;
                } else {
                    res[index++] = i;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Scanner input=new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        int[] a = new int[m];
        int[] res = solution.rank(a, n);
        //System.out.println(Arrays.toString(res));
    }
}
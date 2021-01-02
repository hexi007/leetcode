package greedyalgorithm;

import java.util.*;

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

        public int[] rank(int[] sequence, int n, int m) {
            // set 存放子序列
            Set<Integer> set = new HashSet<>();
            for (int s : sequence) {
                set.add(s);
            }
            int[] res = new int[n];
            int index = 0, indexSequence = 0;
            for (int i = 1; index < n; i++) {
                // 只有 i 不在 set 时才考虑 res 中放入结果
                if (!set.contains(i)) {
                    // i 大于等于当前子序列数时，res 中放的是当前子序列的数
                    if (indexSequence < m &&  i >= sequence[indexSequence]) {
                        res[index++] = sequence[indexSequence++];
                        // i 还没放入 res,自减等待下一次放入机会
                        i--;
                    } else {
                        // 否则将 i 放入
                        res[index++] = i;
                    }
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = input.nextInt();
        }
        int[] res = solution.rank(a, n, m);
        for (int i = 0; i < n - 1; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println(res[n - 1]);
    }
}
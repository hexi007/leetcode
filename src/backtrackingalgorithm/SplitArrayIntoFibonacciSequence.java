package backtrackingalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * description 给定一个数字字符串 S，比如 S = "123456579"
 * 我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：  0 <= F[i] <= 2^31 - 1，
 * （也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3； 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 *
 * @author 27771
 * create 2020-12-08 10:43
 **/
public class SplitArrayIntoFibonacciSequence {

    static class Solution {

        List<Integer> list;
        int length;
        char[] chars;

        /**
         * 使用回溯算法拆分数字字符串
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：37 MB, 在所有 Java 提交中击败了92.20%的用户)
         *
         * @param s 数字字符串
         * @return  拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []
         */
        public List<Integer> splitIntoFibonacci(String s) {
            list = new ArrayList<>();
            length = s.length();
            chars = s.toCharArray();
            backtrack(0, 0, 0);
            return list;
        }

        /**
         * 回溯法求拆分情况
         *
         * @param index 开始才分下标
         * @param sum   前两数之和
         * @param prev  前一个数
         * @return      从当前位置开始是否可以继续拆分下去
         */
        private boolean backtrack(int index, int sum, int prev) {
            // 到达最后一个字符时如果 list.size() 小于 3 则证明这种拆分方法不可行
            if (index == length) {
                return list.size() >= 3;
            }
            // 从 index 开始表示的数
            long currLong = 0;
            for (int i = index; i < length; i++) {
                // 去掉 0 开头的情况
                if (i > index && chars[index] == '0') {
                    break;
                }
                currLong = currLong * 10 + chars[i] - '0';
                // 判断是否溢出
                if (currLong > Integer.MAX_VALUE) {
                    break;
                }
                int currInt = (int) currLong;
                // 如果 list 里至少有两个数，那么当前这数应为前两数之和
                if (list.size() >= 2) {
                    // 小于前两数之和则继续生成新的数，大于前两数之和则直接退出循环
                    if (currInt < sum) {
                        continue;
                    } else if (currInt > sum) {
                        break;
                    }
                }
                // 满足当前这数为前两数之和的情况，将其加入 list
                list.add(currInt);
                // 从 i + 1 开始继续回溯， 新的前两数之和为 prev + currInt， 前一个数就是刚加入list 的数
                if (backtrack(i + 1, prev + currInt, currInt)) {
                    return true;
                } else {
                    // 不能继续搜索下去就就进行回溯，将当前 list 最后的一个数去除
                    list.remove(list.size() - 1);
                }
            }
            // 能运行到这表示从 i 开始继续拆分数字字符串是不可行的
            return false;
        }
    }

    public static void main(String[] args) {
        String s = "123456579";
        System.out.println(new Solution().splitIntoFibonacci(s));
    }
}

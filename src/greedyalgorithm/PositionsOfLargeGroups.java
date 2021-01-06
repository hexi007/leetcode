package greedyalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。
 * 上例中的 "xxxx" 分组用区间表示为 [3,6] 。  我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/positions-of-large-groups 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-01-05 09:48
 **/
public class PositionsOfLargeGroups {

    static class Solution {

        List<List<Integer>> res;
        char[] sChars;

        /**
         * (执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.5 MB, 在所有 Java 提交中击败了81.50%的用户)
         *
         * @param s 字符串
         * @return  每一个较大分组的区间
         */
        public List<List<Integer>> largeGroupPositions(String s) {
            res = new ArrayList<>();
            sChars = s.toCharArray();

            int i = 0, len = sChars.length;
            for (int j = 1; j < len; j++) {
                if (sChars[j] != sChars[i]) {
                    if (j - i >= 3) {
                        res.add(Arrays.asList(i , j - 1));
                    }
                    i = j;
                }
                if (j == len - 1 && len - i >= 3) {
                    res.add(Arrays.asList(i , len - 1));
                    break;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        String s = "aaa";
        for (List<Integer> l : new Solution().largeGroupPositions(s)) {
            System.out.println(l);
        }
    }
}
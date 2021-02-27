package union_finddisjointsets;

/**
 * description 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。
 * 输出 T 的长度。
 *
 * @author 27771
 * create 2021-02-27 09:21
 **/
public class LongestSubstringWithRepeatingCharacters {

    static class Solution {

        /**
         * 先统计所有字符出现次数，按不满足条件字符划分字符串，递归调用自身
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：36.3 MB, 在所有 Java 提交中击败了84.80%的用户)
         *
         * @param s 字符串（由小写字符组成）
         * @param k 最少出现次数
         * @return  最长子串长度
         */
        public int longestSubstring(String s, int k) {
            int res = 0, len = s.length();
            char[] chars = s.toCharArray();

            // count 统计每个字符出现次数
            int[] count = new int[26];
            for (int i = 0; i < len; i++) {
                count[chars[i] - 'a']++;
            }

            // check 判断字符出现次数是否小于 k
            boolean[] check = new boolean[26];
            for (int i = 0; i < check.length; i++) {
                if (count[i] < k) {
                    check[i] = true;
                }
            }

            // start 划分字符串起始位置
            int start = 0;
            // allCheck 判断整个字符串是否满足条件
            boolean allCheck = true;
            for (int i = 0; i < len; i++) {
                // 出现某个字符不满足条件加开始划分
                if (check[chars[i] - 'a']) {
                    // 整个字符串不满足条件
                    allCheck = false;
                    if (start < i) {
                        // 递归调用自身
                        res = Integer.max(res, longestSubstring(s.substring(start, i), k));
                    }
                    // 修改字符串起始位置
                    start = i + 1;
                }
            }

            if (allCheck) {
                // 整个字符串都满足的情况
                res = len;
            } else {
                // 整个字符串不满足时，如果 start 小于整个字符串长度还要再划分一次
                if (start < len) {
                    res = Integer.max(res, longestSubstring(s.substring(start, len), k));
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        String s = "ababbc";
        int k = 2;
        System.out.println(new Solution().longestSubstring(s, k));
    }
}

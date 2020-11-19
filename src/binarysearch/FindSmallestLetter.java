package binarysearch;

/**
 * description 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。
 * 另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 在比较时，字母是依序循环出现的。举个例子：
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
 *
 * @author 27771
 * create 2020-11-19 10:37
 **/
public class FindSmallestLetter {

    static class Solution {

        /**
         * 考虑左右边界
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：39 MB, 在所有 Java 提交中击败了59.39%的用户)
         * @param letters 排序后的字符列表
         * @param target  目标字母
         * @return        有序列表里比目标字母大的最小字母
         */
        public char nextGreatestLetter(char[] letters, char target) {
            int left = 0, right = letters.length - 1, mid = 0;
            while (left <= right) {
                mid = (left + right) >>> 1;
                // 左边界
                if (mid == 0) {
                    if (letters[mid] <= target && letters[mid + 1] > target) {
                        mid = 1;
                        break;
                    }
                    break;
                }
                // 右边界
                if (mid == letters.length - 1) {
                    if (letters[mid - 1] <= target && letters[mid] > target) {
                        break;
                    }
                    mid = 0;
                    break;
                }
                if (letters[mid - 1] <= target && letters[mid] > target) {
                    break;
                }
                if (letters[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return letters[mid];
        }

        /**
         * 简洁的递归
         * (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         * (内存消耗：38.8 MB, 在所有 Java 提交中击败了82.84%的用户)
         * @param letters 排序后的字符列表
         * @param target  目标字母
         * @return        有序列表里比目标字母大的最小字母
         */
        public char nextGreatestLetter1(char[] letters, char target) {
            // 右边界不是 letters.length - 1
            int lo = 0, hi = letters.length;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (letters[mi] <= target) {
                    lo = mi + 1;
                } else {
                    hi = mi;
                }
            }
            return letters[lo % letters.length];
        }
    }

    public static void main(String[] args) {
        char[] letters = {'a', 'b', 'j'};
        char target = 'a';
        System.out.println(new Solution().nextGreatestLetter(letters, target));
    }
}

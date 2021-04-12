package sort;

import java.util.Arrays;

/**
 * description 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * @author 27771
 * create 2021-04-12 09:44
 **/
public class LargestNumber {

    static class Solution {

        /**
         * 转成字符串数组拼接后比较大小
         * (执行用时：6 ms, 在所有 Java 提交中击败了91.49%的用户)
         * (内存消耗：37.7 MB, 在所有 Java 提交中击败了88.20%的用户)
         *
         * @param nums 非负整数组
         * @return     重新排列每个数的顺序使之组成的一个最大整数
         */
        public String largestNumber(int[] nums) {
            int len = nums.length;
            String[] strings = new String[len];
            for (int i = 0; i < len; i++) {
                strings[i] = String.valueOf(nums[i]);
            }
            Arrays.sort(strings, (o1, o2) -> {
                int len1 = o1.length(), len2 = o2.length();
                char[] chars1 = new char[len1 + len2], chars2 = new char[len1 + len2];
                for (int i = 0; i < len1; i++) {
                    chars1[i] = o1.charAt(i);
                    chars2[len2 + i] = o1.charAt(i);
                }
                for (int i = 0; i < len2; i++) {
                    chars1[len1 + i] = o2.charAt(i);
                    chars2[i] = o2.charAt(i);
                }

                for (int i = 0; i < len1 + len2; i++) {
                    if (chars1[i] < chars2[i]) {
                        return 1;
                    } else if (chars1[i] > chars2[i]) {
                        return -1;
                    }
                }

                return 0;
            });

            StringBuilder sb = new StringBuilder();
            for (String s : strings) {
                sb.append(s);
            }
            String res = sb.toString();

            // 如果结果首字符是 0 那么整个字符都是 0
            return res.charAt(0) == '0' ? "0" : res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {432,43243};
        System.out.println(new Solution().largestNumber(nums));
    }
}

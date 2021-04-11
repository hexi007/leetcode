package string;

/**
 * description 给你两个版本号 version1 和 version2 ，请你比较它们。
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。
 * 每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，
 * 下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。
 * 也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。
 * 例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 * 返回规则如下：  如果 version1 > version2 返回 1， 如果 version1 < version2 返回 -1， 除此之外返回 0。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/compare-version-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-11 11:09
 **/
public class CompareVersionNumbers {

    /**
     * 用 split 分割字符串再比较修订号
     * (执行用时：1 ms, 在所有 Java 提交中击败了83.95%的用户)
     * (内存消耗：36.8 MB, 在所有 Java 提交中击败了16.46%的用户)
     *
     * @param version1 版本号 1
     * @param version2 版本号 2
     * @return         版本号比较结果
     */
    public static int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int len1 = s1.length, len2 = s2.length, maxLen = Math.max(len1, len2);

        for (int i = 0; i < maxLen; i++) {
            // 超出长度则默认为 0

            int temp1 = i < len1 ? Integer.parseInt(s1[i]) : 0;
            int temp2 = i < len2 ? Integer.parseInt(s2[i]) : 0;
            if (temp1 < temp2) {
                return -1;
            } else if (temp1 > temp2) {
                return 1;
            }
        }

        return 0;
    }

    /**
     * 分别计算当前版本号
     * (执行用时：0 ms, 在所有 Java 提交中击败了100%的用户)
     * (内存消耗：36.7 MB, 在所有 Java 提交中击败了28.31%的用户)
     *
     * @param version1 版本号 1
     * @param version2 版本号 2
     * @return         版本号比较结果
     */
    public static int compareVersion1(String version1, String version2) {
        char[] chars1 = version1.toCharArray();
        int len1 = chars1.length;
        char[] chars2 = version2.toCharArray();
        int len2 = chars2.length;

        for (int i = 0, j = 0; i < len1 || j < len2; ) {
            int num1 = 0;
            while (i < len1) {
                char c = chars1[i++];
                if (c == '.') {
                    break;
                }
                num1 = num1 * 10 + c - '0';
            }

            int num2 = 0;
            while (j < len2) {
                char c = chars2[j++];
                if (c == '.') {
                    break;
                }
                num2 = num2 * 10 + c - '0';
            }

            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String version1 = "7.5.2.4", version2 = "7.5.3";
        System.out.println(compareVersion(version1, version2));
        System.out.println(compareVersion1(version1, version2));
    }
}

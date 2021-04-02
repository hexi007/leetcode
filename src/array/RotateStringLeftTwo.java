package array;

/**
 * description 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-04-02 19:54
 **/
public class RotateStringLeftTwo {

    static class Solution {
        public String reverseLeftWords(String s, int n) {
            // return s.substring(n) + s.substring(0, n);

            char[] chars = s.toCharArray();
            int len = chars.length;
            reverse(chars, 0, n - 1);
            reverse(chars, n, len - 1);
            reverse(chars, 0, len - 1);
            return String.valueOf(chars);
        }

        private void reverse(char[] input, int start, int end) {
            if (start < end) {
                int half = 2;
                for (int i = start; i < (start + end + 1) / half; i++) {
                    char temp = input[i];
                    input[i] = input[start + end - i];
                    input[start + end - i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int n = 2;
        System.out.println(new Solution().reverseLeftWords(s, n));
    }
}

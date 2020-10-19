package string;

/**
 * description 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 *
 * @author 27771
 * create 2020-10-19 09:52
 **/
public class BackspaceStringCompare {
    static class Solution {
        /**
         标准双指针，一开始想复杂了
         (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         (内存消耗：36 MB, 在所有 Java 提交中击败了100.00%的用户)
         * @param S 字符串S
         * @param T 字符串T
         * @return  退格后两字符串是否相等
         */
        public boolean backspaceCompare(String S, String T) {
            int i = S.length() - 1,j =T.length() - 1;
            int iCount = 0, jCount = 0;
            //只要还有串没访问完就继续扫描
            while (i >= 0 || j >= 0){
                while (i >= 0){
                    if(S.charAt(i) == '#'){
                        iCount++;
                        i--;
                    } else if(iCount > 0){
                        iCount--;
                        i--;
                    } else {
                        break;
                    }
                }
                while (j >= 0){
                    if(T.charAt(j) == '#'){
                        jCount++;
                        j--;
                    } else if(jCount > 0){
                        jCount--;
                        j--;
                    } else {
                        break;
                    }
                }
                if(i >= 0 && j >= 0){
                    if(S.charAt(i) != T.charAt(j)){
                        return false;
                    }
                } else {
                    //注意某串已经扫描完而另一串还有，那就不可能相等
                    if(i >= 0 || j >= 0){
                        return false;
                    }
                }
                //当前位置相等，两串继续逆序扫描
                i--;
                j--;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String S = "abc##c", T = "adc###c";
        System.out.println(new Solution().backspaceCompare(S, T));
    }
}

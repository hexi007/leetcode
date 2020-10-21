package string;

/**
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 *
 * @author 27771
 * create 2020-10-21 18:25
 **/
public class LongPressedName {
    static class Solution {
        /**
         * 暴力搜
         * (执行用时：1 ms, 在所有 Java 提交中击败了86.83% 的用户)
         * (内存消耗：36.7 MB, 在所有 Java 提交中击败了65.90% 的用户)
         * @param name 名字
         * @param typed 输入字符串
         * @return  输入字符串去掉不必要的长按后是否为名字
         */
        public boolean isLongPressedName(String name, String typed) {
            //首字母不同直接退出
            if(name.length() == 0 || typed.length() == 0 || name.charAt(0) != typed.charAt(0)){
                return false;
            }
            int j = 0;
            for(int i = 0; i < typed.length(); i++){
                //相同继续比较
                if(typed.charAt(i) == name.charAt(j)){
                    j++;
                } else {
                    //是否长按
                    if(i > 0 && (typed.charAt(i) == typed.charAt(i - 1))){
                        continue;
                    } else {
                        j = 0;
                    }
                }
                //name串走到头了
                if(j == name.length() ){
                    //type结尾处可能长按
                    while(i + 1 < typed.length()){
                        if(typed.charAt(i + 1) != typed.charAt(i)){
                            return false;
                        }
                        i++;
                    }
                    return i + 1 == typed.length();
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        String name = "zlexya";
        String typed = "aazlllllllllllllleexxxxxxxxxxxxxxxya";
        System.out.println(new Solution().isLongPressedName(name, typed));
    }
}
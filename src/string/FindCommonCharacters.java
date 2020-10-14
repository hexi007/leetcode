package string;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 27771
 * description 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * create 2020-10-14 09:23
 **/
public class FindCommonCharacters {
    static class Solution {

        List<String> ret;
        /**
        * 使用两个数组储存字符出现频率
        * (执行用时：8 ms, 在所有 Java 提交中击败了51.12%的用户)
        * (内存消耗：38.3 MB, 在所有 Java 提交中击败了100.00%的用户)
        * @param A 字符串数组
        * @return 列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表
        */
        public List<String> commonChars(String[] A) {
            ret = new LinkedList<>();
            if(A.length == 1){
                return ret;
            }
            //temp1储存之前所有字符串数组中字符出现频率之交
            int[] temp1 = new int[26];
            for(char c : A[0].toCharArray()){
                temp1[c - 'a']++;
            }
            for(int i = 1; i < A.length; i++){
                //temp2储存当前要比较字符串中各字符出现频率
                int[] temp2 = new int[26];
                for(char c : A[i].toCharArray()){
                    temp2[c - 'a']++;
                }
                //temp1和temp2取交集
                for(int j = 0; j < temp1.length; j++){
                    temp1[j] = Math.min(temp1[j], temp2[j]);
                }
            }
            //将temp1结果保存到列表中
            for(int i = 0; i < temp1.length; i++){
                if(temp1[i] > 0){
                    for(int j = 0 ; j < temp1[i]; j++){
                        ret.add((char)('a' + i) + "");
                    }
                }
            }
            return ret;
        }

        public <T> void printList(List<T> ret) {
            for(T t : ret){
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] A = new String[]{"bella","label","roller"};
        Solution solution = new Solution();
        List<String> ret = solution.commonChars(A);
        solution.printList(ret);
    }
}

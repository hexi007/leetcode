package string;

import java.util.ArrayList;
import java.util.List;

/**
 * description 划分字母区间
 *
 * @author 27771
 * create 2020-10-22 18:36
 **/
public class PartitionLabels {
    static class Solution {
        /**
         * 用数组保存每个字母出现最后的位置，在不断遍历求片段长的子串
         * (执行用时：3 ms, 在所有 Java 提交中击败了96.93% 的用户)
         * (内存消耗：37 MB, 在所有 Java 提交中击败了96.11% 的用户)
         * @param s 字符串
         * @return  每个子串长度
         */
        public List<Integer> partitionLabels(String s) {
            List<Integer> ret = new ArrayList<>();
            if(s == null){
                return ret;
            }

            char[] chars = s.toCharArray();
            int len = chars.length;

            //用数组保存每个字母出现最后的位置
            int[] lastIndex = new int[26];
            for(int i = 0; i < len; i++){
                lastIndex[chars[i] - 'a'] = i;
            }

            //start：片段开始的位置，end:片段结束的位置
            //从i = 0开始遍历
            int start = 0, end = 0;
            for(int i = 0 ; i < len; i++){
                //不断比较求出过程中字母最后位置
                end = Math.max(end, lastIndex[chars[i] - 'a']);
                //只有当前字母位置就是end是时才算找到了合格的子串
                if(i == end){
                    //保存长度
                    ret.add(end - start + 1);
                    //更新start,开始找下一个片段
                    start = end + 1;
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> ret = new Solution().partitionLabels(s);
        for(Integer i : ret){
            System.out.print(i+ " ");
        }
    }
}
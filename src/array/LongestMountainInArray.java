package array;

import javax.sound.midi.SoundbankResource;
import java.util.Vector;

/**
 * description 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 *
 * @author 27771
 * create 2020-10-25 18:58
 **/
public class LongestMountainInArray {

    static class Solution {
        /** 
         中心扩张
         (执行用时：2 ms, 在所有 Java 提交中击败了 100% 的用户)
         (内存消耗：39.2 MB, 在所有 Java 提交中击败了 94% 的用户)
         * @param A 输入数组
         * @return  最长山脉长度
         */
        public int longestMountain(int[] A) {
            if(A.length < 3){
                return 0;
            }
            int maxMountain = 0;
            for(int i = 1; i < A.length - 1; i++){
                //从山顶往两边扩张
                if(A[i] > A[i - 1] && A[i] > A[i+1]){
                    int left = i - 1, right = i + 1;
                    while (left > 0 && A[left - 1] < A[left]){
                        left--;
                    }
                    while (right < A.length - 1 && A[right + 1] < A[right]){
                        right++;
                    }
                    maxMountain = Math.max(maxMountain, right - left + 1);
                }
            }
            return maxMountain;
        }

        /**
         一种C++解法
         int longestMountain(vector<int>& A) {
         if(A.empty()) return 0;

         //首尾增加上元素，使边界元素操作一般化
         A.insert(A.begin(), A.front());
         A.push_back(A.back());

         int len = A.size(), pos = len;
         int res=0;
         for(int i=1; i<len-1; ++i){
         if(A[i-1]>=A[i] && A[i]<=A[i+1] && A[i-1]+A[i+1]!=A[i]<<1) {
            res = max(res, i - pos + 1), pos = i;
         if(A[i]==A[i+1]) pos = len;
         }
         return res;
         }
         */
    }

    public static void main(String[] args) {
        int[] A = {2,2,2};
        System.out.println(new Solution().longestMountain(A));
    }
}

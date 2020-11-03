package array;

/**
 * description 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。 <br/>
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：<br/>
 * A.length >= 3 在 0 < i < A.length - 1 条件下，存在 i 使得：<br/>
 * A[0] < A[1] < ... A[i-1] < A[i] <br/>
 * A[i] > A[i+1] > ... > A[A.length - 1] <br/>
 *
 * @author 27771
 * create 2020-11-03 10:50
 **/
public class ValidMountainArray {
    static class Solution {
        /**
          从左往右找
          (执行用时：2 ms, 在所有 Java 提交中击败了41.65%的用户)
          (内存消耗：39.7 MB, 在所有 Java 提交中击败了39.98%的用户)
         * @param A 整数数组
         * @return  是否是山脉数组
         */
        public boolean validMountainArray(int[] A) {
            if(A.length < 3 || A[1] <= A[0]) {
                return false;
            }
            //方向， true 为上山， false 为下山
            boolean up = true;
            for(int i = 1; i < A.length; i++){
                if(up){
                    if(A[i] > A[i - 1]){
                        continue;
                    }
                    //转换方向
                    if(A[i] < A[i - 1]){
                        up = false;
                    } else if(A[i] == A[i - 1]){
                        return false;
                    }
                } else {
                    if(A[i] < A[i - 1]){
                        continue;
                    }
                    if(A[i] > A[i - 1] || A[i] == A[i - 1]){
                        return false;
                    }
                }
            }
            return !up;
        }

        /**
         从左往右找,不同是循环判断上升在循环判断下降，省去了上面一种无用的循环
         (执行用时：1 ms, 在所有 Java 提交中击败了100%的用户)
         (内存消耗：39.3 MB, 在所有 Java 提交中击败了90.57%的用户)
         * @param A 整数数组
         * @return  是否是山脉数组
         */
        public boolean validMountainArray1(int[] A){
            int lena = A.length;
            int index = -1;
            for (int i = 0; i < lena - 1; i++) {
                if (A[i] > A[i+1]) {
                    index = i;
                    break;
                }
            }
            if (index == -1 || index == 0) {
                return false;
            } else {
                for (int i = 0; i <= index - 1; i++) {
                    if (A[i] >= A[i+1]) {
                        return false;
                    }
                }
                for (int i = index + 1; i < lena - 1; i++) {
                    if (A[i] <= A[i+1]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[] A = {0,3,2,1};
        System.out.println(new Solution().validMountainArray(A));
    }
}

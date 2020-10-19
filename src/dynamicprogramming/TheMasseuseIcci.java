package dynamicprogramming;

/**
 * description 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
 * 在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 *
 * @author 27771
 * create 2020-10-19 11:12
 **/
public class TheMasseuseIcci {
    static class Solution {
        /**
         普通动态规划，如果前i个总预约时间最长为s(i),那么s(i) 等于 s(i-2) + nums[i] 和s(i-1)之间的最大值
         (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         (内存消耗：35.4 MB, 在所有 Java 提交中击败了99.2%的用户)
         * @param nums 输入预约序列
         * @return  最长总预约时间
         */
        public int massage(int[] nums) {
            if(nums.length == 0){
                return 0;
            }
            if(nums.length == 1){
                return nums[0];
            }
            //sum1存s(i-1)，sum2存s(i-2)，注意开始时，sum1是前两个的最大值
            int sum1 = Math.max(nums[0], nums[1]), sum2 = nums[0], sum = sum1;
            int i = 2;
            while(i < nums.length){
                sum = Math.max((sum2 + nums[i]), sum1);
                //记得更新s(i-1)，s(i-2)
                sum2 = sum1;
                sum1 = sum;
                i++;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,1,4,5,3,1,1,3};
        System.out.println(new Solution().massage(nums));
    }
}

package array;

/**
 * description 给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * 实现 NumArray 类：  NumArray(int[] nums) 使用数组 nums 初始化对象 int sumRange(int i, int j)
 * 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点
 * （也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/range-sum-query-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-03-01 17:06
 **/
public class RangeSumQueryImmutable {

    /**
     * (执行用时：9 ms, 在所有 Java 提交中击败了99.83%的用户)
     * (内存消耗：40.9 MB, 在所有 Java 提交中击败了99.39%的用户)
     */
    static class NumArray {

        int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}

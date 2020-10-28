package greedyalgorithm;

/**
 * description 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。
 * 这些片段可能有所重叠，也可能长度不一。
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。
 * 我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。
 * 返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 *
 * @author 27771
 * create 2020-10-28 10:48
 **/
public class VideoStitching {
    static class Solution {
        /**
         贪心解法，每次尽可能找最长的符合条件的最长片段
         (执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户)
         (内存消耗：35.8 MB, 在所有 Java 提交中击败了61.51%的用户)
         * @param clips 视频片段
         * @param T 持续时长
         * @return  所需片段的最小数目
         */
        public int videoStitching(int[][] clips, int T) {
            /*
             * @param tempRight 当前选取片段开始的右边位置
             * @param tempLeft 当前选取片段开始最小位置
             * @param res  所取片段数目
             */
            int tempRight = T, tempLeft = T, res = 0;
            //只要左边还没取到 0 就继续找视频片段
            while (tempLeft > 0){
                for (int[] clip : clips) {
                    //找满足右边条件且左边最小的视频片段
                    if (clip[1] >= tempRight && clip[0] < tempLeft) {
                        tempLeft = clip[0];
                    }
                }
                //如果找了一轮没有满足条件的片段，直接返回 -1
                if(tempLeft == tempRight){
                    return -1;
                }
                //更新当前右边
                tempRight = tempLeft;
                res++;
            }
            //如果最后找到的左边不是 0 ，代表没有合格的能拼接成完整视频的片段
            if(tempLeft != 0){
                return -1;
            } else {
                return res;
            }
        }
    }

    public static void main(String[] args) {
        //int[][] clips = {{0,2},{4,6}, {8,10}, {1, 9}, {1,5},{5, 9}};
        int[][] clips = {{0,1},{2,3}};
        System.out.println(new Solution().videoStitching(clips, 3));
    }
}

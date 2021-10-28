package array;

import java.util.*;

/**
 * description 给定正整数 N，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reordered-power-of-2 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 27771
 * create 2021-10-28 15:24
 **/
public class ReorderedPowerOfTwo {

    static class Solution {
        public boolean reorderedPowerOf2(int n) {
            boolean ans = false;
            Map<Integer, List<Long>> map = getMap();
            int tempN = n, len = 0;
            List<Integer> listN = new ArrayList<>();
            while (tempN > 0) {
                listN.add(tempN % 10);
                len++;
                tempN /= 10;
            }
            Collections.sort(listN);
            List<Long> list = map.get(len);
            for (long num : list) {
                ans = compare(listN, num);
                if (ans) {
                    break;
                }
            }
            return ans;
        }

        private boolean compare(List<Integer> listN, long num) {
            long tempNum = num;
            List<Integer> list = new ArrayList<>();
            while (tempNum > 0) {
                list.add((int) (tempNum % 10));
                tempNum /= 10;
            }
            Collections.sort(list);
            for (int i = 0, size = listN.size(); i < size; i++) {
                if (!listN.get(i).equals(list.get(i))) {
                    return false;
                }
            }
            return true;
        }

        private Map<Integer, List<Long>> getMap() {
            long i = 1, maxNum = (long) 10e9;
            Map<Integer, List<Long>> map = new HashMap<>(16);
            while (i < maxNum) {
                long tempI = i;
                int len = 0;
                while (tempI > 0) {
                    len++;
                    tempI /= 10;
                }
                if (map.containsKey(len)) {
                    map.get(len).add(i);
                } else {
                    List<Long> list = new ArrayList<>();
                    list.add(i);
                    map.put(len, list);
                }
                i = i * 2;
            }
            return map;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reorderedPowerOf2(16));
    }
}

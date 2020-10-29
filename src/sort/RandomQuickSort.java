package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * description 随机化快速排序
 * https://www.cnblogs.com/chuji1988/p/4202250.html
 *
 * @author 27771
 * create 2020-10-27 21:39
 **/
public class RandomQuickSort {

    private void randomQuickSort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     *  递归的排序两个区间
     * @param a 待排序数组
     * @param left 待排序区间左边界
     * @param right 待排序区间右边界
     */
    private void sort(int[] a, int left, int right) {
        if(left < right){
            int p = randomPartition(a, left, right);
            sort(a, left, p - 1);
            sort(a, p + 1, right);
        }
    }

    /**
     * 随机化待排序区间最右元素，再使基准数移到一个位置从而左边的数都比它小，右边的数都比他大
     * @param a 待排序数组
     * @param left 待排序左边界
     * @param right 待排序右边界
     * @return 基准位置
     */
    private int randomPartition(int[] a, int left, int right) {
        //随机化 right 位置的元素
        int randomIndex = new Random().nextInt(right - left + 1) + left;
        swap(a, randomIndex, right);

        //x 基准数，从 left 到 i 存放比基准数小的元素
        int x = a[right], i = left - 1;
        for(int j = left; j < right; j++){
            //如果当前元素比基准数小，就和当前 i 位置的元素交换位置
            if(a[j] <= x){
                i++;
                swap(a, i, j);
            }
        }
        //最后将基准数移到 i 位置上
        swap(a, ++i ,right);
        return i;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
        RandomQuickSort rq = new RandomQuickSort();
        rq.randomQuickSort(a);
        System.out.println(Arrays.toString(a));
    }

}
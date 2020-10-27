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
    private void sort(int[] a, int left, int right) {
        if(left < right){
            int p = randomPartition(a, left, right);
            sort(a, left, p - 1);
            sort(a, p + 1, right);
        }
    }

    private int randomPartition(int[] a, int left, int right) {
        int randomIndex = new Random().nextInt(right - left + 1) + left;
        swap(a, randomIndex, right);

        int x = a[right];
        return x;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = a[i];
    }

    public static void main(String[] args) {
        int[] a = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
        RandomQuickSort rq = new RandomQuickSort();
        rq.sort(a, 0, a.length - 1);

        System.out.println(Arrays.toString(a));;
    }

}
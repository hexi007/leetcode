package sort;

import java.util.Arrays;

/**
 * description 堆排序
 *
 * @author 27771
 * create 2020-10-28 21:40
 **/
public class HeapSort {

    private void sort(int[] a, int i, int i1) {
         for(int i = a.length / 2 - 1; i < )
    }

    public static void main(String[] args) {
        int[] a = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
        HeapSort rq = new HeapSort();
        rq.sort(a, 0, a.length - 1);

        System.out.println(Arrays.toString(a));
    }

}
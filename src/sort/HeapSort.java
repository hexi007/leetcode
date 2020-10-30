package sort;

import java.util.Arrays;

/**
 * description 堆排序
 *
 * @author 27771
 * create 2020-10-28 21:40
 **/
public class HeapSort {

    private void sort(int[] a) {
        int half = 2;
        for(int i = a.length / half - 1; i >= 0; i--){
            //调整大顶堆
            adjustHeap(a, i, a.length);
        }
        for(int i = a.length - 1; i > 0; i--){
            swap(a, 0, i);
            adjustHeap(a, 0, i);
        }
    }

    private void adjustHeap(int[] a, int index, int length) {
        int temp = a[index], doubleK = 2;
        for(int k = doubleK * index + 1; k < length; k = doubleK * k + 1){
            if(k + 1 < length && a[k] < a[k + 1]){
                k++;
            }
            if(a[k] > a[index]){
                swap(a, k, index);
                index = k;
            } else {
                break;
            }
        }
        a[index] = temp;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
        HeapSort rq = new HeapSort();
        rq.sort(a);

        System.out.println(Arrays.toString(a));
    }

}
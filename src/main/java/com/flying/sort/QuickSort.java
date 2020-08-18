package com.flying.sort;

public class QuickSort {
    private static int[] quickSort(int[] arr, int left, int right){
        int len = arr.length;
        if (len<1||left<0||right>=len||right>left) return null;
        int index = partition(arr,left,right);
        if (index>left) quickSort(arr, left,index-1);
        if (index<right) quickSort(arr,index+1,index);
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
//        int pivot =
        return 0;
    }
}

/**
 * https://blog.csdn.net/nrsc272420199/article/details/82587933
 * 从上面的过程中可以看到:
 *
 *   ①先从队尾开始向前扫描且当low < high时,如果a[high] > tmp,则high–,但如果a[high] < tmp,则将high的值赋值给low,即arr[low] = a[high],同时要转换数组扫描的方式,即需要从队首开始向队尾进行扫描了
 *   ②同理,当从队首开始向队尾进行扫描时,如果a[low] < tmp,则low++,但如果a[low] > tmp了,则就需要将low位置的值赋值给high位置,即arr[low] = arr[high],同时将数组扫描方式换为由队尾向队首进行扫描.
 *   ③不断重复①和②,知道low>=high时(其实是low=high),low或high的位置就是该基准数据在数组中的正确索引位置.
 */
class QuickSortBy {
    public static void main(String[] args) {

        int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low<high){
            int index = getIndex(arr, low, high);
            quickSort(arr,low,index-1);
            quickSort(arr,index+1, high);
        }
    }

    private static int getIndex(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low<high){
            while (low<high&&arr[high]>=temp) high--;
            arr[low]=arr[high];
            while (low<high&&arr[low]<=temp) low++;
            arr[high]=arr[low];
        }
        arr[low]=temp;
        return low;
    }

}

package com.flying.algorithm.niuke;

import java.util.*;

/**
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 *
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
 *
 * 测试样例：
 * [1,3,5,2,2],5,3
 * 返回：2
 * @author Administrator
 */
public class Finder {
    public int findKth(int[] a, int n, int K) {
        // write code here
        return find(a, 0, n - 1, K);
    }

    private int find(int[] a, int low, int high, int K) {
        int index = getIndex(a, low, high);
        if (index + 1 > K) {
            return find(a, low, index, K);
        } else if (index + 1 < K) {
            return find(a, index + 1, high, K);
        }
        return a[index];
    }

    // 从大到小
    private int getIndex(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] <= temp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] >= temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }
}

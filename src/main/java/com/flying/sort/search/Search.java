package com.flying.sort.search;

public class Search {
    public static void main(String[] args) {

    }
    //顺序查找
    int SequenceSearch(int a[], int value, int n)
    {
        int i;
        for(i=0; i<n; i++)
            if(a[i]==value)
                return i;
        return -1;
    }

    int BinarySearch(int a[], int value, int n){
        int low, high, mid;
        low=0;
        high=n-1;
        while (low<=high){
            //不能使用mid=(left+right)/2，应该使用mid=left+(right-left)/2。
            //因为如果left和right相加超过int表示的最大范围时就会溢出变为负数。
            mid=low + (high-low)/2;
            if (a[mid]==value){
                return mid;
            }
            if (a[mid]>value){
                high=mid-1;
            }
            if (a[mid]<value){
                low=mid+1;
            }
        }
        return -1;
    }
    int BinarySearch(int a[], int value, int left, int right){
        int mid = left+(right-left)/2;
        if (a[mid]==value){
            return mid;
        }
        if (a[mid]>value){
            BinarySearch(a,value,left,mid-1);
        }
        if (a[mid]<value){
            BinarySearch(a,value,mid+1,right);
        }
        return -1;
    }
    //3. 插值查找 对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多
    int InsertionSearch(int a[], int value, int left, int right){
        int mid = left+(right-left)*(value-a[left])/(a[right]-a[left]);
        if (a[mid]==value){
            return mid;
        }
        if (a[mid]>value){
            InsertionSearch(a,value,left,mid-1);
        }
        if (a[mid]<value){
            InsertionSearch(a,value,mid+1,right);
        }
        return -1;
    }
}

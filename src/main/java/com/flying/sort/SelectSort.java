package com.flying.sort;

public class SelectSort {

    public static void main(String[] args) {
        int[] data={1,2,3,4,6,3,2,1};
        sort(data);
        for(int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }

    }

    private static void sort(int[] data){
        if (data == null || data.length  == 0) {
            throw new RuntimeException("Empty input");
        }

        final int len = data.length;
        int minIndex = 0;
        int temp = 0;


        for(int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
        }
    }
}

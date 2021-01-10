package com.flying.interviewer;


/**
 * 基础题
 * i:10 j:5
 * i:10 j:5
 */
public class PartAa {
    public static void main(String[] args) {
        int i = 10;
        int j = 5;
        PartAa partAa = new PartAa();
        System.out.println("i:" + i + " j:" + j);
        partAa.doSwap(i, j);
        System.out.println("i:" + i + " j:" + j);
    }

    public void doSwap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }
}

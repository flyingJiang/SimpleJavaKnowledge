package com.flying.didi.Q2;

/**
 * 值传递
 */
public class Test {
    public static void main(String[] args) {
//        int i = 10;
//        int j = 5;
        Integer i = 10;
        Integer j = 5;
        Test test = new Test();
        System.out.println("i:" + i + " j:" + j);
        test.doSwap(i, j);
        System.out.println("i:" + i + " j:" + j);
    }
    public void doSwap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }
}

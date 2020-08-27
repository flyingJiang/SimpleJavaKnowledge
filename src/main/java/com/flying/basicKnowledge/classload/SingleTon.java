package com.flying.basicKnowledge.classload;


public class SingleTon {
//    public static int count1;
//    public static int count2 = 0;
    private static SingleTon singleTon = new SingleTon();

    public static int count1;
    public static int count2 = 0;

    private SingleTon() {
//        System.out.println("count1=" + count1);
//        System.out.println("count2=" + count2);
        count1++;
        count2++;
//        System.out.println("count1=" + count1);
//        System.out.println("count2=" + count2);
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}

class Test {
    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("11count1=" + singleTon.count1);
        System.out.println("11count2=" + singleTon.count2);
    }

}
/***
 * count1=1
 * count2=0
 */

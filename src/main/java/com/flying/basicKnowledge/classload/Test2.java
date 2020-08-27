package com.flying.basicKnowledge.classload;


class SuperClass1 {
    public static int count1;
    public static int count2 = 0;
    public SuperClass1() {
        System.out.println("count1=" + count1);
        System.out.println("count2=" + count2);
        count1++;
        count2++;
        System.out.println("count1=" + count1);
        System.out.println("count2=" + count2);
    }
}

public class Test2 {
    public static void main(String[] args) {
        SuperClass1 superClass = new SuperClass1();
        System.out.println("count1=" + superClass.count1);
        System.out.println("count2=" + superClass.count2);
    }

}

package com.flying.basicKnowledge.classload;

/**
 * 1.	子类调用父类的静态变量，子类不会被初始化。只有父类被初始化。。对于静态字段，只有直接定义这个字段的类才会被初始化.
 * 2.	通过数组定义来引用类，不会触发类的初始化
 * 3.	访问类的常量，不会初始化类
 */

class SuperClass {
    static {
        System.out.println("superclass init");
    }
    public static int value = 123;
}

class SubClass1 extends SuperClass {
    static {
        System.out.println("subclass init");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(SubClass1.value);// 被动应用1
        SubClass[] sca = new SubClass[10];// 被动引用2
        SubClass subClass = new SubClass();
    }
}

//class ConstClass {
//    static {
//        System.out.println("ConstClass init");
//    }
//    public static final String HELLOWORLD = "hello world";
//}
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(ConstClass.HELLOWORLD);// 调用类常量
//    }
//}

package com.flying.com.didi.Q4;

/**
 * 异常
 * Test1 无法捕获 Test2 抛出的 new Throwable() 异常， 因为Throwable是Exception的父类
 */
public class Test {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
    }
}
class Test1 {
    Test2 test2 = new Test2();
    public void test() {
        try {
            test2.show();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

class Test2 {
    public Test2() {
    }
    public void show() {
        try {
            throw new Exception();
        } catch (Exception e) {
//            throw new Throwable();
        }
    }
}

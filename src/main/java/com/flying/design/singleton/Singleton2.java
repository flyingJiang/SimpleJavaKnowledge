package com.flying.design.singleton;

/**
 * 饿汉式，从名字上也很好理解，就是“比较勤”，实例在初始化的时候就已经建好了，不管你有没有用到，都先建好了再说。好处是没有线程安全的问题，坏处是浪费内存空间。
 */
public class Singleton2 {
    private static Singleton2 instance = new Singleton2();
    public static Singleton2 getInstance(){
        return instance;
    }
    private Singleton2(){}
}

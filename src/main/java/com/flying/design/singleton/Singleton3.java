package com.flying.design.singleton;

/**
 * 静态内部类的方式效果类似双检锁，但实现更简单。但这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
 */
public class Singleton3 {
    private static class SingletonHolder {
        private static final Singleton3 INSTANCE = new Singleton3();
    }
    public static final Singleton3 getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private Singleton3(){}
}

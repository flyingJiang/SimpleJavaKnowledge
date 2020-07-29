package com.flying.zijie;

/**
 * 问：多线程1调用A，2调用B，互相会阻塞吗？
 * 答：会的,static synchronized锁定的是类文件
 */
public class Q1 {
    static synchronized void A(){}
    static synchronized void B(){}
}

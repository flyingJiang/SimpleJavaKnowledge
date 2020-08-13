package com.flying.com.zijie;

/**
 * 问：n是线程安全的吗？
 * 答：是的，volatile保证可见性和防止重排序
 */
public class Q2 {
    volatile int n = 1;
}

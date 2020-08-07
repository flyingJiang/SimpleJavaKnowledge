package com.flying.pre58.thread;

/**
 * 如何设置线程池
 *
 * 拒绝策略
 *
 * JVM如何利用CAS，实现轻量级锁。
 *
 * 如何保证变量的原子性
     * CAS原理：
     *       通过查看AtomicInteger的源码可知，
     *        `private volatile int value;
     *
     * public final boolean compareAndSet(int expect, int update) {
     *                     return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
     *                } `
     *通过申明一个volatile （内存锁定，同一时刻只有一个线程可以修改内存值）类型的变量，
     * 再加上unsafe.compareAndSwapInt的方法，来保证实现线程同步的。
     *从内存领域来说这是乐观锁，因为它在对共享变量更新之前会先比较当前值是否与更新前的值一致，如果是，则更新，
     * 如果不是，则无限循环执行(称为自旋)，直到当前值与更新前的值一致为止，才执行更新。
 *
 *
 */
public class Test {
}

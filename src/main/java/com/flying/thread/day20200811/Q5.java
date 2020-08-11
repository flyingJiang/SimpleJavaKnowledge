package com.flying.thread.day20200811;
/**
 * 第五：ThreadLocal有没有内存泄漏的问题？为什么？
 * https://blog.csdn.net/qq_33404395/article/details/82356344
 *
 * ThreadLocal作用和原理分析：
 * ThreadLocal主要为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。要理解ThreadLocal需要理解下面三个问题：
 *
 * ①、每个线程的变量副本是存储在哪里的？（参考ThreadLocal的get()源码）
 * 每个线程都有一个threadLocals成员，引用类型是ThreadLocalMap，以ThreadLocal和ThreadLocal对象声明的变量类型作为参数。这样，我们所使用的ThreadLocal变量的实际数据，通过get函数取值的时候，就是通过取出Thread中threadLocals引用的map，然后从这个map中根据当前threadLocal作为参数，取出数据。也就是说其实不同线程取到的变量副本都是由线程本身的提供的，存储在线程本身，只是借助ThreadLocal去获取，不是存放于 ThreadLocal。
 *
 * ②、变量副本【每个线程中保存的那个map中的变量】是怎么声明和初始化的？
 * 当线程中的threadLocals成员是null的时候，会调用ThreadLocal.createMap(Thread t, T firstValue)创建一个map。同时根据函数参数设置上初始值。也就是说，当前线程的threadlocalmap是在第一次调用set的时候创建map并且设置上相应的值的。
 * 在每个线程中，都维护了一个threadlocals对象，在没有ThreadLocal变量的时候是null的。一旦在ThreadLocal的createMap函数中初始化之后，这个threadlocals就初始化了。以后每次ThreadLocal对象想要访问变量的时候，比如set函数和get函数，都是先通过getMap(Thread t)函数，先将线程的map取出，然后再从这个在线程（Thread）中维护的map中取出数据或者存入对应数据。
 *
 * ③、不同的线程局部变量，比如说声明了n个(n>=2)这样的线程局部变量threadlocal，那么在Thread中的threadlocals中是怎么存储的呢？threadlocalmap中是怎么操作的？
 * 在ThreadLocal的set函数中，可以看到，其中的map.set(this, value)把当前的threadlocal传入到map中作为键，也就是说，在不同的线程的threadlocals变量中，都会有一个以你所声明的那个线程局部变量threadlocal作为键的key-value。假设说声明了N个这样的线程局部变量变量，那么在线程的ThreadLocalMap中就会有n个分别以你的线程局部变量作为key的键值对。
 *
 * ThreadLocal为什么会内存泄漏？
 * 这里写图片描述
 *
 * ThreadLocal的实现是这样的：每个Thread 维护一个 ThreadLocalMap 映射表，这个映射表的 key 是 ThreadLocal实例本身，value 是真正需要存储的 Object。
 * 也就是说 ThreadLocal 本身并不存储值，它只是作为一个 key 来让线程从 ThreadLocalMap 获取 value。
 * 值得注意的是图中的虚线，表示 ThreadLocalMap 是使用 ThreadLocal 的弱引用作为 Key 的，弱引用的对象在 GC 时会被回收。
 *
 * ThreadLocalMap使用ThreadLocal的弱引用作为key，如果一个ThreadLocal没有外部强引用来引用它，那么系统 GC 的时候，这个ThreadLocal势必会被回收，这样一来，ThreadLocalMap中就会出现key为null的Entry，就没有办法访问这些key为null的Entry的value，如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链：Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value永远无法回收，造成内存泄漏。
 *
 * ThreadLocal如何防止内存泄漏？
 * 每次使用完ThreadLocal，都调用它的remove()方法，清除数据。
 * 在使用线程池的情况下，没有及时清理ThreadLocal，不仅是内存泄漏的问题，更严重的是可能导致业务逻辑出现问题。所以，使用ThreadLocal就跟加锁完要解锁一样，用完就需要清理。
 *
 *
 */
public class Q5 {
}

package com.flying.com.xiaomi.q6.demo2;


/**
 * 当一个线程访问对象的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块。
 * A:0
 * Bcount:1
 * A:1
 * Bcount:2
 * A:2
 * Bcount:3
 * A:3
 * Bcount:4
 * A:4
 * Bcount:5
 * 上面代码中countAdd是一个synchronized的，printCount是非synchronized的。
 * 从上面的结果中可以看出一个线程访问一个对象的synchronized代码块时，别的线程可以访问该对象的非synchronized代码块而不受阻塞。
 */
public class Main {
    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "A");
        Thread thread2 = new Thread(syncThread, "B");
        thread1.start();
        thread2.start();
    }

}

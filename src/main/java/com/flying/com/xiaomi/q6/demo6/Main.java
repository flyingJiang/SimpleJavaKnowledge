package com.flying.com.xiaomi.q6.demo6;

/**
 * SyncThread1:0
 * SyncThread1:1
 * SyncThread1:2
 * SyncThread1:3
 * SyncThread1:4
 * SyncThread2:5
 * SyncThread2:6
 * SyncThread2:7
 * SyncThread2:8
 * SyncThread2:9
 *
 * 其效果和【Demo5】是一样的，synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁。
 */
public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
        Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
        thread1.start();
        thread2.start();

    }
}
/**
 * 同步线程
 */
class SyncThread implements Runnable {
    private static int count;

    public SyncThread() {
        count = 0;
    }

    public static void method() {
        synchronized(SyncThread.class) {
            for (int i = 0; i < 5; i ++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void run() {
        method();
    }
}

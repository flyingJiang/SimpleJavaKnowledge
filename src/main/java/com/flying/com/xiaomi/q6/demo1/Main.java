package com.flying.com.xiaomi.q6.demo1;

/**
 * 一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞。我们看下面一个例子：
 *
 * 当两个并发线程(thread1和thread2)访问同一个对象(syncThread)中的synchronized代码块时，在同一时刻只能有一个线程得到执行，
 * 另一个线程受阻塞，必须等待当前线程执行完这个代码块以后才能执行该代码块。Thread1和thread2是互斥的，
 * 因为在执行synchronized代码块时会锁定当前的对象，只有执行完该代码块才能释放该对象锁，下一个线程才能执行并锁定该对象。
 * s1:0
 * s1:1
 * s1:2
 * s1:3
 * s1:4
 * s2:5
 * s2:6
 * s2:7
 * s2:8
 * s2:9
 * 我们再把SyncThread的调用稍微改一下：
 * s1:0
 * s2:1
 * s1:2
 * s1:3
 * s1:4
 * s1:5
 * s2:6
 * s2:7
 * s2:8
 * s2:9
 * 这时创建了两个SyncThread的对象syncThread1和syncThread2，线程thread1执行的是syncThread1对象中的synchronized代码(run)，
 * 而线程thread2执行的是syncThread2对象中的synchronized代码(run)；我们知道synchronized锁定的是对象，
 * 这时会有两把锁分别锁定syncThread1对象和syncThread2对象，而这两把锁是互不干扰的，不形成互斥，所以两个线程可以同时执行。
 */
public class Main {
    public static void main(String[] args) {
//        SyncThread syncThread = new SyncThread();
//        Thread thread1 = new Thread(syncThread, "s1");
//        Thread thread2 = new Thread(syncThread, "s2");
//        thread1.start();
//        thread2.start();
        Thread thread1 = new Thread(new SyncThread(), "s1");
        Thread thread2 = new Thread(new SyncThread(), "s2");
        thread1.start();
        thread2.start();
    }

}

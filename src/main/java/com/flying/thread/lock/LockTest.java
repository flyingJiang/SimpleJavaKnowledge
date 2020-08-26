package com.flying.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockTest {
    public static void main(String[] args) {
        TestSynchronizedMethod testSynchronized = new TestSynchronizedMethod();
        TestSynchronizedMethod testSynchronized2 = new TestSynchronizedMethod();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 3000; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        testSynchronized.print();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            service.execute(new Runnable() {
                @Override
                public void run() {
                    testSynchronized2.printK();
                }
            });
//            service.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        testSynchronized2.print();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        }
        service.shutdown();
    }
}

class TestSynchronized {
    private Integer n = 0;

    public void print() throws InterruptedException {
        synchronized (n) {
            n++;
            System.out.println(Thread.currentThread().getName() + ": " + n);
        }
    }

    public void printK() {
        n++;
        System.out.println(Thread.currentThread().getName() + ": " + n);
    }
}

class TestSynchronizedMethod {
    private int n = 0;

    public synchronized void print() throws InterruptedException {
        n++;
        System.out.println(Thread.currentThread().getName() + ": " + n);
    }

    public synchronized void printK() {
        n++;
        System.out.println(Thread.currentThread().getName() + ": " + n);
    }
}

class TestSynchronizedStaticMethod {
    private static int n = 0;

    public synchronized static void print() throws InterruptedException {
//        Thread.sleep(1000);
        n++;
        System.out.println(Thread.currentThread().getName() + ": " + n);
    }

    public void printK() {
        n++;
        System.out.println(Thread.currentThread().getName() + ": " + n);
    }
}
